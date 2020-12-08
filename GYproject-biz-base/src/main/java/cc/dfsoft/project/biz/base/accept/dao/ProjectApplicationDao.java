package cc.dfsoft.project.biz.base.accept.dao;

import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ProjectApplicationDao extends CommonDao<ProjectApplication, String>{
	
	/**
     * 根据工程id获取立项申请单信息
     * @param projId
     * @return
     */
	public ProjectApplication queryById(String projId);
}
