package cc.dfsoft.project.biz.base.inspection.service;


import java.util.Map;





import cc.dfsoft.project.biz.base.inspection.dto.PePipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListASReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPePipeReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.PepipeWelding;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;

/**
 * PE管焊接记录
 * @author Mr.liu
 *
 */
public interface PePipeWeldingService {
	/**
	 * PE管焊接记录查询
	 * @param request
	 * @param pipeWeldingReq
	 * @return
	 */
	public Map<String, Object> queryPePipeWelding(PePipeWeldingReq pePipeWeldingReq);
	/**
	 * PE管焊接记录批量保存
	 * @param list
	 * @return
	 */
	public void savePepipewelding(ProjectCheckListPePipeReq req);
	
	public Map<String, Object> queryRecords(PePipeWeldingReq pipeWeldingReq);

	public void saveRecords(ProjectCheckListPePipeReq checkListReq);

	public void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	public PepipeWelding viewRecordById(String id);

	public String saveRecord(PepipeWelding pePw) throws Exception;

	public void deleteRecordById(String peId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
	
	/**
	 * 保存回调-记录区
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNoticeReocrd(String cwId);
}
