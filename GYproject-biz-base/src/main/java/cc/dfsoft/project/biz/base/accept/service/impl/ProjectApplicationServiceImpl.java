package cc.dfsoft.project.biz.base.accept.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.accept.dao.ProjectApplicationDao;
import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProjectApplicationServiceImpl implements ProjectApplicationService{
	
	/**立项申请单信息Dao*/
	@Resource
	ProjectApplicationDao projectApplicationDao;
	
	@Override
	public ProjectApplication queryById(String projId) {
		return projectApplicationDao.queryById(projId);
	}

}
