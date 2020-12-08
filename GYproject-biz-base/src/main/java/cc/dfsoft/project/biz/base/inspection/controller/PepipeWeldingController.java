package cc.dfsoft.project.biz.base.inspection.controller;

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

import cc.dfsoft.project.biz.base.inspection.dto.PePipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPePipeReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.PepipeWelding;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.PePipeWeldingService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/pepipewelding")
public class PepipeWeldingController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PepipeWeldingController.class);
	
	/**工程报验单实例*/
	@Resource
	ProjectChecklistService proCheckListService;
	
	/**PE管焊接记录实例*/
	@Resource
	PePipeWeldingService pePipeWeldingService;
	/***/
	@Resource
	SignatureService signatureService;
	
	
	@Resource
	SignNoticeService signNoticeService;
	/**
	 * PE管焊接主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView mian(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("actionName","pepipewelding");//controller路径名
		modelView.addObject("checkType",ProjectChecklistTypeEnum.PEPIPE_WELDING.getValue());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("welderPost",PostTypeEnum.WELDER.getValue());
		modelView.addObject("checkerPost",PostTypeEnum.CONSTRUCTION.getValue());
		modelView.addObject("projectLeaderPost",PostTypeEnum.CU_PM.getValue());
		
		modelView.addObject("suJgjpost",PostTypeEnum.SUJGJ.getValue());
		modelView.addObject("builderpost",PostTypeEnum.BUILDER.getValue());
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			    	 //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 		//现场监理
		//报验单升级时间
		modelView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelView.setViewName("inspection/pePipeWelding");
		return modelView;
	}
	
	public ModelAndView viewPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}
	
	/**
	 * 工程报验单列表查询（PE管焊接记录）
	 * @param ProCheckListReq
	 * @author liubo
	 * @createTime 2016-07-26
	 * @return
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.PEPIPE_WELDING.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * PE管焊接记录列表查询
	 * @param ProCheckListReq
	 * @author 刘博
	 * @createTime 2016-07-26
	 * @return 
	 */
	@RequestMapping(value = "/queryPePipeWelding")
	@ResponseBody
	public Object queryPePipeWelding(HttpServletRequest request,PePipeWeldingReq pePipeWeldingReq){
		try {
			pePipeWeldingReq.setSortInfo(request);
			Map<String,Object> map= pePipeWeldingService.queryPePipeWelding(pePipeWeldingReq);
			if(map.isEmpty()){
				return null;
			}else{
				return map.get("data");
			}
		} catch (Exception e) {
			logger.error("PE管焊接记录信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存PE管焊接记录(批量增加)
	 * @author 刘博
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/savePepipewelding")
	@ResponseBody
	public String savePepipewelding(@RequestBody ProjectCheckListPePipeReq req){ 
		
		try{
			pePipeWeldingService.savePepipewelding(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("保存PE管焊接记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 保存报验单
	 * @author liubo
	 * @param projectChecklist
	 * @return
	 */
	@RequestMapping(value = "/saveCheckList")
	@ResponseBody
	public String saveCheckList(@RequestBody ProjectChecklist projectChecklist){ 
		try {
			projectChecklist.setProcess(ProjectChecklistTypeEnum.PEPIPE_WELDING.getMessage());
			return proCheckListService.saveProCheckList(projectChecklist,Constants.MODULE_CODE_PEPIPEWELDING);
		} catch (Exception e) {
			logger.error("保存报验单失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询详述
	 * @return 
	 */
	@RequestMapping(value="viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=proCheckListService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 查询PE管焊接记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , PePipeWeldingReq pepipeWeldingReq){
		try{
			pepipeWeldingReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = pePipeWeldingService.queryRecords(pepipeWeldingReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("PE管焊接记录查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存PE管焊接记录信息
	 * @return
	 */
	@RequestMapping(value="saveRecords")
	@ResponseBody
	public String saveRecords(@RequestBody(required = true) ProjectCheckListPePipeReq checkListReq){
		try{
			pePipeWeldingService.saveRecords(checkListReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存PE管焊接记录失败！",e);
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
			checkList.setProcess(ProjectChecklistTypeEnum.PEPIPE_WELDING.getMessage());
			String pcId= proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_PEPIPEWELDING);
			//回写记录表中pcId
			pePipeWeldingService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("PE管焊接签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id PE管焊接记录ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public PepipeWelding viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		PepipeWelding pepw= pePipeWeldingService.viewRecordById(id);
		return pepw;
	}
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @param request
	 * @param preservative
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  PepipeWelding pepw){
		try {
			return pePipeWeldingService.saveRecord(pepw);
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
			logger.error("PE管焊接记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true)  PepipeWelding pepw,@PathVariable(value="pcDesId") String pcDesId){
		try {
			pePipeWeldingService.deleteRecordById(pepw.getPeId());
			signatureService.deleteImgByBIdAndMenuId(pepw.getPeId(), pepw.getMenuId());
			signNoticeService.deleteByBsId(pepw.getPeId(),pcDesId);   //删除签字通知
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("PE管焊接记录删除失败！",e);
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
			pePipeWeldingService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveSignNoticeReocrd")
	@ResponseBody
	public void saveSignNoticeReocrd(@RequestBody(required = true) String cwId){
		try {
			pePipeWeldingService.saveSignNoticeReocrd(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
