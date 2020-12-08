package cc.dfsoft.project.biz.base.inspection.controller;

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

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.AcceptanceRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.AcceptanceRecordService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 验收登记
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/acceptanceRegister")
public class AcceptanceRegisterController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AcceptanceRegisterController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	@Resource
	SignatureService signatureService;
	
	@Resource
	AcceptanceRecordService acceptanceRecordService;
	/**
	 * 打开验收登记 主页面
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("checkType",ProjectChecklistTypeEnum.ACCEPTANCE_REGISTER.getValue());//
		modelView.addObject("actionName","acceptanceRegister");//controller路径
		modelView.addObject("process",ProjectChecklistTypeEnum.ACCEPTANCE_REGISTER.getMessage());//process
		
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);//签字路径
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
		modelView.addObject("acceptancePost",PostTypeEnum.INSPECTOR.getValue()); //客服部 职务
		modelView.addObject("custCenterSign",PostTypeEnum.INSPECTOR.getValue()); //客服部 验收员
		
		modelView.setViewName("inspection/acceptanceRegister");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei  
	 * @date 2018年12月4日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.ACCEPTANCE_REGISTER.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 报验
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try {
			//自动保存施工工序process
			 checkList.setProcess(ProjectChecklistTypeEnum.ACCEPTANCE_REGISTER.getMessage());
			 String pcId=projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_PIPE_WELD_RECORD);
			//回写记录表中pcId
			 acceptanceRecordService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			 return pcId;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("焊口记录报验单保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询验收记录
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , ElectrodeRecordReq req){
		try{
			req.setSortInfo(request);
			//查询验收记录信息
			Map<String,Object> map = acceptanceRecordService.queryRecords(req);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("验收记录信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存一条记录
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  AcceptanceRecord pwr){
		try {
			return acceptanceRecordService.saveRecord(pwr);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("焊口记录保存失败！",e);
			return null;
		}
	}
	
	
	/**
	 * 查询验收记录详述
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public AcceptanceRecord viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		AcceptanceRecord acceptanceRecord = acceptanceRecordService.viewRecordById(id);
		return acceptanceRecord;
	}
	
	/**
	 * 删除记录
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true)  AcceptanceRecord pwr){
		try {
			acceptanceRecordService.deleteRecordById(pwr.getArId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("验收记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 完成报验单
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/completed")
	@ResponseBody
	public String completed(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			return projectChecklistService.updateFlag(checkList.getPcId(),checkList.getFlag());
			//return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询报验单详述
	 * @author fuliwei  
	 * @date 2018年12月9日  
	 * @version 1.0
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			//菜单ID
			String menuId = request.getParameter("menuId");
			//查询报验单详述
			ProjectChecklist checklist = projectChecklistService.viewProjectCheckList(id);
			if (checklist!=null) {
				//查询简图信息
				Signature signature = signatureService.queryImg(checklist.getPcId(), menuId);
				if(signature!=null){
					checklist.setDrawUrl(Constants.DIAGRAM_DISK_PATH+signature.getImgUrl());
					checklist.setMenuDes(signature.getMenuDes());
				}
			}
			return checklist;
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
}
