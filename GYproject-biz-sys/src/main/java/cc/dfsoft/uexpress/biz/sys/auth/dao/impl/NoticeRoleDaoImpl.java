package cc.dfsoft.uexpress.biz.sys.auth.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.auth.converter.NoticeRoleDtoConverter;
import cc.dfsoft.uexpress.biz.sys.auth.dao.NoticeRoleDao;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleQueryReq;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRole;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Repository
public class NoticeRoleDaoImpl extends NewBaseDAO<NoticeRole, String> implements NoticeRoleDao {
	@Override
	public void insertRole(NoticeRole role) {
		this.save(role);
	}

	@Override
	public NoticeRole queryRole(String tenantId, String roleId) {
		//String hql = "from Role where tenantId=? and roleId=?";
		//Role role = this.findClassByHql(hql, new Object[] { tenantId, roleId });
		String hql = "from Role where  roleId=?";
		NoticeRole role = this.findClassByHql(hql, new Object[] { roleId });
		return role;
	}

	@Override
	public Map<String, Object> queryRoleList(RoleQueryReq roleQueryReq) {
		Criteria criteria = super.getCriteria();

		// 过滤条件
		if (StringUtil.isNotBlank(roleQueryReq.getNrCode())) {
			criteria.add(Restrictions.eq("nrCode", roleQueryReq.getNrCode()));
		}
		if (StringUtil.isNotBlank(roleQueryReq.getNrName())) {
			criteria.add(Restrictions.like("nrName", roleQueryReq.getNrName(), MatchMode.ANYWHERE));
		}
		//分公司Id
		if (StringUtil.isNotBlank(roleQueryReq.getCorpId())) {
			criteria.add(Restrictions.like("corpId", roleQueryReq.getCorpId()+"%"));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(criteria);

		// 根据条件获取查询信息
		List<NoticeRole> roleList = this.findBySortCriteria(criteria, roleQueryReq);
		// 返回结果
		//return ResultUtil.pageResult( filterCount, roleQueryReq.getDraw(),
				//NoticeRoleDtoConverter.convert(roleList));
		return ResultUtil.pageResult( filterCount, roleQueryReq.getDraw(),roleList);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryAllRole(int draw) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String hql = "from NoticeRole where corpId like '"+loginInfo.getCorpId()+"%'";
		List<Role> roleList = super.findByHql(hql);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("draw", draw);
		resultMap.put("data", roleList);
		return resultMap;
	}

	@Override
	public void updateRole(NoticeRole role) {
		this.update(role);
	}

	@Override
	public void deleteRole(String tenantId, String roleId) {
		NoticeRole role = new NoticeRole();
		role.setNrId(roleId);
		//role.setTenantId(tenantId);
		this.delete(role);
	}

	@Override
	public RoleDto queryRoleAndMenus(String tenantId, String roleId) {
		//String sql = "select a.*,b.menu_ids from role a,role_menu b where a.role_id=b.role_id and a.role_id=? and a.tenant_id=?";
		//Map<String, Object> map = this.findObjectBySql(sql, new Object[] { roleId, tenantId });
		String sql = "SELECT A.*,B.NR_MENU_IDS FROM NOTICE_ROLE A,NOTICE_ROLE_MENU B WHERE A.NR_ID=B.NR_ID AND A.NR_ID=? ";
		Map<String, Object> map = this.findObjectBySql(sql, new Object[] { roleId  });

		// 组装对象，将获得的map值set到对象中(由于对象中的值与数据库的字段是对应的，将下划线转换为驼峰式规则，因此map取值时变换成下划线即可)
		RoleDto roleDto = new RoleDto();
		if (map != null && map.size() > 0) {
			roleDto.setNrId((String) map.get("NR_ID"));
			roleDto.setNrCode((String) map.get("NR_CODE"));
			roleDto.setNrName((String) map.get("NR_NAME"));
			//roleDto.setTenantId((String) map.get("tenant_id"));
			roleDto.setCreateTime((Date) map.get("CREATE_TIME"));
			roleDto.setCreateStaffId((String) map.get("CREATE_STAFF_ID"));
			roleDto.setMenuIds((String) map.get("NR_MENU_IDS"));
		}
		return roleDto;
	}

	@Override
	public List<NoticeRole> findByRoleCode(String roleCode) {
		//StringBuffer hql = new StringBuffer();
		Criteria c=this.getCriteria();
		if(StringUtils.isNotBlank(roleCode)){
			c.add(Restrictions.eq("nrCode", roleCode));
			List<NoticeRole> list=super.findByCriteria(c);
			return list;
		}
		return null;
	}
}
