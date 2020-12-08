package cc.dfsoft.project.biz.base.budget.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.BudgetChangeReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author pengtt
 * @CreateTime 2016-06-29
 *
 */
public interface BudgetDao extends CommonDao<Budget, String>{

	Budget queryBudgeByprojId(String projId);
	
	/**
	 * 根据工程id和预算类型id查询预算总表
	 * @author fuliwei
	 * @createTime 2016-7-14
	 * @param
	 * @return Budget
	 */
	Budget queryById(String projId,String budgetTypeId);
	
	/**
	 * 根据工程id和预算类型id查询预算总表
	 * @author zhangjj
	 * @createTime 2016-8-11
	 * @param
	 * @return Budget
	 */
	Budget queryByType(String cmId,String mcType);

	/**
	 * 预算调整列表
	 * @param budgetChangeReq
	 * @return
	 */
	Map<String, Object> queryBudgetAdjust(BudgetChangeReq budgetChangeReq) throws ParseException;
	
	/**
	 * 预算员派遣
	 * @param designerQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq) throws ParseException;
	
	/**
	 *预算结果等级查询审核记录
	 * @param projId
	 * @param StepId
	 * @param mrResult
	 * @return String
	 */
	public String queryManageRecord(String projId,String StepId,String mrResult,String mrLevel);
	
	
	
	/**
	 * 根据工程id和预算类型id、变更过id查询预算总表
	 * @author fuliwei
	 * @createTime 2016-7-14
	 * @param
	 * @return Budget
	 */
	Budget queryById(String projId,String budgetTypeId,String cmId);
	
	/**
	 * 查询变更预算列表
	 * @author fuliwei
	 * @createTime 2017-1-23
	 * @param req
	 * @return Map<String,Object>
	 */
	public Map<String,Object> queryAdjustBudget(BudgetReq req)throws ParseException ;

	/**
	 * 获取预算类型
	 * @param projId
	 * @return
	 */
	public boolean getbudgetType(String projId);

	/**
	 * 查询预算打印列表
	 * @author wangang
	 * @createTime 2018-12-18
	 * @return Map<String,Object>
	 */
	public Map<String, Object> queryBudget(BudgetReq budgetReq) throws ParseException;
	
}
