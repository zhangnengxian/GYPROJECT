package cc.dfsoft.project.biz.base.common.service;

import java.util.List;

import cc.dfsoft.project.biz.base.common.entity.Version;

public interface VersionService {
	/**
	 * 根据版本类型查版本信息
	 * @param veType
	 * @return
	 */
	List<Version> findByType(String veType);

}
