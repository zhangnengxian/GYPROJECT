package cc.dfsoft.project.biz.base.contract.service.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.contract.dao.ProjectCostDao;
import cc.dfsoft.project.biz.base.contract.dto.ProjectCostReq;
import cc.dfsoft.project.biz.base.contract.entity.ProjectCost;
import cc.dfsoft.project.biz.base.contract.service.ProjectCostService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 
 * 描述:工程总造价业务实现
 * @author liaoyq
 * @createTime 2017年8月17日
 */
@Service
@Transactional(readOnly = true, propagation=Propagation.REQUIRED)
public class ProjectCostServiceImpl implements ProjectCostService {
	@Resource
	ProjectCostDao projectCostDao;
	
	@Resource
	ProjectService projectService;
	
	@Resource
	ManageRecordDao manageRecordDao;
	
	@Resource
	DocTypeDao docTypeDao;

	@Resource
	ProjectDao projectDao;

	@Resource
	WorkFlowService workFlowService;

	@Resource
	OperateRecordService operateRecordService;

	/**
	 * 根据工程ID查询工程造价，并进行保存或修改
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param
	 * @param projectCostReq 工程造价dto
	 */
	@Override
	public void findAndSaveOrUpdate(String auditLevel,
			ProjectCostReq projectCostReq) {
		//先按工程ID查询工程总造价记录
		ProjectCost projectCost = projectCostDao.queryByProjId(projectCostReq.getProjId());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if(projectCost==null){//不存在
			projectCost = new ProjectCost();
			projectCost.setPcId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
			projectCost.setProjId(projectCostReq.getProjId());
			projectCost.setProjNo(projectCostReq.getProjNo());
			projectCost.setCorpName(loginInfo.getCorpName());
			projectCost.setCorpId(loginInfo.getCorpId());
			projectCost.setDeptId(loginInfo.getDeptId());
			projectCost.setTenantId(SessionUtil.getTenantId());
			
		}
		projectCost.setProjCostAuditType(projectCostReq.getProjCostAuditType());
		projectCost.setReduceGasTimes(projectCostReq.getReduceGasTimes());
		projectCost.setAffirmCostDate(projectCostDao.getDatabaseDate());
		projectCost.setConfirmTotalCost(projectCostReq.getConfirmTotalCost());
		projectCost.setCostMember(loginInfo != null?loginInfo.getStaffName():"");
		projectCost.setCostMemberId(loginInfo != null?loginInfo.getStaffId():"");
		projectCost.setRemark(projectCostReq.getCostRemark()!=null?projectCostReq.getCostRemark():"");
		projectCost.setAuditLevel(StringUtil.isNotBlank(auditLevel)?auditLevel:"");
		projectCostDao.saveOrUpdate(projectCost);
	}
	/**
	 * 分页获取待工程造价审核的工程列表
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param projectQueryReq 
	 * @param stepId
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	
	@Override
	public Map<String, Object> queryAuditProject(
			ProjectQueryReq projectQueryReq, String stepId) throws ParseException {
		projectQueryReq.setStepId(stepId);
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Map<String, Object> result = projectService.queryProjectByTime(projectQueryReq);
		List<Project> data = (List<Project>) result.get("data");
		
		if (data != null && data.size() > 0) {
			/**
			 * -1 未审核 0 审核未通过 1 审核通过 2待审核 若该
			 * 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0",
			 * "level3":"2"};
			 */
			for (int i = 0; i < data.size(); i++) {
				//审核级别
				DocType docType = docTypeDao.findByStepId(stepId,data.get(i).getCorpId(),data.get(i).getProjectType(),data.get(i).getContributionMode());
				String auditLevel = docType != null?docType.getGrade():"0";//获取标准
				//再 查询工程造价审核级别
				ProjectCost projectCost = projectCostDao.queryByProjId(data.get(i).getProjId());
				
				if(projectCost!=null && StringUtils.isNotBlank(projectCost.getAuditLevel())){
					auditLevel = projectCost.getAuditLevel();
				}
				data.get(i).setLevel(auditLevel);// 设置审核总级数
				Map<String, String> levelBtn = new HashMap<String, String>();

				for (int n = 1; n < Integer.parseInt(auditLevel) + 1; n++) {
					if (n == 1) {
						levelBtn.put("level" + n, "2");// 待审
					} else {
						levelBtn.put("level" + n, "-1");// 未审
					}
				}
				List<ManageRecord> mrls = manageRecordDao.findByStepIdProjIdIsPass(data.get(i).getProjId(), stepId,
						MrResultEnum.PASSED.getValue());
				if (mrls != null && mrls.size() > 0) {
					// 遍历循环，获取审核是否通过
					for (ManageRecord mr : mrls) {
						levelBtn.put("level" + mr.getMrAuditLevel(), mr.getMrResult());
					}
					if (mrls.size() < Integer.parseInt(auditLevel)) {
						//待审核的时候，查询配置是否可进行审核，不可进行审核的，则为未审核“-1”
						//key规则：工程类型_人员ID_审核级别_菜单ID
						System.err.println(mrls.size());
						List<DataFilerSetUpDto> datafiltes = Constants.getDataFilterMapByKey(data.get(i).getProjectType()+"_"+loginInfo.getStaffId()+"_"+(mrls.size() + 1)+"_"+projectQueryReq.getMenuId());
						if(datafiltes!=null && datafiltes.size()>0){
							levelBtn.put("level" + (mrls.size() + 1), "-1");
						}else{
							levelBtn.put("level" + (mrls.size() + 1), "2");
						}
					}
				}else{
					//此处根据工程类型_人员ID_审核级别_菜单ID查找xx人不能审核一级
					List<DataFilerSetUpDto> datafiltes = Constants.getDataFilterMapByKey(data.get(i).getProjectType()+"_"+loginInfo.getStaffId()+"_"+(mrls.size() + 1)+"_"+projectQueryReq.getMenuId());
					if(datafiltes!=null && datafiltes.size()>0){
						levelBtn.put("level" + 1, "-1");
					}else{
						levelBtn.put("level" + 1, "2");
					}
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}

	/**
	 * 根据工程ID查询工程造价信息
	 * @author liaoyq
	 * @createTime 2017年8月17日
	 * @param projId 工程ID
	 * @return 
	 */
	@Override
	public ProjectCost queryByProjId(String projId) {
		return projectCostDao.queryByProjId(projId);
	}

	@Override
	public Map<String, Object> queryCoster(DesignerQueryReq designerQueryReq) throws ParseException {
		return projectCostDao.queryCoster(designerQueryReq);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void updateProject(DesignDispatchDto designDispatchReq) {
		//更新工程信息
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
		pro.setCostMember(designDispatchReq.getSurveyer());		 // 造价审核员
		pro.setCostMemberId(designDispatchReq.getSurveyerId());	 //预造价审核员Id
		pro.setToDoer(designDispatchReq.getSurveyer());
		String statusId=workFlowService.queryProjStatusId(pro.getCorpId(), pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.PROJECT_COST_DISPATCH.getActionCode(), true);
		pro.setProjStatusId(statusId);          				 //更新工程状态

		//更新工程
		projectDao.update(pro);
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);
		//operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.PROJECT_COST_DISPATCH.getValue(), StepEnum.PROJECT_COST_DISPATCH.getMessage(), "");
		operateRecordService.createNextOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.PROJECT_COST_DISPATCH.getValue(), StepEnum.PROJECT_COST_DISPATCH.getMessage(), "", designDispatchReq.getSurveyerId(), designDispatchReq.getSurveyer());
	}

}
