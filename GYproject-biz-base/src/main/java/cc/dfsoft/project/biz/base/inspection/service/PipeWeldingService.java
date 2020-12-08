package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;








import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;

/**
 * 钢管焊接记录
 * @author Administrator
 *
 */
public interface PipeWeldingService {
	/**
	 * 查询钢管焊接记录
	 * @param pipeWeldingReq
	 * @return
	 */
	public Map<String,Object> queryPipeWelding(PipeWeldingReq pipeWeldingReq);
	
	/**
	 * 保存钢管焊接记录
	 * @param req
	 * @return
	 */
	public void savePipewelding(ProjectCheckListPipeReq req);

	public Map<String, Object> queryRecords(PipeWeldingReq pipeWeldingReq);

	public void saveRecords(ProjectCheckListPipeReq checkListReq);

	public void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	public PipeWelding viewRecordById(String id);

	public String saveRecord(PipeWelding pw) throws Exception;

	public void deleteRecordById(String pipeId);
}
