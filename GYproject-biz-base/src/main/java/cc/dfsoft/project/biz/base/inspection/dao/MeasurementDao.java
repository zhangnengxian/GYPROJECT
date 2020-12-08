package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.MeasurementReq;
import cc.dfsoft.project.biz.base.inspection.entity.Measurement;
import cc.dfsoft.uexpress.common.dao.CommonDao;



/**
 * 计量表dao层
 * @author wanghuijun
 * @createTime 2018年9月17日
 */
public interface MeasurementDao extends CommonDao<Measurement, String>{
  
	/**
	 * 根据工程Id查询计量表
	 * 
	 * @author wanghuijun
	 * @createTime 2018年9月17日
	 * @return
	 */
	public Map<String, Object> queryMeasurement(MeasurementReq measurementReq) throws Exception;
	
	
	/**
	 * 根据工程ID查询计量表记录
	 * @author wanghuijun
	 * @createTime 2018年10月10日
	 * @param projId
	 * @return
	 */
	public List<Measurement> findByProjId(String projId);
	
	
}
