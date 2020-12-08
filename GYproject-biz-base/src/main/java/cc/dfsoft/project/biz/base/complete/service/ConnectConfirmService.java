package cc.dfsoft.project.biz.base.complete.service;


import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;

public interface ConnectConfirmService {
	
	/**
	 * 归档资料详述
	 * @param id
	 */
	FilingData jointDetail(String id);
	
	/**
	 * 保存归档资料
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	String saveConnect(FilingData filingData) throws ParseException, Exception;

	/**
	 * 条件查询
	 * @param completionTransferReq
	 * @throws ParseException 
	 */
	Map<String, Object> queryFilingData(CompletionTransferReq completionTransferReq) throws ParseException;

	/**
	 * 标记交接单打印
	 * @param projId
	 */
	void signConnectConfirmPrint(String projId);
}
