package cc.dfsoft.project.biz.base.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.common.dao.VersionDao;
import cc.dfsoft.project.biz.base.common.entity.Version;
import cc.dfsoft.project.biz.base.common.service.VersionService;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class VersionServiceImpl implements VersionService {

	@Resource
	VersionDao versionDao;
	
	@Override
	public List<Version> findByType(String veType) {
		return versionDao.findByType(veType);
	}

}
