package cc.dfsoft.project.biz.base.statisticquery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.monitor.service.ProjectTotalStatisticsService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;

/**
 * 按阶段对工程量进行统计
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/projectStageStatistics")
public class ProjectStageStatisticsController {
	
	@Resource
	ProjectTotalStatisticsService projectTotalStatisticsService;

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/statisticquery/projectStageStatistics");
		return modelView;
	}
	
	/**
	 * 查询汇总数据
	 * @return
	 */
	@RequestMapping(value="/projectStageSum") 
	@ResponseBody
	public Map<String,Object> projectStageSum(){
		Map map = projectTotalStatisticsService.queryProjectOfStage();
		return map;  
	}
	
	/**
	 * 显示单个阶段对应的工程列表
	 * @return
	 */
	@RequestMapping(value="/projectStageDetail") 
	@ResponseBody
	public Map<String,Object> projectStageDetail(ProjectQueryReq pqr){
		
		
		Map map = projectTotalStatisticsService.queryProjectOfStageOfDetail(pqr);
		return map;
	}
	
	/**
	 * 显示工程分系统统计
	 * @return
	 */
	@RequestMapping(value="/projectAnalysis") 
	public ModelAndView projectAnalysis(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/statisticquery/projectAnalysisSum");
		return modelView;
	}
}
