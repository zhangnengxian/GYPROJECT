package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PeWeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPEWLIReq;
import cc.dfsoft.project.biz.base.inspection.entity.PeWeldLineInspect;

/**
 * pe管道焊缝检查业务实现层
 * @author liaoyq
 *
 */
public interface PeWeldLineInspectService {

	/**
	 * 分页查询pe管焊缝检查记录
	 * @param peWeldLineInpectReq
	 * @return
	 */
	Map<String, Object> queryPeWeldLineInpect(
			PeWeldLineInpectReq peWeldLineInpectReq);

	/**
	 * 保存pe管焊缝检查记录
	 * @param checkListPEWLIReq
	 */
	void savePeWeldLineInpect(ProjectCheckListPEWLIReq checkListPEWLIReq);

	/**
	 * 保存报验单时回写记录表pcId
	 * @param recordsId
	 * @param pcId 
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 保存记录（批量）
	 * @param checkListReq
	 */
	void saveRecords(ProjectCheckListPEWLIReq checkListReq);

	/**
	 * 分页查询记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(PeWeldLineInpectReq req);

	/**
	 * 记录详述
	 * @param id
	 * @return
	 */
	PeWeldLineInspect viewRecordById(String id);

	/**
	 * 保存记录（一条）
	 * @param record
	 * @return
	 */
	String saveRecord(PeWeldLineInspect record);

	/**
	 * 删除记录
	 * @param peWliId
	 */
	void deleteRecordById(String peWliId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

}
