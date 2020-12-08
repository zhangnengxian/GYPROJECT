package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowRecordReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.inspection.controller.AltimetricSurveyController;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.RelationShipService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 工程流程管理
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/workFlow")
public class WorkFlowController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AltimetricSurveyController.class);
	
	@Resource
	WorkFlowService workFlowService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	RelationShipService relationShipService;
	
	@Resource
	ContributionModeService contributionModeService;
	
	@Resource
	ProjectTypeService projectTypeService;
	
	@Resource
	CorrelationService correlationService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));
		modelView.addObject("projType",projectTypeService.queryAllList());
		
		modelView.addObject("step", StepEnum.values());
		//查询所有的状态
		modelView.addObject("allType", correlationService.queryAssistWorkFlow());
		modelView.addObject("typeId", WorkFlowTypeEnum.values());				//流程类型
		modelView.addObject("assistTypeId", AssistProgressTypeEnum.values());	//辅助流程
		modelView.setViewName("baseinfo/workFlow/workFlow");
		return modelView;
		
	}
	/**
	 * 查询工程流程列表
	 * @param request
	 * @param pipeWeldingReq
	 * @return
	 */
	@RequestMapping(value = "/queryWorkFlowList")
	@ResponseBody
	public Map<String,Object> queryWorkFlowList(HttpServletRequest request,WorkFlowReq workFlowReq){
		try {
			workFlowReq.setSortInfo(request);
			
			if(StringUtils.isBlank(workFlowReq.getCorpId())){
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				workFlowReq.setCorpId(loginInfo.getCorpId());
			}
			Map<String,Object> map= workFlowService.queryWorkFlowList(workFlowReq);
			return map;
		} catch (Exception e) {
			logger.error("流程列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询工程流程步骤列表
	 * @param request
	 * @param workFlowReq
	 * @return
	 */
	@RequestMapping(value = "/queryWorkFlowRecord")
	@ResponseBody
	public Map<String,Object> queryWorkFlowRecord(HttpServletRequest request,WorkFlowReq workFlowReq){
		try {
			workFlowReq.setSortInfo(request);
			Map<String,Object> map= workFlowService.queryWorkFlowRecord(workFlowReq);
			return map;
		} catch (Exception e) {
			logger.error("流程步骤列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 流程批量保存（未保存流程编码）
	 * @param workFlows
	 * @return
	 */
	@RequestMapping(value = "/saveWorkFlows")
	@ResponseBody
	public String saveWorkFlows(@RequestBody List<WorkFlow> workFlows){ 
		try {
			return workFlowService.saveWorkFlows(workFlows);
		} catch (Exception e) {
			logger.error("流程批量保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 流程批量删除
	 * @param workFlows
	 * @return
	 */
	@RequestMapping(value = "/deleWorkFlows")
	@ResponseBody
	public String deleWorkFlows(@RequestBody List<WorkFlow> workFlows){ 
		try {
			return workFlowService.deleWorkFlows(workFlows);
		} catch (Exception e) {
			logger.error("流程批量删除失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 流程更新（保存流程编码）
	 * @param workFlowRecordReq
	 * @return
	 */
	@RequestMapping(value = "/updateWorkFlowCode")
	@ResponseBody
	public String updateWorkFlowCode(@RequestBody WorkFlowRecordReq workFlowRecordReq){ 
		try {
			return workFlowService.updateWorkFlowCode(workFlowRecordReq);
		} catch (Exception e) {
			logger.error("保存流程编码失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 联查出资类型选框
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/querySubcompany")
	public List<Correlation> querySubcompany(String id){
		try{
			CorrelationReq req = new CorrelationReq();
			req.setCorType(CorrelationTypeEnum.PROJTYPE_CONTRIBUTION.getValue());
			req.setCorrelateInfoId(id);
			return correlationService.findCorType(req);
		}catch(Exception e){
			logger.error("联查出资类型失败！",e);
			return null;
		}
	}
	
	
}
