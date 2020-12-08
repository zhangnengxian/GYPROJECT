package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.complete.enums.AcceptanceAtateEnum;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceApplyService;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.enums.AuditResultEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * 分部验收
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/divisionalAcceptance")
public class DivisionalAcceptanceController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DivisionalAcceptanceController.class);

	/**工程服务接口*/
	@Resource
	ProjectService projectService;

	/**分部验收申请*/
	@Resource
	DivisionalAcceptanceApplyService divisionalAcceptanceApplyService;

	/**分部验收*/
	@Resource
	DivisionalAcceptanceService divisionalAcceptanceService;

	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	OperateRecordService operateRecordService;

	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月20日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("haveNotAcceptance", AcceptanceAtateEnum.NOT_ACCEPTANCE.getValue());//验收
		modelView.addObject("alreadyAcceptance", AcceptanceAtateEnum.ALREADY_ACCEPTANCE.getValue());//已验收
		modelView.addObject("deptDivide",loginInfo.getDeptDivide()); //部门组别
		modelView.addObject("qualitySafetyGroup",DeptDivideEnum.QUALITY_SAFETY_GROUP.getValue()); //质量安全组
		modelView.setViewName("complete/divisionalAcceptance");
		return modelView;
	}

	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年6月20日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage(String projId,String menuId) {
		ModelAndView modelview = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息

		modelview.addObject("sysDate",manageRecordService.getDatabaseDate());//系统时间
		modelview.addObject("post", loginInfo.getPost());
		modelview.addObject("cuSpdPrincipal",PostTypeEnum.BUILDER.getValue()); //现场代表
		modelview.addObject("duDeputy",PostTypeEnum.DESIGNER.getValue()); //设计员
		modelview.addObject("suFieldJgj",PostTypeEnum.SUJGJ.getValue()); //监理
		modelview.addObject("managementQae",PostTypeEnum.CONSTRUCTION.getValue()); //施工员
		modelview.addObject("organizationMan",PostTypeEnum.TECHNICIAN.getValue()); //治安组
		modelview.addObject("custCenterSign",PostTypeEnum.INSPECTOR.getValue()); //客服中心
		modelview.addObject("ceneralEngineer",PostTypeEnum.CENERAL_ENGINEER.getValue()); //总工程师
		modelview.addObject("regulatClass",PostTypeEnum.REGULAT_CLASS.getValue()); //巡线调压班签字
		modelview.addObject("transCompanySign",PostTypeEnum.PROCESS_TECHNICIAN.getValue()); //输配签字
		modelview.addObject("measurementSign",PostTypeEnum.PRODUCTION_STATISTICIANS.getValue()); //计量所签字
		modelview.addObject("MarketMinister", PostTypeEnum.MINISTER.getValue()); // 市场部签字
		modelview.setViewName("complete/"+getViewUrl(loginInfo, projId,menuId ));
		return modelview;
	}




	public String getViewUrl(LoginInfo loginInfo,String projId,String menuId){
		Project project = projectService.findById(StringUtils.isNotBlank(projId)?projId:"");

		String corpId=loginInfo.getCorpId();
		String type= ProjLtypeEnum.CIVILIAN.getValue();
		String cotbm="1";
		menuId=StringUtils.isNotBlank(menuId)?menuId:"110712";
		String viewUrl = "divisionalAcceptanceRight";

		if (project!=null){//右侧页面加载
			corpId=project.getCorpId();
			type=project.getProjectType();
			cotbm=project.getContributionMode();
		}

		String CTCMkey =corpId+"_"+type+"_"+cotbm+"_"+menuId;
		String CTMkey =corpId+"_"+type+"_"+menuId;
		String CMkey =corpId+"_"+menuId;
		Object o=sysConfig(CTCMkey)!=null?sysConfig(CTCMkey):sysConfig(CTMkey)!=null?sysConfig(CTMkey):sysConfig(CMkey);
		viewUrl=o!=null?o.toString():viewUrl;

		return viewUrl;
	}


	public Object sysConfig(String key){
		return Constants.getSysConfigByKey(key);
	}

	/**
	 * 弹屏查询
	 * @author fuliwei
	 * @createTime 2017年8月14日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/acceptanceSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("acceptanceAtate", AcceptanceAtateEnum.values());//是否完成验收
		modelview.setViewName("complete/acceptanceSearchPop");
		return modelview;
	}






	/**
	 * 分部验收申请列表查询
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/queryDivisionalAcceptance")
	@ResponseBody
	public Map<String,Object> queryDivisionalAcceptance(HttpServletRequest request,DivisionalAcceptanceReq req){
		try {
			req.setSortInfo(request);
			req.setAuditState(AuditResultEnum.PASSED.getValue());//默认加载审核过的
			if(StringUtils.isBlank(req.getAcceptanceState())){
				req.setAcceptanceState(AcceptanceAtateEnum.NOT_ACCEPTANCE.getValue());//默认未完成分部验收
			}
			return divisionalAcceptanceApplyService.queryDivisionalAcceptanceApply(req);
		} catch (Exception e) {
			logger.error("分部验收申请列表查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存分部验收
	 * @author fuliwei
	 * @createTime 2017年8月14日
	 * @param
	 * @return
	 */
	@RequestMapping(value="/saveDivisionalAcceptance")
	@ResponseBody
	public String saveDivisionalAcceptance(HttpServletRequest request,@RequestBody(required = true) DivisionalAcceptance divisionalAcceptance){
		try{
			divisionalAcceptanceService.saveDivisionalAcceptance(divisionalAcceptance);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存分部验收失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}



	/**
	 * @MethodDesc: 作废弹出框
	 * @Author zhangnx
	 * @Date 2019/4/2 15:59
	 */
	@RequestMapping(value = "/acceptanceDestroyPopPage")
	public ModelAndView acceptanceDestroyPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/acceptanceDestroyPopPage");
		return modelview;
	}
	/**
	 * @MethodDesc: 作废保存
	 * @Author zhangnx
	 * @Date 2019/4/2 14:07
	 */
	@RequestMapping(value="/saveDestroyDivisiAce")
	@ResponseBody
	public Boolean saveDestroyDivisiAce(@RequestBody(required = true) DivisionalAcceptance divisionalAcceptance){
		String remark=divisionalAcceptance.getRemark();
		Project project = projectService.queryProjectById(divisionalAcceptance.getProjId());
		BeanUtils.copyProperties(project,divisionalAcceptance);
		divisionalAcceptance.setRemark(remark);
		divisionalAcceptance.setRegulatClassOpinion("作废人："+SessionUtil.getLoginInfo().getStaffName());
		divisionalAcceptance.setRegulatClassSignDate(new Date());
		divisionalAcceptance.setThisAcceptanceContent("作废");

		DivisionalAcceptanceApply divisApply;
		try {
			divisApply = divisionalAcceptanceApplyService.findById(divisionalAcceptance.getProjId(), divisionalAcceptance.getDaaId());
			if(divisApply!=null){
				divisApply.setAcceptanceState("1");
				divisionalAcceptanceApplyService.updateDivisionalAcceptanceApply(divisApply);
				//作废分部验收待办通知
				operateRecordService.cerateCurOperateRecord(project,divisApply.getAuditState(), WorkFlowTypeEnum.ASSIST_PROGRESS.getValue(), AssistProgressTypeEnum.DIVISIONAL_ACCEPT.getValue(), divisionalAcceptance.getDaaId(),new Staff(),null,false);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		

		try {
			divisionalAcceptanceService.saveDivisionalAcceptance(divisionalAcceptance);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/viewDivisionalAcceptance")
	@ResponseBody
	public DivisionalAcceptance viewDivisionalAcceptance(@RequestParam(required=true)String id) throws ParseException{
		return divisionalAcceptanceService.viewByDaaId(id);
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
			divisionalAcceptanceService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
}
