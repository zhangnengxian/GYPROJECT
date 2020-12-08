package cc.dfsoft.project.biz.base.complete.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.project.biz.base.complete.service.CompletionTransferService;
import cc.dfsoft.project.biz.base.complete.service.FilingDataService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 资料发放
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/completionTransfer")
public class CompletionTransferController {

		/**工程服务接口*/
		@Resource
		ProjectService projectService;
		
		/**资料流转单服务接口*/
		@Resource
		CompletionTransferService completionTransferService;
		
		/**归档资料服务接口*/
		@Resource
		FilingDataService filingDataService;
		
		/**管理记录服务接口*/
		@Resource
		ManageRecordService manageRecordService;
		
		/** 日志实例 */
		private static Logger logger = LoggerFactory.getLogger(CompletionTransferController.class);
		
		/**
		 * 打开主页面
		 * @return
		 */
		@RequestMapping(value="/main")
		public ModelAndView main(){
			ModelAndView modelView=new ModelAndView();
			modelView.setViewName("complete/completionTransfer");
			return modelView;
		}
		
		/**
		 * 打开右侧页面
		 * @return
		 */
		@RequestMapping(value = "/viewPage")
		public ModelAndView viewPlanningPage() {
			ModelAndView modelview = new ModelAndView();
			modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
			modelview.addObject("baseTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
			modelview.setViewName("complete/completionTransferPage");
			return modelview;
		}
		
		/**
		 * 竣工资料工程列表条件查询
		 * @author cui
		 * @createTime 2016-09-18
		 * @param 
		 * @return
		 */
		@RequestMapping(value = "/queryCompletionTransfer")
		@ResponseBody
		public Map<String,Object> queryCompletionTransfer(HttpServletRequest request,ProjectQueryReq projectQueryReq){
			try {
				projectQueryReq.setSortInfo(request);
				//projectQueryReq.setProjStatusId(ProjStatusEnum.COMPLETION_TRANSFER.getValue());
				//projectQueryReq.setStepId(StepEnum.COMPLETION_TRANSFER.getValue());
				return projectService.queryProjectByTime(projectQueryReq);
			} catch (Exception e) {
				logger.error("工程信息查询失败！", e);
				return null;
			}
		}
//		
//		/**
//		* 打开审核屏
//		* @return ModelAndView
//		*/
//		@RequestMapping(value="/auditPage")
//		public ModelAndView audit(HttpServletRequest request){
//			ModelAndView modelView=new ModelAndView();
//			String isAudit = request.getParameter("isAudit");
//			String auditLevel = request.getParameter("auditLevel");
//			modelView.addObject("isAudit",isAudit);				//当前审核是否通过
//			modelView.addObject("auditLevel",auditLevel);       //当前点击的按钮的级别
//			modelView.addObject("suJgj",PostTypeEnum.SUPERVISOR.getValue());//监理
//			modelView.addObject("constructionPrincipal",PostTypeEnum.SUPERVISOR.getValue());//监理
//			modelView.addObject("director",PostTypeEnum.DIRECTOR.getValue());	//处长
//			modelView.addObject("ongcPrincipal",PostTypeEnum.WEBSITE_ENGINEER.getValue());	//管网工艺员
//			modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
//			modelView.setViewName("complete/completionTransferPage");
//			return modelView;
//		}
		
		/**
		 * 保存资料流转单
		 * @param 
		 * @return
		 */
		@RequestMapping(value = "/saveCompletionTransfer")
		@ResponseBody
		public String saveCompletionTransfer(@RequestBody FilingData filingData){
			try {
				filingDataService.saveFilingData(filingData);
				return Constants.OPERATE_RESULT_SUCCESS;
			} catch (Exception e) {
				logger.error("资料流转单保存失败!",e);
				return Constants.OPERATE_RESULT_FAILURE;
			}
	   }
		
		/**
		 * 打开查询页面
		 * @createTime 2016-09-22
		 * @return
		 */
		@RequestMapping(value="/searchPopPage")
		public ModelAndView searchPopPage(){
			ModelAndView modelView=new ModelAndView();
			modelView.setViewName("complete/filingDataSearchPage");
			return modelView;
		}
}
