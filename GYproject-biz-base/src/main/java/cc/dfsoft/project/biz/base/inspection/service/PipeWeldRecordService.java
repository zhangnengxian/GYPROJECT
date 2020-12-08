package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPWRReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWeldRecord;

/**
 * 焊口记录业务逻辑接口
 * @author liaoyq
 * @createTime 2017年7月25日
 */
public interface PipeWeldRecordService {

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param recordsId 记录ID组合
	 * @param pcId 报验单ID
	 * @param projId 工程ID
	 * @param projNo 工程编号
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId,String projNo);

	/**
	 * 分页查询焊口记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param pipeWeldRecordReq 焊口记录查询辅助类
	 * @return Map<String, Object>
	 */
	Map<String, Object> queryRecords(PipeWeldRecordReq pipeWeldRecordReq);

	/**
	 * 保存焊口记录
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param checkListCRReq 焊口记录保存辅助类
	 */
	void saveRecords(ProjectCheckListPWRReq checkListCRReq);

	/**
	 * 根据记录ID查询记录详述
	 * @param id 焊口记录ID
	 * @return 焊口记录信息
	 */
	PipeWeldRecord viewRecordById(String id);

	/**
	 * 保存记录(一条)
	 * @param pwr
	 * @return
	 */
	String saveRecord(PipeWeldRecord pwr);

	/**
	 * 根据记录ID删除记录
	 * @param pwrId
	 */
	void deleteRecordById(String pwrId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
