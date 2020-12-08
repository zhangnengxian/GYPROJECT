package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionOrganizationDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionWorkDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.WorkReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionOrganization;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.enums.FinishStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.design.service.SurveyInfoService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class WorkReportServiceImpl implements WorkReportService {
	//自定义级别标签
	public static Logger logger=LoggerFactory.getLogger("interfaceinfo");
	@Resource
	WorkReportDao workReportDao;

	@Resource
	ProjectDao projectDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	ContractDao contractDao;
	
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	SignatureService signatureService;

	@Resource
	SubContractDao subContractDao;
	/**工作流服务接口*/
	@Resource
	WorkFlowService workFlowService;

	/**踏勘信息服务接口*/
	@Resource
	SurveyInfoService surveyInfoService;
	
	/**设计信息服务接口*/
	@Resource
	DesignInfoService designInfoService;
	
	/**会审交底*/
	@Resource
	ConstructionWorkDao constructionWorkDao;
	
	/**施工组织*/
	@Resource
	ConstructionOrganizationDao constructionOrganizationDao;

	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	@Resource
	ReportVersionService reportVersionService;
	
	@Resource
	OperateRecordDao operateRecordDao;

	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	SynchronizedService synchronizedService;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String workReportSave(WorkReport workReport) throws Exception {
		boolean flag = false;
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (StringUtils.isBlank(workReport.getWrId())) {
			flag = true;
			workReport.setWrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			workReport.setSuDate(workReportDao.getDatabaseDate());
			List<WorkReport> isNotNullworkReport = workReportDao.findByProjId(workReport.getProjId(),null);
			if(isNotNullworkReport !=null && isNotNullworkReport.size() > 0){  //判断开工报告只能有一条
				return "already";
			}
		}
		Project pro = projectDao.get(workReport.getProjId());
		if (StringUtil.isNotBlank(workReport.getBuilder())) { // 施工员已签字
             OperateRecord operateRecord = operateRecordDao.queryWorkNotice(pro.getProjNo(),StepEnum.CONSTRUCTION.getValue(),pro.getProjectType());  //查当前工程开工报告工作通知
            if(operateRecord != null && flag){
            	 //operateRecordDao.delete(operateRecord);  //先删除
            	 operateRecord.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());  
            	 operateRecord.setOperateTime(operateRecordDao.getDatabaseDate());  //操作时间
            	 operateRecordDao.saveOrUpdate(operateRecord); //后添加
            }
            
		}
		// 更新工程
		if (FinishStateEnum.ALREADY_FINISHED.getValue().equals(workReport.getSignState())) {
			// 全签字
			// 只有待施工才会翻转工程状态
			if (ProjStatusEnum.TO_CONSTRUCTION.getValue().equals(pro.getProjStatusId())) {
				String statusId = workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),
						pro.getContributionMode(), WorkFlowActionEnum.CONSTRUCTION.getActionCode(), true);

				pro.setProjStatusId(statusId);

				// 形成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);// 生成唯一ID
				String todoer = operateRecordService.createOperateRecord(orId, workReport.getProjId(),
						workReport.getProjNo(), StepEnum.CONSTRUCTION.getValue(), StepEnum.CONSTRUCTION.getMessage(),
						"");
				pro.setToDoer(todoer);
				projectDao.update(pro);

			}
		}
		workReportDao.saveOrUpdate(workReport);
		List<Signature> signs = workReport.getSign();
		if (signs != null && signs.size() > 0) {
			for (Signature sign : signs) {
				sign.setDataType(SignDataTypeEnum.STARTING_REPORT.getValue());
			}
			workReport.setSign(signs);
		}

		signatureService.saveOrUpdateSign("menuId+menuNane+22", workReport.getSign(), workReport.getProjId(), workReport.getWrId(), workReport.getClass().getName(), flag);

		//签完字后调用鸿巨接口（开工报告信息新增/修改）返回信息
		if ("1".equals(workReport.getSignState())){
			ResultMsg resultMsg = synchronizedService.callSynchronizedWorkReport(workReport.getProjId(),WebServiceTypeEnum.WORK_REPORT.getValue());
			if (resultMsg!=null && resultMsg.getCode()!=0){
				throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
			}
		}

		resMap.put("data", workReport.getWrId());
		return resMap != null ? JSONObject.fromObject(resMap).toString() : "";
	}

	@Override
	public WorkReport workReportDetail(String projId,String dataType) throws ParseException {
		Project project = projectDao.get(projId);
		List<SurveyInfo> surList = surveyInfoService.findByProjId(projId);
		DesignInfo designInfo = designInfoService.queryById(projId);
		WorkReport workRep = new WorkReport();
		List<WorkReport> workReps = workReportDao.findByProjId(projId,dataType);
		List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
		SubContract subContract = subContractDao.findSubContractByprojId(projId);
		Contract contract = contractDao.viewContractByprojId(projId);
		SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		//查会审交底和施工组织
		ConstructionWorkReq req=new ConstructionWorkReq();
		req.setProjId(projId);
		req.setSignState(FinishStateEnum.ALREADY_FINISHED.getValue());
		Map<String,Object> map=constructionWorkDao.queryList(req);
		List<ConstructionWork> list=(List<ConstructionWork>) map.get("data");
		
		//查施工组织
		ConstructionOrganization con=constructionOrganizationDao.findByProjIdAndState(projId, FinishStateEnum.ALREADY_FINISHED.getValue());
		
		
		if(workReps!=null&&workReps.size()>0){
			workRep=workReps.get(0);
			
			if(list!=null && list.size()>0 && con!=null){
				workRep.setSignState(FinishStateEnum.ALREADY_FINISHED.getValue());
			}else{
				workRep.setSignState(FinishStateEnum.NOT_FINISH.getValue());
			}
			if(constructionPlans!=null && constructionPlans.size()!=0){
				ConstructionPlan constructionPlan = constructionPlans.get(0);
				workRep.setConstructionUnit(constructionPlan.getCuName());
				workRep.setSuName(constructionPlan.getSuName());
			}

			String cptUrl = findCptUrl(project,Constants.STARTREPORT_MENUID,workRep.getPlannedStartDate().toString()!=null? workRep.getPlannedStartDate().toString():"");
			if(StringUtil.isNotBlank(cptUrl)){
				workRep.setCptUrl(cptUrl);
			}
			return workRep;
		}
		
		if(list!=null && list.size()>0 && con!=null){
			workRep.setSignState(FinishStateEnum.ALREADY_FINISHED.getValue());
		}else{
			workRep.setSignState(FinishStateEnum.NOT_FINISH.getValue());
		}
		
		workRep.setProjId(project.getProjId());
		workRep.setProjNo(project.getProjNo());
		workRep.setProjAddr(project.getProjAddr());
		workRep.setProjName(project.getProjName());
		workRep.setProjScaleDes(project.getProjScaleDes());
		workRep.setProjType(project.getProjectTypeDes());
		//增加字段
		if(StringUtils.isNotBlank(project.getCustName())){
			workRep.setCustName(project.getCustName());
		}
		if(StringUtils.isNotBlank(project.getDeptId())){
			workRep.setDeptId(project.getDeptId());
		}
		if(StringUtils.isNotBlank(project.getCorpId())){
			workRep.setCorpId(project.getCorpId());
		}
		if(StringUtils.isNotBlank(project.getCorpName())){
			workRep.setCorpName(project.getCorpName());
		}
		if(StringUtils.isNotBlank(project.getTenantId())){
			workRep.setTenantId(project.getTenantId());
		}
		if(null!=surList&&surList.size()>0){
			workRep.setPatchCode(surList.get(0).getArea());
		}
		if(null!=designInfo){
			workRep.setDrawingNo(designInfo.getDesignDrawingNo());
		}
		try {
			if(contract!=null){
				workRep.setConNo(contract.getConNo());
			}
			if(constructionPlans!=null && constructionPlans.size()!=0){
				ConstructionPlan constructionPlan = constructionPlans.get(0);
				workRep.setPlannedStartDate(workReportDao.getDatabaseDate());//未产生开工报告时开工日期为系统日期
				workRep.setCwDate(workReportDao.getDatabaseDate());//当前日期
				if(StringUtil.isNotBlank(constructionPlan.getProjTimeLimit())){//竣工日期根据当前时间和工期计算
					//存在工期则计算，否则计划竣工日期为
					if(CheckUtil.checkNumber(constructionPlan.getProjTimeLimit())){
						//实际开工日期+计划工期取得
						workRep.setPlannedEndDate(formats.format(DateUtil.addDay(workRep.getPlannedStartDate(), Integer.parseInt(constructionPlan.getProjTimeLimit()))));
						//计划工期
						workRep.setTimeLimit(constructionPlan.getProjTimeLimit());
					}else{
						workRep.setPlannedEndDate(constructionPlan.getProjTimeLimit());
						workRep.setTimeLimit(constructionPlan.getProjTimeLimit());
					}
				}
				workRep.setConstructionUnit(constructionPlan.getCuName());
				workRep.setSuName(constructionPlan.getSuName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String cptUrl = findCptUrl(project,Constants.STARTREPORT_MENUID,workRep.getPlannedStartDate().toString()!=null? workRep.getPlannedStartDate().toString():"");
		if(StringUtil.isNotBlank(cptUrl)){
			workRep.setCptUrl(cptUrl);
		}
		return workRep;
	}

	/**
	 * 
	 * 注释：开工报告cpt 配置规则：各分公司没有，则取全局的
	 * @author liaoyq
	 * @createTime 2018年11月30日
	 * @param project
	 * @return
	 */
	@Override
	public String findCptUrl(Project project,String menuId,String signDate) {
		String cptUrl="";
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String corpId = loginInfo.getCorpId();//默认当前用户的公司ID
		String rvId="";
		if(project!=null){
			corpId = project.getCorpId();
		}
		if(StringUtil.isBlank(rvId)){
			//获取版本信息:菜单ID下取离指定日期最近的一个版本
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			reportVersionReq.setMenuId(menuId);
			if(StringUtils.isNotBlank(signDate)){
				reportVersionReq.setSignDate(signDate);
			}
			reportVersionReq.setCorpId(corpId);
			//查询该版本日期之前的最近一次版本信息
			List<ReportVersion> versions = new ArrayList<ReportVersion>();
			try {
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if(versions!=null && versions.size()>0){
					rvId = versions.get(0).getRvId();
				}else{//取全局的
					reportVersionReq.setCorpId(Constants.START_REPORT_CPT_CORP_MODE);//默认
					versions = reportVersionService.queryReportVersions(reportVersionReq);
					if(versions!=null && versions.size()>0){
						rvId = versions.get(0).getRvId();
					}
				}
			} catch (ParseException e) {
				logger.error("开工报告报表打印版本查询失败！", e);
				e.printStackTrace();
			}
		}
		//分公司下有配置则返回分公司的，否则返回全局的
		String key =corpId + "_" + menuId + "_" + rvId;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj != null){
			cptUrl = obj.toString();
			return cptUrl;
		}
		//全局
		key = Constants.START_REPORT_CPT_CORP_MODE+"_"+menuId + "_" + rvId;
		obj = Constants.getSysConfigByKey(key);
		if(obj != null){
			cptUrl = obj.toString();
			return cptUrl;
		}
		return null;
	}




	/**
	* @Description: 开工报告
	* @author zhangnx
	* @date 2019/8/23 23:42
	*/
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean delBackupsWorkReport(String projId,String rollBackReason) {
		List<WorkReport> wrList = workReportDao.findByProjId(projId,null);

		if (wrList==null ||wrList.size()<1) return true;

		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(WorkReport.class, "projId");
		criteriaMap.put(t_projId,projId);
		String stepId=StepEnum.CONSTRUCTION.getValue();

		String tableName = Annotations.getClassTableAnNameVal(WorkReport.class);
		for (WorkReport wr:wrList) {
			abandonedRecordService.delBackupsThisTableRecordAndSignature(tableName,criteriaMap,wr.getWrId(),rollBackReason,stepId);
		}

		return true;
	}


	@Override
	public String findPrintDataByProjId(String projId,String type) {
		String result ="";
		//根据工程ID查询交底信息
		List<WorkReport> wrList= workReportDao.findByProjId(projId, null);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		WorkReport wr =null;
		if(wrList!=null && wrList.size()>0){
			wr =  new WorkReport();
			wr = wrList.get(0);
		}
		//安装合同报表
		String arrayStr = CompletionDataPrintEnum.STARTING_REPORT.getCptUrl();
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && wr!=null && project !=null){
			net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			 String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
			 Object reportVersion = Constants.getSysConfigByKey(key);
			   if(reportVersion !=null ){
				   //记录特定字符索引  
				   int beginIndex = dto.getReportlet().indexOf("/"); 
				   int endIndex = dto.getReportlet().lastIndexOf("/");
			       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
				   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
			   }
			result += "{reportlet:'"+dto.getReportlet()+"',projName:'"+wr.getProjName()+"',suName:'"+wr.getSuName()+"',projAddr:'"+wr.getProjAddr()
				   +"',constructionUnit:'"+wr.getConstructionUnit()+"',custName:'"+wr.getCustName()+"',corpName='"+wr.getCorpName()+"',wrId:'"+wr.getWrId()
				   +"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
			return result;
		}
		return null;
	}

	@Override
	public WorkReport findByProjId(String projId) {
		return workReportDao.get("projId",projId);
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			WorkReport workReport=workReportDao.get(cwId);
			if(workReport!=null){
				//项目经理
				
				//builder 施工员
				//constructionQc 质检员
				//safetyOfficer 安全员
				// cuPm 项目经理
				
				//suJgj 现场监理
				//suCes 总监
				
				//fieldRepresent 现场代表
				//projectLeader 项目负责人
				
				//质检员
				if(StringUtils.isNotBlank(workReport.getConstructionQc())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				
				//安全员
				if(StringUtils.isNotBlank(workReport.getSafetyOfficer())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SAFTYOFFICER.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				//项目经理
				if(StringUtils.isNotBlank(workReport.getCuPm())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				
				//现场监理
				if(StringUtils.isNotBlank(workReport.getSuJgj())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				//总监
				if(StringUtils.isNotBlank(workReport.getSuCes())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				
				//现场代表
				if(StringUtils.isNotBlank(workReport.getFieldRepresent())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				//组长
				if(StringUtils.isNotBlank(workReport.getProjectLeader())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.PROJECT_LEADER.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				//副总
				if(StringUtils.isNotBlank(workReport.getViceGeneralManager())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.VICE_GENERAL_MANAGER.getValue(), SignDataTypeEnum.STARTING_REPORT.getValue(),
							workReport.getWrId(), workReport.getProjId(),signState);
				}
				
			}
		}
		
	}
}
