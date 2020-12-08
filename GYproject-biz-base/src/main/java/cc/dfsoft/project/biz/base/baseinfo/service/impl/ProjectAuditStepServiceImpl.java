package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.ProjectAuditStepDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.ProjectAuditStep;
import cc.dfsoft.project.biz.base.baseinfo.service.ProjectAuditStepService;

/**
 * 审核设置
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProjectAuditStepServiceImpl implements ProjectAuditStepService{
	
	/**审核设置*/
	@Resource
	ProjectAuditStepDao projectAuditStepDao;
	
	/**
	 * 查询审核设置
	 * @author fuliwei
	 * @createTime 2018年7月16日
	 * @param 
	 * @return
	 */
	@Override
	public List<ProjectAuditStep> queryList() {
		return projectAuditStepDao.queryList();
	}
	
	
	
}
