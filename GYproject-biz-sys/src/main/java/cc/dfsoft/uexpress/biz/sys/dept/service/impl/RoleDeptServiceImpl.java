package cc.dfsoft.uexpress.biz.sys.dept.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.biz.sys.dept.dao.RoleDeptDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.RoleDept;
import cc.dfsoft.uexpress.biz.sys.dept.service.RoleDeptService;
/**
 * 角色-工程受理部门权限配置业务层实现
 * @author liaoyq
 * @createTime 2018年8月6日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RoleDeptServiceImpl implements RoleDeptService {

	@Resource
	RoleDeptDao roleDeptDao;
	
	@Override
	public List<RoleDept> findDepts(String roleId) {
		return roleDeptDao.findDepts(roleId);
	}

}
