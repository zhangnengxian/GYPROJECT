package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.MenuStatusSetUpDao;
import cc.dfsoft.project.biz.base.project.entity.MenuStatusSetUp;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class MenuStatusSetUpDaoImpl extends NewBaseDAO<MenuStatusSetUp, String> implements MenuStatusSetUpDao {
	
	/**
	 * 通过menuId查询菜单设置
	 * @author fuliwei
	 * @createTime 2018年6月30日
	 * @param 
	 * @return
	 */
	@Override
	public MenuStatusSetUp queryByMenuId(String menuId) {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("menuId",menuId));  // 菜单id
		
		List<MenuStatusSetUp> list=this.findByCriteria(criteria);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

}
