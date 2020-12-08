package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.baseinfo.entity.ProjectAuditStep;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 审核设置
 * @author fuliwei
 *
 */
public interface ProjectAuditStepDao extends CommonDao<ProjectAuditStep,String>{
	
	/**
	 * 查询审核设置
	 * @author fuliwei
	 * @createTime 2018年7月16日
	 * @param 
	 * @return
	 */
	public List<ProjectAuditStep> queryList();
}
