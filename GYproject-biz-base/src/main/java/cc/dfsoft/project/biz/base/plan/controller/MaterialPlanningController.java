package cc.dfsoft.project.biz.base.plan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.GoodsCompleteStatusEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;

/**
 * 采购部材料计划
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/materialPlanning")
public class MaterialPlanningController {
	
	/**日志实例*/
	private static Logger logger = LoggerFactory.getLogger(MaterialPlanningController.class);
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**部门service*/
	@Resource
	DepartmentService  departmentService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**合同服务接口*/
	@Resource
	ContractService contractService;
	
	/**计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService ;
	
	/**材料清单*/
	@Resource
	MaterialListService materialListService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017-2-18
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("plan/materialPlanning");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017-2-18
	 * @param 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("plan/materialPlanningRight");
		return modelview;
	}
	
	
	/** 
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author fuliwei
	 * @createTime 2017-2-17
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			if(StringUtils.isBlank(projectQueryReq.getGoodsCompleteStatus())){
				projectQueryReq.setGoodsCompleteStatus(GoodsCompleteStatusEnum.HAVE_NOT_FINISHIED.getValue());
			}
			//工程状态待施工、施工中、待自检、待预验收、待联合验收、待结算报审、报审确认、待结算初审、初审确认、待结算终审、终审确认、资料发送、资料反馈、资料确认
			String[] projStatus = {ProjStatusEnum.TO_CONSTRUCTION.getValue(),ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue(),ProjStatusEnum.TO_PRE_INSPECTION.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.SETTLEMENT_REPORT_START.getValue(),ProjStatusEnum.REPORT_DONE_CONFIRM.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue()};
			Map<String,Object> map = projectService.queryProjectView(projectQueryReq,projStatus);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author fuliwei
	 * @createTime 2017-2-18
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project viewProject(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		Contract contract = contractService.findByProjId(id);
		ConstructionPlan cp=constructionPlanService.viewPlanById(id);
		
		if(contract!=null && cp!=null && pro!=null){
			pro.setCuName(cp.getCuName());//分包单位
			pro.setCuPm(cp.getCuLegalRepresent());//项目经理
			pro.setBuilder(cp.getBuilder());
		}
		return pro;
	}
	
	/**
	 * 导出Excel文件
	 * @param response
	 * @param procurPlanId
	 */
	@RequestMapping(value = "/exportExcel")
	public void exprotExcel(HttpServletResponse response,String  projId){
		try {
			List<MaterialList> list=materialListService.queryMaterialListById(projId);
			String jsonString = JSON.toJSONString(list);
			JSONArray myJsonArray = JSONArray.parseArray(jsonString);
			String title = "材料明细";
			String[] excelHeader = {"材料名称","规格型号","单位","设计总量","领用总量","欠量"};
			String[] propertyNames = {"materialName","materialSpec","materialUnit","materialNum","getAnum","oweNum"};
			Integer[] headerWidth = {10000,2500,2500,2500,2500,2500};
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "MaterialDetail");
			map.put("remarkInfoHeight", "0");
			ExcelXlsxUtil.export(response, title, excelHeader, headerWidth, propertyNames, map, myJsonArray);

		} catch (Exception e) {
			logger.error("材料明细查询失败！", e);
		}
		
	}
	/**
	 * 弹屏搜索
	 * @author fuliwei
	 * @createTime 2017年2月19日
	 * @param 
	 * @return
	 */
	
	@RequestMapping(value="/constructSearchPopPage")
	public ModelAndView constructSearchPopPage() throws Exception{
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projStatus", ProjStatusEnum.getThanValue(ProjStatusEnum.TO_CONSTRUCTION.getValue(), ""));
		BusinessPartnersReq cubq=new BusinessPartnersReq();
		cubq.setUnitType(UnitTypeEnum.CONSTRUCTION_UNIT.getValue());
		Map<String, Object> map=businessPartnersService.queryBusinessPartners(cubq);
		List culist=(List) map.get("data");//
		modelView.addObject("culist",culist);	//分包单位
		List manageOfficeList =departmentService.queryManagementOffice();
		modelView.addObject("manageOfficeList",manageOfficeList);	//施工管理处
		modelView.addObject("goodsCompleteStatus",GoodsCompleteStatusEnum.values());	//发货状态
		modelView.setViewName("plan/materialPalnningSearchPopPage");
		return modelView;
	}
	
	
	/**
	 * 材料明细标记是否发货完毕
	 * @author fuliwei
	 * @createTime 2017年2月19日
	 * @param 
	 * @return
	 */
	
	@RequestMapping(value = "/signProject")
	@ResponseBody
	public String signProject(@RequestBody(required = true) String projId){
		try {
			projectService.signProject(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("材料明细标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	
	
	
}
