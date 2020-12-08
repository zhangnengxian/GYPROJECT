package cc.dfsoft.project.biz.base.project.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.GradeQueryDReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.ScoreStandardQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.ScoreStandard;
import cc.dfsoft.project.biz.base.baseinfo.service.ScoreStandardService;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Controller
@RequestMapping(value="/scoreStandard")
public class ScoreStandardController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ScoreStandardController.class);
	
	/**
	 * 评分标准
	 */
	@Resource
	ScoreStandardService scoreStandardService;
	/**
	 * 工程类型
	 */
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		List<Department> list=departmentService.queryAllList();
		modelView.addObject("department", list);
		modelView.setViewName("standard/scoreStandard");
		return modelView;
	}
	
	/**
	 * 打开右侧
	 * @return
	 */
	@RequestMapping(value="/scoreStandardView")
	public ModelAndView scoreStandardView(){
		ModelAndView modelView=new ModelAndView();
		List<Department> list=departmentService.queryAllList();
		modelView.addObject("department", list);
		modelView.setViewName("standard/scoreStandardRight");
		return modelView;
	}
	
	/**
	 * 评分标准列表条件查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryItem")
	@ResponseBody
	public Map<String,Object> queryAccessory(HttpServletRequest request,ScoreStandardQueryReq scoreStandardQueryReq){
		try {
			scoreStandardQueryReq.setSortInfo(request);
			return scoreStandardService.queryScoreStandard(scoreStandardQueryReq);
		} catch (Exception e) {
			logger.error("评分标准列表查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 弹出搜索屏
	 * @author cui
	 * @createTime 2016-12-1
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/scoreStandardPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("standard/scoreStandardPopPage");
		return modelview;
	}
	
	/**
	 * 保存评分标准
	 * @author cui
	 * @createTime 2016-12-1
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateScoreStandard")
	public String saveOrUpdateScoreStandard(@RequestBody(required = true) ScoreStandard scoreStandard){
		try{
			if(StringUtil.isNotBlank(scoreStandard.getDepartmentId())){
				Department pt=new Department();
				pt.setDeptId(scoreStandard.getDepartmentId());
				scoreStandard.setDept(pt);
			}
			//scoreStandardService.saveOrUpdateScoreStandard(scoreStandard);
			return scoreStandardService.saveOrUpdateScoreStandard(scoreStandard);
		}catch(Exception e){
			logger.error("资料标准保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 删除评分标准
	 * @author 刘博
	 * @createTime 2016-7-15
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteScoreStandard")
	public String deleteScoreStandard(String id){
		try{
			scoreStandardService.deleteScoreStandard(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("评分标准删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 弹出评分屏
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@RequestMapping(value="/popScore")
	public ModelAndView popScore(){
		ModelAndView modelView=new ModelAndView();
		List<Department> list=departmentService.queryAllList();
		modelView.addObject("department", list);
		modelView.setViewName("standard/scorePop");
		return modelView;
	}
	
	
	/**
	 * 弹出屏列表查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryGrade")
	@ResponseBody
	public Map<String,Object> queryGrade(HttpServletRequest request,ScoreStandardQueryReq scoreStandardQueryReq){
		try {
			scoreStandardQueryReq.setSortInfo(request);
			return scoreStandardService.queryGrade(scoreStandardQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 批量添加grade
	 * @author
	 * @createTime 2016-12-07
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/insertGrade")
	@ResponseBody
	public String insertGrade(@RequestBody GradeQueryDReq qdto){
		
		try {
			return scoreStandardService.batInsertGrade(qdto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
}


