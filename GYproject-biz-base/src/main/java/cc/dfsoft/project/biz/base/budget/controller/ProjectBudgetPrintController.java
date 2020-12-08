package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.dto.SubBudgetReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.service.BudgetService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.enums.SubBudgetIsPrintEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 建设工程预算书打印
 * @author wangang
 * 
 */

@Controller
@RequestMapping(value="/projectBudgetPrint")
public class ProjectBudgetPrintController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectBudgetPrintController.class);
	
	/**施工预算服务接口*/
	@Resource
	SubBudgetService subBudgetService;
	@Resource
	ProjectService projService;
	@Resource
	BudgetService budgetService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("alreadyPrint", SubBudgetIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelAndView.addObject("haveNotPrint", SubBudgetIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelAndView.setViewName("budget/projectBudgetPrint");
		return modelAndView;
	}
	
	/**
	 * 加载搜索屏
	 * @return
	 */
	@RequestMapping(value="projectBudgetSearchPopPage")
	public ModelAndView viewBudgetPrintRightPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("budget/projectBudgetSearchPop");
		return modelAndView;
	}
	/**
	 * 查询建设工程预算书列表
	 * 
	 */
	@RequestMapping(value="queryBudget")
	@ResponseBody
	public Map<String,Object> queryBudget(HttpServletRequest request,BudgetReq budgetReq){
		try {
			budgetReq.setSortInfo(request);
			Map<String,Object> map=budgetService.queryBudget(budgetReq);
			return map;
		} catch (Exception e) {
			logger.error("施工预算信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 修改打印标记
	 */
	@RequestMapping(value="signProjectBudgetPrint")
	@ResponseBody
	public String signProjectBudgetPrint(@RequestBody(required = true) String budgetId){
		try {
			return budgetService.signProjectBudgetPrint(budgetId);
		} catch (Exception e) {
			logger.error("打印施工预算标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询配置的施工预算书打印模板
	 * 不存在版本问题 ， key值规则：出资方式_分公司ID_菜单ID_出资方式
	 * @author liaoyq
	 * @createTime 2018年8月22日
	 * @param
	 * @return
	 */
	@RequestMapping(value="viewBudgetReport",method = RequestMethod.POST)
	@ResponseBody
	public String viewBudgetReport(String projId, String menuId){
		String cptUrl = "";
		try {
			//获取工程信息
			Project project = projService.queryProjectById(projId);
			if(project==null){
				return cptUrl;
			}
			//组装key值得到信息
			String key = project.getContributionMode() + "_" + project.getCorpId() + "_" + menuId;
			Object obj = Constants.getSysConfigByKey(key);
			if(obj == null){
				return cptUrl;
			}
			return obj.toString();
		} catch (Exception e) {
			logger.error("施工预算书报表打印版本查询失败！", e);
			e.printStackTrace();
			return cptUrl;
		}
		
	}
}
