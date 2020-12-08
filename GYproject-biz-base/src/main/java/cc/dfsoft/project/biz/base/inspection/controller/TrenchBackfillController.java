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

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListTBReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.TrenchBackfillReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.inspection.service.TrenchBackfillService;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;

/**
 * 沟槽回填controller
 * @author liaoyq
 *
 */
@Controller
@RequestMapping(value="/trenchBackfill")
public class TrenchBackfillController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(TrenchBackfillController.class);
	
	/**沟槽回填实例 */
	@Resource
	TrenchBackfillService trenchBackfillService;
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**
	 * 沟槽回填主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("checkType",ProjectChecklistTypeEnum.TRENCH_BACK_FILL.getValue());
		modelView.addObject("actionName","trenchBackfill");//controller路径名
		modelView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue());//现场代表
		modelView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("projectLeaderPost",PostTypeEnum.CU_PM.getValue());//项目负责人
		modelView.addObject("constructionQcPost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质保人
		modelView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelView.setViewName("inspection/trenchBackfill");
		return modelView;
	}
	
	/**
	 * 工程报验单列表查询（沟槽回填记录）
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
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.TRENCH_BACK_FILL.getValue());
			return projectChecklistService.queryPrProjectChecklist(proCheckListReq);
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存沟槽回填(包括图片)
	 * @author 张萌
	 * @createTime  2016-10-08
	 * @param 
	 * @return 
	 */
	/*@RequestMapping(value = "/saveTrenchBackFile")
	@ResponseBody
	public JSONObject saveTrenchBackFile(HttpServletRequest request,UploadResult changeManagement,@RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			trenchBackfillService.saveTrenchBackfill(request,changeManagement, files);				
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
			json.put("operateId", changeManagement.getOperateId());
			return json;
		} catch (Exception e) {
			logger.error("变更信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}*/
	
	@RequestMapping(value="saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.TRENCH_BACK_FILL.getMessage());
			String pcId= projectChecklistService.saveProCheckList(checkList, Constants.MODULE_CODE_TRENCH_BACK_FILL);
			//回写记录表中pcId
			trenchBackfillService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("沟槽回填  签字区保存失败！",e);
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
			//System.out.println(accDto.getAlPath());
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
			logger.error("保存失败！", e);
		} 
	 
		return null;
		
	}
	
	
	/**
	 * 保存沟槽回填记录(不包括图片)
	 * @author 张萌
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListTBReq checkListTBReq){ 
		try {
			trenchBackfillService.saveRecords(checkListTBReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("沟槽回填检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	
	/**
	 * 详述
	 * @author liaoyq
	 * @createTime 2017-7-21
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
	 * 查询沟槽回填记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , TrenchBackfillReq trenchBackfillReq){
		try{
			trenchBackfillReq.setSortInfo(request);
			//根据沟槽回填记录信息
			Map<String,Object> map = trenchBackfillService.queryRecords(trenchBackfillReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("沟槽回填信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public TrenchBackfill viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		TrenchBackfill trenchBackfill = trenchBackfillService.viewRecordById(id);
		return trenchBackfill;
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
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  TrenchBackfill tb){
		try {
			return trenchBackfillService.saveRecord(tb);
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
			logger.error("沟槽回填记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true) TrenchBackfill tb){
		try {
			trenchBackfillService.deleteRecordById(tb.getTbId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("沟槽回填记录删除失败！",e);
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
			trenchBackfillService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
}
