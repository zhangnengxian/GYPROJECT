package cc.dfsoft.project.biz.base.change.dao;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.BudgetTotalTable;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface MaterialChangeDao extends CommonDao<MaterialChange, String>{
	public Map<String,Object> queryMaterialChange(MaterialChangeReq materialChangeReq);

	public List<MaterialChange> findByType(String cmId, String mcType);

	public List<MaterialChange> findByProjId(String projId);
	
	/**
	 * 变更记录导入材料表-查找是否已存材料变更表
	 * @param jsonArr 
	 * @param req  
	 * 
	 */
	public List<MaterialChange> queryMaterialChangeList(MaterialChangeReq req);
	
}
