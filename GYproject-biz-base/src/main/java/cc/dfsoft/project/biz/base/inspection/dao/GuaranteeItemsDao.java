package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.GuaranteeItems;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 保证项目初始化值
 * @author fuliwei
 *
 */
public interface GuaranteeItemsDao extends CommonDao<GuaranteeItems, String>{
	/**
	 * 保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryGuaranteeItems(ThreadingPipeReq req);
}
