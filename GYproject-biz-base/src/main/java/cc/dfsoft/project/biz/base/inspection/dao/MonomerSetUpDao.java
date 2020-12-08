package cc.dfsoft.project.biz.base.inspection.dao;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.entity.MonomerSetUp;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 单体调校DAO
 * @author Yuanyx
 *
 */
public interface MonomerSetUpDao extends CommonDao<MonomerSetUp, String>{
	/**
	 * 查询单体调校记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String, Object> queryAltimSurvey(AltimetricSurveyReq altimetricSurveyReq);
	
	/**
	 * 删除高程测量记录
	 */
	public void deleteByPcId(String pcId);
}
