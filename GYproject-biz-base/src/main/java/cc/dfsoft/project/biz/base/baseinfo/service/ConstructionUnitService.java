package cc.dfsoft.project.biz.base.baseinfo.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.entity.ConstructionUnit;
import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractDTO;

public interface ConstructionUnitService {
	/**
	 * 分包单位列表查询
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryConstructionUnit(ConstructionUnitReq constructionUnitReq) throws ParseException;
	/**
	 * 分包单位详述查询
	 * @param id
	 * @return
	 */
	public ConstructionUnit viewConstructionUnitById(String id);
	/**
	 * 保存，更新分包单位信息
	 * @param constructionUnit
	 * @return
	 */
	public String saveConstructionUnit(ConstructionUnit constructionUnit);
	/**
	 * 删除分包单位信息
	 * @param cuId
	 */
	public void deleteConstructionUnit(String cuId);
	
	/**
	 * 分包单位详述
	 * @author pengtt
	 * @createTime 2016-09-03
	 * @param id
	 * @return
	 */
	public SubContractDTO viewsubContract(String id);
	
	/**
	 * 分包补充协议详述
	 * @author ht.hu
	 * @param id
	 * @return
	 */
	public SubContractDTO viewSubAgreementContract(String id);
	/**
	 * 分包安全协议详述
	 * @param id
	 * @return
	 */
	public SubContractDTO viewsubSafe(String id);
}
