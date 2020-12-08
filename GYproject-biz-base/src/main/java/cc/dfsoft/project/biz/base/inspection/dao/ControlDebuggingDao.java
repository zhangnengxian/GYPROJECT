package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ControlDebuggingReq;
import cc.dfsoft.project.biz.base.inspection.entity.ControlDebugging;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 控制系统调试
 * @author fuliwei
 *
 */
public interface ControlDebuggingDao extends CommonDao<ControlDebugging, String>{
	
	/**
	 * 控制系统调试记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryControlDebugging(ControlDebuggingReq req);
	
	
	/**
	 * 删除所有的控制系统调试记录
	 * @author fuliwei
	 * @createTime 2017-02-06
	 * @param pcId
	 * @return
	 */
	public void deleteByPcId(String pcId);
}
