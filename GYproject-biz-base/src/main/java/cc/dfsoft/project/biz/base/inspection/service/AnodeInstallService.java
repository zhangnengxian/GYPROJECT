package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.AnodeInstallReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListAIReq;
import cc.dfsoft.project.biz.base.inspection.entity.AnodeInstall;

/**
 * 阳极安装业务接口
 * @author liaoyq
 *
 */
public interface AnodeInstallService {

	/**
	 * 分页查询阳极安装记录
	 * @param anodeInstallReq
	 * @return
	 */
	Map<String, Object> queryAnodeInstall(AnodeInstallReq anodeInstallReq);

	/**
	 * 保存阳极安装记录
	 * @param checkListAIReq
	 */
	void saveAnodeInstall(ProjectCheckListAIReq checkListAIReq);


	/**
	 * 保存记录（批量）
	 * @param checkListTBReq
	 */
	void saveRecords(ProjectCheckListAIReq checkListReq);

	/**
	 * 分页查询记录
	 * @param req
	 * @return
	 */
	Map<String, Object> queryRecords(AnodeInstallReq req);

	/**
	 * 根据记录ID查询记录详述
	 * @param id
	 * @return
	 */
	AnodeInstall viewRecordById(String id);

	/**
	 * 保存记录(一条)
	 * @param ai
	 * @return
	 */
	String saveRecord(AnodeInstall ai);

	/**
	 * 删除记录
	 * @param aiId
	 */
	void deleteRecordById(String aiId);

	/**
	 * 保存报验单时回写记录表pcId
	 * @param recordsId
	 * @param pcId 
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
