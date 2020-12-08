package cc.dfsoft.project.biz.base.contract.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.enums.ProjectTypeEnum;
import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.PayTypeEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 合同打印
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/contractPrint")
public class ContractPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ContractAuditController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**合同服务接口*/
	@Resource
	ContractService contractService;
	
	@Resource
	ReportVersionService reportVersionService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印

		try {
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			List<ReportVersion> reportVersionList =  reportVersionService.queryReportVersions(reportVersionReq);
			/*for (ReportVersion r:reportVersionList) {
				System.out.println(r.getRvName());
			}*/
			modelView.addObject("contractVersion",reportVersionService.queryReportVersions(reportVersionReq));
		} catch (ParseException e) {
			logger.error("查询版本失败！", e);
		}
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.setViewName("contract/contractPrint");
		return modelView;
	}
	/**
	 * 合同列表查询
	 * @param request
	 * @param contractQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,ContractQueryReq contractQueryReq){
		try {
			contractQueryReq.setSortInfo(request);
			Map<String,Object> map=contractService.queryContractPrint(contractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/contractSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.addObject("payType", PayTypeEnum.values());//付款方式
		modelview.setViewName("contract/constractPrintSearchPopPage");
		return modelview;
	}
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("payType", PayTypeEnum.values());//付款方式
		/*modelview.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelview.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
*/		modelview.addObject("isPrint", ContractIsPrintEnum.values());
		modelview.setViewName("contract/contractPrintRight");
		return modelview;
	}
	
	/**
	 * 打印保存合同
	 * @author fuliwei
	 * @createTime 2016-7-22
	 * @param  contract 合同
	 * @return String 
	 */
	@RequestMapping(value = "/saveContract")
	@ResponseBody
	public String saveContract(@RequestBody(required = true) Contract contract){
		try {
			return contractService.saveContractPrint(contract);
		} catch (Exception e) {
			logger.error("打印合同保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 合同打印标记
	 * @author fuliwei
	 * @createTime 2016-12-30
	 * @param  String projId
	 * @return String 
	 */
	@RequestMapping(value = "/signContractPrint")
	@ResponseBody
	public String signContractPrint(@RequestBody(required = true) String projId){
		try {
			return contractService.signContractPrint(projId);
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 详述
	 * @author fuliwei
	 * @createTime 2016-6-28
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewContract")
	@ResponseBody
	public Contract viewContract(@RequestParam(required=true) String id){
		Contract contract = contractService.findByProjId(id);
		return contract;
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
	@RequestMapping(value="viewContractReport",method = RequestMethod.POST)
	@ResponseBody
	public String viewContractReport(String projId, String menuId,String signDate,String rvId){
		String cptUrl = "";
		//获取工程信息
		Project project = projectService.queryProjectById(projId);
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String corpId = loginInfo.getCorpId();//默认当前用户的公司ID
		String projType = ProjLtypeEnum.CIVILIAN.getValue();//默认民用类型工程
		if(project!=null){
			corpId = project.getCorpId();
			projType = project.getProjectType();
		}
		if(StringUtil.isBlank(rvId)){
			//获取版本信息
			ReportVersionReq reportVersionReq = new ReportVersionReq();
			reportVersionReq.setProjType(projType);
			reportVersionReq.setMenuId(menuId);
			reportVersionReq.setCorpId(corpId);
			reportVersionReq.setSignDate(signDate);
			
			//查询该版本日期之前的最近一次版本信息
			List<ReportVersion> versions = new ArrayList<ReportVersion>();
			try {
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if(versions!=null && versions.size()>0){
					rvId = versions.get(0).getRvId();
				}
			} catch (ParseException e) {
				logger.error("安装合同报表打印版本查询失败！", e);
				e.printStackTrace();
			}
		}
		//组装key值得到信息
		String key = projType + "_" + corpId + "_" + menuId + "_" + rvId;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj == null){
			return cptUrl;
		}
		return obj.toString();
		
	}
}
