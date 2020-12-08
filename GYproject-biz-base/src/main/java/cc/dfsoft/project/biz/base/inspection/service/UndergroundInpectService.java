package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListUIReq;
import cc.dfsoft.project.biz.base.inspection.dto.UndergroundInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.project.biz.base.inspection.entity.UndergroundInpect;
/**
 * 埋地检查业务接口
 * @author liaoyq
 *
 */
public interface UndergroundInpectService {

	/**
	 * 分页查询埋地检查记录
	 * @param undergroundInpectReq
	 * @return
	 */
	Map<String, Object> queryUndergroundInpect(
			UndergroundInpectReq undergroundInpectReq);

	/**
	 * 保存埋地检查记录
	 * @param checkListUIReq
	 */
	void saveUndergroundInpect(ProjectCheckListUIReq checkListUIReq);

	/**
	 * 根据记录ID回写pcId
	 * @param recordsId
	 * @param pcId
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 分页查询记录列表
	 * @param purgeReq
	 * @return
	 */
	Map<String, Object> queryRecords(UndergroundInpectReq undergroundInpectReq);

	/**
	 * 保存记录
	 * @param checkListPReq
	 */
	void saveRecords(ProjectCheckListUIReq checkListReq);

	/**
	 * 查询记录详述
	 * @param id
	 * @return
	 */
	UndergroundInpect viewRecordById(String id);

	/**
	 * 保存记录(一条)
	 * @param checkListPReq
	 */
	String saveRecord(UndergroundInpect ug);

	/**
	 * 删除记录
	 * @param ugiId
	 */
	void deleteRecordById(String ugiId);

	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
