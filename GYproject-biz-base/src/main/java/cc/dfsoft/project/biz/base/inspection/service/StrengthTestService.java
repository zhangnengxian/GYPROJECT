package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListSTReq;
import cc.dfsoft.project.biz.base.inspection.dto.StrengthTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.StrengthTest;

/**
 * 强度试验业务接口层
 * @author liaoyq
 *
 */
public interface StrengthTestService {

	/**
	 * 分页查询强度试验记录
	 * @param strengthTestReq
	 * @return
	 */
	Map<String, Object> queryStrengthTest(StrengthTestReq strengthTestReq);

	/**
	 * 保存试验记录
	 * @param checkListSTReq
	 */
	void saveStrengthTest(ProjectCheckListSTReq checkListSTReq);

	/**
	 * 回写记录pcID
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
	void saveRecords(ProjectCheckListSTReq checkListReq);

	/**
	 * 分页查询记录
	 * @param req
	 * @return
	 */
	Map<String, Object> queryRecords(StrengthTestReq req);

	/**
	 * 查询记录详述
	 * @param id
	 * @return
	 */
	StrengthTest viewRecordById(String id);

	/**
	 * 保存记录（一条）
	 * @param st
	 * @return
	 */
	String saveRecord(StrengthTest st);

	/**
	 * 删除记录
	 * @param stId
	 */
	void deleteRecordById(String stId);

	/**
	 * 完成强度测试翻转工程状态为待自检
	 * @param projId
	 * @return
	 */
	String finishStrengthTest(String projId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

}
