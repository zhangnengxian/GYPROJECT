package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownApprovalReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutDownRecord;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownPushStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.ShutdownApprovalService;
import cc.dfsoft.project.biz.base.constructmanage.service.ShutdownRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 复工申报
 * @author liaoyq
 */
@Controller
@RequestMapping(value="/shutdownAproval")
public class ShutdownAprovalController {
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(ShutdownAprovalController.class);
	@Resource
	private ShutdownRecordService shutdownRecordService;
	@Resource
	private ShutdownApprovalService shutdownApprovalService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		//modelView.addObject("fieldsRepresentPost",PostTypeEnum.FIELDS_REPRESENT);//建设单位代表
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("cuManagerPost",PostTypeEnum.CU_PM.getValue());//施工经理
		modelView.addObject("sucesPost",PostTypeEnum.SUCSE.getValue());//总监理工程师
		modelView.addObject("fieldsRepresentPost",PostTypeEnum.SUCSE.getValue());//现场代表
		modelView.setViewName("constructmanage/shutdownAproval");
		return modelView;
	}
	
	/**
	 * 查询复工报审列表
	 * @param request
	 * @param shutdownApprovalReq
	 * @return
	 */
	@RequestMapping(value="/queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request,ShutdownApprovalReq shutdownApprovalReq){
		try {
			shutdownApprovalReq.setSortInfo(request);
			Map<String, Object> map=shutdownApprovalService.queryList(shutdownApprovalReq);
			return map;
		} catch (Exception e) {
			logger.error("复工报审列表查询失败!", e);
			return null;
		}
		
	}
	
	/**
	 * 查询停工记录列表
	 * @param request
	 * @param shutdownApprovalReq
	 * @return
	 */
	@RequestMapping(value="/queryShutdownRecordList")
	@ResponseBody
	public Map<String, Object> queryShutdownRecordList(HttpServletRequest request,ShutdownApprovalReq shutdownApprovalReq){
		try {
			shutdownApprovalReq.setSortInfo(request);
			
			//shutdownRecordReq.setSdrStatus(Integer.parseInt(ShutdownStatusEnum.SHUTDOWN.getValue()));
			Map<String, Object> map=shutdownApprovalService.queryList(shutdownApprovalReq);
			return map;
		} catch (Exception e) {
			logger.error("停工记录列表查询失败!", e);
			return null;
		}
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/viewSearchPopPage")
	public ModelAndView viewSearchPopPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pushStatusEnum", ShutdownPushStatusEnum.values());
		modelView.setViewName("constructmanage/shutdowAprovalSearchPopPage");
		return modelView;
	}
	/**
	 * 根据停工令id查询停工信息和复工报审信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewShutdownAproval")
	@ResponseBody
	public ShutdownApproval viewShutdownAproval(HttpServletRequest request,@RequestParam(required=true) String id){
		//根据停工令id查询复工报审信息
		ShutdownApproval shutdownApproval=shutdownApprovalService.queryBySdrId(id);
		if(shutdownApproval==null){
			shutdownApproval  = new ShutdownApproval();
		}
		//根据停工令id查询停工信息
		ShutDownRecord shutDownRecord = shutdownRecordService.queryById(id);
		if(shutDownRecord!=null){
			shutdownApproval.setShutDownRecord(shutDownRecord);
		}
		return shutdownApproval;
	}
	/**
	 * 保存复工报审信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ShutdownApproval shutdownApproval){
		try{
			shutdownApprovalService.saveShutdownApproval(shutdownApproval);
		    return Constants.OPERATE_RESULT_SUCCESS;
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存复工报审信息失败!", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	@RequestMapping(value="pushShutdownApprovl")
	@ResponseBody
	public String pushShutdownApprovl(@RequestBody(required = true) ShutdownApproval shutdownApproval){
		try{
			shutdownApprovalService.pushShutdownApprovl(shutdownApproval);
		    return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("推送复工报审信息失败!", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveSignNotice")
	@ResponseBody
	public void saveSignNotice(@RequestBody(required = true) String cwId){
		try {
			shutdownApprovalService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
