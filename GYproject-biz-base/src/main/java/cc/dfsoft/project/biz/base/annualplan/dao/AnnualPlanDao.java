package cc.dfsoft.project.biz.base.annualplan.dao;

import cc.dfsoft.project.biz.base.annualplan.dto.AnnualPlanReq;
import cc.dfsoft.project.biz.base.annualplan.entity.AnnualPlan;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;

/**
 * 
 * @author cui
 * @CreateTime 2017-8-8
 *
 */
public interface AnnualPlanDao extends CommonDao<AnnualPlan, String> {
    /**
      * 带气计划查询列表
      * @author cui
      * @CreateTime 2017-8-8
      * @param annualPlanReqReq
      * @return
      */
    Map<String,Object> queryAnnualPlan(AnnualPlanReq annualPlanReqReq);
    
    /**
     * 根据计划编号删除年度计划
     * @author fuliwei
     * @createTime 2017年8月31日
     * @param 
     * @return
     */
    public void deleteByPlanNo(String planNo,String deptId);
}