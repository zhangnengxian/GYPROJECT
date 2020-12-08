package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.uexpress.common.dao.CommonDao;


/**
 * 资料收集标准Dao
 * @author jingjing
 *
 */
public interface AccessoryItemDao extends CommonDao<CollectAccessoryItem, String>{

	/**
	 * 依据工程类型查询资料标准
	 * @param accessoryQueryReq
	 * @return
	 */
	public List queryAll(AccessoryQueryReq accessoryQueryReq);
	
	/**
	 * 资料标准列表条件查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-14
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryAccessoryItemList(AccessoryQueryReq accessoryQueryReq);
	/**
	 * 依据资料名称查询资料标准
	 * @param accessoryName
	 * @return
	 */
	public CollectAccessoryItem queryByAccessoryName(String accessoryName);
}
