package cc.dfsoft.project.biz.base.complete.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.CheckItemReq;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author Yuanyx
 *
 */
public interface CheckItemDao extends CommonDao<CheckItem, String>{

	List<CheckItem> findByType(String type, String checkType,String corpId);

	List<CheckItem> findByType(String type);
	
	/**
	 * 自检项列表查询
	 * @author ht.hu
	 * @param checkItem
	 * @return
	 */
	Map<String, Object> queryCheckItems(CheckItemReq checkItem);
}
