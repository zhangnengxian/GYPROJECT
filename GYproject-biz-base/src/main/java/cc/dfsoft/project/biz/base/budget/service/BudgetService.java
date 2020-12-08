package cc.dfsoft.project.biz.base.budget.service;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import com.alibaba.fastjson.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;


/**
 * 预算总表服务接口
 * @author pengtt
 * @createTime 2016-06-29
 *
 */
public interface BudgetService {

	public Budget queryBudgeByprojId(String projId);
	
	/**
	 * 批量添加单位工程费用汇总表
	 * @param jsonArr 数据对象
	 * @param
	 * @param
	 */
	public void batInsertCostSum(JSONArray jsonArr,BudgetReq req);
	
	/**
	 * 查询单位工程费用总结列表
	 * @param projCostSummaryReq
	 * @return
	 */
	public Map<String,Object> queryCostSummary(ProjCostSummaryReq projCostSummaryReq);
	/**
	 * 更新预算总表
	 * @param budget
	 */
	public void updateBudge(Budget budget);
	
	/**
	 * 根据工程id和预算类型id查询预算总表
	 * @author fuliwei
	 * @createTime 2016-7-14
	 * @param
	 * @return Budget
	 */
	public Budget queryById(String id,String budgetTypeId);
	/**
	 * 更新预算调整总表
	 * @param budget
	 */
	public void updateBudgeAdjust(Budget budget);
	/**
	 * 根据工程id查询费率
	 * @author zhangjj
	 * @createTime 2016-7-22
	 * @param
	 * @return ProjectRate
	 */
	public ProjectRate queryRateById(String id);
	/**
	 * 如果有调整记录则返回调整表，否则返回总表
	 * @author zhangjj
	 * @createTime 2016-8-11
	 * @param projId
	 * @param cmId
	 * @param mcType
	 * @return
	 */
	public Budget queryByType(String projId,String cmId,String mcType);
	
	/**
	 * 添加工程项目总价表
	 * @param jsonArr 数据对象
	 * @param
	 * @param
	 */
	public void batInsertTotalCostSum(JSONArray jsonArr,BudgetReq req);
	
	/**
	 * 工程项目总价表
	 * @param budgetTotalQueryReq
	 * @createTime 2016-11-18
	 * @return
	 */
	public Map<String,Object> queryBudgetTotal(BudgetTotalQueryReq budgetTotalQueryReq);
	
	/**
	 * 更新预算表
	 * @param budget
	 */
	public void updateBudget(Budget budget);
	
	/**
	 * 预算员派遣
	 * @param designerQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq) throws ParseException;
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2017-1-4
	 * @return String 
	 */
	public String updateProject(BudgetReq BudgetReq);
	
	/**
	 *预算结果登记 --查询审核记录
	 * @param projId
	 * @param StepId
	 * @param mrResult
	 * @return String
	 */
	public String queryManageRecord(String projId,String StepId,String mrResult,String mrLevel);
	
	/**
	 * 查询变更预算列表
	 * @author fuliwei
	 * @createTime 2017-1-23
	 * @param req
	 * @return Map<String,Object>
	 */
	public Map<String,Object> queryAdjustBudget(BudgetReq req) throws ParseException ;
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2016-06-29
	 * @param
	 * @return
	 */
	public void update(DesignDispatchDto designDispatchReq);
	
	/**
	 * 预算派工
	 * 更新工程信息
	 * @author liaoyq
	 * @createTime 2017-8-11
	 * @param designDispatchReq
	 */
	public void updateProject(DesignDispatchDto designDispatchReq);

	/**
	 * 保存或更新预算记录(包含附件上传)
	 * @author liaoyq
	 * @createTime 2017-8-14
	 * @param request
	 * @param uploadResult
	 * @param files 
	 * @return 记录主键ID
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public String updateBudgetFile(HttpServletRequest request,
			UploadResult uploadResult, MultipartFile[] files) throws Exception;

	/**
	 * 保存或更新预算记录
	 * @param budget 预算信息
	 * @return 记录主键ID
	 */
	public String budgetSave(Budget budget) throws Exception;

	/**
	 * 查询待预算审核的工程
	 * @author liaoyq
	 * @createTime 2017-8-15
	 * @param projectQueryReq
	 * @param auditLevel 审核级别
	 * @param stepId 工程步骤
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	public Map<String, Object> queryAuditProject(
			ProjectQueryReq projectQueryReq, String auditLevel, String stepId) throws ParseException;

	/**
	 * 预算方式确认
	 * @param budget
	 */
	public void saveProBudgetMethod(Budget budget);

	/**
	 * 预算列表查询
	 * @author wangang
	 * @createTime 2018-12-18
	 */
	public Map<String, Object> queryBudget(BudgetReq budgetReq) throws ParseException;

	/**
	 * 预算打印标记
	 * @param budgetId
	 * @return
	 */
	String signProjectBudgetPrint(String budgetId);

    boolean rollBackContainsBudget(String projId, String rollBackReason);
}
