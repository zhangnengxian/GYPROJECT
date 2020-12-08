package cc.dfsoft.project.biz.base.contract.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.budget.enums.GovAuditCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.GovAuditCostService;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.ContractLogDao;
import cc.dfsoft.project.biz.base.contract.dao.PayTypeDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.ContractLog;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.*;
import cc.dfsoft.project.biz.base.contract.service.ContractLogService;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dao.DispatchInfoDao;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.DispatchInfo;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.project.dao.*;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.*;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.FallbackApplyService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ContractServiceImpl implements ContractService{
	
	@Resource
	ProjectDao projectDao;
	@Resource
	ProjectService projectService;
	@Resource
	ContractDao contractDao;
	@Resource
	DepartmentDao departmentDao;
	@Resource
	ContractLogService contractLogService;
	@Resource
	ProjectService projService;
	/**业务操作记录*/
	@Resource
	OperateRecordDao operateRecordDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	ManageRecordDao managerecorddao;
	
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	@Resource
	AccrualsRecordDao accrualsRecordDao;
	
	/**收付流水DAO处理*/
	@Resource
	CashFlowDao cashFlowDao;

	@Resource
	BudgetDao budgetDao;
	@Resource
	SystemSetDao systemSetDao;
	/** 派遣信息Dao*/
	@Resource
	DispatchInfoDao dispatchInfoDao;
	/** 踏勘信息Dao*/
	@Resource
	SurveyInfoDao surveyInfoDao;
	/** 设计信息Dao*/
	@Resource
	DesignInfoDao designInfoDao;
	/** 设计变更Dao*/
	@Resource
	ChangeManagementDao changeManagementDao;
	/** 延期申请Dao*/
	@Resource
	ApplyDelayDao applyDelayDao;

	/**立项申请单信息Dao*/
	@Resource
	FallbackApplyService fallbackApplyService;
	
	@Resource
	WorkFlowService workFlowService;
	/** 工程类型Dao*/
	@Resource
	ProjectTypeDao projectTypeDao;
	/** 合同日志Dao*/
	@Resource
	ContractLogDao contractLogDao;
	/** 合同日志Dao*/
	@Resource
	OperateRecordLogService operateRecordLogService;
	//FinanceService financeService;
	/** 工程标记*/
	@Resource
	ProjectSignDao projectSignDao;
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;
	@Resource
	FestivalService festivalService;
	
	@Resource
	DocTypeService docTypeService;

	@Resource
	PayTypeDao payTypeDao;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	GovAuditCostService govAuditCostService;
	@Resource
	AbandonedRecordService abandonedRecordService;

	/**
	 * 根据合同编号查询
	 * @author
	 * @createTime 2016-7-4
	 * @param conNo
	 * @return List<Contract>
	 */
	public List<Contract> findByConNo(String conNo) {
		return contractDao.findByConNo(conNo);
	}
	
	
	
	/**
	 * 保存合同
	 * @createTime 2016-06-28
	 * @param contract
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveContract (Contract contract)throws Exception {
		if(StringUtils.isBlank(contract.getConId())){
			contract.setConId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT)); //生成主键ID
		}
		Project pro=projectDao.get(contract.getProjId());					   //根据id查找工程
		contract.setBudgetCost(pro.getConfirmTotalCost());					   //更新合同预算总造价
		contract.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		
		contract.setCorpId(pro.getCorpId());
		//更新工程信息
		if(contract.getFlag().equals("1")){
			//String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getContributionMode(),WorkFlowActionEnum.CONSTRUCT_CONTRACT.getActionCode(), true);
			String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.CONSTRUCT_CONTRACT.getActionCode(), true);
			pro.setProjStatusId(statusId);
			//pro.setProjStatusId(WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.CONSTRUCT_CONTRACT.getActionCode()));  //更新状态
			
			//标记生成特殊工程
			if(StringUtils.isNotBlank(contract.getIsSpecial()) && contract.getIsSpecial().equals("1")){
				ProjectSign projectSign = new ProjectSign();
				projectSign.setProjId(contract.getProjId());
				projectSign.setSignType(ProjectSignTypeEnum.SPECIAL.getValue());//特殊工程
				projectSign.setStatus(IsSignEnum.IS_SIGN.getValue());
				projectSignDao.save(projectSign);
			}
			
			//如果安装合同推送，下一状态不是待安装合同审核，则生成流水和调用接口
			if(!ProjStatusEnum.TO_AUDIT_CONTRACT.getValue().equals(statusId) && !ProjStatusEnum.TERMINATION.getValue().equals(statusId) ){
				//
				contract.setIsPass(ContractIsPassEnum.ALREADY_PASS.getValue());
				String arId=IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
				//生产合同首付款
				accrualsRecordService.insertAccrualsRecord(pro,arId,CollectionTypeEnum.INITIAL_PAYMENT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getFirstPayment(),null,contract.getConNo());
				//获取合同对的付款次数
				PayType payType = payTypeDao.get(contract.getPayType());
				//付款类型为两次付清
				//if(contract.getPayType().equals("2")||contract.getPayType().equals("4")||contract.getPayType().equals("6")){
				if(payType!=null && "2".equals(payType.getPayTypeMode())){
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),null,contract.getConNo());
				//付款类型为三次付清
				}else if(payType!=null && "3".equals(payType.getPayTypeMode())){
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),null,contract.getConNo());
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getThirdPayment(),null,contract.getConNo());
				}
				
				//1.同步工程信息
				if(projectService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
					String operateType = FinanceOperateTypeEnum.CONTRACT_SIGN.getValue();
					if("1".equals(pro.getMaterialFlag())){//存在借料-修改
						operateType = FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue();
					}
					ResultMessage resultMessage = new  ResultMessage();
					String msg = financeInfoService.synProjectInfoClient(pro.getProjId(), operateType, UpdateTypeEnum.INSERT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
					JSONObject jsonbean = JSONObject.fromObject(msg);
					//返回信息-当接口返回失败时，抛出异常
					resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
					if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
						//回滚事物
						throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
					}
				}
			}
		}
	    pro.setContractAmount(contract.getContractAmount());			//更新合同金额
	    pro.setSignDate(contract.getSignDate());						//更新签订日期
	    pro.setOperator(contract.getConAgent());                        //更新经办人
		if(StringUtil.isNotBlank(contract.getCustId())){
			pro.setCustId(contract.getCustId());							//修改工程客户单位
		}
		pro.setCustName(contract.getCustName());						//修改工程客户单位
		if(StringUtil.isNotBlank(contract.getCustEntrustRepresent())){
			pro.setCustContact(contract.getCustEntrustRepresent());
		}
	    //如果工程名称、工程地点改变
	    //更新表派遣信息（dispatchInfo）、踏勘信息（surveyInfo）、设计信息（designInfo）、预算（budget）、设计变更（changeManagement）、延期申请（applyDelay）
	    if(!(pro.getProjName().equals(contract.getProjName())&&pro.getProjAddr().equals(contract.getProjAddr()))){
	    	DispatchInfo dispatchInfo = dispatchInfoDao.findByProjId(contract.getProjId());
	    	if(null!=dispatchInfo){
	    		dispatchInfo.setProjName(contract.getProjName());
	    		dispatchInfoDao.update(dispatchInfo);
	    	}
	    	SurveyInfo surveyInfo = surveyInfoDao.findByProjId(contract.getProjId()).get(0);
	    	if(null!=surveyInfo){
	    		surveyInfo.setProjName(contract.getProjName());
	    		//surveyInfo.setProjAddr(contract.getProjAddr());
	    		surveyInfoDao.update(surveyInfo);
	    	}
	    	DesignInfo designInfo = designInfoDao.queryInfoByProjId(contract.getProjId());
	    	if(null!=designInfo){
	    		designInfo.setProjName(contract.getProjName());
	    		designInfo.setProjAddr(contract.getProjAddr());
	    		designInfoDao.update(designInfo);
	    	}
	    	Budget budget = budgetDao.queryBudgeByprojId(contract.getProjId());
	    	if(null!=budget){
	    		budget.setProjName(contract.getProjName());
	    		budget.setProjAddr(contract.getProjAddr());
	    		budgetDao.update(budget);
	    	}
	    	List<ChangeManagement> changeManagements = changeManagementDao.findByProjId(contract.getProjId(),null,null);
	    	if(null!=changeManagements&&changeManagements.size()>0){
	    		for(ChangeManagement changeManagement:changeManagements){
	    			changeManagement.setProjName(contract.getProjName());
	    			changeManagement.setProjAddr(contract.getProjAddr());
	    		}
	    		changeManagementDao.batchUpdateObjects(changeManagements);
	    	}
	    	List<ApplyDelay> applyDelays = applyDelayDao.findADelayByStepId(null, contract.getProjId());
	    	if(null!=applyDelays&&applyDelays.size()>0){
	    		for(ApplyDelay applyDelay:applyDelays){
	    			applyDelay.setProjName(contract.getProjName());
	    			applyDelay.setProjAddr(contract.getProjAddr());
	    		}
	    		applyDelayDao.batchUpdateObjects(applyDelays);
	    	}
	    	
	    	//生成操作日志
			StringBuffer operateContent= new StringBuffer("");
	    	if(pro.getProjName().equals(contract.getProjName())){
	    		operateContent.append("工程基本信息改变，工程地点由").append(pro.getProjAddr()).append("改变为").append(contract.getProjAddr());
	    	}else if(pro.getProjAddr().equals(contract.getProjAddr())){
	    		operateContent.append("工程基本信息改变，工程名称由").append(pro.getProjName()).append("改变为").append(contract.getProjName());
	    	}else{
	    		operateContent.append("工程基本信息改变，工程名称由").append(pro.getProjName()).append("改变为").append(contract.getProjName()).append(",工程地点由").append(pro.getProjAddr()).append("改变为").append(contract.getProjAddr());
	    	}
	    	String orlId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
			operateRecordLogService.createOperateRecordLog(orlId,OperateTypeEnum.PROJECT_MODIFY.getValue(),pro.getProjId(),operateContent.toString());
			pro.setProjName(contract.getProjName());
	    	pro.setProjAddr(contract.getProjAddr());
	    }
	    String toder="";
	    //形成操作记录
	    if(contract.getFlag().equals("1")){
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
			toder=operateRecordService.createOperateRecord(orId, contract.getProjId(), contract.getProjNo(), StepEnum.CONSTRUCT_CONTRACT.getValue(), StepEnum.CONSTRUCT_CONTRACT.getMessage(), "");	
	    }
	    pro.setToDoer(toder);
	    projectDao.update(pro);
		//设置首付款
//		if (StringUtils.isNotBlank(contract.getPayType())) {
//			BigDecimal amount = BigDecimal.ZERO;
//			if (PayTypeEnum.FULLY_PAID_UP.getValue().equals(contract.getPayType())) {   // 全额支付
//				amount = contract.getContractAmount();
//			}else if(PayTypeEnum.INSTALLMENT.getValue().equals(contract.getPayType())){
//				amount = contract.getContractAmount().multiply(new BigDecimal("0.8"));  // 支付80% 首付款
//			}else{
//				amount = contract.getFirstPayment();									//其他方式
//			}
//			if (amount.compareTo(BigDecimal.ZERO)>0) {
//				contract.setFirstPayment(amount);   // 设置合同中首付款
//				/*accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.INITIAL_PAYMENT.getValue(),
//						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , amount);
//				BigDecimal balancePayment = pro.getContractAmount().subtract(amount); //尾款
//				if(!balancePayment.equals(BigDecimal.ZERO)){
//					accrualsRecordService.insertAccrualsRecord(pro, IDUtil.getUniqueId(Constants.MODULE_CODE_COST), CollectionTypeEnum.BALANCE_PAYMENT.getValue(), 
//							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), balancePayment);
//				}*/
//			}
//		}
	    contract.setConAgent(SessionUtil.getLoginInfo().getStaffName());
	    //设置物资未登记
	    contract.setMaterialIsRegister(MaterialIsPassRegisterEnum.HAVE_NOT_REGISTER.getValue());
	    System.err.println(contract.getConAgent());
		contractDao.saveOrUpdate(contract);
		if(contract.getScaleDetails()!=null &&  contract.getScaleDetails().size()>0){
			pro.setScaleDetails(contract.getScaleDetails());
			projectService.SaveProjectScale(pro,false);
		}
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}



	@Override
	public Contract viewContractByprojId(String id) {
		Contract contract = contractDao.viewContractByprojId(id);
		if (null!=contract && StringUtils.isNotBlank(contract.getProjId())) {
			//设计人
			Project projectcu = projectDao.get(id);
			//contract.setCustName(projectcu.getCustName());  //客户名称
			//contract.setCustPhone(projectcu.getCustPhone());//甲方联系方式
			contract.setProjectTypeDes(projectcu.getProjectTypeDes());//工程类型
			contract.setContributionModeDes(projectcu.getContributionModeDes());//出资方式
			contract.setDeptName(projectcu.getDeptName());//业务部门
			contract.setSurveyer(projectcu.getSurveyer());//勘察员
//			contract.setDuDesigner(projectcu.getDesigner());
//			contract.setDuName(projectcu.getDuName());
			//造价合同处
//			contract.setCostMember(projectcu.getCostMember());  //造价员
//			contract.setCostBudgeter(projectcu.getBudgeter());  //预算员
			
			if (StringUtils.isNotBlank(projectcu.getDuId())) {
			//设计单位负责人
//			contract.setDuDirector(departmentDao.get(projectcu.getDuId()).getPrincipal());
//			contract.setCostContract(departmentDao.get("110206").getPrincipal());
			}
//			BigDecimal downPayment = cashFlowDao.getDownPayment(contract.getProjId(), CollectionTypeEnum.INITIAL_PAYMENT.getValue());
//			contract.setFirstPayment(downPayment);
		}
		return contract;
	}



	@Override
	public Map<String, Object> queryContract(ContractQueryReq contractQueryReq) throws ParseException {
		return contractDao.queryContract(contractQueryReq);
	}
	
    private Map<String, Object> queryContractByTime(ContractQueryReq contractQueryReq) throws ParseException {
    		LoginInfo loginInfo = SessionUtil.getLoginInfo();
    		//SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
    		SystemSet systemSet=systemSetDao.querySystemSetByStepId(contractQueryReq.getStepId(),loginInfo.getCorpId());
    		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
    			contractQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
    		}
			 //最后一个操作记录的工程记录列表（符合状态的当前工程）
			List<Map<String, Object>> ors=operateRecordDao.getOptRecordByTime(contractQueryReq.getProjStatusId());
			Map<String, Object> map=contractDao.queryContract(contractQueryReq);
			//符合当前状态的工程列表
			List<Contract> list=(List<Contract>) map.get("data");
			List<Contract> listNew=new ArrayList<Contract>();
			//时间限制（单位天）
			Integer timel=contractQueryReq.getTimeLimit()!=null?contractQueryReq.getTimeLimit():0;	
			long secondsLimit=-1l;
			if(timel!=null){
				secondsLimit=timel*24*60*60;
			}
			for(Contract con :list){
				for(Map<String, Object> or:ors){
					if(or.get("PROJ_ID").equals(con.getProjId())){
						//业务操作记录中时间
						Date oldTime=(Date) or.get("OPERATE_TIME");									
						//当前时间
						Date nowTime=projectDao.getDatabaseDate();
						//获取两个日期之间的工作日天数
						long workDays = 0;
						try {
							workDays = FestivalUtil.calLeaveDays(oldTime, nowTime, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						long seconds = workDays*24*60*60;
						//用于时限进度展示
						WorkDayDto workDayDto = new WorkDayDto();
						workDayDto.setDaysLimit(String.valueOf(timel));
						workDayDto.setWorkDays(String.valueOf(workDays));
						workDayDto.setHaveDays(String.valueOf(timel-workDays));
						con.setWorkDayDto(workDayDto);
						//如果当前时间-上个步骤的操作时间大于时间限制段则为超时
						if(secondsLimit>0&&seconds>secondsLimit){
							con.setOverdue(true);
							int i = (int)Math.ceil((seconds-secondsLimit)/(24*60*60));
							con.setOverDay(i);		
							continue;
						}
					}
				}
				
				Project pro=projectDao.get(con.getProjId());
				if(pro!=null){
					con.setProjectType(pro.getProjectType());
				}
				listNew.add(con);
			}
			map.put("data",listNew);
			return map;
		}


	@Override
	public Map<String, Object> queryContractforAudit(ContractQueryReq contractQueryReq) throws ParseException {
		  LoginInfo loginInfo = SessionUtil.getLoginInfo();
		  Map<String,Object> result = this.queryContractByTime(contractQueryReq);
		  //按步骤id进行查询 获取单据类型
		  //DocType docType = docTypeDao.findByStepId(StepEnum.CONTRACT_AUDIT.getValue());
		  //String grade = docType==null?"":docType.getGrade();
		  List<Contract> data = (List<Contract>) result.get("data");
		  String grade ="";
		  if(data!=null && data.size()>0){

			/**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				
				
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(data.get(i).getProjId(),IsSignEnum.IS_SIGN.getValue());
				
				if(projectSignList!=null && projectSignList.size()>0){
					data.get(i).setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
				
				Project pro = projectDao.get(data.get(i).getProjId());
				data.get(i).setProjectTypeDes(pro.getProjectTypeDes());
				data.get(i).setContributionModeDes(pro.getContributionModeDes());
				
				
				DocType docType = docTypeService.findByStepId(StepEnum.CONTRACT_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（合同审核2级审核）
				
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
					//根据员工ID查找不能审核那一级别
					List<DataFilerSetUpDto> FilterData = Constants.getDataFilterMapByKey(data.get(i).getProjectType()+"_"+loginInfo.getStaffId()+"_"+n+"_"+contractQueryReq.getMenuId());
					if(FilterData!=null && FilterData.size() > 0){
						levelBtn.put("level"+n, "-1");
					}
				}
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getConId());
				mrq.setStepId(StepEnum.CONTRACT_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				//List<ManageRecord> mrls  = (List<ManageRecord>) managerecorddao.queryManageRecord(mrq).get("data");
				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepEnum.CONTRACT_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					//遍历循环，获取审核是否通过
					for(ManageRecord mr:mrls){
						levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
					}
					//待审核的时候，查询配置是否可进行审核，不可进行审核的，则为未审核“-1”
					//key规则：工程类型_人员ID_审核级别_菜单ID
					System.err.println(mrls.size());
					List<DataFilerSetUpDto> datafiltes = Constants.getDataFilterMapByKey(data.get(i).getProjectType()+"_"+loginInfo.getStaffId()+"_"+(mrls.size() + 1)+"_"+contractQueryReq.getMenuId());
					if(datafiltes!=null && datafiltes.size()>0){
						levelBtn.put("level" + (mrls.size() + 1), "-1");
					}else{
						levelBtn.put("level" + (mrls.size() + 1), "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		
		}
		return result;
	}



	@Override
	public Contract getContract(String id) {
		return contractDao.get(id);
	}



	@Override
	public Map<String, Object> queryContractPrint(ContractQueryReq contractQueryReq) throws ParseException {
		contractQueryReq.setIsPass(ContractIsPassEnum.ALREADY_PASS.getValue());
		//查所有的下计划到待结算的
		Map<String, Object> map=contractDao.queryContractPrint(contractQueryReq);
		
		List<Contract> list=(List<Contract>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(Contract contract:list){
				Project project=projectDao.get(contract.getProjId());
				if(project!=null){
					contract.setProjectType(project.getProjectType());
					if(project.getBudgetTotalCost()!=null){
					 contract.setBudgetTotalCost(project.getBudgetTotalCost());
					 contract.setLegalBudgetTotalCost(MoneyConverter.Num2RMB(project.getBudgetTotalCost().doubleValue()));
					}
				}
				List<CashFlow> cashFlow =cashFlowDao.queryCashFlawByProjID(contract.getProjId());
				if(cashFlow !=null && cashFlow.size() >0 ){   //查询是否有收款，若有收款则不能更改付款比例
					for (CashFlow cashFlow2 : cashFlow) {
						if("0".equals(cashFlow2.getContractType()) && cashFlow2.getIsValid() !="0"){
							contract.setIsPay("1");   //已有收款
						}
					}
				}
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(contract.getProjId(),IsSignEnum.IS_SIGN.getValue());
				if(projectSignList!=null && projectSignList.size()>0){
					contract.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
			}
		}
		
		
		return map;
	}


	/**
	 * 保存打印合同
	 * @author fuliwei
	 * @createTime 2016-07-22
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveContractPrint(Contract contract) {
		
		Project pro=projectDao.get(contract.getProjId());				//根据id查找工程
		//更新合同
		contractDao.saveOrUpdate(contract);
		//更新工程信息
	    pro.setContractAmount(contract.getContractAmount());			//更新合同金额
	    pro.setSignDate(contract.getSignDate());						//更新签订日期
	    pro.setOperator(contract.getConAgent());                        //更新经办人
	    projectDao.update(pro);
		
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
		operateRecordService.createOperateRecord(orId, contract.getProjId(), contract.getProjNo(), StepEnum.CONSTRUCT_CONTRACT.getValue(), StepEnum.CONSTRUCT_CONTRACT.getMessage(), "");
		
		//产生应收流水
		if (StringUtils.isNotBlank(contract.getPayType())) {
			BigDecimal amount = BigDecimal.ZERO;
			if (PayTypeEnum.FULLY_PAID_UP.getValue().equals(contract.getPayType())) {   // 全额支付
				amount = contract.getContractAmount();
			}else if(PayTypeEnum.INSTALLMENT.getValue().equals(contract.getPayType())){
				amount = contract.getContractAmount().multiply(new BigDecimal("0.8"));  // 支付80% 首付款
			}else{
				amount = contract.getFirstPayment();									//其他方式
			}
			if (amount.compareTo(BigDecimal.ZERO)>0) {
				accrualsRecordService.insertAccrualsRecord(pro, CollectionTypeEnum.INITIAL_PAYMENT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , amount);
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	
	/**
	 * 根据工程ID产生合同尾款
	 * @param project
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createRetainage(Project project) {
		if (project!=null && StringUtils.isNotBlank(project.getProjId())) {
			Contract contract = contractDao.viewContractByprojId(project.getProjId());
			AccrualsRecord accrualsRecord = accrualsRecordDao.findByType(project.getProjId(),CollectionTypeEnum.INITIAL_PAYMENT.getValue() );
			BigDecimal amount = contract.getContractAmount();
			if (accrualsRecord !=null) {
				BigDecimal arCost = accrualsRecord.getArCost();
				BigDecimal retainage = amount.subtract(arCost);
				if (retainage.compareTo(BigDecimal.ZERO)>0) {
					accrualsRecordService.insertAccrualsRecord(project, CollectionTypeEnum.BALANCE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , retainage);
				}
			}
		}
	}

	@Override
	public Contract findByProjId(String projId) {
		Contract contract = contractDao.viewContractByprojId(projId);
		//已签订合同
		if(contract!=null){
			//合同收款记录
			List<CashFlow> list = cashFlowDao.queryCashFlowByProjIdType(projId, ARFlagEnum.RECEIVE_ACCOUNT.getValue(),contract.getConNo());
			if(list!=null && list.size()>0){
				contract.setCashFlows(list);
			}
			contractDao.evict(contract);//把持久化对象变成托管状态
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			contract.setConAgent(loginInfo.getStaffName());
			//查询政府预算审定价
			GovAuditCost govAuditCost = govAuditCostService.queryByProjIdAndType(projId,GovAuditCostTypeEnum.BUDGET.getValue());
			if(govAuditCost!=null && govAuditCost.getAuthorizedCost()!=null){
				contract.setGovAuditCost(govAuditCost.getAuthorizedCost()); //政府预算审定价
			}
			return contract;
		//未签订合同
		}else{
			Contract con=new Contract();
			Project project = projectDao.get(projId);
			ProjectType projectType = projectTypeDao.get(project.getProjectType());
			if(null!=projectType){
				con.setContractType(projectType.getContractType());
			}
			con.setGasComp(project.getCorpName());
			con.setProjAddr(project.getProjAddr());
			con.setProjId(project.getProjId());
			con.setProjName(project.getProjName());
			con.setProjNo(project.getProjNo());
			con.setProjScaleDes(project.getProjScaleDes()); 
			con.setCustName(project.getCustName());  //客户名称
			con.setCustEntrustRepresent(project.getCustContact());//甲方联系人
			con.setCustPhone(project.getCustPhone());//甲方联系方式
			con.setCorpId(project.getCorpId());
			con.setBudgetCost(project.getConfirmTotalCost()); 

			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			con.setConAgent(loginInfo.getStaffName());
			if(project!=null && StringUtil.isNotBlank(project.getCustId())&&StringUtil.isBlank(con.getCustId())){
				con.setCustId(project.getCustId());
			}
			//确定造价
			//查询政府预算审定价
			GovAuditCost govAuditCost = govAuditCostService.queryByProjIdAndType(projId,GovAuditCostTypeEnum.BUDGET.getValue());
			if(govAuditCost!=null && govAuditCost.getAuthorizedCost()!=null){
				con.setContractAmount(govAuditCost.getAuthorizedCost()); //政府预算审定价
				con.setGovAuditCost(govAuditCost.getAuthorizedCost()); //政府预算审定价
			}else{
				if(project.getConfirmTotalCost()!=null){//确定造价
					con.setContractAmount(project.getConfirmTotalCost()); //合同金额
				}else{//预算审定价
					con.setBudgetCost(project.getBudgetTotalCost()); 
					con.setContractAmount(project.getBudgetTotalCost()); //合同金额
				}
			}
			con.setCostRemark(StringUtil.isNotBlank(project.getCostRemark())?project.getCostRemark():"");
			Department dept = departmentDao.queryDepartment(project.getCorpId());
			//燃气公司地址
			con.setGasCorpAddr(dept.getLocation()!=null?dept.getLocation():"");
			//公司电话
			con.setGasCompPhone(StringUtil.isNotBlank(dept.getPhone())?dept.getPhone():"");
			return con;
		}
	}


	/**
	 * 合同打印标记
	 * @createTime 2016-12-30
	 * @param   projId
	 * @return String 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String signContractPrint(String projId) {
		Contract contract=contractDao.viewContractByprojId(projId);
		if(contract!=null){
			//标记已打印
			contract.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			contractDao.update(contract);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String modifyContract(Contract contract) throws Exception{

		Contract con = contractDao.get(contract.getConId());
		Project pro=projectDao.get(contract.getProjId());
		if (con==null || pro==null) return null;


		//生成操作日志
		StringBuffer operateContent= new StringBuffer("");
		if(con.getContractAmount().equals(contract.getContractAmount())){
			operateContent.append("合同基本信息修改，合同金额不变");
		}else{
			operateContent.append("合同金额由").append(con.getContractAmount().toString()).append("修改为").append(contract.getContractAmount().toString()).append("");
		}

		String orlId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
		operateRecordLogService.createOperateRecordLog(orlId,OperateTypeEnum.CONTRACT_MODIFY.getValue(),contract.getConId(),operateContent.toString());
		addContractLog(con,contract,orlId);//生成合同日志

		//更新合同
		List<Map<String,Object>> consList = Constants.getConstantsMapByKey(Constants.CONTRACT_AUDIT);
		String status = "";
		if(consList != null && consList.size()>0){
			for(Map<String,Object> m :consList){
				if(pro.getCorpId().equals(String.valueOf(m.get("ID")))){
					status = String.valueOf(m.get("CNVALUE"));
					break;
				}
			}
		}
		 if (isConfig(pro)){//有配置时：合同修改无需审核（配置规则：公司ID_类型_出资方式_菜单ID ||公司ID_类型_菜单ID ||公司ID_菜单ID）
			contract.setModifyStatus(ModifyStatusEnum.AUDIT_PASS.getValue());
			contract.setIsPrint(con.getIsPrint());
			contract.setIsPass(con.getIsPass());
			 BeanUtil.copyNotNullProperties(con, contract);
			modifyPaymentFlow(con,pro);//修改合同、流水、工程表信息
		}else {
			contract.setModifyStatus(ModifyStatusEnum.TO_AUDIT.getValue());
			contractDao.update(contract);
			//合同修改审核待办
			Staff staff = new Staff();
			operateRecordService.cerateCurOperateRecord(pro, StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.CONTRAT_UPDATE.getValue(),contract.getConId(),staff,"1",true);
			
		}

		
		 if(contract.getScaleDetails()!=null &&  contract.getScaleDetails().size()>0){
			pro.setScaleDetails(contract.getScaleDetails());
			projectService.SaveProjectScale(pro,false);
		 }
		return Constants.OPERATE_RESULT_SUCCESS;
	}





	public boolean isConfig(Project pro){
		String menuId="110408";//合同修改菜单ID
		DataFilerSetUpDto config = Constants.isConfig(pro.getCorpId() + "_" + pro.getProjectType() + "_" + pro.getContributionMode() + "_" + menuId);
		if (config==null){
			config = Constants.isConfig(pro.getCorpId() + "_" + pro.getProjectType()+ "_" + menuId);
		}
		if (config==null){
			config = Constants.isConfig(pro.getCorpId()+ "_" + menuId);
		}
		return config!= null? true : false;
	}




	public void addContractLog(Contract con,Contract contract,String orlId) {
		//生成合同日志
		List<ContractLog> logList = new ArrayList<ContractLog>();
		ContractLog contractLogBefor = new ContractLog();
		BeanUtil.copyNotNullProperties(contractLogBefor, con);
		contractLogBefor.setConLogId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		contractLogBefor.setModifystate(ModifyStateEnum.BEFOR_MODIFY.getValue());
		contractLogBefor.setOrlId(orlId);
		logList.add(contractLogBefor);
		ContractLog contractLogAfter = new ContractLog();
		BeanUtil.copyNotNullProperties(contractLogAfter, contract);
		contractLogAfter.setConLogId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		contractLogAfter.setModifystate(ModifyStateEnum.AFTER_MODIFY.getValue());
		contractLogAfter.setOrlId(orlId);
		logList.add(contractLogAfter);
		contractLogDao.batchInsertObjects(logList);
	}

	@Override
	public Map<String, Object> queryContractforModifyAudit(ContractQueryReq contractQueryReq) throws ParseException {
		  SystemSet systemSet=systemSetDao.get("stepId", contractQueryReq.getStepId());
		  if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			  contractQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		  }
		  Map<String,Object> result = this.queryContractByTime(contractQueryReq);
		  //按步骤id进行查询 获取单据类型
		  //DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue());
		  //String grade = docType==null?"":docType.getGrade();
		  String grade ="";
		  List<Contract> data = (List<Contract>) result.get("data");
		  if(data!=null && data.size()>0){

			/**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				Project pro = projectDao.get(data.get(i).getProjId());
				data.get(i).setProjectTypeDes(pro.getProjectTypeDes());
				data.get(i).setContributionModeDes(pro.getContributionModeDes());
				
				DocType docType = docTypeService.findByStepId(StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}
				data.get(i).setLevel(grade);// 设置审核总级数（合同审核2级审核）
				
				data.get(i).setLevel(grade);	//设置审核总级数（合同审核2级审核）
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepOutWorkflowEnum.CONTRACT_MODIFY_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					//遍历循环，获取审核是否通过
					for(ManageRecord mr:mrls){
						levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
					}
					if(mrls.size()<Integer.parseInt(grade)){
						levelBtn.put("level"+(mrls.size()+1), "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		
		}
		return result;
	}

	@Override
	public String findPrintDataByProjId(String projId) {
		String result ="";
		//根据工程ID查询合同信息
		Contract contract = contractDao.viewContractByprojId(projId);
		//安装合同报表
		String arrayStr = CompletionDataPrintEnum.CONTRACT.getCptUrl();
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && contract!=null){
			for(Object obj : jsonArray){
				 JSONObject jsonObject=JSONObject.fromObject(obj);
				 CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				if(dto.getType()!=null && dto.getType().equals(contract.getContractType())){
					
					result = "{reportlet:'"+dto.getReportlet()+"',projName:'"+contract.getProjName()+"',conId:'"+contract.getConId()+"',projId:'"+contract.getProjId();
					if(contract.getContractAmount()!=null){
						result += "',legalAmount:'"+MoneyConverter.Num2RMB(contract.getContractAmount().doubleValue());
					}
					if(contract.getFirstPayment()!=null){
						result += "',legalFirstPayment:'"+MoneyConverter.Num2RMB(contract.getFirstPayment().doubleValue());
					}
					if(contract.getSecondPayment()!=null){
						result += "',legalSecondPaymentAmount:'"+MoneyConverter.Num2RMB(contract.getSecondPayment().doubleValue());
					}
					if(contract.getThirdPayment()!=null){
						result += "',legalThirdPaymentAmount:'"+MoneyConverter.Num2RMB(contract.getThirdPayment().doubleValue());
					}
					result += "'}";
					return result;
				}
			}
		}
		return null;
	}


	@Override
	public Map<String, Object> queryPassContract(ContractQueryReq contractQueryReq) throws ParseException {
		//return contractDao.queryPassContract(contractQueryReq);
		Map<String, Object> map = contractDao.queryPassContract(contractQueryReq);
		List<Contract> lists = (List<Contract>) map.get("data");
        
		
		if(lists!=null && lists.size()>0){
			for(Contract contract:lists){
				
				
					Project project=projectDao.get(contract.getProjId());
					if(project!=null){
						contract.setProjectType(project.getProjectType());
					}
				
				
				//查询所有的有效的
				List<ProjectSign> projectSignList=projectSignDao.findByProjIdAndStatus(contract.getProjId(),IsSignEnum.IS_SIGN.getValue());
				if(projectSignList!=null && projectSignList.size()>0){
					contract.setIsSpecialSign(IsSignEnum.IS_SIGN.getValue());//是特殊工程
				}
			}
		}
        return map;
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveMaterialContract(Contract c) {
		String conId=c.getConId();
		Contract contract=contractDao.get(conId);
		contract.setMaterialIsRegister(c.getMaterialIsRegister());
		contract.setMaterialRemark(c.getMaterialRemark());
		contractDao.update(contract);
		return Constants.OPERATE_RESULT_SUCCESS;
	}


	public void   modifyPaymentFlow(Contract contract,Project project) throws Exception{
		if(StringUtil.isNotBlank(contract.getCustId())){
			project.setCustId(contract.getCustId());							//修改工程客户单位
		}
		project.setCustName(contract.getCustName());						//修改工程客户单位
		if(StringUtil.isNotBlank(contract.getCustEntrustRepresent())){
			project.setCustContact(contract.getCustEntrustRepresent());
		}

		project.setContractAmount(contract.getContractAmount());//合同金额

		OperateRecordLog operateRecordLog = operateRecordLogService.findLatelyLog(OperateTypeEnum.CONTRACT_MODIFY.getValue(),contract.getConId());
		ContractLog contractLog = contractLogService.findByModifystate(ModifyStateEnum.BEFOR_MODIFY.getValue(),operateRecordLog.getOrlId());

		String arId=IDUtil.getUniqueId(Constants.MODULE_CODE_COST);

		//判断是否产生应收流水；
		PayType payTypeLog = payTypeDao.get(contractLog.getPayType());
		PayType payType = payTypeDao.get(contract.getPayType());

		if((payTypeLog.getPayTypeMode().compareTo(payType.getPayTypeMode()) !=0)||isCompareTo(contract,contractLog)) {

			//查询改合同需要更新的应收流水
			//先判断工程合同是否已收款
			//如果合同的付款方式不变，修改流水金额，如果合同付款方式改变（肯定未收费）,删除已有流水，重新生成流水
			List<AccrualsRecord> accrualsRecords = accrualsRecordService.findForUpdate(contract.getProjId(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(), ArContractTypeEnum.CONSTRUCTION.getValue());
			//重新生成修改后的应收流水
			//判断付款次数
			if (payTypeLog != null && payType != null && payTypeLog.getPayTypeMode().equals(payType.getPayTypeMode())) {
				//付款方式不变的，修改流水应收金额
				int i = 1;
				//收付款超收金额
				BigDecimal firstMoney = new BigDecimal(0);
				BigDecimal secondMoney = new BigDecimal(0);
				BigDecimal receiveAmount = new BigDecimal(0);
				for (AccrualsRecord ar : accrualsRecords) {
					if (CollectionTypeEnum.INITIAL_PAYMENT.getValue().contains(ar.getArType())) {
						ar.setArCost(contract.getFirstPayment());
						firstMoney = firstMoney.add(ar.getReceiveAmount() != null ? ar.getReceiveAmount().subtract(contract.getFirstPayment()) : new BigDecimal(0));
						receiveAmount = receiveAmount.add(ar.getReceiveAmount() != null ? ar.getReceiveAmount() : new BigDecimal(0));
					} else if (CollectionTypeEnum.STAGE_PAYMENT.getValue().contains(ar.getArType())) {
						if (i == 2) {
							//如果已收首付款大于第二阶段款并且阶段款还未收
							if (firstMoney.compareTo(new BigDecimal(0)) > 0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())) {
								ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
								ar.setReceiveAmount(firstMoney);
							}
							secondMoney = secondMoney.add(ar.getReceiveAmount() != null ? ar.getReceiveAmount().subtract(ar.getReceiveAmount()) : new BigDecimal(0));
							ar.setArCost(contract.getSecondPayment());
						} else {
							//如果已收首付款大于第二阶段款并且阶段款还未收
							if (secondMoney.compareTo(new BigDecimal(0)) > 0 && ARStatusEnum.TO_BE_CHARGE.getValue().equals(ar.getArStatus())) {
								ar.setArStatus(ARStatusEnum.ALREADY_CHARGE.getValue());
								ar.setReceiveAmount(firstMoney);
							}
							ar.setArCost(contract.getThirdPayment());
						}
						receiveAmount = receiveAmount.add(ar.getReceiveAmount() != null ? ar.getReceiveAmount() : new BigDecimal(0));
					}
					i++;
					accrualsRecordDao.saveOrUpdate(ar);
				}
				//需要退款的生成退款流水
				if (receiveAmount.compareTo(contract.getContractAmount()) > 0) {
					accrualsRecordService.insertAccrualsRecord(project, arId, CollectionTypeEnum.MODIFY_CONTRACT.getValue(),
							Integer.valueOf(ARFlagEnum.REFUND_MENT.getValue()), contract.getContractAmount().subtract(receiveAmount), null, contract.getConNo());
				}
			} else {
				//删除应收流水
				accrualsRecordDao.batchDeleteObjects(accrualsRecords);

				//生产合同首付款
				accrualsRecordService.insertAccrualsRecord(project, arId, CollectionTypeEnum.INITIAL_PAYMENT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), contract.getFirstPayment(), null, contract.getConNo());
				//付款类型为两次付清
				if (payType != null && "2".equals(payType.getPayTypeMode())) {
					accrualsRecordService.insertAccrualsRecord(project, IDUtil.getUniqueId(Constants.MODULE_CODE_COST), CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), contract.getSecondPayment(), null, contract.getConNo());
					//付款类型为三次付清
				} else if (payType != null && "3".equals(payType.getPayTypeMode())) {
					accrualsRecordService.insertAccrualsRecord(project, IDUtil.getUniqueId(Constants.MODULE_CODE_COST), CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), contract.getSecondPayment(), null, contract.getConNo());
					accrualsRecordService.insertAccrualsRecord(project, IDUtil.getUniqueId(Constants.MODULE_CODE_COST), CollectionTypeEnum.STAGE_PAYMENT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()), contract.getThirdPayment(), null, contract.getConNo());
				}
			}
		}

		contractDao.saveOrUpdate(contract);
		projectDao.saveOrUpdate(project);

		ResultMessage resultMessage;

		if(projService.isToCall(project.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
			//调用工程信息同步接口todo:
			String msg = financeInfoService.synProjectInfoClient(contract.getProjId(), FinanceOperateTypeEnum.CONTRACT_UPDATE.getValue(),  UpdateTypeEnum.UPDATE.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
			JSONObject jsonbean = JSONObject.fromObject(msg);
			//返回信息-当接口返回失败时，抛出异常
			resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
			if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(project.getProjId(),WebServiceTypeEnum.CONTRACT_UPDATE.getValue())){
				//回滚事物
				throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
			}
		}
		//projectDao.evict(project);  //托管对象
	}






	public boolean isCompareTo(Contract contract,ContractLog contractLog){
		String cpr1=StringUtil.isBlank(contract.getPaymentRatio1())?"0":contract.getPaymentRatio1();
		String clpr1=StringUtil.isBlank(contractLog.getPaymentRatio1())?"0":contractLog.getPaymentRatio1();
		String cpr2=StringUtil.isBlank(contract.getPaymentRatio2())?"0":contract.getPaymentRatio2();
		String clpr2=StringUtil.isBlank(contractLog.getPaymentRatio2())?"0":contractLog.getPaymentRatio2();
		String cpr3=StringUtil.isBlank(contract.getPaymentRatio3())?"0":contract.getPaymentRatio3();
		String clpr3=StringUtil.isBlank(contractLog.getPaymentRatio3())?"0":contractLog.getPaymentRatio3();
		BigDecimal contractAmount1 = contract.getContractAmount();
		BigDecimal contractAmount = contractLog.getContractAmount();
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
	 * 确认收费
	 * @author fuliwei
	 * @date 2019/5/22
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveChargeConfirm(Contract contract) throws Exception {
		Project pro=projectDao.get(contract.getProjId());//根据Id查询工程
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.CONFIRM_CHARGE.getActionCode(), true);
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);
		String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.CONFIRM_CHARGE.getValue(), StepEnum.DESIGN_DISPATCH_WORKERS.getMessage(), "");
		pro.setToDoer(toder);
		pro.setProjStatusId(statusId);
		//更新工程
		projectDao.update(pro);
		return  Constants.OPERATE_RESULT_SUCCESS;
	}





	/**
	* @Description: 回退合同处理
	* @author zhangnx
	* @date 2019/8/23 12:42
	*/
	@Override
	public boolean rollBackContainsContract(String projId,String rollBackReason) {
		Contract contrac = contractDao.viewContractByprojId(projId);
		if (contrac == null) return true;


		//删除备份流水
		Map<String ,Object> criteriaMap=new HashMap<>();
		String ar_t_projId = Annotations.getFieldGetMethodColumnAnNameVal(AccrualsRecord.class, "projId");
		criteriaMap.put(ar_t_projId,projId);
		String ar_t_conNo = Annotations.getFieldGetMethodColumnAnNameVal(AccrualsRecord.class, "conNo");
		criteriaMap.put(ar_t_conNo,contrac.getConNo());
		String t_arFlag = Annotations.getFieldGetMethodColumnAnNameVal(AccrualsRecord.class, "arFlag");
		criteriaMap.put(t_arFlag,ARFlagEnum.RECEIVE_ACCOUNT.getValue());
		String ar_tableName = Annotations.getClassTableAnNameVal(AccrualsRecord.class);
		abandonedRecordService.delBackupsThisTableRecord(ar_tableName,criteriaMap, contrac.getConId(), rollBackReason, StepEnum.REGISTRATION_OF_RECEIPTS.getValue());



		//备份原信息记录
		Map<String ,Object> whereMap=new HashMap<>();
		String c_t_projId = Annotations.getFieldGetMethodColumnAnNameVal(Contract.class, "projId");
		whereMap.put(c_t_projId,projId);
		String c_tableName = Annotations.getClassTableAnNameVal(Contract.class);
		String origData = abandonedRecordService.getThisTableOrigData(c_tableName,whereMap);
		abandonedRecordService.saveAbandonedRecord(contrac.getConId(),projId,StepEnum.CONSTRUCT_CONTRACT.getValue(),rollBackReason,origData);



		//更新合同
		contrac.setSignDate(null);
		contrac.setConAgent(null);
		contrac.setModifyStatus(null);
		contrac.setIsPass(null);
		contrac.setIsPrint("0");
		contrac.setRemark(contrac.getRemark()+" *************回退备注：原工程已在 "+contrac.getSignDate()+" 签订安装合同。");
		contractDao.saveOrUpdate(contrac); //更新合同信息


        //调用NC接口
		if (projService.isToCall(projId, WebServiceTypeEnum.CONTRACT_DELETE.getValue())) {
			String delContract = FinanceOperateTypeEnum.CONTRACT_DELETE.getValue();
			String insert = UpdateTypeEnum.INSERT.getValue();
			String isIntellPay = IsIntelligentConPayEnum.OTHER_CON_PAY.getValue();
			fallbackApplyService.callNC(projId, delContract, insert, isIntellPay);
		}
		return true;

	}
}


