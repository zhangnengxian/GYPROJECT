package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.entity.SurveyInfo;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.design.service.SurveyInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.DateUtil;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 勘察结果登记
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/surveyResultRegister")
public class SurveyResultRegisterController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);

	/**工程服务接口*/
	@Resource
	ProjectService projectService;

	/**勘察信息接口*/
	@Resource
	SurveyInfoService surveyInfoService;

	/** 附件服务接口 */
	@Resource
	AccessoryService accessoryService;

	/** 设计信息服务 */
	@Resource
	DesignInfoService designInfoService;


	/** 工程服务接口 */
	@Resource
	ProjectTypeService projectTypeService;

	/**部门服务*/
	@Resource
	DepartmentService departmentService;
	@Resource
	ManageRecordService  manageRecordService;

	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		//modelView.addObject("changeDispatch",ButtonLimitsEnum.CHANGE_DISPATCH.getType());
		Department dep = departmentService.queryDepartmentByDivide(DeptDivideEnum.DESIGN_INSTITUTE.getValue(),null);//设计院Id
		modelView.addObject("designInstituteId",dep.getDeptId());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("notThrough",Constants.MODULE_CODE_PROJECTAPPROVAL);
		modelView.addObject("stepId",StepEnum.CONNECT_GAS_AUDIT.getValue());
		modelView.addObject("curStepId",StepEnum.SURVEY_RESULT_REGISTER.getValue());
		modelView.addObject("sysDate",manageRecordService.getDatabaseDate());
		modelView.addObject("currentDate", DateUtil.getDate(new Date()));
		modelView.setViewName("design/surveyResultRegister");
		return modelView;
	}

	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(String projectTypeId,String projId,String corpId,String menuId){

		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String ctmKey=(StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId)+"_"+(StringUtil.isBlank(projectTypeId)?ProjLtypeEnum.CIVILIAN.getValue():projectTypeId)+"_"+menuId;//分公司+类型配置
		String cmKey=(StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId)+"_"+menuId;//分公司默认配置
		String defaultPage = "surveyResultRegisterRight";//全局默认

		String resultViewUrl= queryRightPage(ctmKey)!=null?queryRightPage(ctmKey).toString():queryRightPage(cmKey)!=null?queryRightPage(cmKey).toString():defaultPage;

		ModelAndView modelView=new ModelAndView();
		modelView.addObject("unitType", UnitTypeEnum.GAS_COMPANY.getValue());	// 燃气集团
		modelView.addObject("designerPost", PostTypeEnum.DESIGNER.getValue());	// 设计员
		modelView.addObject("surveyPost", PostTypeEnum.SURVEYER.getValue());	// 踏勘员
		modelView.addObject("surveyBuilderPost", PostTypeEnum.BUILDER.getValue());	//现场代表
		modelView.addObject("directorPost", PostTypeEnum.DIRECTOR.getValue());	// 客户经理
		modelView.addObject("market", PostTypeEnum.SALESMA.getValue());		    // 市场营销员
		modelView.addObject("backReason", BackReasonEnum.values());				//退单原因
		modelView.addObject("post", loginInfo.getPost());						//登录人职务
		modelView.addObject("deptDivide", loginInfo.getDeptDivide());			//部门划分
		modelView.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//部门划分:客服中心
		modelView.addObject("marketingCenter",DeptDivideEnum.MARKETING_CENTER.getValue());//部门划分:营销中心
		List<ProjectType> projectType=projectTypeService.queryAllList();
		modelView.addObject("projectType",projectType);//工程类型
		modelView.setViewName("design/"+resultViewUrl);
		return modelView;
	}


	public Object queryRightPage(String key){
		return Constants.getSysConfigByKey(key);
	}

	/**
	 * 弹出搜索
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("area",AreaEnum.values());
		modelview.setViewName("design/surveyRegisterSearchPopPage");
		return modelview;
	}

	/**
	 * 弹出设计员列表
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/designerPopPage")
	public ModelAndView designerPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("design/surveyRegisterSearchPopPage");
		return modelview;
	}

	/**
	 * 弹出资料上传---没用
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/dataPopPage")
	public ModelAndView dataPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("design/dataPopPage");
		return modelview;
	}

	/**
	 * 文件上传---没用
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request,AccessoryList accDto,@RequestParam(value = "files[]", required = false) MultipartFile[] files) {
		try {
			accessoryService.uploadAccessory(request, accDto, files);
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
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
			logger.error("保存失败",e);
		}
		return null;
	}

	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author fuliwei
	 * @createTime 2017-07-25
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_RESULT_REGISTRY.getValue());//待现场踏勘
			projectQueryReq.setStepId(StepEnum.SURVEY_RESULT_REGISTER.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年7月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/viewSurveyResult")
	@ResponseBody
	public SurveyInfo viewSurveyResult(HttpServletRequest request,@RequestParam(required=true) String id){
		SurveyInfo si= surveyInfoService.viewSurveyInfo(id);
		return si;
	}

	/**
	 * 推送现场踏勘
	 * @author fuliwei
	 * @createTime 2017-7-25
	 * @param  surveyInfo 勘察信息
	 * @return String
	 */
	@RequestMapping(value = "/saveSurveyInfo")
	@ResponseBody
	public String saveSurveyInfo(HttpServletRequest request,@RequestBody(required = true) SurveyInfo surveyInfo){
		try {
			return surveyInfoService.saveSurveyInfo(surveyInfo);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("现场踏勘操作区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 保存现场踏勘
	 * @author fuliwei
	 * @createTime 2017-7-25
	 * @param  surveyInfo 勘察信息
	 * @return String
	 */
	@RequestMapping(value = "/saveSurvey")
	@ResponseBody
	public String saveSurvey(HttpServletRequest request,@RequestBody(required = true) SurveyInfo surveyInfo){
		try {
			return surveyInfoService.saveSurvey(surveyInfo);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("现场踏勘操作区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 改派保存----没用
	 * @param designDispatchReq
	 * @return
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			designInfoService.updateSurveyer(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
