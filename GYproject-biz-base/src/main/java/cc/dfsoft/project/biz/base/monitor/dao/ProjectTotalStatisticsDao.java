package cc.dfsoft.project.biz.base.monitor.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ProjectTotalStatisticsDao extends CommonDao<Project, String>{
	
	/**
     * 查询实时的工程总量统计数据
     * @return
     */
	public Map queryProjectByType();
	
	/**
     * 查询能流图数据
     * @return
     */
	public Map queryProjectDataForSankey();
	
	/**
     * 查询收付款的统计数据
     * @return
     */
	public Map queryProjectOfPayment();

	
	/**
	 * 安全质量查询
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	public Map querySafetyQualityStatistics();
	
	/**
	 * 工程项目总体信息查询-按规模划分
	 * @author
	 * @createTime 2016-11-02
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryScaleAmountStatistics();
	
	
	/**
	 * 工程项目总体信息查询-按区域(table查询用)
	 * @author
	 * @createTime 2016-11-05
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryAreaMountStatistics();
	
	/**
	 * 工程项目总体信息查询-区域划分(饼图查询用)
	 * @author
	 * @createTime 2016-12-05
	 * @param
	 * @return
	 */
	public List<Map<String,Object>> queryAreaSumAmountStatistics();

	
	/**
     * 查询各阶段工程数量的汇总数据
     * @return
     */
	public Map queryProjectOfState();
	
	/**
     * 查询各阶段工程数量的明细数据
     * @param status 状态字符串
     * @return
     */
	public Map queryProjectOfStateDetail(ProjectQueryReq p);

	
	/**
	 * 报表屏首页 去年结转 、本年新增
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryLastYearAndThisYearInfo();
	
	
	
	/**
	 * 报表屏首页 转天然气和退单
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryAlreadyFinishedSum();
	
	/** 报表屏首页 在建工程
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryConstructingProject();
	
	/** 报表屏首页各施工处
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryManagementOffice();
	
	
	/**
	 * 用于近一年每月立项数目
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	public List<Map<String,Object>> queryLastMonthsNum();

	
	/**
	 * 查询工程的汇总数据，按月进行分类统计
	 * @param startDate 起始日期
	 * @param endDate   终止日期
	 * @param fieldName 查询字段的名称
	 * @return
	 */
	public List<Map> queryProjectNumOfMonthByDate(String fieldName) throws Exception;
	
	/**
	 * 用于工程项目第1个grid--统计当年和去年的工程数量、金额
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	public List<Map<String,Object>> queryLastYearAndThisYearNum();
	
	/**
	 * 用于工程项目第2个grid --统计当年的民用、车用、公用工程数量
	 * @author fuliwei
	 * @createTime 2017-01-07
	 * @return List
	 */
	public List<Map<String,Object>> queryProjectTypeNum();
	
	
	/**
	 * 用于工程项目第3个grid-按区域统计当年工程数量和金额
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryProjectAreaNum();
	
	/**
	 * 用于工程项目第4个grid-按阶段统计当年工程数量
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryProjectStageNum();
	
	/**
	 * 用于工程项目第5个grid-按立项、签约统计当年工程数量
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> queryProjectAcceptAndContractNum();
	
	/**
	 * 用于工程项目第6个grid-按结款、未结统计
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	public List<Map<String, Object>> querySignAndAladyCharge();
	
	/**
	 * 用于工程施工 各施工处 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryConstructionNumsAndAmount();
	
	/**
	 * 用于工程施工 各分包单位 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryCuUnitNumsAndAmount();
	
	/**
	 * 用于工程施工 各施工处扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryContructionUnitSafetyNums();
	
	/**
	 * 用于工程施工 各分包单位扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryCuUnitSafetyNums();
	
	
	/**
	 * 用于工程施工 各施工处按工程规模统计-民用
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryContructionUnitScaleNums();
	
	/**
	 * 用于工程施工 各施工处按工程规模统计-公用
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryContructionUnitScalePublicNums();
	
	/**
	 * 用于工程施工 各分包单位按工程规模统计-民用
	 * @author fuliwei
	 * @createTime 2017-1-11
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryCuUnitScaleNums();
	
	
	/**
	 * 用于工程施工 各分包单位按工程规模统计-公用
	 * @author fuliwei
	 * @createTime 2017-1-11
	 * @param
	 * @return Map<String, Object>
	 */
	public List<Map<String, Object>> queryCuUnitScalePublicNums();
	
	
	/**
	 * 用于近一年每月施工处扣分数
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	public List<Map<String, Object>> queryContructionUnitScore ();
	
	/**
	 * 用于当年工程进度
	 * @author fuliwei
	 * @createTime 2017-01-11
	 * @return
	 */
	public List<Map<String, Object>> queryProjectSchedule();

    List<Map<String,Object>> queryConstructionNumsAndAmountRight();
}
