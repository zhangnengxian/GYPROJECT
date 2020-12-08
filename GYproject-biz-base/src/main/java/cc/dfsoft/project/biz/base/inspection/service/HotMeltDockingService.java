package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.HotMeltDockingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListHMDReq;
import cc.dfsoft.project.biz.base.inspection.entity.HotMeltDocking;

/**
 * 热熔对接 业务接口
 * @author liaoyq
 */
public interface HotMeltDockingService {

	/**
	 * 分页查询热熔对接记录
	 * @param hotMeltDockingReq
	 * @return
	 */
	Map<String, Object> queryHotMeltDocking(HotMeltDockingReq hotMeltDockingReq);

	/**
	 * 保存热熔对接记录信息
	 * @param checkListHMDReq
	 */
	void saveHotMeltDocking(ProjectCheckListHMDReq checkListHMDReq);

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
	void saveRecords(ProjectCheckListHMDReq checkListReq);

	/**
	 * 分页查询记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(HotMeltDockingReq req);

	/**
	 * 根据记录ID查询记录详述
	 * @param id
	 * @return
	 */
	HotMeltDocking viewRecordById(String id);

	/**
	 * 保存记录（一条）
	 * @param checkListReq
	 * @return
	 */
	String saveRecord(HotMeltDocking hd);

	/**
	 * 删除记录
	 * @param hdId
	 */
	void deleteRecordById(String hdId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

}
