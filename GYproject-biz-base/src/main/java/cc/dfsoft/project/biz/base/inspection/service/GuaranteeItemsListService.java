package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;

/**
 * 保证项目
 * @author fuliwei
 *
 */
public interface GuaranteeItemsListService {
	
	/**
	 * 保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryGuaranteeItemsList(ThreadingPipeReq req);
}
