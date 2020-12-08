package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPWRReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWeldRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.PipeWeldRecordService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 表具录入controller
 * @author liaoyq
 * @createTime 2017年7月25日
 */
@Controller
@RequestMapping(value="/meterRecord")
public class MeterRecordController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(MeterRecordController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**焊口记录服务接口*/
	@Resource
	PipeWeldRecordService pipeWeldRecordService;
	
	@Resource
	SignatureService signatureService;

	@Resource
	SignNoticeService signNoticeService;
	
	/**
	 * 打开焊口记录主页面
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("checkType",ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getValue());//
		modelView.addObject("actionName","pipeWeldRecord");//controller路径
		modelView.addObject("process",ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getMessage());//process
		
		modelView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue());//现场代表
		modelView.addObject("projectLeaderPost",PostTypeEnum.CU_PM.getValue());//项目负责人
		modelView.addObject("welderPost",PostTypeEnum.WELDER.getValue());//焊接人员
		modelView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		
		
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);//签字路径
		modelView.addObject("drawUrl",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH);//简图路径
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelView.setViewName("inspection/meterRecord");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存焊口记录报验单(不包含简图)
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try {
			//自动保存施工工序process
			 checkList.setProcess(ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getMessage());
			 String pcId=projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_PIPE_WELD_RECORD);
			//回写记录表中pcId
			 pipeWeldRecordService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
	 * 保存焊口记录报验单(包含简图)
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/saveSignFile")
	@ResponseBody
	public JSONObject saveSignFile(HttpServletRequest request,UploadResult proCheckList,@RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		String result = proCheckList.getResult();
		try {
			String pcId=projectChecklistService.saveProCheckListFile(request, proCheckList, files,ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getValue());				
			//回写记录表中pcId
			ProjectChecklist checkList = JSON.parseObject(result, ProjectChecklist.class);
			pipeWeldRecordService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("result", pcId);
			//json.put("drawUrl", request.getAttribute("drawUrl"));
			json.put("drawUrl", Constants.DIAGRAM_DISK_PATH+request.getAttribute("drawUrl"));
			json.put("operateId", proCheckList.getOperateId());
			return json;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			json.put("result", "already");
			return json;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("变更信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}
	
	/**
	 * 查询焊口记录列表
	 * @author liaoyq 
	 * @createTime 2017年7月25日
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , PipeWeldRecordReq pipeWeldRecordReq){
		try{
			pipeWeldRecordReq.setSortInfo(request);
			//查询焊口记录信息
			Map<String,Object> map = pipeWeldRecordService.queryRecords(pipeWeldRecordReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("焊口记录信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存记录区
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param request
	 * @param checkListHWReq
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListPWRReq checkListCRReq){ 
		try {
			pipeWeldRecordService.saveRecords(checkListCRReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("焊口记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param request
	 * @param id 记录ＩＤ
	 * @return
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public PipeWeldRecord viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		PipeWeldRecord pipeWeldRecord = pipeWeldRecordService.viewRecordById(id);
		return pipeWeldRecord;
	}
	/**
	 * 报验单详述
	 * @author liaoyq 
	 * @createTime 2017年7月25日
	 * @param id 报验单ＩＤ　
	 * @return ProjectChecklist
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
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @param pwr
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  PipeWeldRecord pwr){
		try {
			return pipeWeldRecordService.saveRecord(pwr);
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
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @return
	 */



	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true)  PipeWeldRecord pwr){
		try {
			pipeWeldRecordService.deleteRecordById(pwr.getPwrId());
			signNoticeService.deleteByBsId(pwr.getPwrId(),ProjectChecklistTypeEnum.PIPE_WELD_RECORD.getValue());   //删除签字通知

			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("焊口记录删除失败！",e);
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
			return projectChecklistService.updateFlag(checkList.getPcId(),checkList.getFlag());
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
			pipeWeldRecordService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
