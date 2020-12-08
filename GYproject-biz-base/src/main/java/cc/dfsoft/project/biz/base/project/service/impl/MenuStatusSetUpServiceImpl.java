package cc.dfsoft.project.biz.base.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.MenuStatusSetUpDao;
import cc.dfsoft.project.biz.base.project.entity.MenuStatusSetUp;
import cc.dfsoft.project.biz.base.project.service.MenuStatusSetUpService;
/**
 * 菜单状态设置
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MenuStatusSetUpServiceImpl implements MenuStatusSetUpService{
	
	/**菜单状态设置Dao*/
	@Resource
	MenuStatusSetUpDao menuStatusSetUpDao;
	
	/**
	 * 通过menuId查询菜单设置
	 * @author fuliwei
	 * @createTime 2018年6月30日
	 * @param 
	 * @return
	 */
	@Override
	public MenuStatusSetUp queryByMenuId(String menuId) {
		return menuStatusSetUpDao.queryByMenuId(menuId);
	}

}
