package cc.dfsoft.project.biz.base.subpackage.service.impl;


import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dto.SubBudgetReq;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.ProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.enums.SubQuantitiesStatusEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.SubBudgetDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubQuantitiesDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.project.biz.base.subpackage.enums.CostTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.SubBudgetIsPassEnum;
import cc.dfsoft.project.biz.base.subpackage.enums.SubBudgetIsPrintEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.Annotations;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SubBudgetServiceImpl implements SubBudgetService{

	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	/** 工程dao*/
	@Resource
	ProjectDao projectDao;
	/** 施工预算dao*/
	@Resource
	SubBudgetDao subBudgetDao;
	/** 工程计划dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	/** 分包工程量dao*/
	@Resource
	SubQuantitiesDao subQuantitiesDao;
	/** 进度dao*/
	@Resource
	ProgressDao progressDao;
	/** 工作流dao*/
	@Resource
	WorkFlowService workFlowService;
	/** 审核记录dao*/
	@Resource
	ManageRecordDao manageRecordDao;
	
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;

	/** 部门Dao*/
	@Resource
	DepartmentDao departmentDao;
	
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	SignatureService signatureService;
	@Resource
	AbandonedRecordService abandonedRecordService;

	
	@Override
	public SubBudget viewSubBudget(String projId) {
		LoginInfo  loginInfo = SessionUtil.getLoginInfo();  //取得登录人信息

		SubBudget subBudget = subBudgetDao.findByProjId(projId);
		if (subBudget!=null) {
			if (subBudget.getMainMaterialAmountList() == null) {
				subBudget.setMainMaterialAmountList(subBudget.getMainMaterialAmountListAudit());
			}
			if (subBudget.getTotalCost() == null) {
				subBudget.setTotalCost(subBudget.getTotalCostAudit());
			}
			if (subBudget.getTotalQuota() == null) {
				subBudget.setTotalQuota(subBudget.getTotalQuotaAudit());
			}
			if (subBudget.getMainMaterialAmount() == null) {
				subBudget.setMainMaterialAmount(subBudget.getMainMaterialAmountAudit());
			}
		}
		ConstructionPlan constructionPlan = constructionPlanDao.viewPlanById(projId);
		if(null!=subBudget){
			if(constructionPlan!=null && constructionPlan.getCpArriveDate()!=null){
				subBudget.setSbDate(constructionPlan.getCpArriveDate());
			}
			return subBudget;
		}else{
			subBudget = new SubBudget();
			Project project = projectDao.get(projId);
			Department dept = departmentDao.get(project.getDeptId());
			subBudget.setProjId(project.getProjId());
			subBudget.setProjNo(project.getProjNo());
			subBudget.setProjName(project.getProjName());
			subBudget.setProjAddr(project.getProjAddr());
			subBudget.setProjScaleDes(project.getProjScaleDes());
			subBudget.setContractAmount(project.getContractAmount());
			subBudget.setDeptDivide(dept.getDeptDivide());
			if(null!=constructionPlan){
				subBudget.setCuLegalRepresent(constructionPlan.getCuLegalRepresent());
				subBudget.setCuName(constructionPlan.getCuName());
				subBudget.setCuPhone(constructionPlan.getCuPhone());
				subBudget.setSbDate(constructionPlan.getCpArriveDate());
				
			}
			if(StringUtils.isNotBlank(loginInfo.getPost()) && loginInfo.getPost().contains(SignPostEnum.BUDGET_MEMBER.getValue()) && loginInfo.getUnitType().contains(UnitTypeEnum.SUBCONTRACTING_UNIT.getValue())){
				subBudget.setOperaterId(loginInfo.getStaffId());   //操作人ID
				subBudget.setOperater(loginInfo.getStaffName());   //操作人名字
			}

			return subBudget;
		}
	}
	/**
	 * 保存施工预算
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveSubBudget(SubBudget subBudget) throws Exception {

		boolean flag = false;
		if(StringUtils.isBlank(subBudget.getSbId())){
			subBudget.setSbId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			flag = true;
		}
		if(subBudget.getAudit()!=null&&subBudget.getAudit().equals("1")){//施工预算审核保存预算信息
			if(CostTypeEnum.DETAILED_LIST.getValue().equals(subBudget.getCostType())){//清单计价
				this.saveQuantities(subBudget);//更新工程量标准分项
				/*页面传递，可以不要
				if(subBudget.getTotalCost()!=null){//清单计价审定价
					subBudget.setTotalCost(subBudget.getTotalCost());
				}
				if(subBudget.getMainMaterialAmountList()!=null){//清单计价审定材料价
					subBudget.setMainMaterialAmountList(subBudget.getMainMaterialAmountList());
				}*/
			}else{
				/*页面传递，可以不要
				if(subBudget.getTotalQuota()!=null){//定额计价审定价
					subBudget.setTotalQuota(subBudget.getTotalQuota());
				}
				if(subBudget.getMainMaterialAmount()!=null){//定额计价审定材料价
					subBudget.setMainMaterialAmount(subBudget.getMainMaterialAmount());
				}*/
				subQuantitiesDao.deleteBySbIdAndStatus(subBudget.getSbId(),SubQuantitiesStatusEnum.BUDGET.getValue());
				progressDao.deleteByProjId(subBudget.getProjId());
			}
		}else{//施工预算
			if(CostTypeEnum.DETAILED_LIST.getValue().equals(subBudget.getCostType())){//清单计价
				this.saveQuantities(subBudget);//更新工程量标准分项
				if(subBudget.getTotalCost()!=null){//清单计价报送价
					subBudget.setTotalCostAudit(subBudget.getTotalCost());
				}
				if(subBudget.getMainMaterialAmountList()!=null){//清单计价报送材料价
					subBudget.setMainMaterialAmountListAudit(subBudget.getMainMaterialAmountList());
				}
				//改变计价方式，将改变前的删除
				subBudget.setTotalQuotaAudit(null);
				subBudget.setMainMaterialAmountAudit(null);
				//清空页面传递的审定价字段
				subBudget.setTotalCost(null);
				subBudget.setMainMaterialAmountList(null);
			}else{
				if(subBudget.getTotalQuota()!=null){//定额计价报送价
					subBudget.setTotalQuotaAudit(subBudget.getTotalQuota());
				}
				if(subBudget.getMainMaterialAmount()!=null){//定额计价报送材料价
					subBudget.setMainMaterialAmountAudit(subBudget.getMainMaterialAmount());
				}
				//改变计价方式，将改变前的删除
				subBudget.setTotalQuota(null);
				subBudget.setMainMaterialAmount(null);
				//清空页面传递的审定价字段
				subBudget.setTotalCost(null);
				subBudget.setMainMaterialAmountList(null);
				subQuantitiesDao.deleteBySbIdAndStatus(subBudget.getSbId(),SubQuantitiesStatusEnum.BUDGET.getValue());
				progressDao.deleteByProjId(subBudget.getProjId());
				
			}
			//施工预算员
			LoginInfo loginInfo =SessionUtil.getLoginInfo();
			if(StringUtil.isBlank(subBudget.getSuBudgeter())){
				subBudget.setSuBudgeter(loginInfo.getStaffName());
			}
			if(StringUtil.isBlank(subBudget.getSuBudgeterId())){
				subBudget.setSuBudgeterId(loginInfo.getStaffId());
			}
			subBudget.setOperateDate(subBudgetDao.getDatabaseDate());//报送日期
		}
		//保存预算信息
		subBudgetDao.saveOrUpdate(subBudget);
		//更新工程状态
		Project pro= projectDao.get(subBudget.getProjId());
		//推送
		if("1".equals(subBudget.getFlag())){
			//形成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);//生成唯一ID
			String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.QUALITIES_DECLARATION.getValue(), StepEnum.QUALITIES_DECLARATION.getMessage(), "");
			pro.setToDoer(toder);
			String status =workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.QUALITIES_DECLARATION.getActionCode(), true);
			pro.setProjStatusId(status);
			
		}
		if(CostTypeEnum.DETAILED_LIST.getValue().equals(subBudget.getCostType())){
			pro.setFirstSubmitAmount(subBudget.getTotalCostAudit());//初始施工预算合计清单计价
			pro.setSubmitAmount(subBudget.getTotalCost());			//施工预算合计清单计价
		}else{
			pro.setFirstSubmitAmount(subBudget.getTotalQuotaAudit());//初始施工预算合计定额计价
			pro.setSubmitAmount(subBudget.getTotalQuota());			//施工预算合计清单计价
			
		}
	    projectDao.update(pro);
	    
	    
	   //保存签字信息
	  	signatureService.saveOrUpdateSign("menuId+menuNane+29",subBudget.getSign(), subBudget.getProjId(), subBudget.getSbId(), subBudget.getClass().getName(),flag);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	//清单计价，保存相应的清单分项
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveQuantities(SubBudget subBudget) {
		List<SubQuantities> list=new ArrayList<SubQuantities>();
		//更新工程进度
		List<Progress> listProcess=new ArrayList<Progress>();
		String unitQueId;
		//添加分包工程量记录
		for(SubQuantities sq:subBudget.getList()){
			//工程量标准
			unitQueId=IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);
			sq.setSqId(unitQueId);
			sq.setSbId(subBudget.getSbId());
			//工程量状态
			sq.setSqStatus(SubQuantitiesStatusEnum.BUDGET.getValue());
			list.add(sq);
			Progress pros=new Progress();
			//工程进度
			pros.setProjId(subBudget.getProjId());
			pros.setProjNo(subBudget.getProjNo());
			pros.setProjName(subBudget.getProjName());
			pros.setProjScaleDes(subBudget.getProjScaleDes()); 
			pros.setQuId(unitQueId);						//工程进度工程量id
			pros.setNuitProject(sq.getSqBranchProjName());  //分部分项名称
			pros.setSqUnit(sq.getSqUnit());				    //单位
			pros.setAllProgressNum(sq.getSqNum());		    //数量
			pros.setRegisterDate(progressDao.getDatabaseDate());//登记时间
			pros.setProgressId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			listProcess.add(pros);
		}
		subQuantitiesDao.deleteBySbIdAndStatus(subBudget.getSbId(),SubQuantitiesStatusEnum.BUDGET.getValue());
		progressDao.deleteByProjId(subBudget.getProjId());
		
		subQuantitiesDao.batchInsertObjects(list);
		progressDao.batchInsertObjects(listProcess);
	}





	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean rollBackContainsSubBudget(String projId,String rollBackReason) {
		Project project = projectDao.get(projId);
		SubBudget subBudget = subBudgetDao.findByProjId(projId);

		if (subBudget==null || project==null) return false;

		//备份原信息记录
		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(SubBudget.class, "projId");
		criteriaMap.put(t_projId,projId);
		String tableName = Annotations.getClassTableAnNameVal(SubBudget.class);
		String origData = abandonedRecordService.getThisTableOrigData(tableName,criteriaMap);
		abandonedRecordService.saveAbandonedRecord(subBudget.getSbId(),projId,StepEnum.QUALITIES_DECLARATION.getValue(),rollBackReason,origData.toString());


		//更新预算表
		subBudget.setSbDate(null);//预算日期
		subBudgetDao.save(subBudget);


		//更新工程表
		project.setBudgeterAudit(null);		 //施工预算审核员
		project.setBudgeterAuditId(null);	 //施工预算审核员Id
		project.setFirstSubmitAmount(null);//初始施工预算合计清单计价
		project.setSubmitAmount(null);		//施工预算合计清单计价
		projectDao.saveOrUpdate(project);

		return true;
	}




	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void auditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,
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

		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//调用工作流定义方法获取状态码
		Project project = projectDao.get(manageRecord.getProjId());
		String todoer="";
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			isNextStatus = false;
			//删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(),  manageRecord.getMenuId());//结算的签字信息
		//若此次审核级别为单据类型设置的级别，则更新工程记录状态
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			//审核通过--将通知置为无效
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.End_SETTLEMENT_POST);

		}else{
			signatureService.saveOrUpdateSignPicture(manageRecord.getMenuId(), manageRecord.getMrAuditLevel(), manageRecord.getProjId(), manageRecord.getBusinessOrderId(), SettlementDeclaration.class.getName(), false,Constants.End_SETTLEMENT_POST);
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		//noticeDao.saveOrUpdate(notice);
		if(isNextStatus!=null){
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);
			if(isNextStatus==true){
				SubBudget subBudget = subBudgetDao.get(manageRecord.getBusinessOrderId());
				if(null!=subBudget){
					subBudget.setAuditState(SubBudgetIsPassEnum.ALREADY_PASS.getValue());
					subBudget.setIsprint(SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());
					subBudgetDao.update(subBudget);
				}
				String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.QUALITIES_JUDGEMENT.getActionCode(), true);
				project.setProjStatusId(status);
				todoer=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "");
			}else{
				//回退到第一级审核人
				todoer=operateRecordService.batUpdateHistoryRecordByBoIdToFirst(project.getProjId(), "", stepID);
			}
			project.setToDoer(todoer);//待办人
			
			//2.更新工程记录
			projectDao.update(project);
		}
	}
	@Override
	public Map<String, Object> querySubBudgetPrint(SubBudgetReq subBudgetReq) throws ParseException {
		//subBudgetReq.setAuditState(SubBudgetIsPassEnum.ALREADY_PASS.getValue());
		
		Map<String, Object> map=subBudgetDao.querySubBudgetPrint(subBudgetReq);
		List<SubBudget> list=(List<SubBudget>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(SubBudget subBudget:list){
				
					Project project=projectDao.get(subBudget.getProjId());
					if(project!=null){
						subBudget.setProjectType(project.getProjectType());
					}
				
			}
		}
		
		return map;
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String signBudgetPrint(String sbId) {
		SubBudget subBudget=subBudgetDao.get(sbId);
		if(subBudget!=null){
			//标记已打印
			subBudget.setIsprint(SubBudgetIsPrintEnum.ALREADY_PRINT.getValue());
			subBudgetDao.update(subBudget);
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	@Override
	public Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq)throws ParseException {
		return subBudgetDao.queryBudgeter(designerQueryReq);
	}
	@Override
	public String findPrintDataByProjId(String projId,String type) {
		String result ="";
		//根据工程ID查询合同信息
		SubBudget subBudget= subBudgetDao.findByProjId(projId); //根据工程ID取得实体
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		//施工预算
		String arrayStr = CompletionDataPrintEnum.SU_BUDGET.getCptUrl();
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && subBudget!=null && project !=null){
			for(Object obj : jsonArray){
				 JSONObject jsonObject=JSONObject.fromObject(obj);
				 CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
				 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
				 String key = project.getContributionMode()+"_"+project.getCorpId()+"_"+menuId;
				 Object reportVersion = Constants.getSysConfigByKey(key);
				   if(reportVersion !=null){
					   //记录特定字符索引
					   int beginIndex = dto.getReportlet().indexOf("/");
					   int endIndex = dto.getReportlet().lastIndexOf("/");
				       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				   }
				 result = "{reportlet:'"+dto.getReportlet()+"',projId:'"+subBudget.getProjId()+"',sbId:'"+subBudget.getSbId()+"'}";
				 return result;

			}
		}
		return null;
	}
	@Override
	public Date getDatabaseDate() {
		return subBudgetDao.getDatabaseDate();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void firstAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID, String constants) {
		/**
		 * 1.产生审核记录
		 * 2.更新工程记录状态
		 * 3.产生业务操作记录
		 * */
		manageRecord.setMrId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));		//审核记录主键id

		if(StringUtil.isBlank(manageRecord.getBusinessOrderId())){
			manageRecord.setBusinessOrderId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
		}
		Project project=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,project.getCorpId(),project.getProjectType(),project.getContributionMode());
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

		//默认状态标记为下一个状态
		Boolean isNextStatus = null;
		//若当前审核结果为不通过，则更新工程记录状态为上一个状态
		if(manageRecord.getMrResult().equals(MrResultEnum.NOT_PASSED.getValue())){
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
			//删除之前的签字signature
			signatureService.deleteImgByBIdAndMenuId(manageRecord.getBusinessOrderId(), manageRecord.getMenuId());//结算的签字信息

			isNextStatus = false;
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){//若此次审核级别为单据类型设置的级别，则更新工程记录状态
			isNextStatus = true;
			//审核通过--将通知置为无效
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
		
		
		String toder="";
		if(isNextStatus!=null){
			//产生业务操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);
			//调用工作流定义方法获取状态码
			if("0".equals(manageRecord.getMrResult())){
				//未通过
				project.setSignBack(constants);
				//不通过 
				toder=operateRecordService.batUpdateHistoryRecordByBoId(project.getProjId(), "", stepID);
			}
			if("1".equals(manageRecord.getMrResult())){

				toder=operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(), stepID, StepEnum.valueof(stepID).getMessage(), "true");
			}
			project.setToDoer(toder);
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.QUALITIES_DISPATCH_FIRST_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(status);
			
			//2.更新工程记录
			projectDao.update(project);
		}
	}
	/**
	 * 根据工程ID查询施工预算信息
	 */
	@Override
	public SubBudget findSubBudgetByProjID(String projId) {
		return subBudgetDao.get("projId", projId);
	}
}
