package cc.dfsoft.project.biz.base.statisticquery.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.DocTypeQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 审核记录
 * @createTime 2017-1-1
 *
 */
@Controller
@RequestMapping(value="/queryManageRecord")
public class ManageRecordQueryController {
	
	/**审核单据接口*/
	@Resource
	DocTypeService docTypeService;
	@Resource
	ProjectTypeService projTypeService;

	@Resource
	ContributionModeService contributionModeService;
	@Resource
	ProjectService projectService;
	@Resource
	DepartmentService departmentService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	OperateRecordService operateRecordService;

	/**
	 * 打开主页面
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){

		LoginInfo loginInfo=SessionUtil.getLoginInfo();

		ModelAndView modelView=new ModelAndView();
		Map<String,String> deptInfo = projectService.getDeptInfoForStatistic();
		modelView.addObject("unitType", deptInfo.get("unitType"));
		modelView.addObject("deptId", deptInfo.get("deptId"));
		modelView.addObject("projStatus", ProjStatusEnum.values());	//工程状态
		DocTypeQueryReq docTypeQueryReq=new DocTypeQueryReq();
		List<DocType> docList=docTypeService.queryDocTypeDes(docTypeQueryReq);
		modelView.addObject("docList", docList);
		modelView.addObject("staffName",SessionUtil.getLoginInfo().getStaffName());
		modelView.addObject("projectType", projTypeService.queryAllList());//工程类型
		modelView.addObject("contributionMode",contributionModeService.queryAllList());	//出资方式
		modelView.addObject("corpId", loginInfo.getCorpId());//分公司ID
		modelView.addObject("departmentList",departmentService.queryListByDeptType(loginInfo));//子公司

		modelView.setViewName("project/statisticquery/queryManageRecord");
		return modelView;
	}


	@RequestMapping(value = "/queryManageRecord")
	@ResponseBody
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq req){
		try {
			return manageRecordService.queryManageRecord(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 *  @MethodDesc: 审核记录查询
	 * @Author zhangnx
	 * @Date 2019/09/18 18:21
	 */
	@RequestMapping(value = "/queryTodoer")
	@ResponseBody
	public String queryTodoer(String businessOrderId){
		try {
			String todoer=operateRecordService.queryTodoer(businessOrderId);
			return todoer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
