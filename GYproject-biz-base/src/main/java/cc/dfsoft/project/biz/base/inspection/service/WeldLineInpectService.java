package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListWLIReq;
import cc.dfsoft.project.biz.base.inspection.dto.WeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.WeldLineInpect;

/**
 * 管道焊缝检查业务接口
 * @author liaoyq
 *
 */
public interface WeldLineInpectService {

	/**
	 * 分页查询管道焊缝检查记录
	 * @param weldLineInpectReq
	 * @return
	 */
	Map<String, Object> queryWeldLineInpect(WeldLineInpectReq weldLineInpectReq);

	/**
	 * 保存管道焊缝检查记录
	 * @param checkListWLIReq
	 */
	void saveWeldLineInpect(ProjectCheckListWLIReq checkListWLIReq);

	/**
	 * 保存报验单时回写记录表pcId
	 * @param recordsId
	 * @param pcId 
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 保存记录(批量)
	 * @param checkListReq
	 */
	void saveRecords(ProjectCheckListWLIReq checkListReq);

	/**
	 * 分页查询记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(WeldLineInpectReq req);

	/**
	 * 根据记录ID查询记录详述
	 * @param id
	 * @return
	 */
	WeldLineInpect viewRecordById(String id);

	/**
	 * 保存记录
	 * @param wi
	 * @return
	 */
	String saveRecord(WeldLineInpect wi) throws Exception;

	/**
	 * 删除记录
	 * @param wliId
	 */
	void deleteRecordById(String wliId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

}
