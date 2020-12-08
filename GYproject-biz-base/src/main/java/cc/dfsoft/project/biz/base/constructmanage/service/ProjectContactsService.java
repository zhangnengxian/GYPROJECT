package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.constructmanage.dto.ProjectContactsReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ProjectContacts;

import java.text.ParseException;
import java.util.Map;

/**
 * 
 * @author cui
 *
 */
public interface ProjectContactsService {

	/**
	 * 工程联系单列表查询
	 * @param projectContactsReq
	 * @return
	 */
	Map<String, Object> queryProjectContacts(ProjectContactsReq projectContactsReq) throws ParseException;
	/**
	 * 保存联系单
	 * @param projectContacts
	 * @return
	 */
	String projectContactsSave(ProjectContacts projectContacts) throws Exception;

}

