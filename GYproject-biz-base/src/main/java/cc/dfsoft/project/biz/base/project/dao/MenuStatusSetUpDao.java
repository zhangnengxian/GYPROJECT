package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.entity.MenuStatusSetUp;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface MenuStatusSetUpDao extends CommonDao<MenuStatusSetUp, String>{
	
	/**
	 * 通过menuId查询菜单设置
	 * @author fuliwei
	 * @createTime 2018年6月30日
	 * @param 
	 * @return
	 */
	public MenuStatusSetUp queryByMenuId(String menuId);
}
