package cc.dfsoft.project.biz.base.settlement.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.SystemSetDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.settlement.dao.DrawingApplyDao;
import cc.dfsoft.project.biz.base.settlement.dto.DrawingApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.service.DrawingApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONSerializer;

/**
 * 电子图申请
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DrawingApplyServiceImpl implements DrawingApplyService{
	
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	/**电子图验收申请*/
	@Resource
	DrawingApplyDao drawingApplyDao;
	
	/**审核单据*/
	@Resource
	DocTypeDao docTypeDao;
	
	/**系统设置*/
	@Resource
	SystemSetDao systemSetDao;
	
	/**审核记录Dao*/
	@Resource
	ManageRecordDao manageRecordDao;
	
	@Resource
	DocTypeService docTypeService;
	
	/**
	 * 通过工程id查询详述
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@Override
	public DrawingApply findByProjId(String projId) {
		Project proj=projectDao.get(projId);
		List<DrawingApply> list=drawingApplyDao.findByProjId(projId);
		ConstructionPlan cp=constructionPlanDao.viewPlanById(projId);
		LoginInfo login=SessionUtil.getLoginInfo();
		if(list!=null && list.size()>0){
			DrawingApply da=list.get(0);
			da.setProjAddr(proj.getProjAddr());
			da.setProjId(proj.getProjId());
			da.setProjScaleDes(proj.getProjScaleDes());
			da.setProjectTypeDes(proj.getProjectTypeDes());			//工程类型
			da.setDeptName(proj.getDeptName());						//业务部门
			da.setCorpName(proj.getCorpName());						//分公司名称
			da.setContributionModeDes(proj.getContributionModeDes());//出资方式
			da.setDuName(proj.getDuName());
			if(cp!=null){
				da.setCuName(cp.getCuName());//施工单位
				da.setSuName(cp.getSuName());//监理单位
			}
			return da;
		}else{
			DrawingApply da=new DrawingApply();
			da.setProjAddr(proj.getProjAddr());
			da.setProjId(proj.getProjId());
			da.setProjScaleDes(proj.getProjScaleDes());
			da.setProjectTypeDes(proj.getProjectTypeDes());			//工程类型
			da.setDeptName(proj.getDeptName());						//业务部门
			da.setCorpName(proj.getCorpName());						//分公司名称
			da.setContributionModeDes(proj.getContributionModeDes());//出资方式
			da.setProjNo(proj.getProjNo());
			da.setProjName(proj.getProjName());
			if(cp!=null){
				da.setCuName(cp.getCuName());//施工单位
				da.setSuName(cp.getSuName());//监理单位
			}
			da.setDuName(proj.getDuName());
			da.setApplyer(login.getStaffName());
			return da;
		}
	}
	
	/**
	 * 保存电子图审核申请
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveDrawingApply(DrawingApply da) {
		
		LoginInfo login=SessionUtil.getLoginInfo();
		if(StringUtils.isBlank(da.getAcdId())){
			da.setAcdId(IDUtil.getUniqueId(Constants.MODULE_CODE_SETTLEMENT));
		}
		Project proj=projectDao.get(da.getProjId());
		
		da.setApplyer(login.getStaffName());					//申请人
		da.setApplyerId(login.getStaffId());					//申请人id
		da.setCorpId(proj.getCorpId());							//分公司id
		da.setAuditState(AuditResultEnum.APPLYING.getValue()); //审核中
		da.setSignBack("");
		da.setOrgId(proj.getOrgId());
		da.setTenantId(proj.getTenantId());
		drawingApplyDao.saveOrUpdate(da);  						//有可能审核不通过重新修改
	}
	
	/**
	 * 查询左侧列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> queryProject(ProjectQueryReq projectQueryReq) throws ParseException {
		List<String> projStuList = new ArrayList();
		List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
		for(ProjStatusEnum projStatusEnum :enums){
			projStuList.add(projStatusEnum.getValue());
		}
		projectQueryReq.setProjStuList(projStuList);//工程状态,结算报审-待资料归档之间
		
		Map<String, Object> map = projectDao.queryProject(projectQueryReq);
		List<Project> list=(List<Project>) map.get("data");
		if(list!=null && list.size()>0){
			for(Project pro:list){
				List<DrawingApply> drawingApplyList=drawingApplyDao.findByProjId(pro.getProjId());
				if(drawingApplyList!=null && drawingApplyList.size()>0){
					//判定当前审核状态
					//pro.setAuditStatusDes(AuditResultEnum.valueof(drawingApplyList.get(0).getAuditState()).getMessage());//审核状态
					pro.setAuditStatus(drawingApplyList.get(0).getAuditState());
				}else{
					pro.setAuditStatus(AuditResultEnum.NOT_APPLY.getValue());//审核状态
				}
			}
		}
		map.put("data", list);
		return map;
	}
	
	/**
	 * 查询竣工图申请单列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> queryDrawingApply(DrawingApplyReq req) throws ParseException {
		String grade=null;
		//DocType docType= docTypeDao.findByStepId(StepOutWorkflowEnum.AUDIT_COMPLETE_DRAWING.getValue());
		//grade=docType==null?"":docType.getGrade();	
		
		DocType docType= new DocType();
		grade="";
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //SystemSet systemSet=systemSetDao.get("stepId", req.getStepId());
        SystemSet systemSet=systemSetDao.querySystemSetByStepId(req.getStepId(),loginInfo.getCorpId());
		
		if(systemSet!=null&&StringUtil.isNotBlank(systemSet.getTimeSpan())){
			req.setTimeLimit(Integer.parseInt(systemSet.getTimeSpan()));
		}
		
		Map<String, Object> result= drawingApplyDao.queryDrawingApply(req);
		List<DrawingApply> data = (List<DrawingApply>) result.get("data");
		
		Project pro=new Project();
		if(data!=null && data.size()>0){
			for(int i = 0;i<data.size();i++){
				DrawingApply da=data.get(i);
				pro=projectDao.get(data.get(i).getProjId());
				if(pro != null){
					data.get(i).setProjectTypeDes(pro.getProjectTypeDes());//工程类型描述
					data.get(i).setContributionModeDes(pro.getContributionModeDes());//出自方式描述
				}
				docType = docTypeService.findByStepId(StepOutWorkflowEnum.AUDIT_COMPLETE_DRAWING.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
				if(docType!=null && StringUtils.isNotBlank(docType.getGrade())){
					data.get(i).setLevel(docType.getGrade());// 设置审核总级数（电子图审核2级审核）
					grade=docType.getGrade();
				}else{
					grade="0";
					data.get(i).setLevel(grade);
				}
				
				Project project=projectDao.get(data.get(i).getProjId());
				if(project!=null){
					data.get(i).setProjectType(project.getProjectType());
				}
				
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(grade)+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				List<ManageRecord> mrls = manageRecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(),StepOutWorkflowEnum.AUDIT_COMPLETE_DRAWING.getValue(),MrResultEnum.PASSED.getValue());
				if(mrls!=null && mrls.size()>0){
					String size = mrls.size()+"";
					if(mrls.size()<Integer.parseInt(grade)){
						//遍历循环，获取审核是否通过
						for(ManageRecord mr:mrls){
							levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
						}
						if(mrls.size()<Integer.parseInt(grade)){
							levelBtn.put("level"+(mrls.size()+1), "2");
						}
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
				
				
			}
			result.put("data", data);
		}
		return result;
	}

}
