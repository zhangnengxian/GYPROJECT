package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface OperateWorkFlowDao  extends CommonDao<OperateWorkFlow, String>{
	
	/**
	 * 查询流程配置标准
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public Map<String,Object> queryList(OperateWorkFlowReq req);
	
	/**
	 * 通过条件查询查询流程配置标准
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public List<OperateWorkFlow> queryListByReq(OperateWorkFlowReq req);
	
	
	/**
	 * 查询标准
	 * @author fuliwei  
	 * @date 2018年10月17日  
	 * @version 1.0
	 */
	public OperateWorkFlow findByGrade(String corpId,String projectType,String conMode,String stepId,String grade,String opType);

    List<OperateWorkFlow> queryOperateWorkFlowList(String corpId, String projectType, String contributionMode, List<String> stepIds);
}
