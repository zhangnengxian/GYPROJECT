package cc.dfsoft.project.biz.ifs.log.service;


import cc.dfsoft.project.biz.base.maintain.dto.WebServiceSetReq;

import java.util.Map;

/**
 *@Description: 接口服务
 *@author: zhangnx
 *@Date: 2019/11/27 20:25
 *@Version:1.0
 * */
public interface WebserviceSetService {

	Map<String, Object> querywebserviceSetMap(WebServiceSetReq webServiceSetReq);

	String switchStatus(String wsId);
}
