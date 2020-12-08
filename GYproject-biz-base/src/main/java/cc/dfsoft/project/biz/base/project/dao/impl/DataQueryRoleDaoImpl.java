package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.DataQueryRoleDao;
import cc.dfsoft.project.biz.base.project.entity.DataQueryRole;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class DataQueryRoleDaoImpl extends NewBaseDAO<DataQueryRole, String> implements DataQueryRoleDao {
	
	/**
	 * 通过部门id查询
	 * @author fuliwei
	 * @createTime 2017年10月9日
	 * @param 
	 * @return
	 */
	@Override
	public DataQueryRole findByUnitId(String unitId) {
		Criteria c = super.getCriteria();
		
		if (StringUtils.isNotBlank(unitId)) {
			c.add(Restrictions.eq("unitId", unitId));
			List<DataQueryRole> list=this.findByCriteria(c);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		
		return null;
	}

}
