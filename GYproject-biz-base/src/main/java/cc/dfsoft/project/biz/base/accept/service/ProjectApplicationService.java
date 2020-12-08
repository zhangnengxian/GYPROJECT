package cc.dfsoft.project.biz.base.accept.service;

import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;

/**
 * 立项申请单信息服务接口
 * @author Administrator
 *
 */
public interface ProjectApplicationService {
	
	/**
	 * 根据工程 ID查找立项申请单信息
	 * @author	fuliwei
	 * @createTime	2016-7-9
	 * @param	projId
	 * @return	ProjectApplication 
	 */
	public ProjectApplication queryById(String projId);
}
