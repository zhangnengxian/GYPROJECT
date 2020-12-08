package cc.dfsoft.project.biz.base.design.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.dao.ProjectCostSummaryDao;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dao.DrawingMaterialDao;
import cc.dfsoft.project.biz.base.design.dao.DrawingsDirectoryDao;
import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.DrawingMaterial;
import cc.dfsoft.project.biz.base.design.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.design.service.OfficialDrawingService;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.JsonUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class OfficialDrawingServiceImpl implements OfficialDrawingService{
	/***/
	@Resource
	ProjectDao projectDao;
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	/** 图纸目录Dao */
	@Resource
	DrawingsDirectoryDao drawingsDirectoryDao;
	@Resource
	DesignInfoDao designInfoDao;
	@Resource
	ProjectCostSummaryDao projectCostSummaryDao;
	@Resource
	DrawingMaterialDao drawingMaterialDao;
	@Resource
	MaterialListDao materialListDao;
	
	/**工作流服务接口*/
	@Resource
	WorkFlowService workFlowService;
	@Resource
	DocTypeService docTypeService;
	/** 审核记录dao*/
	@Resource
	ManageRecordDao manageRecordDao;
	@Override
	public Map<String, Object> queryMaterialDirectory(DrawingsDirectoryReq pageSortReq) {
		return drawingMaterialDao.queryMaterialDirectory(pageSortReq);
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertCostSum(JSONArray jsonArr, String projId) {
		List<DrawingMaterial> list=new ArrayList<DrawingMaterial>();
		List<MaterialList> listml=new ArrayList<MaterialList>();
		Project pro = projectDao.get(projId);
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			if(StringUtils.isBlank(jo.getString("dmName"))){
				continue;
			}
			DrawingMaterial dm=JsonUtils.jsonToBean(jo, DrawingMaterial.class);
			dm.setDmId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			dm.setProjId(projId);
			dm.setProjNo(pro.getProjNo());
			dm.setOrderNum(i);
			dm.setFlag(StringUtil.isNotBlank(dm.getFlag())?dm.getFlag():MaterialFlagEnum.YES.getValue());
			list.add(dm);
			MaterialList ml=new MaterialList();
			ml.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
			ml.setProjId(projId);
			ml.setProjName(pro.getProjName());
			ml.setProjNo(pro.getProjNo());
			ml.setMaterialNo(dm.getDmNo());
			ml.setMaterialName(dm.getDmName());
			ml.setMaterialSpec(dm.getDmSpec());
			ml.setMaterialUnit(dm.getDmUnit());
			ml.setMaterialNum(dm.getDmNum());
			ml.setRemark(dm.getRemark());
			ml.setFlag(dm.getFlag());
			//ml.setMaterialCode(dm.getMaterialNum());//物料编码
			//ml.setMaterialStandard(dm.getMaterialStandard());//规格
			listml.add(ml);
		}
		List<DrawingMaterial> drawingMaterials=new ArrayList<DrawingMaterial>();
		List<MaterialList> materialLists=new ArrayList<MaterialList>();
		drawingMaterials=drawingMaterialDao.queryDrawingMaterialList(projId);
		materialLists=materialListDao.queryMaterialListList(projId);
		if(null!=drawingMaterials && drawingMaterials.size()>0){
			drawingMaterialDao.batchDeleteObjects(drawingMaterials);
		}
		if(null!=materialLists && materialLists.size()>0){
			materialListDao.batchDeleteObjects(materialLists);
		}
		drawingMaterialDao.batchInsertObjects(list);		
		materialListDao.batchInsertObjects(listml);
		//this.updateProjectRecord(pro);   // 翻转工程状态为“待确定造价” 更新设计人ID，设计人，设计完成时间
		//designInfoDao.updateDesignInfo(pro);  // 更新设计信息中设计人、设计完成时间 
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	//正式出图导入翻转状态和产生操作记录
	public void updateProjectRecord(Project pro) {
		//更新工程状态
		String id=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.OFFICIAL_DRAWING.getActionCode());
		pro.setProjStatusId(id);
		pro.setDesigner(pro.getSurveyer());
		pro.setDesignerId(pro.getSurveyerId());
		pro.setDuCompleteDate(projectDao.getDatabaseDate());
		projectDao.update(pro);
		
		//业务操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);//生成唯一ID
		operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.OFFICIAL_DRAWING.getValue(), StepEnum.OFFICIAL_DRAWING.getMessage(), "");
	}
	
	/**
	 * 图纸签收确认
	 * @author fuliwei
	 * @createTime 2017年8月26日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateState(DesignInfo designInfo) {
		Project pro= projectDao.get(designInfo.getProjId());
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.OFFICIAL_DRAWING.getActionCode(), true);
		
		pro.setProjStatusId(statusId);						//图纸签收
		pro.setDuCompleteDate(projectDao.getDatabaseDate());//设计完成时间
		
		
		designInfo.setDuCompleteDate(projectDao.getDatabaseDate());//设计完成时间
	    //更新设计信息记录
		designInfoDao.update(designInfo);
		
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);//生成唯一ID
	    String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.OFFICIAL_DRAWING.getValue(), StepEnum.OFFICIAL_DRAWING.getMessage(), "");
	    pro.setToDoer(toder);//待办人
	    projectDao.update(pro);
	}
	
	/**
	 * 白图登记
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveWhiteMap(DesignInfo designInfo) {
		Project pro= projectDao.get(designInfo.getProjId());
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.WHITE_MAP.getActionCode(), true);
		pro.setProjStatusId(statusId);								   //白图登记
	
		pro.setWhiteMapRegisterRemark(designInfo.getWhiteMapRegisterRemark());	//白图登记备注
		
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);//生成唯一ID
	    String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.WHITE_MAP.getValue(), StepEnum.WHITE_MAP.getMessage(), "");
	    pro.setToDoer(toder);//待办人
	    projectDao.update(pro);
	}
	
	/**
	 * 白图审核
	 * @author fuliwei  
	 * @date 2018年11月9日  
	 * @version 1.0
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveWhiteMapAudit(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID,
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
		Project project=projectDao.get(manageRecord.getProjId());
		DocType docType = docTypeService.findByStepId(stepID,project.getCorpId(),project.getProjectType(),project.getContributionMode());
		if(docType!=null){
			manageRecord.setDocTypeId(docType.getId());
			auditLevel=docType.getGrade();
		}
		manageRecord.setStepId(stepID);		//白图审核
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
			isNextStatus=false;
			//更新此工程此步骤的审核历史记录更新作废标记
			manageRecordDao.batUpdateHistoryRecord(manageRecord.getProjId(), stepID);
		}else if(auditLevel.equals(manageRecord.getMrAuditLevel())){
			isNextStatus = true;
			//审核通过--将通知置为无效
			
		}else{
			//已通过 但不是最后一级 用当前级别查后面的
			OperateRecord or =operateRecordService.findNextGrade(project.getProjId(), project.getCorpId(),project.getProjectType(),project.getContributionMode(), 
					stepID, manageRecord.getMrAuditLevel(), OperateWorkFlowEnum.NOT_DONE.getValue(),manageRecord.getMrId());
			if(or!=null){
				project.setToDoer(or.getOperater());//下一个审核人
			}
		}
		
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
			String status = workFlowService.queryProjStatusId(project.getCorpId(),project.getProjectType(),project.getContributionMode(),WorkFlowActionEnum.WHITE_MAP_AUDIT.getActionCode(), isNextStatus);
			project.setProjStatusId(status);
			
			//2.更新工程记录
			projectDao.update(project);
		}
		
	}

}
