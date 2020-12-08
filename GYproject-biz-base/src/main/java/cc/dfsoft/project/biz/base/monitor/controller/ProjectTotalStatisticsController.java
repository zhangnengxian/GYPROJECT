
package cc.dfsoft.project.biz.base.monitor.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.monitor.service.ProjectTotalStatisticsService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;

/**
 * 工程数量总体监控屏
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/projectTotalStatistics")
public class ProjectTotalStatisticsController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectTotalStatisticsController.class);
	
	private static String staticsUrl = "";
	private static String staticsUrlOld = "";
	
	/** 工程总体统计接口 */
	@Resource
	ProjectTotalStatisticsService projectTotalStatisticsService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		logger.info(this.getClass().getName() + " main ");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projectTotalStatistics");
		return modelView;
	}
	
	/**
	 * 按照工程类型进行工程总体查询
	 * @return
	 */
	@RequestMapping(value="/projectTypeStatistics")
	@ResponseBody
	public Map<String,Object> projectTypeStatistics(){
		Map map = projectTotalStatisticsService.queryProjectTotalStatisticsByType();
		return map;
	}
	/**
	 * 桑集图(能流图）
	 * @return
	 */
	@RequestMapping(value="/sankeyMain")
	public ModelAndView sankeyMain(){
		logger.info(this.getClass().getName() + " sankeyMain ");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projectSankeyDiagram");
		return modelView;
	}
	
	/**
	 * 展现能流图（工程类型、工程规模、收付款）
	 * @return
	 */
	@RequestMapping(value="/projectSankey")
	@ResponseBody
	public Map<String,Object> projectTypeStatisticsOfSankey(){
		Map map = projectTotalStatisticsService.queryProjectDataForSankey();
		return map; 
	}
	
	/**
	 * 统计收付款情况
	 * @return
	 */
	@RequestMapping(value="/projectPaymentMain")
	public ModelAndView paymentMain(){
		logger.info(this.getClass().getName() + " paymentMain ");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projectPaymentDiagram");
		return modelView;
	}
	
	/**
	 * 收付款统计数据
	 * @return
	 */
	@RequestMapping(value="/projectPaymenetDetail")
	@ResponseBody
	public Map<String,Object> projectPaymentStatistics(){
		Map map = projectTotalStatisticsService.queryProjectOfPayment();
		return map; 
	} 
	
	
	/**
	 * 安全质量统计页面
	 * @author
	 * @createTime 2016-12-01
	 * @param
	 * @return
	 */
	@RequestMapping(value="/safetyQualityMain")
	public ModelAndView safetyQuality(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/safetyQualityStatistics");
		return modelView;
	}
	
	@RequestMapping(value="/safetyQualityStatistics")
	@ResponseBody
	public  Map<String,Object> querySafetyQualityStatistics(){
		Map map=projectTotalStatisticsService.querySafetyQualityStatistics();
		return map;
	}
	
	
	
	/**
	 * 打开规模金额页面
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@RequestMapping(value="/scaleAmount")
	public ModelAndView scaleAmount(){
		ModelAndView modelView=new ModelAndView();
		List<Map<String, Object>> amountList=projectTotalStatisticsService.queryScaleAmountStatistics();
		modelView.addObject("amountList",amountList);
		modelView.setViewName("monitor/scaleAmount");
		return modelView;
	}
	
	/**
	 * 工程项目总体信息查询
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@RequestMapping(value="/scaleAmountStatistics")
	@ResponseBody
	public  List<Map<String, Object>> scaleAmountStatistics(){
		List<Map<String, Object>> list=projectTotalStatisticsService.queryScaleAmountStatistics();
		return list;
	}
	
	/**
	 * 打开区域金额页面
	 * @author
	 * @createTime 2016-12-05
	 * @param
	 * @return
	 */
	@RequestMapping(value="/areaAmount")
	public ModelAndView areaAmount(){
		ModelAndView modelView=new ModelAndView();
		List<Map<String, Object>> scaleList=projectTotalStatisticsService.queryScaleAmountStatistics();
		modelView.addObject("scaleList",scaleList);
		List<Map<String, Object>> amountList=projectTotalStatisticsService.queryAreaMountStatistics();
		modelView.addObject("amountList",amountList);
		modelView.setViewName("monitor/areaAmount");
		return modelView;
	}
	
	/**
	 * 工程项目总体信息查询-按区域划分
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@RequestMapping(value="/areaAmountStatistics")
	@ResponseBody
	public  List<Map<String,Object>> areaAmountStatistics(){
		List<Map<String,Object>> map=projectTotalStatisticsService.queryAreaSumAmountStatistics();
		return map;
	}
	
	
	/**
	 * 近一年各区工程项目数量对比
	 * @return
	 */
	@RequestMapping(value="/projectStatisticOfAreaAndStatus")
	public ModelAndView projectStatisticOfAreaAndStatus(){
		logger.info(this.getClass().getName() + " projectStatisticOfAreaAndStatus ");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projectStatisticOfAreaAndStatus");
		return modelView;
	}
	
	/**
	 * 近一年各区工程项目数量对比显示
	 * @return
	 */
	@RequestMapping(value="/projectStatisticOfAreaAndStatusShowPage")
	@ResponseBody
	public Map<String,Object> projectStatisticOfAreaAndStatusShowPage(){
		Map map = 	projectService.everyAreaProjectNum();
		return map; 
	} 
	
	/**
	 * 监控屏控制页面
	 * @return
	 */
	@RequestMapping(value="/projectMonitorControllPage")
	public ModelAndView projectMonitorControllPage(){
		logger.info(this.getClass().getName() + " projectMonitorControllPage ");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projectControll");
		return modelView;
	}
	
	/**
	 * 监控屏控制页面显示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectMonitorControll")
	@ResponseBody
	public String projectMonitorControll(HttpServletRequest request){
		
		staticsUrlOld = staticsUrl;
		staticsUrl = (String)request.getParameter("data");
		
		return "1"; 
	} 
	
	/**
	 * 主监控屏展示页面
	 * @return
	 */
	@RequestMapping(value="/projectMonitorShowPage")
	public ModelAndView projectMonitorShowPage(){
		logger.info(this.getClass().getName() + " projectMonitorShowPage ");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projectMonitorShow");
		return modelView;
	}
	
	/**
	 * 主监控屏页面内容展示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectMonitorShow")
	@ResponseBody
	public String projectMonitorShow(HttpServletRequest request){
		
		String strRet = "0";
		if(!staticsUrlOld.equals(staticsUrl))
		{
			strRet = staticsUrl;
			staticsUrlOld = staticsUrl;
		}
		return strRet; 
	} 
	
	/**
	 * 展示工程阶段统计数据的展示页面
	 * @return
	 */
	@RequestMapping(value="/projectStage")
	public ModelAndView projectStage(){
		logger.info(this.getClass().getName() + " projectStage ");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("monitor/projectStage");
		return modelView;
	}
	
	/**
	 * 根据工程阶段，统计工程的数量
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectStageShow")
	@ResponseBody
	public String projectStageShow(HttpServletRequest request){	
		return ""; 
	}
	
	/**
	 * 显示立项、签约的统计图表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/projectOfAcceptAndSign")
	@ResponseBody
	public Map projectOfAcceptAndSign()throws Exception{	
		Map map = this.projectTotalStatisticsService.queryProjectNumOfMonthByDate();
		return map; 
	}
}