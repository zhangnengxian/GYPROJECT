package cc.dfsoft.project.biz.base.design.controller;

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

import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.design.service.OfficialDrawingService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 白图登记
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/whiteMapRegister")
public class WhiteMapRegisterController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(WhiteMapRegisterController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/**设计信息服务接口*/
	@Resource
	DesignInfoService designInfoService;
	
	/** 正式出图服务接口 */
	@Resource
	OfficialDrawingService officialDrawingService;
	
	
	/**
	 * 打开主页面
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_BUDGET);
		modelView.addObject("stepId",StepEnum.WHITE_MAP_AUDIT.getValue());
		modelView.addObject("curStepId",StepEnum.WHITE_MAP.getValue());
		modelView.setViewName("design/whiteMapRegister");
		return modelView;
	}
	
	/**
	 * 列表查询
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_WHITE_MAP.getValue());
			projectQueryReq.setStepId(StepEnum.WHITE_MAP.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("design/whiteMapRegisterRight");
		return modelview;
		
	}
	
	/**
	 * 打开查询弹框
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		//提示修改
		modelview.setViewName("design/projectSearchPopPage");
		return modelview;
	}
	
	/**
	 * 
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public  Project viewProject(@RequestParam(required=true) String id){
		Project proj=projectService.queryProjectById(id);
		return proj;
	}
	
	/**
	 * 白图登记保存
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/updateState")
	@ResponseBody
	public String updateState(@RequestBody(required = true) DesignInfo designInfo){ 
		try {
			officialDrawingService.saveWhiteMap(designInfo);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("白图登记保存数据失败！");
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
}
