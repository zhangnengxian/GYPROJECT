package cc.dfsoft.project.biz.base.plan.dao;

import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public  interface ConstructionPlanDao extends CommonDao<ConstructionPlan, String>{
	/**
	 * 按工程编号查询
	 * @param conNo
	 * @return
	 */
	public  List<ConstructionPlan> findByProjNo(String projNo);
	
	/**
	 * 根据工程id查询施工计划
	 * @param id
	 * @return
	 */
	public ConstructionPlan viewPlanById(String id);
	
	
	/**
	 * @author pengtt
	 * 列表条件查询
	 * @createTime 2016-07-06
	 * @param constructionPlanQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> queryConstructionPlan(ConstructionPlanQueryReq constructionPlanQueryReq) throws ParseException;
	/**
	 * 统计工程计划
	 * @return
	 */
	public List<Map<String, Object>> countProjectNum(String name);

	/**
	 * 根据工程ID查询工程计划
	 * @param proID
	 * @return
	 */
	public ConstructionPlan findByProjId(String proID);
	
	
	/**
	 * 查询未派工的任务
	 * @author fuliwei
	 * @createTime 2017年11月21日
	 * @param 
	 * @return
	 */
	public List<ConstructionPlan> findNotDispacthPlan(String type);
	
	/**
	 * 查询任务单通知
	 * @author fuliwei
	 * @createTime 2017年11月22日
	 * @param 
	 * @return
	 */
	public List<ConstructionPlan> queryPlanNotice(ProjectQueryReq req,List<String> projStatus) ;
	
	
	/**
	 * 查询可付款申请工程
	 * @author fuliwei
	 * @createTime 2018年6月11日
	 * @param 
	 * @return
	 */
	public Map<String, Object>  queryPayProject(PaymentApplyReq  paymentApplyReq);

    boolean isExist(String projId);
}
