package cc.dfsoft.project.biz.base.complete.controller;

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

import cc.dfsoft.project.biz.base.complete.dto.GasApplyReq;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.project.biz.base.complete.enums.ConfrimStateEnum;
import cc.dfsoft.project.biz.base.complete.service.GasApplyService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 通气反馈
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/gasFeedback")
public class GasFeedbackController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(JointAcceptanceController.class);
	
	/**通气确认接口*/
	@Resource
	GasApplyService gasApplyService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/gasFeedback");
		return modelView;
	}
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/gasApplySearchPopPage")
	public ModelAndView gasApplySearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("confrimState",ConfrimStateEnum.values());
		modelview.setViewName("complete/gasApplySearchPopPage");
		return modelview;
	}
	
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/gasFeedbackRight");
		return modelview;
	}
	/**
	 * 右屏详述
	 * @return
	 */
	@RequestMapping(value = "/gasApplyDetail")
	@ResponseBody
	public GasApply gasApplyDetail(@RequestParam(required=true) String id){
		try {
			return gasApplyService.gasApplyDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通气申请条件查询
	 * @param request
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryGasApply")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(HttpServletRequest request,GasApplyReq gasApplyReq){
		try {
			gasApplyReq.setSortInfo(request);
			Map<String, Object> map = gasApplyService.queryGasApply(gasApplyReq);
			return map;
		} catch (Exception e) {
			logger.error("通气申请查询失败！", e);
			return null;
		}
	}

	/**
	 * 更新通气申请
	 * @param ga
	 * @return
	 */
	@RequestMapping(value = "/updateGasApply")
	@ResponseBody
	public String updateGasApply(@RequestBody GasApply gasApply){
		try {
			return gasApplyService.updateGasApply(gasApply);
		} catch (Exception e) {
			logger.error("通气申请保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
}
