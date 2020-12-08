package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListERReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeRecord;

/**
 * 焊条领用服务接口层
 * @author liaoyq
 *
 */
public interface ElectrodeRecordService {

	/**
	 * 保存焊条领用记录
	 * 先删除已有的记录，再插入记录数据
	 * @param checkListERReq
	 */
	void saveElectrodeRecords(ProjectCheckListERReq checkListERReq);

	/**
	 * 根据条件类查询焊条记录列表
	 * @param electrodeRecordReq
	 * @return
	 */
	Map<String, Object> queryEclectrodeRecords(
			ElectrodeRecordReq electrodeRecordReq);

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param recordsId 记录ID组合
	 * @param pcId 报验单ID
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param id 记录ＩＤ
	 * */
	ElectrodeRecord viewRecordById(String id);

	/**
	 * 保存焊条领用记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param record
	 * @return 记录ID
	 */
	String saveRecord(ElectrodeRecord record) throws Exception;

	/**
	 *根据记录ID删焊条领用记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param id 焊条领用记录ID
	 */
	void deleteRecordById(String erId);

	/**
	 * 分页查询记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param electrodeRecordReq 焊条领用记录查询辅助类
	 * @return Map<String, Object> 焊条领用记录以及分页信息
	 */
	Map<String, Object> queryRecords(ElectrodeRecordReq electrodeRecordReq);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
