package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.BasicProjectContent;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 基本项目和允许偏差项目初始值
 * @author fuliwei
 *
 */

public interface BasicProjectContentDao extends CommonDao<BasicProjectContent, String>{
	
	/**
	 * 基本项目和允许偏差项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryBasicProjectContent(ThreadingPipeReq req);
}
