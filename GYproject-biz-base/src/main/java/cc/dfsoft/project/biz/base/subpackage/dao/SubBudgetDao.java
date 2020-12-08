package cc.dfsoft.project.biz.base.subpackage.dao;


import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.SubBudgetReq;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SubBudgetDao extends CommonDao<SubBudget,String>{

	/**
	 * 根据工程Id查施工预算
	 * @param projId
	 * @return
	 */
	SubBudget findByProjId(String projId);

	/**
	 *施工预算打印列表查询
	 * @param subBudgetReq
	 * @return
	 */
	Map<String, Object> querySubBudgetPrint(SubBudgetReq subBudgetReq)throws ParseException;

	/**
	 * 施工预算员派遣
	 * @param designerQueryReq
	 * @return
	 */
	Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq)throws ParseException;
	
}
