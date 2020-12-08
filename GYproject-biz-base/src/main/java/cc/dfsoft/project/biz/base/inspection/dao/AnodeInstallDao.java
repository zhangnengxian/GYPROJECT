package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.AnodeInstallReq;
import cc.dfsoft.project.biz.base.inspection.entity.AnodeInstall;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 阳极安装dao接口
 * @author liaoyq
 *
 */
public interface AnodeInstallDao extends CommonDao<AnodeInstall, String> {

	/**
	 * 根据报验单ID 分页查询阳极安装记录
	 * @param anodeInstallReq
	 * @return
	 */
	Map<String, Object> queryAnodeInstall(AnodeInstallReq anodeInstallReq);

	/**
	 * 根据报验单ID 删除阳极安装记录
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
	 * @param req
	 * @return
	 */
	Map<String, Object> queryRecords(AnodeInstallReq req);

}
