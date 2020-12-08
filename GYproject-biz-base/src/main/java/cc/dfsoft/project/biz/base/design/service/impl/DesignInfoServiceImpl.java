package cc.dfsoft.project.biz.base.design.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.budget.dao.GovAuditCostDao;
import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dao.DispatchInfoDao;
import cc.dfsoft.project.biz.base.design.dao.SurveyInfoDao;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.entity.DispatchInfo;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.project.dao.ApplyDelayDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateWorkFlowDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.*;
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
public class DesignInfoServiceImpl implements DesignInfoService{
    
	@Resource
	DesignInfoDao designInfoDao;
	
	@Resource
	ProjectDao projectDao;
	
	/**业务操作记录*/
	@Resource
	OperateRecordDao operateRecordDao;
	
	/**业务操作记录服务接口*/
	@Resource
	OperateRecordService operateRecordService;
	
	@Resource
	DispatchInfoDao dispatchInfoDao;
	
	/**勘察信息dao*/
	@Resource
	SurveyInfoDao surveyInfoDao;
	
	/**工作流服务接口*/
	@Resource
	WorkFlowService workFlowService;
	
	/**工作流服务接口*/
	@Resource
	DepartmentService departmentService;
	/**延期申请Dao*/
	@Resource
	ApplyDelayDao applyDelayDao;
	@Resource
	FestivalService festivalService;
	@Resource
	GovAuditCostDao govAuditCostDao;
	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	StaffDao staffDao;
	@Resource
	OperateWorkFlowDao operateWorkFlowDao;
	
	/**
	 * 踏勘员列表查询
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> querySurveyer(DesignerQueryReq designerQueryReq){
		return designInfoDao.querySurveyer(designerQueryReq);
	};
	
	/**
	 * 资料收集页面保存设计信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveDesignInfo(DesignInfo designInfo) {
		
		Project pro=projectDao.get(designInfo.getProjId());         //通过工程id查找Project
		DesignInfo oldDi = designInfoDao.queryInfoByProjId(designInfo.getProjId());
		//回退已存在设计信息
		if(oldDi!=null){
			designInfo.setDiId(oldDi.getDiId());
		}else{
			oldDi = new DesignInfo();
			oldDi.setDiId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		}
		oldDi.setProjId(pro.getProjId());
		oldDi.setProjName(pro.getProjName());
		oldDi.setProjNo(pro.getProjNo());
		oldDi.setProjAddr(pro.getProjAddr());
		oldDi.setProjScaleDes(pro.getProjScaleDes());
		oldDi.setDuId(pro.getDuId());		//设计院id
		oldDi.setDuName(pro.getDuName());	//设计院
		oldDi.setTenantId(pro.getTenantId());
		oldDi.setOrgId(pro.getOrgId());
		oldDi.setDesignerId(pro.getDesignerId());			//设计员ID
		oldDi.setDesigner(pro.getDesigner());				//设计员
		oldDi.setOcoDate(projectDao.getDatabaseDate());		//设计开始时间
		oldDi.setAcquisitionDays(StringUtil.isNotBlank(designInfo.getAcquisitionDays())?designInfo.getAcquisitionDays():"");

		oldDi.setGasSource(designInfo.getGasSource());//气源
		oldDi.setDesignContent(designInfo.getDesignContent());//设计内容
		oldDi.setGasSupplyMode(designInfo.getGasSupplyMode());//供气方式
		oldDi.setHouseholds(designInfo.getHouseholds());//设计户数
		oldDi.setDiameterReq(designInfo.getDiameterReq());//管径要求
		oldDi.setDataAcqRemark(designInfo.getDesginRemark());//资料收集备注

		if(designInfo.getDuCompleteDate()!=null){
			oldDi.setDuCompleteDate(designInfo.getDuCompleteDate());//设计完成时间
		}
		//根据设计计划天数，自动计算设计计划完成日期（工作日）
		if(StringUtil.isNotBlank(designInfo.getAcquisitionDays()) && CheckUtil.checkNumber(designInfo.getAcquisitionDays())){
			//根据开始日期，工作日天数，计算某个日期之后几个工作日的日期
			Date date = FestivalUtil.calLeaveDate(oldDi.getOcoDate(),Integer.valueOf(designInfo.getAcquisitionDays()) , festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
			if(date!=null){
				pro.setDuPlCompleteDate(date);//计划设计完成时间
			}
		}
		designInfoDao.saveOrUpdate(oldDi);
		String statusId = "";
		if("thirdParty".equals(designInfo.getBudgetType())){
			statusId  = ProjStatusEnum.TO_BUDGET_RESULT_REGISTRATION.getValue();
		}else{
			// 更新工程信息
			statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.DATA_ACQUISITION.getActionCode(), true);
		}
		pro.setProjStatusId(statusId);
		
		
		//生成业务操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL);
		String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.DATA_ACQUISITION.getValue(), StepEnum.DATA_ACQUISITION.getMessage(), "");

		pro.setProjName(designInfo.getProjName());
		pro.setProjAddr(designInfo.getProjAddr());
		pro.setCustName(designInfo.getCustName());
		pro.setCustId(designInfo.getCustId());//客户ID
		pro.setCustPhone(designInfo.getCustPhone());
		pro.setCustContact(designInfo.getCustContact());
		pro.setToDoer(toder);//待办人
		projectDao.update(pro);//更新工程
		
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
    /**
     * 根据委托单号查设计信息
     * @param ocoNo
     * @return
     */
	public List<DesignInfo> findByOcoNo(String ocoNo) {
		return designInfoDao.findByOcoNo(ocoNo);
	}

