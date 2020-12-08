package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.PipelineInstallReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 管道安装dao层
 * @author liaoyq
 *
 */
public interface PipeLineInstallDao extends CommonDao<PipeLineInstall, String> {

	/**
	 * 根据报验单ID删除管道安装报验记录信息
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * 分页查询管道安装记录信息
	 * @param pipelineInstallReq
	 * @return
	 */
	Map<String, Object> queryPipelineInstall(
			PipelineInstallReq pipelineInstallReq);

	/**
	 *　根据记录ID回写报验单ＩＤ
	 * @author laioyq
	 * @createTime 2017-7-20
	 * @param pcId 报验单ＩＤ
	 */
	void updatePcIdByRecordId(String id, String pcId);

	Map<String, Object> queryRecords(PipelineInstallReq pipelineInstallReq);

	/**
	 * 清空pcId
	 * @param pcId
	 */
	void updateByPcId(String pcId);

}
