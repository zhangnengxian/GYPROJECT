package cc.dfsoft.project.biz.base.subpackage.service;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import cc.dfsoft.project.biz.base.subpackage.dto.PriceVersionDto;
import cc.dfsoft.project.biz.base.subpackage.dto.PricedBoqReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PricedBoq;

public interface PricedBoqService {
	/**
	 * 工程量标准查询（返回节点树）
	 * @param id 
	 * @return
	 */
	public List<Map<String, Object>>  queryQuantStandTree(String proId,String versionOfProj,String incIncraMode);
	
	/**
	 * 工程量标准列表查询
	 * @return
	 */
	public Map<String, Object> queryPricedBoq(PricedBoqReq pricedBoqReq) throws ParseException;
	/**
	 * 工程量标准详述查询
	 * @param id
	 * @return
	 */
	public PricedBoq viewPricedBoq(@RequestParam(required=true) String id);
	
	/**
	 * 保存，更新分包单位信息
	 * @param constructionUnit
	 * @return
	 */
	public void saveOrUpdatePricedBoq(PricedBoq pricedBoq);
	/**
	 * 删除分包单位信息
	 * @param cuId
	 */
	
	public void deletePricedBoq(String id);
	/**
	 * 新增版本
	 * @param constructionUnit
	 * @return
	 */
	public void saveOrUpdatePricedBoqBat(PriceVersionDto dto)throws ParseException;
	
}
