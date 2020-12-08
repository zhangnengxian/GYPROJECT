package cc.dfsoft.project.biz.base.statisticquery.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.monitor.service.ProjectTotalStatisticsService;
import cc.dfsoft.uexpress.biz.sys.dept.dto.ProjectTotalStatictisReq;
import cc.dfsoft.uexpress.biz.sys.dept.service.OperateLogService;

/**
 * 监控报表
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/mapStatistics")
public class MapStatisticsController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(MapStatisticsController.class);
	
	/**报表service*/
	@Resource
	ProjectTotalStatisticsService projectTotalStatisticsService;
	
	/**登陆日志*/
	@Resource
	OperateLogService operateLogService;
	
	/**
	 * 打开主页面
	 * @author
	 * @createTime 2016-12-12
	 * @param
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/statisticquery/mapStatistics");
		return modelView;
	}
	
	/**
	 * 统计当前登陆人信息
	 * @author fuliwei
	 * @createTime 2016-12-12
	 * @return
	 */
	@RequestMapping(value="/queryMapStatistics")
	@ResponseBody
	public List<Map<String,Object>> queryMapStatistics(@RequestBody ProjectTotalStatictisReq projectTotalStatictisReq){
		try{
			List<Map<String,Object>> list= operateLogService.queryMapStatistics(projectTotalStatictisReq);
			return list;
		}catch(Exception e){
			logger.error("登陆人信息查询失败！", e);
			return null;
		}
	}
	
}
