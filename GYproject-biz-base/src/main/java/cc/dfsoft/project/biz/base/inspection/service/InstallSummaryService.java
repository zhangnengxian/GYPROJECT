package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.InstallSummaryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListISReq;
import cc.dfsoft.project.biz.base.inspection.entity.InstallSummary;

/**
 * 安装汇总报验业务接口
 * @author liaoyq
 *
 */
public interface InstallSummaryService {

	/**
	 * 保存安装汇总记录
	 * @param checkListISReq
	 */
	void saveInstallSummary(ProjectCheckListISReq checkListISReq);

	/**
	 * 根据报验单id 分页查询安装汇总记录
	 * @param installSummaryReq
	 * @return
	 */
	Map<String, Object> queryInstallSummary(InstallSummaryReq installSummaryReq);

	/**
	 * 回写记录pcID
	 * @param recordsId
	 * @param pcId
	 * @param projNo 
	 * @param projId 
	 */
	void updatePcIdByRecordsId(String recordsId, String pcId, String projId, String projNo);

	/**
	 * 保存记录(批量)
	 * @param checkListReq
	 */
	void saveRecords(ProjectCheckListISReq checkListReq);

	/**
	 * 分页查询记录
	 * @param req
	 * @return
	 */
	Map<String, Object> queryRecords(InstallSummaryReq req);

	/**
	 * 查询记录详述
	 * @param id
	 * @return
	 */
	InstallSummary viewRecordById(String id);

	/**
	 * 保存记录（一条）
	 * @param is
	 * @return
	 */
	String saveRecord(InstallSummary is);

	/**
	 * 根据记录ID删除记录
	 * @param isId
	 */
	void deleteRecordById(String isId);

}
