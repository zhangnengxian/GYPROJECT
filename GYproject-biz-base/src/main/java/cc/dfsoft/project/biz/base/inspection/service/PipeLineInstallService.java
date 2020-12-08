package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PipelineInstallReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPLIReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;

/**
 * 管道安装业务接口层
 * @author liaoyq
 *
 */
public interface PipeLineInstallService {

	/**
	 * 批量保存管道安装记录
	 * @param checkListReq
	 */
	void saveRecords(ProjectCheckListPLIReq checkListReq);

	/**
	 * 分页查询管道安装记录列表
	 * @param pipelineInstallReq
	 * @return
	 */
	Map<String, Object> queryPipelineInstall(
			PipelineInstallReq pipelineInstallReq);

	PipeLineInstall viewRecordById(String id);

	String saveRecord(PipeLineInstall pli);

	void deleteRecordById(String pliId);

	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	Map<String, Object> queryRecords(PipelineInstallReq pipelineInstallReq);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
	

}
