
package cc.dfsoft.project.biz.base.accept.controller;


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

import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.accept.service.RaiseMoneyAplicationService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.constant.Constants;


/**
 * 用户回复
 * @author fulw
 *
 */
@Controller
@RequestMapping(value="/customerResponse")
public class CustomerResponseController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(CustomerResponseController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	
	@Resource
	RaiseMoneyAplicationService raiseMoneyAplicationService;
	
	/**
	 * 打开主页面
	 * @author fuliwei  
	 * @date 2018年9月13日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("accept/customerResponse");
		return modelView;
	}
	
	/**
	 * 工程列表查询
	 * @author fuliwei  
	 * @date 2018年9月13日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_CUST_RESPONSE.getValue());
			projectQueryReq.setStepId(StepEnum.CUST_RESPONSE.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei  
	 * @date 2018年9月13日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("accept/customerResponseRight");
		return modelview;
	}
	
	/**
	 * 弹出查询屏
	 * @author fuliwei  
	 * @date 2018年9月13日  
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
	 * 查询详述
	 * @author fuliwei  
	 * @date 2018年9月13日  
	 * @version 1.0
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public  Project viewProject(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}
	
	/**
	 * 
	 * @author fuliwei  
	 * @date 2018年9月13日  
	 * @version 1.0
	 */
	@RequestMapping(value="/saveRaiseMoney")
	@ResponseBody
	public String saveRaiseMoney(@RequestBody RaiseMoney raiseMoney){
		//raiseMoney.setApplyDate(DateUtil.getDate(new Date()));//提资申请日期

		try {
			raiseMoneyAplicationService.saveCustResponse(raiseMoney);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch (Exception e){
			logger.error("提资信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
	  
	
	
