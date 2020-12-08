package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.entity.OperateRecordLog;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface OperateRecordLogDao extends CommonDao<OperateRecordLog, String>{

	/**
	 * 根据日志类型查找最近一次日志记录
	 * @param operateType
	 * @param businessId
	 * @return
	 */
	OperateRecordLog findLatelyLog(String operateType, String businessId);
}
