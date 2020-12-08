package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeBakingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListEBReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeBaking;

/**
 * 焊条烘烤业务接口
 * @author liaoyq
 *
 */
public interface ElectrodeBakingService {

	/**
	 * 保存焊条烘烤记录信息
	 * @param checkListERReq
	 */
	void saveElectrodeBaking(ProjectCheckListEBReq checkListERReq);

	/**
	 * 分页 查询焊条烘烤记录列表
	 * @param electrodeBakingReq
	 * @return
	 */
	Map<String, Object> queryEclectrodeBakings(
			ElectrodeBakingReq electrodeBakingReq);

	/**
	 * 分页查询记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param electrodeRecordReq 记录查询辅助类
	 * @return Map<String, Object> 记录以及分页信息
	 */
	Map<String, Object> queryRecords(ElectrodeBakingReq ebReq);

	/**
	 * 保存记录
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param preservative
	 * @return 记录ID
	 */
	void saveRecords(ProjectCheckListEBReq checkListEBReq);

	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param id 记录ＩＤ
	 * */
	ElectrodeBaking viewRecordById(String id);

	/**
	 * 保存焊条烘烤记录
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param preservative
	 * @return 记录ID
	 */
	String saveRecord(ElectrodeBaking eb) throws Exception;

	/**
	 *根据记录ID删焊条烘烤记录
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param id 焊条烘烤记录ID
	 */
	void deleteRecordById(String ebId);

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param recordsId 记录ID组合
	 * @param pcId 报验单ID
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId,
			String projNo);
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
