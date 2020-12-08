package cc.dfsoft.project.biz.base.inspection.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PressureQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPressureReq;

public interface PressureService {
	
	/**
	 * 试压记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param pressureQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String,Object> queryPressure(PressureQueryReq pressureQueryReq) throws ParseException;
	

	/**
	 * 保存试压记录(批量增加)
	 * @author fuliwei
	 * @createTime 2016-7-21
	 * @param list
	 * @return String
	 */
	public void savePressureRecord(ProjectCheckListPressureReq req);
}
