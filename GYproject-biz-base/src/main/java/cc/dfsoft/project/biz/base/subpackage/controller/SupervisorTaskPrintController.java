package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.baseinfo.service.SupervisionUnitService;
import cc.dfsoft.project.biz.base.contract.enums.ContractSuIsPrintEnum;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 监理任务单打印
 * @author cui by 2017-11-3
 *
 */
@Controller
@RequestMapping(value="/supervisorTaskPrint")
public class SupervisorTaskPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SupervisorTaskPrintController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectDao projectDao;

	@Resource
	SupervisionUnitService supService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractSuIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractSuIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginName", loginInfo.getStaffName());//登录人
		modelView.addObject("loginPhone", loginInfo.getMobile());//登录人电话
		modelView.addObject("loginPost", SessionUtil.getLoginInfo().getPost());
		modelView.addObject("dataAdmin", PostTypeEnum.DATAMANAGER.getValue());
		modelView.setViewName("subcontract/supervisorTaskPrint");
		return modelView;
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("suIsPrint", ContractSuIsPrintEnum.values());//是否打印
		modelview.setViewName("plan/supervisorTaskSearchPopPage");
		return modelview;
	}


	/**
	 * @MethodDes: 详细
	 * @author zhangnx
	 * @date 2019/8/13 13:01
	 */
	@RequestMapping(value = "/viewDetail")
	@ResponseBody
	public Project viewDetail(@RequestParam(required=true) String id){
		Project project = projectDao.get(id);
		return project;
	}


	@RequestMapping(value = "/modifyInfo")
	@ResponseBody
	public boolean modifyInfo(@RequestBody(required = true) ConstructionPlan cplan){
		try {
			supService.modifyInfo(cplan);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}




}
