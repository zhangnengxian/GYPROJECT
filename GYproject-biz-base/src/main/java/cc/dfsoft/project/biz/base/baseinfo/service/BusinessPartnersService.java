package cc.dfsoft.project.biz.base.baseinfo.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;


public interface BusinessPartnersService {
	
	/**
	 * 单位列表查询
	 * @author pengtt
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryBusinessPartners(BusinessPartnersReq businessPartnersReq) throws ParseException;
	
	/**
	 * 单位详述查询
	 * @author pengtt
	 * @param id
	 * @return
	 */
	public BusinessPartners viewBusinessPartnersById(String id);
	
	/**
	 * 保存，更新单位信息
	 * @param BusinessPartners
	 */
	public void saveBusinessPartners(BusinessPartners businessPartners);
	
	/**
	 * 删除单位信息
	 * @author pengtt
	 * @param cuId
	 */
	public void deleteBusinessPartners(String cuId);
	/**
	 * 业务合作伙伴列表以树节点展示
	 * @return
	 */
	public List<Map<String,Object>> getDataTree(String type);
	
	/**
	 * 查找分包单位
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	public List getCuUnit();
	
}
