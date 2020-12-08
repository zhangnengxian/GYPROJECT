package cc.dfsoft.project.biz.base.inspection.service;

import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGrReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PressureRecords;

import java.util.Map;

/**
 * 复压记录服务接口
 * @author wangang
 * @createTime 2019-01-07
 */
public interface PressureRecordsService {
	/**
	 * 保存复压记录(批量增加)
	 * @author wangang
	 * @createTime 2019-01-07
	 */
	public void savePressureRecords(ProjectCheckListGrReq projectCheckListGrReq);

	/**
	 * 查询复压记录
	 * @author wangang
	 * @createTime 2019-01-07
	 */
	public Map<String,Object> queryPressureRecords(GrooveRecordReq dtoReq);

	/**
	 * 分页查询记录
	 * @author wangang
	 * @createTime 2019-01-07
	 * @param GrooveRecordReq 记录查询辅助类
	 * @return Map<String, Object> 记录以及分页信息
	 */
	Map<String, Object> queryRecords(GrooveRecordReq grReq);

	/**
	 * 保存记录
	 * @author wangang
	 * @createTime 2019-01-07
	 */
	void saveRecords(ProjectCheckListGrReq checkListGrReq);

	/**
	 * 根据记录ID回写报验单ID
	 * @author wangang
	 * @createTime 2019-01-07
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 根据记录ID查询记录详述
	 * @author wangang
	 * @createTime 2019-01-07
	 */
	PressureRecords viewRecordById(String id);

	/**
	 * 保存复压记录
	 * @author wangang
	 * @createTime 2019-01-07
	 */
	String saveRecord(PressureRecords pr) throws Exception;

	/**
	 *根据记录ID删复压记录
	 * @author wangang
	 * @createTime 2019-01-07
	 */
	void deleteRecordById(String id);
	
	/**
	 * 保存回调
	 * @author
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	//public void saveSignNotice(String cwId);
}
