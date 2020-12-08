package cc.dfsoft.project.biz.base.inspection.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPurgeReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeReq;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;

public interface PurgeService {

	/**
	 * 吹扫记录列表查询
	 * @param PageSortReq
	 * @return
	 */
	public Map<String,Object> queryPurge(PurgeQueryReq purgeQueryReq) throws ParseException;
	
	/**
	 * 保存吹扫记录(批量增加)
	 * @author fuliwei
	 * @createTime 2016-7-21
	 * @param list
	 * @return String
	 */
	public void savePurgeRecord(ProjectCheckListPurgeReq req);

	/**
	 * 根据记录ID回写pcId
	 * @param recordsId
	 * @param pcId
	 * @param projNo 
	 * @param projId 
	 */
	public void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 保存记录
	 * @param checkListPReq
	 */
	public void saveRecords(ProjectCheckListPReq checkListPReq);

	/**
	 * 根据ID查询记录详述
	 * @param id
	 * @return
	 */
	public Purge viewRecordById(String id);

	/**
	 * 分页查询记录列表
	 * @param purgeReq
	 * @return
	 */
	public Map<String, Object> queryRecords(PurgeReq purgeReq);
	
	public void deleteRecordById(String purgeId);

	public String saveRecord(Purge purge) throws Exception;
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
