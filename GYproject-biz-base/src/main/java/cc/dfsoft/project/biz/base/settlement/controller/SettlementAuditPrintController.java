package cc.dfsoft.project.biz.base.settlement.controller;

import cc.dfsoft.project.biz.base.common.dto.ReportVersionReq;
import cc.dfsoft.project.biz.base.common.entity.ReportVersion;
import cc.dfsoft.project.biz.base.common.service.ReportVersionService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.settlement.service.SettlementAuditService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value="/settlementAuditPrint")
public class SettlementAuditPrintController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SettlementAuditPrintController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**结算审核服务接口*/
	@Resource
	SettlementAuditService settlementAuditStartService;
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
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.setViewName("settlement/settlementAuditPrint");
		return modelView;
	}
	
	/**
	 * 列表条件查询
	 * @param request
	 * @param jointAcceptanceReq
	 * @return
	 */
	@RequestMapping(value = "/querySettlementAudit")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(HttpServletRequest request,SettlementDeclarationReq settlementDeclarationReq){
		try {
			settlementDeclarationReq.setSortInfo(request);
			Map<String,Object> map = settlementAuditStartService.querySettlementDeclaration(settlementDeclarationReq);
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
	@RequestMapping(value = "/settlementSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelview.setViewName("settlement/settlementSearchPop");
		return modelview;
	}
	
	/**
	 * 打印标记
	 * @author cui
	 * @createTime 2017-2-15
	 * @param  String projId
	 * @return String 
	 */
	@RequestMapping(value = "/signSettlementAuditPrint")
	@ResponseBody
	public String signSettlementAuditPrint(@RequestBody(required = true) String projId){
		try {
			settlementAuditStartService.signSettlementAuditPrint(projId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印合同标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询配置的施工结算书打印模板
	 * 不存在版本问题 ， key值规则：出资方式_分公司ID_菜单ID_出资方式
	 * @author liaoyq
	 * @createTime 2018年8月22日
	 * @param sbId
	 * @return
	 */
	@RequestMapping(value="viewSettlementReport",method = RequestMethod.POST)
	@ResponseBody
	public String viewSubBudgetReport(String projId, String menuId,String rvId){
		String cptUrl = "";
		try {
			//获取工程信息
			Project project = projectService.queryProjectById(projId);
			if(project==null){ return cptUrl; }

			if (StringUtils.isBlank(rvId)) {
				//查询施工结算报送日期
				SettlementDeclaration sd = settlementAuditStartService.findByProjId(projId);

				ReportVersionReq reportVersionReq = new ReportVersionReq();
				reportVersionReq.setProjType(project.getContributionMode());//出资方式
				reportVersionReq.setMenuId(menuId);
				reportVersionReq.setCorpId(project.getCorpId());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				reportVersionReq.setSignDate((sd != null && sd.getOcoDate() != null) ? format.format(sd.getOcoDate()) : "");

				//查询该版本日期之前的最近一次版本信息
				List<ReportVersion> versions = new ArrayList<ReportVersion>();
				versions = reportVersionService.queryReportVersions(reportVersionReq);
				if (versions != null && versions.size() > 0) {
					rvId = versions.get(0).getRvId();
				}
			}
			//组装key值得到信息:g根据出资方式和公司ID
			String key = project.getContributionMode() + "_" + project.getCorpId() + "_" + menuId;
			if(StringUtil.isNotBlank(rvId)){
				key = key + "_"+rvId;
			}
			Object obj = Constants.getSysConfigByKey(key);
			if(obj != null){
				return obj.toString();
			}
			return cptUrl;
		} catch (Exception e) {
			logger.error("施工结算书报表打印版本查询失败！", e);
			e.printStackTrace();
			return cptUrl;
		}
	}
}
