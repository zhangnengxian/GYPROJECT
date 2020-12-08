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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dto.HiddenWorksReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListHWReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.HiddenWorksService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;


/**
 * 隐藏工程报验
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/hiddenWorks")
public class HiddenWorksController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(HiddenWorksController.class);
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**隐蔽工程检查记录服务接口*/
	@Resource
	HiddenWorksService hiddenWorksService;
	/**
	 * 隐蔽工程主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("checkType",ProjectChecklistTypeEnum.HIDDEN_WORKS.getValue());//隐蔽工程
		modelView.addObject("actionName","hiddenWorks");//controller路径
		modelView.addObject("constructionQcPost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue());//现场代表
		modelView.addObject("safetyOfficerPost",PostTypeEnum.SAFTYOFFICER.getValue());//安全员
		modelView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);//签字路径
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		
		modelView.setViewName("inspection/hiddenWorks");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2016-07-22
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.HIDDEN_WORKS.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 隐蔽报验单保存
	 * @author liaoyq
	 * @createTime  2017-7-21
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try {
			//自动保存施工工序process
			 checkList.setProcess(ProjectChecklistTypeEnum.HIDDEN_WORKS.getMessage());
			 String pcId=projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_HIDDEN_WORKS);
			//回写记录表中pcId
			 hiddenWorksService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("隐蔽工程报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	

	
	/**
	 * 隐蔽工程验收记录保存(包括图片)
	 * @author zm
	 * @createTime  2016-10-10
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveHiddenWorksRecordFile")
	@ResponseBody
	public JSONObject saveHiddenWorksRecordFile(HttpServletRequest request,UploadResult hiddenWorks,@RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			//return hiddenWorksService.saveHiddenWorks(hiddenWorks,Constants.MODULE_CODE_HIDDEN_WORKS);
			hiddenWorksService.saveHiddenWorksRecordFile(request,hiddenWorks, files);
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			json.put("drawUrl", request.getAttribute("drawUrl"));
			json.put("operateId", hiddenWorks.getOperateId());
			return json;
		} catch (Exception e) {
			logger.error("变更信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
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
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
			logger.error("保存失败",e);
		} 
	 
		return null;
		
	}
	

	
	/**
	 * 保存记录(不包括图片)
	 * @author 张萌
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListHWReq checkListHWReq){ 
		try {
			hiddenWorksService.saveRecords(checkListHWReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("隐蔽工程检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	
	/**
	 * 查询隐蔽工程记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , HiddenWorksReq hiddenWorksReqt){
		try{
			hiddenWorksReqt.setSortInfo(request);
			//根据隐蔽工程记录信息
			Map<String,Object> map = hiddenWorksService.queryRecords(hiddenWorksReqt);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("隐蔽工程信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 根据记录ID查询记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public HiddenWorks viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		HiddenWorks hiddenWorks = hiddenWorksService.viewRecordById(id);
		return hiddenWorks;
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
			ProjectChecklist checklist = projectChecklistService.viewProjectCheckList(id);
			return checklist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	public String saveRecord(@RequestBody(required = true)  HiddenWorks hw){
		try {
			return hiddenWorksService.saveRecord(hw);
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			logger.error("隐蔽工程记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true)  HiddenWorks hw){
		try {
			hiddenWorksService.deleteRecordById(hw.getHwId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("隐蔽工程记录删除失败！",e);
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
			hiddenWorksService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
