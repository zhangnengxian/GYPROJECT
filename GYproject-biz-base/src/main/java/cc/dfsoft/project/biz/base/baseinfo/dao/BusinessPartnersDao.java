package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author pengtt
 * @createTime 2016-07-15
 *
 */
public interface BusinessPartnersDao extends CommonDao<BusinessPartners,String>{
	
	/**
	 * 外部单位列表查询
	 * @author pengtt
	 * @param businessPartnersReq
	 * @return
	 */
	public Map<String, Object> queryBusinessPartners(BusinessPartnersReq businessPartnersReq) throws ParseException;
	
	/**
	 * 查找分包单位
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	public List<BusinessPartners> getCuUnit();
}
