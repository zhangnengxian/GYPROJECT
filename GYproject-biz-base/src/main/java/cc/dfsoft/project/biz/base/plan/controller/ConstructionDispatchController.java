package cc.dfsoft.project.biz.base.plan.controller;

import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.service.BusinessCommunicationService;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;

import com.fr.stable.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 施工派遣
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/constructionDispatch")
public class ConstructionDispatchController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ConstructionDispatchController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	ContractService contractService;

	@Resource
	ConstructionPlanService constructionPlanService ;
	
	@Resource
	ManageRecordService manageRecordService;

	@Resource
	OperateRecordService operateRecordService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("plan/constructionDispatch");
		return modelView;
	}
	
	/**
	 * 打开右侧施工派遣页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPlanningPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("unitType", UnitTypeEnum.CONSTRUCTION_UNIT.getValue());			//施工单位
		modelview.addObject("managementQae", PostTypeEnum.CONSTRUCTION.getValue());			//施工员
		modelview.addObject("managementWacf", PostTypeEnum.MANAGEMENTWACF.getValue());			//材料员
		modelview.addObject("documenter", PostTypeEnum.BUSINESSASSISTANT.getValue());			//资料员
		modelview.addObject("saftyOfficer", PostTypeEnum.SAFTYOFFICER.getValue());				//安全员
		modelview.addObject("technician", PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());	//质检员
		modelview.addObject("cuPm", PostTypeEnum.CU_PM.getValue());								//项目经理
		modelview.addObject("testLeader", PostTypeEnum.TEST_LEADER.getValue());				//班组长
		modelview.addObject("welder", PostTypeEnum.WELDER.getValue());							//焊工
		modelview.setViewName("plan/constructionDispatchRight");
		return modelview;
	}

	/**
	 * 弹出搜索屏
	 * @author cui
	 * @createTime 2017-11-29
	 * @param
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/cuDispatchSearchPopPage")
	public ModelAndView cuDispatchSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("cuIsDispatch", IsDispatchEnum.values());
		modelview.setViewName("plan/cuDispatchSearchPopPage");
		return modelview;
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
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.TO_SELF_CHECK.getValue());
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}
			//System.out.println(request.getParameter("isPrint"));
			if(StringUtil.isBlank(req.getProjNo())){//根据工程编号查询时，不加状态
				req.setProjStuList(projStuList);
			}
			req.setSortInfo(request);
			Map<String,Object> map=constructionPlanService.queryConstructionPlan(req);
			return map;
		} catch (Exception e) {
			logger.error("计划列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 监理任务单,施工派遣任务单列表条件查询,计划审核-已经竣工
	 * @author wmy
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryConstructionPlan2")
	@ResponseBody
	public Map<String,Object> queryConstructionPlan2(HttpServletRequest request,ConstructionPlanQueryReq req){
		try {
			List<String> projStuList = new ArrayList<String>();
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue());
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}
			req.setProjStuList(projStuList);
			req.setSortInfo(request);
			/*if(null!=request.getParameter("subIsPrint")){
				req.setSubIsPrint(request.getParameter("subIsPrint"));
			}*/
			Map<String,Object> map=constructionPlanService.queryConstructionPlan(req);
			return map;
		} catch (Exception e) {
			logger.error("计划列表查询失败！", e);
			return null;
		}
	}

	/**
	 * 详述
	 * @author cui
	 * @createTime 2017-3-21
	 * @param id 工程id
	 * @return ConstructionPlan
	 */
	@RequestMapping(value="/viewPlan")
	@ResponseBody
	public ConstructionPlan viewPlan(@RequestParam(required=true) String id){
		try {
			return constructionPlanService.detail(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 弹出搜索屏
	 * @author zhangmeng
	 * @createTime 2016-7-04
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("plan/projectSearchPopPage");
		return modelview;
	}

	/**
	 * 保存施工派遣
	 * @author cui
	 * @createTime 2017-3-21
	 * @param  
	 * @return 
	 */

	@Resource
	BusinessCommunicationService businessCommunicationService;
	@RequestMapping(value = "/saveConstructionDispatch")
	@ResponseBody
	public String saveConstructionDispatch(@RequestBody(required = true) ConstructionPlan constructionPlan){
		try {
			constructionPlanService.saveConstructionDispatch(constructionPlan);
			String savePlan = projectService.saveProjectPlanInfo(constructionPlan.getProjId()); //保存信息到工程表

			if ("110507".equals(constructionPlan.getMenuId())){//施工派遣
				handleNotice(constructionPlan.getProjId());//会审交底待办
			}
			return Constants.OPERATE_RESULT_SUCCESS;

		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch (Exception e) {
			logger.error("计划派遣区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	public  void handleNotice(String projId) {
		Project project = projectService.findById(projId);
		if (project!=null) {//生成会审交底待办通知
			String operaterId=","+project.getBuilderId()+",";
			String operater=project.getBudiler()+",";

			if (StringUtils.isNotBlank(project.getManagementQaeId())){
				operaterId+=project.getManagementQaeId()+",";
			}
			if (StringUtils.isNotBlank(project.getManagementQae())){
				operater+=project.getManagementQae()+",";
			}
			operateRecordService.nextDealtNotice(project, StepEnum.TECHNOLOGYTELL.getValue(), StepEnum.TECHNOLOGYTELL.getMessage(),operaterId,operater);
		}
	}


		@RequestMapping(value = "/dispatch")
	public ModelAndView dispatch() {
		ModelAndView modelview = new ModelAndView();		
		modelview.addObject("constructList",constructionPlanService.countProjectNum("c.management_office"));
		modelview.addObject("cuList",constructionPlanService.countProjectNum("c.cu_name"));
		modelview.setViewName("plan/planDispatch");
		return modelview;
	}
	
}
