package cc.dfsoft.project.biz.base.complete.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author cui
 *
 */
public interface FilingDataDao extends CommonDao<FilingData, String>{

	/**
	 * 条件查询
	 * @param completionTransferReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryFilingData(CompletionTransferReq completionTransferReq) throws ParseException;

	/**
	 * 查交接单（项目Id）
	 * @param projId
	 * @return
	 */
	FilingData findByProjId(String projId);

}
