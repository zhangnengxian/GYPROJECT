package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pengtt
 * @CreateTime 2016-06-21
 *
 */
public interface ProjectDao extends CommonDao<Project, String>{

	/**
	 *
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> queryProject(ProjectQueryReq projectQueryReq) throws ParseException;

	public Map<String, Object> queryProjectByStatus(ProjectQueryReq projectQueryReq,List<String> projStuList) throws ParseException;


	public Map<String, Object> getData(String projId);


	/**
	 * 修改待办人
	 * @param doer
	 * @param projId
	 */
	public void updateToDoerById(String doer, String projId);
	/**
	 * 按工程编号查询
	 * @param projNo
	 * @return
	 */
	public List<Project> findByProjNo(String projNo);

	/**
	 * 生成工程编号
	 * @return
	 */
	public String getProjNo();

	/**
	 * 该方法仅用于工程施工、工程报验工程列表
	 * 工程施工：所查询工程状态为待施工至待结算终审 or 工程状态为已竣工 且结束时间至当前时间小于等于3个月的工程记录
	 * 工程报验：所查询工程状态为施工中至待结算终审 or 工程状态为已竣工 且结束时间至当前时间小于等于3个月的工程记录
	 * @author pengtt
	 * @createTime 2016-08-03
	 * @param projectQueryReq
	 * @param projStatusId 多个工程id
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> queryProject(ProjectQueryReq projectQueryReq,String[] projStatusId) throws ParseException;

	/**
	 * 工程分布查询
	 * @param projectQueryReq
	 * @return
	 */
	public Map<String, Object> queryDistributionProject(ProjectQueryReq projectQueryReq) throws ParseException;

	/**
	 * 工程条件查询 （有补充协议）
	 * @param projectQueryReq
	 * @return
	 */
	public Map<String, Object> queryProjectcostSummary(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 * 用于近一年各区工程数量对比分析图方法
	 * @author pengtt
	 * @createTime 2016-09-02
	 * @return
	 */
	public List<Map<String,Object>> everyAreaProjectNum();

	/**
	 * 用于近一年退单工程数量分析图方法
	 * @author pengtt
	 * @createTime 2016-09-05
	 * @return
	 */
	public List<Map<String,Object>> backReasonProjectNum();

	/**
	 * 用于近一年工程款收付对比分析图查询
	 * @author pengtt
	 * @createTime 2016-09-06
	 * @return
	 */
	public List<Map<String,Object>> paymentNum();

	/**
	 * 施工管理分析-----各施工管理处施工进度统计分析图
	 * @author pengtt
	 * @createTime 2016-09-06
	 * @return
	 */
	public List<Map<String, Object>> projectScheduleStatistics();

	/**
	 * 该方法仅用于工程自检工程列表
	 * @author pengtt
	 * @createTime 2016-09-23
	 * @param projectQueryReq
	 * @param projStatusId 多个工程id
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> queryProjectAcceptance(ProjectQueryReq projectQueryReq,String[] projStatusId) throws ParseException;

	/**
	 * 该方法仅用于立项申请工程列表
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> queryAcceptProject(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 *
	 * @param projectQueryReq
	 * @param projStatusId
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> queryProjStatis(ProjectQueryReq projectQueryReq,String[] projStatusId) throws ParseException;

	/**
	 * 根据状态返回工程数量
	 * @author fuliwei
	 * @createTime 2017年7月23日
	 * @param
	 * @return
	 */
	public int queryWorkNotice(String statusId);

	/**
	 * 工程列表查询联合验收专用
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> queryProjectForJoint(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 * 年度计划id
	 * @author fuliwei
	 * @createTime 2017年10月9日
	 * @param
	 * @return
	 */
	public List<Project> findByPlanId(String planId);

	/**
	 * 查可否带气工程(查带气开通用到-20171122)
	 * @author cui
	 * @createTime 2017年11月22日
	 * @param
	 * @return
	 */
	List<Project> findByGas(String isGas);

	/**
	 * 查询受理审核状态工程
	 * @param req
	 * @return
	 */
	public  Map<String, Object> getAccepAuditList(ProjectQueryReq req);
	public Map<String,Object> queryHistoryProject(ProjectQueryReq projectQueryReq) throws ParseException;

	Map<String,Object> toDesignDispatchProjectList(ProjectQueryReq projectQueryReq);


	//根据工程id查询工程、应收应付流水
	public List<Project> getInsttasks(String projId);


    Map<String, Object> queryProjectMap(ProjectQueryReq probDoctReq);
	/**
	 * 施工单位数据过滤sql
	 * @author liaoyq
	 * @createTime 2019-4-17
	 * @param loginInfo
	 * @param post
	 * @param menuId
	 * @return
	 */
	StringBuffer cuUnitFilter(LoginInfo loginInfo, String post,String menuId);

	/**
	 * 监理单位数据过滤sql
	 * @author liaoyq
	 * @createTime 2019-4-17 
	 * @param loginInfo
	 * @param post
	 * @param menuId
	 * @return
	 */
	StringBuffer suUnitFilter(LoginInfo loginInfo, String post, String menuId);

	String addDeptIdLikeQuery(String deptId, String deptTransfer);

	/**
	 * 
	 * 注释：合同管理系统-查询工程信息接口
	 * @author liaoyq
	 * @createTime 2019年12月5日
	 * @param queryReq
	 * @return
	 * @throws ParseException 
	 *
	 */
	public Map<String, Object> queryProjectSerivce(ProjectQueryReq queryReq) throws ParseException;
}
