package cc.dfsoft.project.biz.ifs.material.service;

import javax.jws.WebService;
@WebService
public interface MaterialService {
	
	/**
	 * 领用接口
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	public String doOutStore(String usname,String pwd,String materialReceiveStr);
}
