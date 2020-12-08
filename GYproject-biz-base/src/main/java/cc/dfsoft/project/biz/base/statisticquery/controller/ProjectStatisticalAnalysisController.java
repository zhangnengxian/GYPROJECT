package cc.dfsoft.project.biz.base.statisticquery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.service.ProjectService;

/**
 * 工程统计分析
 * @createTime 2016-08-31
 * @author pengtt
 *
 */
@Controller
@RequestMapping(value="/projectStatisticalAnalysis")
public class ProjectStatisticalAnalysisController {
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/statisticquery/projectStatisticalAnalysis");
		return modelView;
	}
	
	/**
	 * 近一年各区工程项目数量对比
	 * @author pengtt
	 * @createTime 2016-09-05
	 * @return
	 */
	@RequestMapping(value="/everyAreaProjectNum")
	@ResponseBody
	public Map<String,Object> everyAreaProjectNum(){
		try {
			return projectService.everyAreaProjectNum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HashMap<String,Object>();
	}
	
	/**
	 * 近一年退单工程项目数量统计
	 * @author pengtt
	 * @createTime 2016-09-05
	 * @return
	 */
	@RequestMapping(value="/backReasonProjectNum")
	@ResponseBody
	public Map<String,Object> backReasonProjectNum(){
		return projectService.backReasonProjectNum();
	}
	
	/**
	 * 近一年工程款项收付分析图
	 * @author pengtt
	 * @createTime 2016-09-06
	 * @return
	 */
	@RequestMapping(value="/paymentNum")
	@ResponseBody
	public Map<String,Object> paymentNum(){
		return projectService.paymentNum();
	}
	
}
