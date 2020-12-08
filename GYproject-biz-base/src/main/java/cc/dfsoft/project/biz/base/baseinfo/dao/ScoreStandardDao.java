package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.ScoreStandardQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.ScoreStandard;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 评分dao
 * @author cui
 * 
 *
 */
public interface ScoreStandardDao extends CommonDao<ScoreStandard,String>{

	/**
	 * 评分列表查询
	 * @param scoreStandardQueryReq
	 * @return
	 */
	Map<String, Object> queryScoreStandard(ScoreStandardQueryReq scoreStandardQueryReq);

	ScoreStandard queryBySsDes(String ssDes);
	
	Boolean queryScore(ScoreStandard scoreStandard);
	
	ScoreStandard queryBySsId(String ssId);
	
}
