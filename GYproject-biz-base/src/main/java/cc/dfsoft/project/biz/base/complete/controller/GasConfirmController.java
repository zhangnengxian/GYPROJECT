package cc.dfsoft.project.biz.base.complete.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.charge.service.AccrualsRecordService;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.project.biz.base.complete.service.GasApplyService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.MrResultEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.service.SubContractService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 资料交接确认
 * @createTime 2016-09-18
 * @author zhangmeng
 *
 */

@Controller
@RequestMapping(value="/gasConfirm")
public class GasConfirmController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(JointAcceptanceController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**管理记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	
	/**单据类型服务接口*/
	@Resource
	DocTypeService docTypeService;
	
	/**分包合同服务接口*/
	@Resource
	SubContractService subContractService;
	
	/**收付流水接口*/
	@Resource
	AccrualsRecordService accrualsRecordService;
	
	/**通气确认接口*/
	@Resource
	GasApplyService gasApplyService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/gasConfirm");
		return modelView;
	}
	
	/**
	 * 弹出搜索屏
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("complete/searchGasPopPage");
		return modelview;
	}
	
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("auditTime",manageRecordService.getDatabaseDate().getTime());	//当前数据库时间
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelview.addObject("loginInfo",loginInfo);											//登录人信息
		modelview.addObject("cuPm",PostTypeEnum.COST_MANAGER.getValue());//经理
		modelview.addObject("builder",PostTypeEnum.BUILDER.getValue());//甲方代表
		modelview.addObject("chief",PostTypeEnum.DIRECTOR.getValue());//施工处长
		modelview.addObject("vicePresident",PostTypeEnum.VICE_GENERAL_MANAGER.getValue());//副总经理
		modelview.addObject("mrResult",MrResultEnum.values());
		modelview.setViewName("complete/gasConfirmRight");
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author zhangmeng
	 * @createTime 2016-09-19
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryGasConfirm")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setSortInfo(request);
			/*List<String> projStuList = new ArrayList();
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValue(ProjStatusEnum.SETTLEMENT_REPORT.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}*/
			//projectQueryReq.setProjStatusId(ProjStatusEnum.AERATE_APPLY.getValue()); //待通气申请
			projectQueryReq.setSortInfo(request);
			//projectQueryReq.setStepId(StepEnum.GAS_CONFIRM.getValue());
			Map<String, Object> map = projectService.queryProjectByTime(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 分包信息详述
	 * @return
	 */
	@RequestMapping(value = "/jointDetail")
	@ResponseBody
	public GasApply jointDetail(@RequestParam(required=true) String id){
		try {
			return subContractService.jointDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 保存通气申请
	 * @author flw
	 * @createTime 2016-1-12
	 * @param  ga
	 * @return void
	 */
	@RequestMapping(value = "/saveGasApply")
	@ResponseBody
	public String saveGasApply(@RequestBody GasApply ga){
		try {
			gasApplyService.saveGasApply(ga);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("通气申请保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	
}
