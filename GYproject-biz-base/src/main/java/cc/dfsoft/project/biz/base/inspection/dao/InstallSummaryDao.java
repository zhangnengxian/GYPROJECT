package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.InstallSummaryReq;
import cc.dfsoft.project.biz.base.inspection.entity.InstallSummary;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 安装汇总报验dao接口
 * @author liaoyq
 *
 */
public interface InstallSummaryDao extends CommonDao<InstallSummary, String> {

	/**
	 * 根据报验单id 删除安装汇总记录信息
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * 根据报验id 分页查询安装汇总记录
	 * @param installSummaryReq
	 * @return
	 */
	Map<String, Object> queryInstallSummary(InstallSummaryReq installSummaryReq);

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
	Map<String, Object> queryRecords(InstallSummaryReq req);

}
