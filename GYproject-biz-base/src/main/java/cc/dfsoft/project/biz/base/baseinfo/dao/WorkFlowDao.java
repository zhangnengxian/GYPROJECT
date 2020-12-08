package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 工程流程Dao
 * @author Yuanyx
 *
 */
public interface WorkFlowDao extends CommonDao<WorkFlow,String>{

	/**
	 * 工程流程列表查询
	 * @param workFlowReq
	 * @return
	 */
	Map<String, Object> queryWorkFlowList(WorkFlowReq workFlowReq);
	
	/**
	 * 查询工作流，返回下一状态id
	 * @author fuliwei
	 * @createTime 2017年3月10日
	 * @param corpId所属公司id typeID类型id
	 * @return
	 */
	public WorkFlow queryProjStatusId(String corpId,String projType,String contributionCode,String typeId,String assistTypeId);
}
