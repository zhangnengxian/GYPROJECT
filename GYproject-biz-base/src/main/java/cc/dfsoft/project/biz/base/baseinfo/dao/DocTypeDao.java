package cc.dfsoft.project.biz.base.baseinfo.dao;

import cc.dfsoft.project.biz.base.baseinfo.dto.DocTypeQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;
/**
 * 
 * @author pengtt
 * @createTime 2016-08-22
 *
 */
public interface DocTypeDao extends CommonDao<DocType,String>{
	
	/**
	 * @author pengtt
	 * @createTime 2016-08-22
	 * @param docTypeQueryReq
	 * @return
	 */
	public Map<String, Object> queryDocType(DocTypeQueryReq docTypeQueryReq);
	
	/**
	 * 获取下一个主键id
	 * @author pengtt
	 * @createTime 2016-08-22
	 * @return
	 */
	public String getNextId();
	
	/**
	 * 通过步骤id查询单据对象
	 * @param stepId 步骤id
	 * @createTime 2016-08-22
	 * @return
	 */
	public DocType findByStepId(String stepId);
	
	/**
	 * 通过步骤id，corpId查询单据对象
	 * @param stepId
	 * @param corpId
	 * @return
	 */
	public DocType findByStepId(String stepId,String corpId,String projectType,String contributionCode);

    String getAuditLevel(Project pro,String originalStepId, String fallbackStepId);

    boolean isAuditStep(String stepId);
}
