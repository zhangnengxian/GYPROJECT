package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListVTReq;
import cc.dfsoft.project.biz.base.inspection.dto.ValveTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.ValveTest;
/**
 * 阀门试验业务接口层
 * @author liaoyq
 *
 */
public interface ValveTestService {

	/**
	 * 分页查询阀门试验记录
	 * @param valveTestReq
	 * @return
	 */
	Map<String, Object> queryValveTest(ValveTestReq valveTestReq);

	/**
	 * 保存阀门试验记录
	 * @param checkListVTReq
	 */
	void saveValveTest(ProjectCheckListVTReq checkListVTReq);

	/**
	 * 保存报验单时回写记录表pcId
	 * @param recordsId
	 * @param pcId 
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 分页查询记录
	 * @param valveTestReq
	 * @return
	 */
	Map<String, Object> queryRecords(ValveTestReq valveTestReq);

	/**
	 * 批量保存记录
	 * @param checkListReq
	 */
	void saveRecords(ProjectCheckListVTReq checkListReq);

	/**
	 * 根据记录ID查询记录详述
	 * @param id
	 * @return
	 */
	ValveTest viewRecordById(String id);

	/**
	 * 保存记录（一条）
	 * @param vt
	 * @return
	 */
	String saveRecord(ValveTest vt);

	/**
	 * 根据ID删除记录
	 * @param vtId
	 */
	void deleteRecordById(String vtId);

}
