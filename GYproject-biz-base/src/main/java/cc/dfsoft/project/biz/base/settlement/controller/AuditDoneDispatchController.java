package cc.dfsoft.project.biz.base.settlement.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditDoneService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/auditDoneDispatch")
public class AuditDoneDispatchController {	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AuditDoneDispatchController.class);
	/** 报审服务接口 */
	@Resource
	SettlementDeclarationService settlementDeclarationService;
	@Resource
	SettlementAuditService settlementAuditService;
	@Resource
	SettlementAuditDoneService settlementAuditDoneService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	DocTypeService docTypeService;
	@Resource
	SignatureService signatureService;
	@Resource
	ProjectService projectService;
	@Resource
	DesignInfoService designInfoService;
	/** 分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("preSettlementGroup",DeptDivideEnum.PRE_SETTLEMENT_GROUP.getValue());//预结算组
		modelView.addObject("customerServiceCenter", DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//客服中心
		modelView.setViewName("settlement/auditDoneDispatch");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptType",DeptTypeEnum.GROUP_COMPANY.getValue());
		modelView.setViewName("settlement/auditDoneDispatchRight");
		return modelView;
	}
	
	/**
	 * 弹出搜索
	 * @author cui
	 * @createTime 2016-6-27
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("area",AreaEnum.values());
		modelview.setViewName("settlement/declarationSearchPopPage");
		return modelview;
	}
	
	/**
	 * 工程列表查询
	 * @param projectQueryReq
	 * @createTime 2016-06-24
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.AUDIT_DONE_DISPATCH.getValue()); //待终审派遣状态查询
			projectQueryReq.setSortInfo(request);
			//projectQueryReq.setStepId(StepEnum.AUDIT_DONE_DISPATCH.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author cui
	 * @createTime 2016-11-21
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewDispatchResult")
	@ResponseBody
	public SettlementDeclaration viewDispatchResult(@RequestParam(required=true) String id){
		try {
			return settlementDeclarationService.getSettlementDeclaration(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new SettlementDeclaration();
		}
	}
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2016-6-29
	 * @return String 
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			settlementAuditDoneService.update(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 设计员列表查询(弃用)
	 * @param DesignerQueryReq
	 * @createTime 2016-07-1
	 * @return
	 */
	@RequestMapping(value = "/queryMember")
	@ResponseBody
	public Map<String,Object> queryMember(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setPostType(PostTypeEnum.BUDGET_MEMBER.getValue());  			//结算主管
			designerQueryReq.setDeptId(departmentService.queryDepartmentByDivide(designerQueryReq.getDeptDivide(),loginInfo.getDeptId()).getDeptId());
		    //designerQueryReq.setPostType2(PostTypeEnum.COST_MANAGER.getValue());  		//结算经理
			designerQueryReq.setProjStatus(ProjStatusEnum.REPORT_DONE_CONFIRM.getValue());	//待终审确认
//			designerQueryReq.setProjStatusId(ProjStatusEnum.TO_OUT_SKETCH.getValue()); 		//待出草图 
			result= settlementAuditService.queryMember(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询造价员信息
	 * @param request
	 * @param designerQueryReq
	 * @return  Map<String,Object>
	 */
	@RequestMapping(value = "/querySettleMember")
	@ResponseBody
	public Map<String,Object> querySettleMember(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			designerQueryReq.setSortInfo(request);
			//designerQueryReq.setPostType(PostTypeEnum.MANAGER.getValue());  			//结算主管
			//designerQueryReq.setPostType2(PostTypeEnum.COST_MANAGER.getValue());  		//结算经理
			
			//查询是否配置sql
			List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey("0_"+loginInfo.getDeptId()+"_"+designerQueryReq.getMenuId());
			if(list!=null && list.size()>0){
				designerQueryReq.setDeptId(list.get(0).getSupSql());
			}else{
				designerQueryReq.setDeptId(departmentService.queryDepartmentByDivide(designerQueryReq.getDeptDivide(),null).getDeptId());
			}
			designerQueryReq.setCorpId(loginInfo.getCorpId());								//所属公司
			designerQueryReq.setPostType(PostTypeEnum.BUDGET_MEMBER.getValue());  	//结算员
			designerQueryReq.setProjStatus(ProjStatusEnum.REPORT_DONE_CONFIRM.getValue());	//待终审确认
			result= settlementAuditService.querySettleMember(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
}
