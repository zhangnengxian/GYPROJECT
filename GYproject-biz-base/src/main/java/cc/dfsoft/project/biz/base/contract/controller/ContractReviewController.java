package cc.dfsoft.project.biz.base.contract.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.batik.apps.rasterizer.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fr.third.v2.org.apache.xmlbeans.impl.jam.internal.elements.VoidClassImpl;

import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.contract.entity.ContractReview;
import cc.dfsoft.project.biz.base.contract.enums.BudgetRuleEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractMethodEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractReviewService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 用于合同评审
 * 
 * @author wanghuijun 20190625
 */
@Controller
@RequestMapping(value = "/contractReview")
public class ContractReviewController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ContractReviewController.class);

	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	JointAcceptanceService jointAcceptanceService;
	
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ContractReviewService contractReviewService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contract/contractReviewPage");
		return modelAndView;

	}
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String projectType,String corpId,String menuId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//key规则为：工程类型 + 分公司ID + 菜单ID
		String TCMkey=(StringUtil.isBlank(projectType)?ProjLtypeEnum.CIVILIAN.getValue():projectType)+"_"+(StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId)+"_"+menuId;
		//key规则为：分公司ID + 菜单ID
		String CMkey=(StringUtil.isBlank(corpId)?loginInfo.getCorpId():corpId)+"_"+menuId;

		Object resultPage = Constants.getSysConfigByKey(TCMkey);
		if (resultPage==null){
			resultPage=Constants.getSysConfigByKey(CMkey);
		}

		String defalutPage = "renhuai_contractReviewRight";
		String resultViewUrl=resultPage!=null?resultPage.toString():defalutPage;

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());//放入登录人信息
		modelview.addObject("custCenterSign", PostTypeEnum.SALESMA.getValue());  //客服中心营销员
		modelview.addObject("viceMinister", PostTypeEnum.VICE_MINISTER.getValue());  //副部长
		modelview.addObject("treasurer", PostTypeEnum.TREASURER.getValue());  //财务员
		modelview.addObject("minister", PostTypeEnum.MINISTER.getValue());  //部长职务
		modelview.addObject("suCse", PostTypeEnum.SUCSE.getValue());  //总监职务
		modelview.addObject("viceGeneralManager", PostTypeEnum.VICE_GENERAL_MANAGER.getValue());  //副总经理职务
		modelview.addObject("generalManager", PostTypeEnum.GENERAL_MANAGER.getValue());  //总经理职务
		modelview.addObject("sysDate",projectDao.getDatabaseDate());//系统时间
		modelview.setViewName("contract/"+resultViewUrl);
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @author fuliwei
	 * @createTime
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq) throws Exception{
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_CONTRACT_REVIEW.getValue());//待发起合同评审
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.TO_CONTRACT_REVIEW.getValue());
			Map<String,Object> map=projectService.queryProjectByTime(projectQueryReq);
			return map;
		
	}
	
	
	/**
	 * 保存合同评审记录
	 *creater Administrator
	 *@version 2019年9月30日
	 *@param request (request)
	 *@param contractReview (合同评审记录)
	 *@return true or false 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveContractReview")
	@ResponseBody
	public String saveContractReview(HttpServletRequest request, @RequestBody(required = true) ContractReview contractReview)  {
		try{
			 String idenifier = contractReviewService.saveContractReview(contractReview);
			 jointAcceptanceService.finshSignCreateNotice(contractReview.getProjId(), contractReview.getCrId(), null, StepEnum.TO_CONTRACT_REVIEW.getValue(),
					SignDataTypeEnum.CONTRACT_REVIEW.getValue());
			 return idenifier;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存合同评审失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 *根据工程id查询合同评审记录信息
	 *creater wanghuijun
	 *@version 2019年9月30日
	 *@param id
	 *@return  contractReview(合同评审信息)
	 *@throws Exception(查询失败时可能会返回异常，由全局异常类统一进行处理)
	 */
	@RequestMapping(value = "/contractReviewDetail")
	@ResponseBody
	public ContractReview contractReviewDetail(@RequestParam(required=true) String id) throws Exception {
			return contractReviewService.contractReviewDetail(id);
	}
	

	/**
	 * 弹出搜索
	 * @author cui
	 * @createTime 2016-8-16
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projectType", ProjLtypeEnum.values());
		modelview.setViewName("complete/contractReviewSearchPop");
		return modelview;
	}

}
