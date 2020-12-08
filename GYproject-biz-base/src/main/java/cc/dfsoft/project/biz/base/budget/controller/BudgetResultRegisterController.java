package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/budgetResultRegister")
public class BudgetResultRegisterController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(BudgetResultRegisterController.class);
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	/** 预算服务接口 */
	@Resource
	BudgetService budgetService;
	/**材料清单服务接口 */
	@Resource
	MaterialListService materialListService;
	
	@Resource
	ProjectDao projectDao;
	
	/**审核记录*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**设计信息*/
	@Resource
	DesignInfoService designInfoService;
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	ProjectTypeService projectTypeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		//modelView.addObject("civilType", BudgetCostTypeEnum.getObjByType(ProjLtypeEnum.CIVILIAN.getValue()));
		modelView.addObject("costType", BudgetCostTypeEnum.values());
/*		modelView.addObject("civilVal", ProjLtypeEnum.CIVILIAN.getValue());
		modelView.addObject("publicVal", ProjLtypeEnum.PUBLIC.getValue());*/
		/*modelView.setViewName("budget/budgetResultRegister");*/
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.addObject("notThrough",Constants.MODULE_CODE_BUDGET);
		modelView.addObject("stepId",StepEnum.BUDGET_AUDIT.getValue());
		modelView.addObject("curStepId",StepEnum.BUDGET_RESULT_REGISTER.getValue());
		modelView.setViewName("budget/budgetTotalResultRegister");
		return modelView;
	}
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author zhangjj
	 * @createTime 2016-07-06
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			//工程状态：待预算结果登记
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_BUDGET_RESULT_REGISTRATION.getValue());
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.BUDGET_RESULT_REGISTER.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开预算总表页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String corpId,String menuId) {
		String key = corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "budgetTotalSumPage";
		}
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("postBudgeter", PostTypeEnum.BUDGET_MEMBER.getValue());
		modelview.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); //简图上传路径
		modelview.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);	 //签字上传路径
//		modelview.setViewName("budget/budgetTotalSumPage");
		modelview.setViewName("budget/"+viewUrl);
		return modelview;
	}
	/**
	 * 打开预算成本
	 * 
	 * 已作废
	 * @return
	 */
//	@RequestMapping(value = "/viewCostPage")
//	public ModelAndView viewCostPage() {
//		ModelAndView modelview = new ModelAndView();
//		modelview.setViewName("budget/budgetCostPage");
//		return modelview;
//	}
	
	
	/**
	 * 打开文件导入页面
	 * 已作废
	 * @return
	 */
//	@RequestMapping(value = "/importPop")
//	public ModelAndView exportPop(String url) {
//		ModelAndView modelview = new ModelAndView();
//		modelview.addObject("url", url);
//		modelview.setViewName("budget/importPop");
//		return modelview;
//	}
	
	/**
	 * 文件上传
	 * 已作废
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
//	@RequestMapping(value = "/importExcel")
//	@ResponseBody
//	public JSONObject ImportExcel(HttpServletRequest request, BudgetReq req,@RequestParam(value = "files[]") MultipartFile files[]) {
//		try {
//			String isAdjust=BudgetAdjustEnum.NOT_ADJUST.getValue();
//			req.setIsAdjust(isAdjust);
//			// ["序号","费用名称","取费说明","费率","金额"];
//			String[] params = { "pcsNum", "costName", "costDes", "rate","amount"};
//			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "单位工程费用表",10, 4, 1, params);			 			 
//			if (null != jsonarr && jsonarr.size() > 0) {
//			budgetService.batInsertCostSum(jsonarr,req);
//			}
//
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		String url = request.getRequestURL().toString();
//		int i = url.lastIndexOf("/");
//		url = url.substring(0, i + 1);
//		JSONArray js = new JSONArray();
//		JSONObject jso = new JSONObject();
//		JSONObject json = new JSONObject();
//		jso.put("name", files[0].getOriginalFilename());
//		jso.put("size", files[0].getSize());
//		jso.put("type", "xlsx/xls");
//		jso.put("url", files[0].getOriginalFilename());
//		jso.put("thumbnailUrl", files[0].getOriginalFilename());
//		jso.put("deleteUrl", url + "deleteImport?fileName="+ files[0].getOriginalFilename());
//		jso.put("deleteType", "DELETE");
//		js.add(jso);
//		json.put("files", js);
//		logger.info("返回前1===="+System.currentTimeMillis());
//		return json;
//	}
	
	/**
	 * 单位工程费用汇总表查询
	 * @param projId 工程id
	 * @param costType 费用类型
	 * @author zhangjj
	 * @createTime 2016-07-07
	 * @return已作废
	 */
