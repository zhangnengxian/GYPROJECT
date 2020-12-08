package cc.dfsoft.project.biz.base.project.dao.impl;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.StausAndRoleDao;
import cc.dfsoft.project.biz.base.project.entity.StatusAndRole;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

/**
 * menuid、状态id关联服务
 * @author fuliwei
 *
 */
@Repository
public class StausAndRoleDaoImpl extends NewBaseDAO<StatusAndRole, String> implements StausAndRoleDao{

}
