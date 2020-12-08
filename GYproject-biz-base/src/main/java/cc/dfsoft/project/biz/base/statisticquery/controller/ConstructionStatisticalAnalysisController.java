package cc.dfsoft.project.biz.base.statisticquery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.constructmanage.service.InspectionListService;
import cc.dfsoft.project.biz.base.constructmanage.service.InspectionRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;

/**
 * 施工统计分析
 * @createTime 2016-08-31
 * @author pengtt
 *
 */
@Controller
@RequestMapping(value="/constructionStatisticalAnalysis")
public class ConstructionStatisticalAnalysisController {
	
	/***/
	@Resource
	ProjectService projectService;
	
	@Resource
	InspectionRecordService inspectionRecordService;
	
	@Resource
	InspectionListService inspectionListService;
	
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		Map<String, String> map = new HashMap<>();
		map.put("deptName", "施工管理");
		List<Department> department = departmentService.getList(map);
		modelView.addObject("department", department);
		modelView.setViewName("project/statisticquery/constructionStatisticalAnalysis");
		return modelView;
	}
	
	/**
	 * 各管理处施工进度统计分析
	 * @author pengtt
	 * @createTime 2016-09-05
	 * @return
	 */
	@RequestMapping(value="/everySchedule")
	@ResponseBody
	public Map<String,Object> projectScheduleStatistics(){
		try {
			return projectService.projectScheduleStatistics();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 工程项目安全质量检查项目排名TOP10
	 * @author ht.hu  
	 * @return
	 */
	@RequestMapping(value="/queryInspectionRecord")
	@ResponseBody
	public List<Map<String, Object>> queryInspectionRecord(){
		try {
			List<Map<String, Object>> list = inspectionRecordService.inspectionTop();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 近三年施工部安全质量检查对比
	 * @author ht.hu
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/queryInspectionList")
	@ResponseBody
	public List<Map<String, Object>> queryInspectionList(String param){
		try {
			List<Map<String, Object>> inspectionList = inspectionListService.queryInspectionList(param);
			return inspectionList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
