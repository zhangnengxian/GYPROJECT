package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ControlDebuggingReq;
/**
 * 控制系统调试
 * @author fuliwei
 *
 */
public interface ControlDebuggingService {
	
	/**
	 * 控制系统调试记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param ControlDebuggingReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryControlDebugging(ControlDebuggingReq req);
	
	
	/**
	 * 保存控制系统调试记录(批量增加)
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param req
	 * @return void
	 */
	public void saveControlDebugging(ControlDebuggingReq req);
}
