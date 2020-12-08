package cc.dfsoft.project.biz.base.settlement.controller;

import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;
import cc.dfsoft.project.biz.base.settlement.service.SettlementJonintlySignService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * 结算汇签
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/settlementJonintlySign")
public class SettlementJonintlySignController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SettlementJonintlySignController.class);
	
	/**结算汇签*/
	@Resource
	SettlementJonintlySignService settlementJonintlySignService;
	
	/**
	 * 结算汇签
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("post", loginInfo.getPost());
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.addObject("equipmentTechnician", PostTypeEnum.EQUIPMENT_TECHNICIAN.getValue());
		modelView.setViewName("settlement/settlementJonintlySign");
		return modelView;
	}
	
	/**
	 * 打开详述页面
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage(String projId,String corpId,String menuId,String projectType,String contributionMode) {
		String key = corpId+"_"+projectType+"_"+contributionMode+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "settlementJonintlySignRight";
		}
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("proCntractType", ContractTypeEnum.RESIDENT.getValue());//居民户
		modelview.addObject("budgeterPost",PostTypeEnum.BUDGET_MEMBER.getValue());//预结算员
		modelview.addObject("budgeterLeaderPost",PostTypeEnum.BUDGET_GROUP_LEADER.getValue());//预结算组长
		modelview.addObject("builder",PostTypeEnum.BUILDER.getValue());//现场管理员
		modelview.addObject("viceMinisterPost",PostTypeEnum.VICE_MINISTER.getValue());//副部长
		modelview.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue());//监理
		modelview.addObject("suCesPost",PostTypeEnum.SUCSE.getValue());//总理
		modelview.addObject("recorderPost",PostTypeEnum.RECORDER.getValue()); //主任
		modelview.addObject("dataHandlePost",PostTypeEnum.DATA_HANDLE.getValue());//数据处理员
		modelview.addObject("equipmentPost",PostTypeEnum.EQUIPMENT_TECHNICIAN.getValue());//技术装备员
		modelview.addObject("treasurerPost",PostTypeEnum.TREASURER.getValue());//财务员
		modelview.addObject("generalPost",PostTypeEnum.GENERAL_MANAGER.getValue());//财务总经理
//		modelview.setViewName("settlement/settlementJonintlySignRight");
		modelview.setViewName("settlement/"+viewUrl);
		return modelview;
	}
	
	/**
	 * 弹屏查询
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/settlementJonintlySignPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("settlement/settlementJonintlySignPop");
		return modelview;
	}
	
	/**
	 * 工程列表
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq req){
		try {
			req.setSortInfo(request);
			/*if(StringUtils.isBlank(req.getSignStatus())){
				//默认为未完成汇签的
				req.setSignStatus(AuditResultEnum.NOT_APPLY.getValue());
			}*/
			return settlementJonintlySignService.queryProject(req);
		} catch (Exception e) {
			logger.error("工程列表查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存汇签单
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/saveSettlementJonintlySign")
	@ResponseBody
	public String saveSettlementJonintlySign(HttpServletRequest request,@RequestBody(required = true) SettlementJonintlySign settlementJonintlySign){
		try{
			settlementJonintlySignService.saveSettlementJonintlySign(settlementJonintlySign);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存汇签单失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewSettlementJonintlySign")
	@ResponseBody
	public SettlementJonintlySign viewDivisionalAcceptance(@RequestParam(required=true)String id){
		return settlementJonintlySignService.findById(id);
	}
	
}
