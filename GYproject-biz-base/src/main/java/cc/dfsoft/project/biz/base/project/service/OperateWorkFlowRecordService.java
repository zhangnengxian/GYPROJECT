package cc.dfsoft.project.biz.base.project.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlowRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;

public interface OperateWorkFlowRecordService {
	
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
	
	/**
	 * 立项时创建操作记录表
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public void cerateOperateWorkFlowRecord(Project pro,String stepId);
	
	/**
	 * 生成下一个操作通知
	 * @author fuliwei  
	 * @date 2018年9月9日  
	 * @version 1.0
	 */
	public void createNextOperateRecord(Project pro,String stepId,String operateId,String operater);
	
}
