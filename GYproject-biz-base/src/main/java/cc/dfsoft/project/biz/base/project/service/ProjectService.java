package cc.dfsoft.project.biz.base.project.service;

import cc.dfsoft.project.biz.base.contract.dto.ProjectCostReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ProjectViewDto;
import cc.dfsoft.project.biz.base.project.entity.Project;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工程服务接口
 * @author pengtt
 * @createTime 2016-06-21
 *
 */
public interface ProjectService {

	/**
	 * 工程列表条件查询
	 * @author pengtt
	 * @createTime 2016-06-21
	 * @param projectQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException
	 */
	Map<String,Object> queryProject(ProjectQueryReq projectQueryReq) throws ParseException;
	Map<String,Object> queryProjectByTime(ProjectQueryReq projectQueryReq) throws ParseException;
	Map<String,Object> queryProjectByParam(ProjectQueryReq projectQueryReq,List<String> projStuList) throws ParseException;
	/**
	 * 受理申请区保存方法
	 * @author pengtt
	 * @createTime 2016-06-22
	 * @param project
	 * @throws Exception
	 */
	public String acceptTotalSave(Project project ,boolean flag)throws IOException, Exception;
	/**
	 *
	 * 按工程编号查询
	 * @author pengtt
	 * @createTime 2016-06-23
	 * @param projNo
	 * @return
	 */
	public List<Project> findByProjNo(String projNo);

	/**
	 * 按工程ID查询
	 * @param id 主键id
	 * @return
	 */
	public Project viewProject(String id);
	/**
	 * 录入工程造价信息
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void updateProjectCost(ProjectCostReq projectCostReq) throws Exception;

	/**
	 * 图纸审核工程列表条件查询
	 * @author fuliwei
	 * @createTime 2016-07-5
	 * @param projectQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException
	 */
	Map<String,Object> queryAuditProject(ProjectQueryReq projectQueryReq,String grade,String stepId) throws ParseException;

	/**
	 * 该详述仅用于工程日志页面
	 * @author pengtt
	 * @param ProjId
	 * @return
	 */
	public ProjectViewDto diaryViewProject(String ProjId);

	/**
	 * 获取工程编号
	 * @return
	 */
	public String getProjNo();
	/**
	 * 获取最大工程编号
	 * @return
	 */
	public String getProjMaxNo(String deptId,String projType,String contributionModelId);

	/**
	 * 根据id查询工程
	 * @author zhangjj
	 * @param ProjId
	 * @return
	 */
	public Project queryProjectById(String ProjId);

	/**
	 * 工程列表条件查询 ----- 该方法只用于工程施工、工程报验列表
	 * @author pengtt
	 * @createTime 2016-07-29
	 * @param projectQueryReq 查询条件dto
	 * @param  projStatus 多个工程状态
	 * @return Map<String,Object>
	 * @throws ParseException
	 */
	Map<String,Object> queryProjectView(ProjectQueryReq projectQueryReq,String[] projStatus) throws ParseException;

