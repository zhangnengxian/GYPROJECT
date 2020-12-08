package cc.dfsoft.project.biz.base.common.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;


@Controller
@RequestMapping(value="/completionDataScanning")
public class CompletionDataScanningController {
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	ConstructionPlanService constructionPlanService;
	
	@Resource
	ProjectChecklistService  projectCheckListService;
	
	@Resource
	ContractService contractService;
	
	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView modelview = new ModelAndView();
		String projId=request.getParameter("projId");
		Project project =projectDao.get(projId);
		ConstructionPlan plan=constructionPlanService.viewPlanById(projId);
		String pcId=request.getParameter("pcId");
		Contract contract=contractService.findByProjId(projId);
		ProjectChecklist checkList=projectCheckListService.viewProjectCheckList(projId, pcId);
		modelview.addObject("pro", project);
		modelview.addObject("plan", plan);
		modelview.addObject("checkList", checkList);
		modelview.addObject("contract", contract);
		modelview.setViewName("common/completionDataScanning");
		return modelview;
	}
}
