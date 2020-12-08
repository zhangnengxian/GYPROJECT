package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGrReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.GrooveRecordService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 管沟开挖
 * @author liaoyq
 *
 */
@Controller
@RequestMapping(value="/grooveRecord")
public class GrooveRecordController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(GrooveRecordController.class);
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	/**沟槽记录服务接口*/
	@Resource
	GrooveRecordService grooveRecordService;
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	/**签字服务接口*/
	@Resource
	SignatureService signatureService;
	
	@Resource
	SignNoticeService signNoticeService;
	/**
	 * 沟槽记录主页面
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("actionName","grooveRecord");//controller路径名
		modelView.addObject("checkType",ProjectChecklistTypeEnum.GROOVE_RECORD.getValue());
		
		String url=Constants.DISK_PATH+Constants.SIGN_DISK_PATH;
		modelView.addObject("imgUrl",url); 	//img url
		modelView.addObject("CUST_REPRESENTPOST",PostTypeEnum.BUILDER.getValue());//甲方
		modelView.addObject("CONSTRUCTIONPOST",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("SUJGJPOST",PostTypeEnum.SUJGJ.getValue()); //监理
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelView.setViewName("inspection/grooveRecord");
		return modelView;
	}
	/**
	 * 工程报验单列表查询
	 * @param ProCheckListReq
	 * @author zhangjj
	 * @createTime 2016-07-21
	 * @return Map
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.GROOVE_RECORD.getValue());
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
	 * 沟槽记录列表查询
	 * @param ProCheckListReq
	 * @author zhangjj
	 * @createTime 2016-07-06
	 * @return Object
	 */
	@RequestMapping(value = "/queryGrooveRecord")
	@ResponseBody
	public Object queryGrooveRecord(HttpServletRequest request,GrooveRecordReq req){
		try {
			req.setSortInfo(request);
			Map<String,Object> map= grooveRecordService.queryGrooveRecord(req);
	        return map;//.get("data");
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 根据工程编号获取施工计划
	 * @param projNo
	 * @return Object
	 */
	@RequestMapping(value = "/queryConstructPlan")
	@ResponseBody
	public Object queryConstructPlan(String projNo){
		try{
		List<ConstructionPlan> list= constructionPlanService.findByProjNo(projNo);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
	       
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			
		}
		return null;
	}
	
	/**
	 * 保存报验单
	 * @author zhangjj
	 * @param projectChecklist
	 * @return String
	 */
	@RequestMapping(value = "/saveCheckList")
	@ResponseBody
	public String saveCheckList(@RequestBody ProjectChecklist projectChecklist){ 
		try {
			projectChecklist.setProcess(ProjectChecklistTypeEnum.GROOVE_RECORD.getMessage());
			projectChecklist.setFlag(0);
			return proCheckListService.saveProCheckList(projectChecklist,Constants.MODULE_CODE_GROOVE_RECORD);
		} catch (Exception e) {
			logger.error("保存报验单失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 保存沟槽记录(批量增加)
	 * @author zhangjj
	 * @param list
	 * @return String
	 */
	@RequestMapping(value = "/saveGrooveRecord")
	@ResponseBody
	public String saveGrooveRecord(@RequestBody ProjectCheckListGrReq projectCheckListGrReq){ 
		try {
			grooveRecordService.saveGrooveRecord(projectCheckListGrReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("更新管沟开挖记录失败！", e);
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
	 * 查询焊条烘干记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryEclectrodeRecords(HttpServletRequest request , GrooveRecordReq grReq ){
		try{
			grReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = grooveRecordService.queryRecords(grReq);
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
			grooveRecordService.saveRecords(checkListGrReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存管沟开挖记录失败！",e);
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
			checkList.setProcess(ProjectChecklistTypeEnum.GROOVE_RECORD.getMessage());
			String pcId= proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_GROOVE_RECORD);
			//回写记录表中pcId
			grooveRecordService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("管沟开挖签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 记录ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public GrooveRecord viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		GrooveRecord eb = grooveRecordService.viewRecordById(id);
		return eb;
	}
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @param request
	 * @param gr
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  GrooveRecord gr){
		try {
			return grooveRecordService.saveRecord(gr);
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
			logger.error("管沟开挖记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true)  GrooveRecord gr,@PathVariable(value="pcDesId") String pcDesId){
		try {
			grooveRecordService.deleteRecordById(gr.getGrId());
			//删除签字 
			signatureService.deleteImgByBIdAndMenuId(gr.getGrId(),gr.getMenuId());
			signNoticeService.deleteByBsId(gr.getGrId(),pcDesId);   //删除签字通知
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
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
			grooveRecordService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
