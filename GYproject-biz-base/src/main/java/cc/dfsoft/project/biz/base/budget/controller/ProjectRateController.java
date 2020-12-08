package cc.dfsoft.project.biz.base.budget.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.controller.ConstructionUnitController;
import cc.dfsoft.project.biz.base.baseinfo.entity.ConstructionUnit;
import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.budget.dto.ProjectRateReq;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.project.biz.base.budget.service.ProjectRateService;
import cc.dfsoft.uexpress.common.constant.Constants;


@Controller
@RequestMapping(value="/projectRate")
public class ProjectRateController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ConstructionUnitController.class);
	
	/**费率服务接口*/
	@Resource
	ProjectRateService projectRateService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(ProjectRateReq projectRateReq){
		ModelAndView modelView=new ModelAndView();
		
	
		modelView.setViewName("baseinfo/projectRate/projectRate");
		return modelView;
	}
	
	/**
	 * 费率列表查询
	 * @author 刘博
	 * @param PageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryProjectRate")
	@ResponseBody
	public Object queryProjectRate(HttpServletRequest request,ProjectRateReq projectRateReq) {
		try {
			projectRateReq.setSortInfo(request);
			List<Map<String,Object>> list = projectRateService.queryProjectRate(projectRateReq);
		    return list;
		} catch (Exception e) {
			logger.error("费率列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author 刘博
	 * @createTime 2016-7-11
	 * @param id 费率id
	 * @return ProjectRate
	 */
	@RequestMapping(value="/queryProjectRateById")
	@ResponseBody
	public ProjectRate queryProjectRateById(@RequestParam(required=true) String id){
		ProjectRate projectRate = projectRateService.queryProjectRateById(id);
		return projectRate;
	}
	
	/**
	 * 打开费率右侧详述页面
	 * @author 刘博
	 * @return
	 */
	@RequestMapping(value = "/viewPageProjectRate")
	public ModelAndView viewPageProjectRate() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("baseinfo/projectRate/projectRateRight");
		return modelview;
		
	}
	
	/**
	 * 保存费率信息
	 * @author 刘博
	 * @createTime 2016-7-12
	 * @return ModelAndView
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateprojectRate")
	public String saveOrUpdateprojectRate(@RequestBody(required = true) ProjectRateReq projectRateReq){
		try{
			return projectRateService.saveOrUpdateprojectRate("1", projectRateReq.getValue(), projectRateReq.getName());
		}catch(Exception e){
			logger.error("费率信息保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
}
