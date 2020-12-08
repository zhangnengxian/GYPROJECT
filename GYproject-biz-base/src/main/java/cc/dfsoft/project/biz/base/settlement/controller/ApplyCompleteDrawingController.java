package cc.dfsoft.project.biz.base.settlement.controller;

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

import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.project.biz.base.settlement.service.DrawingApplyService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 竣工图审核申请
 * @author liaoyq
 *
 */
@Controller
@RequestMapping(value="/applyCompleteDrawing")
public class ApplyCompleteDrawingController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ApplyCompleteDrawingController.class);
	
	/**电子图审核申请服务接口*/
	@Resource
	DrawingApplyService drawingApplyService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.addObject("notThrough",Constants.MODULE_CODE_SETTLEMENT);
		modelAndView.addObject("stepId",StepOutWorkflowEnum.AUDIT_COMPLETE_DRAWING.getValue());	
		modelAndView.setViewName("settlement/applyCompleteDrawing");
		return modelAndView;
	}
	

	/**
	 * 打开右屏
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("settlement/applyCompleteDrawingRight");
		return modelAndView;
	}
	
	@RequestMapping(value="searchPopPage")
	public ModelAndView viewSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auditResultEnum", AuditResultEnum.values());
		modelAndView.setViewName("settlement/applyCompleteDrawingSearchPop");
		return modelAndView;
	}
	
	/**
	 * 工程列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq req){
		try {
			req.setSortInfo(request);
			Map<String,Object> map=drawingApplyService.queryProject(req);
			return map;
		} catch (Exception e) {
			logger.error("工程列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存申请
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveDrawingApply")
	@ResponseBody
	public String saveDrawingApply(@RequestBody(required = true) DrawingApply da){
		try {
			drawingApplyService.saveDrawingApply(da);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("电子图审核申请保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewDrawingApply")
	@ResponseBody
	public DrawingApply viewDrawingApply(@RequestParam(required=true) String id){
		return drawingApplyService.findByProjId(id);
	}
	
	
	
}
