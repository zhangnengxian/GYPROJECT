package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.complete.dao.IgniteDao;
import cc.dfsoft.project.biz.base.complete.dto.IgniteReq;
import cc.dfsoft.project.biz.base.complete.entity.Ignite;
import cc.dfsoft.project.biz.base.complete.service.IgniteService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractSuIsPrintEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.withgas.dao.GasProjectDao;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.dialect.Ingres10Dialect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class IgniteServiceImpl implements IgniteService {

	@Resource
	IgniteDao igniteDao;

	@Resource
	GasProjectDao gasProjectDao;

	@Resource
	ContractDao contractDao;

	@Resource
	ConstructionPlanDao constructionPlanDao;

	@Resource
	ProjectTypeDao projectTypeDao;

	@Resource
	SignatureService signatureService;

	@Resource
	ProjectDao projectDao;
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void igniteSave(Ignite ignite) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(ignite.getIgniteId())){
			flag = true;
			ignite.setIgniteId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		ignite.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		igniteDao.saveOrUpdate(ignite);
		if(ignite.isPushFlag()==true){
			GasProject gasProject = gasProjectDao.get(ignite.getGprojId());
			//如果通气还没有审核通过,不修改通气状态
			if(gasProject.getGasProjStatus().equals(GasProjectStatusEnum.GAS_AUDIT_DONE.getValue())){
				gasProject.setGasProjStatus(GasProjectStatusEnum.IGNITE.getValue());//工程为点火状态
			}
			gasProject.setIsSignIgnite(ContractSuIsPrintEnum.ALREADY_PRINT.getValue());//已签订点火单
			gasProjectDao.save(gasProject);
		}
		//保存签字
		signatureService.saveOrUpdateSign("menuId+menuName+3",ignite.getSign(), ignite.getProjId(), ignite.getIgniteId(), ignite.getClass().getName(), flag);
	}

	@Override
	public Map<String, Object> queryIgnite(IgniteReq igniteReq) throws ParseException {
		Map<String, Object> map = igniteDao.queryIgnite(igniteReq);
		List<Ignite> list = (List<Ignite>) map.get("data");
		if(list!=null && list.size()>0){
			for(Ignite ignite : list){
				GasProject gasProject = gasProjectDao.get(ignite.getGprojId());
				if(gasProject!=null){
					ignite.setAcceptDate(gasProject.getAcceptDate());
					ignite.setAcceptType(gasProject.getAcceptType());
				}
			}
		}
		return map;
	}

	@Override
	public Ignite viewByGprojId(String gprojId) {
		Ignite ignite = igniteDao.viewByGprojId(gprojId);   //通过id得到实体
		if(null!=ignite){
			return ignite;
		}else{
			Ignite noIgnite = new Ignite();
			GasProject gasProject = gasProjectDao.get(gprojId);
			//查合同
			Contract contract = contractDao.viewContractByprojId(gasProject.getProjId());
			//查工程
			Project project = projectDao.get(gasProject.getProjId());
			//分合同
			ConstructionPlan constructionPlan = constructionPlanDao.findByProjId(gasProject.getProjId());
			if(constructionPlan!=null){
				noIgnite.setBuTel(constructionPlan.getBulTel());
			}
			noIgnite.setGprojId(gasProject.getGprojId());
			noIgnite.setProjId(gasProject.getProjId());
			noIgnite.setProjNo(gasProject.getProjNo());
			noIgnite.setProjAddr(gasProject.getProjAddr());
			noIgnite.setProjScaleDes(gasProject.getProjScaleDes());
			noIgnite.setDeptName(gasProject.getDeptName());
			noIgnite.setProjName(gasProject.getProjName());
			noIgnite.setProjLtypeId(gasProject.getProjLtypeId());
			noIgnite.setProjType(projectTypeDao.get(gasProject.getProjLtypeId()).getProjTypeDes());
			noIgnite.setCuName(gasProject.getCuName());
			noIgnite.setBuilder(gasProject.getGasComLegalRepresent());//现场代表
			noIgnite.setCustName(project.getCustName());              //客户名称custContact;
			noIgnite.setAgent(project.getCustContact());              //经办人
			noIgnite.setAgentPhone(project.getCustPhone());           //经办人联系电话
			if(SessionUtil.getLoginInfo().getPost().equals(PostTypeEnum.GROUP_LEADER.getValue())){  //如果是技术组组长
				noIgnite.setLister(SessionUtil.getLoginInfo().getStaffName());
			}
			noIgnite.setScNo(gasProject.getScNo());
			noIgnite.setPlanGasEndDate(gasProject.getPlanGasEndDate());//计划通气结束时间
			noIgnite.setPlanGasDate(gasProject.getPlanGasDate());//计划通气时间
			if(contract!=null){
				noIgnite.setIgniteNo(contract.getConNo());
				noIgnite.setHouseholds(contract.getHousehold()); //协议户数
			}
		return noIgnite;
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signIgnitePrint(String igniteId) {
		Ignite ignite =igniteDao.get(igniteId);
		if(ignite!=null){
			//标记已打印
			ignite.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			igniteDao.update(ignite);
		}
	}
}
