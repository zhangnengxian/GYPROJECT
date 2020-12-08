package cc.dfsoft.project.biz.ifs.log.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.project.dto.WebserviceLogReq;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;

/**
 * 接口日志service接口
 * @author lenovo
 *
 */
public interface WebserviceLogService {

	/**
	 * 增加日志
	 * @param webserviceLog
	 */
	void saveOrUpdate(WebserviceLog webserviceLog);

	/**
	 * 查询接口日志
	 * @author liaoyq
	 * @createTime 2018年8月7日
	 * @param webserviceLogReq
	 * @return
	 */
	Map<String, Object> queryWebServiceLog(WebserviceLogReq webserviceLogReq);

    void saveSynchronizedLog(String projNo, String interfaceNo, ResultMsg resultMsg, Map map, String url);


    boolean isJson(String string);
}
