package cc.dfsoft.project.biz.base.common.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.common.dao.MenuStepDao;
import cc.dfsoft.project.biz.base.common.entity.MenuStep;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Repository
public class MenuStepDaoImpl extends NewBaseDAO<MenuStep, String> implements MenuStepDao {
	
	/**
	 * 通过步骤id查询menuId
	 * @author fuliwei  
	 * @date 2018年10月17日  
	 * @version 1.0
	 */
	@Override
	public MenuStep findByStepId(String stepId) {
		Criteria c = super.getCriteria();
		if(StringUtil.isNotBlank(stepId)){
			c.add(Restrictions.eq("stepId",stepId));
		}
		List<MenuStep> list = this.findByCriteria(c);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public MenuStep findByMenuId(String menuId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("meunId",menuId));
		List<MenuStep> list = this.findByCriteria(c);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
