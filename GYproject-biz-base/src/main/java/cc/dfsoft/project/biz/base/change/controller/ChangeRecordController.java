package cc.dfsoft.project.biz.base.change.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import org.apache.commons.lang3.StringUtils;
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

import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.change.enums.ChangeStateEnum;
import cc.dfsoft.project.biz.base.change.enums.MCTypeEnum;
import cc.dfsoft.project.biz.base.change.service.MaterialChangeService;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.constructmanage.enums.AuditStateEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ChangeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.EngineeringTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StageProjectApplicationEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.EngineeringVisaService;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.ExcelXlsxUtil;
/**
 * 
 * @author zhangjj
 *
 */
@Controller
@RequestMapping(value="/changeRecord")
public class ChangeRecordController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ChangeRecordController.class);
	
	@Resource
	ChangeManagementService changeManagementService;
	@Resource
	EngineeringVisaService engineeringVisaService;
	@Resource
	MaterialChangeService materialChangeService;
	@Resource
	MaterialListService materialListService;
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	/** 预算服务接口 */
	@Resource
	BudgetService budgetService;
	
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("costType", BudgetCostTypeEnum.values());
		modelView.addObject("stepId",StepOutWorkflowEnum.ENGINEERING_AUDIT.getValue());	
		//modelView.addObject("curStepId",StepEnum.SURVEY_RESULT_REGISTER.getValue());
		//modelView.setViewName("changerecord/changeRecord"); 因导入总表 将原来代码屏蔽
		modelView.setViewName("changerecord/alterationAdjust");
		return modelView;
		
	}
	@RequestMapping(value="/suppCont")
	public ModelAndView suppCont(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("changerecord/supplementalContractRight");
		return modelView;
		
	}
	@RequestMapping(value="/alterationInfo")
	public ModelAndView alterationInfo(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("auditState", AuditStateEnum.TO_AUDIT.getValue());//审核状态---待审
		modelView.addObject("cmState", ChangeStateEnum.NO_HANDLE.getValue());
		modelView.addObject("changeType", ChangeTypeEnum.values());//变更类型
		modelView.addObject("cuPm", PostTypeEnum.DEPUTY_DIRECTOR.getValue());//副处长
		modelView.addObject("suPrincipal", PostTypeEnum.SUJGJ.getValue());//现场监理师
		modelView.addObject("designer", PostTypeEnum.DESIGNER.getValue());//设计员
		modelView.addObject("auditer", PostTypeEnum.DIRECTOR.getValue());//处长
		modelView.addObject("approvaler", PostTypeEnum.CHIEF_ENGINEER.getValue());//总工
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//img url
		modelView.addObject("evType", EngineeringTypeEnum.values());//签证类型
		modelView.addObject("accessorySource", AccessorySourceEnum.COLLECT_FILE.getValue());//附件来源类型
		modelView.setViewName("changerecord/alterationInfo");
		return modelView;
		
	}
	@RequestMapping(value="/queryChangeData")
	public ModelAndView queryChangeData(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("cmState", ChangeStateEnum.values());
		modelView.setViewName("changerecord/changeSelPop");
		return modelView;
		
	}
	
	@RequestMapping(value="/queryVisaData")
	public ModelAndView queryVisaData(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("cmState", ChangeStateEnum.values());
		modelView.addObject("auditState", StageProjectApplicationEnum.values());
		modelView.setViewName("changerecord/visaSelPop2");
		return modelView;
		
	}
	
	
	/**
	 * 变更列表条件查询
	 * @author fuliwei
	 * @createTime 2017年11月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDesignAlteration")
	@ResponseBody
	public Map<String,Object> queryDesignAlteration(HttpServletRequest request,ChangeManagementQueryReq changeManagementQueryReq){
		try {
			changeManagementQueryReq.setSortInfo(request);
			//List<String> auditState = new ArrayList<String>();
			//auditState.add(AuditStateEnum.PUSHED.getValue());
			//changeManagementQueryReq.setAuditState(auditState);
			//默认查询待变更签证调整的
			if(StringUtils.isBlank(changeManagementQueryReq.getDesignChangeType())){
				changeManagementQueryReq.setDesignChangeType(DesignChangeStateEnum.WAIT_BUDGET_CHANGE.getValue());
			}
			return changeManagementService.queryChangeManagement(changeManagementQueryReq);
		} catch (Exception e) {
			logger.error("变更信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 签证列表条件查询
	 * @author zhangjj
	 * @createTime 2016-08-08
	 * @param engineeringVisaQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryEngineeringVisa")
	@ResponseBody
	public Map<String,Object> queryEngineeringVisa(HttpServletRequest request,EngineeringVisaQueryReq engineeringVisaQueryReq){
		try {
			engineeringVisaQueryReq.setSortInfo(request);
			List<String> auditState= new ArrayList<String>();
			if(StringUtils.isBlank(engineeringVisaQueryReq.getAuditStatus())){
				auditState.add(StageProjectApplicationEnum.TO_BUDGET.getValue());
			}else{
				auditState.add(engineeringVisaQueryReq.getAuditStatus());
			}
			engineeringVisaQueryReq.setAuditState(auditState);
			/*if(engineeringVisaQueryReq.getEvState()!=null && engineeringVisaQueryReq.getEvState().size()>0){
				List auditState= new ArrayList<>();
				auditState.add(StageProjectApplicationEnum.TO_BUDGET.getValue());
				engineeringVisaQueryReq.setAuditState(auditState);
			}else{
				List auditState= new ArrayList<>();
				auditState.add(StageProjectApplicationEnum.TO_BUDGET.getValue());
				engineeringVisaQueryReq.setAuditState(auditState);
				
				List evState= new ArrayList<>();
				evState.add(ChangeStateEnum.NO_HANDLE.getValue());
				engineeringVisaQueryReq.setEvState(evState);
			}*/
			
			
			/*List evState= new ArrayList<>();
			evState.add(ChangeStateEnum.HANDLED.getValue());//已处理，即已推送审核
			evState.add(ChangeStateEnum.NO_HANDLE.getValue());
			engineeringVisaQueryReq.setEvState(evState);*/
			return engineeringVisaService.queryEngineeringVisa(engineeringVisaQueryReq);
		} catch (Exception e) {
			logger.error("签证信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 材料导入页面 保存
	 * @author
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveMaterialChange")
	@ResponseBody
	public String saveMaterialChange(@RequestBody List<MaterialChange> list){
		try {
			materialChangeService.saveMaterialChange(list);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 材料变更列表条件查询
	 * @author zhangjj
	 * @createTime 2016-08-08
	 * @param MaterialListQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryMaterialList")
	@ResponseBody
	public Map<String,Object> queryMaterialList(HttpServletRequest request,MaterialListQueryReq req){
		try {
			req.setSortInfo(request);
			return materialListService.queryMaterialList(req);
		} catch (Exception e) {
			logger.error("签证信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 材料变更列表条件查询
	 * @author zhangjj
	 * @createTime 2016-08-10
	 * @param MaterialListQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryMCList")
	@ResponseBody
	public Map<String,Object> queryMCList(HttpServletRequest request,MaterialChangeReq req){
		try {
			req.setSortInfo(request);
			return materialChangeService.queryMaterialChange(req);
		} catch (Exception e) {
			logger.error("材料变更列表条件查询查询失败！", e);
			return null;
		}
	}
	@RequestMapping(value="/materialList")
	public ModelAndView materialList(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("changerecord/materialList");
		return modelView;
		
	}
	/**
	 * 协议详述----------------已作废
	 * @author zhangjj
	 * @createTime 2016-8-08
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewContract")
	@ResponseBody
	public Object viewContract(@RequestParam(required=true) String cmId,@RequestParam(required=true) String mcType,@RequestParam(required=true) String projId){
		//Project pro=projectService.viewProject(projId);
		try {
			Object obj=materialChangeService.getSupplement(cmId,mcType,projId);
		    if(obj!=null){
		    	return obj;
		    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    return new Contract();
	
		
	}
	
	/**
	 * 根据工程id查询预算总表
	 * @author zhangjj
	 * @createTime 2016-8-8
	 * @param projId
	 * @return BudgetAdjust
	 */
	@RequestMapping(value = "/queryBudge")
	@ResponseBody
	public Budget queryBudgeByprojId(@RequestParam(required=true)String projId,@RequestParam(required=true)String cmId,@RequestParam(required=true)String mcType){
		try {
			Budget budget= budgetService.queryByType(projId,cmId,mcType);
			if(null!=budget){
				return budget;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Budget();
	}
	/**
	 * 打开预算总表页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("changerecord/budgetAdjustSum");
		return modelview;
	}
	/**
	 * 更新预算总表
	 * @param budget
	 * @return
	 */
	@RequestMapping(value = "/updateBudge")
	@ResponseBody
	public String updateBudge(@RequestBody Budget budget){ 
		try {
			 budget.setIsUpdatePro("false");//不更新工程状态
			 budgetService.updateBudgeAdjust(budget);
			 return Constants.OPERATE_RESULT_SUCCESS;
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	/**
	 * 更新变更状态---点击确认
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/updateChangeState")
	@ResponseBody
	public ResultMessage updateChangeState(HttpServletRequest request,@RequestBody Budget budget){ 
		String type=budget.getMcType();
		String id=budget.getCmId();
		budget.setIsUpdatePro("false");
		ResultMessage resultMessage = new ResultMessage();
		try {
			 if(type.equals(MCTypeEnum.MATERIAL_VISA.getValue())){
				 //签证预算调整确认，更新签证状态
				 String res = engineeringVisaService.visaConfirm(budget);
				 resultMessage.setRet_type(Constants.SUCCESS_CODE);
				 resultMessage.setRet_message(res);
				 return resultMessage;
			 }else{
				 changeManagementService.updateChangeState(id);
				 resultMessage.setRet_type(Constants.SUCCESS_CODE);
				 resultMessage.setRet_message(Constants.OPERATE_RESULT_SUCCESS);
				 return resultMessage;
			 }
		 }catch (ExpressException e) {
			e.printStackTrace();
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message(Constants.OPERATE_RESULT_FAILURE);
			return resultMessage;
		}
	}
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
	@RequestMapping(value = "/importExcel")
	@ResponseBody
	public JSONObject ImportExcel(HttpServletRequest request,MaterialChangeReq req,@RequestParam(value = "files[]") MultipartFile files[]) {
		try {
			// ["序号","材料汇总表","材料","单位","数量","备注","是否由物资公司购买"];
			String[] params = { "dmNo","dmName","dmSpec","dmUnit", "dmNum","remark","flag"};
			JSONArray jsonarr= ExcelXlsxUtil.importExcelJson(files[0], "材料目录", 0, 3, 0, params);	
			 List<MaterialChange>  lm = new ArrayList<MaterialChange>();
			 if (null != jsonarr && jsonarr.size() > 0) {
				 //officialDrawingService.batInsertCostSum(jsonarr,projId);
				//lm = materialListService.getMaterialList(jsonarr, projId); flw
				 materialChangeService.batInsertMaterialChange(jsonarr,req);
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
			//json.put("materialList", lm);

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/exportExcel")
	public void exportExcel(HttpServletResponse response){
		try {
			String title = "材料汇总表";
			String[] excelHeader = {"序号  NO.","MATERIAL_NAME 材料名称","MATERIAL SPEC 材料规格","单位 UNIT","THE NUMBER OF 数量","备注  NOTE","是否由物资购买 FLAF(0-不是)"};
			Integer[] headerWidth = {2500,10000,10000,10000,10000};
			Map<String,String> map = new HashMap<String,String>();
			map.put("fileName", "Material");
			map.put("remarkInfoHeight", "0");
			String sheetName="材料目录";
			ExcelXlsxUtil.exportExcel(response, title,sheetName, excelHeader, headerWidth, null, map, null);
		} catch (Exception e) {
			logger.error("模板导出失败",e);
		}
	}
	
	/**
	 * 推送到签订补充协议
	 * @author fuliwei
	 * @createTime 2017年11月12日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateChange")
	@ResponseBody
	public String updateChangeState(@RequestBody(required = true)String cmId){
		try {
			ChangeManagement cm=changeManagementService.viewChangeManagement(cmId);
			String type="";
			if(cm!=null){
				if(ChangeTypeEnum.USER_CHANGE.getValue().equals(cm.getChangeType())){
					//用户变更-待签补充协议
					type=DesignChangeStateEnum.WAIT_SUPPLEMENT_CONTRACT.getValue();
				}else{
					//施工变更-已完成
					type=DesignChangeStateEnum.ALREADY_FINISHED.getValue();
				}
			}else{
				return Constants.OPERATE_RESULT_FAILURE;
			}
			changeManagementService.updateChangeState(cmId,type);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("推送设计变更失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 签证作废
	 * @author fuliwei
	 * @createTime 2018年3月8日
	 * @param 
	 * @return
	 */
	@RequestMapping("/deleteEV")
	@ResponseBody
	public String deleteEV(HttpServletRequest request,@RequestBody Budget budget){
		try{
			engineeringVisaService.updateEngineeringVisaState(budget);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("签证记录作废失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * @Des 预算员弹出框
	 * @Author zhangnx
	 * @Date 2019年12月9日
	 */
	@RequestMapping(value = "/budgetPop")
	public ModelAndView budgetPop(ModelAndView model,@RequestParam String  projId) {
		model.setViewName("maintain/reassignment");
		return model;
	}

	/**
	* @Description: 预算员派遣
	* @author zhangnx
	* @param projId 工程ID
	* @param staffId 人员ID
	* @date 2019/12/10 10:33
	**/
	@RequestMapping(value = "/reassignment")
	@ResponseBody
	public String reassignment(String projId,String staffId) {

		return projectService.changeBudgeter(projId,staffId);

	}


	
}
