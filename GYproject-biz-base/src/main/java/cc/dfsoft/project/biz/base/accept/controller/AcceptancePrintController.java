
package cc.dfsoft.project.biz.base.accept.controller;


import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 工程立项
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/acceptancePrintController")
public class AcceptancePrintController {

	@Resource
	ProjectService projectService;

	@Resource
	ProjectTypeService projectTypeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_PROJECTAPPROVAL);
		modelView.addObject("stepId",StepEnum.PROJECT_ACCEPT_AUDIT.getValue());
		modelView.setViewName("accept/acceptancePrint");
		return modelView;
	}


	@RequestMapping(value = "/getProjectList")
	@ResponseBody
	public Map<String,Object> getProjectList(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			return projectService.queryAcceptProject(projectQueryReq);
		} catch (Exception e) {
			return null;
		}
	}


	@RequestMapping(value = "/projectPrintSearchPopPage")
	public ModelAndView projectPrintSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		List<ProjectType> projectTypeList=projectTypeService.queryAllList();
		modelview.addObject("projectTypeList", projectTypeList);
		modelview.setViewName("accept/acceptancePrintSearchPopPage");
		return modelview;
	}


	/**
	  * @methodDesc: 查询cpt
	  * @author: zhangnx
	  * @date 11:16 2018/9/28
	  */
	@RequestMapping(value = "/queryCptUrl")
	@ResponseBody
	public String queryCptUrl(String projId,String menuId){
		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		Project pro=projectService.queryProjectById(projId);

		StringBuilder ctm=new StringBuilder(pro!=null?pro.getCorpId():loginInfo.getCorpId()).append("_").append(pro!=null?pro.getProjectType(): ProjLtypeEnum.PUBLIC.getValue()).append("_").append(menuId);
		StringBuilder cm=new StringBuilder(pro!=null?pro.getCorpId():loginInfo.getCorpId()).append("_").append(menuId);

		String cptURL="acceptSurveyReport.cpt";
		return queryCPT(ctm)!=null?queryCPT(ctm).toString():queryCPT(cm)!=null?queryCPT(cm).toString():cptURL;
	}

	public Object queryCPT(StringBuilder key){
		return Constants.getSysConfigByKey(key.toString());
	}




}
	  
	
	
