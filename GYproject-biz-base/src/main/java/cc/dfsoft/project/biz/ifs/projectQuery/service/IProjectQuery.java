package cc.dfsoft.project.biz.ifs.projectQuery.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 查询工程信息接口
 * @author liaoyq
 * @createTime 2018年8月16日
 */
@WebService(targetNamespace="http://query.dfsoft.cc/service")
public interface IProjectQuery {
	/**
	 * 通过工程编号查询工程进度
	 * @param userName
	 * @param password
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	@WebResult(name="retJson")
	public String projQueryDone(@WebParam(name="usname") String userName,@WebParam(name="pwd")String password,@WebParam(name="data_jeson")String jsonStr);
}
