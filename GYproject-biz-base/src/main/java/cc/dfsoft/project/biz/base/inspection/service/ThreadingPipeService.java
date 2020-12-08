package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;

/**
 * 管内穿线
 * @author fuliwei
 *
 */
public interface ThreadingPipeService {
	
	/**
	 * 管内穿线-保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryGuaranteeItemsList(ThreadingPipeReq req);
	
	/**
	 * 管内穿线-基本项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryThreadingPipeBasic(ThreadingPipeReq req);
	
	/**
	 * 保存记录页面数据
	 * @author fuliwei
	 * @createTime 2017年2月8日
	 * @param req
	 * @return
	 */
	public void saveThreadingPipeRecord(ThreadingPipeReq req);
}
