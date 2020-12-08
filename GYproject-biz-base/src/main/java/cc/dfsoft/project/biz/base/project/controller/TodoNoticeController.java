package cc.dfsoft.project.biz.base.project.controller;

import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value="/todoNotice")
public class TodoNoticeController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(TodoNoticeController.class);
	
	/**操作记录*/
	@Resource
	OperateRecordService operateRecordService;
	
	/**
	 * 打开页面
	 * @author fuliwei
	 * @createTime 2018年1月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("login", SessionUtil.getLoginInfo());
		modelview.setViewName("project/task/todoNotice");
		return modelview;
	}
	
	/**
	 * 查询代办事项
	 * @author fuliwei  
	 * @date 2018年9月9日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryWaitNotice")
	@ResponseBody
	public Map<String,Object> queryWaitNotice(HttpServletRequest request,OperateRecordQueryReq req){
		try {
			req.setSortInfo(request);
			LoginInfo login=SessionUtil.getLoginInfo();
			req.setOperaterId(login.getStaffId());						  //代办人为当前登录人
			req.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());//查询代办任务
			// 查询有效
			req.setIsValid("1"); 
			Map<String,Object> map = operateRecordService.queryOperateRecordList(req);
			return map;
		} catch (Exception e) {
			logger.error("查询代办事项查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询已办事项
	 * @author fuliwei  
	 * @date 2018年9月9日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryHaveDoneNotice")
	@ResponseBody
	public Map<String,Object> queryHaveDoneNotice(HttpServletRequest request,OperateRecordQueryReq req){
		try {
			req.setSortInfo(request);
			LoginInfo login=SessionUtil.getLoginInfo();
			req.setOperaterId(login.getStaffId());						  //代办人为当前登录人
			req.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());//查询已办任务
			Map<String,Object> map = operateRecordService.queryOperateRecordList(req);
			return map;
		} catch (Exception e) {
			logger.error("查询已办事项查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 批量处理历史操作 记录
	 * @author fuliwei  
	 * @date 2018年11月6日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/updateHistoryOperateRecord")
	@ResponseBody
	public String updateHistoryOperateRecord(String projectType,String corpId){
		try {
			 operateRecordService.updateHistoryOperateRecordList(projectType,corpId);
			 return Constants.SUCCESS_CODE;
		} catch (Exception e) {
			logger.error("处理历史操作记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 批量处理审核记录
	 * @author fuliwei  
	 * @date 2018年11月6日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/updateHistoryManagERecord")
	@ResponseBody
	public String updateHistoryManagERecord(String projectType,String corpId){
		try {
			 operateRecordService.updateHistoryManagERecordList(projectType,corpId);
			 return Constants.SUCCESS_CODE;
		} catch (Exception e) {
			logger.error("处理审核记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 批量处理审核记录
	 * @author fuliwei  
	 * @date 2018年11月18日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/updateHistoryConsPlanList")
	@ResponseBody
	public String updateHistoryConsPlanList(String projectType,String corpId){
		try {
			 operateRecordService.updateHistoryConsPlanList(projectType,corpId);
			 return Constants.SUCCESS_CODE;
		} catch (Exception e) {
			logger.error("处理审核记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
