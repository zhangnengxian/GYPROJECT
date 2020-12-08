package cc.dfsoft.project.biz.base.budget.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.BudgetAdjust;
import cc.dfsoft.project.biz.base.budget.enums.BudgetAdjustEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetAdjustService;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
/**
 * 预算调整
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/budgetAdjust")
public class BudgetAdjustController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(BudgetAdjustController.class);
	
	/**变更记录服务接口*/
	@Resource 
	ChangeManagementService changeManagementService;
	
	/** 预算服务接口 */
	@Resource
	BudgetService budgetService;
	
	/** 预算调整服务接口*/
	@Resource
	BudgetAdjustService budgetAdjustService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("civilType", BudgetCostTypeEnum.getObjByType(ProjLtypeEnum.CIVILIAN.getValue()));
		modelView.addObject("publicType", BudgetCostTypeEnum.getObjByType(ProjLtypeEnum.PUBLIC.getValue()));
		modelView.addObject("civilVal", ProjLtypeEnum.CIVILIAN.getValue());
		modelView.addObject("publicVal", ProjLtypeEnum.PUBLIC.getValue());
		modelView.setViewName("budget/budgetAdjust");
		return modelView;
	}
	
	/**
	 * 打开预算总表页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("budget/budgetAdjustSum");
		return modelview;
	}
	
	/**
	 * 弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("design/changeManageSearchPopPage");
		return modelview;
	}
	
	/**
	 * 变更记录列表查询
	 * @author fuliwei
	 * @createTime 2016-07-12
	 * @param changeManagementQueryReq
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryChangeManagement")
	@ResponseBody
	public Map<String,Object> queryChangeManagement(ChangeManagementQueryReq changeManagementQueryReq){
		try {
			return changeManagementService.queryChangeManagement(changeManagementQueryReq);
		} catch (Exception e) {
			logger.error("变更记录列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 打开文件导入页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/importPop")
	public ModelAndView exportPop(String url) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("url", url);
		modelview.setViewName("budget/importPop");
		return modelview;
	}
	
	/**
	 * 文件上传-分项导入
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public JSONObject ImportExcel(HttpServletRequest request, BudgetReq req,@RequestParam(value = "files[]") MultipartFile files[]) {
		try {
			String isAdjust=BudgetAdjustEnum.ADJUSTED.getValue();
			req.setIsAdjust(isAdjust);
			// ["序号","费用名称","取费说明","费率","金额"];
			String[] params = { "pcsNum", "costName", "costDes", "rate","amount"};
			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "单位工程费用表",10, 4, 1, params);			 			 
			if (null != jsonarr && jsonarr.size() > 0) {
				budgetAdjustService.batInsertCostSum(jsonarr,req);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = request.getRequestURL().toString();
		int i = url.lastIndexOf("/");
		url = url.substring(0, i + 1);
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		jso.put("name", files[0].getOriginalFilename());
		jso.put("size", files[0].getSize());
		jso.put("type", "xlsx/xls");
		jso.put("url", files[0].getOriginalFilename());
		jso.put("thumbnailUrl", files[0].getOriginalFilename());
		jso.put("deleteUrl", url + "deleteImport?fileName="+ files[0].getOriginalFilename());
		jso.put("deleteType", "DELETE");
		js.add(jso);
		json.put("files", js);

		return json;
	}
	
	/**
	 * 文件上传-总表导入
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "/ImportTotalExcel")
	@ResponseBody
	public JSONObject ImportTotalExcel(HttpServletRequest request, BudgetReq req,@RequestParam(value = "files[]") MultipartFile files[]) {
		try {
			String isAdjust=BudgetAdjustEnum.ADJUSTED.getValue();
			req.setIsAdjust(isAdjust);
			// ["序号","费用名称","取费说明","费率","金额"];
			String[] params = { "serialNo", "subitemName", "amount"};
			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "工程项目总价表",7, 3, 1, params);					 			 
			if (null != jsonarr && jsonarr.size() > 0) {
				budgetAdjustService.batInsertTotalCostSum(jsonarr,req);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = request.getRequestURL().toString();
		int i = url.lastIndexOf("/");
		url = url.substring(0, i + 1);
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		jso.put("name", files[0].getOriginalFilename());
		jso.put("size", files[0].getSize());
		jso.put("type", "xlsx/xls");
		jso.put("url", files[0].getOriginalFilename());
		jso.put("thumbnailUrl", files[0].getOriginalFilename());
		jso.put("deleteUrl", url + "deleteImport?fileName="+ files[0].getOriginalFilename());
		jso.put("deleteType", "DELETE");
		js.add(jso);
		json.put("files", js);

		return json;
	}
	
	/**
	 * 工程项目总价表
	 * @param projId 工程id
	 * @param costType 费用类型
	 * @return
	 */
	@RequestMapping(value = "/queryBudgetTotalTable")
	@ResponseBody
	public Map<String,Object> queryBudgetTotalTable(HttpServletRequest request,BudgetTotalQueryReq budgetTotalTableReq){
		try {
			budgetTotalTableReq.setSortInfo(request);
			//调整预算
			budgetTotalTableReq.setBudgetType(BudgetTypeEnum.ADJUSTED.getValue());
			return budgetService.queryBudgetTotal(budgetTotalTableReq);
		} catch (Exception e) {
			logger.error("工程项目总价表查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 根据工程id查询预算总表
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param projId
	 * @return BudgetAdjust
	 */
	@RequestMapping(value = "/queryBudge")
	@ResponseBody
	public Budget queryBudgeByprojId(@RequestParam(required=true)String id){
		try {
			return budgetService.queryById(id,BudgetAdjustEnum.ADJUSTED.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 单位工程费用汇总表查询
	 * @param projId 工程id
	 * @param costType 费用类型
	 * @author 
	 * @createTime 2016-07-13
	 * @return
	 */
	@RequestMapping(value = "/queryCostSummary")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjCostSummaryReq projCostSummaryReq){
		try {
			projCostSummaryReq.setSortInfo(request);
			projCostSummaryReq.setIsAdjust(BudgetAdjustEnum.ADJUSTED.getValue());
			return budgetService.queryCostSummary(projCostSummaryReq);
		} catch (Exception e) {
			logger.error("单位工程费用汇总表查询失败！", e);
			return null;
		}
	}

	/**
	 * 更新预算表
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param budget
	 * @return String
	 */
	@RequestMapping(value = "/updateBudge")
	@ResponseBody
	public String updateBudge(@RequestBody Budget budget){ 
		try {
			budgetService.updateBudgeAdjust(budget);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("更新预算总表失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
