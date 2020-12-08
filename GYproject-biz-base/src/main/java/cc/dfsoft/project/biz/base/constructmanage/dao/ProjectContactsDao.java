package cc.dfsoft.project.biz.base.constructmanage.dao;

import cc.dfsoft.project.biz.base.constructmanage.dto.ProjectContactsReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ProjectContacts;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;

/**
 * 
 * @author cui
 *
 */
public interface ProjectContactsDao extends CommonDao<ProjectContacts,String> {

	/**
	 * 工程联系单列表查询
	 * @param projectContactsReq
	 * @return
	 */
	Map<String, Object> queryProjectContacts(ProjectContactsReq projectContactsReq);

}
