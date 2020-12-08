package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.project.biz.base.complete.service.PreinspectionService;
import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.constructmanage.service.WorkReportService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 预验收打印
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/preinspectionPrint")
public class PreinspectionPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(PreinspectionPrintController.class);
	
	/**预验收服务接口*/
	@Resource
	PreinspectionService preinspectionService;
	
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	@Autowired
	WorkReportService workReportService;
	
	@Autowired
	ReportVersionService reportVersionService;
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("loginPost", SessionUtil.getLoginInfo().getPost());
		modelAndView.addObject("dataAdmin", PostTypeEnum.DATAMANAGER.getValue());
		modelAndView.setViewName("complete/preinspectionPrint");
		return modelAndView;
	}
	
	/**
	 * 弹出查询屏
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewSearchPopPage")
	public ModelAndView viewSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelAndView.setViewName("complete/preinspectionPrintSearchPop");
		return modelAndView;
	}



	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryPreinspectionPrint")
	@ResponseBody
	public Map<String,Object> queryPreinspectionPrint(HttpServletRequest request,PreinspectionReq req)throws ParseException {
		try {
			req.setSortInfo(request);
			if(StringUtils.isBlank(req.getIsPrint())){
				req.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//默认加载未打印
			}
			Map<String,Object> map = preinspectionService.querySelInspection(req);
			return map;
		} catch (Exception e) {
			logger.error("预验收列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 预验收打印标记
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/signPreInspectionPrint")
	@ResponseBody
	public String signPreInspectionPrint(@RequestBody(required = true) String projId){
		try {
			preinspectionService.signSelInspectionPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("预验收打印标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	@RequestMapping(value="/viewDetail")
	@ResponseBody
	public Preinspection viewDetail(@RequestParam(required=true) String id){
		try {
			Preinspection preinspection = preinspectionService.findByProjId(id);
			return preinspection;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}


	@RequestMapping(value="/modifyPreinspection")
	@ResponseBody
	public boolean modifyPreinspection(@RequestBody(required = true)Preinspection preinspection){
		try {
			boolean b=preinspectionService.modifyPreinspection(preinspection);
			return b;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}






	/**
	 * 根据公司ID,工程类型，菜单ID查找cpt类型
	 * 参数不是必须的
	 * @author wanghuijun
	 * @createTime 2018年9月7日
	 * @param corpId
	 * @param projectType
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/queryCptType")
	@ResponseBody
	public String queryCptType (String projId,String corpId,String projectType,String menuId){
		WorkReport workReport = null;
		try {
			if(StringUtil.isNotBlank(projId)){
				workReport = workReportService.workReportDetail(projId, null);
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  //查找开工报告取实际开工日期
		String cptUrl="preinspectionPrint.cpt";   //默认cpt(贵阳)
		String rvId = "";  //版本ID
		if(StringUtil.isNotBlank(projId) && StringUtil.isNotBlank(corpId) && StringUtil.isNotBlank(projectType) 
				&& StringUtil.isNotBlank(menuId) && StringUtil.isNotBlank(workReport.getPlannedStartDate().toString())){
			//获取版本信息:菜单ID下取离指定日期最近的一个版本
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			reportVersionReq.setMenuId(menuId);
			reportVersionReq.setSignDate(workReport.getPlannedStartDate().toString());
			reportVersionReq.setCorpId(corpId);
			reportVersionReq.setProjType(projectType);
			//查询该版本日期之前的最近一次版本信息
			List<ReportVersion> versions = new ArrayList<ReportVersion>();
			try {
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if(versions!=null && versions.size()>0){
					rvId = versions.get(0).getRvId();
				}else{//取全局的
					corpId = Constants.START_REPORT_CPT_CORP_MODE;
					reportVersionReq.setCorpId(corpId);//默认
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
