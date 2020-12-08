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

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListUIReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.UndergroundInpectReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.UndergroundInpect;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.inspection.service.UndergroundInpectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 埋地检查
 * @author liaoyq
 */
@Controller
@RequestMapping(value="undergroundInpect")
public class UndergroundInpectController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(UndergroundInpectController.class);
	
	@Resource
	private UndergroundInpectService undergroundInpectService;
	
	@Resource
	private ProjectChecklistService proChecklistService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","undergroundInpect");//controller路径
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.UNDER_GROUND_INPECT.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue()); //施工员
		modelAndView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue()); //现场代表
		modelAndView.addObject("fieldsRepresentPost1",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelAndView.addObject("projectLeaderPost",PostTypeEnum.CU_PM.getValue()); //项目负责人
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelAndView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelAndView.setViewName("inspection/undergroundInpect");
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
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.UNDER_GROUND_INPECT.getValue());
			Map<String,Object> map= proChecklistService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
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
			checkList.setProcess(ProjectChecklistTypeEnum.UNDER_GROUND_INPECT.getMessage());
			String pcId = proChecklistService.saveProCheckList(checkList, Constants.MODULE_CODE_UNDER_GROUND_INPECT);
			//回写记录表中pcId
			undergroundInpectService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			 return pcId;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch (Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("埋地检查 签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 埋地检查记录记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , UndergroundInpectReq undergroundInpectReq){
		try{
			undergroundInpectReq.setSortInfo(request);
			//埋地检查记录
			Map<String,Object> map = undergroundInpectService.queryRecords(undergroundInpectReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("埋地检查信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存记录区
	 * @author liaoyq
	 * @param request
	 * @param checkListHWReq
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListUIReq checkListReq){ 
		try {
			undergroundInpectService.saveRecords(checkListReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("埋地检查检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @param request
	 * @param id 
	 * @return
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public UndergroundInpect viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		UndergroundInpect ug = undergroundInpectService.viewRecordById(id);
		return ug;
	}
	/**
	 * 详述
	 * @author liaoyq 
	 * @param pcId
	 * @return ProjectChecklist
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			ProjectChecklist checklist = proChecklistService.viewProjectCheckList(id);
			return checklist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	public String saveRecord(@RequestBody(required = true)  UndergroundInpect ug){
		try {
			return undergroundInpectService.saveRecord(ug);
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			logger.error("埋地检查记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true) UndergroundInpect ug){
		try {
			undergroundInpectService.deleteRecordById(ug.getUgiId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("埋地检查记录删除失败！",e);
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
			return proChecklistService.updateFlag(checkList.getPcId(),checkList.getFlag());
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
			undergroundInpectService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
	
}
