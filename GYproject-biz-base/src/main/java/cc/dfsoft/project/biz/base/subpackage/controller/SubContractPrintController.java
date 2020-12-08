package cc.dfsoft.project.biz.base.subpackage.controller;

import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.contract.controller.ContractAuditController;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分包合同打印
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/subContractPrint")
public class SubContractPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	/**合同服务接口*/
	@Resource
	SubContractService subContractService;
	
	@Resource
	ReportVersionService reportVersionService;
	/**
	 * 打开主页面
	 * @return
	 *
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		try {
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			modelView.addObject("contractVersion",reportVersionService.queryReportVersions(reportVersionReq));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			System.err.println("合同版本查询失败!");
		}
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//签字路径
		modelView.setViewName("subcontract/subContractPrint");
		return modelView;
	}
	/**
	 * 分包合同列表查询
	 * @param request
	 * @param subContractQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySubContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,SubContractQueryReq subContractQueryReq){
		try {
			subContractQueryReq.setSortInfo(request);
			Map<String,Object> map=subContractService.querySubContract(subContractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("分包合同信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/subContractSearchPopPage")
	public ModelAndView subContractSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("subcontract/subContractPrintSearchPopPage");
		return modelview;
	}

	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("subcontract/subContractPrintRight");
		return modelview;
		
	}

	/**
	 * 保存打印分包合同
	 * @param subContract
	 * @return
	 */
	@RequestMapping(value = "/saveSubContractPrint")
	@ResponseBody
	public String saveSubContract(@RequestBody(required = true) SubContract subContract){
		try {
			return subContractService.saveSubContractPrint(subContract);
		} catch (Exception e) {
			logger.error("分包合同签订区信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 合同打印标记
	 * @author cui
	 * @createTime 2017-2-14
	 * @param  projId
	 * @return String
	 */
	@RequestMapping(value = "/signSubContractPrint")
	@ResponseBody
	public String signSubContractPrint(@RequestBody(required = true) String projId){
		try {
			subContractService.signSubContractPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 根据工程ID、菜单ID、报表版本获取相应的打印模板
	 * @author liaoyq
	 * @createTime 2018年8月13日
	 * @param projId
	 * @param menuId
	 * @param rvId
	 * @return
	 */
	@RequestMapping(value="viewContractReport")
	@ResponseBody
	public String viewContractReport(String projId, String menuId,String signDate,String corpId,String rvId){
		String cptUrl ="subContractPrint.cpt";
		//获取工程信息
		Project project = projectService.queryProjectById(projId);
		if(project==null){
			return cptUrl;
		}
		if(StringUtil.isBlank(rvId)){
			//获取版本信息
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			reportVersionReq.setProjType(project.getProjectType());
			reportVersionReq.setMenuId(menuId);
			reportVersionReq.setCorpId(project.getCorpId());
			reportVersionReq.setSignDate(signDate);
			
			//查询该版本日期之前的最近一次版本信息
			List<ReportVersion> versions = new ArrayList<ReportVersion>();
			try {
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if(versions==null || versions.size()<1){
					return cptUrl;
				}else{
					rvId = versions.get(0).getRvId();
				}
			} catch (ParseException e) {
				logger.error("施工合同报表打印版本查询失败！", e);
				e.printStackTrace();
				return cptUrl;
			}
		}
		//组装key值得到信息
		String key = project.getProjectType() + "_" + project.getCorpId() + "_" + menuId + "_" + rvId;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj == null){
			return cptUrl;
		}
		return obj.toString();
	}
}
