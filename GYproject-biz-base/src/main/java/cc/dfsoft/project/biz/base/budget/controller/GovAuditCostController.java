package cc.dfsoft.project.biz.base.budget.controller;
import java.util.List;
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

import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.project.biz.base.budget.enums.BudgetAdjustEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.enums.GovAuditCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.budget.service.GovAuditCostService;
import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
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
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;

/**
 * 
 * 描述:政府评价格登记
 * @author liaoyq
 * @createTime 2017年9月8日
 */
@Controller
@RequestMapping(value="/govAuditCost")
public class GovAuditCostController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(GovAuditCostController.class);
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
	
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	@Resource
	ProjectTypeService projectTypeService;
	
	@Resource
	GovAuditCostService govAuditCostService;
	
	/**
	 * 打开预算审定价登记主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("drawUrl",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); //简图上传路径
		modelView.addObject("gacType", GovAuditCostTypeEnum.BUDGET.getValue()); //预算审定价
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.setViewName("budget/govAuditCost");
		return modelView;
	}
	
	/**
	 * 打开结算审定价登记主页面
	 * @return
	 */
	@RequestMapping(value="/mains")
	public ModelAndView mains(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("drawUrl",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); //简图上传路径
		modelView.addObject("gacType", GovAuditCostTypeEnum.SETTLEMENT.getValue()); //结算审定价
		modelView.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginInfo",loginInfo);											//登录人信息
		modelView.setViewName("settlement/govAuditCost");
		return modelView;
	}
	/**
	 * 工程列表条件查询(待预算审定价登记)
	 * @param projectQueryReq
	 * @author liaoyq
	 * @createTime 2017-9-8
	 * @return
	 */
	@RequestMapping(value = "/queryProjectBgac")
	@ResponseBody
	public Map<String,Object> queryProjectBgac(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_BUDGET_GOV_AUDIT_COST.getValue()); //待预算审定价登记
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.BUDGET_GOV_AUDIT_COST.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 工程列表条件查询(待结算审定价登记)
	 * @param projectQueryReq
	 * @author liaoyq
	 * @createTime 2017-9-8
	 * @return
	 */
	@RequestMapping(value = "/queryProjectSgac")
	@ResponseBody
	public Map<String,Object> queryProjectSgac(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.SETTLEMENT_GOV_AUDIT_COST.getValue()); //待结算审定价登记
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.SETTLEMENT_GOV_AUDIT_COST.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 政府审定价登记
	 * @param budget
	 * @return
	 */
	@RequestMapping(value = "/updateGovAUditCost")
	@ResponseBody
	public String updateGovAUditCost(@RequestBody GovAuditCost govAuditCost){ 
		try {
			String gacId = govAuditCostService.updateGovAUditCost(govAuditCost);
			 return gacId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	
	/**
	 * 查询审定价详述 （含工程信息）
	 * @author liaoyq
	 * @createTime 2017-9-8
	 * @param id 工程ID
	 * @gacType gacType 审定价类型
	 */
	@RequestMapping(value = "/queryGovAuditCost")
	@ResponseBody
	public GovAuditCost queryByProjId(String id,String gacType){
		 try {
			 Project project = projectService.queryProjectById(id);
			 GovAuditCost govAuditCost = govAuditCostService.queryByProjIdAndType(id,gacType);
			 govAuditCost.setGacType(gacType);
			 if(project!=null){
				 govAuditCost.setProject(project);
			 }
			 return govAuditCost;
		} catch (Exception e) {
			logger.error("审定价详述查询失败！", e);
		}
		 return null;
	}
	/**
	 * 审定价推送
	 * @author liaoyq
	 * @createTime  2017-9-9
	 * @param id 审定价记录ID
	 * @return gacType 审定价类型
	 */
	@RequestMapping(value = "/pushGovAuditCost")
	@ResponseBody
	public String pushGovAuditCost(@RequestBody GovAuditCost govAuditCost){ 
		try {
			govAuditCostService.pushGovAuditCost(govAuditCost);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("审定价推送失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	
	
	/**
	 * 更新审定价记录(包含附件上传)
	 * @author liaoyq 
	 * @createTime 2017-8-15
	 * @param request
	 * @param uploadResult
	 * @return
	 */
	@RequestMapping(value = "/updateGovAUditCostFile")
	@ResponseBody
	public JSONObject updateGovAUditCostFile(HttpServletRequest request, UploadResult uploadResult, @RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			String gacId = govAuditCostService.updateBudgetFile(request,uploadResult,files);
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("operateId", gacId);
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			return json;
		} catch (Exception e) {
			logger.error("审定价信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}
	/**
	 * 更新工程审定价记录(不包含附件上传)
	 * @author liaoyq 
	 * @createTime 2017-8-15
	 * @param request
	 * @param uploadResult
	 * @return
	 */
	@RequestMapping(value = "/updateGovAUditCosts")
	@ResponseBody
	public String updateGovAUditCosts(HttpServletRequest request,@RequestBody(required=true) UploadResult uploadResult){
		try {
			return govAuditCostService.updateBudgetFile(request,uploadResult, null);
		} catch (Exception e) {
			logger.error("保存审定价记录失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * @author liaoyq 
	 * @createTime 2017-8-16
	 * 打开预算查询屏
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
	
	/**
	 * 打开结算审定价查询屏
	 * @return
	 */
	@RequestMapping(value="/settleSearchPopPage")
    public ModelAndView settleSearchPopPage(String state){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("state", state);
		modelView.setViewName("settlement/settleSearchPopPage");
		return modelView;  
    }
}
