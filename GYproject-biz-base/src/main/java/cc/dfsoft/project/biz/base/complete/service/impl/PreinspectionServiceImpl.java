package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.complete.dao.CheckItemDao;
import cc.dfsoft.project.biz.base.complete.dao.PreInspectionRecordDao;
import cc.dfsoft.project.biz.base.complete.dao.PreinspectionDao;
import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionListDao;
import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.project.biz.base.complete.entity.PreInspectionRecord;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.complete.service.PreinspectionService;
import cc.dfsoft.project.biz.base.constructmanage.dao.CompleteReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.WorkReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.service.CompleteReportService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.Annotations;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PreinspectionServiceImpl implements PreinspectionService {

	@Resource
	ProjectDao projectDao;
	
	@Resource
	PreinspectionDao preinspectionDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	OperateRecordService operateRecordService;
	
	/**预验收记录*/
	@Resource
	PreInspectionRecordDao preInspectionRecordDao;
	
	/**工作流*/
	@Resource
	WorkFlowService workFlowService;
	
	/**检查项Dao*/
	@Resource
	CheckItemDao checkItemDao;
	
	/**预验收*/
	@Resource
	SignatureService signatureService;
	
	/**安装合同Dao*/
	@Resource
	ContractDao contractDao;
	
	/**施工合同Dao*/
	@Resource
	SubContractDao subContractDao;
	
	/**开工报告Dao*/
	@Resource
	WorkReportDao workReportDao;
	
	/**竣工报告*/
	@Resource
	CompleteReportDao completeReportDao;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	CompleteReportService completeReportService;
	@Resource
	SelfInspectionListDao selfInspectionListDao;
	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	SynchronizedService synchronizedService;




	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String savePreinspection(Preinspection preinspection,String stepID) throws Exception {
		boolean flag = false;
		if(StringUtil.isBlank(preinspection.getPiId())){
			flag = true;
			String piId=IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);//生成唯一ID
			preinspection.setPiId(piId);
			preinspection.setIsDel("1");
			Preinspection isNotNullOfPre = preinspectionDao.findByProjId(preinspection.getProjId());  //查找是否已有记录，预验收只允许有一条记录
			if(isNotNullOfPre != null ){
				return "already";  //已有预验收记录则返回提示刷新页面
			}
		}
		preinspection.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
		preinspection.setPiDate(preinspectionDao.getDatabaseDate());//预检日期
		preinspectionDao.saveOrUpdate(preinspection);
		//签字类型
		List<Signature> signs=preinspection.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.PRE_INSPECTION.getValue());
			}
			preinspection.setSign(signs);
		}
		//暂存
		if(preinspection.getFlag().equals("0")){
			//保存预验收记录
			List<PreInspectionRecord> selfInspectionRecords = preinspection.getPreInspectionRecords();
			if(selfInspectionRecords!=null && selfInspectionRecords.size()>0){
				for(PreInspectionRecord sir:selfInspectionRecords){
					sir.setPiId(preinspection.getPiId());
					if(StringUtils.isBlank(sir.getPirId())){
						sir.setPirId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
					 }				
					sir.setProjId(preinspection.getProjId());
				}
				if (flag) {
					preInspectionRecordDao.batchInsertObjects(selfInspectionRecords);
				}else{
					preInspectionRecordDao.deleteObjects(preinspection.getPiId());
					preInspectionRecordDao.batchInsertObjects(selfInspectionRecords);
				}
			}
			//保存签字
			signatureService.saveOrUpdateSign("menuId+menuNane+6",preinspection.getSign(), preinspection.getProjId(), preinspection.getPiId(), preinspection.getClass().getName(),flag);
			return Constants.OPERATE_RESULT_SUCCESS;
		}
		List<PreInspectionRecord> selfInspectionRecords = preinspection.getPreInspectionRecords();
		if(selfInspectionRecords!=null && selfInspectionRecords.size()>0){
			for(PreInspectionRecord sir:selfInspectionRecords){
				sir.setPiId(preinspection.getPiId());
				if(StringUtils.isBlank(sir.getPirId())){
					sir.setPirId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
				 }				
				sir.setProjId(preinspection.getProjId());
			}
			if (flag) {
				preInspectionRecordDao.batchInsertObjects(selfInspectionRecords);
			}else{
				preInspectionRecordDao.deleteObjects(preinspection.getPiId());
				preInspectionRecordDao.batchInsertObjects(selfInspectionRecords);
			}
		}
		Project project = projectDao.get(preinspection.getProjId());
		String todoer="";
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
		//推送
		//String statusId=workFlowService.queryProjStatusId(project.getCorpId(),project.getContributionMode(),WorkFlowActionEnum.PRE_INSPECTION.getActionCode(), true);
		String statusId=workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(),project.getContributionMode(), WorkFlowActionEnum.PRE_INSPECTION.getActionCode(), true);
		if(StringUtil.isNotBlank(preinspection.getIsBack()) && preinspection.getIsBack().equals("1")){//回退到上一步骤
			statusId=workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(),project.getContributionMode(), WorkFlowActionEnum.PRE_INSPECTION.getActionCode(), false);
			//删除竣工报告
			completeReportDao.deleteByProjId(project.getProjId(),"0");
			signNoticeService.deleteByProjIdAndType(project.getProjId(), SignDataTypeEnum.COMPLETE_REPORT.getValue());  //回退时删除竣工报告签字通知
			//删除自检
			selfInspectionListDao.deleteByProjID(project.getProjId(),"0");
			
			//删除当前预验收信息
			preinspectionDao.deleteByprojId(project.getProjId(),"0");
			signNoticeService.deleteByProjIdAndType(project.getProjId(), SignDataTypeEnum.PRE_INSPECTION.getValue());  //回退时删除预验收签字通知
			
			//退回上一个待办人
			FallbackApply fa=new FallbackApply();
			fa.setOriginalStepId(stepID);
			fa.setFallbackStepId(StepEnum.SELF_CHECK.getValue());
			todoer=operateRecordService.rollBackHandle(preinspection.getProjId(),fa);

		}else{//下一工作流
			//将竣工报告的监理签字通知激活
			preinspectionDao.saveOrUpdate(preinspection);
			//下一个待办人
			todoer=operateRecordService.createOperateRecord(orId, preinspection.getProjId(), preinspection.getProjNo(), StepEnum.PRE_INSPECTION.getValue(), StepEnum.PRE_INSPECTION.getMessage(), "");
			//获取竣工报告
			CompleteReport completeReport = completeReportService.findByProjId(project.getProjId(),"1");
			if(completeReport!=null && StringUtil.isNotBlank(completeReport.getCrId())){
				completeReportService.saveSignNotice(completeReport.getCrId());
			}
			//实际竣工日期
			//project.setCompletedDate(preinspection.getActualCompleteDate());
			//保存签字
			signatureService.saveOrUpdateSign("menuId+menuNane+7",preinspection.getSign(), preinspection.getProjId(), preinspection.getPiId(), preinspection.getClass().getName(),flag);
			//推送时将签字通知置为已签
			signNoticeService.signNoticeSetInvalid(preinspection.getPiId(), preinspection.getProjId(), SignDataTypeEnum.PRE_INSPECTION.getValue(), null, SignStateEnum.ALREADY_SIGN.getValue());


			//推送时调用鸿巨接口（预验收信息新增/修改）返回信息
			ResultMsg resultMsg = synchronizedService.callSynchronizedPreinspection(preinspection.getProjId(), WebServiceTypeEnum.PREINSPECTION.getValue());
			if (resultMsg!=null && resultMsg.getCode()!=0){
				throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
			}

		}
		project.setToDoer(todoer);	//待办人
		project.setProjStatusId(statusId); 
		projectDao.update(project);


		
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 详述
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public Preinspection findByProjId(String projId) throws ParseException {
		
		Project pro = projectDao.get(projId);
		List<Preinspection> preList=preinspectionDao.findById(projId);
		ConstructionPlan cp = constructionPlanDao.viewPlanById(projId);
		Contract con=contractDao.viewContractByprojId(projId);
		SubContract sc=subContractDao.findSubContractByprojId(projId);
		
		List<WorkReport> workReport = workReportDao.findByProjId(projId, "");
		//竣工报告-实际竣工日期-作为预验收中的实际竣工日期
		List<CompleteReport> crs = completeReportDao.findByProjId(projId);
		CompleteReport cr = new CompleteReport();
		if(crs!=null && crs.size()>0){
			cr=crs.get(0);
		}
		if(preList!=null && preList.size()>0){
			preList.get(0).setProjectTypeDes(pro.getProjectTypeDes());
			preList.get(0).setContributionModeDes(pro.getContributionModeDes());
			preList.get(0).setCorpName(pro.getCorpName());
			preList.get(0).setDeptName(pro.getDeptName());
			preList.get(0).setProjAddr(pro.getProjAddr());
			preList.get(0).setProjScaleDes(pro.getProjScaleDes());
			preList.get(0).setCpArriveDate(cp.getCpArriveDate());//计划下达时间
			if(workReport!=null && workReport.size()>0){
				preList.get(0).setPlannedStartDate(workReport.get(0).getPlannedStartDate());//开工日期
				preList.get(0).setPlanCompleteDate(workReport.get(0).getPlannedEndDate());//竣工日期
			}
			if(cr!=null && cr.getRealEndDate()!=null){
				preList.get(0).setActualCompleteDate(cr.getRealEndDate());//实际竣工日期
			}
			return preList.get(0);
		}
		Preinspection pre = new Preinspection();
		
		pre.setProjId(projId);
		if(pro!=null){
			pre.setProjName(pro.getProjName());
			pre.setProjNo(pro.getProjNo());
			pre.setProjectTypeDes(pro.getProjectTypeDes());
			pre.setContributionModeDes(pro.getContributionModeDes());
			pre.setCorpName(pro.getCorpName());
			pre.setDeptName(pro.getDeptName());
			pre.setProjScaleDes(pro.getProjScaleDes());
			pre.setProjAddr(pro.getProjAddr());
		}
		
		if(cp!=null){
			pre.setSuName(cp.getSuName());//监理单位
			pre.setCmoName(cp.getCuName());//施工单位
			pre.setBuilder(cp.getBuilder());
			pre.setCpArriveDate(cp.getCpArriveDate());//计划下达时间
		}
		if(workReport!=null && workReport.size()>0){
			pre.setPlannedStartDate(workReport.get(0).getPlannedStartDate());//开工日期
			pre.setPlanCompleteDate(workReport.get(0).getPlannedEndDate());//竣工日期
			
		}
		if(cr!=null && cr.getRealEndDate()!=null){
			pre.setActualCompleteDate(cr.getRealEndDate());//实际竣工日期
		}
		
		
		if(con!=null){
			pre.setConNo(con.getConNo());
		}
		
		if(sc!=null){
			pre.setScNo(sc.getScNo());
		}
		return pre;
	}
	
	/**
	 * 自检单列表查询
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> querySelInspection(PreinspectionReq req) throws ParseException {
		//return preinspectionDao.querySelInspection(req);

		Map<String, Object> map=preinspectionDao.querySelInspection(req);
		
		List<Preinspection> list=(List<Preinspection>) map.get("data");
		
		if(list!=null && list.size()>0){
			for(Preinspection preinspection:list){
				
				
					Project project=projectDao.get(preinspection.getProjId());
					if(project!=null){
						preinspection.setProjectType(project.getProjectType());
						preinspection.setCorpId(project.getCorpId());  //设置corpId
					}
				}
			
		}
		
		return map;		
	}
	
	
	/**
	 * 自检单打印标记
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void signSelInspectionPrint(String projId) {
		List<Preinspection> preList=preinspectionDao.findById(projId);
		if(preList!=null && preList.size()>0){
			Preinspection pre=preList.get(0);
			pre.setIsPrint(ContractIsPrintEnum.ALREADY_PRINT.getValue());
			preinspectionDao.update(pre);
		}
		
	}
	
	
	/**
	 * 查询质量自检记录详述
	 * @param projId
	 * @return
	 */
	@Override
	public Map<String, String> viewPreInspectionRecordQuqlity(String projId) {
		
		Project pro=projectDao.get(projId);
		
		List<PreInspectionRecord> courtyardRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"3");// 庭院质量自检
		//查检查项
		List<CheckItem> checkItems1 = checkItemDao.findByType("3","3",pro.getCorpId());
		
		Map<String,String> map = new HashMap<String,String>();
		
		if(courtyardRecords!=null&&courtyardRecords.size()>0){
			//如果只保存部分
			for(PreInspectionRecord ser :courtyardRecords){
				if(ser.getSirResult()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult()); 
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
				}else{
					map.put(ser.getCiId()+"_ciId",ser.getCiId());
					map.put(ser.getCiId()+"_sirType",ser.getSirType());
				}
				
			}
		}else{
			//初次加载没有保存质量自检项
			for(int i=0;i<checkItems1.size();i++){
				map.put(checkItems1.get(i).getId()+"_ciId", checkItems1.get(i).getId());
				map.put(checkItems1.get(i).getId()+"_sirType", checkItems1.get(i).getCheckType());
			}
		}
		
		
		List<PreInspectionRecord> indoorsRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"4");// 户内质量自检
		//查检查项
		List<CheckItem> checkItems2 = checkItemDao.findByType("3","4",pro.getCorpId());
		if(indoorsRecords!=null && indoorsRecords.size()>0){
			//如果只保存部分
			for(PreInspectionRecord ser :indoorsRecords){
				if(ser.getSirResult()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult()); 
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
				}else{
					map.put(ser.getCiId()+"_ciId",ser.getCiId());
					map.put(ser.getCiId()+"_sirType",ser.getSirType());
				}
				
			}
		}else{
			//初次加载没有保存质量自检项
			for(int i=0;i<checkItems2.size();i++){
				map.put(checkItems2.get(i).getId()+"_ciId", checkItems2.get(i).getId());
				map.put(checkItems2.get(i).getId()+"_sirType", checkItems2.get(i).getCheckType());
			}
		}
		
		
		List<PreInspectionRecord> surgeTankRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"5");// 调压箱质量自检
		//查检查项
		List<CheckItem> checkItems3 = checkItemDao.findByType("3","5",pro.getCorpId());
		if(surgeTankRecords!=null && surgeTankRecords.size()>0){
			//如果只保存部分
			for(PreInspectionRecord ser :surgeTankRecords){
				if(ser.getSirResult()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult()); 
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
				}else{
					map.put(ser.getCiId()+"_ciId",ser.getCiId());
					map.put(ser.getCiId()+"_sirType",ser.getSirType());
				}
				
			}
		}else{
			//初次加载没有保存质量自检项
			for(int i=0;i<checkItems3.size();i++){
				map.put(checkItems3.get(i).getId()+"_ciId", checkItems3.get(i).getId());
				map.put(checkItems3.get(i).getId()+"_sirType", checkItems3.get(i).getCheckType());
			}
		}
		
		List<PreInspectionRecord> meteringRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"9");// 计量设备
		//查检查项
		List<CheckItem> checkItems4 = checkItemDao.findByType("3","9",pro.getCorpId());
		if(meteringRecords!=null && meteringRecords.size()>0){
			//如果只保存部分
			for(PreInspectionRecord ser :meteringRecords){
				if(ser.getSirResult()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult()); 
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
				}else{
					map.put(ser.getCiId()+"_ciId",ser.getCiId());
					map.put(ser.getCiId()+"_sirType",ser.getSirType());
				}
				
			}
		}else{
			//初次加载没有保存质量自检项
			for(int i=0;i<checkItems4.size();i++){
				map.put(checkItems4.get(i).getId()+"_ciId", checkItems4.get(i).getId());
				map.put(checkItems4.get(i).getId()+"_sirType", checkItems4.get(i).getCheckType());
			}
		}
		
		return map;
	}
	
	/**
	 * 查询资料预验收记录详述
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, String> viewPreInspectionRecordMaterial(String projId) {
		Project pro=projectDao.get(projId);
		//资料预验收
		List<PreInspectionRecord> coverRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"6");
		
		List<CheckItem> checkItems1 =  checkItemDao.findByType("4","6",pro.getCorpId());
		Map<String,String> map = new HashMap<String,String>();
		if(coverRecords!=null&&coverRecords.size()>0){
			for(PreInspectionRecord ser :coverRecords){
				//已保存过
				if(ser.getSirResult()!=null||ser.getSirRemark()!=null||ser.getSirNum()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult());
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
					map.put(ser.getCiId()+"_sirNum", ser.getSirNum());
				}else{
					//未保存过
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
				}
			}
		}else{
			//初次加载没有保存资料自检
			for(int i=0;i<checkItems1.size();i++){
				map.put(checkItems1.get(i).getId()+"_ciId", checkItems1.get(i).getId());
				map.put(checkItems1.get(i).getId()+"_sirType", checkItems1.get(i).getCheckType());
			}
		}
		
		//竣工图
		List<PreInspectionRecord> drawingRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"7");
		
		List<CheckItem> checkItems2 =  checkItemDao.findByType("4","7",pro.getCorpId());
		if(drawingRecords!=null && drawingRecords.size()>0){
			for(PreInspectionRecord ser :drawingRecords){
				//已保存过
				if(ser.getSirResult()!=null||ser.getSirRemark()!=null||ser.getSirNum()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult());
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
					map.put(ser.getCiId()+"_sirNum", ser.getSirNum());
				}else{
					//未保存过
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
				}
			}
		}else{
			//初次加载没有保存资料自检
			for(int i=0;i<checkItems2.size();i++){
				map.put(checkItems2.get(i).getId()+"_ciId", checkItems2.get(i).getId());
				map.put(checkItems2.get(i).getId()+"_sirType", checkItems2.get(i).getCheckType());
			}
		}
		
		List<PreInspectionRecord> dataRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"8");
		
		List<CheckItem> checkItems3 =  checkItemDao.findByType("4","8",pro.getCorpId());
		if(dataRecords!=null && dataRecords.size()>0){
			for(PreInspectionRecord ser :dataRecords){
				//已保存过
				if(ser.getSirResult()!=null||ser.getSirRemark()!=null||ser.getSirNum()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult());
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
					map.put(ser.getCiId()+"_sirNum", ser.getSirNum());
				}else{
					//未保存过
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
				}
			}
		}else{
			//初次加载没有保存资料自检
			for(int i=0;i<checkItems3.size();i++){
				map.put(checkItems3.get(i).getId()+"_ciId", checkItems3.get(i).getId());
				map.put(checkItems3.get(i).getId()+"_sirType", checkItems3.get(i).getCheckType());
			}
		}
		
		return map;
	}
	
	/**
	 * 查询材料预验收记录详述
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, String> viewPreInspectionRecordData(String projId) {
		Project pro=projectDao.get(projId);
		//材料预验收
		List<PreInspectionRecord> dataRecords =preInspectionRecordDao.findQuqlityByProjIdType(projId,"9");
		
		List<CheckItem> checkItems1 =  checkItemDao.findByType("5","9",pro.getCorpId());
		Map<String,String> map = new HashMap<String,String>();
		if(dataRecords!=null && dataRecords.size()>0){
			for(PreInspectionRecord ser :dataRecords){
				//已保存过
				if(ser.getSirResult()!=null||ser.getSirRemark()!=null||ser.getSirNum()!=null){
					map.put(ser.getCiId()+"_pirId", ser.getPirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult());
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
					map.put(ser.getCiId()+"_sirNum", ser.getSirNum());
				}else{
					//未保存过
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
				}
			}
		}else{
			//初次加载没有保存资料自检
			for(int i=0;i<checkItems1.size();i++){
				map.put(checkItems1.get(i).getId()+"_ciId", checkItems1.get(i).getId());
				map.put(checkItems1.get(i).getId()+"_sirType", checkItems1.get(i).getCheckType());
			}
		}
		return map;
	}

	/**
	 * 根据工程ID查询预验收信息并组装预验收单打印报表
	 * @author liaoyq
	 * @createTime 2017-11-27
	 * @param projId
	 * @return
	 */
	@Override
	public String findPrintDataByProjId(String projId,String type) {
		String result ="";
		//根据工程ID查询预验收单
		Preinspection preinspection = preinspectionDao.findByProjId(projId);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		String arrayStr = CompletionDataPrintEnum.PRE_INSPECTION.getCptUrl();
		//2、使用JSONArray
		net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && preinspection!=null && project !=null){
			net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			 String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
			 Object reportVersion = Constants.getSysConfigByKey(key);
			   if(reportVersion !=null){
				   //记录特定字符索引  
				   int beginIndex = dto.getReportlet().indexOf("/"); 
				   int endIndex = dto.getReportlet().lastIndexOf("/");
			       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
				   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
			   }
			result = "{reportlet:'"+dto.getReportlet()+"',piId:'"+preinspection.getPiId()+"',projId:'"+preinspection.getProjId()
				   +"',path:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
			return result;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
				//suJgj现场监理
				//projManager项目经理
				
				if(StringUtils.isNotBlank(cwId)){
					Preinspection list=preinspectionDao.get(cwId);
					if(list!=null){
						if(StringUtils.isNotBlank(list.getSuFieldJgj())){
							//现场监理通知置为无效
							signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.PRE_INSPECTION.getValue(),
									list.getPiId(), list.getProjId(),signState);
						}
						if(StringUtils.isNotBlank(list.getProjManager())){
							//项目经理通知置为无效
							signNoticeService.saveThisSignNotice(SignPostEnum.CU_PM.getValue(), SignDataTypeEnum.PRE_INSPECTION.getValue(),
									list.getPiId(), list.getProjId(),signState);
						}
						
						
					}
				}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean modifyPreinspection(Preinspection preinspection) throws Exception {


		ConstructionPlan cp = constructionPlanDao.viewPlanById(preinspection.getProjId());
		if (cp!=null){
			cp.setCpArriveDate(preinspection.getCpArriveDate());//计划下达时间
			constructionPlanDao.saveOrUpdate(cp);
		}


		List<WorkReport> workReport = workReportDao.findByProjId(preinspection.getProjId(), "");
		if (workReport!=null && workReport.size()>0){
			workReport.get(0).setPlannedStartDate(preinspection.getPlannedStartDate());//计划开工日期
			workReport.get(0).setPlannedEndDate(preinspection.getPlanCompleteDate());//计划竣工日期
			workReportDao.saveOrUpdate(workReport.get(0));
		}


		List<CompleteReport> crs = completeReportDao.findByProjId(preinspection.getProjId());
		if (crs!=null && crs.size()>0) {//竣工报告
			if (crs.get(0) != null) {
				crs.get(0).setScPlannedStartDate(preinspection.getCpArriveDate());//计划开工日期
				crs.get(0).setRealStartDate(preinspection.getPlannedStartDate());//实际开工日期
				crs.get(0).setScPlannedEndDate(preinspection.getPlanCompleteDate());//计划竣工日期
				crs.get(0).setRealEndDate(preinspection.getActualCompleteDate());//实际竣工日期
				completeReportDao.saveOrUpdate(crs.get(0));
			}
		}

		Preinspection p = preinspectionDao.get(preinspection.getPiId());
		if (p!=null){//预验收表
//			p.setCpArriveDate(preinspection.getCpArriveDate());//计划下达日期
//			p.setPlannedStartDate(preinspection.getPlannedStartDate());//实际开工日期
//			p.setPlanCompleteDate(preinspection.getPlanCompleteDate());//计划竣工日期
//			p.setActualCompleteDate(preinspection.getActualCompleteDate());//实际竣工日期
			p.setCesDate(preinspection.getCesDate());//现场监理签字日期
			p.setPmDate(preinspection.getPmDate());//项目经理签字日期

			preinspectionDao.saveOrUpdate(p);
		}
		return true;
	}









	@Override
	public boolean rollBackContainsPreinspection(String projId, String fallbackReason) {
		List<Preinspection> preList = preinspectionDao.findById(projId);
		if (preList==null || preList.size()<1) return true;

		String stepId=StepEnum.PRE_INSPECTION.getValue();
		for (Preinspection pre:preList) {
			if ("1".equals(pre.getIsDel())){
				pre.setIsDel("0");//作废
				preinspectionDao.saveOrUpdate(pre);
				abandonedRecordService.delBackupsThisTableSignatureAndNotice(pre.getPiId(),projId,stepId,fallbackReason);
			}
		}
		return false;
	}

}
