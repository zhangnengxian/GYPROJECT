package cc.dfsoft.project.biz.base.budget.controller;

import cc.dfsoft.project.biz.base.budget.dto.SubBudgetReq;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;
import cc.dfsoft.project.biz.base.subpackage.enums.SubBudgetIsPrintEnum;
import cc.dfsoft.project.biz.base.subpackage.service.SubBudgetService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 预算书打印
 * @author liaoyq
 * 
 */

@Controller
@RequestMapping(value="/budgetPrint")
public class BudgetPrintController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(BudgetPrintController.class);
	
	/**施工预算服务接口*/
	@Resource
	SubBudgetService subBudgetService;
	@Resource
	ProjectService projService;
	@Resource
	ReportVersionService reportVersionService;
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
		modelAndView.setViewName("budget/budgetPrint");
		return modelAndView;
	}
	
	/**
	 * 加载搜索屏
	 * @return
	 */
	@RequestMapping(value="budgetSearchPopPage")
	public ModelAndView viewBudgetPrintRightPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isPrint", SubBudgetIsPrintEnum.values());//是否打印
		modelAndView.setViewName("budget/budgetSearchPop");
		return modelAndView;
	}
	/**
	 * 查询预算书列表
	 * 
	 */
	@RequestMapping(value="querySubBudget")
	@ResponseBody
	public Map<String,Object> querySubBudget(HttpServletRequest request,SubBudgetReq subBudgetReq){
		try {
			subBudgetReq.setSortInfo(request);
			Map<String,Object> map=subBudgetService.querySubBudgetPrint(subBudgetReq);
			return map;
		} catch (Exception e) {
			logger.error("施工预算信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 修改打印标记
	 */
	@RequestMapping(value="signSubBudgetPrint")
	@ResponseBody
	public String signBudgetPrint(@RequestBody(required = true) String sbId){
		try {
			return subBudgetService.signBudgetPrint(sbId);
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
	 * @param sbId
	 * @return
	 */
	@RequestMapping(value="viewSubBudgetReport",method = RequestMethod.POST)
	@ResponseBody
	public String viewSubBudgetReport(String projId, String menuId,String rvId){
		String cptUrl = "";
		try {
			//获取工程信息
			Project project = projService.queryProjectById(projId);
			if(project==null){return cptUrl;}

			if (StringUtils.isBlank(rvId)) {
				//查询施工预算报送日期
				SubBudget subBudget = subBudgetService.findSubBudgetByProjID(projId);
				//获取版本信息
				ReportVersionReq reportVersionReq = new ReportVersionReq();
				//出资方式
				reportVersionReq.setProjType(project.getContributionMode());
				reportVersionReq.setMenuId(menuId);
				reportVersionReq.setCorpId(project.getCorpId());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				reportVersionReq.setSignDate((subBudget != null && subBudget.getOperateDate() != null) ? format.format(subBudget.getOperateDate()) : "");

				//查询该版本日期之前的最近一次版本信息
				List<ReportVersion> versions = new ArrayList<ReportVersion>();
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if (versions != null && versions.size() > 0) {
					rvId = versions.get(0).getRvId();
				}
			}

			//组装key值得到信息:g根据出资方式和公司ID
			String key = project.getContributionMode() + "_" + project.getCorpId() + "_" + menuId;
			if(StringUtil.isNotBlank(rvId)){
				key = key + "_"+rvId;
			}
			Object obj = Constants.getSysConfigByKey(key);
			if(obj != null){
				return obj.toString();
			}
			return cptUrl;
		} catch (Exception e) {
			logger.error("施工预算书报表打印版本查询失败！", e);
			e.printStackTrace();
			return cptUrl;
		}
		
	}
}
