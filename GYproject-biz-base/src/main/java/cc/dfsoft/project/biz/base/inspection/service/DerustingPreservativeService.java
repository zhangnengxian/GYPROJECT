package cc.dfsoft.project.biz.base.inspection.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.DerustingPreservativeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PreservativeReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListDerustingReq;
import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;

public interface DerustingPreservativeService {
	
	/**
	 * 除锈防腐列表查询
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param dpQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	public Map<String,Object> queryDerusting(DerustingPreservativeQueryReq dpQueryReq) throws ParseException;
	
	/**
	 * 保存除锈防腐(批量增加)
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param list
	 * @return void
	 */
	public void saveDerustingRecord(ProjectCheckListDerustingReq req);

	/**
	 * 分页查询防腐记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param preservativeReq
	 * @return  Map<String, Object>
	 */
	public Map<String, Object> queryRecords(PreservativeReq preservativeReq);

	/**
	 * 保存防腐记录信息
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param checkListDReq 防腐记录保存辅助类
	 */
	public void saveDerustingPreservativeRecord(
			ProjectCheckListDerustingReq checkListDReq);

	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 记录ＩＤ
	 * */
	public DerustingPreservative viewRecordById(String id);

	/**
	 * 保存防腐记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param preservative
	 * @return 防腐记录ID
	 */
	public String saveRecord(DerustingPreservative preservative) throws Exception;

	/**
	 *根据记录ID删除防腐记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param id 防腐记录ID
	 */
	public void deleteRecordById(String id);

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param recordsId 防腐记录ID组合
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
