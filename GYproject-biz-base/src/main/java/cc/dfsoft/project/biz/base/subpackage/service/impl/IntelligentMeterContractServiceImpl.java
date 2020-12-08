package cc.dfsoft.project.biz.base.subpackage.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.ImcContractLogDao;
import cc.dfsoft.project.biz.base.contract.dao.PayTypeDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.PayType;
import cc.dfsoft.project.biz.base.contract.enums.*;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContractLog;
import cc.dfsoft.project.biz.base.subpackage.service.IntelligentMeterContractService;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceContractTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.FinanceOperateTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.finance.service.IFinanceInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * 描述:智能表合同业务实现层
 * @author liaoyq
 * @createTime 2017年9月16日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class IntelligentMeterContractServiceImpl implements
		IntelligentMeterContractService {

	@Resource
	IntelligentMeterContractDao meterContractDao;

	/** 合同日志Dao*/
	@Resource
	ImcContractLogDao imcContractLogDao;

	@Resource
	ProjectDao projectDao;

	@Resource
	PayTypeDao payTypeDao;

	@Resource
	DepartmentDao departmentDao;

	@Resource
	ConstructionPlanDao constructionPlanDao;
	/**施工预算业务接口*/
	@Resource
	SubBudgetService subBudgetService;

	@Resource
	WorkFlowService workFlowService;

	@Resource
	OperateRecordService operateRecordService;

	/** 合同日志Dao*/
	@Resource
	OperateRecordLogService operateRecordLogService;

	@Resource
	ContractDao contractDao;

	@Resource
	SurveyInfoDao surveyInfoDao;

	@Resource
	SystemSetDao systemSetDao;

	@Resource
	OperateRecordDao operateRecordDao;
	@Resource
	SubContractDao subContractDao;
	@Resource
	AccrualsRecordService accrualsRecordService;

	@Resource
	DocTypeDao docTypeDao;

	@Resource
	ManageRecordDao manageRecordDao;
	@Resource
	ProjectService projectService;
	@Resource
	IFinanceInfoService financeInfoService;
	@Resource
	CashFlowDao cashFlowDao;
	@Resource
	DocTypeService docTypeService;
	@Resource
	ManageRecordService manageRecordService;
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public String saveContract(IntelligentMeterContract contract) throws Exception{
		if(StringUtils.isBlank(contract.getImcId())&&this.findByImcNo(contract.getImcNo()).size()>0){
			return "exist";
		}
		Project pro = projectDao.get(contract.getProjId());
		if(StringUtils.isBlank(contract.getImcId())){
			contract.setImcId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		}
		contract.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());	   //未打印
		//经办人
		contract.setfPartyConAgent(SessionUtil.getLoginInfo().getStaffName());
		contract.setfPartyConAgentId(SessionUtil.getLoginInfo().getStaffId());
		meterContractDao.saveOrUpdate(contract);
		
		//生成应收流水
		if(contract.getFlag().equals("1")){
			if (StringUtils.isNotBlank(contract.getPayType())) {
				//生产合同首付款
				accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.INTELLIGENT_CONTRACT.getValue(),
						Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getFirstPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),contract.getImcNo());
				//付款类型为两次付清
				PayType payType = payTypeDao.get(contract.getPayType());
				if(payType!=null && "2".equals(payType.getPayTypeMode())){
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),contract.getImcNo());
				}else if(payType!=null && "3".equals(payType.getPayTypeMode())){
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getSecondPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),contract.getImcNo());
					accrualsRecordService.insertAccrualsRecord(pro,IDUtil.getUniqueId(Constants.MODULE_CODE_COST),CollectionTypeEnum.STAGE_INTELLIGENT_CONTRACT.getValue(),
							Integer.valueOf(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) , contract.getThirdPayment(),ArContractTypeEnum.INTELLIGENCE.getValue(),contract.getImcNo());
				}
			}
			//智能表合同推送时，调用用友财务接口todo:
			if(projectService.isToCall(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
				ResultMessage resultMessage = new ResultMessage();
				String msg = financeInfoService.synProjectInfoClient(contract.getProjId(),  FinanceOperateTypeEnum.CONTRACT_SIGN.getValue(),  FinanceContractTypeEnum.CONTRACT.getValue(),IsIntelligentConPayEnum.INTELLIGENT_CON_PAY.getValue());
				JSONObject jsonbean = JSONObject.fromObject(msg);
				//返回信息-当接口返回失败时，抛出异常
				resultMessage = (ResultMessage) JSONObject.toBean(jsonbean,ResultMessage.class);
				if(resultMessage!=null && ResultTypeEnum.FAIL.getValue().equals(resultMessage.getRet_type()) && projectService.isSynchronization(pro.getProjId(),WebServiceTypeEnum.CONTRACT_SIGN.getValue())){
					//回滚事物
					throw new ExpressException(resultMessage.getRet_type(),resultMessage.getRet_message());
				}
			}
		}
		//生成操作记录
		/*if(contract.getFlag().equals("1")){
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
			operateRecordService.createOperateRecord(orId, contract.getProjId(), contract.getProjNo(), StepOutWorkflowEnum.INTELLIGENT_CONTRACT.getValue(), StepOutWorkflowEnum.INTELLIGENT_CONTRACT.getMessage(), "");
		}*/
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	private List<IntelligentMeterContract> findByImcNo(String imcNo) {
		return meterContractDao.findByImcNo(imcNo);
	}

	/**
	 * 根据工程ID查询智能表合同详述
	 * @author liaoyq
	 * @createTime 2017-9-18
	 */
	@Override
	public IntelligentMeterContract viewContractByProjId(String id) {
		try {
			Project project = projectDao.get(id);
			IntelligentMeterContract meterContract= meterContractDao.findContractByprojId(id);
			if(null == meterContract){
				meterContract = new IntelligentMeterContract();
				//ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(id);
				meterContract.setProjId(project.getProjId());
				meterContract.setProjNo(project.getProjNo());
				meterContract.setProjName(project.getProjName());
				meterContract.setProjAddr(project.getProjAddr());
				meterContract.setProjScaleDes(project.getProjScaleDes());
				//meterContract.setImcNo(project.getProjNo());//默认工程编号-可修改

				//imc.setIsIntelligentMeter(project.getIsIntelligentMeter()!=null?project.getIsIntelligentMeter():"");//是否是智能表字段

				meterContract.setGasCustName(project.getCustName());				//用户单位-用气单位
				Contract contract = contractDao.viewContractByprojId(id);
				if(contract!=null){
					meterContract.setGasConNo(contract.getConNo());				//安装合同-用气合同编号
				}else{
					meterContract.setGasConNo(project.getProjNo());               //用气合同编号
				}
				//获取踏勘信息,查询安装户数
				List<SurveyInfo> surveyInfos = surveyInfoDao.findByProjId(id);
				if(surveyInfos!=null&&surveyInfos.size()>0){
					meterContract.setInstallNums(surveyInfos.get(0).getInstallNums()!=null?surveyInfos.get(0).getInstallNums():"");
				}
				meterContract.setDeptId(project.getDeptId());
				meterContract.setDeptName(project.getDeptName());
				meterContract.setCorpId(project.getCorpId());                     //甲方
				meterContract.setCorpName(project.getCorpName());
																		//经办人
																		//授权代表人
				//查询甲方电话及地址
				Department dept = departmentDao.get(project.getCorpId());
				if(dept!=null){
					meterContract.setfPartyAddr(dept.getLocation()!=null?dept.getLocation():"");//甲方地址
					meterContract.setfPartyTelNumber(dept.getPhone()!=null?dept.getPhone():"");	  //甲方联系电话
				}
				meterContract.setsPartyId(project.getCustId());
				meterContract.setsPartyName(project.getCustName());		//乙方
				meterContract.setsPartyTelNumber(project.getCustPhone());
				meterContract.setImcSignDate(meterContractDao.getDatabaseDate());
				//楼盘地址默认是工程地址
				meterContract.setHouseAddr(project.getProjAddr());
			}else{
				meterContract.setProjScaleDes(project.getProjScaleDes());
			}
			if(StringUtil.isNotBlank(project.getIsIntelligentMeter())){ //是否是智能表
				meterContract.setIsIntelligentMeter(project.getIsIntelligentMeter());
			}
			//甲方经办人
			meterContract.setfPartyConAgent(SessionUtil.getLoginInfo().getStaffName()!=null?SessionUtil.getLoginInfo().getStaffName():"");
			//查询合同是否已收款
			List<CashFlow> list = cashFlowDao.queryCashFlowByProjIdType(meterContract.getProjId(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(), meterContract.getImcNo());
			if(list!=null && list.size()>0){
				meterContract.setIsCashFlow(Integer.toString(list.size()));
			}
			return meterContract;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 分页查询智能表合同--弃用
	 */
	@Override
	public Map<String, Object> queryContract(IntelligentMeterContractReq imcReq) throws java.text.ParseException {

		Map<String, Object> map = meterContractDao.queryContract(imcReq);

		List<IntelligentMeterContract> imcs = (List<IntelligentMeterContract>) map.get("data");
		if(imcs!=null&&imcs.size()>0){
			for(IntelligentMeterContract imc :imcs){
					Project project=projectDao.get(imc.getProjId());
					if(project!=null){
						imc.setProjectType(project.getProjectType());
						imc.setProjStatusDes(project.getProjStatusDes());
					}

			}
		}
		return map;
	}

	/**
	 * 修改合同的打印标记
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void signImcPrint(String projId) {
		IntelligentMeterContract imc = meterContractDao.findContractByprojId(projId);
		if(imc!=null && StringUtil.isNotBlank(imc.getImcId())){
			imc.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
		}
		meterContractDao.saveOrUpdate(imc);
	}

	/**
	 * 待签智能表合同列表工程查询
	 */
	@Override
	public Map<String, Object> queryProjectImc(
			IntelligentMeterContractReq imcReq) throws java.text.ParseException {
		Map<String, Object> map = meterContractDao.queryImContractBySql(imcReq);
		List<Map<String, Object>> objs= (List<Map<String, Object>>) map.get("data");
		List<Project> projs = new ArrayList<Project>();
		if(objs!=null && objs.size()>0){
			for(Map<String, Object> obj:objs){
				Project project = new Project();
				project.setProjId(obj.get("projId").toString());
				project.setProjName(obj.get("projName").toString());
				project.setProjNo(obj.get("projNo").toString());
				project.setCorpId(obj.get("corpId").toString());
				project.setCorpName(obj.get("corpName").toString());
				project.setProjStatusId(obj.get("projStatusId")!=null?obj.get("projStatusId").toString():"");
				project.setFlag(obj.get("flag")!=null?obj.get("flag").toString():"");
				project.setProjectType(obj.get("projectType").toString());
				projs.add(project);
			}
			map.put("data", projs);
		}
		return map;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String modifyImcContract(IntelligentMeterContract intllMterContract) {
		IntelligentMeterContract imcCon = meterContractDao.get(intllMterContract.getImcId());
		//生成操作日志
		StringBuffer operateContent= new StringBuffer("");
		if(imcCon.getTotalCost().compareTo(intllMterContract.getTotalCost())==0){
			operateContent.append("智能表合同基本信息修改，合同金额不变");
		}else{
			operateContent.append("智能表合同金额由").append(imcCon.getTotalCost().toString()).append("修改为").append(intllMterContract.getTotalCost().toString())
					.append("");
		}
		String orlId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);//生成唯一ID
		operateRecordLogService.createOperateRecordLog(orlId, OperateTypeEnum.IMCCONTRACT_MODIFY.getValue(),intllMterContract.getImcId(),operateContent.toString());
		//生成智能表合同日志
		List<IntelligentMeterContractLog> logList = new ArrayList<IntelligentMeterContractLog>();
		IntelligentMeterContractLog imcContractLogBefor = new IntelligentMeterContractLog();
		BeanUtil.copyNotNullProperties(imcContractLogBefor, imcCon);
		imcContractLogBefor.setImclogId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		imcContractLogBefor.setModifyState(ModifyStateEnum.BEFOR_MODIFY.getValue());
		imcContractLogBefor.setOrlId(orlId);
		logList.add(imcContractLogBefor);
		IntelligentMeterContractLog imcContractLogAfter = new IntelligentMeterContractLog();
		BeanUtil.copyNotNullProperties(imcContractLogAfter, intllMterContract);
		imcContractLogAfter.setImclogId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		imcContractLogAfter.setModifyState(ModifyStateEnum.AFTER_MODIFY.getValue());
		imcContractLogAfter.setOrlId(orlId);
		logList.add(imcContractLogAfter);
		imcContractLogDao.batchInsertObjects(logList);


		//更新智能表合同
		List<Map<String,Object>> consList = Constants.getConstantsMapByKey(Constants.INTELLIFENT_METER_AUDIT);
		String status = "";
		Project pr = projectDao.get(intllMterContract.getProjId());
		if(consList != null && consList.size()>0){
			for(Map<String,Object> m :consList){
				if(pr.getCorpId().equals(String.valueOf(m.get("ID")))){
					status = String.valueOf(m.get("CNVALUE"));
					break;
				}
			}
		}
		if("".equals(status)){    //未配置则需要审核
			intllMterContract.setModifyStatus(ModifyStatusEnum.TO_AUDIT.getValue());
			//审核待办todo:
			operateRecordService.cerateCurOperateRecord(pr,StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.INTE_CONTRAT_UPDATE.getValue(),intllMterContract.getImcId(),new Staff(),"1",true);
			
		}else if(ModifyStatusEnum.AUDIT_PASS.getValue().equals(status)){  //配置不需要审核
			String result = "";
			try {
				result = manageRecordService.imcModifySaveTrsnRd(intllMterContract);
				return result;
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return	Constants.OPERATE_RESULT_FAILURE;
			}
			
		}

		meterContractDao.update(intllMterContract);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	
	
	@Override
	public Map<String, Object> queryContractforModifyAudit(IntelligentMeterContractReq imcQueryReq) throws java.text.ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(imcQueryReq.getStepId(),loginInfo.getCorpId());
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
//			imcQueryReq.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		Map<String,Object> result = meterContractDao.queryContract(imcQueryReq);
		//按步骤id进行查询 获取单据类型
		//DocType docType = docTypeDao.findByStepId(StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue());
		DocType docType=new DocType();
		//String grade = docType==null?"":docType.getGrade();
		String grade="";
		List<IntelligentMeterContract> data = (List<IntelligentMeterContract>) result.get("data");
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
				docType = docTypeService.findByStepId(StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
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
				List<ManageRecord> mrls = manageRecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(), StepOutWorkflowEnum.INTELLIGENT_MODIFY_AUDIT.getValue(), MrResultEnum.PASSED.getValue());
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
	public IntelligentMeterContract getImContract(String imcId) {
		IntelligentMeterContract imc = meterContractDao.get(imcId);
		if (imc==null) return null;

		Project project = projectDao.get(imc.getProjId());
		if (project==null) return imc;

		imc.setProjectTypeDes(project.getProjectTypeDes());
		imc.setContributionModeDes(project.getContributionModeDes());
		imc.setProjScaleDes(project.getProjScaleDes());
		return imc;
	}

	@Override
	public IntelligentMeterContract findContractByprojId(String projId) {
		return meterContractDao.findContractByprojId(projId);
	}
}