	/**
	 * 工程概述查询方法
	 * @author pengtt
	 * @createTime 2016-08-09
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String,Object> querySummaryProject(ProjectQueryReq projectQueryReq) throws ParseException;
	/**
	 * 工程概述查询方法(包含首页图片)
	 * @author zhangjingjing
	 * @createTime 2016-11-14
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String,Object> queryProjectPicture(ProjectQueryReq projectQueryReq) throws ParseException;


	/**
	 * 工程分布查询方法
	 * @param projectQueryReq
	 * @createTime 2016-08-29
	 * @return
	 */
	Map<String, Object> queryDistributionProject(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 * 工程条件查询 （有补充协议）
	 * @param projectQueryReq
	 * @return
	 */
	Map<String, Object> queryProjectcostSummary(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 * 项目管理分析-----近一年各区工程项目数量对比
	 * @author pengtt
	 * @createTime 2016-09-01
	 * @return
	 */
	public Map<String,Object> everyAreaProjectNum();

	/**
	 * 项目管理分析-----近一年退单工程项目数量统计
	 * @author pengtt
	 * @createTime 2016-09-05
	 * @return
	 */
	public Map<String,Object> backReasonProjectNum();

	/**
	 * 项目管理分析-----近一年工程款收付对比图
	 * @author pengtt
	 * @createTime 2016-09-06
	 * @return
	 */
	public Map<String,Object> paymentNum();

	/**
	 * 施工管理分析-----各施工管理处施工进度统计分析图
	 * @author pengtt
	 * @createTime 2016-09-06
	 * @return
	 */
	public Map<String,Object> projectScheduleStatistics();

	/**
	 * 工程签字列表查询
	 * @author pengtt
	 * @createTime 2016-09-21
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	List<Project> queryProjectSign(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 * 工程列表条件查询 ----- 该方法只用于工程自检列表
	 * @author pengtt
	 * @createTime 2016-09-23
	 * @param projectQueryReq 查询条件dto
	 * @param  projStatus 多个工程状态
	 * @return Map<String,Object>
	 * @throws ParseException
	 */
	Map<String,Object> queryProjectAcceptance(ProjectQueryReq projectQueryReq,String[] projStatus) throws ParseException;
	/**
	 * 工程列表条件查询---------该方法只用于立项申请工程列表
	 * @param projectQueryReq
	 * @return
	 */
	Map<String, Object> queryAcceptProject(ProjectQueryReq projectQueryReq)throws ParseException;
	/**
	 * 退单确认
	 * @param projId
	 * @return
	 */
	String informSave(String projId);

	/**
	 * 要更正的工程列表
	 * @param projectQueryReq
	 * @return
	 */
	Map<String,Object> queryModifyProject(ProjectQueryReq projectQueryReq) throws ParseException;

	/**
	 * 工程列表条件查询 ----- 该方法只用于报验系统屏
	 * @author
	 * @createTime 2017-1-4
	 * @param projectQueryReq 查询条件dto
	 * @param  projStatus 多个工程状态
	 * @return Map<String,Object>
	 * @throws ParseException
	 */
	Map<String,Object> queryProjStatis(ProjectQueryReq projectQueryReq,String[] projStatus) throws ParseException;
	/**
	 * 工程更新反馈信息
	 * @param projId
	 * @param procureFeedback
	 */
	void updateFeedInfo(String projId, String procureFeedback);
	/**
	 * 工程列表查询-------------仅限设计出图
	 * @param projectQueryReq
	 * @return
	 */
	Map<String, Object> queryProjectByNetTime(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 * 工程材料出货完成标记
	 * @author fuliwei
	 * @createTime 2017年2月19日
	 * @param
	 * @return
	 */
	public void signProject(String projId);

	/**
	 * 计划立项
	 * @author fuliwei
	 * @createTime 2017年9月9日
	 * @param
	 * @return
	 */
	public String acceptPlanTotalSave(Project project,String menuId ,boolean flag)throws IOException;

	/**
	 * 工程列表查询联合验收专用
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryProjectByTimeForJoint(ProjectQueryReq projectQueryReq)throws ParseException;

	/**
	 * 查询工程派遣情况
	 * @author fuliwei
	 * @createTime 2017年12月27日
	 * @param
	 * @return
	 */
	public Project viewProjectDispatch(String projId,String stepId);
	/**
	 * 获得登录人部门信息（统计分析数据过滤）
	 * @return
	 */
	Map<String, String> getDeptInfoForStatistic();

	boolean isToCall(String projId, String serviceNo);
	/**
	 * 查询两个工程状态之间使用的日期时限
	 * @author liaoyq
	 * @createTime 2018年5月25日
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryProjectLimitTime(ProjectQueryReq projectQueryReq) throws ParseException;

	/**
	 * 合同修改时保存工程规模
	 * wang.hui.jun
	 * 2018.06.26
	 */
	public String SaveProjectScale(Project project, boolean flag) throws Exception;
	/**
	 * 接口报装受理工程
	 * @author liaoyq
	 * @createTime 2018年8月16日
	 * @param project
	 * @param
	 * @param acceptDto
	 * @return
	 */
	Project acceptService(Project project,
						  cc.dfsoft.project.biz.ifs.project.dto.ProjectAcceptDto acceptDto,
						  String operateType);
	/**
	 *
	 * 根据工程ID取工程计划表中的信息然后转存到工程表中
	 * @author wanghuijun
	 * @createTime 2018年7月26日
	 * @param projId
	 * @return
	 * @throws Exception
	 */
	public String saveProjectPlanInfo(String projId) throws Exception;
	/**
	 * 保存工程的预算方式
	 * @author liaoyq
	 * @createTime 2018-9-8
	 * @param proj
	 */
	void saveProBudgetMethod(Project proj);
	/**
	 * 工程详述，查询工程一些详述信息
	 * @param project
	 * @return
	 * @throws ParseException
	 */
	Project viewDetial(Project project) throws ParseException;
	/**
	 * 描述:根据原有计划设计天数，延期天数，获取计划设计完成日期（除去节假日、周末）
	 * @author liaoyq
	 * @createTime 2018年10月17日
	 * @param designInfo
	 * @return
	 */
	Date queryDuPlCompletedDate(DesignInfo designInfo);

	Map<String,Object> toDesignDispatchProjectList(ProjectQueryReq projectQueryReq);


	public Project getData(String peojId);

	/**
	 * 描述:根据工程id查询工程表、应收应付表
	 * @author wangang
	 * @createTime 2018年12月14日
	 * @param projId
	 * @return
	 */
	public Project getInsttasks(String projId);

	/**
	 * 描述:更新工程表
	 * @author wangang
	 * @createTime 2018年12月14日
	 * @param project
	 * @return
	 */
	public void updateProject(Project project);

	/**
	 * 描述:保存安装任务下达
	 * @author wangang
	 * @createTime 2018年12月14日
	 * @return
	 */
	public void saveInstTasks(Project project);

	/**
	 * 修改待办人
	 * @param doer
	 * @param projId
	 */
	public void updateToDoerById(String doer,String projId);

    Map<String, Object> queryProjectMap(ProjectQueryReq probDoctReq);

    boolean updateProjectRelationInfo(Project project);

    Project findById(String projId);

    String addDeptIdLikeQuery(String deptId);
    /**
	 * 
	 * 注释：查看接口是否是实时同步
	 * @author liaoyq
	 * @createTime 2019年10月10日
	 * @param projId
	 * @param serviceNo
	 * @return
	 *
	 */
	boolean isSynchronization(String projId, String serviceNo);
	/**
	 * 
	 * 注释：根据工程信息查询到该工程类型的当前工作流配置
	 * @author liaoyq
	 * @createTime 2019年10月17日
	 * @param pro
	 * @return
	 *
	 */
	String queryProjectFlow(Project pro);

    String deptIdFilterStr(String deptId);
    /**
     * 
     * 注释：合同管理系统查询工程-合同信息接口数据组装
     * @author liaoyq
     * @createTime 2019年12月5日
     * @param queryReq
     * @return
     * @throws ParseException 
     *
     */
	Map<String, Object> queryProjectContractService(ProjectQueryReq queryReq) throws ParseException;

    String changeBudgeter(String projId, String staffId);
}
