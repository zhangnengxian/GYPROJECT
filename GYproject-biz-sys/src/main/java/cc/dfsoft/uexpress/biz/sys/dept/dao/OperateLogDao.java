package cc.dfsoft.uexpress.biz.sys.dept.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.dept.dto.ProjectTotalStatictisReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.OperateLog;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 操作日志DAO
 * @author cui
 *
 */
public interface OperateLogDao extends CommonDao<OperateLog, String>{
	
	/**
	 * 统计当前登陆人信息
	 * @author fuliwei
	 * @createTime 2016-12-12
	 * @return
	 */
	public List<Map<String,Object>> queryMapStatistics(ProjectTotalStatictisReq projectTotalStatictisReq);
	

}
