package cc.dfsoft.uexpress.biz.sys.dept.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.biz.sys.dept.dao.OperateLogDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.ProjectTotalStatictisReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.OperateLog;
import cc.dfsoft.uexpress.biz.sys.dept.service.OperateLogService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

/**
 * 
 * @author cui
 * @createTime 2016-12-1
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OperateLogServiceImpl implements OperateLogService {

	/**
	 * 操作日志Dao
	 */
	@Resource
	OperateLogDao operateLogDao;

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOperteLog(LoginInfo loginInfo, String device) {

		OperateLog operateLog = new OperateLog();
		if(loginInfo!=null){
			operateLog.setOperateLogId(IDUtil.getUniqueId(Constants.MODULE_CODE_AUTH));
			operateLog.setStaffId(loginInfo.getStaffId());
			operateLog.setDeptId(loginInfo.getDeptId());
			operateLog.setOperateType("1");
			operateLog.setOperateTime(operateLogDao.getDatabaseDate());
			operateLog.setIsMobile(device);
			operateLog.setXaxis(loginInfo.getLongitude());
			operateLog.setYaxis(loginInfo.getLatitude());
			operateLogDao.save(operateLog);
			//device=
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOperteLogOut(LoginInfo loginInfo, String device) {
		// TODO Auto-generated method stub
		OperateLog operateLog = new OperateLog();
		if(loginInfo!=null){
			operateLog.setOperateLogId(IDUtil.getUniqueId(Constants.MODULE_CODE_AUTH));
			operateLog.setStaffId(loginInfo.getStaffId());
			operateLog.setDeptId(loginInfo.getDeptId());
			operateLog.setOperateType("2");
			operateLog.setOperateTime(operateLogDao.getDatabaseDate());
			operateLog.setIsMobile(device);
			operateLogDao.save(operateLog);
		}
	}
	
	/**
	 * 统计当前登陆人信息
	 * @author fuliwei
	 * @createTime 2016-12-12
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryMapStatistics(ProjectTotalStatictisReq projectTotalStatictisReq) {
		List<Map<String,Object>> list=operateLogDao.queryMapStatistics(projectTotalStatictisReq);
		return list;
	}
}
