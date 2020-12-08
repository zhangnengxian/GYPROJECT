package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffRoleDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.StaffRole;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

/**
 * 员工角色处理DAO实现
 * @author 1919
 *
 */
@Repository
public class StaffRoleDaoImpl extends NewBaseDAO<StaffRole, String> implements StaffRoleDao{

	@Override
	public void insertStaffRole(StaffRole staffRole) {
		this.save(staffRole);
	}

	@Override
	public void updateStaffRole(StaffRole staffRole) {
		this.saveOrUpdate(staffRole);
	}

	@Override
	public StaffRole queryStaffRoleInfo(String tenantId, String staffId) {
		//String hql = "from StaffRole where tenantId=? and staffId=?";
		//StaffRole staffRole = this.findClassByHql(hql, new Object[] { tenantId, staffId });
		String hql = "from StaffRole where staffId=?";
		StaffRole staffRole = this.findClassByHql(hql, new Object[] { staffId });
		return staffRole;
	}
	
	/**
	 * 查找施工、报验查看权限
	 * @author flw
	 * @createTime 2017-1-12
	 * @param staffId
	 * @return String
	 */
	@Override
	public String queryCheckRole(String staffId) {
		StringBuffer roleSql=new StringBuffer();
		roleSql.append(" select role_id from role_menu where menu_ids like '%").append("92022").append("%'");
		List rolelist=this.findBySql(roleSql.toString());
		List staffList=new ArrayList();
		
		if(rolelist!=null && rolelist.size()>0){
			for (int i= 0;i<rolelist.size();i++){
				String roleId=(String) rolelist.get(i);
				StringBuffer staffSql=new StringBuffer();
				staffSql.append(" select staff_id from staff_role where role_ids like '%").append(roleId).append("%'");
				List staffRoleList=this.findBySql(staffSql.toString());
				if(staffRoleList!=null && staffRoleList.size()>0){
					for(int j=0;j<staffRoleList.size();j++){
						staffList.add(staffRoleList.get(j));
					}
				}
			}
		}
		if(staffList!=null && staffList.size()>0){
			for(int i=0;i<staffList.size();i++){
				if(staffId.equals(staffList.get(i))){
					return "true";
				}
			}
			return "false";
		}
		
		return null;
	}

	@Override
	public boolean deleteStaffRole(String staffId) {
		String hql = "delete StaffRole where staffId=?";
		return this.executeHql(hql, new Object[]{staffId});
	}

}
