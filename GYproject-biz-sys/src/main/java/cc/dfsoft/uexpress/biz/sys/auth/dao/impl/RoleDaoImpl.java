package cc.dfsoft.uexpress.biz.sys.auth.dao.impl;

import cc.dfsoft.uexpress.biz.sys.auth.converter.RoleDtoConverter;
import cc.dfsoft.uexpress.biz.sys.auth.dao.RoleDao;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理处理实现类
 * 
 * @author 1919
 *
 */
@Repository
public class RoleDaoImpl extends NewBaseDAO<Role, String> implements RoleDao {

	@Override
	public void insertRole(Role role) {
		this.save(role);
	}

	@Override
	public Role queryRole(String tenantId, String roleId) {
		//String hql = "from Role where tenantId=? and roleId=?";
		//Role role = this.findClassByHql(hql, new Object[] { tenantId, roleId });
		String hql = "from Role where  roleId=?";
		Role role = this.findClassByHql(hql, new Object[] { roleId });
		return role;
	}

	@Override
	public Map<String, Object> queryRoleList(RoleQueryReq roleQueryReq) {
		Criteria criteria = super.getCriteria();

		if (StringUtils.isNotBlank(roleQueryReq.getRoleIds())){
			String[] roleIds = roleQueryReq.getRoleIds().split(",");
			criteria.add(Restrictions.in("roleId",roleIds));
		}

		// 过滤条件
		if (StringUtil.isNotBlank(roleQueryReq.getRoleCode())) {
			criteria.add(Restrictions.eq("roleCode", roleQueryReq.getRoleCode()));
		}
		if (StringUtil.isNotBlank(roleQueryReq.getRoleName())) {
			criteria.add(Restrictions.like("roleName", roleQueryReq.getRoleName(), MatchMode.ANYWHERE));
		}
		//分公司Id
		//全局和当前分公司下的
		if (StringUtil.isNotBlank(roleQueryReq.getCorpId())) {
			criteria.add(Restrictions.or(Restrictions.like("corpId", roleQueryReq.getCorpId()+"%"),Restrictions.eq("corpId", "0")));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(criteria);

		// 根据条件获取查询信息
		List<Role> roleList = this.findBySortCriteria(criteria, roleQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, roleQueryReq.getDraw(),
				RoleDtoConverter.convert(roleList));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryAllRole(int draw) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		List<Role> roleList = findRoleList(loginInfo.getCorpId());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("draw", draw);
		resultMap.put("data", roleList);
		return resultMap;
	}


	public List<Role> findRoleList(String corpId){
		String hql = "from Role where corpId like '"+corpId+"%' or corpId='0'";
		List<Role> roleList = super.findByHql(hql);

		if (roleList!=null && roleList.size()>0){
			return roleList;
		}else {
			return null;
		}
	}



	@Override
	public void updateRole(Role role) {
		this.update(role);
	}

	@Override
	public void deleteRole(String tenantId, String roleId) {
		Role role = this.get(roleId);
		if (role!=null){
			this.delete(role);
		}
	}

	@Override
	public RoleDto queryRoleAndMenus(String tenantId, String roleId) {
		//String sql = "select a.*,b.menu_ids from role a,role_menu b where a.role_id=b.role_id and a.role_id=? and a.tenant_id=?";
		//Map<String, Object> map = this.findObjectBySql(sql, new Object[] { roleId, tenantId });
		String sql = "select a.*,b.menu_ids as MENU_IDS,c.dept_ids as DEPT_IDS from role a LEFT JOIN role_menu b on a.role_id=b.role_id  LEFT JOIN role_dept c on a.role_id = c.role_id where 1=1 and a.role_id=? ";
		Map<String, Object> map = this.findObjectBySql(sql, new Object[] { roleId  });

		// 组装对象，将获得的map值set到对象中(由于对象中的值与数据库的字段是对应的，将下划线转换为驼峰式规则，因此map取值时变换成下划线即可)
		RoleDto roleDto = new RoleDto();
		if (map != null && map.size() > 0) {
			roleDto.setRoleId((String) map.get("ROLE_ID"));
			roleDto.setRoleCode((String) map.get("ROLE_CODE"));
			roleDto.setRoleName((String) map.get("ROLE_NAME"));
			//roleDto.setTenantId((String) map.get("tenant_id"));
			roleDto.setCreateTime((Date) map.get("CREATE_TIME"));
			roleDto.setCreateStaffId((String) map.get("CREATE_STAFF_ID"));
			roleDto.setMenuIds((String) map.get("MENU_IDS"));
			roleDto.setCorpId((String) map.get("CORP_ID"));
			roleDto.setCorpId((String) map.get("CORP_ID"));
			roleDto.setDeptIds((String) map.get("DEPT_IDS"));
		}
		return roleDto;
	}

	@Override
	public List<Role> findByRoleCode(String roleCode) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Role where roleCode = '").append(roleCode).append("'");
		return super.findByHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRoleListByInCorpId(List<String> corpIds,String roleIds) {
		Criteria criteria = super.getCriteria();

		if (corpIds!=null && corpIds.size()>0) {
			criteria.add(Restrictions.in("corpId",corpIds));
		}
		if (StringUtils.isNotBlank(roleIds)){
			criteria.add(Restrictions.in("roleId",roleIds.split(",")));
		}


		int filterCount = this.countByCriteria(criteria);

		List<Role> roleList = this.findBySortCriteria(criteria, new RoleQueryReq());
		return ResultUtil.pageResult( filterCount, new RoleQueryReq().getDraw(),RoleDtoConverter.convert(roleList));
	}

}
