package cc.dfsoft.project.biz.base.project.service;

import cc.dfsoft.project.biz.base.project.entity.MenuStatusSetUp;

/**
 * 菜单状态设置
 * @author fuliwei
 *
 */
public interface MenuStatusSetUpService {
	
	/**
	 * 通过menuId查询菜单设置
	 * @author fuliwei
	 * @createTime 2018年6月30日
	 * @param 
	 * @return
	 */
	public MenuStatusSetUp queryByMenuId(String menuId);
}
