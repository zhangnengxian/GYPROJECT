package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGrReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PressureRecords;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.GrooveRecordService;
import cc.dfsoft.project.biz.base.inspection.service.PressureRecordsService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 复压记录
 * @author wangang
 *
 */
@Controller
@RequestMapping(value="/pressureRecords")
public class PressureRecordsController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PressureRecordsController.class);
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	/**沟槽记录服务接口*/
	@Resource
	GrooveRecordService grooveRecordService;

	/**复压记录服务接口*/
	@Resource
	PressureRecordsService pressureRecordsService;

	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	/**签字服务接口*/
	@Resource
	SignatureService signatureService;
	
	@Resource
	SignNoticeService signNoticeService;

	/**
	 * 复压记录主页面
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("actionName","pressureRecords");//controller路径名
		modelView.addObject("checkType",ProjectChecklistTypeEnum.PRESSURE_RECORDS.getValue());
		
		String url=Constants.DISK_PATH+Constants.SIGN_DISK_PATH;
		modelView.addObject("imgUrl",url); 	//img url
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		modelView.setViewName("inspection/pressureRecords");
		return modelView;
	}

	/**
	 * 工程报验单列表查询
	 * @author wangang
	 * @createTime 2019-01-07
	 * @return Map
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.PRESSURE_RECORDS.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 右侧页面
	 * @return ModelAndView
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}
	
	/**
	 * 复压记录列表查询
	 * @author wangang
	 * @createTime 2019-01-07
	 * @return Object
	 */
	@RequestMapping(value = "/queryPressureRecords")
	@ResponseBody
	public Object queryPressureRecords(HttpServletRequest request,GrooveRecordReq req){
		try {
			req.setSortInfo(request);
			Map<String,Object> map= pressureRecordsService.queryPressureRecords(req);
	        return map;//.get("data");
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存报验单
	 * @author wangang
	 * @param projectChecklist
	 * @return String
	 */
	@RequestMapping(value = "/saveCheckList")
	@ResponseBody
	public String saveCheckList(@RequestBody ProjectChecklist projectChecklist){ 
		try {
			projectChecklist.setProcess(ProjectChecklistTypeEnum.PRESSURE_RECORDS.getMessage());
			projectChecklist.setFlag(0);
			return proCheckListService.saveProCheckList(projectChecklist,Constants.PRESSURE_RECORDS);
		} catch (Exception e) {
			logger.error("保存报验单失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 保存复压记录(批量增加)
	 * @author wangang
	 * @param list
	 * @return String
	 */
	@RequestMapping(value = "/saveGrooveRecord")
	@ResponseBody
	public String savePressureRecords(@RequestBody ProjectCheckListGrReq projectCheckListGrReq){
		try {
			pressureRecordsService.savePressureRecords(projectCheckListGrReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("更新复压记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 根据报验单ID 查询报验详述
	 * @return
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewElectrodeBaking(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist projectChecklist =  proCheckListService.viewProjectCheckList(id);
		return projectChecklist;
	}
	
	/**
	 * 查询复压记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , GrooveRecordReq grReq ){
		try{
			grReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = pressureRecordsService.queryRecords(grReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("焊条烘干记录信息查询失败！",e);
			return null;
		}
	}

	/**
	 * 保存记录区信息
	 * @return
	 */
	@RequestMapping(value="/saveRecords")
	@ResponseBody
	public String saveElectrodeRecord(@RequestBody ProjectCheckListGrReq checkListGrReq){
		try{
			pressureRecordsService.saveRecords(checkListGrReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存复压记录失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 保存签字区信息
	 */
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.PRESSURE_RECORDS.getMessage());
			String pcId= proCheckListService.saveProCheckList(checkList, Constants.PRESSURE_RECORDS);
			//回写记录表中pcId
			pressureRecordsService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			e.printStackTrace();
			logger.error("复压记录签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 * @author wangang
	 * @createTime 2019-01-07
	 * @param id 记录ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public PressureRecords viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		PressureRecords eb = pressureRecordsService.viewRecordById(id);
		return eb;
	}

	/**
	 * 保存记录(一条记录)
	 * @author wangang
	 * @param request
	 * @param preservative
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  PressureRecords pr){
		try {
			return pressureRecordsService.saveRecord(pr);
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
			logger.error("复压记录保存失败！",e);
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
	@RequestMapping(value="/deleteRecord/{pcDesId}")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true)  PressureRecords pr,@PathVariable(value="pcDesId") String pcDesId){
		try {
			pressureRecordsService.deleteRecordById(pr.getId());
			//删除签字 
			//signatureService.deleteImgByBIdAndMenuId(gr.getGrId(),gr.getMenuId());
			//signNoticeService.deleteByBsId(gr.getGrId(),pcDesId);   //删除签字通知
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("记录删除失败！",e);
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
			proCheckListService.updateFlag(checkList.getPcId(),checkList.getFlag());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

}
