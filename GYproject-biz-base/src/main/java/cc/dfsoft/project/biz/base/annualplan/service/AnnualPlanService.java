package cc.dfsoft.project.biz.base.annualplan.service;

import cc.dfsoft.project.biz.base.annualplan.dto.AnnualPlanReq;
import cc.dfsoft.project.biz.base.annualplan.entity.AnnualPlan;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;

/**
 * 带气计划服务接口
 * @author cui
 * @createTime 2017-08-8
 *
 */
public interface AnnualPlanService {

    /**
     * 查询年度计划列表
     * @param annualPlanReq
     * @return
     */
    Map<String,Object> queryAnnualPlan(AnnualPlanReq annualPlanReq);

    /**
     * @author cui
     * @createTime 2017-08-8
     * 保存年度计划
     * @param annualPlan
     * @return
     */
    void saveAnnualPlan(AnnualPlan annualPlan);

    /**
     * 获取数据库时间
     * @return
     */
    java.util.Date getDatabaseDate();
    
    /**
     * 年度计划批量导入
     * @author fuliwei
     * @createTime 2017年8月31日
     * @param 
     * @return
     */
	public void batInsertCostSum(JSONArray jsonArr);
    
	/**
	 * 删除年度计划
	 * @author fuliwei
	 * @createTime 2017年10月9日
	 * @param 
	 * @return
	 */
	public String deleteAnnualPlan(String planId);
}
