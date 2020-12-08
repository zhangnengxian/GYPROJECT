package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.IndoorPocketWatchReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListIPWReq;
import cc.dfsoft.project.biz.base.inspection.entity.IndoorPocketWatch;
/**
 * 户内挂表业务接口层
 * @author liaoyq
 * @createTime 2017年7月24日
 */
public interface IndoorPocketWatchService {

	/**
	 * 分页查询户内挂表记录
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param indoorPocketWatchReq 户内挂表查询辅助类
	 * @return 户内挂表记录及分页信息
	 */
	Map<String, Object> queryRecords(IndoorPocketWatchReq indoorPocketWatchReq);

	/**
	 * 保存户内挂表记录
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param checkListIPWReq
	 */
	void saveIndoorPocketWatchServiceRecord(
			ProjectCheckListIPWReq checkListIPWReq);

	/**
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param id 记录表ID
	 * @return 记录详述
	 */
	IndoorPocketWatch viewRecordById(String id);

	
	/**
	 * 保存报验单信息后，回写户内挂表pcId
	 * @author liaoyq
	 * @createTime 2017年7月24日
	 * @param recordsId 选中报验的记录ID组和
	 * @param pcId 报验单ID
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 删除记录
	 * @param ipwId
	 */
	void deleteRecordById(String ipwId);

	/**
	 * 查询记录详述
	 * @param record
	 * @return
	 */
	String saveRecord(IndoorPocketWatch record);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
