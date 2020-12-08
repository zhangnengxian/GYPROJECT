package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.dao.RoleDeptDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.RoleDept;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 角色-工程受理部门权限配置表dao实现层
 * @author liaoyq
 * @createTime 2018年8月6日
 */
@Repository
public class RoleDeptDaoImpl extends NewBaseDAO<RoleDept, String> implements
		RoleDeptDao {

	@Override
	public List<RoleDept> findDepts(String roleId) {
		Criteria criteria = super.getCriteria();
		
		if (StringUtil.isNotBlank(roleId)) {
			criteria.add(Restrictions.eq("roleId", roleId));
			return this.findByCriteria(criteria);
		}else{
			return null;
		}
	}

	@Override
	public void insertRoleDept(RoleDept roleDept) {
		this.save(roleDept);
	}

	@Override
	public void updateRoleDept(RoleDept roleDept) {
		this.update(roleDept);
	}

}
