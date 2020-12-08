package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.baseinfo.enums.UnitEnum;
import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.change.enums.ChangeStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.enums.AuditStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.FileUtil;
@Controller
@RequestMapping(value="/designAlteration")
public class DesignAlterationController {
  
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DesignAlterationController.class);
	
	@Resource
	ChangeManagementService changeManagementService;
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("post", loginInfo.getPost());//职务
		modelView.addObject("auditState", AuditStateEnum.TO_AUDIT.getValue());//审核状态---待审
		modelView.addObject("cmState", ChangeStateEnum.NO_HANDLE.getValue());
		modelView.addObject("changeType", ChangeTypeEnum.values());//变更类型
		modelView.addObject("suPrincipal", PostTypeEnum.SUJGJ.getValue());//现场监理师
		modelView.addObject("designer", PostTypeEnum.DESIGNER.getValue());//设计员
		modelView.addObject("auditer", PostTypeEnum.DIRECTOR.getValue());//处长
		modelView.addObject("approvaler", PostTypeEnum.CHIEF_ENGINEER.getValue());//总工
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//img url
		
		modelView.addObject("cuPm", PostTypeEnum.CU_PM.getValue());//项目经理
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("constructionPost",PostTypeEnum.CONSTRUCTION.getValue());  	//施工员
		modelView.addObject("sucse", PostTypeEnum.SUCSE.getValue());		//总监监理师
		modelView.addObject("cuUnitType",UnitTypeEnum.CONSTRUCTION_UNIT.getValue());//施工单位类型
		Map<String,String> deptInfo = projectService.getDeptInfoForStatistic();
		modelView.addObject("unitType", deptInfo.get("unitType"));
		modelView.setViewName("constructmanage/designAlteration");
		return modelView;
	}
	
	/**
	 * 变更列表条件查询
	 * @author cui
	 * @createTime 2016-07-20
	 * @param changeManagementQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryDesignAlteration")
	@ResponseBody
	public Map<String,Object> queryDesignAlteration(HttpServletRequest request,ChangeManagementQueryReq changeManagementQueryReq){
		try {
			changeManagementQueryReq.setSortInfo(request);
			Map<String, Object> stringObjectMap = changeManagementService.queryChangeManagement(changeManagementQueryReq);
			return stringObjectMap;
		} catch (Exception e) {
			logger.error("变更信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author cui
	 * @createTime 2016-7-20
	 * @param id
	 * @return ChangeManagement
	 */
	@RequestMapping(value="/viewChangeManagement")
	@ResponseBody
	public ChangeManagement viewChangeManagement(HttpServletRequest request,@RequestParam(required=true) String id){
		String menuDes=request.getParameter("menuDes");
		ChangeManagement changeManagement=changeManagementService.viewChangeManagement(id,menuDes);
		return changeManagement;
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
			//accessoryService.uploadAccessory(request, accDto, files);
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

			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(accDto.getAlPath());
			FileUtil.deleteFile(Constants.DISK_PATH+accDto.getAlPath());
			e.printStackTrace();
		} 
	 
		return null;
		
	}
	/**
	 * 变更记录保存
	 * @author pengtt
	 * @createTime  2016-7-21
	 * @param changeManagement
	 * @return String
	 */
	@RequestMapping(value = "/saveDesignAlterationFile")
	@ResponseBody
	public JSONObject saveDesignAlterationFile(HttpServletRequest request,UploadResult changeManagement,@RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			changeManagementService.saveChangeManagement(request,changeManagement, files);				
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("operateId", changeManagement.getOperateId());
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			return json;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			 json.put("result", "already");
			 return json;
		}catch(StaleObjectStateException e ){
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
	 * 变更记录保存
	 * @author zhangjj
	 * @createTime  2016-7-21
	 * @param changeManagement
	 * @return String
	 */
	@RequestMapping(value = "/saveDesignAlteration")
	@ResponseBody
	public String saveDesignAlteration(HttpServletRequest request,@RequestBody(required=true) UploadResult changeManagement){
		try {
			changeManagementService.saveChangeManagement(request,changeManagement, null);
			//handleNotice(changeManagement.getOperateId());

			return changeManagement.getOperateId();
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
			e.printStackTrace();
			logger.error("变更信息保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	/**
	 * 变更记录保存
	 * @author cui
	 * @createTime  2016-7-21
	 * @param changeManagement
	 * @return String
	 *//*
	@RequestMapping(value = "/saveDesignAlteration")
	@ResponseBody
	public String saveDesignAlteration(@RequestBody ChangeManagement changeManagement){
		try {
			changeManagementService.saveChangeManagement(changeManagement);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("变更信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}*/

	/**
	 * 变更记录推送至造价处
	 * @param cmId
	 * @return
	 */
	@RequestMapping(value = "/pushDesignAlteration")
	@ResponseBody
	public String pushDesignAlteration(String cmId){
		try {
			return changeManagementService.pushChangeManagement(cmId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("变更信息推送失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	/**
	 * 通过工程id查询工程对象
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/findByProjId")
	@ResponseBody
	public ChangeManagement findByProjId(String projId){
		try {
			return changeManagementService.findByProjId(projId);
		} catch (Exception e) {
			logger.error("工程id查询工程对象失败！", e);
			return null;
		}
	}
	
	
	//变更审核保存
	@RequestMapping(value = "/saveDesignAlterationAudit")
	@ResponseBody
	public ResultMessage saveDesignAlterationAudit(HttpServletRequest request,@RequestBody(required=true) UploadResult changeManagement){
		ResultMessage resultMessage = new ResultMessage();
		try {
			changeManagementService.saveChangeManagementAudit(request,changeManagement, null);
			handleNotice(changeManagement.getOperateId());
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			resultMessage.setRet_message(changeManagement.getOperateId());
			return resultMessage;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message("already");
			return resultMessage;
		} catch (ExpressException e) {
			e.printStackTrace();
			logger.error("变更信息保存失败！", e);
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("变更信息保存失败！", e);
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}
	public  void handleNotice(String cwId) {
		changeManagementService.saveSignNotice(cwId);
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
			changeManagementService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
