package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
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
 * @createTime 2017年10月31日
 */
@Controller
@RequestMapping(value="/intelligentCashFlowStatistics")
public class IntelligentCashFlowStatisticsController {
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
		//工程状态
		modelView.addObject("projStatus", ProjStatusEnum.values());
		modelView.addObject("arFlagEnum", ARFlagEnum.values());//收付款类型
		modelView.addObject("staffName",loginInfo.getStaffName());
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//子公司

		modelView.setViewName("project/statisticquery/intelligentCashFlowStatistics");
		return modelView;
	}
	

}
