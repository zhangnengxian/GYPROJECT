package cc.dfsoft.uexpress.biz.sys.dept.service;

import java.util.List;
import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.ProjectTotalStatictisReq;

/**
 * 操作日志管理
 * 
 * @author cui
 *
 */
public interface OperateLogService {

	/**
	 * 登陆日志保存
	 * @param loginInfo
	 * @param device
	 */
	void saveOperteLog(LoginInfo loginInfo, String device);
	/**
	 *  注销日志保存
	 * @param loginInfo
	 * @param device
	 */
	void saveOperteLogOut(LoginInfo loginInfo, String device);
	
	
	
	/**
	 * 统计当前登陆人信息
	 * @author fuliwei
	 * @createTime 2016-12-12
	 * @return
	 */
	public List<Map<String,Object>> queryMapStatistics(ProjectTotalStatictisReq projectTotalStatictisReq);
}
