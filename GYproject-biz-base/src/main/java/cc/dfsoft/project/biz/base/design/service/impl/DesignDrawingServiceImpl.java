package cc.dfsoft.project.biz.base.design.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dao.DispatchInfoDao;
import cc.dfsoft.project.biz.base.design.dao.DrawingsDirectoryDao;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.DispatchInfo;
import cc.dfsoft.project.biz.base.design.entity.DrawingsDirectory;
import cc.dfsoft.project.biz.base.design.service.DesignDrawingService;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlan;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;
import cc.dfsoft.project.biz.base.plan.enums.ProjectOperateEnum;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanDetailService;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanService;
import cc.dfsoft.project.biz.base.project.dao.NoticeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Notice;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.NoticeAuditTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeMenuEnum;
import cc.dfsoft.project.biz.base.project.enums.NoticeStateEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.JsonUtils;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DesignDrawingServiceImpl implements DesignDrawingService{
	/** 图纸目录Dao */
	@Resource
	DrawingsDirectoryDao drawingsDirectoryDao;
	@Resource
	DesignInfoDao designInfoDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	DispatchInfoDao dispatchInfoDao;
	
	/** 采购计划明细服务*/
	@Resource
	ProcurementPlanDetailService procurementPlanDetailService;
	
	/** 采购计划服务*/
	@Resource
	ProcurementPlanService procurementPlanService;
	
	/**客户Dao*/
	@Resource
	StaffDao staffDao;
	
	/**工作流服务接口*/
	@Resource
	WorkFlowService workFlowService;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	/**通知Dao*/
	@Resource
	NoticeDao noticeDao;
	
	@Override
	public Map<String, Object> queryDrawDirectory(DrawingsDirectoryReq pageSortReq) {
		return drawingsDirectoryDao.queryDrawDirectory(pageSortReq);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insertDrawDirectory(DrawingsDirectory drawingsDirectory) {
		drawingsDirectory.setDrawDirectId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		String diId="";
		if(StringUtils.isNotBlank(drawingsDirectory.getProjId())){
			//根据工程id获取设计信息
			DesignInfo designInfo= designInfoDao.queryInfoByProjId(drawingsDirectory.getProjId());
		    if(null!=designInfo){
		    	diId=designInfo.getDiId();
		    }
		}
		drawingsDirectory.setDiId(diId);//设计id
		drawingsDirectoryDao.save(drawingsDirectory);
	}

	public String judgeRepeat(DrawingsDirectory drawingsDirectory) {
		List<DrawingsDirectory> list=drawingsDirectoryDao.queryAllDrawDirect(drawingsDirectory);
		String drawNo="";
		String drawName="";
		if(null!=list&&list.size()>0){
			for(DrawingsDirectory draw:list){
			  if(drawingsDirectory.getDrawingNo().equals(draw.getDrawingNo())){
				  drawNo="drawNo";
			  }
			  if(drawingsDirectory.getDrawDirect().equals(draw.getDrawDirect())){
				  drawName="drawName";
			  }
			}
		}
		return drawNo+drawName;
	}
	
	/**
	 * 设计出图确认
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateState(ProjectQueryReq  proReq) {
		 Project pro= projectDao.get(proReq.getProjId());
		 
		 //保存设计信息
		 DesignInfo di=designInfoDao.queryInfoByProjId(proReq.getProjId());
		 if(di==null){
			 //创建设计信息
			 di = new DesignInfo();
			 BeanUtil.copyNotNullProperties(di, pro);
			 di.setDiId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		 }
		 if(di!=null){
			 di.setDesignFee(proReq.getDesignFee());//设计费
			 di.setDesginRemark(proReq.getRemark());//备注
		 }
		 
		 
		 //String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getContributionMode(),WorkFlowActionEnum.DESIGN_DRAWING.getActionCode(), true);
		 String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.DESIGN_DRAWING.getActionCode(), true);
		 pro.setProjStatusId(statusId);
		
		// di.setDesigner(pro.getDesigner());
		//不更新设计完成时间，图纸签收时完成设计
		//designInfo.setDuCompleteDate(this.getDatabaseDate());
		 designInfoDao.saveOrUpdate(di);
		 
		 //形成操作记录
		 String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);//生成唯一ID
		 String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.DESIGN_DRAWING.getValue(), StepEnum.DESIGN_DRAWING.getMessage(), "");
		 
		 pro.setToDoer(toder);//待办人
		 projectDao.update(pro);
	

	 	//生成审核通知
		/* Notice notice=noticeDao.findByProjIdAndType(pro.getProjId(), NoticeAuditTypeEnum.DRAWING_AUDIT.getValue());//图纸审核
		 if(notice!=null){
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());			//将通知置为有效;
			 notice.setGrade(NoticeMenuEnum.DRAWING_AUDIT1.getGrade());			//一级审核
			 notice.setNoticeContent(NoticeMenuEnum.DRAWING_AUDIT1.getMessage());	//待图纸审核一级
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
		 }else{
			 notice=new Notice();
			 notice.setNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			 notice.setNoticeState(NoticeStateEnum.EFFECTIVE.getValue());			//将通知置为有效;
			 notice.setGrade(NoticeMenuEnum.DRAWING_AUDIT1.getGrade());	 		//图纸审核一级审核
			 notice.setAuditType(NoticeMenuEnum.DRAWING_AUDIT1.getType());
			 notice.setNoticeTime(noticeDao.getDatabaseDate());
			 notice.setNoticeTitle("审核通知");
			 notice.setNoticeContent(NoticeMenuEnum.DRAWING_AUDIT1.getMessage());//待图纸审核一级
			 notice.setNoticeType("2");
			 notice.setProjId(pro.getProjId());
			 notice.setCorpId(pro.getCorpId());
		 }
		 
		 noticeDao.saveOrUpdate(notice);*/
	}
	
	/**
	 * 图纸批量导入
	 * @author fuliwei
	 * @createTime 2016-7-25
	 * @param
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertCostSum(JSONArray jsonArr, String projId) {
		List<DrawingsDirectory> list=new ArrayList<DrawingsDirectory>();
		DesignInfo designInfo=designInfoDao.queryInfoByProjId(projId);
		Project pro=projectDao.get(projId);
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			if(StringUtils.isBlank(jo.getString("drawQuantity"))){
				continue;
			}
			DrawingsDirectory dm=JsonUtils.jsonToBean(jo, DrawingsDirectory.class);
			dm.setDrawDirectId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));//图纸目录id
			dm.setProjId(projId);//工程id
			dm.setDiId(designInfo.getDiId());//设计id
			dm.setProjNo(pro.getProjNo());//工程编号
			list.add(dm);
		}
		List<DrawingsDirectory> li=new ArrayList<DrawingsDirectory>();
		li =drawingsDirectoryDao.queryDrawingsDirectoryList(projId);
		if(null!=li&&li.size()>0){
			drawingsDirectoryDao.batchDeleteObjects(li);
		}
		drawingsDirectoryDao.batchInsertObjects(list);		
	}
	/**
	 * 改派保存
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateProject(DesignDispatchDto designDispatchReq) {
		 Project pro= projectDao.get(designDispatchReq.getProjId());
		 pro.setDesigner(designDispatchReq.getSurveyer());
		 pro.setDesignerId(designDispatchReq.getDesignerId());
		 projectDao.update(pro);
		 
		 DispatchInfo dispatchInfo= new DispatchInfo();
		 dispatchInfo.setDisId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		 dispatchInfo.setProjId(pro.getProjId());
		 dispatchInfo.setProjNo(pro.getProjNo());
		 dispatchInfo.setProjName(pro.getProjName());
		 dispatchInfo.setDisDesignerDate(dispatchInfoDao.getDatabaseDate());
		 dispatchInfo.setDesignerId(designDispatchReq.getDesignerId());
		 dispatchInfo.setDesigner(designDispatchReq.getSurveyer());
		 dispatchInfoDao.save(dispatchInfo);
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveProcurePlan(String projId) {
		Project pro= projectDao.get(projId);
		ProcurementPlan procurementPlan = new ProcurementPlan();
		BeanUtil.copyNotNullProperties(procurementPlan, pro);
		//保存采购计划			
		procurementPlan.setProcurPlanId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		procurementPlan.setStatus(ProjectOperateEnum.DESIGN_DRAWING.getValue());
		procurementPlan.setProjId(projId);
		procurementPlan.setBusinessOrderId(projId);
		procurementPlan.setIsExport(ProcurementPlanExport.HAVE_NOT_EXPORT.getValue());//未导出
		procurementPlanService.saveProcurementPlan(procurementPlan);
		//保存采购明细
		procurementPlanDetailService.saveDetail(procurementPlan);
		//pro.setFeedbackState(FeedbackStateEnum.FEEDBACKING.getValue());
		projectDao.update(pro);
		
	}
}