//	@RequestMapping(value = "/queryCostSummary")
//	@ResponseBody
//	public Map<String,Object> queryProject(HttpServletRequest request,ProjCostSummaryReq projCostSummaryReq){
//		try {
//			projCostSummaryReq.setSortInfo(request);
//			return budgetService.queryCostSummary(projCostSummaryReq);
//		} catch (Exception e) {
//			logger.error("单位工程费用汇总表查询失败！", e);
//			return null;
//		}
//	}
//	@RequestMapping(value = "/queryBudge")
//	@ResponseBody
//	public Budget queryBudgeByprojId(String id){
//	 try {
//		 Budget budget= budgetService.queryById(id,BudgetAdjustEnum.NOT_ADJUST.getValue());
//		 if(null==budget){
//			 budget=new Budget();
//		 }
//		 return budget;
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	 return null;
//	}
//	@RequestMapping(value = "/updateBudge")
//	@ResponseBody
//	public String updateBudge(@RequestBody Budget budget){ 
//		try {
//			 budgetService.updateBudge(budget);
//			 return Constants.OPERATE_RESULT_SUCCESS;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return Constants.OPERATE_RESULT_FAILURE;
//	}
	
//	@RequestMapping(value = "/queryRateById")
//	@ResponseBody
//	public ProjectRate queryRateById(String id){
//	 try {
//		 id="1";
//		 ProjectRate projectRate=budgetService.queryRateById(id);
//		 if(null==projectRate){
//			 projectRate=new ProjectRate();
//		 }
//		 return projectRate;
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	 return null;
//	}
	
//	@RequestMapping(value = "/updateMaterialList")
//	@ResponseBody
//	public String  updateMaterialList(@RequestBody List<MaterialList> list){ 
//		try {
//			materialListService.updateMaterialList(list);
//			 return Constants.OPERATE_RESULT_SUCCESS;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return Constants.OPERATE_RESULT_FAILURE;
//	}
	
	
	/**
	 * 总表导入
	 * @param request
	 * @param response
	 * @param fileName
	 * @return 已作废
	 */
//	@RequestMapping(value = "/importTotalExcel")
//	@ResponseBody
//	public JSONObject importTotalExcel(HttpServletRequest request, BudgetReq req,@RequestParam(value = "files[]") MultipartFile files[]) {
//		try {
//			String isAdjust=BudgetAdjustEnum.NOT_ADJUST.getValue();
//			req.setIsAdjust(isAdjust);
//			// ["序号","分项名称","金额"];
//			String[] params = { "serialNo", "subitemName", "amount"};
//			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "工程项目总价表",7, 3, 1, params);			 			 
//			if (null != jsonarr && jsonarr.size() > 0) {
//			budgetService.batInsertTotalCostSum(jsonarr,req);
//			}
//
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		String url = request.getRequestURL().toString();
//		int i = url.lastIndexOf("/");
//		url = url.substring(0, i + 1);
//		JSONArray js = new JSONArray();
//		JSONObject jso = new JSONObject();
//		JSONObject json = new JSONObject();
//		jso.put("name", files[0].getOriginalFilename());
//		jso.put("size", files[0].getSize());
//		jso.put("type", "xlsx/xls");
//		jso.put("url", files[0].getOriginalFilename());
//		jso.put("thumbnailUrl", files[0].getOriginalFilename());
//		jso.put("deleteUrl", url + "deleteImport?fileName="+ files[0].getOriginalFilename());
//		jso.put("deleteType", "DELETE");
//		js.add(jso);
//		json.put("files", js);
//		logger.info("返回前1===="+System.currentTimeMillis());
//		return json;
//	}
	
	/**
	 * 工程项目总价表
	 * @param projId 工程id
	 * @param costType 费用类型
	 * @return已作废
	 */
