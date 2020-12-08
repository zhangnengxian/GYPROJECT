package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.ConstructionUnitDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.entity.ConstructionUnit;
import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditDoneService;
import cc.dfsoft.project.biz.base.subpackage.converter.SubContractConverter;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSafeContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractDTO;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSafeContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ConstructionUnitServiceImpl implements ConstructionUnitService{
	
	@Resource
	ConstructionUnitDao constructionUnitDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	@Resource
	SettlementAuditDoneService settlementAuditDoneService;
	
	/**分包补充协议*/
	@Resource
	SubSupplyContractDao subSupplyContractDao;
	
	/**分包协议Dao*/
	@Resource
	SubContractDao subContractDao;
	/**安全协议Dao*/
	@Resource
	SubSafeContractDao subSafeContractDao;
	
	@Override
	public Map<String, Object> queryConstructionUnit(ConstructionUnitReq constructionUnitReq) throws ParseException {
		
		return constructionUnitDao.queryConstructionUnit(constructionUnitReq);
	}

	@Override
	public ConstructionUnit viewConstructionUnitById(String id) {
		return constructionUnitDao.viewConstructionUnitById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveConstructionUnit(ConstructionUnit constructionUnit) {
		if(StringUtil.isNoneBlank(constructionUnit.getCuId())){
			constructionUnitDao.update(constructionUnit);
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			constructionUnit.setCuId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
			constructionUnitDao.save(constructionUnit);
			return Constants.OPERATE_RESULT_SUCCESS;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteConstructionUnit(String id) {
		constructionUnitDao.delete(id);
	}

	@Override
	public SubContractDTO viewSubAgreementContract(String id) {
		ConstructionPlan plan=constructionPlanDao.viewPlanById(id);
		SubContractDTO convertDto = SubContractConverter.convert(plan);
		Project project = projectDao.get(plan.getProjId());
		convertDto.setScAmount(project.getSubmitAmount());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		convertDto.setConAgent(loginInfo.getStaffName());
		BusinessPartners bp = businessPartnersDao.get(convertDto.getCuId());//分包单位id
		SettlementDeclaration sd = settlementAuditDoneService.auditStartDetail(project.getProjId());
		//资格证相关属性赋值
		convertDto.setCertificationNo(bp.getQualificationNo()); 	//资格证编号
		convertDto.setCertificationName(bp.getQualificationName()); //资格证名称
		convertDto.setCuPm(bp.getUnitManager()); 					//项目经理
		convertDto.setCuPmPhone(bp.getUnitMobile()); 				//联系电话
		convertDto.setCuLegalRepresent(bp.getUnitDirector()); 		//负责人
		//convertDto.setEndDeclarationCost(sd.getEndDeclarationCost()); //终审金额
		
		SubSupplyContract ssc=subSupplyContractDao.findByProjId(project.getProjId());
		if(null !=ssc){
			BeanUtil.copyNotNullProperties(convertDto,ssc);
		}
		return convertDto;
	}

	@Override
	public SubContractDTO viewsubContract(String id) {
		try {
			
			ConstructionPlan plan=constructionPlanDao.viewPlanById(id);
			SubContractDTO convertDto = SubContractConverter.convert(plan);
			convertDto.setGasComLegalRepresent(plan.getBuilder());//甲方代表
			convertDto.setGasComPhone(plan.getBulTel());//联系电话
			Project project = projectDao.get(plan.getProjId());
			convertDto.setScAmount(project.getSubmitAmount());
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			convertDto.setConAgent(loginInfo.getStaffName());
			//convertDto.setScNo(project.getProjNo());   //设置分包合同编号为工程编号
			BusinessPartners bp = businessPartnersDao.get(convertDto.getCuId());//分包单位id
			//资格证相关属性赋值
			convertDto.setCertificationNo(bp.getQualificationNo()); 	//资格证编号
			convertDto.setCertificationName(bp.getQualificationName()); //资格证名称
			convertDto.setCuPm(bp.getUnitManager()); 					//项目经理
			convertDto.setCuPmPhone(bp.getUnitMobile()); 				//联系电话
			convertDto.setCuLegalRepresent(bp.getUnitDirector()); 		//负责人
			SubContract subContract = subContractDao.findSubContractByprojId(convertDto.getProjId());
			if(null !=subContract){
				BeanUtil.copyNotNullProperties(convertDto,subContract);
			}
			return convertDto;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SubContractDTO viewsubSafe(String id) {
		try {
		ConstructionPlan plan=constructionPlanDao.viewPlanById(id);
		SubContractDTO convertDto = SubContractConverter.convert(plan);
		BusinessPartners bp = businessPartnersDao.get(convertDto.getCuId());//分包单位id
		convertDto.setCuPmPhone(bp.getUnitMobile()); 				//联系电话
		convertDto.setCuLegalRepresent(bp.getUnitDirector()); 		//负责人
		SubSafeContract subSafeContract;
		subSafeContract = subSafeContractDao.findSubSafeContractByprojId(convertDto.getProjId());
		if(null !=subSafeContract){
			BeanUtil.copyNotNullProperties(convertDto,subSafeContract);
		}
		return convertDto;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
