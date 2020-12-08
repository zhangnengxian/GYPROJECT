package cc.dfsoft.project.biz.ifs.project.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 报装接口
 * @author liaoyq
 * @createTime 2018年8月16日
 */
@WebService(targetNamespace = "http://accept.dfsoft.cc/service")
public interface IProjectAccept {
	/**
	 * 工程报装接口
	 * @param userName
	 * @param password
	 * @param jsonStr
	 * @return
	 */
	@WebMethod
	@WebResult(name="resJson")
	public String projAcceptDone(@WebParam(name="usname") String userName,@WebParam(name="pwd")String password,@WebParam(name="data_jeson")String jsonStr);
}
