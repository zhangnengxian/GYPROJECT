package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.Map;
/**
 * 回退申请Dao
 * @author Yuanyx
 *
 */
public interface FallbackApplyDao extends CommonDao<FallbackApply, String>{

	/**
	 * 回退申请条件查询
	 * @param req
	 * @return
	 */
	Map<String, Object> queryFallbackApply(FallbackApplyReq req)throws ParseException;

    Map<String, Object> queryRollBackAuditList(FallbackApplyReq req);

	FallbackApply queryFallbackApplyInfo(String projId, String currentStepId, String fallBackStepId);
}
