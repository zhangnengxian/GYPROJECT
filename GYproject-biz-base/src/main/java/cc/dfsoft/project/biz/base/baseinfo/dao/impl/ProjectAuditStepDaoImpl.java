package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.ProjectAuditStepDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.ProjectAuditStep;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class ProjectAuditStepDaoImpl extends NewBaseDAO<ProjectAuditStep,String> implements ProjectAuditStepDao{
	
	/**
	 * 查询审核设置
	 * @author fuliwei
	 * @createTime 2018年7月16日
	 * @param 
	 * @return
	 */
	@Override
	public List<ProjectAuditStep> queryList() {
		Criteria c = super.getCriteria();
		c.addOrder(Order.asc("stepId"));
		// 根据条件获取查询信息
		List<ProjectAuditStep> list = this.findByCriteria(c);
		
		return list;
	}

}
