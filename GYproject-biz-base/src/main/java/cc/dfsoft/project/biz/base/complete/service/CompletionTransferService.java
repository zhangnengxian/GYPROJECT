package cc.dfsoft.project.biz.base.complete.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.CompletionTransfer;

/**
 * 
 * @author cui
 *
 */
public interface CompletionTransferService {


	/**
	 * 查询资料流转单
	 * @param completionTransferReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryCompletionTransfer(CompletionTransferReq completionTransferReq);

	/**
	 * 保存资料流转单
	 */
	void saveCompletionTransfer(CompletionTransfer completionTransfer) throws Exception;
	}

