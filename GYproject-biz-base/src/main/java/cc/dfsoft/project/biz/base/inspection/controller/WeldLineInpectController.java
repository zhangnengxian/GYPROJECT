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

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListWLIReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.WeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.WeldLineInpect;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.inspection.service.WeldLineInpectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 管道焊缝外观检查记录
 * @author liaoyq
 */
@Controller
@RequestMapping(value="/weldLineInpect")
public class WeldLineInpectController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(WeldLineInpectController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	
	@Resource
	WeldLineInpectService weldLineInpectService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","weldLineInpect");//controller路径名
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.WELD_LINE_INPECT.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelAndView.addObject("welder",PostTypeEnum.WELDER.getValue());		//焊工
		modelAndView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue());		//监理员
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
				
		
		modelAndView.setViewName("inspection/weldLineInpect");
		return modelAndView;
	}
	
	/**
	 * 查询报验列表
	 * @param request
	 * @param proCheckListReq
	 * @return
	 */
	@RequestMapping(value="/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.WELD_LINE_INPECT.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 报验单详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist checklist = proCheckListService.viewProjectCheckList(id);
		return checklist;
	}
	
	/**
	 * 保存签字信息
	 * @return
	 */
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.WELD_LINE_INPECT.getMessage());
			String pcId = proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_WELD_LINE_INPECT);
			//回写记录表中pcId
			weldLineInpectService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			return pcId;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("管道焊缝检查 签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询管道焊缝检查记录列表
	 * @return
	 */
	@RequestMapping(value="/queryWeldLineInpect")
	@ResponseBody
	public Map<String, Object> queryWeldLineInpect(HttpServletRequest request , WeldLineInpectReq WeldLineInpectReq){
		try{
			WeldLineInpectReq.setSortInfo(request);
			//根据管道焊缝检查记录信息
			Map<String,Object> map = weldLineInpectService.queryWeldLineInpect(WeldLineInpectReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("管道焊缝检查信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存管道焊缝检查记录(不包括图片)
	 * @author 张萌
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListWLIReq checkListReq){ 
		try {
			weldLineInpectService.saveRecords(checkListReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("管道焊缝检查检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	/**
	 * 查询管道焊缝检查记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , WeldLineInpectReq req){
		try{
			req.setSortInfo(request);
			//根据管道焊缝检查记录信息
			Map<String,Object> map = weldLineInpectService.queryRecords(req);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("管道焊缝检查信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public WeldLineInpect viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		WeldLineInpect wi = weldLineInpectService.viewRecordById(id);
		return wi;
	}
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param request
	 * @param preservative
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  WeldLineInpect wi){
		try {
			return weldLineInpectService.saveRecord(wi);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("管道焊缝检查记录保存失败！",e);
			return null;
		}
	}
	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param request
	 * @param id 记录ID
	 * @return
	 */
	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true) WeldLineInpect wi){
		try {
			weldLineInpectService.deleteRecordById(wi.getWliId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("管道焊缝检查记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 完成报验单
	 * 修改flag
	 * @param checkList
	 * @return
	 */
	@RequestMapping(value="/completed")
	@ResponseBody
	public String completed(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			return proCheckListService.updateFlag(checkList.getPcId(),checkList.getFlag());
			//return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
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
			weldLineInpectService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}

}
