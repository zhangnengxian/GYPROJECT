package cc.dfsoft.project.biz.base.inspection.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.EquipmentInstallReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListEIReq;
import cc.dfsoft.project.biz.base.inspection.entity.EquipmentInstall;

/**
 * 设备安装业务接口
 * @author liaoyq
 *
 */
public interface EquipmentInstallService {

	/**
	 * 分页查询设备安装记录
	 * @param equipmentInstallReq
	 * @return
	 */
	Map<String, Object> queryEquipmentInstall(
			EquipmentInstallReq equipmentInstallReq);

	/**
	 * 保存设备安装报验记录
	 * @param checkListEIReq
	 */
	void saveEquipmentInstall(ProjectCheckListEIReq checkListEIReq);

	/**
	 * 保存记录（批量）
	 * @param checkListTBReq
	 */
	void saveRecords(ProjectCheckListEIReq checkListReq);

	/**
	 * 分页查询记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(EquipmentInstallReq req);

	/**
	 * 根据记录ID查询记录详述
	 * @param id
	 * @return
	 */
	EquipmentInstall viewRecordById(String id);

	/**
	 * 保存记录(一条)
	 * @param ei
	 * @return
	 */
	String saveRecord(EquipmentInstall ei);

	/**
	 * 删除记录
	 * @param eiId
	 */
	void deleteRecordById(String eiId);

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
