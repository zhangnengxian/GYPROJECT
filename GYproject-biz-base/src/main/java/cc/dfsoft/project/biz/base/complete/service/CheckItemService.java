package cc.dfsoft.project.biz.base.complete.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.CheckItemReq;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;

/**
 * 
 * @author Yuanyx
 *
 */
public interface CheckItemService {
	/**
	 * 根据自检类型查询自检项
	 * @param type
	 * @param checkType
	 * @return
	 */
	List<CheckItem> findByType(String type, String checkType,String corpId);
	/**
	 * 根据自检类型查询自检项
	 * @param type
	 * @return
	 */
	List<CheckItem> findByType(String type);
	
	/**
	 * 自检项列表查询
	 * @author ht.hu
	 * @param checkItem
	 * @return
	 */
	Map<String, Object> queryCheckItems(CheckItemReq checkItem);
	
	/**
	 * 自检项详情
	 * @author ht.hu
	 * @param checkItem
	 * @return
	 */
	CheckItem findCheckItem(String id);
	
	/**
	 * 删除自检项
	 * @author ht.hu
	 * @param id
	 */
	void delCheckItem(String id);
	
	/**
	 * 保存自检项
	 * @author ht.hu
	 * @param checkItem
	 */
	void saveCheckItem(CheckItem checkItem);
	}

