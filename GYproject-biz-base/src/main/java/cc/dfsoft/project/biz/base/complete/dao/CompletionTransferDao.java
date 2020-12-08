package cc.dfsoft.project.biz.base.complete.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.CompletionTransfer;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author cui
 *
 */
public interface CompletionTransferDao extends CommonDao<CompletionTransfer, String>{

	/**
	 * 条件查询资料流转单
	 * @param completionTransferReq
	 * @return
	 */
	Map<String, Object> queryCompletionTransfer(CompletionTransferReq completionTransferReq);

}
