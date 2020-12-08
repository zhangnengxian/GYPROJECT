package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.InsulationResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.InsulationResistanceTest;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 电器绝缘电阻
 * @author fuliwei
 *
 */
public interface InsulationResistanceTestDao extends CommonDao<InsulationResistanceTest, String>{
	
	/**
	 * 电器绝缘电阻记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryInsulationResistanceTest(InsulationResistanceTestReq req);
	
	/**
	 * 删除所有的电器绝缘电阻记录
	 * @author fuliwei
	 * @createTime 2017-02-06
	 * @param pcId
	 * @return
	 */
	public void deleteByPcId(String pcId);
}
