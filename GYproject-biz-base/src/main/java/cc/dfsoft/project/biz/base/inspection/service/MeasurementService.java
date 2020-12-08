package cc.dfsoft.project.biz.base.inspection.service;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cc.dfsoft.project.biz.base.inspection.dto.MeasurementReq;
import cc.dfsoft.project.biz.base.inspection.entity.Measurement;



public interface MeasurementService {
	/**
	 * 根据工程Id查询计量表
	 * 
	 * @author wanghuijun
	 * @createTime 2018年9月17日
	 * @return
	 */
	public Map<String, Object> queryMeasurement(MeasurementReq measurementReq) throws Exception ;
	
	/**
	 * 保存计量表
	 * @author wanghuijun
	 * @createTime 2018年9月18日
	 * @param measurement
	 * @return
	 * @throws Exception
	 */
	public void savaMeasurement(Measurement measurement) throws Exception;
	
	
	/**
	 * 根据计量表id查询计量表详述
	 * @author wanghuijuns
	 * @createTime 2018年9月18日
	 * @param id
	 * @return
	 */
	public  Measurement viewMeasurement(String msId) throws Exception;
	
	
	/**
	 * 根据计量表msId删除记录
	 * @author wanghuijun
	 * @createTime 2018年9月19日
	 * @param msId
	 * @return
	 */
	public void byMsIdDeleteMeasurement(String msId)throws Exception;
	
	/**
	 * 根据工程ID 、 type类型去查找相应的cpt文件
	 * @author wanghuijun
	 * @createTime 2018年10月10日
	 * @param projId
	 * @param type
	 * @return
	 */
	public List<Object> findPrintDataByProjId(String projId,String type);
	
}
