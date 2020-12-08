package cc.dfsoft.project.biz.base.plan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.contract.controller.ContractAuditController;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.enums.FeedbackStateEnum;
import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanDetailReq;
import cc.dfsoft.project.biz.base.plan.dto.ProcurementPlanReq;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.entity.ProcurementPlanDetail;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;
import cc.dfsoft.project.biz.base.plan.enums.ProjectOperateEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanDetailService;
import cc.dfsoft.project.biz.base.plan.service.ProcurementPlanService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
/**
 * 采购计划
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/procurementPlan")
public class ProcurementPlanController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	/**采购计划服务接口*/
	@Resource
	ProcurementPlanService procurementPlanService;
	
	/**采购计划明细服务接口*/
	@Resource
	ProcurementPlanDetailService procurementPlanDetailService;
	
	/**工程计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	@Resource
	ProjectService projectService;
	
	/**合同*/
	@Resource
	ContractService contractService;
	
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
		modelView.addObject("alreadyExport", ProcurementPlanExport.ALREADY_EXPORT.getValue());//已导出
		modelView.addObject("haveNotExport", ProcurementPlanExport.HAVE_NOT_EXPORT.getValue());//未导出
		modelView.setViewName("plan/procurementPlan");
		return modelView;
	}
	
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("plan/procurementPlanRight");
		return modelview;
	}
	
	@RequestMapping(value="/queryNote")
	@ResponseBody
	public String queryNote(String projId){
		try {
			if(projId!=null){
				ConstructionPlan cp = constructionPlanService.converDTO(projId);
				return cp.getRemark();
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/queryProject")
	@ResponseBody
	public Project queryProject(String id){
		try {
			Project project = projectService.queryProjectById(id);
			Contract contract=contractService.findByProjId(id);
			if(contract!=null && project!=null){
				
			}
			return project;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/saveNote")
	@ResponseBody
	public String saveNote(String projId,String note){
		try {
			ConstructionPlan cp = constructionPlanService.converDTO(projId);
			cp.setRemark(note);
			constructionPlanService.saveNotePlan(cp);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	@RequestMapping(value="/saveProcureFeedback")
	@ResponseBody
	public String saveProcureFeedback(String projId,String procureFeedback){
		try {
			projectService.updateFeedInfo(projId,procureFeedback);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	/**
	 * 采购计划条件查询
	 * @param request
	 * @param procurementPlanReq
	 * @return
	 */
	@RequestMapping(value = "/queryProcurementPlan")
	@ResponseBody
	public Map<String,Object> queryProcurementPlan(HttpServletRequest request,ProcurementPlanReq procurementPlanReq){
		try {
			procurementPlanReq.setSortInfo(request);
			
			/*if(StringUtils.isBlank(procurementPlanReq.getIsExport())){
				procurementPlanReq.setIsExport(ProcurementPlanExport.HAVE_NOT_EXPORT.getValue());//默认未导出
			}*/
			
			Map<String,Object> map=procurementPlanService.queryProcurementPlan(procurementPlanReq);
			return map;
		} catch (Exception e) {
			logger.error("采购计划信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/procurementPlanSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isFeedBack", FeedbackStateEnum.getObjByType("1"));//是否反馈
		modelview.addObject("projectOperate", ProjectOperateEnum.values());//工程操作
		modelview.addObject("isExport", ProcurementPlanExport.values());//是否导出
		List manageOfficeList =departmentService.queryManagementOffice();
		modelview.addObject("manageOfficeList",manageOfficeList);	//施工管理处
		modelview.setViewName("plan/procurementPlanSearchPopage");
		return modelview;
	}
	
	/**
	 * 采购计划明细条件查询
	 * @param request
	 * @param procurementPlanDetailReq
	 * @return
	 */
	@RequestMapping(value = "/queryProcurementPlanDetail")
	@ResponseBody
	public Map<String,Object> queryProcurementPlanDetail(HttpServletRequest request,ProcurementPlanDetailReq procurementPlanDetailReq){
		try {
			procurementPlanDetailReq.setSortInfo(request);
			Map<String,Object> map=procurementPlanDetailService.queryProcurementPlanDetail(procurementPlanDetailReq);
			return map;
		} catch (Exception e) {
			logger.error("采购计划明细查询失败！", e);
			return null;
		}
	}
	/**
	 * 导出Excel文件
	 * @param response
	 * @param procurPlanId
	 */
	@RequestMapping(value = "/exportExcel")
	public void exprotExcel(HttpServletResponse response,String  procurPlanId){
		try {
			List<ProcurementPlanDetail> procurementPlanDetails=procurementPlanDetailService.findByProcurPlanId(procurPlanId);
			String jsonString = JSON.toJSONString(procurementPlanDetails);
			JSONArray myJsonArray = JSONArray.parseArray(jsonString);
			String title = "采购计划明细";
			String[] excelHeader = {"材料名称","规格型号","单位","数量"};
			String[] propertyNames = {"materialName","materialSpec","materialUnit","materialNum"};
			Integer[] headerWidth = {10000,2500,2500,2500};
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "Procurement Plan Detail");
			map.put("remarkInfoHeight", "0");
			ExcelXlsxUtil.export(response, title, excelHeader, headerWidth, propertyNames, map, myJsonArray);

		} catch (Exception e) {
			logger.error("采购计划明细查询失败！", e);
		}
		
	}
	/**
	 * 采购计划导出标记
	 * @param procurPlanId
	 * @return
	 */
	@RequestMapping(value = "/signprocurementPlanExport")
	@ResponseBody
	public String signprocurementPlanExport(@RequestBody(required = true) String procurPlanId){
		try {
			return procurementPlanService.signprocurementPlanExport(procurPlanId);
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
