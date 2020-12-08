package cc.dfsoft.project.biz.base.complete.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.complete.dao.GasApplyDao;
import cc.dfsoft.project.biz.base.complete.dto.GasApplyReq;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.project.biz.base.complete.enums.ConfrimStateEnum;
import cc.dfsoft.project.biz.base.complete.service.GasApplyService;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeSCEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ActionEnum;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.service.NoticeService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class GasApplyServiceImpl implements GasApplyService{
	
	/**通气申请dao*/
	@Resource
	GasApplyDao  gasApplyDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	SubContractDao subContractDao;
	
	/**应收应付流水 service*/
	@Resource
	AccrualsRecordService accrualsRecordService;
	

	@Resource
	SignatureService signatureService;

	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	NoticeService noticeService;

	/**
	 * 保存通气确认 
	 * @author flw
	 * @createTime 2016-1-12
	 * @param  ga
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveGasApply(GasApply ga) throws Exception {
		boolean flag = false;
		//保存通气申请
		if(StringUtils.isBlank(ga.getGaId())){
			flag = true;
			String gaId=IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
			ga.setGaId(gaId);
		}
		LoginInfo lofinInfo=SessionUtil.getLoginInfo();
		ga.setGasApplyCsr(lofinInfo.getStaffName());
		ga.setApplyCsrId(lofinInfo.getStaffId());
		//ga.setGasApplyTime(gasApplyDao.getDatabaseDate());
		ga.setConfrimState(ConfrimStateEnum.TO_FEEDBACK.getValue());
		gasApplyDao.saveOrUpdate(ga);
		
		//推送
		if("1".equals(ga.getFlag())){
			//翻转工程状态
			Project pro=projectDao.get(ga.getProjId());
			String id=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.AERATE_APPLY.getActionCode());
			pro.setProjStatusId(id);
			projectDao.update(pro);
			
			//形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);//生成唯一ID
			//operateRecordService.createOperateRecord(orId, ga.getProjId(), ga.getProjNo(), StepEnum.GAS_CONFIRM.getValue(), StepEnum.GAS_CONFIRM.getMessage(), "");
			//生成流水
			String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
			SubContract subContract = subContractDao.findSubContractByprojId(pro.getProjId());
			String mes = PayTypeSCEnum.FULLY_PAID_UP.getMessage();
			BigDecimal amount = subContract.getScAmount().multiply(new BigDecimal(mes.substring(0, mes.length()-1)).divide(new BigDecimal("100")));
			accrualsRecordService.insertAccrualsRecord(pro,arId, CollectionTypeEnum.GAS_CONFIRM.getValue(),Integer.parseInt(ARFlagEnum.ACCOUNTS_PAY.getValue()), amount,null,null);
		}
		signatureService.saveOrUpdateSign("menuId+menuName+2",ga.getSign(), ga.getProjId(), ga.getGaId(), ga.getClass().getName(), flag);
	
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public Map<String, Object> queryGasApply(GasApplyReq gasApplyReq) throws ParseException {
		
		return gasApplyDao.queryGasApply(gasApplyReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String updateGasApply(GasApply gasApply) {
		if(gasApply!=null){
			LoginInfo lofinInfo=SessionUtil.getLoginInfo();
			gasApply.setConfrimGasCsr(lofinInfo.getStaffName());
			gasApply.setConfrimCsrid(lofinInfo.getStaffId());
			gasApply.setConfrimState(ConfrimStateEnum.FEEDBACK.getValue());
			gasApplyDao.update(gasApply);
			
			//生成通知
			String projId = gasApply.getProjId();
			Date databaseDate = gasApplyDao.getDatabaseDate(); 
			Date cgTime = gasApply.getConfirmGasTime();  //通气时间
			if (databaseDate.after(cgTime)) { 
				noticeService.updateNotice(projId, ActionEnum.GAS_FEEDBACK.getValue());  //设置通知失效
			}else {
				// 下达通气通知
				if (!noticeService.checkExist(projId, ActionEnum.GAS_FEEDBACK.getValue())) {  // 如果不存在该活动的通知则下达
					noticeService.saveNotice(projId ,gasApply.getProjName(),cgTime
							,ActionEnum.GAS_FEEDBACK.getMessage().substring(0, 2),ActionEnum.GAS_FEEDBACK.getValue());
				}
			}
			return Constants.OPERATE_RESULT_SUCCESS;
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}

	@Override
	public GasApply gasApplyDetail(String gaId) {
		GasApply gasApply = gasApplyDao.get(gaId);
		ConstructionPlan con = constructionPlanDao.viewPlanById(gasApply.getProjId());
		Project pro = projectDao.get(gasApply.getProjId());
		List<SubContract> subContracts = subContractDao.findByProjNo(gasApply.getProjNo());
		SubContract su = new SubContract();
		if(subContracts.size()>0){
			su = subContracts.get(0);
		}
		gasApply.setProjScaleDes(su.getProjScaleDes());
		gasApply.setProjAddr(pro.getProjAddr());
		gasApply.setAreaDes(pro.getAreaDes());
		gasApply.setCustName(pro.getCustName());//建设单位
		gasApply.setCustContact(pro.getCustContact());//联系人
		gasApply.setCustPhone(pro.getCustPhone());//联系方式
		gasApply.setBuilder(con.getBuilder());//施工员
		gasApply.setCuPm(su.getCuPm());
		gasApply.setScAmount(su.getScAmount());
		
		return gasApply;
	}

}
