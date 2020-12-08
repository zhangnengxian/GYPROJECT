package cc.dfsoft.project.biz.base.constructmanage.controller;

import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.RelationShip;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.*;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.hibernate.StaleObjectStateException;
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
import java.util.List;
/**
 * 开工报告
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/startingReport")
public class StartingReportController {
	
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(StartingReportController.class);
	
	/**工程服务接口*/
   @Resource
   ProjectService projectService;

   /**开工报告服务接口*/
   @Resource
   WorkReportService workReportService;

   @Resource
   RelationShipService relationShipService;

   @Resource
   ContributionModeService contributionModeService;

   @Resource
	OperateRecordService operateRecordService;

	@Resource
	SignNoticeService signNoticeService;
   
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		List<RelationShip> relationShiplist=relationShipService.queryRelationShip();
		modelView.addObject("relationShip", relationShiplist);		//工程类型
		List<ContributionMode> contributionMode= contributionModeService.findById(null);
		modelView.addObject("contributionMode",contributionMode);//出资方式
		modelView.addObject("SUJGJ",PostTypeEnum.SUJGJ.getValue()); 					//现场监理师
		modelView.addObject("SUCSE",PostTypeEnum.SUCSE.getValue()); 					//总监
		
		modelView.addObject("BUILDER",PostTypeEnum.BUILDER.getValue()); 				//现场代表
		modelView.addObject("PROJECT_LEADER",PostTypeEnum.PROJECT_LEADER.getValue()); //负责人-组长
		modelView.addObject("viceGeneralManager",PostTypeEnum.VICE_GENERAL_MANAGER.getValue()); //安顺-增加 副总经理签字
		
		
		modelView.addObject("CU_PM",PostTypeEnum.CU_PM.getValue()); 					  //项目经理
		modelView.addObject("QUALITATIVE_CHECK_MEMBER",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue()); //质检员
		modelView.addObject("SAFTYOFFICER",PostTypeEnum.SAFTYOFFICER.getValue()); 							//安全员
		modelView.addObject("CONSTRUCTION",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("loginPost",loginInfo.getPost());	//登录人员
		modelView.addObject("dataAdmin",PostTypeEnum.DATAMANAGER.getValue());	//数据管理员

		//增加的签字页面
		String key = loginInfo.getCorpId()+"_"+Constants.STARTREPORT_MENUID+"_startingReport";//配置规则：公司ID+菜单ID+‘startingReport’;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj!=null){
			modelView.addObject("startingReportDiffPage",obj.toString());//不同的页面信息
		}
		modelView.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//部门划分:客服中心
		modelView.setViewName("constructmanage/startingReport");
		return modelView;
	}
	/**
	 * 保存开工报告
	 * @param workReport
	 * @return
	 */
	@RequestMapping(value = "/saveWorkReport")
	@ResponseBody
	public String saveWorkReport(HttpServletRequest request, @RequestBody(required=true) WorkReport workReport){
		try {

			if ("0".equals(workReport.getAuditResult())){//作废时
				workReportService.delBackupsWorkReport(workReport.getProjId(),workReport.getAbandonedReason());
				handleNotice(workReport,workReport.getAuditResult());//待办
				return  "{\"data\":\""+workReport.getWrId()+"\"}";

			}else {
				String s = workReportService.workReportSave(workReport);
				handleNotice(workReport,workReport.getAuditResult());//待办
				return s;
			}



		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("singtureType","error");
			logger.error("开工报告保存失败!",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	public  boolean handleNotice(WorkReport workReport,String flag) {
		operateRecordService.noticeSetInvalid(workReport.getProjId(), StepEnum.CONSTRUCTION.getValue(), "2");
		Project project = projectService.findById(workReport.getProjId());
		WorkReport wr = workReportService.findByProjId(workReport.getProjId());

		if (project==null) return false;

		String operaterId = "," + project.getManagementQaeId() + ",";
		String operater = project.getManagementQae();



		if ("0".equals(flag)) {//审核不通过时(重新生成代办)
			operateRecordService.nextDealtNotice(project, StepEnum.CONSTRUCTION.getValue(), StepEnum.CONSTRUCTION.getMessage(), operaterId, operater);

			//签字通知置为无效
			boolean b=signNoticeService.signNoticeSetInvalid(null,workReport.getProjId(), SignDataTypeEnum.STARTING_REPORT.getValue(),null,null);
		}


		if (wr != null && "1".equals(wr.getSignState())) {//试压待办通知
			operateRecordService.nextDealtNotice(project, StepEnum.DURING_CONSTRUCTION.getValue(), StepEnum.DURING_CONSTRUCTION.getMessage(), operaterId, operater);
		}
		return true;

	}
		/**
         * 详述
         * @param id
         * @return
         */
	@RequestMapping(value = "/startReportDetail")
	@ResponseBody
	public WorkReport startReportDetail(@RequestParam(required=true) String id){
		try {
			return workReportService.workReportDetail(id,null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveSignNotice")
	@ResponseBody
	public void saveSignNotice(@RequestBody(required = true) String cwId){
		try {
			workReportService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
	
	
	
}
