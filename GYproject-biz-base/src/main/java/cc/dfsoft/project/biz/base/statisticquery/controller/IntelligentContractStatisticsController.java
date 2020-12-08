package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 
 * 描述:智能表合同统计
 * @author liaoyq
 * @createTime 2017年10月30日
 */
@Controller
@RequestMapping(value="/intelligentContractStatistics")
public class IntelligentContractStatisticsController {

	@Resource
	DepartmentService departmentService;

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){

		LoginInfo loginInfo=SessionUtil.getLoginInfo();

		ModelAndView modelView=new ModelAndView();
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//子公司

		modelView.setViewName("project/statisticquery/intelligentContractStatistics");
		return modelView;
	}

}
