package cc.dfsoft.project.biz.base.baseinfo.service;

import java.util.List;

import cc.dfsoft.project.biz.base.baseinfo.entity.ProjectAuditStep;

/**
 * 审核设置
 * @author fuliwei
 *
 */
public interface ProjectAuditStepService {
	
	/**
	 * 查询审核设置
	 * @author fuliwei
	 * @createTime 2018年7月16日
	 * @param 
	 * @return
	 */
	public List<ProjectAuditStep> queryList();
}
