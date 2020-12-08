package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.BallRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListBRReq;
import cc.dfsoft.project.biz.base.inspection.entity.BallRecord;

/**
 * 通球记录业务接口
 * @author liaoyq
 *
 */
public interface BallRecordService {

	/**
	 *根据pcID 分页查询通球记录
	 * @param ballRecordReq
	 * @return
	 */
	Map<String, Object> queryBallRecord(BallRecordReq ballRecordReq);

	/**
	 * 保存通球记录
	 * @param checkListBRReq
	 */
	void saveBallRecord(ProjectCheckListBRReq checkListBRReq);

	/**
	 * 回写记录pcID
	 * @param recordsId
	 * @param pcId
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId);

	/**
	 * 分页查询记录信息
	 * @param req
	 * @return
	 */
	Map<String, Object> queryRecords(BallRecordReq req);

	/**
	 * 保存记录(批量)
	 * @param checkListReq
	 */
	void saveRecords(ProjectCheckListBRReq checkListReq);

	/**
	 * 查询记录详述
	 * @param id
	 * @return
	 */
	BallRecord viewRecordById(String id);

	/**
	 * 保存记录(一条)
	 * @param br
	 * @return
	 */
	String saveRecord(BallRecord br);

	/**
	 * 根据记录ID删除记录
	 * @param tbId
	 */
	void deleteRecordById(String tbId);
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
