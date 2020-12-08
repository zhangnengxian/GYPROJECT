package cc.dfsoft.project.biz.base.project.service.impl;

import cc.dfsoft.project.biz.base.accept.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.WorkFlowDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.budget.dao.GovAuditCostDao;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.budget.enums.BudgetAdjustEnum;
import cc.dfsoft.project.biz.base.budget.enums.GovAuditCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.charge.service.ChargeService;
import cc.dfsoft.project.biz.base.complete.dao.DivisionalAcceptanceApplyDao;
import cc.dfsoft.project.biz.base.complete.dao.JointAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.constructmanage.dao.EngineeringVisaDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.enums.*;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.dao.*;
import cc.dfsoft.project.biz.base.contract.entity.*;
import cc.dfsoft.project.biz.base.contract.enums.*;
import cc.dfsoft.project.biz.base.contract.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractLogService;
import cc.dfsoft.project.biz.base.contract.service.ImcContractLogService;
import cc.dfsoft.project.biz.base.contract.service.IntelligentSupplementService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.dao.InstTasksDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.entity.InstTasks;
import cc.dfsoft.project.biz.base.project.dao.*;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.project.biz.base.settlement.dao.DrawingApplyDao;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementJonintlySignDao;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.enums.SettlementAuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.service.SettlementJonintlySignService;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.PaymentApplyDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubBudgetDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.*;
import cc.dfsoft.project.biz.base.subpackage.enums.FeeTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.SubBudgetIsPrintEnum;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.base.withgas.dao.GasProjectDao;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceContractTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialTableTypeEnum;
import cc.dfsoft.project.biz.ifs.material.service.MaterialNcService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 管理记录服务接口实现
 * @author pengtt
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ManageReordServiceImpl implements ManageRecordService{


	@Resource
	ManageRecordDao manageRecordDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	StaffDao staffDao;
	
	@Resource
	OperateRecordService operateRecordService;

    /**带气工程*/
    @Resource
    GasProjectDao gasProjectDao;
	
	/**合同dao*/
	@Resource
	ContractDao contractDao;
	
	/**应收应付流水service*/
	@Resource
	AccrualsRecordDao accrualsRecordDao;
	/**签字记录*/
	@Resource
	SignatureDao signatureDao;
	
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	/**分包合同接口*/
	@Resource
	SubContractService subContractService;
	/** 工作流接口*/
	@Resource
	WorkFlowService workFlowService;
	/**签证记录*/
	@Resource
	EngineeringVisaDao engineeringVisaDao;
	/** 工程计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**电子图审核申请Dao*/
	@Resource
	DrawingApplyDao drawingApplyDao;
	
	/**分部验收申请Dao*/
	@Resource
	DivisionalAcceptanceApplyDao divisionalAcceptanceApplyDao;
	
	/**付款申请Dao*/
	@Resource
	PaymentApplyDao paymentApplyDao;
	
	/**工程造价dao*/
	@Resource
	ProjectCostDao projCostDao;
	@Resource
	BudgetDao budgetDao;
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	/**延期申请*/
	@Resource
	ApplyDelayDao applyDelayDao;
	
	/**操作日志接口*/
	@Resource
	OperateRecordLogService operateRecordLogService;
	/**合同修改日志接口*/
	@Resource
	ContractLogService contractLogService;
	
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;

	@Resource
	IntelligentMeterContractDao intelligentMeterContractDao;

	@Resource
	ImcContractLogService imContractLogService;
	
	@Resource
	IntelligentMeterContractService intelligentMeterContractService;
	
	@Resource
	ImcContractLogDao imcContractLogDao;
	
	/**回退申请*/
	@Resource
	FallbackApplyService fallbackApplyService;
	
	/**设计信息Dao*/
	@Resource
	DesignInfoDao designInfoDao;
	
	/**政府审定价*/
	@Resource
	GovAuditCostDao govAuditCostDao;
	
	/**操作记录Dao*/
	@Resource
	OperateRecordDao operateRecordDao;
	
	
	/**回退申请Dao*/
	@Resource
	FallbackApplyDao fallbackApplyDao;
	
	/**审核信息*/
	@Resource
	DocTypeService docTypeService;
	
	/**回退信息备份*/
	@Resource
	BackInfoDao backInfoDao;
	
	/**施工预算*/
	@Resource
	SubBudgetDao subBudgetDao;
	/**协议审核*/
	@Resource
	SupplementalContractDao supplementalContractDao;
	
	/**补充协议*/
	@Resource
	ChangeManagementDao changeManagementDao;
	/**物料信息同步接口*/
	@Resource
	MaterialNcService materialNcService;
	
	/**部门服务*/
	@Resource
	DepartmentService departmentService;
	
	/**签字通知*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	SubContractDao subContractDao;
	@Resource
	ChangeManagementService changeManagementService;
	@Resource
	EngineeringVisaService engineeringVisaService;
	@Resource
	ProjectService projService;
	@Resource
	SettlementJonintlySignService settlementJonintlySignService;
	@Resource
	SignatureService signatureService;
	@Resource
	SettlementJonintlySignDao settlementJonintlySignDao;
	@Resource
	CashFlowDao cashFlowDao;
	@Resource
	ChargeService chargeService;
	@Resource
	SupplementalContractModifyHistoryDao suppContractModifyhistoryDao;
	@Resource
	JointAcceptanceDao jointAcceptanceDao;
	@Resource
	DepartmentDao departmentDao;
	@Resource
	PayTypeDao payTypeDao;
	@Resource
	ProjectChecklistDao projectChecklistDao;
	
	@Resource
	SignNoticeDao signNoticeDao;
	@Resource
	WorkReportService workReportService;

	@Resource
	SurveyInfoDao surveyInfoDao;

	@Resource
	ProjectCostDao projectCostDao;

	@Resource
	WorkFlowDao workFlowDao;

	@Resource
	SignNoticeStandardDao signNoticeStandardDao;

	@Resource
	InstTasksDao instTasksDao;

	@Resource
	DocTypeDao docTypeDao;

	@Resource
	IntelligentSupplementDao isDao;
	@Resource
	IntelligentSupplementService isService;
	@Resource
	SignNoticeStandardService signNoticeStandardService;
	@Resource
	SynchronizedService synchronizedService;

	/**
	 * 查询审核历史
	 */
	@Override
	public Map<String, Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq) {
		Map<String,Object> result =  manageRecordDao.queryManageRecord(manageRecordQueryReq);
		List<ManageRecord> data = (List<ManageRecord>) result.get("data");
		if(data!=null && data.size()>0){
			for(int i=0;i<data.size();i++){
				Staff staff = staffDao.queryStaff("", data.get(i).getMrCsr());
				if(staff!=null){
					data.get(i).setMrCsr(staff.getStaffName());
				}
			}
		}
		result.put("data", data);
		return result;
	}
	/**
	 * 查询审核历史
	 */
	@Override
	public Map<String, Object> queryManageRecordSign(ManageRecordQueryReq manageRecordQueryReq) {
		Map<String,Object> result =  manageRecordDao.queryManageRecord(manageRecordQueryReq);
		List<ManageRecord> data = (List<ManageRecord>) result.get("data");
		if(data!=null && data.size()>0){
			for(int i=0;i<data.size();i++){
				Staff staff = staffDao.queryStaff("", data.get(i).getMrCsr());
				if(staff!=null){
					data.get(i).setMrCsr(staff.getStaffName());
				}
				List<Signature> signatures=signatureDao.findByProperties(data.get(i).getClass().getName(),"",data.get(i).getMrId(),"");
				if(signatures!=null&&signatures.size()>0){
					data.get(i).setSigns(signatures.get(0).getImgUrl());
				}
			}
		}
		result.put("data", data);
		return result;
	}
	/**
	 * 产生审核记录
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants ) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(constants));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(constants));
		}
		manageRecord.setDocTypeId(docTypeID);
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			Project project = projectDao.get(manageRecord.getProjId());
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),stepID, isNextStatus);
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(constants);
			operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(constants);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
			}
			//2.更新工程记录
			projectDao.update(project);
		}
	}

	@Override
	public java.util.Date getDatabaseDate() {
		return manageRecordDao.getDatabaseDate();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String contractAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * 4.逐级审核通过，产生应收流水
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id
		
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			//审核级别取配置表中的
			auditLevel = docType.getGrade();
		}
		
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		Project project = projectDao.get(manageRecord.getProjId());
		String toder="";
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
			//删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//结算的签字信息
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;

			//审核通过--将通知置为无效
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
			//生成电子签名
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.FIRST_SETTLEMENT_POST);
		}else{
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.FIRST_SETTLEMENT_POST);
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		/*notice.setNoticeContent(NoticeMenuEnum.gardeof(notice.getGrade(),NoticeMenuEnum.CONTRACT_AUDIT1.getType()).getMessage());
		noticeDao.saveOrUpdate(notice);*/
		
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String status =workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),stepID, isNextStatus);
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(Constants.MODULE_CODE_CONTRACT);
				//不通过 
				toder=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(toder);
			}
			//清空未通过标记
			if("1".equals(manageRecord.getMrResult())){
				toder=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
				project.setToDoer(toder);//待办人
				project.setSignBack("");
				//查合同首付款
				Contract contract=contractDao.viewContractByprojId(project.getProjId());
				contract.setIsPass(ContractIsPassEnum.ALREADY_PASS.getValue());
				String arId=IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
				//生产合同首付款
				accrualsRecordService.insertAccrualsRecord(project,arId,CollectionTypeEnum.INITIAL_PAYMENT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getFirstPayment(),null,contract.getConNo());
				//获取合同对的付款次数
				PayType payType = payTypeDao.get(contract.getPayType());
				//付款类型为两次付清
				//if(contract.getPayType().equals("2")||contract.getPayType().equals("4")||contract.getPayType().equals("6")){
				if(payType!=null && "2".equals(payType.getPayTypeMode())){
					accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),null,contract.getConNo());
				//付款类型为三次付清
				}else if(payType!=null && "3".equals(payType.getPayTypeMode())){
					accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),null,contract.getConNo());
					accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getThirdPayment(),null,contract.getConNo());
				}
				contractDao.update(contract);
				//生成合同尾款
