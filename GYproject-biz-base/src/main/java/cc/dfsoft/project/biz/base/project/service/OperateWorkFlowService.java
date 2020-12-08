package cc.dfsoft.project.biz.base.project.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
/**
 * 流程配置标准
 * @author fulw
 *
 */
public interface OperateWorkFlowService {
	
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
	 * 保存操作流
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public String saveOperateWork(OperateWorkFlow opl) throws Exception;
	
	
	/**
	 * 通过id删除
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public void deleteByOwfId(String owfId) throws Exception;
	
	/**
	 * 通过主键id查详述
	 * @author fuliwei  
	 * @date 2018年9月7日  
	 * @version 1.0
	 */
	public OperateWorkFlow queryById(String owfId);
	
	/**
	 * 查询已选的人员
	 * @author fuliwei  
	 * @date 2018年10月26日  
	 * @version 1.0
	 */
	public Map<String,Object> queryOpStaff(OperateWorkFlowReq req);
}
