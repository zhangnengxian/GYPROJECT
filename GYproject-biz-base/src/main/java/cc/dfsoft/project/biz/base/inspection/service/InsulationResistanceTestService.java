package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.InsulationResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPurgeReq;

/**
 * 电器绝缘电阻
 * @author fuliwei
 *
 */
public interface InsulationResistanceTestService {
	
	/**
	 * 电器绝缘电阻记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryInsulationResistanceTest(InsulationResistanceTestReq req);
	
	
	/**
	 * 保存电器绝缘电阻记录(批量增加)
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param req
	 * @return void
	 */
	public void saveInsulationResistanceTestRecord(InsulationResistanceTestReq req);
}
