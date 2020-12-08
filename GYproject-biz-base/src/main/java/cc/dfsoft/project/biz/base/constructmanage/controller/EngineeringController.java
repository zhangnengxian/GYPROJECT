package cc.dfsoft.project.biz.base.constructmanage.controller;

import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.EngineeringVisa;
import cc.dfsoft.project.biz.base.constructmanage.enums.EngineeringTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.project.biz.base.constructmanage.service.VisaQuantitiesRecordService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
import java.math.BigDecimal;
import java.util.Map;

/**
 * 签证记录
 *
 * @author cui
 */
@Controller
@RequestMapping(value="/engineering")
public class EngineeringController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(EngineeringController.class);

	@Resource
	EngineeringVisaService engineeringVisaService;

	/**工程量签证记录*/
	@Resource
	VisaQuantitiesRecordService visaQuantitiesRecordService;

	@Resource
	ProjectService projectService;

	@Resource
	ManageRecordService manageRecordService;
	@Resource
	SignNoticeService signNoticeService;
	@Resource
	AccessoryService accessoryService;

	/**
	 * 签证记录主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo =  SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost());
		modelView.addObject("dataAdminPost",PostTypeEnum.DATAMANAGER.getValue());

		modelView.addObject("budgetGroupLeader",PostTypeEnum.BUDGET_GROUP_LEADER.getValue());
		Map<String,String> deptInfo = projectService.getDeptInfoForStatistic();
		modelView.addObject("unitType", deptInfo.get("unitType"));
		modelView.addObject("cuUnitType",UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		modelView.addObject("stepId",StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());
		modelView.addObject("evType", EngineeringTypeEnum.values());//签证类型
		modelView.addObject("builder", PostTypeEnum.BUILDER.getValue());//甲方代表
		modelView.addObject("CU_PM", PostTypeEnum.CU_PM.getValue());//项目经理
		modelView.addObject("CONSTRUCTION", PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("suJgj", PostTypeEnum.SUJGJ.getValue());//监理
		modelView.addObject("budgeter", PostTypeEnum.BUDGET_MEMBER.getValue());//预算员
		modelView.addObject("costControlDepartment", PostTypeEnum.MANAGER.getValue());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("drawUrl1",Constants.DISK_PATH); 	//图片 url
		modelView.addObject("accessorySource", AccessorySourceEnum.COLLECT_FILE.getValue());//附件来源类型
		modelView.setViewName("constructmanage/engineeringRecord");
		return modelView;
	}
	/**
	 * 签证列表条件查询
	 * @author cui
	 * @createTime 2016-07-25
	 * @param engineeringVisaQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryEngineeringVisa")
	@ResponseBody
	public Map<String,Object> queryEngineeringVisa(HttpServletRequest request,EngineeringVisaQueryReq engineeringVisaQueryReq){
		try {
			engineeringVisaQueryReq.setSortInfo(request);
			return engineeringVisaService.queryEngineeringVisa(engineeringVisaQueryReq);
		} catch (Exception e) {
			logger.error("签证信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 查询详述
	 * @author cui
	 * @createTime 2016-7-25
	 * @param id
	 * @return EngineeringVisa
	 */
	@RequestMapping(value="/viewEngineeringVisa")
	@ResponseBody
	public EngineeringVisa viewEngineeringVisa(HttpServletRequest request,@RequestParam(required=true) String id){
		String menuDes=request.getParameter("menuDes");
		EngineeringVisa engineeringVisa= new EngineeringVisa();
		if(!id.equals("-1")){
			engineeringVisa = engineeringVisaService.viewEngineeringVisa(id,menuDes);
		}else{
			engineeringVisa.setVisaDate(engineeringVisaService.getDatabaseDate());
		}
		return engineeringVisa;
	}

	/**
	 * 签证信息保存
	 * @author cui
	 * @createTime  2016-7-25
	 * @param EngineeringVisa
	 * @return String
	 */
	/*@RequestMapping(value = "/saveEngineeringVisa")
	@ResponseBody
	public String saveEngineeringVisa(@RequestBody EngineeringVisa engineeringVisa){
		try {
			engineeringVisaService.saveEngineeringVisa(engineeringVisa);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("签证信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}*/

	/**
	 * 签证记录保存(包括图片)
	 * @author 张萌
	 * @createTime  2016-10-9
	 * @param changeManagement
	 * @return String
	 */
	@RequestMapping(value = "/saveEngineeringVisaFile")
	@ResponseBody
	public JSONObject saveDesignAlterationFile(HttpServletRequest request,UploadResult enginneringVisa,@RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			String evId = engineeringVisaService.saveEngineeringVisaFile(request,enginneringVisa, files);
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("operateId", evId);
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			json.put("accListId", request.getAttribute("accListId"));
			return json;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("变更信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}
	/**
	 * 签证记录保存(不包括图片)
	 * @author 张萌
	 * @createTime  2016-10-9
	 * @param changeManagement
	 * @return String
	 */
	@RequestMapping(value = "/saveEngineeringVisa")
	@ResponseBody
	public String saveDesignAlteration(HttpServletRequest request,@RequestBody(required=true) UploadResult enginneringVisa){
		try {
			String result = engineeringVisaService.saveEngineeringVisaFile(request,enginneringVisa, null);
			resetSignNotesStatus(enginneringVisa);
			return result;
			//return Constants.OPERATE_RESULT_SUCCESS;
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
	 * @methodDesc: 通知置为无效
	 * @author: zhangnx
	 * @date: 9:47 2018/10/17
	 */
	public  void resetSignNotesStatus(UploadResult enginneringVisa){
		EngineeringVisa cmt = JSON.parseObject(enginneringVisa.getResult(), EngineeringVisa.class);
		String post="noSign" ;   //没有签字的标识符
		if (MrResultEnum.NOT_PASSED.getValue().equals(cmt.getSuResult())){//现场监理：审核结果不通过时将签字通知（sign_notes）置为无效
			post= PostTypeEnum.SUJGJ.getValue();
		}else if (MrResultEnum.NOT_PASSED.getValue().equals(cmt.getCmoPrincipalResult())){//现场代表：审核结果不通过时将签字通知（sign_notes）置为无效
			post= PostTypeEnum.BUILDER.getValue();
		}
		SignNotice signNotice=signNoticeService.queryByProjIdAndPost(cmt.getProjId(),post, SignDataTypeEnum.ENGINEERING.getValue());
		if (signNotice!=null){
			signNoticeService.updateSignNotice(signNotice.getPost(),signNotice.getDataType(),signNotice.getBusinessOrderId(),signNotice.getProjId());
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
			System.out.println(accDto.getAlPath());
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 签证工程量记录
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param EngineeringVisaQueryReq
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryVisaQuantitiesRecord")
	@ResponseBody
	public Map<String,Object> queryVisaQuantitiesRecord(HttpServletRequest request,EngineeringVisaQueryReq engineeringVisaQueryReq){
		try {
			engineeringVisaQueryReq.setSortInfo(request);
			return visaQuantitiesRecordService.queryVisaQuantitiesRecord(engineeringVisaQueryReq);
		} catch (Exception e) {
			logger.error("签证工程量标准查询失败！", e);
			return null;
		}
	}
	/**
	 * 推送签证单
	 * @param evId
	 * @return
	 */
	@RequestMapping(value = "/pushEv")
	@ResponseBody
	public String pushEv(@RequestBody(required = true) String evId){
		try {
			return engineeringVisaService.pushEv(evId,new BigDecimal(0));
		} catch (Exception e) {
			logger.error("申请单推送失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 推送签证单-到预算
	 * @param evId
	 * @return
	 */
	@RequestMapping(value = "/pushEvToBudget")
	@ResponseBody
	public String pushEvToBudget(@RequestBody(required = true) String evId,String menuId){
		try {
			//验证签证预算书是否已上传
			//查询该签证是否已上传附件
			if(!accessoryService.isUploadFile(null, menuId,evId,AccessorySourceEnum.COLLECT_FILE.getValue())){
				return "no";
			}
			return engineeringVisaService.pushEvToBudget(evId);
		} catch (Exception e) {
			logger.error("申请单推送失败！", e);
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
			engineeringVisaService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	/**
	 * 查询签证审核历史
	 * @author liaoyq
	 * @createTime 2018年3月30日
	 * @param
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq){
		try {
			return manageRecordService.queryManageRecord(manageRecordQueryReq);
		} catch (Exception e) {
			logger.error("管理记录列表查询失败！",e);
			return null;
		}
	}


    @RequestMapping(value = "/updateEngineeringVisa")
    @ResponseBody
    public boolean updateEngineeringVisa(String evId,String auditState){
       return engineeringVisaService.updateEngineeringVisa(evId,auditState);

	}






	/**
	 * 签证作废
	 * @author 王会军
	 * @createTime 2019年2月28日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/deleteEV")
	@ResponseBody
	public boolean deleteEV(String evId,String reason){
		try{
			Budget budget = new Budget();
			budget.setCmId(evId);  //设置签证记录ID
			if(StringUtils.isNotBlank(reason)){
				budget.setAuditOpinion(reason);
			}else{
				budget.setAuditOpinion("/");
			}
			engineeringVisaService.updateEngineeringVisaState(budget);
			return true;
		}catch(Exception e){
			logger.error("签证记录作废失败！",e);
			return false;
		}
	}
	/**
	 * 作废弹窗页面
	 * @return
	 */
	@RequestMapping(value = "/optionPage")
	public ModelAndView optionPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("plan/engineerSearchPage");
		return modelAndView;
	}

}
