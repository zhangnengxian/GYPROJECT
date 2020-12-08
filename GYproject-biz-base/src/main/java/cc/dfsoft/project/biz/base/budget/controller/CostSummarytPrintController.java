package cc.dfsoft.project.biz.base.budget.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.contract.controller.ContractAuditController;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
/**
 * 造价汇总打印
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/costSummarytPrint")
public class CostSummarytPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("budget/costSummaryPrint");
		return modelView;
	}
	/**
	 * 工程列表条件查询
	 * @param request
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			//工程状态：待预算结果登记
			//projectQueryReq.setProjStatusId(ProjStatusEnum.TO_BUDGET_RESULT_REGISTRATION.getValue());
			projectQueryReq.setSortInfo(request);
			return projectService.queryProjectcostSummary(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/queryProjectPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("budget/projectSearchPopPage");
		return modelview;
	}
	
	
}
