package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.enums.BusinessPartnersTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.baseinfo.service.SafetyPunishService;
import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionList;
import cc.dfsoft.project.biz.base.constructmanage.enums.InspectionListTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.InspectionListService;
import cc.dfsoft.project.biz.base.constructmanage.service.InspectionRecordService;
import cc.dfsoft.project.biz.base.inspection.controller.SurveyLiningController;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 安全检查
 * @author Administrator
 *
 */
@Controller  
@RequestMapping(value="/securityInspection")
public class SecurityInspectionController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(SurveyLiningController.class);
	/** 安全质量检查表服务*/
	@Resource
	InspectionListService inspectionListService;
	/** 安全质量检查记录服务*/
	@Resource
	InspectionRecordService inspectionRecordService;
	/** 工程服务*/
	@Resource
	ProjectService projectService;
	/** 分包合同服务*/
	@Resource
	SubContractService subContractService;
	/** 安全处罚工程服务*/
	@Resource
	SafetyPunishService safetyPunishService;
	
	@Resource
	BusinessPartnersService businessPartnersService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main() throws ParseException{
		ModelAndView modelView=new ModelAndView();
		//modelView.addObject("project", projectService.viewProject("1100"));
		//modelView.addObject("subContract", subContractService.findSubContractByprojId("1100"));
		modelView.addObject("SAFTYOFFICER",PostTypeEnum.SAFTYOFFICER.getValue());//安全员
		// 复查人职务
		modelView.addObject("reviewOfPeoplePostion",PostTypeEnum.SAFTYOFFICER.getValue());
		modelView.addObject("SUB_FIELDPRINCIPAL",PostTypeEnum.SUB_FIELDPRINCIPAL.getValue());//分包现场负责人
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		BusinessPartners bp=businessPartnersService.viewBusinessPartnersById(loginInfo.getDeptId());
		if(bp!=null&&bp.getUnitType().equals(BusinessPartnersTypeEnum.BUILD.getValue())){
		modelView.addObject("addBtnHide","true");//显示增加按钮
		}
		modelView.addObject("deptName",loginInfo.getDeptName()); 	//登录人部门
		modelView.setViewName("constructmanage/securityInspection");
		return modelView;
	}
	
	/**
	 * 安全处罚工程
	 * @return
	 */
	@RequestMapping(value="/securityTopPage")
	public ModelAndView securityTopPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("constructmanage/securityTopPage");
		return modelView;
	}
	/**
	 * 安全处罚工程查询
	 * @return
	 */
	@RequestMapping(value = "/querySafetyPunish")
	@ResponseBody
	public List<Map<String,Object>> querySafetyPunishTree(String corpId){
		try {
			return safetyPunishService.querySafetyPunishTree("1",corpId);
		} catch (Exception e) {
			logger.error("处罚工程查询失败！", e);
			return null;
		}
	}
	/**
	 * 安全质量检查单条件查询
	 * @param request
	 * @param inspectionRecordQueryReq
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping(value = "/queryInspectionList")
	@ResponseBody
	public Map<String,Object> queryInspectionList(HttpServletRequest request,InspectionListQueryReq inspectionListQueryReq) throws Exception {
		inspectionListQueryReq.setType(InspectionListTypeEnum.SECURITY.getValue());
			inspectionListQueryReq.setSortInfo(request);			
			return inspectionListService.queryInspectionList(inspectionListQueryReq);
	}
	/**
	 * 安全质量检查记录条件查询
	 * @param request
	 * @param inspectionRecordQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryInspectionRecord")
	@ResponseBody
	public Map<String,Object> queryInspectionRecord(HttpServletRequest request,InspectionRecordQueryReq inspectionRecordQueryReq){
		try {
			inspectionRecordQueryReq.setSortInfo(request);			
			return inspectionRecordService.queryInspectionRecord(inspectionRecordQueryReq);
		} catch (Exception e) {
			logger.error("安全质量检查记录查询失败！", e);
			return null;
		}
	}
	/**
	 * 查询详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewInspectionRecord")
	@ResponseBody
	public InspectionList viewInspectionRecordResult(HttpServletRequest request,@RequestParam(required=true) String id){
		InspectionList inspection=inspectionListService.viewInspectionListResult(id);
		return inspection;
	}
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/inspectionRecordSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("constructmanage/inspectionRecordSearchPopPage");
		return modelview;
	}
	/**
	 * 保存安全质量检查单
	 * @param inspectionList
	 * @return
	 */
	@RequestMapping(value = "/saveInspectionRecord")
	@ResponseBody
	public Map<String, Object> savePurge(HttpServletRequest request,@RequestBody(required = true) InspectionList inspectionList){
		inspectionList.setType(InspectionListTypeEnum.SECURITY.getValue());
		try {
			Map<String, Object> imgurl = inspectionListService.saveInspectionList(inspectionList);
			imgurl.put("objectId", inspectionList.getIlId());
			imgurl.put("result", Constants.OPERATE_RESULT_SUCCESS);
			return imgurl;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("检查记录保存失败！", e);
			Map<String,Object> result = new HashMap<String,Object>(16);
			result.put("result", Constants.OPERATE_RESULT_FAILURE);
			return result;
		}
	}
	
}
