package cc.dfsoft.project.biz.base.settlement.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 监检报告
 * @author fulw
 *
 */
@Controller
@RequestMapping(value="/monitoringReport")
public class MonitoringReportController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(MonitoringReportController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**结算报审服务接口*/
	@Resource
	SettlementAuditService settlementAuditStartService;
	
	@Resource
	AccessoryService accessoryService;
	
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("stepId", StepEnum.MONITORING_REPORT.getValue());
		modelView.setViewName("settlement/monitoringReport");
		return modelView;
	}
	
	/**
	 * 列表查询
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_CONSTRUCTION_PROC.getValue());
			projectQueryReq.setStepId(StepEnum.CONSTRUCTION_PROC.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("settlement/monitoringReportProcRight");
		return modelview;
		
	}
	
	/**
	 * 打开查询弹框
	 * @author fuliwei  
	 * @date 2018年11月8日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/projectSearchDonePopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("settlement/projectSearchDonePopPage");
		return modelview;
	}
	
	/**
	 * 监检报告登记保存
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/updateState")
	@ResponseBody
	public String updateState(@RequestBody(required = true) SubBudget subBudget){ 
		try {
			if(!accessoryService.isUploadFile(subBudget.getProjId(), subBudget.getStepId(),null,AccessorySourceEnum.COLLECT_FILE.getValue())){
				return "no";
			}
			settlementAuditStartService.saveMonitoringReport(subBudget);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("监检报告保存数据失败！");
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
	
}
