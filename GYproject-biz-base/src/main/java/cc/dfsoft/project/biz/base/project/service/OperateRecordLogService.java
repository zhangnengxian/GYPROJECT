package cc.dfsoft.project.biz.base.project.service;

import cc.dfsoft.project.biz.base.project.entity.OperateRecordLog;

/**
 * 操作日志
 * @author Yuanyx
 *
 */
public interface OperateRecordLogService{

	/**
	 * 创建操作日志记录
	 * @param orlId
	 * @param operateType
	 * @param businessId
	 * @param operateContent
	 */
	void createOperateRecordLog(String orlId, String operateType, String businessId, String operateContent);

	/**
	 * 根据日志类型查找最近一次日志记录
	 * @param operateType
	 * @param businessId
	 * @return
	 */
	OperateRecordLog findLatelyLog(String operateType, String businessId);
}
