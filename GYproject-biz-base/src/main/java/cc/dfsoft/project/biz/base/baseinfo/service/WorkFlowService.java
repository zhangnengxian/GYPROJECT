package cc.dfsoft.project.biz.base.baseinfo.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowRecordReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;

public interface WorkFlowService {

	/**
	 * 工程流程列表查询
	 * @param workFlowReq
	 * @return
	 */
	Map<String, Object> queryWorkFlowList(WorkFlowReq workFlowReq);

	/**
	 * 流程步骤列表查询
	 * @param workFlowReq
	 * @return
	 */
	Map<String, Object> queryWorkFlowRecord(WorkFlowReq workFlowReq);

	/**
	 * 批量保存流程
	 * @param workFlows
	 */
	String saveWorkFlows(List<WorkFlow> workFlows);

	/**
	 * 批量删除流程
	 * @param workFlows
	 */
	String deleWorkFlows(List<WorkFlow> workFlows);

	/**
	 * 更新工程流程中流程编码
	 * @param workFlowRecordReq
	 * @return
	 */
	String updateWorkFlowCode(WorkFlowRecordReq workFlowRecordReq);
	
	/**
	 * 查询工作流，返回下一状态id
	 * @author fuliwei
	 * @createTime 2017年3月10日
	 * @param corpId所属公司id typeID类型id statusCode 状态id
	 * @return
	 */
	public String queryProjStatusId(String corpId,String projType,String contributionCode,String statusCode,Boolean flag);
	
	/**
	 * 查询辅助工作流 如通气、签证、变更
	 * @author fuliwei  
	 * @date 2018年8月29日  
	 * @version 1.0
	 */
	public String queryAssistProgressStatusId(String corpId,String projType,String contributionCode,
			String statusCode,Boolean flag,String typeId,String assistTypeId);

    WorkFlow queryWorkFlowCode(String corpId, String projType, String conb, String type);
}
