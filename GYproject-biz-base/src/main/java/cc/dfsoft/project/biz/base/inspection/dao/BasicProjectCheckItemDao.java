package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.BasicProjectCheckItem;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 基本项目和允许偏差项目
 * @author fuliwei
 *
 */
public interface BasicProjectCheckItemDao extends CommonDao<BasicProjectCheckItem, String>{
	
	/**
	 * 基本项目和允许偏差项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryBasicProjectCheckItem(ThreadingPipeReq req);
	
	/** 根据pcId删除所有的记录
	 * @author fuliwei
	 * @createTime 2017年2月8日
	 * @param pcId
	 * @return
	 */
	public void deleteById(String pcId,String type);
}
