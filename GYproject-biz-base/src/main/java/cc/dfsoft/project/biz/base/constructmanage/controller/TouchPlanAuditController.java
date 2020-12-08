package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.constructmanage.dto.TouchPlanQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchPlanService;
import cc.dfsoft.project.biz.base.contract.controller.ContractAuditController;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 碰口方案审核
 * @author liubo
 *
 */
@Controller
@RequestMapping(value="/touchPlanAudit")
public class TouchPlanAuditController {
	
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	/**碰口方案服务接口*/
	@Resource
	TouchPlanService touchPlanService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("constructmanage/touchPlanAudit");
		return modelView;
	}
	
	
	/**
	 * 碰口方案审核列表条件查询
	 * @author liubo
	 * @createTime 2016-08-04
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryTouchPlan")
	@ResponseBody
	public Map<String,Object> queryTouchPlan(HttpServletRequest request,TouchPlanQueryReq touchPlanQueryReq){
		try {
			touchPlanQueryReq.setSortInfo(request);
			Map<String, Object> map = touchPlanService.queryTouchPlanAudit(touchPlanQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	
	/**
	* 打开审核屏
	* @return ModelAndView
	*/
	@RequestMapping(value="/auditPage")
	public ModelAndView audit(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		String isAudit = request.getParameter("isAudit");
		String auditLevel = request.getParameter("auditLevel");
		modelView.addObject("isAudit",isAudit);				//当前审核是否通过
		modelView.addObject("auditLevel",auditLevel);       //当前点击的按钮的级别
		modelView.addObject("suJgj",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("constructionPrincipal",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("director",PostTypeEnum.DIRECTOR.getValue());	//处长
		modelView.addObject("ongcPrincipal",PostTypeEnum.WEBSITE_ENGINEER.getValue());	//管网工艺员
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//img url	
		modelView.setViewName("constructmanage/touchPlanAuditPage");
		return modelView;
	}
	
	/**
	 * 打开审核查询页面
	 * @createTime 2016-07-11
	 * @return
	 */
	@RequestMapping(value="/searchPopPage")
	public ModelAndView touchPlanAuditSearchPage(String projId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projId", projId);
		modelView.setViewName("constructmanage/touchPlanAuditSearchPage");
		return modelView;
	}
	
}
