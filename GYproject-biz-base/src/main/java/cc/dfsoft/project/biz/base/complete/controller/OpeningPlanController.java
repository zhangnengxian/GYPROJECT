package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.base.withgas.enums.GasProjectStatusEnum;
import cc.dfsoft.project.biz.base.withgas.service.GasProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 计划确认
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/openingPlan")
public class OpeningPlanController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(OpeningPlanController.class);

	/**通气工程服务接口*/
	@Resource
	GasProjectService gasProjectService;

	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough", GasProjectStatusEnum.GAS_AUDIT_NO_PASS.getValue());
		modelView.addObject("stepId", StepOutWorkflowEnum.GAS_PROJECT_AUDIT.getValue());
		modelView.setViewName("complete/openingPlan");
		return modelView;
	}

	/**
	 * 打开右侧详述页面
	 * @author cui
	 * @createTime 2017-8-9
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewOpenPage")
	public ModelAndView viewOpenPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/openPlanRight");
		return modelview;
	}

	/**
	 * 打开右侧详述页面
	 * @author cui
	 * @createTime 2017-8-9
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelView = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);
		modelView.setViewName("complete/openProjectRight");
		return modelView;
	}

	/**
	 * 通气工程查询弹窗
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/searchPopPage")
	public ModelAndView gasPlanSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("gasProjectStatusEnum",GasProjectStatusEnum.values());
		modelview.setViewName("withgas/gasProjectPopPage");
		return modelview;
	}

	/**
	 * 查询待通气工程
	 * @author cui
	 * @createTime 2017-8-7
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryGasProject")
	@ResponseBody
	public Map<String,Object> queryGasProject(HttpServletRequest request, GasProjectReq gasProjectReq){
		try {
			gasProjectReq.setSortInfo(request);
			return gasProjectService.queryGasProject(gasProjectReq);
		} catch (Exception e) {
			logger.error("待通气工程列表查询查询失败！", e);
			return null;
		}
	}

	/**
	 * 通气工程开通确认
	 * @author cui
	 * @createTime 2017-8-7
	 * @param  gasProject 通气工程
	 * @return String
	 */
	@RequestMapping(value = "/saveGasProject")
	@ResponseBody
	public String saveGasProject(@RequestBody(required = true) GasProject gasProject){
		try {
			return gasProjectService.enterGasProject(gasProject);
		} catch (Exception e) {
			logger.error("通气工程信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
