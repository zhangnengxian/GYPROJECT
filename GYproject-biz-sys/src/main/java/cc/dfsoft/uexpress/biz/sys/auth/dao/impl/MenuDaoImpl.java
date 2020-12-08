package cc.dfsoft.uexpress.biz.sys.auth.dao.impl;

import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 菜单处理DAO实现类
 * @author 1919
 *
 */
@Repository
public class MenuDaoImpl extends NewBaseDAO<Menu, String>  implements MenuDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> queryAll() {
		String hql = "from Menu where isValid = '1' order by menuLevel,sortNo";
		 List<Menu> list =super.findByHql(hql);
	    return list;
	}

	@Override
	public List<Map<String, Object>> menuRelationDoc() {
		
		LoginInfo loginInfo =SessionUtil.getLoginInfo();
		
		String sql = "select M.*,D.GRADE from MENU M left join DOC_TYPE D on (M.MENU_ID = D.MENU_ID and d.STANDARD_TYPE='1') where M.IS_VALID = '1' order by m.MENU_LEVEL,m.SORT_NO";
		List<Map<String, Object>> list = super.findListBySql(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getMenuTreeForSet() {
		String sql = "select M.*,S.STEP_ID from MENU M left join menu_step s on M.MENU_ID=S.MEUN_ID  WHERE 1=1 AND M.IS_VALID = '1' AND M.TYPE='1' AND M.MENU_LEVEL!='-1' order by m.MENU_LEVEL,m.SORT_NO";
		List<Map<String, Object>> list = super.findListBySql(sql);
		return list;
	}
	
	/***
	 * 通name查菜单
	 * @author fuliwei  
	 * @date 2018年11月18日  
	 * @version 1.0
	 */
	@Override
	public Menu findByName(String name) {
		Criteria criteria = super.getCriteria();
		if(StringUtils.isNotBlank(name)){
			criteria.add(Restrictions.like("menuName", "%"+name+"%"));
			List<Menu> roleList =this.findByCriteria(criteria);
			if(roleList!=null && roleList.size()>0){
				return roleList.get(0);
			}
		}
		return null;
	}

	@Override
	public List<Menu> findlevel2MenuList() {
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("menuLevel", "2"));
		criteria.add(Restrictions.eq("isValid", "1"));
		criteria.add(Restrictions.eq("type", "1"));
		criteria.add(Restrictions.eq("url", "#"));

		List<Menu> menuList = this.findByCriteria(criteria);
		return menuList;
	}

	@Override
	public List<Menu> findChildMenuList(String menuId) {
		Criteria criteria = super.getCriteria();

		if (StringUtil.isNotBlank(menuId)){
			criteria.add(Restrictions.eq("parentId",menuId));
		}
		criteria.add(Restrictions.eq("isValid","1"));
		criteria.addOrder(Order.asc("sortNo"));
		List<Menu> menuList = this.findByCriteria(criteria);
		return menuList;
	}


}
