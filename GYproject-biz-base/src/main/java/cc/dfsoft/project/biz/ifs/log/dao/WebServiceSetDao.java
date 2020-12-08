package cc.dfsoft.project.biz.ifs.log.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.maintain.dto.WebServiceSetReq;
import cc.dfsoft.project.biz.ifs.log.entity.WebServiceSet;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface WebServiceSetDao extends CommonDao<WebServiceSet, String> {

	/**
	 * 根据接口编号查询接口配置
	 * @author liaoyq
	 * @createTime 2018年4月22日
	 * @param serviceNo
	 * @param corpId 
	 * @return
	 */
	List<WebServiceSet> queryIsOpenByNO(String serviceNo, String corpId);

    WebServiceSet queryWebServiceSet(String interfaceNo, String corpId);

	Map<String, Object> querywebserviceSetMap(WebServiceSetReq webServiceSetReq);
}
