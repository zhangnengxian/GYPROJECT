package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPipeReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.PipeWeldingService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

@Controller
@RequestMapping(value="/pipewelding")
public class PipeWeldingController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PipeWeldingController.class);
	
	@Resource
	PipeWeldingService pipeWeldingService;
	@Resource
	ProjectChecklistService proCheckListService;
	/** 附件记录服务接口 */
	@Resource 
	AccessoryService accessoryService;
	
	@Resource
	SignatureService signatureService;
	
	/**
	 * 钢管焊接主页面
	 * @return
	 */
	@RequestMapping(value="main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("actionName","pipewelding");//controller路径名
		modelView.addObject("checkType",ProjectChecklistTypeEnum.PIPE_WELDING.getValue());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("checkerPost",PostTypeEnum.CHECKER.getValue());//检查人
		modelView.addObject("projectLeaderPost",PostTypeEnum.PROJECT_LEADER.getValue());//项目负责人
		modelView.addObject("welderPost",PostTypeEnum.WELDER.getValue());//焊接人员
		modelView.addObject("CUST_REPRESENTPOST",PostTypeEnum.CUST_REPRESENT.getValue());//甲方
		modelView.addObject("CONSTRUCTIONPOST",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("SUJGJPOST",PostTypeEnum.SUJGJ.getValue()); //监理
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		
		modelView.setViewName("inspection/pipeWelding");
		return modelView;
	}
	
	/**
	 * 右侧页面
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}
	
	/**
	 * 钢管焊接记录列表查询
	 * @param ProCheckListReq
	 * @author 刘博
	 * @createTime 2016-07-21
	 * @return
	 */
	@RequestMapping(value = "/queryPipeWelding")
	@ResponseBody
	public Map<String,Object> queryPipeWelding(HttpServletRequest request,PipeWeldingReq pipeWeldingReq){
		try {
			pipeWeldingReq.setSortInfo(request);
			Map<String,Object> map= pipeWeldingService.queryPipeWelding(pipeWeldingReq);
			return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 工程报验单列表查询（钢管焊接记录）
	 * @param ProCheckListReq
	 * @author liubo
	 * @createTime 2016-07-22
	 * @return
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.PIPE_WELDING.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
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
	 * 查询钢管焊接记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , PipeWeldingReq pipeWeldingReq){
		try{
			pipeWeldingReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = pipeWeldingService.queryRecords(pipeWeldingReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("钢管焊接记录查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存钢管焊接记录信息
	 * @return
	 */
	@RequestMapping(value="saveRecords")
	@ResponseBody
	public String saveRecords(@RequestBody(required = true) ProjectCheckListPipeReq checkListReq){
		try{
			pipeWeldingService.saveRecords(checkListReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存钢管焊接记录失败！",e);
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
			checkList.setProcess(ProjectChecklistTypeEnum.PIPE_WELDING.getMessage());
			String pcId= proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_PIPEWELDING);
			//回写记录表中pcId
			pipeWeldingService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("钢管焊接签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 钢管焊接记录ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public PipeWelding viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		PipeWelding pli= pipeWeldingService.viewRecordById(id);
		return pli;
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
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  PipeWelding pw){
		try {
			return pipeWeldingService.saveRecord(pw);
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
			logger.error("钢管焊接记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true)  PipeWelding pw){
		try {
			pipeWeldingService.deleteRecordById(pw.getPipeId());
			signatureService.deleteImgByBIdAndMenuId(pw.getPipeBaseType(), pw.getMenuId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("钢管焊接记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 保存钢管焊接记录(批量增加)
	 * @author 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/savePipewelding")
	@ResponseBody
	public String savePipewelding(@RequestBody ProjectCheckListPipeReq req){ 
		try {
			pipeWeldingService.savePipewelding(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存钢管焊接记录失败！", e);
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
			//增加工序
			projectChecklist.setProcess(ProjectChecklistTypeEnum.PIPE_WELDING.getMessage());
			return proCheckListService.saveProCheckList(projectChecklist,Constants.MODULE_CODE_PIPEWELDING);
		} catch (Exception e) {
			logger.error("保存报验单失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request,AccessoryList accDto,@RequestParam(value = "files[]", required = false) MultipartFile[] files) {
		
		try {
			String result= accessoryService.uploadAccessoryBusRecordId(request, accDto, files);
			JSONArray js = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject json = new JSONObject();
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("result", result);
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println(accDto.getAlPath());
			logger.error("保存钢管焊接记录失败！", e);
			logger.error("路径", accDto.getAlPath());
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
		} 
		return null;
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
