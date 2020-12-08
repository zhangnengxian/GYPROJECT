package cc.dfsoft.project.biz.base.subpackage.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkDayDto;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ARStatusEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.common.dao.ReportVersionDao;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.complete.dao.GasApplyDao;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionWorkDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.enums.FinishStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.dao.PayTypeDao;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.*;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractLogDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractLog;
import cc.dfsoft.project.biz.base.subpackage.enums.CostTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.SubContractMethodEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceContractTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
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
import java.text.SimpleDateFormat;
import java.util.*;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SubContractServiceImpl implements SubContractService{

	/**分包协议Dao*/
	@Resource
	SubContractDao subContractDao;
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;

	/**管理记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;

	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;

	/**应收应付流水 service*/
	@Resource
	AccrualsRecordService accrualsRecordService;

	/**业务操作记录*/
	@Resource
	OperateRecordDao operateRecordDao;

	@Resource
	AccrualsRecordDao accrualsRecordDao;

	/**单据类型DAO处理*/
	@Resource
	DocTypeDao docTypeDao;

	@Resource
	ManageRecordDao managerecorddao;
	@Resource
	SystemSetDao systemSetDao;

	/**通气申请*/
	@Resource
	GasApplyDao gasApplyDao;

	/**支付方式*/
	@Resource
	PayTypeDao payTypeDao;

	/**
	 * 工作流服务类
	 */
	@Resource
	WorkFlowService workFlowService;
	@Resource
	FestivalService festivalService;
	/**
	 * 分包预算
	 */
	@Resource
	SubBudgetService subBudgetService;

	@Resource
	ReportVersionDao reportVersionDao;

	@Resource
	OperateRecordLogService operateRecordLogService;
	@Resource
	SubContractLogDao subContractLogDao;
	@Resource
	ProjectService projService;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	WorkReportService workReportService;
	@Resource
	ConstructionWorkDao constructionWorkDao;

	@Resource
	DocTypeService docTypeService;

	@Resource
	AbandonedRecordService abandonedRecordService;

	/**
	 * 保存分包合同
	 * @author fuliwei
	 * @createTime 2016-7-5
	 * @param  subContract 分包合同
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED, rollbackFor = {Exception.class})
	public String saveSubContract(SubContract subContract) throws Exception{
		if(StringUtils.isBlank(subContract.getScId())&&this.findByScNo(subContract.getScNo()).size()>0){
			return "exist";
		}
		if(StringUtils.isBlank(subContract.getScId())){
			subContract.setScId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}
		subContract.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		Project pro=projectDao.get(subContract.getProjId());
		subContract.setCorpId(pro.getCorpId());
		//施工合同工期
		if(StringUtil.isBlank(subContract.getScPlannedTotalDays())){
			ConstructionPlan cp = constructionPlanDao.get("projId", subContract.getProjId());
			subContract.setScPlannedTotalDays((cp!=null && StringUtil.isNotBlank(cp.getProjTimeLimit()))?cp.getProjTimeLimit():"");
		}
		subContractDao.saveOrUpdate(subContract);
		//推送
		if(subContract.getFlag().equals("1")){
			//更新工程信息

			String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.SUB_CONTRACT.getActionCode(), true);
			//判断工程是否已完成开工报告，已完成，并且不需要施工合同审核的，则工程状态跳转到施工中
			WorkReport workReport = workReportService.findByProjId(pro.getProjId());
			String nextStepId = "";
			String todoer="";
			if(workReport!=null && FinishStateEnum.ALREADY_FINISHED.getValue().equals(workReport.getSignState()) && !ProjStatusEnum.TO_AUDIT_SUBCONTRACT.getValue().equals(statusId)){
				statusId = ProjStatusEnum.DURING_CONSTRUCTION.getValue();				
				nextStepId = (WorkFlowActionEnum.byStatusCode(ProjStatusEnum.DURING_CONSTRUCTION.getValue()))!=null ?WorkFlowActionEnum.byStatusCode(ProjStatusEnum.DURING_CONSTRUCTION.getValue()).getActionCode():"";
				//形成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);//生成唯一ID
				todoer=operateRecordService.updateCurStepActivNextStep(orId, subContract.getProjId(), subContract.getProjNo(), StepEnum.SUB_CONTRACT.getValue(), StepEnum.SUB_CONTRACT.getMessage(), "",nextStepId);
			}else{
				//形成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);//生成唯一ID
				todoer=operateRecordService.createOperateRecord(orId, subContract.getProjId(), subContract.getProjNo(), StepEnum.SUB_CONTRACT.getValue(), StepEnum.SUB_CONTRACT.getMessage(), "");
			}
			pro.setToDoer(StringUtil.isNotBlank(todoer)?todoer:pro.getManagementQae());//待办人,没有，则为施工员
			pro.setProjStatusId(statusId);
			projectDao.update(pro);
//				String payType = subContract.getPayType();
//				BigDecimal pt = new BigDecimal(payType.substring(0, payType.length()-1)).divide(new BigDecimal("100"));
//				//协议价款*付款方式 保留两位小数
//				BigDecimal scAmount = subContract.getScAmount().multiply(pt).setScale(2,BigDecimal.ROUND_HALF_UP);
//				//形成应付流水
//				String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
//				accrualsRecordService.insertAccrualsRecord(pro, arId,CollectionTypeEnum.SUBCONTRACT_PAYMENT.getValue(),Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()) , scAmount);
//				return arId;
			//施工合同推送成功并且施工合同不需要审核的,调用用友的接口，并将数据传递给用友todo:
			if(projService.isToCall(pro.getProjId(),WebServiceTypeEnum.SUBCONTRACT_SIGN.getValue()) && !ProjStatusEnum.TO_AUDIT_SUBCONTRACT.getValue().equals(statusId)){
				String msg = financeInfoService.synProjectInfoClient(subContract.getProjId(), FinanceOperateTypeEnum.SUBCONTRACT_SIGN.getValue(), FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.OTHER_CON_PAY.getValue());
				ResultMessage resultMessage = new ResultMessage();
				JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.SUBCONTRACT_SIGN.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 根据合同编号查询
	 * @author
	 * @createTime 2016-7-6
	 * @param scNo
	 * @return List<SubContract>
	 */
	public List<SubContract> findByScNo(String scNo) {
		return subContractDao.findByScNo(scNo);
	}
	/**
	 * 分包合同列表条件查询
	 */
	@Override
	public Map<String, Object> querySubContract(SubContractQueryReq subContractQueryReq)throws ParseException {
		List<String> statusList=new ArrayList();
		List<ProjStatusEnum> enums=ProjStatusEnum.getThanValue(ProjStatusEnum.TO_CONSTRUCTION.getValue(), ProjStatusEnum.ALREADY_COMPLETED.getValue());
		for(ProjStatusEnum projStatusEnum:enums){
			statusList.add(projStatusEnum.getValue());
		}
		subContractQueryReq.setProjStuList(statusList);

		Map<String, Object> map = subContractDao.querySubContract(subContractQueryReq);

		List<SubContract> subContracts = (List<SubContract>) map.get("data");
		if(subContracts!=null&&subContracts.size()>0){
			for(SubContract sub :subContracts){
				Project project = projectDao.get(sub.getProjId());
				sub.setProjLtypeId(project.getProjLtypeId());
				sub.setProjectType(project.getProjectType());
				if(sub.getScAmount()!=null){
					sub.setLegalAmount(MoneyConverter.Num2RMB(sub.getScAmount().doubleValue()));
				}
			}
		}
		return map;
	}

	@Override
	public SubContract findSubContractByprojId(String projId) throws ParseException {
		Project project = projectDao.get(projId);
		List<PayType> payTypes = payTypeDao.findByProjType(project);
		SubContract subContract = subContractDao.findSubContractByprojId(projId);
		SubBudget subBudget = subBudgetService.viewSubBudget(projId);
		if(null!=subContract){
			if(null != payTypes){
				subContract.setPayTypes(payTypes);
			}
			if(null != subBudget){
				subContract.setQuAmount(subBudget.getTotalAmount());                   //预算价
			}
		}
		return subContract;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveSubContractPrint(SubContract subContract) {
		Project pro=projectDao.get(subContract.getProjId());
		List<AccrualsRecord> ars = accrualsRecordService.findbyProjIdType(pro.getProjId(), pro.getProjNo(), ARFlagEnum.ACCOUNTS_PAY.getValue(),subContract.getScNo());
		AccrualsRecord ar = ars.get(0);
		String arId = ar.getArId();
		BigDecimal scamount = subContract.getScAmount().setScale(4,BigDecimal.ROUND_HALF_DOWN);
		BigDecimal oldCost = ar.getArCost().setScale(4,BigDecimal.ROUND_HALF_DOWN);
		//若应付流水已付费  且修改了协议价款
		if(ARStatusEnum.ALREADY_CHARGE.getValue().equals(ar.getArStatus()) && !scamount.equals(oldCost)){
			return "not update";

			//若应付流水未付费，且修改了协议价款，则删除原流水记录，新增一条流水记录
		}else if(!scamount.equals(oldCost)){
			accrualsRecordDao.delete(ar);
			//形成应付流水
			arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
			accrualsRecordService.insertAccrualsRecord(pro, arId,CollectionTypeEnum.SUBCONTRACT_PAYMENT.getValue(),Integer.valueOf(ARFlagEnum.ACCOUNTS_PAY.getValue()) , subContract.getScAmount(),null,subContract.getScNo());
		}
		//更新分包合同
		subContractDao.saveOrUpdate(subContract);
		return arId;
	}

	@Override
	public GasApply jointDetail(String projId) {

		GasApply gasApply=gasApplyDao.viewGasApplyById(projId);


		Project project = projectDao.get(projId);
		ConstructionPlan con = constructionPlanDao.viewPlanById(projId);
		List<SubContract> subContracts = subContractDao.findByProjNo(project.getProjNo());
		SubContract su = new SubContract();
		if(subContracts.size()>0){
			su = subContracts.get(0);
		}


		if(gasApply==null){
			gasApply=new GasApply();
			gasApply.setProjNo(project.getProjNo());
			gasApply.setProjId(project.getProjId());
			gasApply.setProjName(project.getProjName());
			gasApply.setProjScaleDes(project.getProjScaleDes());
			gasApply.setCuName(con.getCuName());
			gasApply.setCuPm(con.getCuLegalRepresent());//项目经理
			gasApply.setBuilder(con.getBuilder());//甲方代表
			gasApply.setScAmount(su.getScAmount());//分包款
			return gasApply;
		}else{
			gasApply.setProjScaleDes(project.getProjScaleDes());
			gasApply.setCuName(con.getCuName());
			gasApply.setCuPm(con.getCuLegalRepresent());//项目经理
			gasApply.setBuilder(con.getBuilder());//甲方代表
			gasApply.setScAmount(su.getScAmount());//分包款
			return gasApply;
		}


		/*List<AccrualsRecord> ls = accrualsRecordService.findbyProjIdType(project.getProjNo());
		if(ls != null){
			for(AccrualsRecord ar : ls){
				if(ar.getArType()==CollectionTypeEnum.GAS_CONFIRM.getValue() || CollectionTypeEnum.GAS_CONFIRM.getValue().equals(ar.getArType())){
					su.setSubFlag("true");
				}
			}
		}
		su.setBuilder(con.getBuilder());
		return su;*/
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveGas(SubContract subContract) throws ParseException {
		if(StringUtils.isBlank(subContract.getCuId())){
			subContract.setCuId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		//更新工程信息
		Project pro=projectDao.get(subContract.getProjId());         //通过工程id查找Project
		projectDao.saveOrUpdate(pro);
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
		//operateRecordService.createOperateRecord(orId, subContract.getProjId(), pro.getProjNo(),StepEnum.GAS_CONFIRM.getValue(), StepEnum.GAS_CONFIRM.getMessage(),"");
		//生成通气确认应付流水
		String arId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);
		subContract = subContractDao.findSubContractByprojId(pro.getProjId());
		String mes = PayTypeSCEnum.FULLY_PAID_UP.getMessage();
		BigDecimal amount = subContract.getScAmount().multiply(new BigDecimal(mes.substring(0, mes.length()-1)).divide(new BigDecimal("100")));
		accrualsRecordService.insertAccrualsRecord(pro,arId, CollectionTypeEnum.GAS_CONFIRM.getValue(),Integer.parseInt(ARFlagEnum.ACCOUNTS_PAY.getValue()), amount,null,null);
		return arId;
	}


	private Map<String, Object> queryContractByTime(SubContractQueryReq subContractQueryReq) throws ParseException {

		//最后一个操作记录的工程记录列表（符合状态的当前工程）
		List<Map<String, Object>> ors=operateRecordDao.getOptRecordByTime(subContractQueryReq.getProjStatusId());
		Map<String, Object> map=subContractDao.querySubContract(subContractQueryReq);
		//符合当前状态的工程列表
		List<SubContract> list=(List<SubContract>) map.get("data");
		List<SubContract> listNew=new ArrayList<SubContract>();
		//时间限制（单位天）
		Integer timel = subContractQueryReq.getTimeLimit();
		long secondsLimit=-1l;
		if(timel!=null){
			secondsLimit=timel*24*60*60;
		}
		for(SubContract con :list){
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
					long seconds=workDays*24*60*60;
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
			listNew.add(con);

		}
		map.put("data",listNew);
		return map;
	}

	@Override
	public Map<String, Object> querySubAudit(SubContractQueryReq subContractQueryReq) throws ParseException {
		SystemSet systemSet=systemSetDao.get("stepId", subContractQueryReq.getStepId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			subContractQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		Map<String,Object> result = this.queryContractByTime(subContractQueryReq);
		//按步骤id进行查询 获取单据类型
		DocType docType = docTypeDao.findByStepId(StepEnum.PROJECT_PLAN_AUDIT.getValue());
		String grade = docType==null?"":docType.getGrade();
		List<SubContract> data = (List<SubContract>) result.get("data");
		if(data!=null && data.size()>0){

			/**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * */
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
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
				ManageRecordQueryReq mrq = new ManageRecordQueryReq();
				mrq.setBusinessOrderId(data.get(i).getScId());
				//mrq.setStepId(StepEnum.SUB_CONTRACT_AUDIT.getValue());
				mrq.setProjId(data.get(i).getProjId());
				//List<ManageRecord> mrls  = (List<ManageRecord>) managerecorddao.queryManageRecord(mrq).get("data");
				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepEnum.PROJECT_PLAN_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
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
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signSubContractPrint(String projId) throws ParseException {
		SubContract subContract = subContractDao.findSubContractByprojId(projId);
		if(subContract!=null){
			//标记已打印
			subContract.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			subContractDao.update(subContract);
		}
	}

	@Override
	public SubContract viewSubContractByProjId(String id) {
		try {
			Project project = projectDao.get(id);
			List<PayType> payTypes = payTypeDao.findByProjType(project);
			SubContract subContract = subContractDao.findSubContractByprojId(id);
			SubBudget subBudget = subBudgetService.viewSubBudget(id);
			ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(id);
			if(null == subContract){
				SubContract sc = new SubContract();

				sc.setDepartmentName(project.getDeptName());
				sc.setContributionModeDes(project.getContributionModeDes());
				sc.setProjectTypeDes(project.getProjectTypeDes());
				sc.setCorpName(project.getCorpName());

				sc.setProjId(constructionPlan.getProjId());
				sc.setProjNo(constructionPlan.getProjNo());
				sc.setProjName(constructionPlan.getProjName());
				sc.setProjAddr(constructionPlan.getProjAddr());
				sc.setProjScaleDes(constructionPlan.getProjScaleDes());
				sc.setCuId(constructionPlan.getCuId());
				sc.setCuName(constructionPlan.getCuName());                //乙方
				sc.setCuPm(constructionPlan.getCuPm());                    //乙方项目经理
				sc.setCuResponsiblePerson(constructionPlan.getManagementQae());        //施工员

				sc.setProjLtypeId(project.getProjLtypeId());               // 工程类型
				sc.setDeptId(project.getCorpId());
				sc.setDeptName(project.getCorpName());                     //甲方
				sc.setGasComLegalRepresent(constructionPlan.getBuilder()); //甲方现场代表

				//施工合同的计划开竣工日期组装
				sc = getScContractPlanDate(sc,constructionPlan);

				if(constructionPlan.getFirstPartyProvide()!=null&&constructionPlan.getFirstPartyProvide().equals(SubContractMethodEnum.SUPPLY.getValue())){
					sc.setContractMethod1(SubContractMethodEnum.SUPPLY.getValue());
				}

				if(null != subBudget){
					if(subBudget.getCostType().equals(CostTypeEnum.DETAILED_LIST.getValue())){
						sc.setQuAmount(subBudget.getTotalCost());                   //清单计价总造价
					}else{
						sc.setQuAmount(subBudget.getTotalQuota());                  //定额总造价
					}
				}
				if(null != payTypes){
					sc.setPayTypes(payTypes);
				}
				return sc;
			}
			subContract.setProjLtypeId(project.getProjLtypeId());               // 工程类型
			if(null != payTypes){
				subContract.setPayTypes(payTypes);
			}
			if(null != subBudget){
				subContract.setQuAmount(subBudget.getTotalAmount());                   //预算价
			}

			if(project!=null){
				subContract.setDepartmentName(project.getDeptName());
				subContract.setContributionModeDes(project.getContributionModeDes());
				subContract.setProjectTypeDes(project.getProjectTypeDes());
				subContract.setCorpName(project.getCorpName());
			}
			//施工合同的计划开竣工日期组装
			if(StringUtil.isNotBlank(constructionPlan.getProjTimeLimit()) && CheckUtil.checkNumber(constructionPlan.getProjTimeLimit())){//竣工日期根据当前时间和工期计算
				subContract = getScContractPlanDate(subContract,constructionPlan);
			}

			return subContract;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SubContract findSubContractByScId(String scId) throws ParseException {
		SubContract subContract = subContractDao.get(scId);
		if(subContract!=null){
			Project project = projectDao.get(subContract.getProjId());
			subContract.setDepartmentName(project.getDeptName());
			subContract.setContributionModeDes(project.getContributionModeDes());
			subContract.setProjectTypeDes(project.getProjectTypeDes());
			subContract.setCorpName(project.getCorpName());
			subContract.setProjLtypeId(project.getProjLtypeId());
			// 工程类型
			List<PayType> payTypes = payTypeDao.findByProjType(project);
			if(null != payTypes){
				subContract.setPayTypes(payTypes);
			}
		}

		return subContractDao.get(scId);
	}

	@Override
	public Map<String, Object> querySubContractByTime(SubContractQueryReq subContractQueryReq) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findPrintDataByProjId(String projId,String type) throws ParseException {
		//根据工程ID查询合同信息
		SubContract suCon = subContractDao.findSubContractByprojId(projId);
		Project pro = projectDao.get(projId);
		String result="";
		//分包合同报表
		String arrayStr = CompletionDataPrintEnum.SUB_CONTRACT.getCptUrl();
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && suCon != null && pro!=null){
			for(Object obj : jsonArray){
				JSONObject jsonObject=JSONObject.fromObject(obj);
				CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				if(dto.getType()!=null && dto.getType().equals(pro.getProjLtypeId())){
					String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
					String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
					ReportVersionReq reportVersionReq = new ReportVersionReq();  //创建实体
					/*
					 * 若菜单ID、工程类型、公司ID不为空则
					 */
					if(StringUtils.isNotBlank(menuId) && StringUtils.isNotBlank(pro.getCorpId()) && StringUtils.isNotBlank(pro.getProjectType())){
						reportVersionReq.setCorpId(pro.getCorpId());
						reportVersionReq.setMenuId(menuId);
						reportVersionReq.setProjType(pro.getProjectType());
					}
					try {
						List<ReportVersion> versions = reportVersionDao.queryReportVersions(reportVersionReq);  //根据实体reportVersionReq查找报表版本
						String rvId = versions.get(0).getRvId();
						String key = pro.getProjectType()+"_"+pro.getCorpId()+"_"+menuId+"_"+rvId;   //组装key值进行报表查找
						Object reportVersion = Constants.getSysConfigByKey(key);
						if(reportVersion !=null){
							//记录特定字符索引
							int beginIndex = dto.getReportlet().indexOf("/");
							int endIndex = dto.getReportlet().lastIndexOf("/");
							String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
							dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.err.println("竣工资料打印--施工合同打印版本查询失败!");
					}
					result = "{reportlet:'"+dto.getReportlet()+"',scId:'"+suCon.getScId()+"'}";
					return result;
				}
			}

		}
		return null;
	}

	/**
	 * 分合同修改
	 * 1.修改状态
	 * 2.增加修改日志
	 * 3.增加操作记录日期
	 * @author liaoyq
	 * @createTime 2018-1-23
	 *
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String modifySubContract(SubContract subContract) {
		SubContract oldSc = subContractDao.get(subContract.getScId());
		//生成操作日志
		StringBuffer operateContent= new StringBuffer("");
		if(oldSc.getScAmount().compareTo(subContract.getScAmount())==0){
			operateContent.append("分合同基本信息修改，合同金额不变");
		}else{
			operateContent.append("分合同金额由").append(oldSc.getScAmount().toString()).append("修改为").append(subContract.getScAmount().toString());
		}
		String orlId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);//生成唯一ID
		operateRecordLogService.createOperateRecordLog(orlId,OperateTypeEnum.SC_MODIFY.getValue(),subContract.getScId(),operateContent.toString());
		//生成分合同修改日志
		List<SubContractLog> logList = new ArrayList<SubContractLog>();
		SubContractLog contractLogBefor = new SubContractLog();
		BeanUtil.copyNotNullProperties(contractLogBefor, oldSc);
		contractLogBefor.setScLogId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		contractLogBefor.setModifyState(ModifyStateEnum.BEFOR_MODIFY.getValue());
		contractLogBefor.setOrlId(orlId);
		logList.add(contractLogBefor);
		SubContractLog contractLogAfter = new SubContractLog();
		BeanUtil.copyNotNullProperties(contractLogAfter, subContract);
		contractLogAfter.setScLogId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		contractLogAfter.setModifyState(ModifyStateEnum.AFTER_MODIFY.getValue());
		contractLogAfter.setOrlId(orlId);
		logList.add(contractLogAfter);
		subContractLogDao.batchInsertObjects(logList);
		//更新合同
		List<Map<String,Object>> consList = Constants.getConstantsMapByKey(Constants.SUB_CONTRACT_MODIFY_AUDIT);
		String status = "";
		Project pr = projectDao.get(subContract.getProjId());
		if(consList != null && consList.size()>0){
			for(Map<String,Object> m :consList){
				if(pr.getCorpId().equals(String.valueOf(m.get("ID")))){
					status = String.valueOf(m.get("CNVALUE"));
					break;
				}
			}
		}
		if("".equals(status)){
			subContract.setModifyState(ModifyStatusEnum.TO_AUDIT.getValue());
		}else{
			subContract.setModifyState(status);
		}
		subContractDao.update(subContract);
		//需要审核时，生成通知
		if(ModifyStatusEnum.TO_AUDIT.getValue().equals(subContract.getModifyState())){
			//生成审核待办
			Staff staff = new Staff();
			operateRecordService.cerateCurOperateRecord(pr, StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.SUB_CONTRAT_UPDATE.getValue(),subContract.getScId(),staff,"1",true);
			
		}


		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 分合同修改审核列表
	 * @throws ParseException
	 */
	@Override
	public Map<String, Object> querySubContractAudit(
			SubContractQueryReq subContractQueryReq) throws ParseException {
		SystemSet systemSet=systemSetDao.get("stepId", subContractQueryReq.getStepId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			subContractQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		Map<String,Object> result = this.queryContractByTime(subContractQueryReq);
		//按步骤id进行查询 获取单据类型
		//DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue());
		//String grade = docType==null?"":docType.getGrade();
		DocType docType = new DocType();
		String grade = "";
		List<SubContract> data = (List<SubContract>) result.get("data");
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
				docType = docTypeService.findByStepId(StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					grade=docType.getGrade();
				}else{
					grade="0";
				}

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
				List<ManageRecord> mrls = managerecorddao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepOutWorkflowEnum.SC_MODIFY_AUDIT.getValue(),MrResultEnum.PASSED.getValue());
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
	/**
	 * 获取施工合同的签订日期，计划开工、计划竣工日期
	 * 已交底还未签订取第一次交底时间，否则取当前日期
	 */
	@Override
	public SubContract getScContractPlanDate(SubContract scContract, ConstructionPlan constructionPlan){
		//工程第一次交底信息
		ConstructionWorkReq constructionWorkReq = new ConstructionWorkReq();
		constructionWorkReq.setProjId(scContract.getProjId());
		ConstructionWork constructionWork = constructionWorkDao.queryFirstCw(constructionWorkReq);
		Date scPlannedStartDate = new Date();
		if(constructionWork==null){
			scPlannedStartDate = constructionWorkDao.getDatabaseDate();//开工日期取系统时间
		}else{
			scPlannedStartDate = constructionWork.getCwDate();//第一次交底时间
		}
		scContract.setScPlannedStartDate(scPlannedStartDate);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");

		//验证工期是否为数字，否则，计划竣工日期为配合施工
		if(StringUtil.isNotBlank(constructionPlan.getProjTimeLimit())){//竣工日期根据当前时间和工期计算
			//存在工期则计算，否则计划竣工日期为
			if(CheckUtil.checkNumber(constructionPlan.getProjTimeLimit())){
				scContract.setScPlannedEndDate(format.format(DateUtil.addDay(scPlannedStartDate, Integer.parseInt(constructionPlan.getProjTimeLimit()))));
			}else{
				//查看是否已有开工报告，并且开工报告已有计划竣工日期，则施工合同计划竣工日期取开工报告的计划竣工日期
				WorkReport report = workReportService.findByProjId(scContract.getProjId());
				if(report!=null && CheckUtil.checkDate(report.getPlannedEndDate())){
					scContract.setScPlannedEndDate(report.getPlannedEndDate());
				}else{
					scContract.setScPlannedEndDate(constructionPlan.getProjTimeLimit());
				}
			}
			scContract.setScPlannedTotalDays(constructionPlan.getProjTimeLimit());
		}
		/*if(StringUtil.isNotBlank(constructionPlan.getProjTimeLimit())){//竣工日期根据当前时间和工期计算
			scContract.setScPlannedEndDate(format.format(DateUtil.addDay(scPlannedStartDate, Integer.parseInt(constructionPlan.getProjTimeLimit()))));
		}*/
		scContract.setScSignDate(scPlannedStartDate);
		return scContract;
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean rollBackContainsSubContract(String projId,String rollBackReason) {

		SubContract subContract = subContractDao.findByProjId(projId);
		if (subContract==null) return true;


		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(SubContract.class, "projId");
		criteriaMap.put(t_projId,projId);
		String stepId=StepEnum.SUB_CONTRACT.getValue();
		String tableName = Annotations.getClassTableAnNameVal(SubContract.class);
		String thisTableOrigData = abandonedRecordService.getThisTableOrigData(tableName, criteriaMap);
		abandonedRecordService.saveAbandonedRecord(subContract.getScId(),projId,stepId,rollBackReason,thisTableOrigData);


		//更新施工合同表
		subContract.setScSignDate(null);//签订日期
		subContractDao.saveOrUpdate(subContract);


		return true;
	}


}
