package cc.dfsoft.project.biz.base.subpackage.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 施工任务单打印
 * @author cui by 2017-9-6
 *
 */
@Controller
@RequestMapping(value="/constructionTaskPrint")
public class ConstructionTaskPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ConstructionTaskPrintController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	/**计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService ;
	/**报表版本配置*/
	@Resource
	ReportVersionService reportVersionService;

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginName", loginInfo.getStaffName());//登录人
		modelView.addObject("loginPhone", loginInfo.getMobile());//登录人电话
		try {
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			modelView.addObject("contractVersion",reportVersionService.queryReportVersions(reportVersionReq));
		} catch (ParseException e) {
			logger.error("查询版本失败！", e);
		}
		modelView.setViewName("subcontract/constructionTaskPrint");
		return modelView;
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("plan/constructionTaskSearchPopPage");
		return modelview;
	}
	/**
	 * 合同打印标记
	 * @author cui
	 * @createTime 2017-2-14
	 * @param  projId
	 * @return String
	 */
	@RequestMapping(value = "/signConstructionTaskPrint")
	@ResponseBody
	public String signConstructionTaskPrint(@RequestBody(required = true) String projId){
		try {
			constructionPlanService.signConstructionTaskPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 监理任务单打印标记
	 * @author cui
	 * @createTime 2017-2-14
	 * @param  projId
	 * @return String
	 */
	@RequestMapping(value = "/signSupervisorTaskPrint")
	@ResponseBody
	public String sigSupervisorTaskPrint(@RequestBody(required = true) String projId){
		try {
			constructionPlanService.signSupervisorTaskPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 计划列表条件查询
	 * @author zhangmeng
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryConstructionPlan")
	@ResponseBody
	public Map<String,Object> queryConstructionPlan(HttpServletRequest request,ConstructionPlanQueryReq req){
		try {
			List<String> projStuList = new ArrayList<String>();
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),null);
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}
			req.setProjStuList(projStuList);
			req.setSortInfo(request);
			Map<String,Object> map=constructionPlanService.queryConstructionPlan(req);
			return map;
		} catch (Exception e) {
			logger.error("计划列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 不同的单位查询不同的cpt
	 * 如果没有工程ID表示没有选中行，默认加载该菜单下，当前用户单位ID下的配置cpt
	 * @param projId 工程ID
	 * @param rvId 版本ID  key值规则：工程分公司ID_菜单ID_版本ID
	 * @return 数据库配置的cpt
	 * @author liaoyq
	 * @createTime 2018-8-29
	 */
	@RequestMapping(value = "/viewReportUrl")
	@ResponseBody
	public String viewReportUrl(String projId, String menuId,String signDate,String rvId){
		String cptUrl = "";
		//获取工程信息
		Project project = projectService.queryProjectById(projId);
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String corpId = loginInfo.getCorpId();
		if(project!=null){
			corpId = project.getCorpId();
		}
		if(StringUtil.isBlank(rvId)){
			//获取版本信息
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			//reportVersionReq.setProjType(project.getProjectType());--不需要工程类型
			reportVersionReq.setMenuId(menuId);
			reportVersionReq.setCorpId(corpId);
			reportVersionReq.setSignDate(StringUtil.isNotBlank(signDate)?signDate:"");
			
			//查询该版本日期之前的最近一次版本信息
			List<ReportVersion> versions = new ArrayList<ReportVersion>();
			try {
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if(versions!=null && versions.size()>0){
					rvId = versions.get(0).getRvId();
				}
			} catch (ParseException e) {
				logger.error("施工任务单报表打印版本查询失败！", e);
				e.printStackTrace();
			}
		}
		//组装key值得到信息
		String key = corpId + "_" + menuId + "_" + rvId;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj == null){
			return cptUrl;
		}
		return obj.toString();
	}
}
