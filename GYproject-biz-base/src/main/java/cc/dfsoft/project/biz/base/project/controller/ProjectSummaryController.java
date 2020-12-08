package cc.dfsoft.project.biz.base.project.controller;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 工程概述
 * @author pengtt
 * @createTime 2016-06-20
 */
@Controller
@RequestMapping(value="/projectSummary")
public class ProjectSummaryController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectSummaryController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	SignatureService signatureService;

	@Resource
	ProjectTypeService projTypeService;

	@Resource
	ContributionModeService contributionModeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/summary/projectSummary");
		return modelView;
	}

	@RequestMapping(value="/filter")
	public ModelAndView filter(){
		ModelAndView modelView=new ModelAndView();
		//工程状态
		modelView.addObject("projStatus", ProjStatusEnum.values());
		modelView.addObject("projStatusId", ProjStatusEnum.DURING_CONSTRUCTION.getValue());
		//工程类型
		modelView.addObject("projectType", projTypeService.queryAllList());
//		//出资方式
		modelView.addObject("contributionMode",contributionModeService.queryAllList());	//出资方式
		modelView.addObject("signStep", signatureService.findSignStep()); //签字步骤
		modelView.setViewName("project/summary/projectFilter");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author fuliwei
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(@RequestBody ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_RESULT_REGISTRY.getValue());
			Map<String,Object> map = projectService.querySummaryProject(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 工程列表条件查询(包括首页图片)
	 * @param projectQueryReq
	 * @author zhangjingjing
	 * @createTime 2016-11-14
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectPicture")
	@ResponseBody
	public Map<String,Object> queryProjectPicture(@RequestBody ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setHomePageRole(ProjStatusEnum.TO_DETERMINE_COST.getValue());
			Map<String,Object> map = projectService.queryProjectPicture(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

}
