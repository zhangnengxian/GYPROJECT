package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 工程统计查询
 * @author pengtt
 * @createTime 2016-08-19
 */
@Controller
@RequestMapping(value="/statisticquery")
public class StatisticqueryController {

	@Resource
	ProjectTypeService projTypeService;
	@Resource
	ContributionModeService contributionModeService;
	@Resource
	ProjectService projectService;
	@Resource
	DepartmentService departmentService;

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		
		ModelAndView modelView=new ModelAndView();
		//工程状态
		modelView.addObject("projStatus", ProjStatusEnum.values());
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.addObject("projectType", projTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());	//出资方式
		Map<String,String> deptInfo = projectService.getDeptInfoForStatistic();
		modelView.addObject("unitType", deptInfo.get("unitType"));
		modelView.addObject("deptId", deptInfo.get("deptId"));
		modelView.addObject("loginDeptId", loginInfo.getDeptId());
		modelView.addObject("corpId", loginInfo.getCorpId());
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//分公司
		String key = deptInfo.get("deptId") +"_"+"1402";   //拼接key值查找报表
		Object obj = Constants.getSysConfigByKey(key);
		String cptType = "";
		if(obj != null){
			cptType = obj.toString();
		}else{
			cptType = "queryProject.cpt";
		}
		modelView.addObject("cptType", cptType);  //cpt类型
		modelView.setViewName("project/statisticquery/projectQueryReport");
		return modelView;
	}

}
