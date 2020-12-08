package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ClearRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListCRReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeReq;
import cc.dfsoft.project.biz.base.inspection.entity.ClearRecord;

/**
 * 清扫记录业务逻辑接口层
 * @author liaoyq
 * @createTime 2017年7月25日
 */
public interface ClearRecordService {

	/**
	 * 根据清扫记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param recordsId 清扫记录ID组合
	 * @param pcId 报验单ID
	 * @param projId 工程Id
	 * @param projNo 工程编号
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId,String projNo);

	/**
	 * 分页查询清扫记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param clearRecordReq
	 * @return Map<String, Object>
	 */
	Map<String, Object> queryRecords(ClearRecordReq clearRecordReq);

	/**
	 * 保存清扫记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param checkListCRReq
	 */
	void saveRecords(ProjectCheckListCRReq checkListCRReq);

	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param id 记录ID
	 * @return ClearRecord
	 */
	ClearRecord viewRecordById(String id);

	/**
	 * 保存记录(一条)
	 * @param cr
	 * @return
	 */
	String saveRecord(ClearRecord cr) throws Exception;

	/**
	 * 根据ID删除记录
	 * @param crId
	 */
	void deleteRecordById(String crId);
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

}
