package cc.dfsoft.project.biz.base.inspection.dao;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.entity.AltimetricSurvey;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 高程测量dao
 * @author zhangjj
 *
 */
public interface AltimetricSurveyDao extends CommonDao<AltimetricSurvey, String>{
	/**
	 * 查询高程测量记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String, Object> queryAltimSurvey(AltimetricSurveyReq altimetricSurveyReq);
	
	/**
	 * 删除高程测量记录
	 */
	public void deleteByPcId(String pcId);
}
