package cc.dfsoft.project.biz.base.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.OperateRecordLogDao;
import cc.dfsoft.project.biz.base.project.entity.OperateRecordLog;
import cc.dfsoft.project.biz.base.project.service.OperateRecordLogService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;

/**
 * 操作日志接口实现
 * @author Yuanyx
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class OperateRecordLogServiceImpl implements OperateRecordLogService{

	/** 操作日志Dao*/
	@Resource
	OperateRecordLogDao operateRecordLogDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void createOperateRecordLog(String orlId, String operateType, String businessId, String operateContent) {
		//取得登录人信息
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		OperateRecordLog operateRecordLog = new OperateRecordLog();
		operateRecordLog.setOrlId(orlId);
		operateRecordLog.setOperateType(operateType);
		operateRecordLog.setBusinessId(businessId);
		operateRecordLog.setOperateDept(loginInfo!=null?loginInfo.getDeptId():"1");
		operateRecordLog.setOperateCsr(loginInfo!=null?loginInfo.getStaffId():"1");
		operateRecordLog.setOperateTime(operateRecordLogDao.getDatabaseDate());
		operateRecordLog.setOperateContent(operateContent);
		operateRecordLogDao.save(operateRecordLog);
	}

	@Override
	public OperateRecordLog findLatelyLog(String operateType, String businessId) {
		return operateRecordLogDao.findLatelyLog(operateType,businessId);
	}
}
