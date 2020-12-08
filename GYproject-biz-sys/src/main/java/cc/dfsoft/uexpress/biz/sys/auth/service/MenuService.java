package cc.dfsoft.uexpress.biz.sys.auth.service;

import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * 菜单服务接口
 * @author 1919
 *
 */
public interface MenuService {

	/**
	 * 查询所有菜单，临时用
	 * @return
	 */
	public List<Menu> queryAll();
	
	/**
	 * 获取菜单树结构
	 * @return
	 */
	public List<Map<String, String>> getMenuTreeData();

	/**
	 * 获取菜单关联单据类型树结构
	 * @return
	 */
	public List<Map<String, String>> getMenurelationDocTreeData();
	
	public List<Map<String, String>> getMenuTreeForSet();

	List<Menu> findlevel2MenuList();

	List<Menu> findChildMenuList(String menuId);

	/**
	 * 新增租户菜单
	 * @param menuTenantDto
	 *//*
	public void insertMenuTenant(MenuTenantDto menuTenantDto);
	
	*//**
	 * 查询租户菜单
	 * @param tenantId
	 * @return
	 *//*
	public MenuTenantDto queryMenuTenantDto(String tenantId);*/
}
