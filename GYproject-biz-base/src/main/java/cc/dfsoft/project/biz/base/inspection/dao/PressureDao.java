package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PressureQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.Pressure;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 试压记录Dao
 * @author Administrator
 *
 */
public interface PressureDao extends CommonDao<Pressure, String>{
	
	/**
	 * 试压记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param pressureQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String, Object> queryPressure(PressureQueryReq pressureQueryReq);
	
	/**
	 * 删除所有的试压记录
	 * @author
	 * @createTime 2016-09-08
	 * @param
	 * @return
	 */
	public void deleteByPcId(String pcId);
}
