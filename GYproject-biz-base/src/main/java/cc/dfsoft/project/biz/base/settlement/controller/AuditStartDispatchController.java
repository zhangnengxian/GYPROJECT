package cc.dfsoft.project.biz.base.settlement.controller;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementDeclarationService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/auditStartDispatch")
public class AuditStartDispatchController {	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AuditStartDispatchController.class);
	/** 结算审核服务接口 */
	@Resource
	SettlementAuditService settlementAuditService;
	@Resource
	SettlementDeclarationService settlementDeclarationService;
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
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("settlement/auditStartDispatch");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("deptType",DeptTypeEnum.SUBCOMPANY.getValue());
		modelView.setViewName("settlement/auditStartDispatchRight");
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
			//projectQueryReq.setProjStatusId(ProjStatusEnum.AUDIT_START_DISPATCH.getValue()); //待初审派遣状态查询
			projectQueryReq.setSortInfo(request);
			//projectQueryReq.setStepId(StepEnum.AUDIT_START_DISPATCH.getValue());
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
			SettlementDeclaration result = new SettlementDeclaration();
			try {
				result = settlementDeclarationService.getSettlementDeclaration(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2016-6-29
	 * @return String 
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody DesignDispatchDto designDispatchReq){
		try {
			settlementDeclarationService.update(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("派工区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 结算员列表查询
	 * @param DesignerQueryReq
	 * @createTime 2016-07-1
	 * @return
	 */
	@RequestMapping(value = "/queryBudgetMember")
	@ResponseBody
	public Map<String,Object> queryBudgetMember(HttpServletRequest request, DesignerQueryReq designerQueryReq){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			designerQueryReq.setSortInfo(request);
			designerQueryReq.setPostType(PostTypeEnum.BUDGET_MEMBER.getValue());  			//结算员
//			designerQueryReq.setPostType2(PostTypeEnum.DESIGN_DIRECTOR.getValue());  		//设计所长
			designerQueryReq.setProjStatus(ProjStatusEnum.SETTLEMENT_REPORT_START.getValue());	//待结算初审 
//			designerQueryReq.setProjStatusId(ProjStatusEnum.TO_OUT_SKETCH.getValue()); 		//待出草图 
			result= settlementAuditService.queryBudgetMember(designerQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
}
