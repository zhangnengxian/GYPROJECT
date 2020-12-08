package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.budget.service.ChangeManagementService;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.service.ContractService;
import cc.dfsoft.project.biz.base.design.enums.DesignChangeStateEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.subpackage.controller.PaymentApplyController;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 设计变更申请
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/designChangeApply")
public class DesignChangeApplyController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(PaymentApplyController.class);
	
	/** 安装合同接口*/
	@Resource
	ContractService contractService;
	
	/**变更记录*/
	@Resource
	ChangeManagementService changeManagementService;
	
	/**审核信息*/
	@Resource
	ManageRecordService manageRecordService;
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年11月3日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("design/designChangeApply");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author fuliwei
	 * @createTime 2017年11月3日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginInfo",SessionUtil.getLoginInfo());			//放入登录人信息
		modelview.addObject("sysDate",manageRecordService.getDatabaseDate());//系统时间
		modelview.setViewName("design/designChangeApplyRight");
		return modelview;
	}
	
	/**
	 * 查询
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/designSearchPopPage")
	public ModelAndView designSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("design/designSearchPopPage");
		return modelview;
	}
	
	/**
	 * 合同列表查询-签订完合同才能发生变更
	 * @author fuliwei
	 * @createTime 2017年11月3日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryContract")
	@ResponseBody
	public Map<String,Object> queryContract(HttpServletRequest request,ContractQueryReq contractQueryReq){
		try {
			//此处改为下计划之后才能查找出工程进行变更申请，若不改当工程签订完合同但是未下计划并且现场代表和踏勘员为同一人时，此时职务为现场代表的便不能看见
			//已签合同但是并未下计划的工程（因为代码联查了计划表，只有查计划表才能知道现场代表是谁，因为未下计划，故不知道现场代表是谁，所以便不能看见工程），
			//但是踏勘员能够看见已签合同并且未下计划的工程（因踏勘员职务未存入计划表，而是工程表，代码是从工程表中查询，故踏勘员能看见工程），若现场代表和踏勘员
			//为同一人时，则能看见已签合同但是未下计划的工程，若此时发起变更申请，则发起之后现场代表便看不见自己发起的记录（因为未下计划所以查不出现场代表，故看不见发起的变更申请记录）。
			//当工程到下计划时，也不能保证发起人就是现场代表。故改为下计划之后才查找出工程进行变更申请。
			contractQueryReq.setSortInfo(request);
			List<String>projStuList = new ArrayList<String>();
			List<ProjStatusEnum> projStusEnum =ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_APPROVAL.getValue(),ProjStatusEnum.SETTLEMENT_REPORT.getValue());
			for (ProjStatusEnum projStatusEnum : projStusEnum) {
				projStuList.add(projStatusEnum.getValue());
			}
			contractQueryReq.setProjStuList(projStuList);
			//contractQueryReq.setModifyStatus("all");
			Map<String,Object> map=contractService.queryContractPrint(contractQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewChangeManagement")
	@ResponseBody
	public ChangeManagement viewChangeManagement(@RequestParam(required=true)String projId,String cmId){
		try {
			return changeManagementService.viewChangeManagementById(projId,cmId);
		} catch (Exception e) {
			logger.error("查询失败！");
		}
		return null;
	}
	
	/**
	 * 变更记录保存
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveChangeManagement")
	@ResponseBody
	public String saveChangeManagement(@RequestBody ChangeManagement changeManagement){
		try {
			changeManagementService.saveChangeManagement(changeManagement,DesignChangeStateEnum.WAIT_PUSH.getValue());
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			logger.error("变更信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 推送到设计院确认
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateChangeState")
	@ResponseBody
	public String updateChangeState(@RequestBody(required = true) String cmId){
		try {
			changeManagementService.updateChangeState(cmId,DesignChangeStateEnum.WAIT_CONFIRM.getValue());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("推送设计变更失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 查询变更列表
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryChange")
	@ResponseBody
	public Map<String,Object> queryChange(HttpServletRequest request,ChangeManagementQueryReq changeManagementQueryReq){
		try {
			changeManagementQueryReq.setSortInfo(request);
			Map<String,Object> map=changeManagementService.queryChange(changeManagementQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("付款申请列表查询失败！", e);
			return null;
		}
	}
	
}
