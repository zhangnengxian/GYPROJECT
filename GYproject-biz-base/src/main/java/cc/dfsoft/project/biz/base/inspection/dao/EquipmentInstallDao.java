package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.EquipmentInstallReq;
import cc.dfsoft.project.biz.base.inspection.entity.EquipmentInstall;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 设备安装dao接口
 * @author liaoyq
 *
 */
public interface EquipmentInstallDao extends CommonDao<EquipmentInstall, String> {

	/**
	 * 根据报验单ID 分页查询设备安装记录
	 * @param equipmentInstallReq
	 * @return
	 */
	Map<String, Object> queryEquipmentInstall(
			EquipmentInstallReq equipmentInstallReq);

	/**
	 * 根据报验单id 删除设备安装记录
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * 置空报验pcId
	 * @param pcId
	 */
	void updateByPcId(String pcId);

	/**
	 * 根据记录id回写pcId
	 * @param id
	 * @param pcId
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 删除已报验记录和未报验记录
	 * @param pcId
	 */
	void deletePcIdIsNull(String pcId);

	/**
	 * 删除pcId为空的记录，即未报验的记录
	 */
	void deletePcIdIsNull();

	/**
	 * 分页查询记录
	 * @param trenchBackfillReq
	 * @return
	 */
	Map<String, Object> queryRecords(EquipmentInstallReq req);

}
