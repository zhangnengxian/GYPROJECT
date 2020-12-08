package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlowRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface OperateWorkFlowRecordDao extends CommonDao<OperateWorkFlowRecord, String>{

	/**
	 * 查询流程操作记录
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public Map<String,Object> queryList(OperateWorkFlowReq req);
	
	/**
	 * 通过条件查询操作记录
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public List<OperateWorkFlowRecord> queryListByReq(OperateWorkFlowReq req);
}
