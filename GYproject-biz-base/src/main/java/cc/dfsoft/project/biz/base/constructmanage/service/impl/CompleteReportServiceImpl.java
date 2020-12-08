package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionListDao;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.CompleteReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.WorkReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompleteReportReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.service.CompleteReportService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.*;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class CompleteReportServiceImpl implements CompleteReportService {

	 /**竣工报告Dao*/
    @Resource
     CompleteReportDao  completeReportDao;

	@Resource
	SubContractDao subContractDao;
    /** 签字服务接口*/
    @Resource
    SignatureService signatureService;

	@Resource
	WorkReportDao workReportDao;

	@Resource
	ProjectDao projectDao;
	
	@Resource
	SelfInspectionListDao selfInspectionListDao;
	@Resource
	ReportVersionService reportVersionService;


	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	@Resource
	OperateRecordService operateRecordService;

	@Resource
	AbandonedRecordService abandonedRecordService;
	
	@Resource
	AccessoryDao accessoryDao;
	
	/**
	 * 动工报审列表查询
	 * @param  completeReportReq
	 * return
	 */
	@Override
	public Map<String, Object> queryCompleteReport( CompleteReportReq  completeReportReq) throws ParseException {
		Map<String, Object> map = completeReportDao.queryCompleteReport(completeReportReq);
		List<CompleteReport> completeReportList = (List<CompleteReport>) map.get("data");
		for(CompleteReport completeReport : completeReportList){
			SubContract subContract = subContractDao.findSubContractByprojId(completeReportReq.getProjId());
			if(null!=subContract){
				completeReport.setScAmount(subContract.getScAmount());
				completeReport.setScPlannedStartDate(subContract.getScPlannedStartDate());
				completeReport.setScPlannedEndDate(subContract.getScPlannedEndDate());
				completeReport.setScPlannedTotalDays(subContract.getScPlannedTotalDays());
				completeReport.setCorpName(subContract.getDeptName());
				completeReport.setConstructionUnit(subContract.getCuName());
			}
		}
		return map;
	}

	/**
	 * 根据竣工报告id查询详述
	 * @param crId
	 * @return
	 */
	@Override
	public  CompleteReport findByCrId(String crId) {
		       CompleteReport  completeReport =  completeReportDao.findByCrId(crId);
		       Project project = projectDao.get(completeReport.getProjId());
		       List<WorkReport> wrs = workReportDao.findByProjId(completeReport.getProjId(),null);  
		         //若是2019年2月25之后的工程，竣工报告计划开工日期、计划竣工日期取开工报告上实际开工日期、计划竣工日期
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				ReportVersionReq reportVersionReq = new ReportVersionReq();
				reportVersionReq.setMenuId("120502");
				if (completeReport.getRealStartDate()!=null) {
					reportVersionReq.setSignDate(dateFormat.format(completeReport.getRealStartDate()));  //日期转为字符串
				}
				reportVersionReq.setCorpId(Constants.START_REPORT_CPT_CORP_MODE);  //默认贵阳
				reportVersionReq.setProjType(project.getProjectType());  //得到工程类型
				//查询该版本日期之前的最近一次版本信息
				List<ReportVersion> versions = new ArrayList<ReportVersion>();
				try {
					versions = reportVersionService.queryReportVersions(reportVersionReq);
					if(versions.size() >0 ){
					Date SignDate = versions.get(0).getRvDate();
					   if(SignDate.getTime() < wrs.get(0).getPlannedStartDate().getTime()){  //判断实际开工日期是否大于配置日期，若是竣工报告上实际开工日期、计划竣工日期则取开工报告
						   completeReport.setScPlannedStartDate(wrs.get(0).getPlannedStartDate());
						   completeReport.setScPlannedEndDate(wrs.get(0).getPlannedEndDate());
						   completeReport.setRealStartDate(wrs.get(0).getPlannedStartDate());
						   completeReport.setScPlannedTotalDays(wrs.get(0).getTimeLimit());   //计划工作天数
					   }
					}
					
				
				} catch (ParseException e) {
					e.printStackTrace();
				}
		  return  completeReport;
	}

	/**
	 * 保存竣工报告
	 * @param  completeReport
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String completeReportSave( CompleteReport completeReport) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank( completeReport.getCrId())){
			flag = true;
			 completeReport.setCrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			}
		completeReport.setCrFlag("1");
		completeReportDao.saveOrUpdate(completeReport);
		//关联工程
		Project pro=projectDao.get(completeReport.getProjId());
		
		List<Signature> signs=completeReport.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.COMPLETE_REPORT.getValue());
			}
			completeReport.setSign(signs);
		}
		

		signatureService.saveOrUpdateSign("menuId+menuNane+9",completeReport.getSign(),  completeReport.getProjId(),  completeReport.getCrId(),  completeReport.getClass().getName(), flag);

		//将竣工报告待办通知置为无效
		operateRecordService.noticeSetInvalid(completeReport.getProjId(), StepEnum.COMPLETED_REPORT.getValue(),"2" );

		//总监签字后
		//如果监理单位未上传评估报告，则通知监理单位资料员传递监理评估报告
		//如果已上传监理评估报告，如果存在待办，则将待办置为已办
		boolean noticeFlag = false;
		if(StringUtil.isNotBlank(completeReport.getSuCse())){
			AccessoryList accessoryQueryReq = new AccessoryList();
			accessoryQueryReq.setStep(Constants.COMPLETE_REPORT);//竣工报告菜单
			accessoryQueryReq.setSourceType(AccessorySourceEnum.COLLECT_FILE.getValue());//来源：采集
			accessoryQueryReq.setProjId(completeReport.getProjId());//工程ID
			accessoryQueryReq.setBusRecordId(completeReport.getCrId());//业务单ID
			List<AccessoryList> list = accessoryDao.queryAccessory(accessoryQueryReq);
			if(list==null ||  list.size()<=0){
				noticeFlag = true;
			}else{
				noticeFlag = false;
			}
		}else{
			noticeFlag = false;
		}
		//评估报告待办通知
		operateRecordService.noticeSuReport(completeReport.getCrId(),pro,noticeFlag,Constants.COMPLETE_REPORT);
		return  completeReport.getCrId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteById(String crId) {
		completeReportDao.deleteById(crId);
	}

	@Override
	public CompleteReport findByProjId(String projId) throws ParseException {
		CompleteReport completeReport = new CompleteReport();
		WorkReport wr = new WorkReport();
		List<WorkReport> wrs = workReportDao.findByProjId(projId,null);
		Project project = projectDao.get(projId);  //得到记录
		if(wrs!=null && wrs.size()!=0){
			wr = wrs.get(0);
		}
		completeReport.setProjId(wr.getProjId());
		completeReport.setProjNo(wr.getProjNo());
		completeReport.setProjName(wr.getProjName());
		completeReport.setProjAddr(wr.getProjAddr());
		completeReport.setRealStartDate(wr.getPlannedStartDate());//实际开工日期
		completeReport.setProjAddr(wr.getProjAddr());
		completeReport.setSuName(wr.getSuName());
		List<SelfInspectionList> selfInspectionList=selfInspectionListDao.findByprojectId(projId);
		//添加默认的竣工日期（自检单推送日期）
		if(selfInspectionList!=null&&selfInspectionList.size()>0){
			completeReport.setRealEndDate(selfInspectionList.get(0).getPushDate());
		}
		SubContract subContract = subContractDao.findSubContractByprojId(projId);
		if(null!=subContract){
			completeReport.setScAmount(subContract.getScAmount());
			completeReport.setScPlannedStartDate(subContract.getScPlannedStartDate());
			completeReport.setScPlannedEndDate(subContract.getScPlannedEndDate());
			completeReport.setScPlannedTotalDays(subContract.getScPlannedTotalDays());
			completeReport.setCorpName(subContract.getDeptName());
			completeReport.setConstructionUnit(subContract.getCuName());
		}
		//若是2019年2月25之后的工程，竣工报告计划开工日期、计划竣工日期取开工报告上实际开工日期、计划竣工日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ReportVersionReq reportVersionReq = new ReportVersionReq();
		reportVersionReq.setMenuId("120502");
		reportVersionReq.setSignDate(dateFormat.format(wr.getPlannedStartDate()));  //日期转为字符串
		reportVersionReq.setCorpId(Constants.START_REPORT_CPT_CORP_MODE);  //默认贵阳
		reportVersionReq.setProjType(project.getProjectType());  //得到工程类型
		//查询该版本日期之前的最近一次版本信息
		List<ReportVersion> versions = new ArrayList<ReportVersion>();
		try {
			versions = reportVersionService.queryReportVersions(reportVersionReq);
			if(versions.size() >0 ){
			Date SignDate = versions.get(0).getRvDate();
			   if(SignDate.getTime() < wr.getPlannedStartDate().getTime()){  //判断实际开工日期是否大于配置日期，若是竣工报告上实际开工日期、计划竣工日期则取开工报告
				   completeReport.setScPlannedStartDate(wr.getPlannedStartDate());
					completeReport.setScPlannedEndDate(wr.getPlannedEndDate());
					completeReport.setScPlannedTotalDays(wr.getTimeLimit());   //计划工作天数
			   }
			}
			
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return completeReport;
	}

	@Override
	public List<Object> findPrintDataByProjId(String projId,String type) {
		String result ="";
		List<Object> list = new ArrayList<Object>();
		//根据工程ID查询变更记录信息
		//多条竣工报告
		List<CompleteReport> crs = completeReportDao.findByProjId(projId);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		//安装合同报表
		String arrayStr = CompletionDataPrintEnum.COMPLETE_REPORT.getCptUrl();
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && crs!=null && crs.size()>0 && project !=null){
			 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			 String key = project.getCorpId()+"_"+project.getProjectType()+"_"+menuId;
			 Object reportVersion = Constants.getSysConfigByKey(key);
			//遍历变更记录
			for(CompleteReport cr : crs){
				//遍历cpt
				for(Object obj:jsonArray){
					net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(obj);
					CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
					 if(reportVersion !=null){
						   //记录特定字符索引  
						   int beginIndex = dto.getReportlet().indexOf("/"); 
						   int endIndex = dto.getReportlet().lastIndexOf("/");
					       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
						   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
					   }
					result = "{reportlet:'"+dto.getReportlet()+"',crId:'"+cr.getCrId()+"',projId:'"+cr.getProjId()
						   +"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
					list.add(result);
				}
			}
		}
		return list;
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
			CompleteReport cr=completeReportDao.get(cwId);
			if(cr!=null){
				//项目负责人（4）projectLeader
				//现场代表（4）builder
				//现场监理（3）suJgj
				//总监（3）suCse
				//施工员（1）construction
				//项目经理（2）cuPm
				//安全员（2）safetyOfficer
				//质检员（2）qualitativeCheckMember
				
				
				//施工员（1）construction
				if(StringUtils.isNotBlank(cr.getConstruction())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}
				
				//项目经理（2）cuPm
				if(StringUtils.isNotBlank(cr.getCuPm())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}
				
				///安全员（2）safetyOfficer
				if(StringUtils.isNotBlank(cr.getSafetyOfficer())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SAFTYOFFICER.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}
				
				//质检员（2）qualitativeCheckMember
				if(StringUtils.isNotBlank(cr.getQualitativeCheckMember())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}
				
				//现场监理（3）suJgj
				if(StringUtils.isNotBlank(cr.getSuJgj())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}else{
					//工程是否已验收，未验收将监理通知置为无效，已验收则激活
					Project project = projectDao.get(cr.getProjId());
					if(ProjStatusEnum.TO_PRE_INSPECTION.getValue().compareTo(project.getProjStatusId()) < 1){
						signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),SignStateEnum.READY_SIGN.getValue());
					}else{//没有预验收
						signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
								cr.getCrId(), cr.getProjId(),SignStateEnum.NO_SIGN.getValue());
					}
				}
				
				//总监（3）suCse
				if(StringUtils.isNotBlank(cr.getSuCse())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUCSE.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}
				
				//builder
				if(StringUtils.isNotBlank(cr.getBuilder())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}
				
				//项目负责人（4）projectLeader
				if(StringUtils.isNotBlank(cr.getProjectLeader())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.PROJECT_LEADER.getValue(), SignDataTypeEnum.COMPLETE_REPORT.getValue(),
							cr.getCrId(), cr.getProjId(),signState);
				}

			}
		}
		
	}

	@Override
	public CompleteReport findByProjId(String projId, String cfFlag) {
		return completeReportDao.findByProjId(projId,cfFlag);
	}

	@Override
	public boolean rollBackCompleteReport(String projId, String fallbackReason) {
		List<CompleteReport> crList = completeReportDao.findByProjId(projId);
		if (crList==null || crList.size()<1) return true;

		String stepId=StepEnum.COMPLETED_REPORT.getValue();
		for (CompleteReport cr:crList) {
			if ("1".equals(cr.getCrFlag())){
				cr.setCrFlag("0");//作废
				completeReportDao.saveOrUpdate(cr);
				abandonedRecordService.delBackupsThisTableSignatureAndNotice(cr.getCrId(),projId,stepId,fallbackReason);
			}
		}

		return false;
	}

	@Override
	public boolean signCompleted(String projId) {
		CompleteReport cr = completeReportDao.findByProjId(projId,"1");
		Project pro = projectDao.get(projId);
		//监理单位可能为空
		if(cr!=null && StringUtils.isNotBlank(cr.getConstruction())
				&& StringUtils.isNotBlank(cr.getCuPm())
				&& StringUtils.isNotBlank(cr.getSafetyOfficer())
				&& StringUtils.isNotBlank(cr.getQualitativeCheckMember())
				&&(StringUtils.isBlank(pro.getSuId()) || (StringUtils.isNotBlank(cr.getSuJgj()) && StringUtils.isNotBlank(cr.getSuCse())))
				&& StringUtils.isNotBlank(cr.getBuilder())
				&& StringUtils.isNotBlank(cr.getProjectLeader())){
			//已完成签字
			return true;
		}
		return false;
	}
}
