package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListTBReq;
import cc.dfsoft.project.biz.base.inspection.dto.TrenchBackfillReq;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;



public interface TrenchBackfillService {

	/**
	 * 保存沟槽回填检查记录
	 * @param checkListTBReq
	 */
	void saveTrenchBackfill(ProjectCheckListTBReq checkListTBReq);

	/**
	 * 分页查询沟槽回填记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(TrenchBackfillReq trenchBackfillReq);
	
	/**
	 * 保存报验单时回写记录表pcId
	 * @param recordsId
	 * @param pcId 
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 根据记录ID查询记录详述
	 * @param id
	 * @return
	 */
	TrenchBackfill viewRecordById(String id);

	String saveRecord(TrenchBackfill tb);

	void deleteRecordById(String tbId);

	/**
	 * 保存记录（批量）
	 * @param checkListTBReq
	 */
	void saveRecords(ProjectCheckListTBReq checkListTBReq);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
