package cc.dfsoft.project.biz.base.budget.service;

import java.text.ParseException;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.budget.dto.BudgetChangeReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.BudgetAdjust;

public interface BudgetAdjustService {
	
	/**
	 * 批量添加单位工程费用汇总表
	 * @param jsonArr 数据对象
	 * @param projId  工程id
	 * @param costType 费用类型
	 */
	public void batInsertCostSum(JSONArray jsonArr,BudgetReq req);
	
	/**
	 * 批量添加单位工程费用汇总表-预算总表
	 * @param jsonArr 数据对象
	 * @param projId  工程id
	 * @param costType 费用类型
	 */
	public void batInsertTotalCostSum(JSONArray jsonArr,BudgetReq req);
	
	
	
	/**
	 * 根据工程id查询预算调整表
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param projId
	 * @return BudgetAdjust
	 */
	public BudgetAdjust queryByprojId(String projId);
	
	/**
	 * 更新预算调整表
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param budgetAdjust
	 * @return 
	 */	
	public void updateBudgeAdjsut(BudgetAdjust budgetAdjust);

	/**
	 * 预算总表调整列表查询
	 * @param budgetChangeReq
	 * @return
	 */
	public Map<String, Object> queryBudgetAdjust(BudgetChangeReq budgetChangeReq) throws ParseException;
}
