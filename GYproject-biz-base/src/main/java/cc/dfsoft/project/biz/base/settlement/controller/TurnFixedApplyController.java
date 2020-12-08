package cc.dfsoft.project.biz.base.settlement.controller;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.entity.TurnFixedApply;
import cc.dfsoft.project.biz.base.settlement.service.TurnFixedApplyService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;


/**
 * 转固申请
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/turnFixedApply")
public class TurnFixedApplyController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(TurnFixedApplyController.class);

	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**转固服务接口*/
	@Resource
	TurnFixedApplyService turnFixedApplyService;
	
	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_SETTLEMENT);
		modelView.addObject("stepId",StepEnum.TURN_FIXED_AUDIT.getValue());	
		modelView.setViewName("settlement/turnFixedApply");
		return modelView;
	}
	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年6月25日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelview.addObject("loginPost",loginInfo.getPost());
		modelview.addObject("treasurer",PostTypeEnum.TREASURER.getValue());
		modelview.setViewName("settlement/turnFixedApplyRight");
		return modelview;
	}
	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("settlement/turnFixedApplySearchPopPage");
		return modelview;
	}
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author fuliwei
	 * @createTime 2017-07-25
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setProjStatusId(ProjStatusEnum.TURN_FIXED_APPLY.getValue());
			projectQueryReq.setStepId(StepEnum.TURN_FIXED_APPLY.getValue());
			return projectService.queryProjectByTimeForJoint(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 查详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewTurnFixedApply")
	@ResponseBody
	public TurnFixedApply viewTurnFixedApply(HttpServletRequest request,@RequestParam(required=true) String id){
		TurnFixedApply tfa;
		try {
			tfa = turnFixedApplyService.viewTurnFixedApply(id);
			return tfa;
		} catch (ParseException e) {
			logger.error("详述查询失败！", e);
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 保存转固信息
	 * @param turnFixedApply
	 * @return
	 */
	@RequestMapping(value = "/saveTurnFixedApply")
	@ResponseBody
	public String saveTurnFixedApply(@RequestBody(required = true) TurnFixedApply turnFixedApply){
		try {
			return turnFixedApplyService.saveTurnFixedApply(turnFixedApply);
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			logger.error("转固信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
