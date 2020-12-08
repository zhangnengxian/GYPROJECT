package cc.dfsoft.project.biz.base.project.service;

import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.ifs.ResultMessage;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 回退申请接口
 * @author Yuanyx
 *
 */
public interface FallbackApplyService {

	/**
	 * 回退申请列表条件查询
	 * @param req
	 * @return
	 */
	Map<String, Object> queryFallbackApply(FallbackApplyReq req)throws ParseException;

	/**
	 * 回退申请详述
	 * @param id
	 * @return
	 */
	FallbackApply findById(String id);

	/**
	 * 保存回退申请
	 * @param fallbackApply
	 * @throws Exception 
	 */
	void saveFallbackApply(FallbackApply fallbackApply) throws Exception;
	
	/**
	 * 查询待审核列表
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryAuditFallBack(FallbackApplyReq req) throws ParseException;
	
	/**
	 * 通过主键id查询
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 */
	FallbackApply findByFaId(String id);

	/**
	 * 用回退前步骤id 和回退后步骤id 查2个步骤间的步骤id
	 * @param pro
	 * @param originalStepId
	 * @param fallbackStepId
	 * @return
	 */
	public List<String> findStepIds(Project pro, String originalStepId, String fallbackStepId);

	ResultMessage callNC(String projId, String opType, String execType, String markType);

	boolean rollBackProcessingRelatedContent(Project pro,FallbackApply fallbackApply);

}
