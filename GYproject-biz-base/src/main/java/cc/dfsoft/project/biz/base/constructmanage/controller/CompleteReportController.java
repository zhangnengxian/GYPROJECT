package cc.dfsoft.project.biz.base.constructmanage.controller;


import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompleteReportReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.project.biz.base.constructmanage.service.CompleteReportService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 竣工报告
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/completeReport")
public class CompleteReportController {
	
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(CompleteReportController.class);
	

    /**竣工报告服务接口*/
    @Resource
    CompleteReportService completeReportService;
    
    /**签字通知服务接口*/
    @Resource
    SignNoticeService signNoticeService;   

    @Resource
    ProjectService projectService;
    
    @Resource
    ReportVersionService reportVersionService;
    
    @Resource
    ProjectDao projectDao;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("imgUrl", Constants.DISK_PATH+ Constants.SIGN_DISK_PATH);
		
		modelView.addObject("CU_PM",PostTypeEnum.CU_PM.getValue()); 					  //项目经理
		modelView.addObject("QUALITATIVE_CHECK_MEMBER",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue()); //质检员
		modelView.addObject("SAFTYOFFICER",PostTypeEnum.SAFTYOFFICER.getValue()); 							//安全员
		modelView.addObject("CONSTRUCTION",PostTypeEnum.CONSTRUCTION.getValue()); 							//施工员
		
		modelView.addObject("BUILDER",PostTypeEnum.BUILDER.getValue()); 				//现场代表
		modelView.addObject("PROJECT_LEADER",PostTypeEnum.PROJECT_LEADER.getValue()); //负责人-组长
	
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
		modelView.addObject("dataAdmin",PostTypeEnum.DATAMANAGER.getValue());	//数据管理员

		modelView.addObject("SUPERVISOR", PostTypeEnum.SUJGJ.getValue()); //监理工程师
		modelView.addObject("SUCSE", PostTypeEnum.SUCSE.getValue()); //总监
		modelView.addObject("unitType", loginInfo.getUnitType()); //单位类型
		modelView.addObject("cuUnitType", UnitTypeEnum.SUBCONTRACTING_UNIT.getValue()); //施工单位类型
		modelView.addObject("accessorySource", AccessorySourceEnum.COLLECT_FILE.getValue());//附件来源类型
		modelView.setViewName("constructmanage/completeReport");
		return modelView;
	}

	/**
	 * 竣工报告列表查询
	 * @param request
	 * @param completeReportReq
	 * @return
	 * @throws Exception  
	 */
	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String,Object> queryPage(HttpServletRequest request,CompleteReportReq completeReportReq) throws Exception {
			completeReportReq.setSortInfo(request);
			Map<String,Object> map= completeReportService.queryCompleteReport(completeReportReq);
	        return map;
	}
	
	/**
	 * 根据竣工报告id查询详述
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detailById")
	@ResponseBody
	public CompleteReport detailById(HttpServletRequest request,@RequestParam(required=true) String id) throws  Exception {
			CompleteReport cr = completeReportService.findByCrId(id);
			return cr;
	}

	/**
	 * 保存竣工报告
	 * @param completeReport
	 * @return
	 */
	@RequestMapping(value = "/saveCompleteReport")
	@ResponseBody
	public String technologyTellSave(HttpServletRequest request,@RequestBody(required=true) CompleteReport completeReport){
		try {
			return completeReportService.completeReportSave(completeReport);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", ExceptionUtil.getMessage(e));
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", ExceptionUtil.getMessage(e));
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("竣工报告保存失败!",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 详述
	 * @param id
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/constructionWorkDetail")
	@ResponseBody
	public CompleteReport constructionWorkDetail(@RequestParam(required=true) String id) throws Exception {
			return completeReportService.findByProjId(id);
		
	}

	/**
	 * 竣工报告添加删除标记，竣工报告删除时相应的签字通知同时删除
	 * @param 
	 * @return
	 */
	@RequestMapping("/deleteList")
	@ResponseBody
	public String deleteList(String crId){
		try{
			completeReportService.deleteById(crId);
			signNoticeService.deleteByBsId(crId,"1");   //删除签字通知,传入参数1原因，是此方法必须传入两个参数且不能传入空值。所以传入1参与辅助
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("竣工报告删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
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
	public void saveSignNotice(@RequestBody(required = true) String cwId) throws Exception {
			completeReportService.saveSignNotice(cwId);
	}
	/**
	 * 根据公司ID,工程类型，菜单ID查找cpt类型
	 * 参数不是必须的
	 * @author wanghuijun
	 * @createTime 2019年3月11日
	 * @param projId
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/queryCptType")
	@ResponseBody
	public String queryCptType (String projId,String menuId,String signDate){
		Project project = null;
		if(StringUtil.isNotBlank(projId)){
			project = projectDao.get(projId);
		}
		String cptUrl="completeReport1.cpt";
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String corpId = loginInfo.getCorpId();//默认当前用户的公司ID
		String projectType = null;
		String rvId="";
		if(project!=null){
			corpId = project.getCorpId();
			projectType = project.getProjectType();  //工程类型
		}
		if(StringUtil.isBlank(rvId)){
			//获取版本信息:菜单ID下取离指定日期最近的一个版本
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			reportVersionReq.setMenuId(menuId);
			reportVersionReq.setSignDate(signDate);
			reportVersionReq.setCorpId(corpId);
			reportVersionReq.setProjType(projectType);
			//查询该版本日期之前的最近一次版本信息
			List<ReportVersion> versions = new ArrayList<ReportVersion>();
			try {
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if(versions!=null && versions.size()>0){
					rvId = versions.get(0).getRvId();
				}else{//取全局的
					reportVersionReq.setCorpId(Constants.START_REPORT_CPT_CORP_MODE);//默认
					corpId = Constants.START_REPORT_CPT_CORP_MODE;
					versions = reportVersionService.queryReportVersions(reportVersionReq);
					if(versions!=null && versions.size()>0){
						rvId = versions.get(0).getRvId();
					}
				}
			} catch (ParseException e) {
				logger.error("开工报告报表打印版本查询失败！", e);
				e.printStackTrace();
			}
		}
		//分公司下有配置则返回分公司的，否则返回全局的
		String key =projectType+"_"+corpId + "_" + menuId + "_" + rvId;  
		Object obj = Constants.getSysConfigByKey(key);
		if(obj != null){
			cptUrl = obj.toString();
			return cptUrl;
		}
		//全局
		key = Constants.START_REPORT_CPT_CORP_MODE+"_"+menuId + "_" + rvId;
		obj = Constants.getSysConfigByKey(key);
		if(obj != null){
			cptUrl = obj.toString();
			return cptUrl;
		}
		return cptUrl;
	}
}