	/**
	 * 设计派遣二级页面更新工程信息
	 * @author fuliwei
	 * @createTime 2016-09-02
	 * @param designDispatchReq
	 * @return
	 */
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void update(DesignDispatchDto designDispatchReq) {
		//更新工程信息
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
		
		pro.setSurveyer(designDispatchReq.getSurveyer()); 	     //勘察人
		pro.setSurveyerId(designDispatchReq.getSurveyerId());
		pro.setProjectRemark(designDispatchReq.getProjectRemark());//受理备注
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.DESIGN_DISPATCH.getActionCode(), true);
		pro.setProjStatusId(statusId);          				 //更新工程状态

		String deptTransfer = getDeptTransfer(designDispatchReq.getSurveyerId());
		pro.setDeptTransfer(deptTransfer);//踏勘人的新部门
		pro.setToDoer(designDispatchReq.getSurveyer());	//待办人

		//更新工程
		projectDao.update(pro);
		
		//生成派遣信息
		DispatchInfo dispatchInfo= new DispatchInfo();
		dispatchInfo.setDisId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		dispatchInfo.setProjId(pro.getProjId());
		dispatchInfo.setProjNo(pro.getProjNo());
		dispatchInfo.setProjName(pro.getProjName());
		dispatchInfo.setDisSurveyDate(dispatchInfoDao.getDatabaseDate());
		dispatchInfo.setSurveyerId(pro.getSurveyerId());
		dispatchInfo.setSurveyer(designDispatchReq.getSurveyer());
		dispatchInfoDao.save(dispatchInfo);
		
		
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);
		operateRecordService.createNextOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.DESIGN_DISPATCH.getValue(), StepEnum.DESIGN_DISPATCH.getMessage(), "", designDispatchReq.getSurveyerId(), designDispatchReq.getSurveyer());




		//更新操作记录待办（根据踏勘员的分组更新相应的步骤代办人（如：北组踏勘员由北组的人进行审核，待办标准配置南北组两个人的待办）SupSql为逗号分隔的步骤ID）
		DataFilerSetUpDto dfsd = Constants.isConfig(pro.getCorpId()+"_"+pro.getProjectType()+"_"+pro.getContributionMode()+"_"+designDispatchReq.getMenuId());//配置查询
		if (dfsd==null){
			dfsd = Constants.isConfig(pro.getCorpId()+"_"+pro.getProjectType()+"_"+designDispatchReq.getMenuId());
		}
		if (dfsd==null ||StringUtils.isBlank(dfsd.getSupSql()) ){ return;}

		List<OperateRecord> orList = operateRecordDao.queryListByStepIds(pro.getProjId(), null, Arrays.asList(dfsd.getSupSql().split(",")));
		if (orList==null || orList.size()<1) {return;}

		for (OperateRecord or:orList) {
			if (StringUtils.isBlank(or.getOperateCsr1())){continue;}
			String[] staffIds = or.getOperateCsr1().split(",");
			List<Staff> staffList = staffDao.queryStaffListByIds(staffIds);
			if (staffList==null || staffList.size()<1){ continue;}
			No:for (Staff s:staffList) {
				if (StringUtils.isNotBlank(s.getDeptTransfer()) && StringUtils.isNotBlank(deptTransfer)){
					String[] deptArr = deptTransfer.split(",");
					for (int i = 0; i <deptArr.length ; i++) {
						if (s.getDeptTransfer().contains(deptArr[i])){
							or.setOperateCsr1(","+s.getStaffId()+",");
							or.setOperater(s.getStaffName());
							operateRecordDao.update(or);
							break No;
						}
					}
				}
			}
		}
	}



	public String getDeptTransfer(String staffId){
		Staff staff = staffDao.get(staffId);
		if (staff==null){return null;}
		String deptTransfer = staff.getDeptTransfer();
		if (StringUtils.isNotBlank(deptTransfer)){
			String[] split = deptTransfer.split(",");
			return split[split.length-1];
		}
		return null;
	}



	/**
	 * 图纸审核页面查信息设计信息
	 * @author
	 * @createTime	2016-7-5 
	 * @param	projId
	 * @return	DesignInfo
	 */
	@Override
	public DesignInfo queryById(String projId) {
		DesignInfo designInfo=designInfoDao.queryInfoByProjId(projId);
		Project pro=projectDao.get(projId);
		if(designInfo!=null){
			designInfo.setContributionModeDes(pro.getContributionModeDes());
			designInfo.setDeptName(pro.getDeptName());
			designInfo.setProjectTypeDes(pro.getProjectTypeDes());
			designInfo.setCorpName(pro.getCorpName());
			designInfo.setCustName(pro.getCustName());
			designInfo.setCustContact(pro.getCustContact());
			designInfo.setCustPhone(pro.getCustPhone());
		}
		
		return designInfo;
	}
	
	/**
	 * 设计派遣一级页面更新工程信息
	 * @author fuliwei
	 * @createTime 2016-09-02
	 * @param designDispatchReq
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateProject(DesignDispatchDto designDispatchReq) {
		//更新工程信息
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
		pro.setDesignStation(designDispatchReq.getDesignStation());//更新设计所
		pro.setDesignStationId(designDispatchReq.getDesignStationId());//更新设计所ID
		
		pro.setDuName(designDispatchReq.getDuName());//更新设计院
		pro.setDuId(designDispatchReq.getUnitId());//更新设计院ID
		
		pro.setDesignStationDirector(designDispatchReq.getDesignStationDirector());//负责人
			
		
		//更新工程状态
		String statusId=WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.DESIGN_DISPATCH.getActionCode());
		pro.setProjStatusId(statusId);          				
		//更新工程
		projectDao.update(pro);
		
		//业务操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);
		operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.DESIGN_DISPATCH.getValue(), StepEnum.DESIGN_DISPATCH.getMessage(), "");
	}
	
	/**
	 * 创建设计信息
	 * @param project
	 */
	@Override
	public void insertDesignInfo(Project project) {
		/*private String diId;		//设计ID
		private String projId;		//工程ID
		private String projNo;		//工程编号
		private String projName;	//工程名称
		private String depositGet;	//定金是否收取
		private String ocoNo;		//委托单号
		private String duId;		//设计院ID
		private String duName;		//设计院名称
		private String designFull;	//全款是否收取
		private String designer;	//设计人
		private Date duCompleteDate;//设计完成日期
		private Date ocoDate;		//委托日期
*/		DesignInfo designInfo = new DesignInfo();
		designInfo.setDiId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		if (StringUtils.isNotBlank(project.getProjId())) {
			designInfo.setProjId(project.getProjId());
		}
		if (StringUtils.isNotBlank(project.getProjName())) {
			designInfo.setProjName(project.getProjName());
		}
		
		if (StringUtils.isNotBlank(project.getDuId())) {
			designInfo.setDuId(project.getDuId());
		}
		if (StringUtils.isNotBlank(project.getDuName())) {
			designInfo.setDuName(project.getDuName());
		}
		designInfo.setOcoDate(designInfoDao.getDatabaseDate());
		designInfoDao.save(designInfo);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateSurveyer(DesignDispatchDto designDispatchReq) {
		
		//更新工程信息
		SurveyInfo si = new SurveyInfo();
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
		List<SurveyInfo> lists = surveyInfoDao.findByProjId(designDispatchReq.getProjId());
		
		//勘察信息存在
		if(lists!=null && lists.size()>0){
			si = lists.get(0);
			si.setSurveyer(designDispatchReq.getSurveyer());
			surveyInfoDao.update(si);
		}
		//pro.setDispatchSurveyer(designDispatchReq.getSurveyer()); 					 //派遣勘察人
		pro.setSurveyer(designDispatchReq.getSurveyer()); 	
		pro.setDesigner(designDispatchReq.getSurveyer());
		
		//根据勘察人 查找员工表 设置勘察人Id
 		List<Staff> list= designInfoDao.findByStaffName(designDispatchReq.getSurveyer());
 		if(list.size()>0){
 			Staff staff=list.get(0);
 			pro.setSurveyerId(staff.getStaffId());  			 //更新勘察人ID
 			pro.setDesignerId(staff.getStaffId());
 		}
		
		//更新工程
		projectDao.update(pro);
		
		DispatchInfo dispatchInfo= new DispatchInfo();
		dispatchInfo.setDisId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		dispatchInfo.setProjId(pro.getProjId());
		dispatchInfo.setProjNo(pro.getProjNo());
		dispatchInfo.setProjName(pro.getProjName());
		dispatchInfo.setDisSurveyDate(dispatchInfoDao.getDatabaseDate());
		dispatchInfo.setSurveyerId(pro.getSurveyerId());
		dispatchInfo.setSurveyer(designDispatchReq.getSurveyer());
		dispatchInfoDao.save(dispatchInfo);
				
		//形成操作记录
		/*String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);
		operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.DESIGN_DISPATCH_TWO.getValue(), StepEnum.DESIGN_DISPATCH_TWO.getMessage(), "");*/
		
	}
	
	/**
	 * 查询详述
	 * @return
	 */
	@Override
	public Project findByProjId(String projId) {
		Project proj=projectDao.get(projId);
		if(proj != null){
			Department department = departmentService.queryDepartment(proj.getDeptId());
			proj.setDeptDivide(department.getDeptDivide());
			List<SurveyInfo> diList=surveyInfoDao.findByProjId(projId);
			if(diList != null && diList.size() > 0){
				proj.setTechnicalSuggestion(diList.get(0).getTechnicalSuggestion());//技术建议
				proj.setConnectGasDes(diList.get(0).getConnectGasDes());		    //接气方案
			}
			DesignInfo di=designInfoDao.queryInfoByProjId(projId);
			if(di!=null){
				proj.setRemark(StringUtil.isNotBlank(di.getDesginRemark())?di.getDesginRemark():"");
				proj.setOcoDate(di.getOcoDate());//设计委托日期
				BigDecimal delyDays =applyDelayDao.getDelyDays(projId,StepEnum.DESIGN_DRAWING.getValue());
				if(StringUtil.isNotBlank(di.getAcquisitionDays())){//委托天数
					BigDecimal acquisitionDays = new BigDecimal(di.getAcquisitionDays());
					proj.setAcquisitionPlDays(String.valueOf(acquisitionDays.setScale(0, BigDecimal.ROUND_HALF_UP).intValue()));
					delyDays = delyDays.add(acquisitionDays);
					/*//根据设计计划天数，自动计算设计计划完成日期（工作日）
					//根据开始日期，工作日天数，计算某个日期之后几个工作日的日期
					Date date = FestivalUtil.calLeaveDate(di.getOcoDate(),Integer.valueOf(acquisitionDays.toString()) , festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
					if(date!=null){
						proj.setDuPlCompleteDate(date);//计划设计完成时间
					}*/
				}
				 int days = delyDays.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				 if(days>0){
					proj.setAcquisitionDays(String.valueOf(days));
					if(di.getDuCompleteDate()!=null){
						proj.setDuCompleteDate(di.getDuCompleteDate());
					}else{
						//proj.setDuCompleteDate(DateUtil.addDay(di.getOcoDate(), days));
						Date date = FestivalUtil.calLeaveDate(di.getOcoDate(), days, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
						proj.setDuCompleteDate(date);
					}
				 }
				
			}
			return proj;
		}
		return null;
	}

	@Override
	public Map<String, Object> queryDesigner(DesignerQueryReq designerQueryReq) {
		return designInfoDao.queryDesigner(designerQueryReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void designDispatchUpdateProject(DesignDispatchDto designDispatchReq) {
		//更新工程信息
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程

		pro.setDesigner(designDispatchReq.getDesigner()); 	     //设计人员
		pro.setDesignerId(designDispatchReq.getDesignerId());	//设计人员ID

		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(),WorkFlowActionEnum.DESIGN_DISPATCH_WORKERS.getActionCode(), true);

		if (designDispatchReq.getIsFlag()) {//设计派工走流程
			pro.setProjStatusId(statusId); //更新工程状态
		}

		//生成派遣信息
		DispatchInfo dispatchInfo= new DispatchInfo();
		dispatchInfo.setDisId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
		dispatchInfo.setProjId(pro.getProjId());
		dispatchInfo.setProjNo(pro.getProjNo());
		dispatchInfo.setProjName(pro.getProjName());
		dispatchInfo.setDisSurveyDate(dispatchInfoDao.getDatabaseDate());
		dispatchInfo.setSurveyerId(pro.getSurveyerId());
		dispatchInfo.setSurveyer(designDispatchReq.getSurveyer());
		dispatchInfoDao.save(dispatchInfo);


		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN);

		String toder=operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.DESIGN_DISPATCH_WORKERS.getValue(), StepEnum.DESIGN_DISPATCH_WORKERS.getMessage(), "");
		pro.setToDoer(toder);
		//更新工程
		projectDao.update(pro);
	}

	@Override
	public Map<String, Object> getDataList(DesignerQueryReq designerQueryReq) throws ParseException {


		Map<String, Object> map = designInfoDao.getDataList(designerQueryReq);

		List<DesignInfo> list = (List<DesignInfo>) map.get("data");
		if(list != null && list.size()>0){
			for(DesignInfo di:list){
				if(StringUtil.isNotBlank(di.getAcquisitionDays())){
					BigDecimal acquisitionDays = new BigDecimal(di.getAcquisitionDays());
					int days = acquisitionDays.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
					Date completeDate = FestivalUtil.calLeaveDate(di.getOcoDate(), days, festivalService.getCacheMap(Constants.WORKDAYS), festivalService.getCacheMap(Constants.HOLIDAYS));
					di.setDuCompleteDate(completeDate);//计划完成时间
				}
			}
		}

		return map;
	}

	@Override
	public boolean rollBackContainsSurvey(String projId, String rollBackReason) {
		DesignInfo di=designInfoDao.queryInfoByProjId(projId);

		if (di==null) return false;

		//备份原信息记录
		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(DesignInfo.class, "projId");
		criteriaMap.put(t_projId,projId);
		String tableName = Annotations.getClassTableAnNameVal(DesignInfo.class);
		String origData = abandonedRecordService.getThisTableOrigData(tableName,criteriaMap);
		abandonedRecordService.saveAbandonedRecord(di.getDiId(),projId,StepEnum.DESIGN_DRAWING.getValue(),rollBackReason,origData);


		//更新设计信息
		di.setDuCompleteDate(null);
		designInfoDao.saveOrUpdate(di);

		return true;
	}
}
