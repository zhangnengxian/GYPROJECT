package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.inspection.service.PurgeService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import com.alibaba.fastjson.JSON;
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


/**
 * 吹扫记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/purge")
public class PurgeController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PurgeController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**吹扫记录服务接口*/
	@Resource
	PurgeService purgeService;
	
	@Resource
	SignatureService signatureService;
	
	/**
	 * 吹扫记录主页面
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("checkType",ProjectChecklistTypeEnum.PURGE.getValue());//吹扫
		modelView.addObject("actionName","purge");//controller路径
		modelView.addObject("process",ProjectChecklistTypeEnum.PURGE.getMessage());//process
		modelView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue());//现场代表
		modelView.addObject("operatorPost",PostTypeEnum.TEST_LEADER.getValue());//操作员
		modelView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);//签字路径
		modelView.addObject("drawUrl",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH);//简图路径
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelView.setViewName("inspection/purge");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2016-07-19
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.PURGE.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 隐蔽报验单保存
	 * @author liaoyq
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try {
			//自动保存施工工序process
			 checkList.setProcess(ProjectChecklistTypeEnum.PURGE.getMessage());
			 String pcId=projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_PURGE);
			//回写记录表中pcId
			 purgeService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("吹扫报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询吹扫记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , PurgeReq purgeReq){
		try{
			purgeReq.setSortInfo(request);
			//根据吹扫记录信息
			Map<String,Object> map = purgeService.queryRecords(purgeReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("吹扫信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存记录区
	 * @author liaoyq
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListPReq checkListPReq){ 
		try {
			purgeService.saveRecords(checkListPReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("吹扫检查记录保存失败！", e);
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
	public Purge viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		Purge purge = purgeService.viewRecordById(id);
		return purge;
	}
	/**
	 * 详述
	 * @author liaoyq 
	 * @param
	 * @return ProjectChecklist
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			//菜单ID
			String menuId = request.getParameter("menuId");
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
			e.printStackTrace();
			return null;
		}
	}
	

	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  Purge purge){
		try {
			return purgeService.saveRecord(purge);
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
			logger.error("吹扫记录保存失败！",e);
			return null;
		}
	}


	@Resource
	SignNoticeService signNoticeService;
	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
		 * @return
	 */


	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true) Purge purge){
		try {
			purgeService.deleteRecordById(purge.getPurgeId());
			signatureService.deleteImgByBIdAndMenuId(purge.getPurgeId(), purge.getMenuId());
			signNoticeService.deleteByBsId(purge.getPurgeId(),ProjectChecklistTypeEnum.PURGE.getValue());   //删除签字通知
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("吹扫记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 保存吹扫记录报验单(包含简图)
	 * @author liaoyq
	 * @createTime 2017年7月25日
	 * @param
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
			purgeService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			//json.put("drawUrl", Constants.FIRST_DISK_PATH+request.getAttribute("drawUrl"));
			json.put("operateId", proCheckList.getOperateId());
			return json;
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			json.put("result", "already");
			return json;
		} catch (Exception e) {
			logger.error("信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
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
			purgeService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
