package cc.dfsoft.project.biz.base.subpackage.dao;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import cc.dfsoft.project.biz.base.subpackage.dto.PricedBoqReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PricedBoq;
import cc.dfsoft.uexpress.common.dao.CommonDao;




public interface PricedBoqDao extends CommonDao<PricedBoq,String>{
	

	public Map<String, Object> queryPricedBoq(PricedBoqReq pricedBoqReq) throws ParseException;

	/**
	 * 工程量标准详述查询
	 * @param id
	 * @return
	 */
	public PricedBoq viewPricedBoq(@RequestParam(required=true) String id);

	/**
	 * 根据版本号查工程量标准
	 * @param id
	 * @return
	 */
	public List<PricedBoq> findByVeid(String corpId,String versionOfProj,String incIncraMode);

}