//				BigDecimal balancePayment = project.getContractAmount().subtract(contract.getFirstPayment()); //尾款
//				if(!balancePayment.equals(BigDecimal.ZERO)){
//					accrualsRecordService.insertAccrualsRecord(project, IDUtil.getUniqueId(Constants.MODULE_CODE_COST), CollectionTypeEnum.BALANCE_PAYMENT.getValue(), 
//							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), balancePayment);
//				}
				projectDao.update(project);
				//合同审核最后一级通过后,调用工程信息同步接口todo:
				//1.同步工程信息
				if(projService.isToCall(project.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
					String operateType = FinanceOperateTypeEnum.CONTRACT_SIGN.getValue();
					if("1".equals(project.getMaterialFlag())){//存在借料-修改
						operateType = FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue();
					}
					ResultMessage resultMessage = new  ResultMessage();
					String msg = financeInfoService.synProjectInfoClient(manageRecord.getProjId(), operateType, UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				return  arId;
			}
			//2.更新工程记录
			projectDao.update(project);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	
	/**
	 * 协议审核保存
	 * @author wmy
	 * @createTime 2016-07-11
	 * @param manageRecord
	 * @return
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String supplementalContractAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * 4.逐级审核通过，产生应收流水1
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		SupplementalContract supplementalContract=supplementalContractDao.get(manageRecord.getBusinessOrderId());
		ChangeManagement changeManagement = new ChangeManagement();
		Budget budget= budgetDao.get(supplementalContract.getBudgetId());
		changeManagement=changeManagementDao.get(budget.getCmId());//获得设计变更
		
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}else{
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro,DesignChangeStateEnum.WAIT_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),new Staff(),nextGrade.toString(),true);
			
		}
		
		if(isNextStatus!=null){
			if("0".equals(manageRecord.getMrResult())){
				if(budget!=null){
					//处理变更、预算、补充协议
					budget.setIsSignSuContrct(IsSignEnum.HAVE_NOT_SIGN.getValue());//标记未签订
					budgetDao.update(budget);
				}
				if(changeManagement!=null){
					changeManagement.setDesignChangeType(DesignChangeStateEnum.WAIT_SUPPLEMENT_CONTRACT.getValue());//待签补充协议
					changeManagementDao.update(changeManagement);
				}

				supplementalContract.setIsAudit(supplementalContractIsAuditEnum.NOT_HAVE_PASS.getValue());//标记未通过
				supplementalContractDao.saveOrUpdate(supplementalContract);//保存协议
				//待签补充协议todo:
				operateRecordService.cerateCurOperateRecord(pro,changeManagement.getDesignChangeType(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),new Staff(),null,true);
				
			}
			if("1".equals(manageRecord.getMrResult())){

				if(budget!=null){
					budget.setIsSignSuContrct(IsSignEnum.ALRRADY_SIGN.getValue());//标记已签订
					budgetDao.update(budget);
				}
				if(changeManagement!=null){
					changeManagement.setDesignChangeType(DesignChangeStateEnum.ALREADY_FINISHED.getValue());//已完成
					changeManagementDao.update(changeManagement);
				}

				//生成流水-一条流水
				String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
				if(pro != null){
					accrualsRecordService.insertAccrualsRecord(pro,arId, CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue(),Integer.parseInt(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), supplementalContract.getScAmount(),ArContractTypeEnum.SUP_CONTRACT.getValue(),supplementalContract.getScNo());
				}
				supplementalContract.setIsAudit(supplementalContractIsAuditEnum.HAVE_PASS.getValue());//标为通过
				supplementalContractDao.saveOrUpdate(supplementalContract);//保存协议
				//将变更材料整合存入到材料清单表中
				boolean flags= changeManagementService.updateMaterialList(pro,changeManagement);
				if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
					ResultMessage resultMessage = new ResultMessage();
					//补充协议审核通过， 调用财务接口同步工程信息 -工程同步信息中没有补充协议信息,todo:
					String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.CONTRACT_SIGN.getValue(), FinanceContractTypeEnum.SUPPLE_CONTRACT.getValue(), IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}else{
						//是否调用财务接口同步工程信息，todo:
						//签订补充协议，需要调用物资的接口，将变更材料信息发往NC
						if(flags && projService.isToCall(pro.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
							msg = materialNcService.synProjectInfoClient(pro.getProjId(), changeManagement.getCmId(),MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue(),WebServiceTypeEnum.MATERIAL.getValue()) ;
							jsonbean = JSONObject.fromObject(msg);
							//返回信息-当接口返回失败时，抛出异常
							resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
							if(resultMessage!=null &&  ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
								//回滚事物
								throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
							}
						}
					}
				}
				//补充协议未收款标记
				chargeService.projSignSup(pro.getProjId());
				//补充协议审核完成todo:
				operateRecordService.cerateCurOperateRecord(pro,DesignChangeStateEnum.ALREADY_SIGN_SUPPLEMENT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CHANGE_PROGRESS.getValue(),changeManagement.getCmId(),new Staff(),null,false);
				
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 补充协议推送时无需审核
	 * @param supplementalContract
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void supplementalContractSave(SupplementalContract supplementalContract) throws Exception{
		//虚拟审核信息--开始
		ManageRecord manageRecord = new ManageRecord();    //创建一个新对象
		if(StringUtils.isBlank(manageRecord.getMrId())){
			manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));  //生成审核记录主键id
		}
		if(supplementalContract !=null ){
			manageRecord.setProjId(supplementalContract.getProjId());  //设置工程Id
			manageRecord.setProjNo(supplementalContract.getProjNo());  //设置工程编号
			manageRecord.setBusinessOrderId(supplementalContract.getScId());  //设置业务单id
		}
		manageRecord.setStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_AUDIT.getValue());		//补充协议审核步骤id
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		manageRecord.setMrResult(MrResultEnum.PASSED.getValue());  //审核状态1表示已经通过，0表示未通过
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate()); //设置操作时间
		//1.产生虚拟审核记录
		manageRecordDao.save(manageRecord);
		//虚拟审核信息--结束
	
		Project pro=projectDao.get(supplementalContract.getProjId());    //根据工程id获取记录
		Budget budget= budgetDao.get(supplementalContract.getBudgetId());  //根据预算id取得预算记录
		ChangeManagement changeManagement=changeManagementDao.get(budget.getCmId());//获得设计变更
		//生成流水-一条流水
		String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
		if(pro != null){
			accrualsRecordService.insertAccrualsRecord(pro,arId, CollectionTypeEnum.SUPPLEMENTAL_CONTRACT.getValue(),Integer.parseInt(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), supplementalContract.getScAmount(),ArContractTypeEnum.SUP_CONTRACT.getValue(),supplementalContract.getScNo());
		}
		supplementalContract.setIsAudit(supplementalContractIsAuditEnum.HAVE_PASS.getValue());//标为通过
		supplementalContractDao.saveOrUpdate(supplementalContract);//保存协议
		//将变更材料整合存入到材料清单表中
		boolean flags= changeManagementService.updateMaterialList(pro,changeManagement);
		if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
			ResultMessage resultMessage = new ResultMessage();
			//补充协议审核通过， 调用财务接口同步工程信息 -工程同步信息中没有补充协议信息,todo:
			String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.CONTRACT_SIGN.getValue(), FinanceContractTypeEnum.SUPPLE_CONTRACT.getValue(), IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
			JSONObject jsonbean = JSONObject.fromObject(msg);
			//返回信息-当接口返回失败时，抛出异常
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
			if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
				//回滚事物
				throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
			}else{
				//是否调用财务接口同步工程信息，todo:
				//签订补充协议，需要调用物资的接口，将变更材料信息发往NC
				if(flags && projService.isToCall(pro.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
					msg = materialNcService.synProjectInfoClient(pro.getProjId(), changeManagement.getCmId(),MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue(),WebServiceTypeEnum.MATERIAL.getValue()) ;
					jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
			}
		}
		//补充协议未收款标记
		chargeService.projSignSup(pro.getProjId());
	
	}
	
	@Override
	public ManageRecord queryAuditInformation(String projId, String StepId) {
		ManageRecord mr = manageRecordDao.queryAuditInformation(projId, StepId);
		if (mr !=null ) {
			if(StringUtil.isNotBlank(mr.getMrCsr())){
				Staff s = staffDao.get(mr.getMrCsr());
				mr.setMrCsrName(s.getStaffName());
			}
		}
		return mr;
	}

	@Override
	public ManageRecord queryAuditInsInformation(String businessOrderId) {
		ManageRecord mr = manageRecordDao.queryAuditInsInformation(businessOrderId);
		if(StringUtil.isNotBlank(mr.getMrCsr())){
			Staff s = staffDao.get(mr.getMrCsr());
			mr.setMrCsrName(s.getStaffName());
		}
		return mr;
	}

	/**
	 * 产生分包合同审核记录
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String subContractAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws ParseException {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * 4.逐级审核通过，产生应收流水
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id
		manageRecord.setDocTypeId(docTypeID);
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String status = WorkFlowUtil.workFlowStatus(stepID,isNextStatus);
			Project project = projectDao.get(manageRecord.getProjId());
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(Constants.MODULE_CODE_CONTRACT);
			}
			//清空未通过标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				SubContract subContract=subContractService.findSubContractByprojId(project.getProjId());
				String payType = subContract.getPayType();
				String payTypeDes = PayTypeSCEnum.valueof(payType).getMessage();
				BigDecimal pt = new BigDecimal(payTypeDes.substring(0, payTypeDes.length()-1)).divide(new BigDecimal("100"));
				//协议价款*付款方式 保留两位小数
				BigDecimal scAmount = subContract.getScAmount().multiply(pt).setScale(2,BigDecimal.ROUND_HALF_UP);
				//形成应付流水
				String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
				accrualsRecordService.insertAccrualsRecord(project, arId,CollectionTypeEnum.SUBCONTRACT_PAYMENT.getValue(),Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()) , scAmount,null,subContract.getScNo());
				
				projectDao.update(project);
				return arId;
			}
			//2.更新工程记录
			projectDao.update(project);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String engineeringAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID)
			throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.3级审核通过生成分期工程
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));		//审核记录主键id
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);	
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		EngineeringVisa env = engineeringVisaDao.get(manageRecord.getBusinessOrderId());
		 BigDecimal totalCost = engineeringVisaDao.getTotalCostByType(env.getProjId(),env.getEvType());
		 
		 if(totalCost==null){
			 totalCost = new BigDecimal(0);
		 }
			BigDecimal cost = totalCost.add(env.getQuantitiesTotal());
			//判断审核级别
			String reserve = Constants.ENGINEERING_VISA+"_"+pro.getCorpId()+"_"+pro.getProjectType()+"_"+pro.getContributionMode();
			List<Map<String,Object>> consList = Constants.getConstantsMapByKey(reserve);
			if(consList == null || consList.size()<1){
				//分公司默认
				reserve =  Constants.ENGINEERING_VISA+"_"+pro.getCorpId();
				consList = Constants.getConstantsMapByKey(reserve);
			}
			//系统默认
			if(consList ==null || consList.size()<1){
				//默认级别
				consList = Constants.getConstantsMapByKey(Constants.ENGINEERING_VISA);
			}
			//获取审核级别
			for(Map<String,Object> map :consList){
				String [] str = String.valueOf(map.get("CNNAME")).split(",");
				BigDecimal big1 = new BigDecimal(str[0]);
				BigDecimal big2 = new BigDecimal(str[1]);
				if(cost.compareTo(big1) > 0&&cost.compareTo(big2) <= 0){
					auditLevel = String.valueOf(map.get("CNVALUE"));
					break;
				}
			}
			
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新分期申请单状态为未通过
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.updateAuditRecord(manageRecord.getBusinessOrderId(), stepID);
			isNextStatus = false;
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}else{
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro,StageProjectApplicationEnum.TO_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),new Staff(),nextGrade.toString(),true);
			
		}

		if(null!=isNextStatus){
			if(isNextStatus==true){
				env.setAuditState(StageProjectApplicationEnum.PASSED.getValue());
				//将施工单位确认状态重置为未确定
				env.setCuReState(CuReStateEnum.NOT_RE.getValue());
				//将变更材料整合存入到材料清单表中
				boolean flag = engineeringVisaService.updateMaterialList(env);
				
				//调用用友物资接口物料计划单,todo:
				if(flag && projService.isToCall(env.getProjId(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
				    String msg = materialNcService.synProjectInfoClient(env.getProjId(), env.getEvId(),MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue()) ;
					ResultMessage resultMessage = new ResultMessage();
					net.sf.json.JSONObject jsonbean = net.sf.json.JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) net.sf.json.JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(env.getProjId(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				operateRecordService.cerateCurOperateRecord(pro,env.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,false);
				
			}else if(isNextStatus==false){
				//env.setAuditState(StageProjectApplicationEnum.NO_PASSING.getValue());
				env.setAuditState(StageProjectApplicationEnum.TO_BUDGET.getValue());//签证审核不通过，更新签证为待预算调整状态
				env.setFlag("1");//签证有审核不通过标记
				env.setBackReason(manageRecord.getMrAopinion());
				//待办人：默认施工预算审核人 todo
				Staff staff = new Staff();
				staff.setStaffId(pro.getBudgeterAuditId());
				staff.setStaffName(pro.getBudgeterAudit());
				operateRecordService.cerateCurOperateRecord(pro,env.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),staff,null,true);
				
			}
			//审核的时候将定时任务时间值为空，只有存在有异议的并且再次签证预算调整后的工程才会存定时任务时间
			env.setCrontabDate(null);
			engineeringVisaDao.saveOrUpdate(env);
		}

		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 签证量审核
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String engineeringQuantyAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID)
			throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.3级审核通过生成分期工程
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));		//审核记录主键id
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType == null){
			docType = docTypeDao.findByStepId(stepID, pro.getCorpId(), pro.getProjectType(), null);
		}
		if(docType == null){
			docType = docTypeDao.findByStepId(stepID, pro.getCorpId(), null, null);
		}
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);	
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		EngineeringVisa env = engineeringVisaDao.get(manageRecord.getBusinessOrderId());
		 /*BigDecimal totalCost = engineeringVisaDao.getTotalCostByType(env.getProjId(),env.getEvType());
		 
		 if(totalCost==null){
			 totalCost = new BigDecimal(0);
		 }
			BigDecimal cost = totalCost.add(env.getQuantitiesTotal());
			//判断审核级别
			String reserve = Constants.ENGINEERING_VISA+"_"+pro.getCorpId()+"_"+pro.getProjectType()+"_"+pro.getContributionMode();
			List<Map<String,Object>> consList = Constants.getConstantsMapByKey(reserve);
			if(consList == null || consList.size()<1){
				//分公司默认
				reserve =  Constants.ENGINEERING_VISA+"_"+pro.getCorpId();
				consList = Constants.getConstantsMapByKey(reserve);
			}
			//系统默认
			if(consList ==null || consList.size()<1){
				//默认级别
				consList = Constants.getConstantsMapByKey(Constants.ENGINEERING_VISA);
			}
			//获取审核级别
			for(Map<String,Object> map :consList){
				String [] str = String.valueOf(map.get("CNNAME")).split(",");
				BigDecimal big1 = new BigDecimal(str[0]);
				BigDecimal big2 = new BigDecimal(str[1]);
				if(cost.compareTo(big1) > 0&&cost.compareTo(big2) <= 0){
					auditLevel = String.valueOf(map.get("CNVALUE"));
					break;
				}
			}*/
			
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新分期申请单状态为未通过
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.updateAuditRecord(manageRecord.getBusinessOrderId(), stepID);
			isNextStatus = false;
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}else{
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro,StageProjectApplicationEnum.QUANTY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),new Staff(),nextGrade.toString(),true);
			
		}

		if(null!=isNextStatus){
			if(isNextStatus==true){
				env.setAuditState(StageProjectApplicationEnum.TO_BUDGET.getValue());
				Staff staff = new Staff();
				staff.setStaffId(pro.getBudgeterAuditId());
				staff.setStaffName(pro.getBudgeterAudit());
				operateRecordService.cerateCurOperateRecord(pro,env.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),staff,null,true);
				
			}else if(isNextStatus==false){
				env.setAuditState(StageProjectApplicationEnum.RE_PUSH.getValue());//签证量审核不通过，则为待签证调整
				env.setFlag("1");//签证有审核不通过标记
				env.setBackReason(manageRecord.getMrAopinion());
				//待办人：默认施工员 todo
				Staff staff = new Staff();
				staff.setStaffId(pro.getManagementQaeId());
				staff.setStaffName(pro.getManagementQae());
				operateRecordService.cerateCurOperateRecord(pro,env.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue(),manageRecord.getBusinessOrderId(),staff,null,true);
				
			}
			engineeringVisaDao.saveOrUpdate(env);
		}

		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 产生审核记录(此方法只用于图纸审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String auditDrawingSave(ManageRecord manageRecord, String docTypeID,
			String auditLevel, String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		}
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		
		
		manageRecord.setStepId(stepID);		//图纸审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		//存在多人操作一级审核
		manageRecordDao.saveManageRecord(manageRecord);
		
		
		
		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.DRAWING_AUDIT.getValue());//踏勘审核
		
		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			 notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			 notice.setAuditType(NoticeMenuEnum.DRAWING_AUDIT1.getType());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeContent(NoticeMenuEnum.DRAWING_AUDIT1.getMessage());					//待踏勘审核一级
			 notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setProjId(manageRecord.getProjId());
			 notice.setCorpId(pro.getCorpId());
		}*/
		
		
		Project project = projectDao.get(manageRecord.getProjId());
		String toder="";
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			//审核通过--将通知置为无效
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		}else{
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		//noticeDao.saveOrUpdate(notice);
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			
			String statusId=workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.DRAWING_SIGN_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(statusId);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(constants);
				//不通过 
				toder=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				toder=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "true");
				//return manageRecord.getBusinessOrderId();
			}
			project.setToDoer(toder);
			//2.更新工程记录
			projectDao.update(project);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	
	/**
	 * 产生审核记录(此方法只用于资料审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditDataSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,
			String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//资料审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		

		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.DATA_AUDIT.getValue());//资料审核
		
		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));		//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			 notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	//当前审核级别
			 notice.setAuditType(NoticeMenuEnum.DATA_AUDIT1.getType());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeContent(NoticeMenuEnum.DATA_AUDIT1.getMessage());						//待资料审核一级
			 notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setProjId(manageRecord.getProjId());
			 notice.setCorpId(pro.getCorpId());
		}*/
		
		Project project = projectDao.get(manageRecord.getProjId());
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;

			//审核通过--将通知置为无效
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		String todoer="";
		//noticeDao.saveOrUpdate(notice);
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String statusId=workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.COMPLETION_DATA_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(statusId);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
			
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(constants);
				//不通过 
				todoer=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(todoer);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			}
			project.setToDoer(todoer);
			//2.更新工程记录
			projectDao.update(project);
		}
		
	}

	/**
	 * 产生审核记录
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String auditPlanSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants ) throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		
		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.PLAN_AUDIT.getValue());//计划审核
		
		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			 notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			 notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			 notice.setAuditType(NoticeMenuEnum.PLAN_AUDIT1.getType());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeContent(NoticeMenuEnum.PLAN_AUDIT1.getMessage());					//待计划审核一级
			 notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setProjId(manageRecord.getProjId());
			 notice.setCorpId(pro.getCorpId());
		}*/


		ConstructionPlan cp=constructionPlanDao.viewPlanById(manageRecord.getProjId());

		//已交钱-》有配置：审核级=单据级-配置数
		auditLevel=paidReduceLevel(pro, cp.getIsinitialPayment(), manageRecord.getMenuId(), auditLevel);

		Project project = projectDao.get(manageRecord.getProjId());
		String toder="";
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){

//			//生成派遣通知记录
//			Notice notice1 = new Notice();
//			notice1.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN));
//			notice1.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());		//将通知置为有效;
//			notice1.setNoticeTime(noticeDao.getDatabaseDate());
//			notice1.setNoticeTitle("派遣通知");
//			Project project1 = projectDao.get(manageRecord.getProjId());
//			notice1.setNoticeContent(project1.getProjName()+"工程已下单施工任务或监理任务给您,请及时查看");
//			notice1.setProjId(project1.getProjId());
//			noticeDao.saveOrUpdate(notice1);
			isNextStatus = true;
			String saveStatus=projService.saveProjectPlanInfo(manageRecord.getProjId());   //审核通过将工程计划信息保存到工程表

			operateRecordService.saveOperateRecord(manageRecord.getProjId());//生成派遣通知

			//调用鸿巨接口（施工任务单新增/修改）返回信息
			ResultMsg resultMsg = synchronizedService.callSynchronizedConstructionTask(manageRecord.getProjId(), WebServiceTypeEnum.CONSTRUCTION_TASK.getValue());
			if (resultMsg!=null && resultMsg.getCode()!=0){
				throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
			}

		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		
		//noticeDao.saveOrUpdate(notice);
		//返回信息
		String result =Constants.OPERATE_RESULT_SUCCESS;
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			ConstructionPlan constructionPlan = constructionPlanDao.get(manageRecord.getBusinessOrderId());
			
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.PROJECT_PLAN_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				if(constructionPlan.getIsinitialPayment().equals("false")){
					if(StringUtil.isNotBlank(constructionPlan.getRemark())){
						constructionPlan.setRemark(constructionPlan.getRemark().substring(0, constructionPlan.getRemark().length()-8));
					}
				}
				project.setSignBack(constants);
				//不通过 
				toder=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(toder);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				//生成施工员签字通知
				//signNoticeService.insertSignNotice(manageRecord.getProjId(), SignPostEnum.BUILDER.getValue(), constructionPlan.getBuilderId(), SignGenarateTypeEnum.SINGLE.getValue());
				project.setSignBack("");
				
				toder=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
				project.setToDoer(toder);
				//公司出资的不存在借料的工程，在计划审核时先同步工程信息，再调用物料计划单接口 todo:
				String msg = "";
				ResultMessage resultMessage = new ResultMessage();
				if(ContributionMode.COMPANY_TYPE.contains(","+project.getContributionMode()+",") && !MaterialFlagEnum.YES.getValue().equals(project.getMaterialFlag())&&projService.isToCall(project.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
					//1.同步工程信息
					msg= financeInfoService.synProjectInfoClient(manageRecord.getProjId(), FinanceOperateTypeEnum.CONTRACT_SIGN.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				if(projService.isToCall(project.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
					//计划审核通过，调用物料计划单接口todo:
					msg = materialNcService.synProjectInfoClient(manageRecord.getProjId(),null,MaterialTableTypeEnum.MATERIAL.getValue(),"1",UpdateTypeEnum.INSERT.getValue(),WebServiceTypeEnum.MATERIAL.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.MATERIAL.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				//任务下达日期
				constructionPlan.setCpArriveDate(manageRecord.getMrTime());
				project.setCpArriveDate(manageRecord.getMrTime());
			}
			constructionPlanDao.saveOrUpdate(constructionPlan);
			//2.更新工程记录
			projectDao.update(project);
			
		}
		return result;
	}

	public String paidReduceLevel(Project project,String firstPayment, String menuId,String level){
		if (project==null) return level;

		String cpcmKey="0_"+project.getCorpId()+"_"+project.getProjectType()+"_"+project.getContributionMode()+"_"+menuId;
		String cpmKey="0_"+project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
		String cmKey="0_"+project.getCorpId()+"_"+menuId;

		DataFilerSetUpDto dataFSD =isConfig(cpcmKey)!=null?isConfig(cpcmKey):isConfig(cpmKey)!=null?isConfig(cpmKey):isConfig(cmKey);
		boolean flag =dataFSD!=null?true:false;

		if ("true".equals(firstPayment) && flag && CheckUtil.checkNumber(dataFSD.getSupSql())) {//已交钱-》有配置-》配置的SupSql字段为整数
			int currentGrade = Integer.parseInt(level);
			int configGrade = Integer.parseInt(dataFSD.getSupSql());
			if (currentGrade>configGrade) {//当前级别大于配置的级别时才相减
				level = String.valueOf(currentGrade - configGrade);
			}
		}
		return level;
	}


	public DataFilerSetUpDto isConfig(String key){
		return  Constants.isConfig(key);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String auditInstTask(ManageRecord manageRecord) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id
		List<InstTasks> tasksList=new ArrayList<InstTasks>();
		tasksList = instTasksDao.findByProjId(manageRecord.getProjId());
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}
		//查询工程
		Project project = projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(StepEnum.AUDIT_INST_TASKS.getValue(),project.getCorpId(),project.getProjectType(),project.getContributionMode());
		String auditLevel = "3";
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(StepEnum.AUDIT_INST_TASKS.getValue());		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		//已付首付款的 审核级别为-1
		/*List<AccrualsRecord> list = accrualsRecordService.getDataByProj(manageRecord.getProjId(), "13", "1", "2");
		if(list !=null && list.size()>0 && tasksList.size()>0){
			Date drDate = list.get(0).getArDate();
			Date taskDate = tasksList.get(0).getOrderDate();
			if(drDate.after(taskDate)){
				//收款时间大于下单时间，则是3级
				auditLevel = String.valueOf(Integer.parseInt(auditLevel));
			}else{
				//收款时间小于下单时间
				auditLevel = String.valueOf(Integer.parseInt(auditLevel) - 1);
			}
		}*/
		if(tasksList!=null  && tasksList.size()>0){
			Date taskDate = tasksList.get(0).getOrderDate();//下达时间

			List<CashFlow> cashList = cashFlowDao.queryCashFlowByProjIdType(manageRecord.getProjId(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(),null);
			if(cashList != null && cashList.size() > 0){
				for(CashFlow cf:cashList){
					if(cf.getCfDate()!=null && cf.getCfDate().after(taskDate)){
						//收款时间大于下单时间，则是3级
						auditLevel = String.valueOf(Integer.parseInt(auditLevel));
						break;
					}else{
						//收款时间小于下单时间
						auditLevel = String.valueOf(Integer.parseInt(auditLevel) - 1);
					}
				}
			}
		}




		String toder="";
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), StepEnum.AUDIT_INST_TASKS.getValue());
			isNextStatus = false;
			
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())&&manageRecord.getMrResult().equals(MrResultEnum.PASSED.getValue())){
			
			isNextStatus = true;
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					StepEnum.AUDIT_INST_TASKS.getValue(), manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		
		//返回信息
		String result =Constants.OPERATE_RESULT_SUCCESS;
		if(isNextStatus!=null){
			
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.INST_STASK_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONSTRUCTIONPLAN);
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(Constants.MODULE_CODE_CONSTRUCTIONPLAN);
				//不通过 
				toder=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", StepEnum.AUDIT_INST_TASKS.getValue());
				project.setToDoer(toder);
				project.setSignBack(ProjStatusEnum.TO_AUDIT_INSTASKS.getValue());
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				toder=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), StepEnum.AUDIT_INST_TASKS.getValue(), StepEnum.valueof(StepEnum.AUDIT_INST_TASKS.getValue()).getMessage(), "");
				project.setToDoer(toder);
				project.setSignBack("");
			}
			//2.更新工程记录
			projectDao.update(project);
			
		}
		return result;
	}
	
	
	
	
	
	/**
	 * 产生审核记录(此方法只用于电子图审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditDrawingApplySave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
		}
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//图纸审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门

		boolean isExist= manageRecordDao.isExist(manageRecord.getBusinessOrderId(),manageRecord.getMrAuditLevel(),manageRecord.getStepId());
		//1.产生审核记录
		if (!isExist) {
			manageRecordDao.save(manageRecord);
		}
		
		
		
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}
		if(isNextStatus!=null){
			//审核通过 不翻转工程状态 翻转审核单状态
			List<DrawingApply> list=drawingApplyDao.findByProjId(manageRecord.getProjId());
			if(list!=null && list.size()>0){
				DrawingApply da=list.get(0);
				if(isNextStatus){
					da.setAuditState(AuditResultEnum.PASSED.getValue());//审核已通过
				}else{
					da.setAuditState(AuditResultEnum.NOT_PASSED.getValue());//审核未通过
					pro.setSignBack(Constants.MODULE_CODE_SETTLEMENT);
					projectDao.saveOrUpdate(pro);
				}
				drawingApplyDao.update(da);
			}
		}
		
	}
	
	/**
	 * 产生审核记录(此方法只用于分部验收审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditDivisionalAcceptanceSave(ManageRecord manageRecord, String docTypeID, String auditLevel,
			String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel = docType.getGrade();
		}
		
		manageRecord.setStepId(stepID);		//图纸审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecordByBoId(manageRecord.getBusinessOrderId(), stepID);
			isNextStatus = false;
			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
			//删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//分部验收审核的签字信息
		
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.DA_APPLY_SIGN);
		}else{
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.DA_APPLY_SIGN);
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro,AuditResultEnum.APPLYING.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue(),manageRecord.getBusinessOrderId(),new Staff(),nextGrade.toString(),true);
			
		}
		if(isNextStatus!=null){
			//审核通过 不翻转工程状态 翻转审核单状态
			DivisionalAcceptanceApply da=divisionalAcceptanceApplyDao.get(manageRecord.getBusinessOrderId());//查询分部验收申请
			if(da!=null){
				if(isNextStatus){
					da.setAuditState(AuditResultEnum.PASSED.getValue());//审核已通过
					//生成分部验收待办通知
					operateRecordService.cerateCurOperateRecord(pro,da.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,true);
					
				}else{
					da.setAuditState(AuditResultEnum.NOT_PASSED.getValue());//审核未通过
					da.setSignBack(Constants.MODULE_CODE_COMPLETE);		//审核未通过标记
					//生成待推送的待办通知
					//待办人：默认施工预算审核人 todo
					Staff staff = new Staff();
					staff.setStaffId(da.getApplyerId());
					staff.setStaffName(da.getApplyer());
					operateRecordService.cerateCurOperateRecord(pro,AuditResultEnum.NOT_APPLY.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue(),manageRecord.getBusinessOrderId(),staff,null,true);
				}
				divisionalAcceptanceApplyDao.update(da);
			}
		}
		
	}
	@Override
	public void save(ManageRecord manageRecord) {
		manageRecordDao.save(manageRecord);
	}

    @Override
    @Transactional(readOnly = false,propagation=Propagation.REQUIRED)
    public void gasAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID, String constants) throws ParseException {
        /**
         * 1.产生审核记录
         * 2.更新工程记录状态
         * 3.产生业务操作记录
         * */
        manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));		//审核记录主键id

        if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
            manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH));
        }
        Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
        manageRecord.setStepId(stepID);		//通气审核
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        if(StringUtil.isBlank(manageRecord.getMrCsr())){
            manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
        }
        manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
        //1.产生审核记录
        manageRecordDao.save(manageRecord);
        GasProject gasProject=gasProjectDao.get(manageRecord.getBusinessOrderId());//查询通气工程
        
        //默认状态标记为下一个状态
        Boolean isNextStatus = null;
        //若当前审核结果为不通过，则更新工程记录状态为上一个状态
        if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
            //更新此步骤的审核历史记录更新作废标记
            manageRecordDao.batUpdateHistoryRecordByBoId(manageRecord.getBusinessOrderId(), stepID);
            isNextStatus = false;

            //若此次审核级别为单据类型设置的级别，则更新通气审核状态
            //删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//结算的签字信息
        }else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
            isNextStatus = true;
            signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), GasProject.class.getName(), false,Constants.FIRST_SETTLEMENT_POST);
        }else{
        	signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), GasProject.class.getName(), false,Constants.FIRST_SETTLEMENT_POST);
        	//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),nextGrade.toString(),true);
			
        }
       if("1".equals(manageRecord.getMrAuditLevel())){
        	//一级审核
        	 if(gasProject!=null){
        		 gasProject.setIsSignIgnite(ContractSuIsPrintEnum.HAVE_NOT_PRINT.getValue());;//一级审核通过 未签打印单
        	 }
        }/*else if("2".equals(manageRecord.getMrAuditLevel())){
        	//二级审核：如果通气审核过，并且点火单已签订，则修改通气工程为点火状态
		   	 if(gasProject!=null && ContractSuIsPrintEnum.ALREADY_PRINT.getValue().equals(gasProject.getIsSignIgnite())){
		   		 gasProject.setGasProjStatus(GasProjectStatusEnum.IGNITE.getValue());//点火状态
		   	 }
        }*/
        
        
        if(isNextStatus!=null){
			//产生业务操作记录
			Project project = projectDao.get(manageRecord.getProjId());
			operateRecordService.createOperateRecord(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTTOUCH), project.getProjId(), project.getProjNo(), stepID, StepOutWorkflowEnum.valueof(stepID).getMessage(), "");
            //审核通过 不翻转工程状态 翻转通气审核状态
            //GasProject gasProject=gasProjectDao.get(manageRecord.getBusinessOrderId());//查询通气工程
			
            if(gasProject!=null){
                if(isNextStatus){
                	//判断分公司是否配置辅助流程 
                	String status=workFlowService.queryAssistProgressStatusId(project.getCorpId(), null, null, GasProjectStatusEnum.GAS_AUDIT.getValue(),
        					true, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue());
                	if(WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(status)){
                		//未配置流 按原来的走
                		gasProject.setGasProjStatus(GasProjectStatusEnum.GAS_AUDIT_DONE.getValue());//审核已通过
                		operateRecordService.cerateCurOperateRecord(pro,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),null,false);
            		}else{
                		gasProject.setGasProjStatus(status);
                		operateRecordService.cerateCurOperateRecord(pro,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),null,true);	
                	}
                	
                	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                	if(StringUtil.isNotBlank(manageRecord.getPlanGasDate())){
                		gasProject.setPlanGasDate(sdf.parse(manageRecord.getPlanGasDate()));
                	}
                	if(StringUtil.isNotBlank(manageRecord.getPlanGasEndDate())){
                		gasProject.setPlanGasEndDate(sdf.parse(manageRecord.getPlanGasEndDate()));
                	}
                }else{
                    String status=workFlowService.queryAssistProgressStatusId(project.getCorpId(), null, null, GasProjectStatusEnum.GAS_AUDIT.getValue(),
        					false, WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue());
                	if(WorkFlowActionEnum.CONTRACT_END.getActionCode().equals(status)){
                		//未配置流 按原来的走
                		gasProject.setGasProjStatus(GasProjectStatusEnum.GAS_AUDIT_NO_PASS.getValue());//审核未通过
                		//todo:
                		operateRecordService.cerateCurOperateRecord(pro,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),null,true);
                	}else{
                		gasProject.setCorpBack(GasProjectStatusEnum.GAS_AUDIT_NO_PASS.getValue());//分公司新加字段，用于判断是否标红
                		gasProject.setGasProjStatus(status);
                		//todo:
                		operateRecordService.cerateCurOperateRecord(pro,gasProject.getGasProjStatus(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.GAS_PROGRESS.getValue(),gasProject.getGpId(),new Staff(),null,true);
                	}
                }
            }
        }
        
        gasProjectDao.saveOrUpdate(gasProject);
    }

	/**
	 * 保存工程预算审批(仅用于工程预算审批)
	 * @author liaoyq
	 * @createTime 2017-8-15
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void auditBudgetSave(ManageRecord manageRecord, String docTypeID,
			String auditLevel, String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * 4.更新预算记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
		}
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		
		
		manageRecord.setStepId(stepID);		//资料审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		
		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.BUDGET_AUDIT.getValue());//预算审核
		
		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			 notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			 notice.setAuditType(NoticeMenuEnum.BUDGET_AUDIT1.getType());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setNoticeContent(NoticeMenuEnum.BUDGET_AUDIT1.getMessage());					//待预算审核一级
			 notice.setNoticeType("2");
			 notice.setProjId(manageRecord.getProjId());
			 notice.setCorpId(pro.getCorpId());
		}
		*/
		Project project = projectDao.get(manageRecord.getProjId());
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			//审核通过--将通知置为无效
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		
		//noticeDao.saveOrUpdate(notice);
		String todoer="";
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			
			String statusId=workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.BUDGET_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(statusId);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET);
			
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(constants);
				//不通过 
				todoer=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(todoer);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			}
			//2.更新工程记录
			project.setToDoer(todoer);//待办人
			projectDao.update(project);
		}
		//更新预算记录(审核人)
		Budget budget = budgetDao.queryBudgeByprojId(manageRecord.getProjId());
		if(budget!=null){
			budget.setAuditorId(loginInfo.getStaffId());
			budget.setAuditorName(loginInfo.getStaffName());
			budgetDao.saveOrUpdate(budget);
		}
	}
	/**
	 * 保存工程造价审批(仅用于工程造价审批)
	 * @author liaoyq
	 * @createTime 2017-8-17
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void auditProjectCostSave(ManageRecord manageRecord, String docTypeId,
			String auditLevel, String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.产生业务操作记录
		 * 3.更新工程记录状态
		 * 4.更新造价审核记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		}
		
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		
		
		manageRecord.setStepId(stepID);		//资料审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.COST_AUDIT.getValue());//造价审核
		
		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));		//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			 notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			 notice.setAuditType(NoticeMenuEnum.COST_AUDIT1.getType());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeContent(NoticeMenuEnum.COST_AUDIT1.getMessage());					//待造价审核一级
			 notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setProjId(manageRecord.getProjId());
			 notice.setCorpId(pro.getCorpId());
		}*/
		Project project = projectDao.get(manageRecord.getProjId());
		
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			//审核通过--将通知置为无效
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		//noticeDao.saveOrUpdate(notice);
		String todoer="";
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String statusId=workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.PROJECT_COST_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(statusId);
			//2.产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(constants);
				//不通过 
				todoer=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(todoer);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
				project.setToDoer(todoer);
			}
			//3.更新工程记录
			projectDao.update(project);
		}
		//4.更新工程造价记录（审核人）
		ProjectCost projectCost = projCostDao.queryByProjId(manageRecord.getProjId());
		if(projectCost!=null){
			projectCost.setProjCostAuditorId(loginInfo.getStaffId());
			projectCost.setProjCostAuditor(loginInfo.getStaffName());
			projectCost.setAuditDate(projCostDao.getDatabaseDate());
			projCostDao.saveOrUpdate(projectCost);
		}
	}
	
	/**
	 * 产生审核记录(此方法只用于付款审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void auditPaymentApplySave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,
			String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}

		PaymentApply pay=paymentApplyDao.get(manageRecord.getBusinessOrderId());

		//监理费、检测费、设计费在请款记录中没有存储工程ID
		Project pro=projectDao.get(manageRecord.getProjId());

		if(pro==null){
			pro = new Project();//监理费、检测费、设计费取全局配置
			//获取付款部门所在的公司ID
			Department department = departmentDao.get("deptId", StringUtil.isNotBlank(pay.getOrgId())?pay.getOrgId():"");
			pro.setCorpId(department.getOrgId());
			pro.setProjectType("0");//默认
			
		}


		//************************审核级别查询*********************************
		auditLevel="";
		if (pay!=null){
			String corfet = pro.getCorpId() + "_" + pay.getFeeType();
			auditLevel= docTypeService.queryAuditLevel(stepID, corfet, pro.getProjectType(), pro.getContributionMode());

			if (StringUtils.isBlank(auditLevel)){
				auditLevel = docTypeService.queryAuditLevel(stepID, pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());
			}
		}




		manageRecord.setStepId(stepID);		//付款审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecordByBoId(manageRecord.getBusinessOrderId(), stepID);
			isNextStatus = false;
			
			//删除之前的签字
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//结算的签字信息
			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			
			//生成电子签名
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), PaymentApply.class.getName(), false,Constants.PAYMENT_ADIT);
		}else {
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), PaymentApply.class.getName(), false, Constants.PAYMENT_ADIT);
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro,AuditResultEnum.APPLYING.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),manageRecord.getBusinessOrderId(),new Staff(),nextGrade.toString(),true);
			
		}
		if(isNextStatus!=null){
			//审核通过 不翻转工程状态 翻转审核单状态
			PaymentApply pa=paymentApplyDao.get(manageRecord.getBusinessOrderId());//查询付款申请
			if(pa!=null){
				if(isNextStatus){
					pa.setAuditState(AuditResultEnum.PASSED.getValue());//审核已通过
					//工程款
					if(FeeTypeEnum.CONSFEE.getValue().equals(pa.getFeeType())){
						//生成付款流水
						BigDecimal applyAmount = pa.getApplyAmount();
						//形成应付流水
						String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
						//将请款的ID存入到应付流水
						pro.setPaId(pa.getPaId());
						accrualsRecordService.insertAccrualsRecord(pro, arId,pa.getApplyReason(),Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()) , applyAmount,null,null);
					}else{
						//批量生成费用
						accrualsRecordService.insertPaAccrualsRecord(pa.getPaId());
					}
					//审核通过，修改待办为已办
					Staff staff = new Staff();
					operateRecordService.cerateCurOperateRecord(pro,pa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),pa.getPaId(),staff,null,true);
					
				}else{
					pa.setAuditState(AuditResultEnum.NOT_PASSED.getValue());//审核未通过
					pa.setSignBack(Constants.MODULE_CODE_SUBCONTRACT);	 //未通过标记
					Staff staff = new Staff();
					//审核未通过，申请人重新推送
					staff.setStaffId(StringUtil.isNotBlank(pa.getApplyerId())?pa.getApplyerId():"");
					staff.setStaffName(StringUtil.isNotBlank(pa.getApplyer())?pa.getApplyer():"");
					operateRecordService.cerateCurOperateRecord(pro,pa.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.PAYMENT.getValue(),pa.getPaId(),staff,null,true);
					
				}
				paymentApplyDao.update(pa);
				
			}
		}
		
	}
	
	/**
	 * 产生审核记录(此方法只用结算终审)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void auditSettlementEndSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,
			String constants) throws Exception{
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
		}
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		Project project = projectDao.get(manageRecord.getProjId());
		SettlementDeclaration sd = settlementDeclarationDao.get(manageRecord.getBusinessOrderId());
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			sd.setAuditStatus(SettlementAuditResultEnum.NOT_PASSED.getValue().toString());
			isNextStatus = false;
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
			//删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//结算的签字信息
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;

			//生成结算会签排序为1的签字通知
			addSettlementRemittanceSignNotice(manageRecord.getProjId(),"110815");


			//审核通过--将通知置为无效
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
			//生成签字
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.End_SETTLEMENT_POST);
		}else{
			//生成签字
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.End_SETTLEMENT_POST);
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		//noticeDao.saveOrUpdate(notice);
		//调用工作流定义方法获取状态码
		String todoer="";
		if(isNextStatus!=null){
			if(isNextStatus==true){
				if(null!=sd){
					sd.setAuditStatus(SettlementAuditResultEnum.PASSED.getValue().toString());
					sd.setIsPrint(SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());
					settlementDeclarationDao.update(sd);
				}
				String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.REPORT_DONE_CONFIRM.getActionCode(), true);
				project.setProjStatusId(status);
				//2.更新工程记录
				project.setSettlementDate(projectDao.getDatabaseDate());//结算终审日期
				
				//调用财务接口，同步工程信息todo:
				if(projService.isToCall(project.getProjId(),WebServiceTypeEnum.PROJECT_SETTLEMENT.getValue())){
				//调用工程信息同步的 施工合同修改接口todo:
					String res = "";
					ResultMessage resultMessage = new ResultMessage();
					res = financeInfoService.synProjectInfoClient(manageRecord.getProjId(), FinanceOperateTypeEnum.SUBCONTRACT_UPDATE.getValue(), FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(res);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.PROJECT_SETTLEMENT.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
					//调用工程信息同步，同步结算信息
					
					//查询工程是否已有终审确认操作历史，已有则调用修改结算信息接口(2-B)，否则调用新增结算信息接口(2-A)
					String updateType = FinanceOperateTypeEnum.PROJECT_SETTLEMENT.getValue();
					OperateRecordQueryReq req = new OperateRecordQueryReq();
					req.setProjId(project.getProjId());//工程ID
					req.setStepId(StepEnum.REPORT_DONE_CONFIRM.getValue());//步骤
					req.setGrade(auditLevel);//级别
					req.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());//已办
					Map<String, Object> map = operateRecordDao.queryOperateRecord(req);
					List<OperateRecord> oplist = (List<OperateRecord>) map.get("data");
					if(oplist!=null && oplist.size()>0){
						updateType = FinanceOperateTypeEnum.PROJECT_SETTLEMENT_UPDATE.getValue();
					}
					res = financeInfoService.synProjectInfoClient(manageRecord.getProjId(),updateType,UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					jsonbean = JSONObject.fromObject(res);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.PROJECT_SETTLEMENT.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				//产生业务操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
				todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
				//更新结算表的终审人和终审日期
				if(sd!=null){
					//当前审核人
					sd.setFinalAuditer(loginInfo.getStaffName());
					sd.setFinalAuditerId(loginInfo.getStaffId());
					sd.setEndDeclaraDate(project.getSettlementDate());
					settlementDeclarationDao.update(sd);
				}
			}else{
				//回退到第一级审核人
				todoer=operateRecordService.batUpdateHistoryRecordByBoIdToFirst(project.getProjId(), "", stepID);
			}
			project.setToDoer(todoer);//待办人
			projectDao.update(project);
		}
		
	}


	public boolean addSettlementRemittanceSignNotice(String projId,String menuId) throws Exception {

		Project project = projectDao.get(projId);
		if (project==null) return false;

		List<SignNoticeStandard> list=signNoticeStandardService.findSignNoticeStandardList(menuId,projId);

		if (list==null || list.size()<1)return false;

		for (SignNoticeStandard s:list) {
			if ("1".equals(s.getSortNo())) {
				SignNotice signNotice = new SignNotice();
				signNotice.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
				BeanUtils.copyProperties(signNotice, s);
				BeanUtils.copyProperties(signNotice, project);
				signNotice.setPost(s.getPost());
				signNotice.setBusinessOrderId(projId);
				signNotice.setStatus("1");
				signNoticeDao.saveOrUpdate(signNotice);
			}
		}
		return true;
	}




	/**
	 * 产生审核记录(此方法只用于延期审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditApplyDelaySave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,
			String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_DELAY));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_DELAY));
		}
		
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			auditLevel=docType.getGrade();
			manageRecord.setDocTypeId(docType.getId());
		}
		manageRecord.setStepId(stepID);		//延期审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecordByBoId(manageRecord.getBusinessOrderId(), stepID);
			isNextStatus = false;
			
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}
		if(isNextStatus!=null){
			//审核通过 不翻转工程状态 翻转审核单状态
			ApplyDelay da=applyDelayDao.get(manageRecord.getBusinessOrderId()); //查询延期申请
			if(da!=null){
				if(isNextStatus){
					da.setAuditState(AuditResultEnum.PASSED.getValue());		//审核已通过
					da.setNotPassReason(manageRecord.getMrAopinion());
					//如果设计延期，则修改原计划设计完成日期
					if(StepEnum.DESIGN_DRAWING.getValue().equals(da.getStepId())){
						//重新计算计划设计完成日期
						DesignInfo designInfo = designInfoDao.get("projId",pro.getProjId());
						Date date = projService.queryDuPlCompletedDate(designInfo);
						if(date!=null){
							//更新工程表计划设计完成日期
							pro.setDuPlCompleteDate(date);
							projectDao.saveOrUpdate(pro);
						}
					}
				}else{
					da.setAuditState(AuditResultEnum.NOT_PASSED.getValue());	//审核未通过
					da.setSignBack(Constants.MODULE_CODE_DELAY);				//审核未通过原因
					da.setNotPassReason(manageRecord.getMrAopinion());
				}
				applyDelayDao.update(da);
			}
		}
		
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String contractModifyAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * 4.逐级审核通过，产生应收流水
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));//审核记录主键id
		//manageRecord.setDocTypeId(docTypeID);
		Project pro = projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);											//合同修改审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 						//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());						//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		/*todo:stepOut
		 */
		Staff staff = new Staff();
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，合同修改状态变为未通过
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			//若此次审核级别为单据类型设置的级别，则更新工程信息及合同审核状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = true;
		}else{
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),staff,nextGrade.toString(),true);
			
		}
		/*noticeDao.saveOrUpdate(notice);*/
		//产生业务操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
		String toder="";
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			Contract contract = contractDao.get(manageRecord.getBusinessOrderId());
			OperateRecordLog operateRecordLog = operateRecordLogService.findLatelyLog(OperateTypeEnum.CONTRACT_MODIFY.getValue(),contract.getConId());
			ContractLog contractLog = contractLogService.findByModifystate(ModifyStateEnum.BEFOR_MODIFY.getValue(),operateRecordLog.getOrlId());
			//保存未通过标记
			if(MrResultEnum.NOT_PASSED.getValue().equals(manageRecord.getMrResult())){
				BeanUtil.copyNotNullProperties(contract, contractLog);
				contract.setModifyStatus(ModifyStatusEnum.AUDIT_NO_PASS.getValue());
				contract.setSignBack(Constants.MODULE_CODE_CONTRACT);
				//不通过 
				//liaoyq:修改代办todo:
				operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),staff,null,false);
				
			}
			//清空未通过标记
			if(MrResultEnum.PASSED.getValue().equals(manageRecord.getMrResult())){
				String stepName = "";
				if(StepEnum.valueof(stepID)!=null){
					stepName=StepEnum.valueof(stepID).getMessage();
				}else if(StepOutWorkflowEnum.valueof(stepID)!=null){
					stepName=StepOutWorkflowEnum.valueof(stepID).getMessage();
				}
				//liaoyq:修改代办todo:
				operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),staff,null,false);
				
				contract.setSignBack("");
				//合同修改状态：已通过
				contract.setModifyStatus(ModifyStatusEnum.AUDIT_PASS.getValue());
				Project project = projectDao.get(manageRecord.getProjId());
				if(StringUtil.isNotBlank(contract.getCustId())){
					project.setCustId(contract.getCustId());							//修改工程客户单位
				}
				project.setCustName(contract.getCustName());						//修改工程客户单位
				if(StringUtil.isNotBlank(contract.getCustEntrustRepresent())){
					project.setCustContact(contract.getCustEntrustRepresent());
				}
				//工程名称
				if(StringUtil.isNotBlank(contract.getProjName())){
					project.setProjName(contract.getProjName());
				}
				//工程地点
				if(StringUtil.isNotBlank(contract.getProjAddr())){
					project.setProjAddr(contract.getProjAddr());
				}
				//合同签订日期
				if(contract.getSignDate()!=null){
					project.setSignDate(contract.getSignDate());
				}
				String arId=IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
				ResultMessage resultMessage = new ResultMessage();



				//判断是否产生应收流水；
				PayType payTypeLog = payTypeDao.get(contractLog.getPayType());
				PayType payType = payTypeDao.get(contract.getPayType());
				//若合同金额改变或者付款方式改变或者付款比例改变,则修改应收金额流水
				if((payTypeLog.getPayTypeMode().compareTo(payType.getPayTypeMode()) !=0)||isCompareTo(contract,contractLog)){
					project.setContractAmount(contract.getContractAmount());
//					BigDecimal difference = contract.getContractAmount().subtract(contractLog.getContractAmount());
//					String arId=IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
//					accrualsRecordService.insertAccrualsRecord(projectDao.get(contract.getProjId()),arId,CollectionTypeEnum.MODIFY_CONTRACT.getValue(),
//							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , difference,null);
					//查询改合同需要更新的应收流水
					//先判断工程合同是否已收款
					//如果合同的付款方式不变，修改流水金额，如果合同付款方式改变（肯定未收费）,删除已有流水，重新生成流水
					List<AccrualsRecord> accrualsRecords = accrualsRecordService.findForUpdate(contract.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),ArContractTypeEnum.CONSTRUCTION.getValue());
					//重新生成修改后的应收流水
					//判断付款次数
					if(payTypeLog!=null && payType!=null && payTypeLog.getPayTypeMode().equals(payType.getPayTypeMode())){
						//付款方式不变的，修改流水应收金额
						int i=1;
						//收付款超收金额
						BigDecimal firstMoney = new BigDecimal(0);
						BigDecimal secondMoney = new BigDecimal(0);
						BigDecimal receiveAmount = new BigDecimal(0);
						for(AccrualsRecord ar : accrualsRecords){
							if(CollectionTypeEnum.INITIAL_PAYMENT.getValue().contains(ar.getArType())){
								ar.setArCost(contract.getFirstPayment());
								firstMoney = firstMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(contract.getFirstPayment()):new BigDecimal(0));
								receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
							}else if(CollectionTypeEnum.STAGE_PAYMENT.getValue().contains(ar.getArType())){
								if(i==2){
									//如果已收首付款大于第二阶段款并且阶段款还未收
									if(firstMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
										ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
										ar.setReceiveAmount(firstMoney);
									}
									secondMoney = secondMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(ar.getReceiveAmount()):new BigDecimal(0));
									ar.setArCost(contract.getSecondPayment());
								}else{
									//如果已收首付款大于第二阶段款并且阶段款还未收
									if(secondMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
										ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
										ar.setReceiveAmount(firstMoney);
									}
									ar.setArCost(contract.getThirdPayment());
								}
								receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
							}
							i++;
							accrualsRecordDao.saveOrUpdate(ar);
						}
						//需要退款的生成退款流水
						if(receiveAmount.compareTo(contract.getContractAmount())>0){
							accrualsRecordService.insertAccrualsRecord(project,arId,CollectionTypeEnum.MODIFY_CONTRACT.getValue(),
									Integer.valueOf(ARFlagEnum.REFUND_MENT.getValue()) ,contract.getContractAmount().subtract(receiveAmount) ,null,contract.getConNo());
						}
					}else{
						//删除应收流水
						accrualsRecordDao.batchDeleteObjects(accrualsRecords);
						
						//生产合同首付款
						accrualsRecordService.insertAccrualsRecord(project,arId,CollectionTypeEnum.INITIAL_PAYMENT.getValue(),
								Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getFirstPayment(),null,contract.getConNo());
					//付款类型为两次付清
						if(payType!=null && "2".equals(payType.getPayTypeMode())){
							accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
									Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),null,contract.getConNo());
						//付款类型为三次付清
						}else if(payType!=null && "3".equals(payType.getPayTypeMode())){
							accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
									Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),null,contract.getConNo());
							accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
									Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getThirdPayment(),null,contract.getConNo());
						}
					}
					
					projectDao.update(project);
					contractDao.update(contract);
					IntelligentMeterContract intelligentMeterContract = intelligentMeterContractService.findContractByprojId(project.getProjId());
					if(project.getIsIntelligentMeter()!=null&&ArContractTypeEnum.INTELLIGENCE.getValue().equals(project.getIsIntelligentMeter()) && intelligentMeterContract!=null){
						//生成智能表合同日志
						List<IntelligentMeterContractLog> logList = new ArrayList<IntelligentMeterContractLog>();
						//修改前
						IntelligentMeterContractLog imcContractLogBefor = new IntelligentMeterContractLog();
						BeanUtil.copyNotNullProperties(imcContractLogBefor, intelligentMeterContract);
						imcContractLogBefor.setImclogId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
						imcContractLogBefor.setModifyState(ModifyStateEnum.BEFOR_MODIFY.getValue());
						//更新智能表合同
						intelligentMeterContract.setGasCustName(project.getCustName());//燃气用户
						intelligentMeterContract.setsPartyName(project.getCustName());//燃气用户---乙方
						intelligentMeterContract.setfPartyBankName(contract.getOpenBank());//燃气单位开户行
						intelligentMeterContract.setfPartyBankAccount(contract.getAccount());//燃气单位开户行账号
						intelligentMeterContract.setInstallNums(contract.getHousehold());//户数
						intelligentMeterContract.setTotalCost(new BigDecimal(intelligentMeterContract.getInstallNums()).multiply(intelligentMeterContract.getUnitCost()));//合同金额
						intelligentMeterContract.setsPartyId(project.getCustId());
						PayType payTypeImc = payTypeDao.get(intelligentMeterContract.getPayType());
						if(payTypeImc!=null && "1".equals(payTypeImc.getPayTypeMode())){
							intelligentMeterContract.setFirstPayment(intelligentMeterContract.getTotalCost());//首付款
						}else{
							intelligentMeterContract.setFirstPayment(intelligentMeterContract.getTotalCost().multiply(new BigDecimal(0.5)));//首付款
							intelligentMeterContract.setSecondPayment(intelligentMeterContract.getTotalCost().multiply(new BigDecimal(0.5)));//阶段款
						}
						//修改后
						IntelligentMeterContractLog imcContractLogAfter = new IntelligentMeterContractLog();
						BeanUtil.copyNotNullProperties(imcContractLogAfter, intelligentMeterContract);
						imcContractLogAfter.setImclogId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
						imcContractLogAfter.setModifyState(ModifyStateEnum.AFTER_MODIFY.getValue());
						//生成操作日志
						StringBuffer operateContent= new StringBuffer("");
						if(imcContractLogBefor.getTotalCost().compareTo(imcContractLogAfter.getTotalCost())==0){
							operateContent.append("智能表合同基本信息修改，合同金额不变");
						}else{
							operateContent.append("智能表合同金额由").append(imcContractLogBefor.getTotalCost().toString()).append("修改为").append(imcContractLogAfter.getTotalCost().toString())
									.append("");
						}
						String orlId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
						operateRecordLogService.createOperateRecordLog(orlId, OperateTypeEnum.IMCCONTRACT_MODIFY.getValue(),intelligentMeterContract.getImcId(),operateContent.toString());
						
						imcContractLogBefor.setOrlId(orlId);
						logList.add(imcContractLogBefor);
						imcContractLogAfter.setOrlId(orlId);
						logList.add(imcContractLogAfter);
						imcContractLogDao.batchInsertObjects(logList);
						intelligentMeterContractDao.update(intelligentMeterContract);
						//判断智能表合同是否收款，已收款，则修改流水，位收款则删除之前流水，重新生成流水
						List<CashFlow> cashFlows = cashFlowDao.queryCashFlowByProjIdType(intelligentMeterContract.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),intelligentMeterContract.getImcNo());
						if(cashFlows!=null && cashFlows.size()>0){
							//智能表合同修改应收流水金额
							List<AccrualsRecord> aRecords = accrualsRecordService.findForUpdate(contract.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),ArContractTypeEnum.INTELLIGENCE.getValue());
							int i=1;
							//收付款超收金额
							BigDecimal firstMoney = new BigDecimal(0);
							BigDecimal secondMoney = new BigDecimal(0);
							BigDecimal receiveAmount = new BigDecimal(0);
							for(AccrualsRecord ar : aRecords){
								if(CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue().contains(ar.getArType())){
									ar.setArCost(intelligentMeterContract.getFirstPayment());
									firstMoney = firstMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(intelligentMeterContract.getFirstPayment()):new BigDecimal(0));
									receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
								}else if(CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue().contains(ar.getArType())){
									if(i==2){
										//如果已收首付款大于第二阶段款并且阶段款还未收
										if(firstMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
											ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
											ar.setReceiveAmount(firstMoney);
										}
										secondMoney = secondMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(ar.getReceiveAmount()):new BigDecimal(0));
										ar.setArCost(intelligentMeterContract.getSecondPayment());
									}else{
										//如果已收首付款大于第二阶段款并且阶段款还未收
										if(secondMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
											ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
											ar.setReceiveAmount(firstMoney);
										}
										ar.setArCost(intelligentMeterContract.getThirdPayment());
									}
									receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
								}
								i++;
								accrualsRecordDao.saveOrUpdate(ar);
							}
							//需要退款的生成智能表合同退款流水
							if(receiveAmount.compareTo(intelligentMeterContract.getTotalCost())>0){
								accrualsRecordService.insertAccrualsRecord(project,arId,CollectionTypeEnum.IMC_MODIFY_CONTRACT.getValue(),
										Integer.valueOf(ARFlagEnum.REFUND_MENT.getValue()) ,intelligentMeterContract.getTotalCost().subtract(receiveAmount) ,null,intelligentMeterContract.getImcNo());
							}
						}else{
							//没有收款
							if (StringUtils.isNotBlank(intelligentMeterContract.getPayType())) {
								//查询改合同需要更新的应收流水
								List<AccrualsRecord> accrualsRecord = accrualsRecordService.findForUpdate(intelligentMeterContract.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),ArContractTypeEnum.INTELLIGENCE.getValue());
								//删除应收流水
								accrualsRecordDao.batchDeleteObjects(accrualsRecord);
								//生产合同首付款
								accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue(),
										Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , intelligentMeterContract.getFirstPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),intelligentMeterContract.getImcNo());
								//付款类型为两次付清
								if(payTypeImc!=null && "2".equals(payTypeImc.getPayTypeMode())){
									accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
											Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , intelligentMeterContract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),intelligentMeterContract.getImcNo());
								}else if(payTypeImc!=null && "3".equals(payTypeImc.getPayTypeMode())){
									accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
											Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , intelligentMeterContract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),intelligentMeterContract.getImcNo());
								    accrualsRecordService.insertAccrualsRecord(project,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
											Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , intelligentMeterContract.getThirdPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),intelligentMeterContract.getImcNo());
								}
							}
						}
						
						if(projService.isToCall(project.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
							//修改智能表合同审核最后一级通过后,调用工程信息同步接口todo:
							//1.同步工程信息
							String msg = financeInfoService.synProjectInfoClient(manageRecord.getProjId(), FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue(), FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.INTELLIGENT_CON_PAY.getValue());
							JSONObject jsonbean = JSONObject.fromObject(msg);
							//返回信息-当接口返回失败时，抛出异常
							resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
							if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
								//回滚事物
								throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
							}
						}
					}
				}
				if(projService.isToCall(project.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
					//修改主合同审核最后一级通过后,调用工程信息同步接口todo:
					//1.同步工程信息
					String msg = financeInfoService.synProjectInfoClient(manageRecord.getProjId(), FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue(),  UpdateTypeEnum.UPDATE.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				return arId;
			}
			contractDao.update(contract);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}


	public boolean isCompareTo(Contract contract,ContractLog contractLog){
		String cpr1=StringUtil.isBlank(contract.getPaymentRatio1())?"0":contract.getPaymentRatio1();
		String clpr1=StringUtil.isBlank(contractLog.getPaymentRatio1())?"0":contractLog.getPaymentRatio1();
		String cpr2=StringUtil.isBlank(contract.getPaymentRatio2())?"0":contract.getPaymentRatio2();
		String clpr2=StringUtil.isBlank(contractLog.getPaymentRatio2())?"0":contractLog.getPaymentRatio2();
		String cpr3=StringUtil.isBlank(contract.getPaymentRatio3())?"0":contract.getPaymentRatio3();
		String clpr3=StringUtil.isBlank(contractLog.getPaymentRatio3())?"0":contractLog.getPaymentRatio3();

		if (contract.getContractAmount().compareTo(contractLog.getContractAmount())!=0){
			return true;
		}
		if (cpr1.compareTo(clpr1)!=0){
			return true;
		}else if (cpr2.compareTo(clpr2)!=0){
			return true;
		}else if (cpr3.compareTo(clpr3)!=0){
			return true;
		}else {
			return false;
		}
	}



	/**
	 * 现场踏勘审核
	 * @author fuliwei
	 * @createTime 2017年9月23日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditConnectGasAudit(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,
			String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(constants));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(constants));
		}
		
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.saveManageRecord(manageRecord);
		//manageRecordDao.save(manageRecord);
		
		
		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.SURVEY_AUDIT.getValue());//踏勘审核
		
		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			 notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			 notice.setAuditType(NoticeMenuEnum.SURVEY_AUDIT1.getType());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeContent(NoticeMenuEnum.SURVEY_AUDIT1.getMessage());					//待踏勘审核一级
			 notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setProjId(manageRecord.getProjId());
			 notice.setCorpId(pro.getCorpId());
		}*/
		Project project = projectDao.get(manageRecord.getProjId());
		//贵安有指定工程部现场代表，则按原来的审核级别
		//获取数据过滤配置：规则分公司ID_立项部门ID_菜单ID
		//贵安市场部立项的，如果现场踏勘没有现场代表参与，则为审核级别减1
		List<DataFilerSetUpDto> filters = Constants.getDataFilterMapByKey(pro.getCorpId()+"_" +pro.getDeptId()+ "_"+manageRecord.getMenuId());
		if(filters!=null && filters.size()>0){
			if(StringUtil.isNotBlank(project.getSurveyBuilderId())){
				auditLevel=String.valueOf(Integer.parseInt(auditLevel));
			}else if (1<Integer.parseInt(auditLevel)){
				auditLevel=String.valueOf(Integer.parseInt(auditLevel)-1);
			}
		}
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);
		String toder="";
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			
			//审核通过--将通知置为无效
			/*if(notice!=null){
				 notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		
		//noticeDao.saveOrUpdate(notice);
		
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),stepID, isNextStatus);
			project.setProjStatusId(status);
			//产生业务操作记录
			//String orId = IDUtil.getUniqueId(constants);
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(constants);
				//不通过 
				toder=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(toder);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				if(ProjLtypeEnum.CIVILIAN.getValue().equals(project.getProjectType())){
					project.setCostMember(project.getAccepter());
					project.setCostMemberId(project.getAccepterId());
				}
				toder=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "true");
				project.setToDoer(toder);
			}
			//2.更新工程记录
			projectDao.update(project);
		}
		
	}
	/**
	 * 根据工程id、步骤id、审核人id查询审核通过的信息
	 * @author liaoyq
	 * @createTime 2017年10月25日
	 * @param projId
	 * @param stepId
	 * @param mrCsr
	 */
	@Override
	public ManageRecord findByMrCsrId(String projId, String stepId,
			String mrCsr) {
		return manageRecordDao.findByMrCsrId(projId,stepId,mrCsr);
	}
	/**
	 * 根据工程id、步骤id、审核级别查询审核通过的信息
	 * @author liaoyq
	 * @createTime 2017年10月25日
	 * @param projId
	 * @param stepId
	 * @param level
	 */
	@Override
	public ManageRecord findFirstMrCsr(String projId, String stepId, String level) {
		return manageRecordDao.findFirstMrCsr(projId,stepId,level);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String imcModifyAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * 4.逐级审核通过，产生应收流水
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));//审核记录主键id
		//manageRecord.setDocTypeId(docTypeID);
		Project pro = projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);											//合同修改审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 						//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());						//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);

		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，合同修改状态变为未通过
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;

			//若此次审核级别为单据类型设置的级别，则更新工程信息及合同审核状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = true;
		}else{
			//智能表审核待办todo：
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro,StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.INTE_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),nextGrade.toString(),true);
			
		}
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			IntelligentMeterContract imContract = intelligentMeterContractDao.get(manageRecord.getBusinessOrderId());
			OperateRecordLog operateRecordLog = operateRecordLogService.findLatelyLog(OperateTypeEnum.IMCCONTRACT_MODIFY.getValue(),imContract.getImcId());
			IntelligentMeterContractLog contractLog = imContractLogService.findByModifystate(ModifyStateEnum.BEFOR_MODIFY.getValue(),operateRecordLog.getOrlId());
			//保存未通过标记
			if(MrResultEnum.NOT_PASSED.getValue().equals(manageRecord.getMrResult())){
				BeanUtil.copyNotNullProperties(imContract, contractLog);
				imContract.setModifyStatus(ModifyStatusEnum.AUDIT_NO_PASS.getValue());
				imContract.setSignBack(Constants.MODULE_CODE_CONTRACT);
				operateRecordService.cerateCurOperateRecord(pro,StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.INTE_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,false);
				
			}
			//PayType payType = payTypeDao.get(imContract.getPayType());
			//清空未通过标记
			if(MrResultEnum.PASSED.getValue().equals(manageRecord.getMrResult())){
				imContract.setSignBack("");
				//合同修改状态：已通过
				imContract.setModifyStatus(ModifyStatusEnum.AUDIT_PASS.getValue());
				//判断付款次数
				PayType payTypeLog = payTypeDao.get(contractLog.getPayType());
				PayType payType = payTypeDao.get(imContract.getPayType());
				//查询改合同需要更新的应收流水
				List<AccrualsRecord> accrualsRecords = accrualsRecordService.findForUpdate(imContract.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),ArContractTypeEnum.INTELLIGENCE.getValue());
				
				if(payTypeLog!=null && payType!=null && payTypeLog.getPayTypeMode().equals(payType.getPayTypeMode())){
					//付款方式不变的，修改流水应收金额
					int i=1;
					//收付款超收金额
					BigDecimal firstMoney = new BigDecimal(0);
					BigDecimal secondMoney = new BigDecimal(0);
					BigDecimal receiveAmount = new BigDecimal(0);
					for(AccrualsRecord ar : accrualsRecords){
						if(CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue().contains(ar.getArType())){
							ar.setArCost(imContract.getFirstPayment());
							firstMoney = firstMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(imContract.getFirstPayment()):new BigDecimal(0));
							receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
						}else if(CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue().contains(ar.getArType())){
							if(i==2){
								//如果已收首付款大于第二阶段款并且阶段款还未收
								if(firstMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
									ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
									ar.setReceiveAmount(firstMoney);
								}
								secondMoney = secondMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(ar.getReceiveAmount()):new BigDecimal(0));
								ar.setArCost(imContract.getSecondPayment());
							}else{
								//如果已收首付款大于第二阶段款并且阶段款还未收
								if(secondMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
									ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
									ar.setReceiveAmount(firstMoney);
								}
								ar.setArCost(imContract.getThirdPayment());
							}
							receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
						}
						i++;
						accrualsRecordDao.saveOrUpdate(ar);
					}
					//需要退款的生成退款流水
					if(receiveAmount.compareTo(imContract.getTotalCost())>0){
						accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.IMC_MODIFY_CONTRACT.getValue(),
								Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) ,imContract.getTotalCost().subtract(receiveAmount) ,ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
					}
				}else{
					//删除应收流水
					accrualsRecordDao.batchDeleteObjects(accrualsRecords);
					//生产合同首付款
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getFirstPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
					//付款类型为两次付清
					if(payType!=null && "2".equals(payType.getPayTypeMode())){
						accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
								Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
					}if(payType!=null && "3".equals(payType.getPayTypeMode())){
						accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
								Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
					
						accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
								Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getThirdPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
					}
				}
				intelligentMeterContractDao.update(imContract);
				if(projService.isToCall(imContract.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
					ResultMessage resultMessage = new ResultMessage();
					//智能表合同审核，调用工程信息同步接口todo:
					String msg = financeInfoService.synProjectInfoClient(manageRecord.getProjId(), FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue(),  FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.INTELLIGENT_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(imContract.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
				operateRecordService.cerateCurOperateRecord(pro,StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.INTE_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,false);
				
				return Constants.OPERATE_RESULT_SUCCESS;
			}
			intelligentMeterContractDao.update(imContract);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 智能表合同修改通过时改变流水
	 * @return
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public String imcModifySaveTrsnRd(IntelligentMeterContract imContract) throws ParseException{
		//虚拟审核信息--开始，智能表合同无需修改审核时生成一条虚拟的审核记录存入数据库方便查找
		ManageRecord  manageRecord = new ManageRecord();
		if(StringUtils.isBlank(manageRecord.getMrId())){
			manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));  //生成审核记录主键
		}
		if(imContract != null){
			manageRecord.setBusinessOrderId(imContract.getImcId()); //业务ID
			manageRecord.setProjId(imContract.getProjId());   //工程ID
			manageRecord.setProjNo(imContract.getProjNo());   //工程编号
		}
		manageRecord.setStepId(StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue());	//智能表合同修改审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 						//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());						//审核部门
		manageRecord.setMrResult(MrResultEnum.PASSED.getValue());   //审核通过
		manageRecord.setMrResultDes(MrResultEnum.PASSED.getMessage());   //审核通过
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());  //审核时间
		manageRecordDao.saveOrUpdate(manageRecord);  //保存审核记录
		//虚拟审核信息--结束
		Project pro = projectDao.get(manageRecord.getProjId());
		OperateRecordLog operateRecordLog = operateRecordLogService.findLatelyLog(OperateTypeEnum.IMCCONTRACT_MODIFY.getValue(),imContract.getImcId());
		IntelligentMeterContractLog contractLog = imContractLogService.findByModifystate(ModifyStateEnum.BEFOR_MODIFY.getValue(),operateRecordLog.getOrlId());
		imContract.setSignBack("");
		//合同修改状态：已通过
		imContract.setModifyStatus(ModifyStatusEnum.AUDIT_PASS.getValue());
		//判断付款次数
		PayType payTypeLog = payTypeDao.get(contractLog.getPayType());
		PayType payType = payTypeDao.get(imContract.getPayType());
		//查询改合同需要更新的应收流水
		List<AccrualsRecord> accrualsRecords = accrualsRecordService.findForUpdate(imContract.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),ArContractTypeEnum.INTELLIGENCE.getValue());
		
		if(payTypeLog!=null && payType!=null && payTypeLog.getPayTypeMode().equals(payType.getPayTypeMode())){
			//付款方式不变的，修改流水应收金额
			int i=1;
			//收付款超收金额
			BigDecimal firstMoney = new BigDecimal(0);
			BigDecimal secondMoney = new BigDecimal(0);
			BigDecimal receiveAmount = new BigDecimal(0);
			for(AccrualsRecord ar : accrualsRecords){
				if(CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue().contains(ar.getArType())){
					ar.setArCost(imContract.getFirstPayment());
					firstMoney = firstMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(imContract.getFirstPayment()):new BigDecimal(0));
					receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
				}else if(CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue().contains(ar.getArType())){
					if(i==2){
						//如果已收首付款大于第二阶段款并且阶段款还未收
						if(firstMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
							ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
							ar.setReceiveAmount(firstMoney);
						}
						secondMoney = secondMoney.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount().subtract(ar.getReceiveAmount()):new BigDecimal(0));
						ar.setArCost(imContract.getSecondPayment());
					}else{
						//如果已收首付款大于第二阶段款并且阶段款还未收
						if(secondMoney.compareTo(new BigDecimal(0))>0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())){
							ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
							ar.setReceiveAmount(firstMoney);
						}
						ar.setArCost(imContract.getThirdPayment());
					}
					receiveAmount = receiveAmount.add(ar.getReceiveAmount()!=null?ar.getReceiveAmount():new BigDecimal(0));
				}
				i++;
				accrualsRecordDao.saveOrUpdate(ar);
			}
			//需要退款的生成退款流水
			if(receiveAmount.compareTo(imContract.getTotalCost())>0){
				accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.IMC_MODIFY_CONTRACT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) ,imContract.getTotalCost().subtract(receiveAmount) ,ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
			}
		}else{
			//删除应收流水
			accrualsRecordDao.batchDeleteObjects(accrualsRecords);
			//生产合同首付款
			accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue(),
					Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getFirstPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
			//付款类型为两次付清
			if(payType!=null && "2".equals(payType.getPayTypeMode())){
				accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
			}if(payType!=null && "3".equals(payType.getPayTypeMode())){
				accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
			
				accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , imContract.getThirdPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),imContract.getImcNo());
			}
		}
		intelligentMeterContractDao.saveOrUpdate(imContract);
		if(projService.isToCall(imContract.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
			ResultMessage resultMessage = new ResultMessage();
			//智能表合同审核，调用工程信息同步接口todo:
			String msg = financeInfoService.synProjectInfoClient(manageRecord.getProjId(), FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue(),  FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.INTELLIGENT_CON_PAY.getValue());
			JSONObject jsonbean = JSONObject.fromObject(msg);
			//返回信息-当接口返回失败时，抛出异常
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
			if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(imContract.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
				//回滚事物
				throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	
	}
	/**
	 * 查询业务单审核信息结果
	 * @author fuliwei
	 * @createTime 2017年12月1日
	 * @param 
	 * @return
	 */
	@Override
	public ManageRecord queryBusAuditInformation(String businessOrderId, String StepId) {
		ManageRecord mr = manageRecordDao.queryBusAuditInformation(businessOrderId, StepId);
		if(mr!=null && StringUtil.isNotBlank(mr.getMrCsr())){
			Staff s = staffDao.get(mr.getMrCsr());
			mr.setMrCsrName(s.getStaffName());
		}
		return mr;
	}
	
	
	/**
	 * 保存回退审核
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void auditFallbackSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,String constants) throws Exception{

		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));		//审核记录主键id
		
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
		}
		
		Project pro=projectDao.get(manageRecord.getProjId());
		if (pro!=null) {
			String originalStepId = WorkFlowActionEnum.getStepCodeByStatusCode(pro.getProjStatusId());
			String level = docTypeDao.getAuditLevel(pro, originalStepId, stepID);
			if (StringUtil.isNotBlank(level)) {
				auditLevel = level;
			}
		}

		manageRecord.setStepId(StepOutWorkflowEnum.FALLBACK_AUDIT.getValue()+"_"+stepID);
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){ //审核人
			manageRecord.setMrCsr(loginInfo.getStaffId());
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());//审核部门

		manageRecordDao.saveOrUpdate(manageRecord);//1.产生审核记录


		FallbackApply fa=fallbackApplyService.findByFaId(manageRecord.getBusinessOrderId());
		if (fa!=null) {
			if (manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())) {//审核不通过
				fa.setAuditState(AuditResultEnum.NOT_PASSED.getValue());
				fa.setAuditInfo(manageRecord.getMrAopinion());
				fallbackApplyDao.saveOrUpdate(fa);
				manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);//将审核历史作废

				operateRecordService.updateNextNotice(manageRecord.getBusinessOrderId(),manageRecord.getMrAuditLevel(),false);

			} else if (auditLevel.equals(manageRecord.getMrAuditLevel())) {//若此次审核级别为单据类型设置的级别，则更新工程状态
				fa.setAuditState(AuditResultEnum.PASSED.getValue());//审核通过
				fa.setAuditInfo(manageRecord.getMrAopinion());
				fallbackApplyDao.saveOrUpdate(fa);
				//this.fallBackNew(pro, fa);//2019-01-24 fuliwei，回退新方法
				fallbackApplyService.rollBackProcessingRelatedContent(pro,fa);

				operateRecordService.updateNextNotice(manageRecord.getBusinessOrderId(),manageRecord.getMrAuditLevel(),false);

			}else if (manageRecord.getMrResult().equals(MrResultEnum.PASSED.getValue())){//审核通过且不是最后一级操作待办

				operateRecordService.updateNextNotice(manageRecord.getBusinessOrderId(),manageRecord.getMrAuditLevel(),true);
			}
		}

	}




	/**
	 * 
	 * 注释：回退到预算派工
	 * 1.修改工程状态
	 * 2.生成回退信息
	 * 3.作废操作记录
	 * @author liaoyq
	 * @createTime 2018年12月7日
	 * @param projId
	 * @param fa
	 *
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String fallBackBudgetDis(String projId, FallbackApply fa) {
		Project pro=projectDao.get(projId);
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
		bi.setProjId(projId);
		bi.setProjNo(pro.getProjNo());
		bi.setProjName(pro.getProjName());
        bi.setBudgeter(pro.getBudgeter());
        bi.setBudgeterId(pro.getBudgeterId());
		
		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());
		//操作记录无效
		List<String> stepIds=new ArrayList<String>();
		stepIds.add(StepEnum.BUDGET_DISPATCH.getValue());
		operateRecordDao.updateOrValid(stepIds, projId);//作废操作记录
		
		pro.setProjStatusId(WorkFlowActionEnum.byActionCode(StepEnum.BUDGET_DISPATCH.getValue()).getStatusCode());
		pro.setSignBack(Constants.MODULE_CODE_BUDGET);
		projectDao.saveOrUpdate(pro);

		backInfoDao.save(bi);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 回退到安装合同，增加回退时已判断没有收款的才进行回退，可删除应收流水
	 * 1.处理合同信息
	 * 2.处理操作日志
	 * 3.处理审核日志
	 * 4.虚拟审核信息
	 * 5.删除应收流水
	 * @author liaoyq
	 * @createTime 2018年5月9日
	 * @param projId
	 * @param fa
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
	public String fallBackContract(String projId, FallbackApply fa) throws ParseException {
		Project pro=projectDao.get(projId);
		
		//处理合同信息
		Contract con=contractDao.viewContractByprojId(projId);
		
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		bi.setProjId(projId);
		bi.setProjNo(pro.getProjNo());
		bi.setProjName(pro.getProjName());
		bi.setContractAmount(con.getContractAmount());			//更新合同金额
		bi.setSignDate(con.getSignDate());						//更新签订日期
		bi.setOperator(con.getConAgent());						//更新经办人
		
		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());
		
		con.setSignDate(null);
		con.setConAgent(null);
		con.setModifyStatus(null);
		con.setIsPass(null);
		con.setIsPrint("0");
		con.setRemark(con.getRemark()+" 回退备注：原工程已在 "+pro.getSignDate()+" 签订安装合同，现已回退到安装合同。");
		contractDao.saveOrUpdate(con); //更新合同信息
		//操作记录无效
		List<String> stepIds=new ArrayList<String>();
		
		pro.setContractAmount(null);		//更新合同金额
		pro.setSignDate(null);				//更新签订日期
		pro.setOperator(null);              //更新经办人
	    manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.CONTRACT_AUDIT.getValue());		//作废合同审核
	    stepIds.add(StepEnum.CONSTRUCT_CONTRACT.getValue());		//安装合同
		
		//民用的需要删除
	    if("11".equals(pro.getProjectType())){
	    	stepIds.add(StepEnum.DESIGN_DRAWING.getValue());			//设计出图
	    	stepIds.add(StepEnum.DRAWING_SIGN_AUDIT.getValue());		//图纸审核
	    	stepIds.add(StepEnum.OFFICIAL_DRAWING.getValue());			//图纸签收
	    	manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.DRAWING_SIGN_AUDIT.getValue());		//作废图纸审核
	    	//处理设计信息
			DesignInfo di=designInfoDao.queryInfoByProjId(projId);
			bi.setProjId(projId);
			bi.setProjNo(pro.getProjNo());
			bi.setProjName(pro.getProjName());
			bi.setDuCompleteDate(di.getDuCompleteDate());			//设计完成日期
			bi.setDesignDrawingNo(di.getDesignDrawingNo());			//设计图号
			bi.setDesignDrawingCopies(di.getDesignDrawingCopies());	//图纸份数
			bi.setDesignDrawingSheets(di.getDesignDrawingSheets());	//每份张数
			designInfoDao.delete(di);
			
			pro.setDuCompleteDate(null);	//设计完成时间
	    }
	    
		operateRecordDao.updateOrValid(stepIds, projId);//作废操作记录
		
		pro.setProjStatusId(WorkFlowActionEnum.byActionCode(StepEnum.CONSTRUCT_CONTRACT.getValue()).getStatusCode());
		pro.setSignBack(Constants.MODULE_CODE_CONTRACT);
		projectDao.saveOrUpdate(pro);

		backInfoDao.save(bi);
		//虚拟审核信息
		DocType docType = docTypeService.findByStepId(StepEnum.CONTRACT_AUDIT.getValue());
		ManageRecord manageRecord=new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));		//审核记录主键id
		if(con!=null){
			manageRecord.setBusinessOrderId(con.getConId());
		}
		manageRecord.setDocTypeId(docType.getId());
		manageRecord.setStepId(StepEnum.CONTRACT_AUDIT.getValue());
		manageRecord.setProjId(projId);
		manageRecord.setProjNo(pro.getProjNo());
		manageRecord.setMrAopinion(fa.getFallbackReason());	//回退原因
		manageRecord.setMrCsr(fa.getApplyOperatorId());		//退回申请人即审核人
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());//审核时间
		manageRecord.setFlag("0");							//默认为无效
		manageRecord.setMrResult("0");						//结果不通过
		manageRecordDao.save(manageRecord);
		
		//删除应收流水
		accrualsRecordDao.deleteArList(pro.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),con.getConNo());
		//调用接口删除工程信息，todo:
		if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_DELETE.getValue())){
			String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.CONTRACT_DELETE.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
			ResultMessage resultMessage = new ResultMessage();
			JSONObject jsonbean = JSONObject.fromObject(msg);
			//返回信息-当接口返回失败时，抛出异常
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
			if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_DELETE.getValue())){
				//回滚事物
				throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 退回到设计出图
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String fallBackDesign(String projId, FallbackApply fa) {
		
		Project pro=projectDao.get(projId);
		
		//处理设计信息
		DesignInfo di=designInfoDao.queryInfoByProjId(projId);
		
		GovAuditCost govAuditCost = govAuditCostDao.queryByProjIdAndType(projId,GovAuditCostTypeEnum.BUDGET.getValue());
		
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
		bi.setProjId(projId);
		if (di!=null) {
			bi.setDuCompleteDate(di.getDuCompleteDate());            //设计完成日期
			bi.setDesignDrawingNo(di.getDesignDrawingNo());            //设计图号
			bi.setDesignDrawingCopies(di.getDesignDrawingCopies());    //图纸份数
			bi.setDesignDrawingSheets(di.getDesignDrawingSheets());    //每份张数
		}
		if (pro!=null) {
			bi.setProjNo(pro.getProjNo());
			bi.setProjName(pro.getProjName());
			bi.setBudgeter(pro.getBudgeter());                        //预算员
			bi.setBudgeterId(pro.getBudgeterId());                    //预算员ID
			bi.setBudgetTotalCost(pro.getBudgetTotalCost());        //预算总造价
			bi.setBudgetDate(pro.getBudgetDate());                    //预算日期
			bi.setConfirmTotalCost(pro.getConfirmTotalCost());        //确定造价
			bi.setChangeReason(pro.getChangeReason());                //变动原因
			bi.setCostRemark(pro.getCostRemark());                    //造价备注
			bi.setAffirmCostDate(pro.getAffirmCostDate());            //确定日期
			bi.setCostMember(pro.getCostMember());                    //造价员
			bi.setCostMemberId(pro.getCostMemberId());                //造价员ID
			bi.setContractAmount(pro.getContractAmount());			//更新合同金额
			bi.setSignDate(pro.getSignDate());						//更新签订日期
			bi.setOperator(pro.getOperator());						//更新经办人
		}
		if(govAuditCost!=null && StringUtil.isNotBlank(govAuditCost.getGacId())){
			bi.setAuthorizedCost(govAuditCost.getAuthorizedCost());					//审定价格
			bi.setAuthorizedCostDate(govAuditCost.getAuthorizedCostDate());			//审定价登记日期
			bi.setGacStaffId(govAuditCost.getGacStaffId());							//审定价登记人ID
		}

		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());
		
		backInfoDao.save(bi);
		
		
		//设计信息
		if(di!=null){
			if(pro.getDuCompleteDate()!=null){
				di.setDesginRemark(di.getDesginRemark()+" 备注：原工程已在 "+pro.getDuCompleteDate()+" 完成设计，现已回退到设计出图。");
			}else{
				di.setDesginRemark(di.getDesginRemark()+" 备注：原工程完成设计，现已回退到设计出图。");
			}
			
			
			pro.setDuCompleteDate(null);	//设计完成时间
			
			di.setDuCompleteDate(null);		//设计完成时间
			di.setDesignDrawingNo(null);	//设计图号
			di.setDesignDrawingCopies(null);//图纸份数
			di.setDesignDrawingSheets(null);//每份张数
		}
		designInfoDao.saveOrUpdate(di);
		
		
		
		//删除预算记录及工程信息、审核记录无效、操作记录无效
		Budget budget=budgetDao.queryById(projId,BudgetAdjustEnum.NOT_ADJUST.getValue());
		if(budget!=null && StringUtil.isNotBlank(budget.getBudgetId())){
			budgetDao.delete(budget.getBudgetId());
		}
		pro.setBudgeter(null);				//预算员
		pro.setBudgeterId(null);			//预算员ID
		pro.setBudgetTotalCost(null);		//预算总造价
		pro.setBudgetDate(null);			//预算日期
		
		
		//删除造价及工程信息、审核记录无效、操作记录无效
		pro.setConfirmTotalCost(null);		//确定造价
		pro.setChangeReason(null);         //变动原因
		pro.setCostRemark(null);           //造价备注
		pro.setAffirmCostDate(null);	   //确定日期
		pro.setCostMember(null); 			// 造价员
		pro.setCostMemberId(null); 			// 造价员ID
		
		
		
		//政府预算评审及工程信息、审核记录无效、操作记录无效
		if(govAuditCost!=null && StringUtil.isNotBlank(govAuditCost.getGacId())){
			govAuditCostDao.delete(govAuditCost.getGacId());
		}
		
		
		//删除合同及工程信息、审核记录无效、操作记录无效
		Contract con=contractDao.viewContractByprojId(projId);
		
		//操作记录无效
		List<String> stepIds=new ArrayList<String>();
				
		//如果是非民用的 则删除合同
		Department dep = departmentService.queryDepartment(pro.getDeptId());
		if(null!=dep&&StringUtil.isNoneBlank(dep.getDeptDivide())){
			String deptDivide=dep.getDeptDivide();
			if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(dep.getDeptDivide())){
				if(con!=null && StringUtil.isNotBlank(con.getConId())){
					contractDao.delete(con.getConId());
				}
				pro.setContractAmount(null);		//更新合同金额
			    pro.setSignDate(null);				//更新签订日期
			    pro.setOperator(null);              //更新经办人
			    manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.CONTRACT_AUDIT.getValue());		//合同审核
			    stepIds.add(StepEnum.CONSTRUCT_CONTRACT.getValue());		//安装合同
			}
		}
			
		
		
		
	    
		//审核记录置为无效
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.DRAWING_SIGN_AUDIT.getValue());	//图纸审核
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.BUDGET_AUDIT.getValue());			//预算审核
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.PROJECT_COST_AUDIT.getValue());	//造价审核
		
		
		
		stepIds.add(StepEnum.DESIGN_DRAWING.getValue());			//设计出图
		stepIds.add(StepEnum.DRAWING_SIGN_AUDIT.getValue());		//图纸审核
		stepIds.add(StepEnum.OFFICIAL_DRAWING.getValue());			//图纸签收
		stepIds.add(StepEnum.BUDGET_DISPATCH.getValue());			//预算派工
		stepIds.add(StepEnum.BUDGET_RESULT_REGISTER.getValue());	//预算记录
		stepIds.add(StepEnum.BUDGET_AUDIT.getValue());				//预算审核
		stepIds.add(StepEnum.BUDGET_GOV_AUDIT_COST.getValue());	//预算审定价
		stepIds.add(StepEnum.PROJECT_COST.getValue());				//造价确认
		stepIds.add(StepEnum.PROJECT_COST_AUDIT.getValue());		//造价审核
		
		
		
		
		
		operateRecordDao.updateOrValid(stepIds, projId);
		
		pro.setProjStatusId(WorkFlowActionEnum.byActionCode(StepEnum.DESIGN_DRAWING.getValue()).getStatusCode());
		pro.setSignBack(Constants.MODULE_CODE_DESIGN);
		projectDao.saveOrUpdate(pro);
		
		
		//通知未处理
		//预算审核
		Notice noticeBudget=noticeDao.findByProjIdAndType(projId, NoticeAuditTypeEnum.BUDGET_AUDIT.getValue());
		if(noticeBudget!=null){
			noticeBudget.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			noticeDao.saveOrUpdate(noticeBudget);
		}
		
		//造价审核
		Notice noticeCost=noticeDao.findByProjIdAndType(projId, NoticeAuditTypeEnum.BUDGET_AUDIT.getValue());
		if(noticeCost!=null){
			noticeCost.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			noticeDao.saveOrUpdate(noticeCost);
		}
		
		
		
		//虚拟审核信息
		DocType docType = docTypeService.findByStepId(StepEnum.DRAWING_SIGN_AUDIT.getValue());
		ManageRecord manageRecord=new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));		//审核记录主键id
		if(di!=null){
			manageRecord.setBusinessOrderId(di.getDiId());
		}
		manageRecord.setDocTypeId(docType.getId());
		manageRecord.setStepId(StepEnum.DRAWING_SIGN_AUDIT.getValue());
		manageRecord.setProjId(projId);
		manageRecord.setProjNo(pro.getProjNo());
		manageRecord.setMrAopinion(fa.getFallbackReason());	//回退原因
		manageRecord.setMrCsr(fa.getApplyOperatorId());		//退回申请人即审核人
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());			//退回申请时间即审核时间
		manageRecord.setFlag("0");							//默认为无效
		manageRecord.setMrResult("0");						//结果不通过
		manageRecordDao.save(manageRecord);
		
		
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	
	/**
	 * 退回到预算记录
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String fallBackBudget(String projId,FallbackApply fa) {
		Project pro=projectDao.get(projId);
		
		GovAuditCost govAuditCost = govAuditCostDao.queryByProjIdAndType(projId,GovAuditCostTypeEnum.BUDGET.getValue());
		
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
		bi.setProjId(projId);
		bi.setProjNo(pro.getProjNo());
		bi.setProjName(pro.getProjName());
		
		bi.setBudgeter(pro.getBudgeter());						//预算员
		bi.setBudgeterId(pro.getBudgeterId());					//预算员ID
		bi.setBudgetTotalCost(pro.getBudgetTotalCost());		//预算总造价
		bi.setBudgetDate(pro.getBudgetDate());					//预算日期
		
		
		
		bi.setConfirmTotalCost(pro.getConfirmTotalCost());		//确定造价
		bi.setChangeReason(pro.getChangeReason());				//变动原因
		bi.setCostRemark(pro.getCostRemark());					//造价备注
		bi.setAffirmCostDate(pro.getAffirmCostDate());			//确定日期
		bi.setCostMember(pro.getCostMember());					//造价员
		bi.setCostMemberId(pro.getCostMemberId());				//造价员ID
		
		if(govAuditCost!=null && StringUtil.isNotBlank(govAuditCost.getGacId())){
			bi.setAuthorizedCost(govAuditCost.getAuthorizedCost());					//审定价格
			bi.setAuthorizedCostDate(govAuditCost.getAuthorizedCostDate());			//审定价登记日期
			bi.setGacStaffId(govAuditCost.getGacStaffId());							//审定价登记人ID
		}
		
		
		bi.setContractAmount(pro.getContractAmount());			//更新合同金额
		bi.setSignDate(pro.getSignDate());						//更新签订日期
		bi.setOperator(pro.getOperator());						//更新经办人
		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());
		
		backInfoDao.save(bi);
		
		
		//删除造价及工程信息、审核记录无效、操作记录无效
		pro.setConfirmTotalCost(null);		//确定造价
		pro.setChangeReason(null);         //变动原因
		pro.setCostRemark(null);           //造价备注
		pro.setAffirmCostDate(null);	   //确定日期
		pro.setCostMember(null); 			// 造价员
		pro.setCostMemberId(null); 			// 造价员ID
		
		//删除预算记录及工程信息、审核记录无效、操作记录无效
		Budget budget=budgetDao.queryById(projId,BudgetAdjustEnum.NOT_ADJUST.getValue());
		
		if(budget!=null){
			budget.setRemark(budget.getRemark()+" 备注：原工程已在 "+budget.getBudgetDate()+" 完成预算，现已回退到预算记录。");
			budgetDao.saveOrUpdate(budget);
		}
		
		
		//政府预算评审及工程信息、审核记录无效、操作记录无效
		if(govAuditCost!=null && StringUtil.isNotBlank(govAuditCost.getGacId())){
			govAuditCostDao.delete(govAuditCost.getGacId());
		}
		
		
		//删除合同及工程信息、审核记录无效、操作记录无效
		Contract con=contractDao.viewContractByprojId(projId);
		if(con!=null && StringUtil.isNotBlank(con.getConId())){
			contractDao.delete(con.getConId());
		}
		pro.setContractAmount(null);		//更新合同金额
	    pro.setSignDate(null);				//更新签订日期
	    pro.setOperator(null);              //更新经办人
		
	    
		//审核记录置为无效
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.BUDGET_AUDIT.getValue());			//预算审核
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.PROJECT_COST_AUDIT.getValue());	//造价审核
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.CONTRACT_AUDIT.getValue());		//合同审核
		
		
		//操作记录无效
		List<String> stepIds=new ArrayList<String>();
		stepIds.add(StepEnum.BUDGET_RESULT_REGISTER.getValue());	//预算记录
		stepIds.add(StepEnum.BUDGET_AUDIT.getValue());				//预算审核
		stepIds.add(StepEnum.BUDGET_GOV_AUDIT_COST.getValue());	//预算审定价
		stepIds.add(StepEnum.PROJECT_COST.getValue());				//造价确认
		stepIds.add(StepEnum.PROJECT_COST_AUDIT.getValue());		//造价审核
		stepIds.add(StepEnum.CONSTRUCT_CONTRACT.getValue());		//安装合同
		operateRecordDao.updateOrValid(stepIds, projId);
		
		pro.setProjStatusId(WorkFlowActionEnum.byActionCode(StepEnum.BUDGET_RESULT_REGISTER.getValue()).getStatusCode());
		pro.setSignBack(Constants.MODULE_CODE_BUDGET);
		projectDao.saveOrUpdate(pro);
		
		//通知未处理
		//预算审核
		Notice noticeBudget=noticeDao.findByProjIdAndType(projId, NoticeAuditTypeEnum.BUDGET_AUDIT.getValue());
		if(noticeBudget!=null){
			noticeBudget.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			noticeDao.saveOrUpdate(noticeBudget);
		}
		
		
		//造价审核
		Notice noticeCost=noticeDao.findByProjIdAndType(projId, NoticeAuditTypeEnum.BUDGET_AUDIT.getValue());
		if(noticeCost!=null){
			noticeCost.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			noticeDao.saveOrUpdate(noticeCost);
		}
		
		
		
		//虚拟审核信息
		DocType docType = docTypeService.findByStepId(StepEnum.BUDGET_AUDIT.getValue());
		ManageRecord manageRecord=new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));		//审核记录主键id
		if(budget!=null&& StringUtil.isNotBlank(budget.getBudgetId())){
			manageRecord.setBusinessOrderId(budget.getBudgetId());
		}
		manageRecord.setDocTypeId(docType.getId());
		manageRecord.setStepId(StepEnum.BUDGET_AUDIT.getValue());
		manageRecord.setProjId(projId);
		manageRecord.setProjNo(pro.getProjNo());
		manageRecord.setMrAopinion(fa.getFallbackReason());	//回退原因
		manageRecord.setMrCsr(fa.getApplyOperatorId());		//退回申请人即审核人
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());			//退回申请时间即审核时间
		manageRecord.setFlag("0");							//默认为无效
		manageRecord.setMrResult("0");						//默认为无效
		manageRecordDao.save(manageRecord);
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 退回到施工预算
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String fallBackConsBudget(String projId, FallbackApply fa) {
		Project pro=projectDao.get(projId);
		
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		bi.setProjId(projId);
		bi.setProjNo(pro.getProjNo());
		bi.setProjName(pro.getProjName());
		
		bi.setBudgeterAudit(pro.getBudgeterAudit());		//施工预算审核员
		bi.setBudgeterAuditId(pro.getBudgeterAuditId());	//施工预算审核员Id
		bi.setFirstSubmitAmount(pro.getFirstSubmitAmount());//初始施工预算合计清单计价
		bi.setSubmitAmount(pro.getSubmitAmount());			//施工预算合计清单计价
		
		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());
				
		backInfoDao.save(bi);
		
		
		//处理分包预算信息的审核人
		
		pro.setBudgeterAudit(null);		 //施工预算审核员
		pro.setBudgeterAuditId(null);	 //施工预算审核员Id
		
		pro.setFirstSubmitAmount(null);//初始施工预算合计清单计价
		pro.setSubmitAmount(null);		//施工预算合计清单计价
		
	    
		//审核记录置为无效
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());	//预算初审核
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.QUALITIES_JUDGEMENT.getValue());	//预算审核

		
		//操作记录无效
		List<String> stepIds=new ArrayList<String>();
		stepIds.add(StepEnum.QUALITIES_DECLARATION.getValue());		//施工预算
		stepIds.add(StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());			//施工预算派工
		stepIds.add(StepEnum.QUALITIES_DISPATCH.getValue());			//施工预算派工
		stepIds.add(StepEnum.QUALITIES_JUDGEMENT.getValue());			//预算审核
		operateRecordDao.updateOrValid(stepIds, projId);
		
		
		pro.setProjStatusId(WorkFlowActionEnum.byActionCode(StepEnum.QUALITIES_DECLARATION.getValue()).getStatusCode());
		pro.setSignBack(Constants.MODULE_CODE_SUBCONTRACT);
		projectDao.saveOrUpdate(pro);
		
		
		//通知未处理
		//施工预算审核
		Notice noticeCost=noticeDao.findByProjIdAndType(projId, NoticeAuditTypeEnum.CON_BUDGET_AUDIT.getValue());
		if(noticeCost!=null){
			noticeCost.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			noticeDao.saveOrUpdate(noticeCost);
		}
		
		
		SubBudget sb=subBudgetDao.findByProjId(projId);
		
		
		//虚拟审核信息
		DocType docType = docTypeService.findByStepId(StepEnum.QUALITIES_JUDGEMENT.getValue());
		ManageRecord manageRecord=new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));		//审核记录主键id
		if(sb!=null){
			manageRecord.setBusinessOrderId(sb.getSbId());
		}else{
			manageRecord.setBusinessOrderId(manageRecord.getMrId());
		}
		
		manageRecord.setDocTypeId(docType.getId());
		manageRecord.setStepId(StepEnum.QUALITIES_JUDGEMENT.getValue());
		manageRecord.setProjId(projId);
		manageRecord.setProjNo(pro.getProjNo());
		manageRecord.setMrAopinion(fa.getFallbackReason());	//回退原因
		manageRecord.setMrCsr(fa.getApplyOperatorId());		//退回申请人即审核人
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());			//退回申请时间即审核时间
		manageRecord.setFlag("0");							//默认为无效
		manageRecord.setMrResult("0");						//不通过
		manageRecordDao.save(manageRecord);
		
		
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	
	/**
	 * 退回到施工结算
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String fallBackSettlement(String projId,FallbackApply fa) throws SerialException, SQLException {
		
		Project pro=projectDao.get(projId);
		
		
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
		bi.setProjId(projId);
		bi.setProjNo(pro.getProjNo());
		bi.setProjName(pro.getProjName());
		
		bi.setSettlementer(pro.getSettlementer());		//结算员
		bi.setSettlementerId(pro.getSettlementerId());	//结算员ID
		bi.setSettlementDate(pro.getSettlementDate());  //结算时间
		
		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());
		
		
		
		//处理分包结算信息的审核人
		pro.setSettlementer(null);	//结算员
		pro.setSettlementerId(null);//结算员ID
		pro.setSettlementDate(null);//结算时间
		
		//审核记录置为无效
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.SETTLEMENT_REPORT_START.getValue());	//结算初审
		manageRecordDao.batUpdateHistoryRecord(projId, StepEnum.REPORT_DONE_CONFIRM.getValue());	//终审确认

		
		//操作记录无效
		List<String> stepIds=new ArrayList<String>();
		stepIds.add(StepEnum.SETTLEMENT_REPORT.getValue());			//结算报审
		stepIds.add(StepEnum.SETTLEMENT_REPORT_START.getValue());		//结算初审
		stepIds.add(StepEnum.AUDIT_DONE_DISPATCH.getValue());			//终审派遣
		stepIds.add(StepEnum.REPORT_DONE_CONFIRM.getValue());			//终审确认
		operateRecordDao.updateOrValid(stepIds, projId);
		
		
		pro.setProjStatusId(WorkFlowActionEnum.byActionCode(StepEnum.SETTLEMENT_REPORT.getValue()).getStatusCode());
		pro.setSignBack(Constants.MODULE_CODE_SETTLEMENT);
		projectDao.saveOrUpdate(pro);
		
		
		//通知未处理
		//终审确认
		Notice noticeCost=noticeDao.findByProjIdAndType(projId, NoticeAuditTypeEnum.SETTLEMENT_AUDIT.getValue());
		if(noticeCost!=null){
			noticeCost.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			noticeDao.saveOrUpdate(noticeCost);
		}
		//已有结算汇签todo:
		SettlementJonintlySign jonintlySign = settlementJonintlySignService.findById(projId);
		if(jonintlySign!=null){
			//删除结算汇签单的签字通知
			signNoticeService.deleteByProjIdAndType(projId,SignDataTypeEnum.SETTLEMENT_JONINTLY_SIGN.getValue());
			//删除签字
			signatureService.deleteImgByBIdAndMenuId(jonintlySign.getSjsId(), "110815");//结算汇签
			//删除结算汇签单信息
			settlementJonintlySignDao.delete(jonintlySign);
		}
		SettlementDeclaration sd=settlementDeclarationDao.findByProjId(projId);
		
		//虚拟审核信息
		DocType docType = docTypeService.findByStepId(StepEnum.REPORT_DONE_CONFIRM.getValue());
		ManageRecord manageRecord=new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));		//审核记录主键id
		if(sd!=null){
			manageRecord.setBusinessOrderId(sd.getSdId());
			bi.setSettlementSendCost(sd.getSendDeclarationCost());
			if(sd.getEndDeclarationCost()!=null){
				bi.setSettlementEndCost(sd.getEndDeclarationCost());
			}
			//删除结算的签字
			//删除签字
			signatureService.deleteImgByBIdAndMenuId(sd.getSdId(), null);//结算的签字信息（结算报审、结算初审、结算终审）
			sd.setCompilerSign(null); //编制人签字
			sd.setCuPrincipal(null); //施工负责人签字
			sd.setCuAudit(null);//施工审核人签字
			sd.setOcoDate(null);//报审日期
			sd.setCostControlPrincipal(null);//
			sd.setCostControlReAudit(null);
			sd.setCostControlAudit(null);
			sd.setEndDeclaraDate(null);
			sd.setEndDeclarationCost(null);
			sd.setSendDeclarationCost(null);
			sd.setFirstAuditDate(null);
			sd.setFirstDeclarationCost(null);
			
			settlementDeclarationDao.saveOrUpdate(sd);
		}else{
			manageRecord.setBusinessOrderId(manageRecord.getMrId());
		}
		
		backInfoDao.save(bi);
		manageRecord.setDocTypeId(docType.getId());
		manageRecord.setStepId(StepEnum.SETTLEMENT_REPORT_START.getValue());
		manageRecord.setProjId(projId);
		manageRecord.setProjNo(pro.getProjNo());
		manageRecord.setMrAopinion(fa.getFallbackReason());	//回退原因
		manageRecord.setMrCsr(fa.getApplyOperatorId());		//退回申请人即审核人
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());			//退回申请时间即审核时间
		manageRecord.setFlag("0");							//默认为无效
		manageRecord.setMrResult("0");						//不通过
		manageRecordDao.save(manageRecord);
		
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 结算初审回退到结算报审
	 * 设计出图回退到资料收集
	 * 资料收集退单
	 * 1.修改工程状态
	 * 2.生成backinfo信息
	 * 3.生成审核信息（未通过的审核信息）
	 * @author liaoyq
	 * @createTime 2018-04-16
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String fallBack(Project proj,FallbackApply fa) throws Exception{
		String orgStepId = "";//原stepId
		String stepId="";//回退的stepId
		String modeCode="";
		String statusId = "";//工程状态
		String busOrderId = "";
		Project pro=projectDao.get(proj.getProjId());
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(modeCode));
		bi.setProjId(proj.getProjId());
		bi.setProjNo(pro.getProjNo());
		bi.setProjName(pro.getProjName());

		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());
		//由预算记录回退到预算派工
		if(WorkFlowActionEnum.BUDGET_RESULT_REGISTER.getStatusCode().equals(fa.getOriginalStepId())){
			//操作记录无效
			List<String> stepIds=new ArrayList<String>();
			if(StepEnum.WHITE_MAP.getValue().equals(fa.getFallbackStepId())){
				stepIds.add(StepEnum.WHITE_MAP.getValue());			//白图登记
				stepIds.add(StepEnum.BUDGET_CONFIRM.getValue());			//预算方式确认
				if(StringUtil.isNotBlank(pro.getBudgetMethod())){
					bi.setBudgetMethod(pro.getBudgetMethod());
				}
				pro.setBudgetMethod(null);									//预算方式
				if(StringUtil.isNotBlank(pro.getWhiteMapRegisterRemark())){
					bi.setWhiteMapRegisterRemark(pro.getWhiteMapRegisterRemark());
				}
				pro.setWhiteMapRegisterRemark(null);                        //白图登记备注
			}
			stepIds.add(StepEnum.BUDGET_DISPATCH.getValue());			//预算派工
			operateRecordDao.updateOrValid(stepIds, proj.getProjId());
			orgStepId = StepEnum.BUDGET_RESULT_REGISTER.getValue();
			modeCode =Constants.MODULE_CODE_BUDGET;
			stepId = fa.getFallbackStepId();
			Budget budget=budgetDao.get(proj.getProjId());
			if(budget!=null){
				busOrderId = budget.getBudgetId();
			}
		}
		//由预算初审回退
		if(WorkFlowActionEnum.QUALITIES_DISPATCH_FIRST_AUDIT.getStatusCode().equals(fa.getOriginalStepId())){
			//操作记录无效
			List<String> stepIds=new ArrayList<String>();
			stepIds.add(StepEnum.QUALITIES_DECLARATION.getValue());			//施工预算
			operateRecordDao.updateOrValid(stepIds, proj.getProjId());
			orgStepId = StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue();
			modeCode =Constants.MODULE_CODE_SUBCONTRACT;
			stepId = fa.getFallbackStepId();
			SubBudget sb=subBudgetDao.findByProjId(proj.getProjId());
			if(sb!=null){
				busOrderId = sb.getSbId();
			}
		}
		//由预算审核回退到施工预算
		if(WorkFlowActionEnum.QUALITIES_JUDGEMENT.getStatusCode().equals(fa.getOriginalStepId())){
			//操作记录无效
			List<String> stepIds=new ArrayList<String>();
			stepIds.add(StepEnum.QUALITIES_DECLARATION.getValue());			//施工预算
			stepIds.add(StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());	//施工预算初审
			stepIds.add(StepEnum.QUALITIES_DISPATCH.getValue());	//施工预算派遣
			//标记操作记录
			operateRecordDao.updateOrValid(stepIds, proj.getProjId());
			orgStepId = StepEnum.QUALITIES_JUDGEMENT.getValue();
			modeCode =Constants.MODULE_CODE_SUBCONTRACT;
			stepId = fa.getFallbackStepId();
			SubBudget sb=subBudgetDao.findByProjId(proj.getProjId());
			if(sb!=null){
				busOrderId = sb.getSbId();
			}
			//预算初审审核记录置为无效
			manageRecordDao.batUpdateHistoryRecord(proj.getProjId(), StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());
		}
		//由结算初审回退
		if(WorkFlowActionEnum.SETTLEMENT_REPORT_START.getStatusCode().equals(fa.getOriginalStepId())){
			//操作记录无效
			List<String> stepIds=new ArrayList<String>();
			stepIds.add(StepEnum.SETTLEMENT_REPORT.getValue());			//结算报审
			operateRecordDao.updateOrValid(stepIds, proj.getProjId());
			orgStepId = StepEnum.SETTLEMENT_REPORT_START.getValue();
			modeCode =Constants.MODULE_CODE_SETTLEMENT;
			stepId = fa.getFallbackStepId();
			SettlementDeclaration sd=settlementDeclarationDao.findByProjId(proj.getProjId());
			if(sd!=null){
				busOrderId = sd.getSdId();
			}
		}
		//由结算终审回退到结算报审
		if(WorkFlowActionEnum.REPORT_DONE_CONFIRM.getStatusCode().equals(fa.getOriginalStepId())){
			//操作记录无效
			List<String> stepIds=new ArrayList<String>();
			stepIds.add(StepEnum.SETTLEMENT_REPORT.getValue());			//结算报审
			stepIds.add(StepEnum.SETTLEMENT_REPORT_START.getValue());	//结算初审
			stepIds.add(StepEnum.AUDIT_DONE_DISPATCH.getValue());	//终审派遣
			//标记操作记录
			operateRecordDao.updateOrValid(stepIds, proj.getProjId());
			orgStepId = StepEnum.REPORT_DONE_CONFIRM.getValue();
			modeCode =Constants.MODULE_CODE_SETTLEMENT;
			stepId = fa.getFallbackStepId();
			SettlementDeclaration sd=settlementDeclarationDao.findByProjId(proj.getProjId());
			if(sd!=null){
				busOrderId = sd.getSdId();
			}
			//结算初审审核记录置为无效
			manageRecordDao.batUpdateHistoryRecord(proj.getProjId(), StepEnum.SETTLEMENT_REPORT_START.getValue());
		}
		//由设计出图回退
		if(WorkFlowActionEnum.DESIGN_DRAWING.getStatusCode().equals(fa.getOriginalStepId())){
			//操作记录无效
			List<String> stepIds=new ArrayList<String>();
			stepIds.add(StepEnum.DATA_ACQUISITION.getValue());			//资料收集
			operateRecordDao.updateOrValid(stepIds, proj.getProjId());
			orgStepId = StepEnum.DESIGN_DRAWING.getValue();
			modeCode =Constants.MODULE_CODE_DESIGN;
			stepId = fa.getFallbackStepId();
			DesignInfo designInfo = designInfoDao.queryInfoByProjId(proj.getProjId());
			if(designInfo!=null){
				busOrderId = designInfo.getDiId();
				bi.setAcquisitionDays(designInfo.getAcquisitionDays());
				bi.setOcoDate(designInfo.getOcoDate());
			}
		}
		//由资料收集退单
		if(WorkFlowActionEnum.DATA_ACQUISITION.getStatusCode().equals(fa.getOriginalStepId())){
			//操作记录无效
			List<String> stepIds=new ArrayList<String>();
			stepIds.add(StepEnum.DATA_ACQUISITION.getValue());			//资料收集
			operateRecordDao.updateOrValid(stepIds, proj.getProjId());
			orgStepId = StepEnum.DESIGN_DRAWING.getValue();
			modeCode =Constants.MODULE_CODE_DESIGN;
			stepId = fa.getFallbackStepId();
		}
		if(StepEnum.CONTRACT_END.getValue().equals(fa.getFallbackStepId())){//退单
			statusId = WorkFlowUtil.workFlowStatus("");		//工程状态终止
			pro.setBackReason(BackReasonEnum.OTHERS.getValue());	//退单原因
			pro.setBackDate(projectDao.getDatabaseDate());			//退单日期
			pro.setBackRemarks(fa.getFallbackReason());				//退单备注
			pro.setFinishedDate(projectDao.getDatabaseDate()); 		//结束日期
			pro.setProjStatusId(statusId); 							//设置工程状态
			pro.setSignBack(modeCode);
			//清空代办人
			pro.setToDoer("");
			projectDao.saveOrUpdate(pro);
			//待办事项置为无效
			operateRecordDao.cancelTodo(pro.getProjId());
			
			Contract contract = contractDao.viewContractByprojId(pro.getProjId());
			//退单的如果是借料工程、已签订安装合同的工程调用接口删除工程信息，todo:
			//2019-7-15 调用工程信息修改接口，NC中不删除项目，而是标记为停用
			if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_UPDATE.getValue())&&(MaterialFlagEnum.YES.getValue().equals(pro.getMaterialFlag()) || (contract!=null&&ContractIsPassEnum.ALREADY_PASS.getValue().equals(contract.getIsPass())))){
				String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.PROJ_ACCEPT_UPDATE.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
				ResultMessage resultMessage = new ResultMessage();
				JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_UPDATE.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}else{
			WorkFlowActionEnum workFlowActionEnum = WorkFlowActionEnum.byActionCode(fa.getFallbackStepId());

			if (workFlowActionEnum!=null)statusId=workFlowActionEnum.getStatusCode();

			pro.setProjStatusId(statusId); 							//设置工程状态
			pro.setSignBack(modeCode);
			projectDao.saveOrUpdate(pro);
		}
				
		backInfoDao.save(bi);
		
		//审核记录置为无效
		manageRecordDao.batUpdateHistoryRecord(proj.getProjId(), orgStepId);

		
		//虚拟审核信息
		DocType docType = docTypeService.findByStepId(orgStepId);
		ManageRecord manageRecord=new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(modeCode));		//审核记录主键id
		manageRecord.setBusinessOrderId(busOrderId!=null?busOrderId:manageRecord.getMrId());
		manageRecord.setDocTypeId(docType!=null?docType.getId():"");
		manageRecord.setStepId(orgStepId);//
		manageRecord.setProjId(proj.getProjId());
		manageRecord.setProjNo(pro.getProjNo());
		manageRecord.setMrAopinion(fa.getFallbackReason());	//回退原因
		manageRecord.setMrCsr(fa.getApplyOperatorId());		//退回申请人即审核人
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());			//退回申请时间即审核时间
		manageRecord.setFlag("0");							//默认为无效
		manageRecord.setMrResult("0");						//不通过
		manageRecordDao.save(manageRecord);
		
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 分合同修改审核
	 * @author liaoyq
	 * @createTime 2018-1-26
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String suContractModifyAuditSave(ManageRecord manageRecord,
			String docTypeID, String auditLevel, String stepID) throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.修改通知
		 * 3.更新分合同修改状态
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));//审核记录主键id
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);											//合同修改审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 						//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());						//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}else{
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUB_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),nextGrade.toString(),true);
		}
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			SubContract subContract = subContractDao.get(manageRecord.getBusinessOrderId());
			OperateRecordLog operateRecordLog = operateRecordLogService.findLatelyLog(OperateTypeEnum.SC_MODIFY.getValue(),subContract.getScId());
			ContractLog contractLog = contractLogService.findByModifystate(ModifyStateEnum.BEFOR_MODIFY.getValue(),operateRecordLog.getOrlId());
			//保存未通过标记
			if(MrResultEnum.NOT_PASSED.getValue().equals(manageRecord.getMrResult())){
				BeanUtil.copyNotNullProperties(subContract, contractLog);
				subContract.setModifyState(ModifyStatusEnum.AUDIT_NO_PASS.getValue());
				subContract.setSignBack(Constants.MODULE_CODE_SUBCONTRACT);
				//审核待办todo:
				operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUB_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,false);
			}
			//清空未通过标记
			if(MrResultEnum.PASSED.getValue().equals(manageRecord.getMrResult())){
				//审核待办todo:
				operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUB_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,false);
				subContract.setSignBack("");
				//合同修改状态：已通过
				subContract.setModifyState(ModifyStatusEnum.AUDIT_PASS.getValue());
				//修改合同审核最后一级通过后,调用工程信息同步接口todo:
				if(projService.isToCall(subContract.getProjId(),WebServiceTypeEnum.SUBCONTRACT_UPDATE.getValue())){
					//1.同步工程信息
					String msg = financeInfoService.synProjectInfoClient(manageRecord.getProjId(), FinanceOperateTypeEnum.SUBCONTRACT_UPDATE.getValue(), UpdateTypeEnum.UPDATE.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					ResultMessage resultMessage = new ResultMessage();
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(subContract.getProjId(),WebServiceTypeEnum.SUBCONTRACT_UPDATE.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}	
			}
			subContractDao.update(subContract);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 协议修改审核保存
	 * @author wmy
	 * @createTime 2016-07-11
	 * @param manageRecord
	 * @return
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String supplementalContractModifyAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws Exception {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * 4.逐级审核通过，产生应收流水
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
		}else{
			//下一审核级别的待办todo:
			Integer nextGrade = Integer.valueOf(manageRecord.getMrAuditLevel())+1;
			operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUPPL_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),nextGrade.toString(),true);
			
		}
		
		SupplementalContract supplementalContract=supplementalContractDao.get(manageRecord.getBusinessOrderId());
		if(isNextStatus!=null){
			OperateRecordLog operateRecordLog = operateRecordLogService.findLatelyLog(OperateTypeEnum.SUPPLECON_MODIFY.getValue(),supplementalContract.getScId());
			SupplementalContractModifyhistory supContractLog = suppContractModifyhistoryDao.findByModifystate(ModifyStateEnum.BEFOR_MODIFY.getValue(),operateRecordLog.getOrlId());
			//保存未通过标记
			if(MrResultEnum.NOT_PASSED.getValue().equals(manageRecord.getMrResult())){
				BeanUtil.copyNotNullProperties(supplementalContract, supContractLog);
				supplementalContract.setModifyStatus(ModifyStatusEnum.AUDIT_NO_PASS.getValue());
				supplementalContractDao.saveOrUpdate(supplementalContract);//保存协议
				//审核待办todo:
				operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUPPL_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,false);
			
			}
			//审核通过
			if(MrResultEnum.PASSED.getValue().equals(manageRecord.getMrResult())){
				//审核待办todo:
				operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUPPL_CONTRAT_UPDATE.getValue(),manageRecord.getBusinessOrderId(),new Staff(),null,false);
			
				//在原有流水基础上增加或减少金额
				//已全额收款的，增加一个负的流水
				if(pro != null){
					//根据补充协议编号查询应收流水进行修改
				List<AccrualsRecord> accrualsRecord  = accrualsRecordService.findbyProjIdType( pro.getProjId(), pro.getProjNo(),"1", supplementalContract.getScNo());
				if(accrualsRecord  !=null && accrualsRecord.size() > 0){
					if(accrualsRecord.get(0).getReceiveAmount() !=null && accrualsRecord.get(0).getReceiveAmount().compareTo(supplementalContract.getScAmount()) !=0){  //协议款不等于实收金额
						accrualsRecord.get(0).setArStatus("1"); //更改为未收款标志
						accrualsRecord.get(0).setArCost(supplementalContract.getScAmount());
						accrualsRecordDao.update(accrualsRecord.get(0));  //更新数据
					}
					if(accrualsRecord.get(0).getReceiveAmount() !=null && accrualsRecord.get(0).getReceiveAmount().compareTo(supplementalContract.getScAmount()) == 1 && accrualsRecord.get(0).getReceiveAmount().compareTo(new BigDecimal(0))==1){  //协议款小于实收金额、则生成一个负的流水
					    String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);  //生成唯一arId
					    BigDecimal scAmount = supplementalContract.getScAmount().subtract(accrualsRecord.get(0).getReceiveAmount());  //得到应退款多少，为负的金额
						accrualsRecordService.insertAccrualsRecord(pro,arId, CollectionTypeEnum.SUPPLEMENTAL_MODIFY_CONTRACT.getValue(),Integer.parseInt(ARFlagEnum.REFUND_MENT.getValue()), scAmount,ArContractTypeEnum.SUP_CONTRACT.getValue(),supplementalContract.getScNo());
					}
					if(accrualsRecord.get(0).getReceiveAmount() ==null){   //未进行收款时
						accrualsRecord.get(0).setArCost(supplementalContract.getScAmount());  //更改应收金额为协议款
						accrualsRecord.get(0).setArStatus("1"); //更改为未收款标志
						accrualsRecordDao.update(accrualsRecord.get(0));  //更新数据
					}
				}
					
				}
				supplementalContract.setModifyStatus(ModifyStatusEnum.AUDIT_PASS.getValue());//审核通过
				
				supplementalContractDao.saveOrUpdate(supplementalContract);//保存协议

				if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
					ResultMessage resultMessage = new ResultMessage();
					//补充协议审核通过， 调用财务接口同步工程信息 -工程同步信息中没有补充协议信息,todo:
					String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue(), FinanceContractTypeEnum.SUPPLE_MODIFY_CONTRACT.getValue(), IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}else{
						//将变更材料整合存入到材料清单表中todo：
						/*boolean flags= changeManagementService.updateMaterialList(pro,changeManagement);
						//签订补充协议，需要调用物资的接口，将变更材料信息发往NC
						if(flags && projService.isToCall(pro.getProjId(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
							msg = materialNcService.synProjectInfoClient(pro.getProjId(), changeManagement.getCmId(),MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue()) ;
							jsonbean = JSONObject.fromObject(msg);
							//返回信息-当接口返回失败时，抛出异常
							resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
							if(resultMessage!=null && resultMessage.getRet_type().equals(ResultTypeEnum.FAIL.getValue())){
								//回滚事物
								throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
							}
						}*/
					}
				}
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 补充协议修改无需审核
	 */
	@Override
	@Transactional(readOnly = false,propagation =  Propagation.REQUIRED)
	public void supplementalContractModifySave(SupplementalContract supplementalContract) throws Exception{
		//虚拟审核信息--开始
		ManageRecord manageRecord = new ManageRecord();    //创建一个新对象
		if(StringUtils.isBlank(manageRecord.getMrId())){
			manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));  //生成审核记录主键id
		}
		if(supplementalContract !=null ){
			manageRecord.setProjId(supplementalContract.getProjId());  //设置工程Id
			manageRecord.setProjNo(supplementalContract.getProjNo());  //设置工程编号
			manageRecord.setBusinessOrderId(supplementalContract.getScId());  //设置业务单id
		}
		manageRecord.setStepId(StepOutWorkflowEnum.SUPPLEMENTAL_CONTRACT_MODIFY_AUDIT.getValue());		//补充协议修改审核步骤id
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		manageRecord.setMrResult(MrResultEnum.PASSED.getValue());  //审核状态1表示已经通过，0表示未通过
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate()); //设置操作时间
		//1.产生虚拟审核记录
		manageRecordDao.save(manageRecord);
		//虚拟审核信息--结束
		Project pro=projectDao.get(manageRecord.getProjId());
		//在原有流水基础上增加或减少金额
		//已全额收款的，增加一个负的流水
		if(pro != null){
			//根据补充协议编号查询应收流水进行修改
		List<AccrualsRecord> accrualsRecord  = accrualsRecordService.findbyProjIdType( pro.getProjId(), pro.getProjNo(),"1", supplementalContract.getScNo());
		if(accrualsRecord  !=null && accrualsRecord.size() > 0){
			if(accrualsRecord.get(0).getReceiveAmount() !=null && accrualsRecord.get(0).getReceiveAmount().compareTo(supplementalContract.getScAmount()) !=0){  //协议款不等于实收金额，已收款过
				accrualsRecord.get(0).setArStatus("1"); //更改为未收款标志
				accrualsRecord.get(0).setArCost(supplementalContract.getScAmount());
				accrualsRecordDao.update(accrualsRecord.get(0));  //更新数据
			}
			if(accrualsRecord.get(0).getReceiveAmount() !=null && accrualsRecord.get(0).getReceiveAmount().compareTo(supplementalContract.getScAmount()) == 1 && accrualsRecord.get(0).getReceiveAmount().compareTo(new BigDecimal(0))==1){  //协议款小于实收金额、则生成一个负的流水
			    String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);  //生成唯一arId
			    BigDecimal scAmount = supplementalContract.getScAmount().subtract(accrualsRecord.get(0).getReceiveAmount());  //得到应退款多少，为负的金额
				accrualsRecordService.insertAccrualsRecord(pro,arId, CollectionTypeEnum.SUPPLEMENTAL_MODIFY_CONTRACT.getValue(),Integer.parseInt(ARFlagEnum.REFUND_MENT.getValue()), scAmount,ArContractTypeEnum.SUP_CONTRACT.getValue(),supplementalContract.getScNo());
			}
			if(accrualsRecord.get(0).getReceiveAmount() ==null){   //未进行收款时
				accrualsRecord.get(0).setArCost(supplementalContract.getScAmount());  //更改应收金额为协议款
				accrualsRecord.get(0).setArStatus("1"); //更改为未收款标志
				accrualsRecordDao.update(accrualsRecord.get(0));  //更新数据
			}
		}
			
		}
		supplementalContract.setModifyStatus(ModifyStatusEnum.AUDIT_PASS.getValue());//审核通过
		
		supplementalContractDao.saveOrUpdate(supplementalContract);//保存协议

		if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
			ResultMessage resultMessage = new ResultMessage();
			//补充协议审核通过， 调用财务接口同步工程信息 -工程同步信息中没有补充协议信息,todo:
			String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue(), FinanceContractTypeEnum.SUPPLE_MODIFY_CONTRACT.getValue(), IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
			JSONObject jsonbean = JSONObject.fromObject(msg);
			//返回信息-当接口返回失败时，抛出异常
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
			if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
				//回滚事物
				throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
			}else{
				//将变更材料整合存入到材料清单表中todo：
				/*boolean flags= changeManagementService.updateMaterialList(pro,changeManagement);
				//签订补充协议，需要调用物资的接口，将变更材料信息发往NC
				if(flags && projService.isToCall(pro.getProjId(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue())){
					msg = materialNcService.synProjectInfoClient(pro.getProjId(), changeManagement.getCmId(),MaterialTableTypeEnum.CHANGE_MATERIAL.getValue(), MaterialChangeTypeEnum.DESIGN_CHANGE.getValue(), MaterialOperateTypeEnum.CHANGE.getValue(),WebServiceTypeEnum.MATERIAL_UPDATE.getValue()) ;
					jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && resultMessage.getRet_type().equals(ResultTypeEnum.FAIL.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}*/
			}
		}
	
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public void subContractAudit(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID, String constants) throws ParseException {

		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(constants));		//审核记录主键id

		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(constants));
		}
		manageRecord.setDocTypeId(docTypeID);
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		Project project = projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,project.getCorpId(),project.getProjectType(),project.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			//审核级别取配置表中的
			auditLevel = docType.getGrade();
		}
		//1.产生审核记录
		manageRecordDao.saveManageRecord(manageRecord);
		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.SUB_CONTRACT__AUDIT.getValue());//施工合同审核

		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			notice.setAuditType(NoticeMenuEnum.SUB_CONTRACT_AUDIT1.getType());
			notice.setNoticeTitle("审核通知");
			notice.setNoticeContent(NoticeMenuEnum.SUB_CONTRACT_AUDIT1.getMessage());					//施工合同审核一级
			notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			notice.setProjId(manageRecord.getProjId());
			Project pro = projectDao.get(manageRecord.getProjId());
			notice.setCorpId(pro.getCorpId());
		}*/
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;

			//审核通过--将通知置为无效
			/*if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}

		//noticeDao.saveOrUpdate(notice);
		String todoer="";
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),stepID, isNextStatus);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(constants);
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(Constants.MODULE_CODE_CONTRACT);
				//不通过 
				todoer=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				//判断工程是否已完成开工报告，已完成，则工程状态跳转到施工中
				WorkReport workReport = workReportService.findByProjId(project.getProjId());
				if(workReport!=null && FinishStateEnum.ALREADY_FINISHED.getValue().equals(workReport.getSignState())){
					status = ProjStatusEnum.DURING_CONSTRUCTION.getValue();				
					String nextStepId = (WorkFlowActionEnum.byStatusCode(ProjStatusEnum.DURING_CONSTRUCTION.getValue()))!=null ?WorkFlowActionEnum.byStatusCode(ProjStatusEnum.DURING_CONSTRUCTION.getValue()).getActionCode():"";
					todoer=operateRecordService.updateCurStepActivNextStep(orId, project.getProjId(), project.getProjNo(), StepEnum.SUB_CONTRACT_AUDIT.getValue(), StepEnum.SUB_CONTRACT_AUDIT.getMessage(), "",nextStepId);
				}else{
					todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
					
				}
				project.setSignBack("");
			}
			project.setToDoer(StringUtil.isNotBlank(todoer)?todoer:project.getManagementQae());
			project.setProjStatusId(status);
			//2.更新工程记录
			projectDao.update(project);
			//施工合同推送成功并且施工合同不需要审核的,调用用友的接口，并将数据传递给用友todo:
			if(projService.isToCall(project.getProjId(),WebServiceTypeEnum.SUBCONTRACT_SIGN.getValue())){
				String msg = financeInfoService.synProjectInfoClient(project.getProjId(), FinanceOperateTypeEnum.SUBCONTRACT_SIGN.getValue(), FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
				ResultMessage resultMessage = new ResultMessage();
				JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.SUBCONTRACT_SIGN.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void accepAudit(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID, String constants) {

		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(constants));		//审核记录主键id

		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(constants));
		}
		Project pro=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//方案审核
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.saveManageRecord(manageRecord);
		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.PROJECT_ACCEPT_AUDIT.getValue());//受理申请审核

		if(notice!=null){
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			notice.setGrade(auditLevel.equals(manageRecord.getMrAuditLevel())?auditLevel:String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			notice.setAuditType(NoticeMenuEnum.PROJECT_ACCEPT_AUDIT1.getType());
			notice.setNoticeTitle("审核通知");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			notice.setNoticeContent(NoticeMenuEnum.PROJECT_ACCEPT_AUDIT1.getMessage());					//受理申请审核一级
			notice.setNoticeType("2");
			notice.setProjId(manageRecord.getProjId());
			notice.setCorpId(pro.getCorpId());
		}*/

		Project project = projectDao.get(manageRecord.getProjId());
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;

			//审核通过--将通知置为无效
			/*if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}

		//noticeDao.saveOrUpdate(notice);
		String todoer="";
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),stepID, isNextStatus);
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(constants);
			
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(Constants.MODULE_CODE_PROJECTAPPROVAL);
				//不通过 
				todoer=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(todoer);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
				todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			}
			project.setToDoer(todoer);//待办人
			//2.更新工程记录
			projectDao.update(project);
		}
	}

	/**
	 * 保存报验审核记录
	 */
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED,rollbackFor = {Exception.class})
	public void saveAuditIns(ManageRecord manageRecord,String constants){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		/**
		 * 1.产生审核记录
		 * 2.更新报验单状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(constants));		//审核记录主键id
		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(constants));
		}
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		
		//审核不通过
		if("0".equals(manageRecord.getMrResult())){
			//产生审核记录
			manageRecord.setFlag("0");
			manageRecordDao.saveManageRecordNew(manageRecord);
			//改报验单状态为驳回
			projectChecklistDao.updateData(manageRecord.getBusinessOrderId());
			//删除已审核签字
			signatureService.deleteByBId(manageRecord.getBusinessOrderId());
			//设置审核记录失效
			manageRecordDao.batUpdateHistoryRecordByBoId(manageRecord.getBusinessOrderId(),null);
			
			//所有的审核的签字通知置无效
			signNoticeDao.updateInspectionAudit(manageRecord.getBusinessOrderId());
			
		}else{
			//产生审核记录
			if(Constants.FAIL_CODE.equals(manageRecordDao.saveManageRecordNew(manageRecord))){
				//回滚事物
				throw new ExpressException(Constants.NO_OPERATE,"该信息已被审核！");
			}
			//保存签字
			signatureService.saveOrUpdateSignPictureAuditIns(manageRecord.getLongitude(),manageRecord.getLatitude(),manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.AUDIT_INS_POST);
			//最后一级
			List<Map<String, Object>> list = Constants.getConstantsMapByKey(Constants.AYDIT_INS);
			String stepId = "";
			String level =Constants.AUDIT_INS_LEVEL;//默认2级
            ProjectChecklist proChe = projectChecklistDao.get(manageRecord.getBusinessOrderId());
            Project project = projectDao.get(manageRecord.getProjId());
            if(list != null && list.size()>0){
                for(Map<String, Object> m :list){
                    if(String.valueOf(m.get("RESERVE1")).equals(proChe.getPcDesId())){
                        stepId = String.valueOf(m.get("CNVALUE"));
						String levelGrade= docTypeService.queryAuditLevel(stepId, project.getCorpId(), project.getProjectType(), project.getContributionMode());
						if(StringUtil.isNotBlank(levelGrade)){
							level = levelGrade;
						};
						break;
                    }
                }
            }
			//最后一级设置状态 已完成
            if(manageRecord.getMrAuditLevel().equals(level)){
            	String flag = "1";
				if(Constants.getConsByKeyAndId(Constants.CHECK_STATUS,"100302")!=null){
					Object levelGrade=Constants.getConsByKeyAndId(Constants.CHECK_STATUS,"100302").get("CNVALUE");
					if(levelGrade!=null){
						flag = StringUtil.isNotBlank(String.valueOf(levelGrade))?String.valueOf(levelGrade): "1";
					}
				}

            	proChe.setFlag(Integer.valueOf(flag));

            	//当前审核通知置为无效
				projectChecklistDao.updateFlagByPcId1(proChe);
				//当前审核通知置为无效
            	signNoticeDao.updateInspectionThisAudit(manageRecord.getBusinessOrderId(),manageRecord.getMrAuditLevel());
            }else{
            	//生成下一级审核通知
            	signNoticeService.createNextInspectionAuditNotice(manageRecord.getBusinessOrderId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId());
            }
		}

	}





	/**
	 * 结算初审
	 * @author liaoyq
	 * @createTime 2018-8-24
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void auditSettlementStartSave(ManageRecord manageRecord,
			String docTypeID, String auditLevel, String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));		//审核记录主键id

		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
		}
		Project project=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,project.getCorpId(),project.getProjectType(),project.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel = docType.getGrade();
		}
		manageRecord.setStepId(stepID);		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);

		/*Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.SETTLEMENT_FIRST_AUDIT.getValue());//结算预算初审

		if(notice!=null){
			notice.setGrade(String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			notice.setGrade(String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			notice.setAuditType(NoticeMenuEnum.SETTLEMENT_FIRST_AUDIT1.getType());
			notice.setNoticeTitle("审核通知");
			notice.setNoticeContent(NoticeMenuEnum.SETTLEMENT_FIRST_AUDIT1.getMessage());					//施工结算审核一级
			notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			notice.setProjId(manageRecord.getProjId());
			Project pro = projectDao.get(manageRecord.getProjId());
			notice.setCorpId(pro.getCorpId());
		}*/

		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			/*if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}*/
			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
			//删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//结算的签字信息
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			//审核通过--将通知置为无效
			/*if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
				notice.setGrade(auditLevel);//置为最后一级
			}*/
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.FIRST_SETTLEMENT_POST);
		}else{
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.FIRST_SETTLEMENT_POST);
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		//noticeDao.saveOrUpdate(notice);
		
		SettlementDeclaration sd = settlementDeclarationDao.get(manageRecord.getBusinessOrderId());
		String todoer="";
		if(isNextStatus!=null){
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT);
			//调用工作流定义方法获取状态码
			if(isNextStatus==true){
				/*
				if(null!=sd){
					sd.setAuditState(SubBudgetIsPassEnum.ALREADY_PASS.getValue());
					sd.setIsprint(SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());
					subBudgetDao.update(sd);
				}*/
				//审核通过
				sd.setFirstDeclarationCost(sd.getSendDeclarationCost());
				sd.setFirstMaterialsCost(sd.getFirstMaterialsCost());
				todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "true");
				project.setToDoer(todoer);
			}else{
				//审核不通过
				todoer=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
				project.setToDoer(todoer);
			}
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.SETTLEMENT_REPORT_START.getActionCode(), isNextStatus);
			project.setProjStatusId(status);
			project.setToDoer(todoer);//待办人
			//2.更新工程记录
			projectDao.update(project);
		}
		//保存初审日期和人员
		sd.setFirstAuditerId(loginInfo.getStaffId());
		sd.setFirstAuditer(loginInfo.getStaffName());
		sd.setFirstAuditDate(manageRecordDao.getDatabaseDate());
		try{
			settlementDeclarationDao.saveOrUpdate(sd);
		}catch(Exception e){
			System.out.println(e+"请刷新再次修改");
		}
	}
	
	/**
	 * 联合验收审核
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void auditJointAcceptanceSave(ManageRecord manageRecord,
			String docTypeID, String auditLevel, String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));		//审核记录主键id

		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		Project project=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,project.getCorpId(),project.getProjectType(),project.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel = docType.getGrade();
		}
		manageRecord.setStepId(stepID);		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);

		Notice notice=noticeDao.findByProjIdAndType(manageRecord.getProjId(), NoticeAuditTypeEnum.JOINT_ACCEPTANCE_AUDIT.getValue());//联合验收审核

		if(notice!=null){
			notice.setGrade(String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));//如当前审核为一级，则将通知置为2级
		}else{
			notice=new Notice();
			notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
			notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());							//将通知置为有效;
			notice.setGrade(String.valueOf(Integer.valueOf(manageRecord.getMrAuditLevel())+1));	 //当前审核级别
			notice.setAuditType(NoticeMenuEnum.JOINT_ACCEPTANCE_AUDIT1.getType());
			notice.setNoticeTitle("审核通知");
			notice.setNoticeContent(NoticeMenuEnum.JOINT_ACCEPTANCE_AUDIT1.getMessage());					//施工结算审核一级
			notice.setNoticeType("2");
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			notice.setProjId(manageRecord.getProjId());
			Project pro = projectDao.get(manageRecord.getProjId());
			notice.setCorpId(pro.getCorpId());
		}

		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
			}
			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
			//删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//联合验收审核信息
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			//审核通过--将通知置为无效
			if(notice!=null){
				notice.setNoticeState(NoticeStateEnum.NOT_EFFECTIVE.getValue());//将通知置为无效;
				notice.setGrade(auditLevel);//置为最后一级
			}
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), JointAcceptance.class.getName(), false,Constants.JA_APPLY_SIGN);
		}else{

			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(),
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}

			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), JointAcceptance.class.getName(), false,Constants.JA_APPLY_SIGN);
		}
		noticeDao.saveOrUpdate(notice);
		
		JointAcceptance ja = jointAcceptanceDao.get(manageRecord.getBusinessOrderId());
		
		if(isNextStatus!=null){
			//调用工作流定义方法获取状态码
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.UNION_PRE_INSPECTION_AUDIT.getActionCode(), isNextStatus);
			if(isNextStatus==true){
				if(null!=ja){
					ja.setAuditState(AuditResultEnum.PASSED.getValue());
					ja.setIsPrint(SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());
					jointAcceptanceDao.update(ja);
				}
			}else{
				if(null!=ja){
					ja.setAuditState(AuditResultEnum.NOT_PASSED.getValue());
					ja.setIsPrint(SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());
					jointAcceptanceDao.update(ja);
				}
				project.setSignBack(Constants.MODULE_CODE_COMPLETE);		//审核未通过标记
			}
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
			String toder = operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			project.setToDoer(toder);//待办人
		}
		//2.更新工程记录
		projectDao.update(project);
	}
	/**
	 * 查询最后一次审批的信息
	 */
	@Override
	public ManageRecord findEndMrCsr(ManageRecordQueryReq queryReq) {
		return manageRecordDao.findEndMrCsr(queryReq);
	}


	/**
	 * 产生审核记录(结算汇签审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditSettlementJonintSignAuditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants ) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 */
		manageRecord.setMrId(IDUtil.getUniqueId(constants));		//审核记录主键id

		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(constants));
		}
		manageRecord.setDocTypeId(docTypeID);
		manageRecord.setStepId(stepID);
		manageRecord.setProjNo(projectDao.get(manageRecord.getProjId()).getProjNo());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(StringUtil.isBlank(manageRecord.getMrCsr())){
			manageRecord.setMrCsr(loginInfo.getStaffId()); 			//审核人
		}
		manageRecord.setMrDeptId(loginInfo.getDeptId());		//审核部门
		//1.产生审核记录
		manageRecordDao.save(manageRecord);
		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		SettlementJonintlySign old = settlementJonintlySignService.findById(manageRecord.getProjId());
		//若有一级不通过，将结算汇签的状态改为审核中，重新开始审核
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			old.setSignStatus("2");
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;

			//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			old.setSignStatus("1");
			isNextStatus = true;
		}
		if(isNextStatus!=null){
			//更新结算汇签状态
			settlementJonintlySignDao.update(old);
			//调用工作流定义方法获取状态码
			Project project = projectDao.get(manageRecord.getProjId());
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),stepID, isNextStatus);
			project.setProjStatusId(status);
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(constants);
			operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			//保存未通过标记
			if("0".equals(manageRecord.getMrResult())){
				project.setSignBack(constants);
			}
			//通过清空标记
			if("1".equals(manageRecord.getMrResult())){
				project.setSignBack("");
			}
			//2.更新工程记录
			projectDao.update(project);
		}
	}
	/**
	 * 回退不需要审核，直接通过修改工程状态、生成审核历史-新方法
	 * * @author fulw
	 * @param pro
	 * @param fallbackApply
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String fallBackNew(Project pro, FallbackApply fallbackApply) throws Exception {

		//1、拿到回退前stepId 和回退后的步骤id
		String orgStepId=fallbackApply.getOriginalStepId();//原来的stepId
		String stepId=fallbackApply.getFallbackStepId();	 //回退后stepId

		String nextStepId="";//下一个步骤id
		String modeCode="";	//常量
		String statusId = "";//工程状态
		String projId=pro.getProjId();
		String busOrderId = "";
		//原信息保存 到最上边
		BackInfo bi=new BackInfo();
		bi.setBiId(IDUtil.getUniqueId(modeCode));
		bi.setProjId(projId);
		bi.setProjNo(pro.getProjNo());
		bi.setProjName(pro.getProjName());

		LoginInfo login=SessionUtil.getLoginInfo();
		bi.setCreateDate(backInfoDao.getDatabaseDate());
		bi.setCreater(login.getStaffName());
		bi.setCreaterId(login.getStaffId());

		//2.回退步骤对应的常量、下一个审核id，用于生成审核信息，如回退到设计出图1201,需生成1202的审核历史
		List<Map<String, Object>> list = Constants.getConstantsMapByKey(Constants.FALL_BACK);
		if(list != null && list.size()>0) {
			for (Map<String, Object> m : list) {
				if(String.valueOf(m.get("ID")).equals(stepId)){
					//常量
					modeCode = String.valueOf(m.get("CNVALUE"));
					//下一个审核id
					nextStepId=String.valueOf(m.get("RESERVE1"));
				}
			}
		}



		//3、回退工程，修改工程状态，处理待办人
		if(StepEnum.CONTRACT_END.getValue().equals(fallbackApply.getFallbackStepId())){//退单
			statusId = WorkFlowUtil.workFlowStatus("");		//工程状态终止
			pro.setBackReason(BackReasonEnum.OTHERS.getValue());	    //退单原因
			pro.setBackDate(projectDao.getDatabaseDate());			    //退单日期
			pro.setBackRemarks(fallbackApply.getFallbackReason());	    //退单备注
			pro.setFinishedDate(projectDao.getDatabaseDate()); 		    //结束日期
			pro.setProjStatusId(statusId); 							    //设置工程状态
			pro.setSignBack(modeCode);
			//清空代办人
			pro.setToDoer("已退单");
			projectDao.saveOrUpdate(pro);
			//待办事项置为无效
			operateRecordDao.cancelTodo(pro.getProjId());

			Contract contract = contractDao.viewContractByprojId(pro.getProjId());
			//退单的如果是借料工程、已签订安装合同的工程调用接口删除工程信息，todo:
			//2019-7-15：终止的工程，调用NC系统的修改接口，状态标记为废弃
			if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_UPDATE.getValue())&&(MaterialFlagEnum.YES.getValue().equals(pro.getMaterialFlag()) || (contract!=null&&ContractIsPassEnum.ALREADY_PASS.getValue().equals(contract.getIsPass())))){
				String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.PROJ_ACCEPT_UPDATE.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
				ResultMessage resultMessage = new ResultMessage();
				JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.PROJ_ACCEPT_UPDATE.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			//4、返回业务单id
			busOrderId=this.qurryBusinessOrderIdByStep(projId,stepId);

			statusId = WorkFlowActionEnum.byActionCode(stepId).getStatusCode();
			pro.setProjStatusId(statusId);							//设置工程状态
			pro.setSignBack(modeCode);
			//退回的步骤id对应的操作记录，如图纸签收回退到设计出图，该id为设计出图步骤id
			OperateRecord or=new OperateRecord();
			if(StepEnum.QUALITIES_JUDGEMENT.getValue().equals(stepId) || StepEnum.REPORT_DONE_CONFIRM.getValue().equals(stepId)){
				//终身确认和预算审核从退回到审核一级
				or=operateRecordDao.findByMaxTime(projId,pro.getCorpId(),"","",stepId,"1","");
			}else{
				or=operateRecordDao.findByMaxTime(projId,pro.getCorpId(),"","",stepId,"","");
			}

			if(or!=null){
				pro.setToDoer(or.getOperater());
			}
			projectDao.saveOrUpdate(pro);
			//退回前的步骤id对应的操作记录，如图纸签收回退到设计出图，该id为图纸签收步骤id
			//与下面 获取两个步骤之间的stepId 重复了先去掉
			/*OperateRecord orStepOr=operateRecordDao.findByMaxTime(projId,pro.getCorpId(),"","",orgStepId,"","");
			orStepOr.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());//改为未办
			operateRecordDao.saveOrUpdate(orStepOr);*/
		}

		//5、处理操作记录，复制操作记录，将历史操作记录置为无效
		WorkFlow workFlow = workFlowDao.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),
				WorkFlowTypeEnum.MAIN_PROGRESS.getValue(),"");
		/*String nextStepIdSortNo=workFlowService.queryAssistProgressStatusId(pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), stepId,
				true, WorkFlowTypeEnum.MAIN_PROGRESS.getValue(), null);*/
		List<OperateRecord> owfrList=new ArrayList<OperateRecord>();
		OperateRecord owfr;
		if(workFlow!=null){
			String workFlowCode = workFlow.getWorkFlowCode();

			if(workFlowCode!=null){
				//如从图纸签收回退到设计出图
				int begin=workFlowCode.indexOf(stepId); //设计出图id 1201
				int end=workFlowCode.indexOf(orgStepId);//图纸签收id 1203
				if(begin > 0){ //字符串位置不在第一位
					begin = begin-1;
				}
				String code=workFlowCode.substring(begin,end+orgStepId.length());//两个步骤之间的stepId
				String [] actionCodes= code.split(",");
				for(int i=1;i<actionCodes.length;i++){
					//1、把历史审核记录置为无效
					manageRecordDao.batUpdateHistoryRecord(projId, actionCodes[i]);  //回退前的审核记录并未置为空
					//2、把操作记录置为无效
					//liaoyq 2019-4-10
					//原orList为步骤下有效的已办，如果原步骤已有审核，但是还没有审核完，就查询不到待办的数据，待办有问题，修改为查询已办和待办，将已办置为无效，将待办置为未办
					List<OperateRecord> orList=operateRecordDao.findByMaxTimeList(projId,pro.getCorpId(),"","",actionCodes[i],"",OperateWorkFlowEnum.HAVE_DONE.getValue());
					if(orList!=null && orList.size()>0){
						for(OperateRecord or:orList){
							if(or!=null){
								//待办状态的置为未办
								if(OperateWorkFlowEnum.WAIT_DONE.getValue().equals(or.getModifyStatus())){
									or.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());
								}else{//已办的置为无效并重新增加操作记录
									or.setIsValid("0");
									//3、复制操作记录
									owfr=new OperateRecord();
									owfr.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
									owfr.setProjectType(pro.getProjectType());			//工程类型
									owfr.setContributionMode(pro.getContributionMode());//出资方式
									owfr.setCorpId(pro.getCorpId());
									owfr.setProjId(pro.getProjId());					//工程id
									owfr.setProjNo(pro.getProjNo());					//工程编号
									owfr.setOperateDept1(or.getOperateDept1());
									owfr.setOwfId(or.getOwfId());						//标准操作流id
									owfr.setStepId(or.getStepId());
									owfr.setStepName(or.getStepName());
									owfr.setGrade(or.getGrade());
									owfr.setOpType(or.getOpType());
									if(actionCodes[i].equals(stepId)){
										if("1".equals(or.getGrade()) || "".equals(or.getGrade()) || StringUtils.isBlank(or.getGrade())){ //判断是否是一级审核或者是否为空即不用审核的，是代办事项为代办，否代办事项为已办
											owfr.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());		//待办
										}else{
											owfr.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());		//未办
										}
									}else{
										owfr.setModifyStatus(OperateWorkFlowEnum.NOT_DONE.getValue());		//未办
									}
									if(or.getOperateCsr1().contains(",")){
										owfr.setOperateCsr1(or.getOperateCsr1());			//操作人id
									}else{
										owfr.setOperateCsr1(","+or.getOperateCsr1()+",");			//操作人id
									}
									owfr.setOperater(or.getOperater());					//操作人
									owfr.setIsValid("1");//有效
									owfrList.add(owfr);
								}
								operateRecordDao.saveOrUpdate(or);
							}
						}
					}


					//4、如果包含合同，则删除流水
					if(actionCodes[i].equals(StepEnum.CONSTRUCT_CONTRACT.getValue())){
						Contract contrac=contractDao.viewContractByprojId(projId);
						if(contrac!=null){
							accrualsRecordDao.deleteArList(pro.getProjId(),ARFlagEnum.RECEIVE_ACCOUNT.getValue(),contrac.getConNo());
							//调用接口删除工程信息，todo:
							if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_DELETE.getValue())){
								String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), FinanceOperateTypeEnum.CONTRACT_DELETE.getValue(), UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
								ResultMessage resultMessage = new ResultMessage();
								JSONObject jsonbean = JSONObject.fromObject(msg);
								//返回信息-当接口返回失败时，抛出异常
								resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
								if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_DELETE.getValue())){
									//回滚事物
									throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
								}
							}
						}
					}
				}
				if(owfrList!=null && owfrList.size()>0){
					operateRecordDao.batchInsertObjects(owfrList);
				}
			}
		}


		//虚拟审核信息
		DocType docType = docTypeService.findByStepId(orgStepId);
		ManageRecord manageRecord=new ManageRecord();
		manageRecord.setMrId(IDUtil.getUniqueId(modeCode));				//审核记录主键id
		manageRecord.setBusinessOrderId(busOrderId!=null?busOrderId:fallbackApply.getFaId());
		manageRecord.setDocTypeId(docType!=null?docType.getId():"");
		manageRecord.setStepId(nextStepId == ""? orgStepId:nextStepId);//如回退到设计出图1201,需生成1202的审核历史
		manageRecord.setProjId(projId);
		manageRecord.setProjNo(pro.getProjNo());
		manageRecord.setMrAopinion(fallbackApply.getFallbackReason());	//回退原因
		manageRecord.setMrCsr(fallbackApply.getApplyOperatorId());		//退回申请人即审核人
		manageRecord.setMrTime(manageRecordDao.getDatabaseDate());		//审核时间即退回申请时间
		manageRecord.setFlag("0");										//默认为无效
		manageRecord.setMrResult("0");									//不通过
		manageRecordDao.save(manageRecord);

		surveyResultRegisterSet(orgStepId,stepId,projId);//白图登记回退到现场踏勘

		return Constants.OPERATE_RESULT_SUCCESS;
	}



	/**
	 * @MethodDesc: 白图登记回退到现场踏勘将上次填写的探勘信息置为无效
	 * @Author zhangnx
	 * @Date 2019/5/17 17:18
	 */
	public boolean surveyResultRegisterSet(String orgStepId,String stepId,String projId){
		boolean isWM= orgStepId.equals(StepEnum.WHITE_MAP.getValue());
		boolean isSRR = stepId.equals(StepEnum.SURVEY_RESULT_REGISTER.getValue());

		if (isSRR && isWM){//白图登记回退到现场踏勘将上次填写的探勘信息置为无效
			SurveyInfo surveyInfo = surveyInfoDao.get("projId", projId);
			if (surveyInfo!=null){
				surveyInfo.setSurveyDate(null);//踏勘日期
				surveyInfo.setSurveyDesDate(null);//结果登记日期
				surveyInfo.setTechSugDate(null);//建议填写日期
				surveyInfoDao.saveOrUpdate(surveyInfo);
				return true;
			}

		}

		return false;
	}







	public String qurryBusinessOrderIdByStep(String projId,String stepId){
		String businessOrderId=projId;
		if(stepId.equals(StepEnum.DESIGN_DRAWING.getValue())||stepId.equals(StepEnum.DATA_ACQUISITION.getValue())){
			//回退到设计出图或资料收集 返回设计信息的主键id
			DesignInfo di=designInfoDao.queryInfoByProjId(projId);
			if(di!=null){
				businessOrderId=di.getDiId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.SURVEY_RESULT_REGISTER.getValue())){
			//回退到现场踏勘 返回踏勘信息的主键id
			List<SurveyInfo> list = surveyInfoDao.findByProjId(projId);
			if(list!=null && list.size()>0){
				businessOrderId=list.get(0).getSurveyId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.BUDGET_RESULT_REGISTER.getValue())){
			//回退到预算记录 返回预算的主键id
			Budget budget=budgetDao.queryBudgeByprojId(projId);
			if(budget!=null){
				businessOrderId=budget.getBudgetId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.PROJECT_COST.getValue())){
			//回退到造价确认 返回造价的主键id
			ProjectCost pc=projectCostDao.queryByProjId(projId);
			if(pc!=null){
				businessOrderId=pc.getPcId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.CONSTRUCT_CONTRACT.getValue())){
			//回退到安装合同 返回安装合同的主键id
			Contract contrac=contractDao.viewContractByprojId(projId);
			if(contrac!=null){
				businessOrderId=contrac.getConId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.PROJECT_PLAN.getValue())){
			//回退到下计划   返回下计划的主键id
			ConstructionPlan cp=constructionPlanDao.findByProjId(projId);
			if(cp!=null){
				businessOrderId=cp.getCpId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.QUALITIES_DECLARATION.getValue())){
			//回退到施工预算 返回施工预算的主键id
			SubBudget subBudget=subBudgetDao.findByProjId(projId);
			if(projId!=null){
				businessOrderId=subBudget.getSbId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.SUB_CONTRACT.getValue())){
			//回退到施工合同 返回施工合同
			SubContract sc=subContractDao.findByProjId(projId);
			if(sc!=null){
				businessOrderId=sc.getScId();
			}
			return businessOrderId;
		}else if(stepId.equals(StepEnum.SETTLEMENT_REPORT.getValue()) || stepId.equals(StepEnum.REPORT_DONE_CONFIRM.getValue())){
			//回退到结算报审 返回结算信息的主键id
			SettlementDeclaration sd=settlementDeclarationDao.findByProjId(projId);
			if(sd!=null){
				businessOrderId=sd.getSdId();
			}
			return businessOrderId;
		}
		return null;
	}
	/**
	 * 查询当前审核级别是否已审核
	 */
	@Override
	public ManageRecord queryManRdHistory(String projId, String businssId, String stepId, String MrAuditLevel,
			String flag) {
		// TODO Auto-generated method stub
		return manageRecordDao.queryManRdHistory(projId,businssId,stepId,MrAuditLevel,flag);
	}




	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String isAuditSave(ManageRecord manageRecord, String s, String s1, String stepId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));//审核记录主键id
		manageRecord.setMrCsr(loginInfo.getStaffId());//审核人
		manageRecord.setMrDeptId(loginInfo.getDeptId());//审核部门
		manageRecord.setDocTypeId(stepId);
		manageRecord.setStepId(stepId);
		manageRecordDao.save(manageRecord);//1.产生审核记录

		Project pro = projectDao.get(manageRecord.getProjId());
		if (pro!=null) {
			String level = docTypeService.queryAuditLevel(StepOutWorkflowEnum.INTELLIGENT_SUPPLEMENT_AUDIT.getValue(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode());

			IntelligentSupplement intelligentSupplement = isDao.get(manageRecord.getBusinessOrderId());
			if (intelligentSupplement != null) {

				if ("0".equals(manageRecord.getMrResult())) {//审核不通过

					intelligentSupplement.setIsStatus(AuditStatusEnum.NO_PASS.getCode());
					isDao.saveOrUpdate(intelligentSupplement);
					manageRecordDao.batUpdateHistoryRecordByBoId(intelligentSupplement.getIsId(), stepId);//将审核记录置为无效

				} else if ("1".equals(manageRecord.getMrResult()) && level.equals(manageRecord.getMrAuditLevel())) {//审核通过并且为最后一级审核

					intelligentSupplement.setIsStatus(AuditStatusEnum.PASS.getCode());
					isService.saveOrUpdateintelligentSupplement(intelligentSupplement);
				}
			}
		}
		return null;
	}


	@Override
	public List<Map<String, Object>> queryStepIdByProjId(String projId) {
		List<Map<String, Object>> mapList = manageRecordDao.queryStepIdByProjId(projId);

		if (mapList!=null && mapList.size()>0){
			for (int i = 0; i <mapList.size() ; i++) {
				Object setpId = mapList.get(i).get("stepId");
				String stepName = StepEnum.getNameByCode(setpId+"");
				if (StringUtils.isBlank(stepName)){
					stepName=StepOutWorkflowEnum.getNameByCode(setpId+"");
				}
				mapList.get(i).put("stepName",stepName);
			}
		}
		return mapList;
	}
}
