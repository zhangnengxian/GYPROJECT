package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.enums.SubQuantitiesStatusEnum;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.project.biz.base.subpackage.enums.CostTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.service.PricedBoqService;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.project.biz.base.subpackage.service.SubQuantitiesService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 施工预算
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/qualitiesDeclaration")
public class QualitiesDeclarationController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(QualitiesDeclarationController.class);
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	/** 工程量标准服务接口 */
	@Resource
	PricedBoqService quantityStandardService;
	/** 分包工程量服务接口*/
	@Resource
	SubQuantitiesService subQuantitiesService;
	/** 施工预算接口*/
	@Resource
	SubBudgetService subBudgetService;
	@Resource
	AccessoryService accessoryService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_SUBCONTRACT);
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("costType",CostTypeEnum.values());
		modelView.addObject("sysDate",subBudgetService.getDatabaseDate());//系统时间
		modelView.addObject("stepId",StepEnum.QUALITIES_JUDGEMENT.getValue());
		modelView.addObject("stepIds",StepEnum.QUALITIES_JUDGEMENT.getValue()+Constants.SPLIT_CODE+StepEnum.QUALITIES_JUDGEMENT_FIRST.getValue());//审核历史stepId组合
		modelView.addObject("curStepId",StepEnum.QUALITIES_DECLARATION.getValue());
		modelView.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());
		modelView.setViewName("subcontract/qualitiesDeclaration");
		return modelView;
	}
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelview.addObject("loginInfo",loginInfo);	//登录人信息
		modelview.setViewName("subcontract/qualitiesDeclarationRight");
		return modelview;
	}
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("subcontract/quantitySearchPopPage");
		return modelview;
	}
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author zhangjj
	 * @createTime 2016-06-21
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue());
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.QUALITIES_DECLARATION.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 详述
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewCost")
	@ResponseBody
	public SubBudget viewContract(String id,String stepId){
		SubBudget result = new SubBudget();
		try {
			result = subBudgetService.viewSubBudget(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setUploadFlag(accessoryService.isUploadFile(id,stepId,null,AccessorySourceEnum.COLLECT_FILE.getValue())?"1":"0");
		return result;
	}
	/**
	 * 工程量标准查询弹框
	 * @return
	 */
	@RequestMapping(value="/standardPop/{projId}")
	public ModelAndView securityTopPage(@PathVariable String projId){
		ModelAndView modelView=new ModelAndView();
	    Project project = projectService.viewProject(projId);   //根据工程ID,获取实体
	    //查询工程量版本
	    List<DataFilerSetUpDto> filterVersion = Constants.getDataFilterMapByKey(project.getCorpId()+"_"+project.getProjectType()+"_"+"110601"); //查询工程量版本
	    if(filterVersion != null && filterVersion.size() > 0){
	    	modelView.addObject("version",filterVersion); 
	    }
	    //查询增收方式
	    List<DataFilerSetUpDto> incIncraMode = Constants.getDataFilterMapByKey(project.getCorpId()+"_"+project.getProjectType()+"_"+"0"); //查询工程量版本
	    if(incIncraMode != null && incIncraMode.size() > 0){
	    	modelView.addObject("incIncraMode",incIncraMode);  //工程量版本
	    }
		modelView.setViewName("subcontract/quantityStandardPop");
		return modelView;
	}
	/**
	 * 工程量标准查询
	 * @author zhangjj
	 * @createTime 2016-07-12
	 * @return
	 * update wanghuijun
	 * 修改为按照公司ID、工程类型、增收方式进行查找
	 * 修改时间2019-01-22
	 */
	@RequestMapping(value = "/queryQuantityStand/{projId}/{versionOfProj}/{incIncraMode}")
	@ResponseBody
	public List<Map<String,Object>> queryQuantStandTree(@PathVariable String projId,@PathVariable String versionOfProj,@PathVariable String incIncraMode){
		try {
			return quantityStandardService.queryQuantStandTree(projId,versionOfProj,incIncraMode);
		} catch (Exception e) {
			logger.error("工程量标准查询失败！", e);
			return null;
		}
	}
	/**
	 * 分步分项名称查询
	 * @author pengtt
	 * @createTime 2016-08-30
	 * @param subQuantitiesReq
	 * @return
	 */
	@RequestMapping(value = "/queryQuantityStandard")
	@ResponseBody
	public Map<String, Object> queryQuantityStandard(SubQuantitiesReq subQuantitiesReq){
		try {
			subQuantitiesReq.setSqStatus(SubQuantitiesStatusEnum.BUDGET.getValue());
			return subQuantitiesService.queryQuantityStandardNoPage(subQuantitiesReq);
		} catch (Exception e) {
			logger.error("分步分项名称查询失败！", e);
			return new HashMap<String,Object>();
		}
	}
	/**
	 * 分包工程量导出Excel文件
	 * @author fuliwei
	 * @createTime 2016-12-31
	 * @param String  projId
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exprotExcel(HttpServletResponse response,String  projId,String sbId){
		try {
			//查找分包工程量
			List<SubQuantities> list=subQuantitiesService.exprotExcel(projId, sbId);
			String jsonString = JSON.toJSONString(list);
			JSONArray myJsonArray = JSONArray.parseArray(jsonString);
			String title = "工程量清单";
			String[] excelHeader = {"分步分项工程名称","单位","劳务单价","数量","分项金额"};
			String[] propertyNames = {"sqBranchProjName","sqUnit","sqLabourPrice","sqNum","sqAmount"};
			Integer[] headerWidth = {9000,2500,2500,2500,2500};
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "Sub-Quantities-Detail");
			map.put("remarkInfoHeight", "0");
			ExcelXlsxUtil.export(response, title, excelHeader, headerWidth, propertyNames, map, myJsonArray);

		} catch (Exception e) {
			logger.error("工程量清单查询失败！", e);
		}
	}
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project viewPlan(@RequestParam(required=true) String id){
		try {
			return  subQuantitiesService.findProjectByProjId(id);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 保存施工预算
	 * @param subBudget
	 * @return
	 */
	@RequestMapping(value = "/saveSubBudget")
	@ResponseBody
	public String saveSubBudget(HttpServletRequest request,@RequestBody SubBudget subBudget){
		try {
			if(subBudget.getFlag().equals("1") && !accessoryService.isUploadFile(subBudget.getProjId(), subBudget.getStepId(),null,AccessorySourceEnum.COLLECT_FILE.getValue())){
				return "no";
			}
			return subBudgetService.saveSubBudget(subBudget);
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			e.printStackTrace();
			logger.error("施工预算保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
}