//	@RequestMapping(value = "/queryBudgetTotalTable")
//	@ResponseBody
//	public Map<String,Object> queryBudgetTotalTable(HttpServletRequest request,BudgetTotalQueryReq budgetTotalTableReq){
//		try {
//			budgetTotalTableReq.setSortInfo(request);
//			//正常预算总表
//			budgetTotalTableReq.setBudgetType(BudgetTypeEnum.NOT_ADJUST.getValue());
//			return budgetService.queryBudgetTotal(budgetTotalTableReq);
//		} catch (Exception e) {
//			logger.error("工程项目总价表查询失败！", e);
//			return null;
//		}
//	}
	
	/**
	 * 查询工程预算详述
	 * @author
	 * @createTime 2016-11-18 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryPro")
	@ResponseBody
	public Budget queryByProjId(String id){
		 try {
			 Budget budget = budgetService.queryBudgeByprojId(id);
			 /*Project pro=projectDao.get(id);
			 Budget budget= budgetService.queryById(id,BudgetAdjustEnum.NOT_ADJUST.getValue());
			 if(budget!=null && pro!=null){
				 pro.setBudgetTotalCost(budget.getBudgetTotalCost());
				 pro.setInspectionCost(budget.getInspectionCost());
				 pro.setSuCost(budget.getSuCost());
				 pro.setStoreCost(budget.getStoreCost());
				 pro.setRemark(budget.getNote());
			 }*/
			 return budget;
		} catch (Exception e) {
			logger.error("工程预算详述查询失败！", e);
		}
		 return null;
	}
	/**
	 * 预算确认
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateBudget")
	@ResponseBody
	public String updateBudget(@RequestBody Budget budget){ 
		try {
			 budgetService.updateBudget(budget);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	
	
	/**
	 * 查询设计信息
	 * @author fuliwei
	 * @createTime 2017-1-7
	 * @param  String projId
	 * @return DesignInfo 
	 */
	@RequestMapping(value = "/queryDesignInfo")
	@ResponseBody
	public DesignInfo signContractPrint(@RequestBody(required = true) String projId){
		try {
			 DesignInfo designInfo =designInfoService.queryById(projId);
			 return designInfo;
		} catch (Exception e) {
			logger.error("查询设计信息失败！", e);
			return null;
		}
		
	}
	
	/**
	 *预算结果登记查询审核记录
	 * @param projId
	 * @return  String
	 */
	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public String queryManageRecord(@RequestBody(required = true) String projId){
		try {
			DocType docType = docTypeService.findByStepId(StepEnum.DRAWING_SIGN_AUDIT.getValue());
			String mrResult=MrResultEnum.PASSED.getValue();
			
			return budgetService.queryManageRecord(projId, docType == null?"":docType.getStepId(), mrResult,"2");
		} catch (Exception e) {
			logger.error("查询设计信息失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
	/**
	 * 更新工程预算记录(包含附件上传)
	 * @author liaoyq 
	 * @createTime 2017-8-15
	 * @param request
	 * @param uploadResult
	 * @return
	 */
	@RequestMapping(value = "/updateBudgetFile")
	@ResponseBody
	public JSONObject updateBudgetFile(HttpServletRequest request, UploadResult uploadResult, @RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			budgetService.updateBudgetFile(request,uploadResult,files);
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("operateId", uploadResult.getOperateId());
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			return json;
		} catch (Exception e) {
			logger.error("预算信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}
	/**
	 * 更新工程预算记录(不包含附件上传)
	 * @author liaoyq 
	 * @createTime 2017-8-15
	 * @param request
	 * @param uploadResult
	 * @return
	 */
	@RequestMapping(value = "/updateBudgets")
	@ResponseBody
	public String updateBudgets(HttpServletRequest request,@RequestBody(required=true) UploadResult uploadResult){
		try {
			return budgetService.updateBudgetFile(request,uploadResult, null);
		} catch (Exception e) {
			logger.error("保存预算记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * @author liaoyq 
	 * @createTime 2017-8-16
	 * 打开查询屏
	 * @return
	 */
	@RequestMapping(value="/projectSearchPopPage")
	public ModelAndView projectSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		List<ProjectType> projType=projectTypeService.queryAllList();
		modelAndView.addObject("projLtype", projType);
		modelAndView.setViewName("budget/budgetResultRegisterSearchPopPage");
		return modelAndView;
	}
}
