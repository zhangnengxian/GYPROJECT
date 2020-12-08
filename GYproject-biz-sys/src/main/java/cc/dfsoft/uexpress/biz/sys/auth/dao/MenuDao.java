package cc.dfsoft.uexpress.biz.sys.auth.dao;

import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

/**
 * 菜单处理DAO
 * @author 1919
 *
 */
public interface MenuDao extends CommonDao<Menu,String>{

	/**
	 * 查询所有菜单，临时用
	 * @return
	 */
	public List<Menu> queryAll();
	/**
	 * 菜单表但据类型表联查
	 * @return
	 */
	public List<Map<String, Object>> menuRelationDoc();
	/**
	 * 查询菜单列表（用于系统设置）
	 * @return
	 */
	public List<Map<String, Object>> getMenuTreeForSet();
	
	/***
	 * 通name查菜单
	 * @author fuliwei  
	 * @date 2018年11月18日  
	 * @version 1.0
	 */
	public Menu findByName(String name);

    List<Menu> findlevel2MenuList();

	List<Menu> findChildMenuList(String menuId);
}
