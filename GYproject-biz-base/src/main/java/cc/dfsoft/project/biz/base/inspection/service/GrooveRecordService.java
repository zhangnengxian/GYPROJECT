package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGrReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;

/**
 * 沟槽记录服务接口
 * @author zhangjj
 * @createTime 2016-07-21
 */
public interface GrooveRecordService {
	/**
	 * 保存沟槽记录(批量增加)
	 * @param list
	 */
	public void saveGrooveRecord(ProjectCheckListGrReq projectCheckListGrReq);
	/**
	 * 查询沟槽记录
	 * @param altimetricSurveyReq
	 * @return
	 */
	public Map<String,Object> queryGrooveRecord(GrooveRecordReq dtoReq);
	
	/**
	 * 分页查询记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param GrooveRecordReq 记录查询辅助类
	 * @return Map<String, Object> 记录以及分页信息
	 */
	Map<String, Object> queryRecords(GrooveRecordReq grReq);

	/**
	 * 保存记录
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param preservative
	 * @return 记录ID
	 */
	void saveRecords(ProjectCheckListGrReq checkListGrReq);

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param recordsId 记录ID组合
	 * @param pcId 报验单ID
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo);

	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param id 记录ＩＤ
	 * */
	GrooveRecord viewRecordById(String id);

	/**
	 * 保存管沟开挖记录
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param preservative
	 * @return 记录ID
	 */
	String saveRecord(GrooveRecord gr) throws Exception;

	/**
	 *根据记录ID删管沟开挖记录
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param id 管沟开挖记录ID
	 */
	void deleteRecordById(String ebId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
