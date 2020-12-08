package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;

/**
 * 基本项目和允许偏差项目
 * @author fuliwei
 *
 */
public interface BasicProjectCheckItemService {
	
	/**
	 * 基本项目和允许偏差项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryBasicProjectCheckItem(ThreadingPipeReq req);
}
