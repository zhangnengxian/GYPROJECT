package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.entity.ConstructionUnit;
import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ConstructionUnitDao extends CommonDao<ConstructionUnit,String>{
	/**
	 * 分包单位列表查询
	 * @param constructionUnitReq
	 * @return
	 */
	public Map<String, Object> queryConstructionUnit(ConstructionUnitReq constructionUnitReq) throws ParseException;
	/**
	 * 分包单位详述查询
	 * @param id
	 * @return
	 */
	public ConstructionUnit viewConstructionUnitById(String id);
}
