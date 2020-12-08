package cc.dfsoft.project.biz.base.inspection.dao;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.GroundResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.GroundResistanceTest;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 接地测试DAO
 * @author Yuanyx
 *
 */
public interface GroundResistanceTestDao extends CommonDao<GroundResistanceTest, String>{
	/**
	 * 查询接地测试记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String, Object> queryGroundResistanceTestSurvey(GroundResistanceTestReq groundResistanceTestReq);
	
	/**
	 * 删除接地测试记录
	 */
	public void deleteByPcId(String pcId);
}
