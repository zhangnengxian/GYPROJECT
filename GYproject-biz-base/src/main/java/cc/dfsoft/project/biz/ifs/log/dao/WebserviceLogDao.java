package cc.dfsoft.project.biz.ifs.log.dao;

import cc.dfsoft.project.biz.base.project.dto.WebserviceLogReq;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;
/**
 * 接口日志dao接口层
 * @author liaoyq
 *
 */
public interface WebserviceLogDao extends CommonDao<WebserviceLog, String> {

	/**
	 * 查询接口调用日志
	 * @author liaoyq
	 * @createTime 2018年8月7日
	 * @param webserviceLogReq
	 * @return
	 */
	Map<String, Object> queryWebServiceLog(WebserviceLogReq webserviceLogReq);

    int getWebServiceLogListByProjIdResultType(String projId,String resultType);
}
