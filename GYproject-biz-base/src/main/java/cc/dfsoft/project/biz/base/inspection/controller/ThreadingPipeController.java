package cc.dfsoft.project.biz.base.inspection.controller;

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

import cc.dfsoft.project.biz.base.inspection.dto.ControlDebuggingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.BasicCheckItemTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.GuaranteeCheckItemTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.inspection.service.ThreadingPipeService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 管内穿线
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/threadingPipe")
public class ThreadingPipeController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ThreadingPipeController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**管内穿线服务接口*/
	@Resource
	ThreadingPipeService threadingPipeService;
	
	/**
	 * 管内穿线主页面
	 * @author fuliwei
	 * @createTime 2017年2月7日
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.THREADING_PIPE.getValue());
		modelView.addObject("qualitativeCheckMember",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelView.addObject("constructionQaePost",PostTypeEnum.MANAGEMENTQAE.getValue());//质保师
		modelView.addObject("sujgj",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("path",Constants.DISK_PATH+"sign/");
		modelView.setViewName("inspection/threadingPipe");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2017-02-6
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.THREADING_PIPE.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-02-07
	 * @param id 工程id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewThreadingPipe")
	@ResponseBody
	public ProjectChecklist viewThreadingPipe(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=projectChecklistService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 管内穿线保存
	 * @author fuliwei
	 * @createTime  2017-2-7
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveThreadingPipe")
	@ResponseBody
	public String saveThreadingPipe(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			return projectChecklistService.saveProCheckList(checkList,Constants.THREADING_PIPE);
		} catch (Exception e) {
			logger.error("管内穿线报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 管内穿线-保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryGuaranteeItemsList")
	@ResponseBody
	public Map<String, Object> queryGuaranteeItemsList(HttpServletRequest request,ThreadingPipeReq req) {
		try {
			req.setSortInfo(request);
			req.setItemType(GuaranteeCheckItemTypeEnum.THREADING_PIPE_GUARANTEE.getValue());//管内穿线保证项目
		    return threadingPipeService.queryGuaranteeItemsList(req);
		} catch (Exception e) {
			logger.error("管内穿线-保证项目列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 管内穿线-基本项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryThreadingPipeBasic")
	@ResponseBody
	public Map<String, Object> queryThreadingPipeBasic(HttpServletRequest request,ThreadingPipeReq req) {
		try {
			req.setSortInfo(request);
			req.setItemType(BasicCheckItemTypeEnum.THREADING_PIPE_BASIC.getValue());//管内穿线基本项目
		    return threadingPipeService.queryThreadingPipeBasic(req);
		} catch (Exception e) {
			logger.error("管内穿线-基本项目列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 管内穿线-允许偏差项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryThreadingPipeAllowableError")
	@ResponseBody
	public Map<String, Object> queryThreadingPipeAllowableError(HttpServletRequest request,ThreadingPipeReq req) {
		try {
			req.setSortInfo(request);
			req.setItemType(BasicCheckItemTypeEnum.THREADING_PIPE_ALLOWABLE_ERROR.getValue());//管内穿线允许偏差项目
		    return threadingPipeService.queryThreadingPipeBasic(req);
		} catch (Exception e) {
			logger.error("管内穿线-保证项目列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存记录页面数据
	 * @author fuliwei
	 * @createTime 2017年2月8日
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveThreadingPipeRecord")
	@ResponseBody
	public String saveThreadingPipeRecord(@RequestBody ThreadingPipeReq req){
		try {
			threadingPipeService.saveThreadingPipeRecord(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	};
	
}
