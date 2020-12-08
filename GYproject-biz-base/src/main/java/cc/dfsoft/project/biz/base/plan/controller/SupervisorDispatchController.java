package cc.dfsoft.project.biz.base.plan.controller;

import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ConstructionPlanQueryReq;
import cc.dfsoft.project.biz.base.plan.enums.IsDispatchEnum;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
 * 监理派遣
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/supervisorDispatch")
public class SupervisorDispatchController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SupervisorDispatchController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	ContractService contractService;

	@Resource
	ConstructionPlanService constructionPlanService ;
	
	@Resource
	ManageRecordService manageRecordService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("plan/supervisorDispatch");
		return modelView;
	}
	
	/**
	 * 打开右侧监理派遣页面
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPlanningPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("unitType", UnitTypeEnum.SUPERVISION_UNIT.getValue());				// 监理单位
		modelview.addObject("suCse", PostTypeEnum.SUCSE.getValue());							// 总监
		modelview.addObject("suJuj", PostTypeEnum.SUJGJ.getValue());							// 现场监理
		modelview.addObject("suProfessional", PostTypeEnum.PROFESSIONAL_SUPERVISION.getValue());// 专业监理师
		modelview.addObject("suRepresentative", PostTypeEnum.SUCSE_REPRESENTATIVE.getValue());	// 总监代表
		modelview.setViewName("plan/supervisorDispatchRight");
		return modelview;
	}

	/**
	 * 弹出搜索屏
	 * @author cui
	 * @createTime 2017-11-29
	 * @param
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/suDispatchSearchPopPage")
	public ModelAndView suDispatchSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("suIsDispatch", IsDispatchEnum.values());
		modelview.setViewName("plan/suDispatchSearchPopPage");
		return modelview;
	}

	/**
	 * 工程计划列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryConstructionPlan")
	@ResponseBody
	public Map<String,Object> queryConstructionPlan(HttpServletRequest request,ConstructionPlanQueryReq req){
		try {
			List<String> projStuList = new ArrayList<String>();
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.UNION_PRE_INSPECTION.getValue());
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}
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
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年6月19日
	 * @param 
	 * @return
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
}
