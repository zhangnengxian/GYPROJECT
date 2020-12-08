package cc.dfsoft.project.biz.base.monitor.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.contract.enums.ChangeReasonEnum;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeEnum;
import cc.dfsoft.project.biz.base.design.enums.DesignStationTypeEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.monitor.service.ProjectTotalStatisticsService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjSourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工程数量总体监控屏
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/projTS")
public class ProjStaController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjStaController.class);
	
	/** 工程总体统计接口 */
	@Resource
	ProjectTotalStatisticsService projectTotalStatisticsService;
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**工程接口*/
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开规模金额页面
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@RequestMapping(value="/newStatistics")
	public ModelAndView scaleAmount(){
		ModelAndView modelView=new ModelAndView();
		List<Map<String, Object>> amountList=projectTotalStatisticsService.queryScaleAmountStatistics();
		modelView.addObject("amountList",amountList);
		modelView.setViewName("monitor/newStatistics");
		return modelView;
	}
	
	/**
	 * 六个gird屏
	 */
	@RequestMapping(value="/projInfo")
	public ModelAndView queryProjInfo(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/newProjInfo");
		return modelView;
	}
	
	/**
	 * 修改六个gird屏
	 */
	@RequestMapping(value="/projItem")
	public ModelAndView queryProjItem(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/newProjItem");
		return modelView;
	}
	
	/**
	 * 修改六个gird屏自适应
	 */
	@RequestMapping(value="/projItemSelf")
	public ModelAndView projItemSelf(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projItemSelf");
		return modelView;
	}
	
	@RequestMapping(value="/newSelf")
	public ModelAndView newSelf(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/newSelf");
		return modelView;
	}
	
	@RequestMapping(value="/newSelfInfo")
	public ModelAndView newSelfInfo(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/newSelfInfo");
		return modelView;
	}
	
	/**
	 * 六个gird屏-第一个
	 */
	@RequestMapping(value="/thisYearShow")
	public ModelAndView queryThisYearShow(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/thisYearShow");
		return modelView;
	}
	
	/**
	 * 六个gird屏-第2个
	 */
	@RequestMapping(value="/projTypeShow")
	public ModelAndView queryProjTypeShow(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projTypeShow");
		return modelView;
	}
	
	/**
	 * 六个gird屏-第3个
	 */
	@RequestMapping(value="/projAreaShow")
	public ModelAndView queryProjAreaShow(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projAreaShow");
		return modelView;
	}
	
	/**
	 * 六个gird屏-第4个
	 */
	@RequestMapping(value="/projStageShow")
	public ModelAndView projStageShow(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projStageShow");
		return modelView;
	}
	
	/**
	 * 六个gird屏-第5个
	 */
	@RequestMapping(value="/projApplySignShow")
	public ModelAndView projApplySignShow(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projApplySignShow");
		return modelView;
	}
	
	/**
	 * 六个gird屏-第一个
	 */
	@RequestMapping(value="/projAmountShow")
	public ModelAndView projAmountShow(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projAmountShow");
		return modelView;
	}
	
	
	/**
	 * 施工屏
	 */
	@RequestMapping(value="/projConstructionInfo")
	public ModelAndView projConstructionInfo(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/newProjConstructionInfo");
		return modelView;
	}
	
	/**
	 * 工程施工报表屏加载工程列表-部门
	 * @return
	 */
	@RequestMapping(value = "/viewListPage")
	public ModelAndView viewUnitPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/projectList");
		return modelview;
	}
	
	/**
	 * 工程施工报表屏加载工程列表-承包商
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/viewCuListPage")
	public ModelAndView viewCuListPage() throws ParseException {
		ModelAndView modelview = new ModelAndView();
		BusinessPartnersReq bq=new BusinessPartnersReq();
		bq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(bq);
		List list=(List) map.get("data");
		modelview.addObject("culist", list);
		modelview.setViewName("monitor/projectCuList");
		return modelview;
	}
	
	/**
	 * 工程施工报表屏-安全质量排名
	 * @return
	 */
	@RequestMapping(value = "/viewSafePage")
	public ModelAndView viewSafePage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/projectSafePage");
		return modelview;
	}
	
	
	/**
	 * 4
	 * @return
	 */
	@RequestMapping(value = "/viewMapPage")
	public ModelAndView viewMapPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/projectMap");
		return modelview;
		
	}
	
	
	/**
	 * 工程项目首屏
	 * @return
	 */
	@RequestMapping(value = "/viewProjectItem")
	public ModelAndView viewProjectItem() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/projectItem");
		return modelview;
	}
	
	
	/**
	 * 工程阶段
	 * @return
	 */
	@RequestMapping(value = "/viewStageStatistics")
	public ModelAndView viewStageStatistics() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/stageStatistics");
		return modelview;
	}
	
	/**
	 * 工程阶段全屏
	 * @return
	 */
	@RequestMapping(value = "/viewStageStatisticsFullScreen")
	public ModelAndView viewStageStatisticsFullScreen() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/fullScreenStageStatistics");
		return modelview;
	}
	
	/**
	 * 报表首屏跳转到列表屏
	 * @return
	 */
	@RequestMapping(value = "/viewPorjectList")
	public ModelAndView viewPorjectList() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/firstScreenPorjectList");
		return modelview;
	}
	
	/**
	 * 报表首屏列表屏跳转到详述屏
	 * @return
	 */
	@RequestMapping(value = "/viewPorjectDetail")
	public ModelAndView viewPorjectDetail(String projId) {
		//String id=request.getParameter("projId");
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projId", projId);
		modelview.setViewName("monitor/firstScreenPorjectDetail");
		return modelview;
	}
	
	/**
	 * 工程综合详述------工程基础信息页面
	 * @author 
	 * @createTime 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectDetailBase")
	public ModelAndView projectDetailBase(HttpServletRequest request){
		
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("projLtype", ProjLtypeEnum.values());
		modelView.addObject("area", AreaEnum.values());
		modelView.addObject("projStatus", ProjStatusEnum.values());//工程状态
		modelView.addObject("projSource", ProjSourceEnum.values());//受理来源
		modelView.addObject("changeReason", ChangeReasonEnum.values());//变动原因
		modelView.setViewName("monitor/projectDetailBase");
		return modelView;
	}
	
	
	/**
	 * 工程综合详述---工程进度
	 * @author 
	 * @createTime 
	 * @return
	 */
	@RequestMapping(value = "/projectDetailSchedulePage")
	public ModelAndView projectDetailSchedulePage(HttpServletRequest request) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projId", request.getParameter("projId"));
		modelview.setViewName("monitor/projectDetailSchedule");
		return modelview;
	}
	
	/**
	 * 施工进度
	 * @return
	 */
	@RequestMapping(value="/projectDetailconsSchedule")
	public ModelAndView constructionSchedule(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projId", request.getParameter("projId"));
		modelView.setViewName("monitor/projectDetailConsSchedule");
		return modelView;
	}
	
	/**
	 * 项目位置---工程信息
	 * @author 
	 * @createTime 
	 * @return
	 */
	@RequestMapping(value = "/projectLocation")
	public ModelAndView projectLocation() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/constructionMonitoring");
		return modelview;
	}
	
	/**
	 * 工程综合详述---施工合同页面
	 * @author 
	 * @createTime 
	 * @return
	 */
	@RequestMapping(value = "/projectDetailContractPage")
	public ModelAndView projectDetailContractPage() {
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("payType", PayTypeEnum.values());//付款方式
		modelview.setViewName("monitor/projectDetailContract");
		return modelview;
		
	}
	
	/**
	 * 工程综合详述---施工计划页面
	 * @author pengtt
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/projectDetailPlanPage")
	public ModelAndView projectDetailPlanPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("monitor/projectDetailPlan");
		return modelview;
	}
	
	
	/**
	 * 报表屏首页
	 * @author
	 * @createTime 2017-1-1
	 * @param
	 * @return
	 */
	@RequestMapping(value="/lastYearAndThisYearInfo")
	@ResponseBody
	public  List<Map<String, Object>> queryLastYearAndThisYearInfo(){
		List<Map<String, Object>> list=projectTotalStatisticsService.queryLastYearAndThisYearInfo();
		return list;
	}
	
	/**
	 * 报表屏首页 转天然气和退单
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@RequestMapping(value="/alreadyFinishedSum")
	@ResponseBody
	public  List<Map<String, Object>> queryAlreadyFinishedSum(){
		List<Map<String, Object>> list=projectTotalStatisticsService.queryAlreadyFinishedSum();
		return list;
	}
	
	
	 /** 报表屏首页 在建工程
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@RequestMapping(value="/constructingProject")
	@ResponseBody
	public  List<Map<String, Object>> queryConstructingProject(){
		List<Map<String, Object>> list=projectTotalStatisticsService.queryConstructingProject();
		return list;
	}
	
	 /** 报表屏首页各施工处工程数量
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@RequestMapping(value="/queryManagementOffice")
	@ResponseBody
	public  List<Map<String, Object>> queryManagementOffice(){
		List<Map<String, Object>> list=projectTotalStatisticsService.queryManagementOffice();
		return list;
	}
	
	
	
	/**
	 * 工程列表条件查询-报表系统
	 * @param 
	 * @author 
	 * @createTime 2017-01-4
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(ProjectQueryReq projectQueryReq){
		
		try {
			//projectQueryReq.setSortInfo(request); getThanValueNew
			//工程状态施工中、待自检、待预验收、待联合验收、待结算报审、报审确认、待结算初审、初审确认、待结算终审、终审确认、资料发送、资料反馈、资料确认
//			String[] projStatus = {ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.TO_PRE_INSPECTION.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.REPORT_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.REPORT_START_CONFIRM.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_DONE.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.COMPLETION_TRANSFER.getValue(),ProjStatusEnum.DATA_FEEDBACK.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
			List<ProjStatusEnum> list = ProjStatusEnum.getThanValueNew(ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
			List<String> list1  = new ArrayList();
			for(ProjStatusEnum p:list){
				list1.add(p.getValue());
			}
			String[] projStatus = list1.toArray(new String[list1.size()]);
			Map<String,Object> map = projectService.queryProjStatis(projectQueryReq,projStatus);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2017-1-4
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("area", AreaEnum.values());
		modelview.addObject("projStatusId", ProjStatusEnum.values());
		modelview.addObject("designStationId",DesignStationTypeEnum.values());
		modelview.setViewName("project/view/projectGlobalViewSearchPopPage");
		return modelview;
	}
	
	
	 /** 近一年每月立项数目
	 * @author fuliwei
	 * @createTime 2017-1-4
	 * @return Map<String,Object>
	 */
	@RequestMapping(value="/acceptNum")
	@ResponseBody
	public Map<String,Object> queryLastMonthsNum(){
		return projectTotalStatisticsService.queryLastMonthsNum();
	}

	/**
	 * 用于工程项目第1个grid--统计当年和去年的工程数量、金额
	 * @author fuliwei
	 * @createTime 2017-01-07
	 * @return
	 */
	@RequestMapping(value="/queryLastYearAndThisYearNum")
	@ResponseBody
	public  List<Map<String, Object>> queryLastYearAndThisYearNum(){
		return projectTotalStatisticsService.queryLastYearAndThisYearNum();
	}
	
	/**
	 * 用于工程项目第2个grid --统计当年的民用、车用、公用工程数量
	 * @author fuliwei
	 * @createTime 2017-01-07
	 * @return List
	 */
	@RequestMapping(value="/queryProjectTypeNum")
	@ResponseBody
	public List<Map<String,Object>> queryProjectTypeNum(){
		return projectTotalStatisticsService.queryProjectTypeNum();
	}
	
	/**
	 * 用于工程项目第3个grid-按区域统计当年工程数量和金额
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@RequestMapping(value="/queryProjectAreaNum")
	@ResponseBody
	public List<Map<String,Object>> queryProjectAreaNum(){
		return projectTotalStatisticsService.queryProjectAreaNum();
	}
	
	/**
	 * 用于工程项目第4个grid-按阶段统计当年工程数量
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@RequestMapping(value="/queryProjectStageNum")
	@ResponseBody
	public List<Map<String,Object>> queryProjectStageNum(){
		return projectTotalStatisticsService.queryProjectStageNum();
	}
	
	/**
	 * 用于工程项目第5个grid-按立项、签约统计当年工程数量
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@RequestMapping(value="/queryProjectAcceptAndContractNum")
	@ResponseBody
	public List<Map<String,Object>> queryProjectAcceptAndContractNum(){
		return projectTotalStatisticsService.queryProjectAcceptAndContractNum();
	}
	
	/**
	 * 用于工程项目第6个grid-按结款、未结统计
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@RequestMapping(value="/querySignAndAladyCharge")
	@ResponseBody
	public List<Map<String,Object>> querySignAndAladyCharge(){
		return projectTotalStatisticsService.querySignAndAladyCharge();
	}
	
	/**
	 * 用于工程施工 各施工处 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/queryjConstructionNumsAndAmount")
	@ResponseBody
	public Map<String, Object> queryjConstructionNumsAndAmount(){
		return projectTotalStatisticsService.queryConstructionNumsAndAmount();
	};
	
	/**
	 * 用于工程施工 各分包单位 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	 
	@RequestMapping(value="/queryCuUnitNumsAndAmount")
	@ResponseBody
	public Map<String, Object> queryCuUnitNumsAndAmount(){
		return projectTotalStatisticsService.queryCuUnitNumsAndAmount();
	}

	/**
	 * 用于工程施工 各分包单位 数量、金额统计-暂为右屏统计
	 * @author cui
	 * @createTime 2017-7-25
	 * @param
	 * @return Map<String, Object>
	 */

	@RequestMapping(value="/queryCuUnitNumsAndAmountRight")
	@ResponseBody
	public Map<String, Object> queryCuUnitNumsAndAmountRight(){
		return projectTotalStatisticsService.queryCuUnitNumsAndAmountRight();
	}
	
	/**
	 * 用于工程施工 各施工处扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/queryContructionUnitSafetyNums")
	@ResponseBody
	public Map<String, Object> queryContructionUnitSafetyNums(){
		return projectTotalStatisticsService.queryContructionUnitSafetyNums();
	}
	
	/**
	 * 用于工程施工 各分包单位扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/queryCuUnitSafetyNums")
	@ResponseBody
	public Map<String, Object> queryCuUnitSafetyNums(){
		return projectTotalStatisticsService.queryCuUnitSafetyNums();
	};
	
	/**
	 * 用于工程施工 各施工处按工程规模统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/queryContructionUnitScaleNums")
	@ResponseBody
	public Map<String, Object> queryContructionUnitScaleNums(){
		return projectTotalStatisticsService.queryContructionUnitScaleNums();
	};
	
	
	
	/**
	 * 用于工程施工 各分包单位按工程规模统计
	 * @author fuliwei
	 * @createTime 2017-1-11
	 * @param
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/queryCuUnitScaleNums")
	@ResponseBody
	public Map<String, Object> queryCuUnitScaleNums(){
		return projectTotalStatisticsService.queryCuUnitScaleNums();
	};
	
	/**
	 * 用于近一年每月施工处扣分数
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	@RequestMapping(value="/queryContructionUnitScore")
	@ResponseBody
	public Map<String,Object> queryContructionUnitScore (){
		return projectTotalStatisticsService.queryContructionUnitScore();
	};
	
	/**
	 * 用于当年工程进度
	 * @author fuliwei
	 * @createTime 2017-01-11
	 * @return
	 */
	@RequestMapping(value="/queryProjectSchedule")
	@ResponseBody
	public Map<String,Object> queryProjectSchedule(){
		return projectTotalStatisticsService.queryProjectSchedule();
	};
}
