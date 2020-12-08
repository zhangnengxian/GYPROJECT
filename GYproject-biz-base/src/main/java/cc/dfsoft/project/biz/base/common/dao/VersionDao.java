package cc.dfsoft.project.biz.base.common.dao;

import java.util.Date;
import java.util.List;

import cc.dfsoft.project.biz.base.common.entity.Version;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author Yuanyx
 *
 */
public interface VersionDao extends CommonDao<Version, String>{

	/**
	 * 根据版本类型查版本信息
	 * @param veType
	 * @return
	 */
	List<Version> findByType(String veType);

	/**
	 * 根据使用时间查版本信息
	 * @param veType
	 * @return
	 */
	Version findVer(Date signDate);

}
