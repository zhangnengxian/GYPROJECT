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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dto.IndoorPocketWatchReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListIPWReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.IndoorPocketWatch;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.IndoorPocketWatchService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 户内挂表Controller
 * @author liaoyq
 */
@Controller
@RequestMapping(value="/indoorPocketWatch")
public class IndoorPocketWatchController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(IndoorPocketWatchController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	@Resource
	IndoorPocketWatchService indoorPocketWatchService;
	
	/**
	 * 打开主页面
	 * @author liaoyq
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.INDOOR_POCKET_WATCH.getValue());//报验类型
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("actionName","indoorPocketWatch");//controller路径名
		modelAndView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue());//现场代表
		modelAndView.addObject("cuPm",PostTypeEnum.CU_PM.getValue());//项目经理
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelAndView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelAndView.setViewName("inspection/indoorPocketWatch");
		return modelAndView;
	}
	
	/**
	 * 保存签字区信息(报验信息)
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param request
	 * @param checkList  报验单信息
	 * @return 报验单ID
	 */
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.INDOOR_POCKET_WATCH.getMessage());
			String pcId= projectChecklistService.saveProCheckList(checkList, Constants.MODULE_CODE_INDOOR_POCKET_WATCH);
			//回写记录表中pcId
			indoorPocketWatchService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			return pcId;
			//return Constants.OPERATE_RESULT_SUCCESS;
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
			logger.error("户内挂表签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 保存清扫记录报验单(包含简图)
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param checkList
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
			String pcId=projectChecklistService.saveProCheckListFile(request, proCheckList, files,ProjectChecklistTypeEnum.CLEAR_RECORD.getValue());				
			//回写记录表中pcId
			ProjectChecklist checkList = JSON.parseObject(result, ProjectChecklist.class);
			indoorPocketWatchService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("result",pcId);
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
			logger.error("信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}
	/**
	 * 分页查询报验单列表
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param request
	 * @param checklistQueryReq  报验单查询辅助类 dto
	 * @return 报验单列表及分页信息
	 */
	@RequestMapping(value="/queryCheckList")
	@ResponseBody
	public Map<String, Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq checklistQueryReq ){
		try {
			checklistQueryReq.setSortInfo(request);
			checklistQueryReq.setPcDesId(ProjectChecklistTypeEnum.INDOOR_POCKET_WATCH.getValue());
			return projectChecklistService.queryPrProjectChecklist(checklistQueryReq);
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 根据报验单ID查询报验单详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param request
	 * @param id 报验单ID
	 * @return 报验单详述
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			ProjectChecklist checklist = projectChecklistService.viewProjectCheckList(id);
			return checklist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 分页查询户内挂表记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param request
	 * @param indoorPocketWatchReq
	 * @return 户内挂表记录及分页信息
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , IndoorPocketWatchReq indoorPocketWatchReq){
		try{
			indoorPocketWatchReq.setSortInfo(request);
			//户内挂表记录信息
			Map<String,Object> map = indoorPocketWatchService.queryRecords(indoorPocketWatchReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("户内挂表信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存户内挂表记录信息
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param request
	 * @param checkListIPWReq 户内挂表保存辅助类
	 * @return 操作结果
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListIPWReq checkListIPWReq){ 
		try {
			indoorPocketWatchService.saveIndoorPocketWatchServiceRecord(checkListIPWReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("户内挂表记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	
	/**
	 * 根基户内挂表记录查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param request
	 * @param id 户内挂表记录ID
	 * @return 户内挂表记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public IndoorPocketWatch viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		IndoorPocketWatch indoorPocketWatch = indoorPocketWatchService.viewRecordById(id);
		return indoorPocketWatch;
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
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  IndoorPocketWatch record){
		try {
			return indoorPocketWatchService.saveRecord(record);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("焊条领用保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true) IndoorPocketWatch record){
		try {
			indoorPocketWatchService.deleteRecordById(record.getIpwId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("焊条灵领用删除失败！",e);
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
			indoorPocketWatchService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
