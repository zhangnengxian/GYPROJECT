package cc.dfsoft.project.biz.base.baseinfo.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.DocTypeQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;

public interface DocTypeService {
	
	/**
	 * 单据列表查询
	 * @author pengtt
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryDocType(DocTypeQueryReq docTypeQueryReq) throws ParseException;
	
	/**
	 * 保存
	 * @param docType
	 */
	void saveDocType(DocType docType);
	
	/**
	 * 通过步骤id查询单据对象
	 * @param stepId 步骤id
	 * @createTime 2016-08-23
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
	
	
	/**
	 * 审核记录报表查询单据类型
	 * @author fuliwei
	 * @createTime 2017-1-1
	 * @param PageSortReq
	 * @return
	 */
	public List queryDocTypeDes(DocTypeQueryReq docTypeQueryReq);
	
	/**
	 * 通过主键id查询
	 * @author fuliwei
	 * @createTime 2018年7月16日
	 * @param 
	 * @return
	 */
	public void deleteById(String id);

	String queryAuditLevel(String stepId, String corpId, String projectType, String contributionMode);
}
