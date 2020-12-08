package cc.dfsoft.project.biz.base.plan.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;

public interface ConstructionPlanService {
	
	/**
	 * 保存合同
	 * @author 张萌
	 * @createTime 2016-07-04
	 * @param 
	 * @return
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public String savePlan(ConstructionPlan constructionPlan) throws ParseException, Exception;
	
	/**
	 * 分包页面根据工程id查询施工计划
	 * @author
	 * @createTime	2016-7-5
	 * @param	id 工程id
	 * @return	ConstructionPlan 施工计划
	 */
	public ConstructionPlan viewPlanById(String id);
	
	/**
	 * 列表条件查询
	 * @author pengtt
	 * @createTime 2016-07-06
	 * @param constructionPlanQueryReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String,Object> queryConstructionPlan(ConstructionPlanQueryReq constructionPlanQueryReq) throws ParseException;
	
	/**
	 * 该计划信息列表查询专用于计划审核页面
	 * @author pengtt
	 * @createTime 2016-07-06
	 * @param surveyInfoQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> queryConstructionPlanForAudit(ConstructionPlanQueryReq constructionPlanQueryReq) throws ParseException;
	
	/**
	 * 通过主键id查询详述 
	 * @param cpId
	 * @createTime 2016-07-06
	 * @return
	 */
    public ConstructionPlan detail(String cpId);
    /**
     * 根据工程编号查询施工计划
     * @param ProjNo
     * @return
     */
    public List<ConstructionPlan> findByProjNo(String ProjNo);
    /**
     * 统计工程计划
     * @return
     */
    public List<Map<String, Object>> countProjectNum(String name);
    
    /**
     * 根据工程编号获取施工计划编制显示对象
     * @param projID
     * @return
     */
    public ConstructionPlan converDTO(String projID);
    
    /**
     * 保存采购计划备注
     * @param constructionPlan
     */
    public void saveNotePlan(ConstructionPlan constructionPlan);
	
    /**
	 * 根据工程Id查计划
	 * @param id
	 * @return
	 */
	ConstructionPlan findByProjId(String id);

	/**
	 * 
	 * @param constructionPlan
	 */
	public String saveConstructionDispatch(ConstructionPlan constructionPlan)throws HibernateOptimisticLockingFailureException;

	/**
	 * 施工任务书标记打印
	 * @auther cui by 2017-9-6
	 * @param projId
	 */
    void signConstructionTaskPrint(String projId);
    /**
	 * 监理任务书标记打印
	 * @auther cui by 2017-11-17
	 * @param projId
	 */
    void signSupervisorTaskPrint(String projId);

    /**
     * 根据工程ID获取施工任务单
     * 组装施工任务单报表数据
     * @author liaoyq
     * @createTime 2017-12-27
     * @param projId
     * @return
     */
	public String findPrintDataByProjId(String projId,String type);
	
	/**
	 * 查询可付款申请工程
	 * @author fuliwei
	 * @createTime 2018年6月11日
	 * @param 
	 * @return
	 */
	public Map<String, Object>  queryPayProject(PaymentApplyReq  paymentApplyReq);
	
	/**
	 * 保存报审手续
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	public  void saveConProc(SubBudget subBudget);

	/**
	 * 注释：获取施工单位预算员，根据分公司ID和施工单位ID
	 * @author liaoyq
	 * @createTime 2019年6月3日
	 * @param cuId
	 * @param corpId
	 * @return
	 *
	 */
	Staff findCuBudgeter(String cuId, String corpId);

	/**
	 * 注释：根据业务合作伙伴单位ID，所属分包单位，查询相应职务下的操作人
	 * @author liaoyq
	 * @createTime 2019年9月2日
	 * @param busUnitId
	 * @param corpId
	 * @param post
	 * @return
	 *
	 */
	Staff findbusUnitOperator(String busUnitId, String corpId, String post);
}
