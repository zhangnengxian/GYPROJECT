package cc.dfsoft.project.biz.base.baseinfo.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.WorkFlowDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowDto;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowRecordReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.dao.ContributionModeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dao.RelationShipDao;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.ResultUtil;

/**
 * 工程流程实现
 * @author Yuanyx
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class WorkFlowServiceImpl implements WorkFlowService{


	@Resource
	DepartmentDao departmentDao;


	/**工作流程Dao*/
	@Resource
	WorkFlowDao workFlowDao;

	@Resource
	ContributionModeDao contributionModeDao;

	@Resource
	RelationShipDao relationShipDao;

	/**工程类型*/
	@Resource
	ProjectTypeDao projectTypeDao;

	/**
	 * 工程流程列表查询
	 * @param workFlowReq
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryWorkFlowList(WorkFlowReq workFlowReq) {
		Map<String, Object> map = workFlowDao.queryWorkFlowList(workFlowReq);
		List<WorkFlow> list = (List<WorkFlow>) map.get("data");
		for(WorkFlow workFlow : list){
			ContributionMode cm = contributionModeDao.get(workFlow.getContributionCode());
			ProjectType projectType = projectTypeDao.get(workFlow.getProjType());
			workFlow.setCorp(departmentDao.get(workFlow.getCorpId()).getDeptName());
			if(null!=projectType){
				workFlow.setProjTypeDes(projectType.getProjConstructDes());
			}
			if(null!=cm){
				workFlow.setContribution(cm.getContributionDes());
			}
		}
		return map;
	}

	/**
	 * 流程步骤列表查询
	 * @param workFlowReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryWorkFlowRecord(WorkFlowReq workFlowReq) {

		List<WorkFlowDto> list = new ArrayList<WorkFlowDto>();
		int filterCount = 0;
		WorkFlow workFlow = workFlowDao.get(workFlowReq.getWfId());
		if(null!=workFlow){
			String workFlowCode = workFlow.getWorkFlowCode();
			if(null!=workFlowCode){
				String [] actionCodes= workFlowCode.split(",");
				for(int i=0;i<actionCodes.length;i++){
					WorkFlowDto wfDto = new WorkFlowDto();
					wfDto.setActionCode(actionCodes[i]);
					wfDto.setActionNo(i+1);
					/*StepEnum stepEnum = StepEnum.valueof(actionCodes[i]);
					if(stepEnum!=null){
						String actionDes = stepEnum.getMessage();
						if(actionDes!=null){
							wfDto.setActionDes(actionDes);
						}
					}*/
					String actionDes=this.queryActionDes(workFlow.getTypeId(), workFlow.getAssistTypeId(), actionCodes[i]);
					if(actionDes!=null){
						wfDto.setActionDes(actionDes);
					}
					list.add(wfDto);
				}
			}
		}
		// 返回结果
		return ResultUtil.pageResult(filterCount, workFlowReq.getDraw(),list);
	}



	//查询相应的枚举
	public String queryActionDes(String typeId,String assistTypeId,String actionCode){
		if(WorkFlowTypeEnum.MAIN_PROGRESS.getValue().equals(typeId)){
			//主流程 返回StepEnum
			StepEnum stepEnum = StepEnum.valueof(actionCode);
			if(stepEnum!=null){
				String actionDes = stepEnum.getMessage();
				return actionDes;
			}
		}else{
			//辅助流程

			if(AssistProgressTypeEnum.GAS_PROGRESS.getValue().equals(assistTypeId)){
				//通气流程
				GasProjectStatusEnum gasEnum=GasProjectStatusEnum.valueof(actionCode);
				if(gasEnum!=null){
					String actionDes = gasEnum.getMessage();
					return actionDes;
				}
			}else if(AssistProgressTypeEnum.ENGINEER_PROGRESS.getValue().equals(assistTypeId)){
				//签证
				StageProjectApplicationEnum engineerStageEnum=StageProjectApplicationEnum.valueof(actionCode);
				if(engineerStageEnum!=null){
					String actionDes = engineerStageEnum.getMessage();
					return actionDes;
				}
			}else if(AssistProgressTypeEnum.CHANGE_PROGRESS.getValue().equals(assistTypeId)){
				//变更
				DesignChangeStateEnum designEnum=DesignChangeStateEnum.valueof(actionCode);
				if(designEnum!=null){
					String actionDes = designEnum.getMessage();
					return actionDes;
				}
			}else if(AssistProgressTypeEnum.PAYMENT.getValue().equals(assistTypeId)){
				//付款
				AuditResultEnum payEnum=AuditResultEnum.valueof(actionCode);
				if(payEnum!=null){
					String actionDes = payEnum.getMessage();
					return actionDes;
				}
			}
		}
		return null;
	}


	/**
	 * 批量保存流程
	 * @param workFlows
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveWorkFlows(List<WorkFlow> workFlows) {
		if(null!=workFlows && workFlows.size()>0){
			for(WorkFlow workFlow : workFlows){
				workFlow.setWfId(IDUtil.getUniqueId(Constants.BASIS));
			}
			workFlowDao.batchInsertObjects(workFlows);
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			return "noData";
		}

	}

	/**
	 * 批量删除流程
	 * @param workFlows
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String deleWorkFlows(List<WorkFlow> workFlows) {
		workFlowDao.batchDeleteObjects(workFlows);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	/**
	 * 更新工程流程中流程编码
	 * @param workFlowRecordReq
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String updateWorkFlowCode(WorkFlowRecordReq workFlowRecordReq) {
		WorkFlow workFlow = workFlowDao.get(workFlowRecordReq.getWfId());
		List<WorkFlowDto> list = workFlowRecordReq.getList();
		String workFlowCode = "";
		for(WorkFlowDto workFlowDto:list){
			workFlowCode = workFlowCode+workFlowDto.getActionCode()+",";
		}
		workFlowCode=workFlowCode.substring(0,workFlowCode.length()-1);
		workFlow.setWorkFlowCode(workFlowCode);
		workFlowDao.update(workFlow);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 查询工作流，返回下一状态id
	 * @author fuliwei
	 * @createTime 2017年3月10日
	 * @param corpId所属公司id typeID类型id
	 * @return
	 */
	@Override
	public String queryProjStatusId(String corpId, String projType,String contributionCode,String statusCode,Boolean flag) {
		/*WorkFlow workFlow=workFlowDao.queryProjStatusId(corpId, typeId);
		if(workFlow!=null ){
			//查询工作流记录
			List<WorkFlowRecord> workFlowRecordList=workFlowRecordDao.quertWorkFlowRecord(workFlow.getWfId());
			if(workFlowRecordList!=null && workFlowRecordList.size()>0){
				for(int i=0;i<workFlowRecordList.size();i++){
					if(workFlowRecordList.get(i).getStatusCode().equals(statusCode)){
						if(!flag && i>0 && i<workFlowRecordList.size()-1){
							return workFlowRecordList.get(i-1).getStatusCode();
						}else if(flag && i<workFlowRecordList.size()-1){
							return workFlowRecordList.get(i+1).getStatusCode();
						}else{
							return WorkFlowActionEnum.CONTRACT_END.getActionCode();
						}
					}
				}
			}

		}
		return WorkFlowActionEnum.CONTRACT_END.getActionCode();*/

		String typeId=WorkFlowTypeEnum.MAIN_PROGRESS.getValue();
		WorkFlow workFlow=workFlowDao.queryProjStatusId(corpId, projType,contributionCode,typeId,null);

		if(workFlow==null){
			workFlow=workFlowDao.queryProjStatusId("1101", projType,contributionCode,typeId,null);
		}

		String nextAction = "";
		if(workFlow!=null ){
			String workflowCode=workFlow.getWorkFlowCode();
			String [] workFlowArray =workflowCode.split(",");
			for(int i=0;i<workFlowArray.length;i++){
				String acode = workFlowArray[i];
				if(acode.equals(statusCode)){
					if(!flag && i>0){
						nextAction=  workFlowArray[i-1];
					}else if(flag && i<workFlowArray.length-1){
						nextAction= workFlowArray[i+1];
					}else{
						//若无定义操作，则获取 “终止” 操作码id
						nextAction= WorkFlowActionEnum.CONTRACT_END.getActionCode();
					}
					break;
				}
			}
		}



		//若传入操作id有误，则下一个操作id为空串  则获取 “终止” 操作码id
		if(StringUtils.isBlank(nextAction)){
			nextAction = WorkFlowActionEnum.CONTRACT_END.getActionCode();
		}
		//若传入操作id有误，则下一个操作id为空串  则获取 “终止” 操作码id
		return WorkFlowActionEnum.byActionCode(nextAction).getStatusCode();
	}

	@Override
	public String queryAssistProgressStatusId(String corpId, String projType, String contributionCode,
											  String statusCode, Boolean flag, String typeId, String assistTypeId) {
		WorkFlow workFlow=workFlowDao.queryProjStatusId(corpId, projType,contributionCode,typeId,assistTypeId);

		String nextAction = "";
		if(workFlow!=null ){
			String workflowCode=workFlow.getWorkFlowCode();
			String [] workFlowArray =workflowCode.split(",");
			for(int i=0;i<workFlowArray.length;i++){
				String acode = workFlowArray[i];
				if(acode.equals(statusCode)){
					if(!flag && i>0){
						nextAction=  workFlowArray[i-1];
					}else if(flag && i<workFlowArray.length-1){
						nextAction= workFlowArray[i+1];
					}else{
						//若无定义操作，则获取 “终止” 操作码id
						nextAction= WorkFlowActionEnum.CONTRACT_END.getActionCode();
					}
					break;
				}
			}
		}

		//若传入操作id有误，则下一个操作id为空串  则获取 “终止” 操作码id
		if(StringUtils.isBlank(nextAction)){
			nextAction = WorkFlowActionEnum.CONTRACT_END.getActionCode();
		}
		//若传入操作id有误，则下一个操作id为空串  则获取 “终止” 操作码id
		return nextAction;
	}

	@Override
	public WorkFlow queryWorkFlowCode(String corpId, String projType, String conb, String type) {
		return workFlowDao.queryProjStatusId(corpId, projType, conb, type, null);
	}
}
