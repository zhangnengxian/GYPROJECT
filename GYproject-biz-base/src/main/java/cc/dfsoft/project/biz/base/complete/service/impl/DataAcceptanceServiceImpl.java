package cc.dfsoft.project.biz.base.complete.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.complete.dao.DataAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.dao.DataAcceptanceRecordDao;
import cc.dfsoft.project.biz.base.complete.entity.DataAcceptance;
import cc.dfsoft.project.biz.base.complete.entity.DataAcceptanceRecord;
import cc.dfsoft.project.biz.base.complete.service.DataAcceptanceService;
import cc.dfsoft.project.biz.base.constructmanage.service.CompleteReportService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.AccessoryItemDao;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectSignDao;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjectSignTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.Annotations;
import cc.dfsoft.uexpress.common.util.IDUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资料验收服务接口实现
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DataAcceptanceServiceImpl implements DataAcceptanceService{
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**资料验收申请*/
	@Resource
	DataAcceptanceDao dataAcceptanceDao;
	
	/**服务接口*/
	@Resource
	WorkFlowService workFlowService;
	
	/**资料验收记录Dao*/
	@Resource
	DataAcceptanceRecordDao dataAcceptanceRecordDao;
	
	/**资料标准*/
	@Resource
	AccessoryItemDao accessoryItemDao;
	
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;
	
	/**操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;

	@Resource
	AccrualsRecordDao accrualsRecordDao;

	@Resource
	ProjectSignDao projectSignDao;
	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	CompleteReportService completeReportService;
	/**
	 * 根据工程id查询详述
	 * @author fuliwei
	 * @createTime 2017年8月3日
	 * @param 
	 * @return
	 */
	@Override
	public Project findProjectById(String projId) {
		Project pro=projectDao.get(projId);
		ConstructionPlan con = constructionPlanDao.viewPlanById(projId);
		DataAcceptance da=dataAcceptanceDao.findByProjId(projId);
		if(da!=null){
			pro.setApplyDate(da.getApplyDate());
			pro.setNote(da.getNote());
			pro.setDaId(da.getDaId());
		}
		
		pro.setCuName(con.getCuName());
		pro.setSuName(con.getSuName());
		
		return pro;
	}
	
	/**
	 * 根据工程id查询资料验收申请表
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	@Override
	public DataAcceptance findByProjId(String projId) {
		return dataAcceptanceDao.findByProjId(projId);
	}
	
	/**
	 * 保存资料申请
	 * @author fuliwei
	 * @createTime 2017年8月8日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveDataAcceptance(DataAcceptance da) {
		if(StringUtils.isBlank(da.getDaId())){
			da.setDaId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		//保存资料验收表
		dataAcceptanceDao.saveOrUpdate(da);
		
		List<DataAcceptanceRecord> list=new ArrayList<DataAcceptanceRecord>();
		DataAcceptanceRecord dar;
		if(StringUtils.isNotBlank(da.getCaiIds())){
			String [] caiIds = da.getCaiIds().split(",");
			for(String caiId : caiIds){
				String daid=da.getDaId();
				//删除历史数据
				dar=new DataAcceptanceRecord();
				dataAcceptanceRecordDao.deleteByDaId(daid);
				CollectAccessoryItem cai=accessoryItemDao.get(caiId);
				dar.setDarId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
				dar.setCaiId(cai.getCaiId());				//标准id
				dar.setDataName(cai.getAccessoryName());	//文件名称
				dar.setDataType(cai.getDataType());			//资料类型
				dar.setProjectTypeId(cai.getProjTypeId());  //工程类型 id
				dar.setProjId(da.getProjId());
				dar.setDaId(da.getDaId());
				list.add(dar);
			}
			//保存资料记录
			dataAcceptanceRecordDao.batchInsertObjects(list);
		}

		//点击推送
		if("1".equals(da.getFlag())){
			//判断如果有效竣工报告未完成签字，则不能发起申请
			if(!completeReportService.signCompleted(da.getProjId())){
				return Constants.OPERATE_RESULT_DATA_INCOMPLETE;
			}
			//更新工程信息
			Project pro=projectDao.get(da.getProjId());         //通过工程id查找Project
			//生成工程状态
			//String statusId=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.UNION_PRE_INSPECTION.getActionCode());
			//String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getContributionMode(),WorkFlowActionEnum.COMPLETION_DATA_APPLY.getActionCode(), true);
			String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.COMPLETION_DATA_APPLY.getActionCode(), true);
			pro.setProjStatusId(statusId); 								//设置工程状态
			

			//标记生成款项未结清工程
			ChargeReq chargeReq = new ChargeReq();
			chargeReq.setProjId(da.getProjId());
			chargeReq.setArFlag(ARFlagEnum.RECEIVE_ACCOUNT.getValue());
			chargeReq.setArOver("1");
			Map<String,Object> map =accrualsRecordDao.queryAccrualsRecord(chargeReq);
			List<AccrualsRecord> accrualsRecords = (List<AccrualsRecord>) map.get("data");
			if(accrualsRecords!=null && accrualsRecords.size()>0){
				ProjectSign projectSign = projectSignDao.findOnly(da.getProjId(),ProjectSignTypeEnum.INCOMPLETE_COST.getValue());
				if(projectSign==null){
					ProjectSign projectSign1 = new ProjectSign();
					projectSign1.setProjId(da.getProjId());
					projectSign1.setSignType(ProjectSignTypeEnum.INCOMPLETE_COST.getValue());//款项未结清工程
					projectSign1.setStatus(IsSignEnum.IS_SIGN.getValue());
					projectSignDao.save(projectSign1);
				}
			}

			//生成操作记录
			String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
			String todoer=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.COMPLETION_DATA_APPLY.getValue(), StepEnum.COMPLETION_DATA_APPLY.getMessage(), "");
			
			pro.setToDoer(todoer);//待办人
			projectDao.saveOrUpdate(pro);
			 //生成审核通知
				/* Notice notice=noticeDao.findByProjIdAndType(pro.getProjId(), NoticeAuditTypeEnum.DATA_AUDIT.getValue());//资料审核
				 if(notice!=null){
					 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());//将通知置为有效;
					 notice.setGrade(NoticeMenuEnum.DATA_AUDIT1.getGrade());	 //一级审核
					 notice.setNoticeContent(NoticeMenuEnum.DATA_AUDIT1.getMessage());//待踏勘审核一级
					 notice.setNoticeTime(noticeDao.getDatabaseDate());
				 }else{
					 notice=new Notice();
					 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
					 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());		//将通知置为有效;
					 notice.setGrade(NoticeMenuEnum.DATA_AUDIT1.getGrade());	 		//一级审核
					 notice.setAuditType(NoticeMenuEnum.DATA_AUDIT1.getType());
					 notice.setNoticeTime(noticeDao.getDatabaseDate());
					 notice.setNoticeTitle("审核通知");
					 notice.setNoticeContent(NoticeMenuEnum.DATA_AUDIT1.getMessage());//待资料审核一级
					 notice.setNoticeType("2");
					 notice.setProjId(pro.getProjId());
					 notice.setCorpId(pro.getCorpId());
				 }
				 noticeDao.saveOrUpdate(notice);*/
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public boolean rollBackContainsDataAcceptance(String projId, String fallbackReason) {
		DataAcceptance da = dataAcceptanceDao.findByProjId(projId);
		if (da==null) return true;

		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(DataAcceptance.class, "projId");
		criteriaMap.put(t_projId,projId);
		String stepId=StepEnum.COMPLETION_DATA_APPLY.getValue();
		String tableName = Annotations.getClassTableAnNameVal(DataAcceptance.class);
		String thisTableOrigData = abandonedRecordService.getThisTableOrigData(tableName, criteriaMap);
		abandonedRecordService.saveAbandonedRecord(da.getDaId(),projId,stepId,fallbackReason,thisTableOrigData);

		//更新资料申请表
		da.setApplyDate(null);//申请时间
		dataAcceptanceDao.saveOrUpdate(da);

		return true;
	}

}
