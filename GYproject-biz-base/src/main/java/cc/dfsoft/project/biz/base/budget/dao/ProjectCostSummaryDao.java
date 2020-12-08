package cc.dfsoft.project.biz.base.budget.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.ProjectCostSummary;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author zhangjj
 * @CreateTime 2016-07-06
 *
 */
public interface ProjectCostSummaryDao extends CommonDao<ProjectCostSummary, String>{
	
	/**
	 * 查询单位工程费用汇总
	 * @param projCostSummaryReq
	 * @return
	 */
	public Map<String,Object> queryCostSummary(ProjCostSummaryReq projCostSummaryReq);
	
	public List<ProjectCostSummary> queryCostSummaryList(ProjCostSummaryReq projCostSummaryReq);
}
