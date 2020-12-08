package cc.dfsoft.project.biz.base.project.controller;

import cc.dfsoft.project.biz.base.baseinfo.entity.WorkFlow;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.MenuBackSet;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.FallbackApplyService;
import cc.dfsoft.project.biz.base.project.service.MenuBackSetService;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.ArraysUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * 回退申请
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/fallbackApply")
public class FallbackApplyController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(FallbackApplyController.class);
	
	/** 延期申请*/
	@Resource
	FallbackApplyService fallbackApplyService;
	
	/**菜单回退设置服务接口*/
	@Resource
	MenuBackSetService menuBackSetService;
	@Resource
	CashFlowDao cashFlowDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	WorkFlowService workFlowService;
	
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(String menuId,String projectType,String contributionMode,String corpId,String projStatusId,String tableId){
		ModelAndView modelView=new ModelAndView();
		List<MenuBackSet> mbsList = queryMenuBackSetList(projStatusId,menuId,corpId,projectType,contributionMode);
		modelView.addObject("mbsList", mbsList);
		modelView.addObject("menuId", menuId);
		modelView.addObject("tableId", tableId);
		modelView.setViewName("common/fallbackApplyPop");
		return modelView;
	}
	/**
	 * 回退申请条件查询
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryFallbackApply")
	@ResponseBody
	public Map<String,Object> queryFallbackApply(HttpServletRequest request,FallbackApplyReq req){
		try {
			req.setSortInfo(request);
			Map<String,Object> map=fallbackApplyService.queryFallbackApply(req);
			return map;
		} catch (Exception e) {
			logger.error("回退申请列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 回退申请查询详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	public FallbackApply findById(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			return fallbackApplyService.findById(id);
		} catch (Exception e) {
			logger.error("回退申请查询详述失败！", e);
			return null;
		}
	}
	/**
	 * 回退申请保存
	 * @param fallbackApply
	 * @return
	 */
	@RequestMapping(value = "/saveFallbackApply")
	@ResponseBody
	public ResultMessage saveFallbackApply(@RequestBody FallbackApply fallbackApply){
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setRet_type(Constants.FAIL_CODE);

		Project project = projectDao.get(fallbackApply.getProjId());
		if (project==null || fallbackApply==null) return resultMessage;
		String origStepId= WorkFlowActionEnum.getStepCodeByStatusCode(project.getProjStatusId());


		boolean existStep = isExistStep(fallbackApply.getFallbackStepId(),project);
		if (!existStep){
			resultMessage.setRet_message("该工程没有《<span style='color:red'>"+StepEnum.getNameByCode(fallbackApply.getFallbackStepId())+"</span>》这个节点！请选择正确的节点回退");
			return resultMessage;
		}

		boolean beforeCurrentStep = isBeforeCurrentStep(fallbackApply.getFallbackStepId(),origStepId,project);
		if (!beforeCurrentStep){
			resultMessage.setRet_message("《"+StepEnum.getNameByCode(fallbackApply.getFallbackStepId())+"》在《"+StepEnum.getNameByCode(origStepId)+"》之后！不能回退");
			return resultMessage;
		}

		try {
			resultMessage.setRet_type(Constants.SUCCESS_CODE);
			//判断工程是否已存在收款
			List<CashFlow> list = cashFlowDao.queryCashFlowByProjIdType(fallbackApply.getProjId(), ARFlagEnum.RECEIVE_ACCOUNT.getValue(),null);
			//已有收款不可退单
			if(list!=null && list.size()>0 && StepEnum.CONTRACT_END.getValue().equals(fallbackApply.getFallbackStepId())){
				resultMessage.setRet_message("该工程已存在收款，请先在收款登记处取消收款再退单！");
				return resultMessage;
			}

			fallbackApplyService.saveFallbackApply(fallbackApply);
			resultMessage.setRet_message("数据保存成功!");
			return resultMessage;


		} catch (ExpressException e) {
			e.printStackTrace();
			resultMessage.setRet_type(Constants.CALL_ERROR_CODE);
			resultMessage.setRet_message(e.getMessage());
			return resultMessage;
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage.setRet_type(Constants.FAIL_CODE);
			resultMessage.setRet_message("数据保存失败");
			return resultMessage;
		}
	}
	



	/**
	* @Description: 该工程是否有回退到的步骤
	* @author zhangnx
	* @date 2019/8/24 20:30
	*/
	public boolean isExistStep(String backStepId,Project project){

		if (StepEnum.CONTRACT_END.getValue().equals(backStepId)){//终止
			return true;
		}

		if (project==null) return false;

		WorkFlow workFlow = workFlowService.queryWorkFlowCode(project.getCorpId(), project.getProjectType(), project.getContributionMode(), WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
		if (workFlow == null ||StringUtil.isBlank(workFlow.getWorkFlowCode())) return false;

		List<String> stepIds = Arrays.asList(workFlow.getWorkFlowCode().split(","));
		if (stepIds!=null && stepIds.contains(backStepId)){
			return true;
		}
		return false;
	}




	/**
	* @Description: 回退步骤是否在当前步骤之前
	* @author zhangnx
	* @date 2019/8/24 20:29
	*/
	public boolean isBeforeCurrentStep(String backStepId,String origStepId,Project project){

		if (StepEnum.CONTRACT_END.getValue().equals(backStepId)){//终止
			return true;
		}

		if (project==null) return false;

		WorkFlow workFlow = workFlowService.queryWorkFlowCode(project.getCorpId(), project.getProjectType(), project.getContributionMode(), WorkFlowTypeEnum.MAIN_PROGRESS.getValue());
		if (workFlow == null ||StringUtil.isBlank(workFlow.getWorkFlowCode())) return false;
		String[] split = workFlow.getWorkFlowCode().split(",");
		int backPosition = ArraysUtil.searchPosition(split,backStepId);
		int origPosition = ArraysUtil.searchPosition(split, origStepId);
		if (backPosition==-1 || origPosition==-1) return false;

		if (backPosition>origPosition){
			return false;
		}
		return true;
	}




	/**
	 * 
	 * @author fuliwei
	 * @createTime 2017年12月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryMenuBackSet")
	@ResponseBody
	public String queryMenuBackSet(String menuId,String projectType,String contributionMode,String corpId,String projStatusId){
		try {
			List<MenuBackSet> list = queryMenuBackSetList(projStatusId, menuId, corpId, projectType, contributionMode);

			if(list!=null && list.size()>0){
				return Constants.OPERATE_RESULT_SUCCESS;
			}
			return Constants.OPERATE_RESULT_FAILURE;

		} catch (Exception e) {
			logger.error("查询失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}



	/**
	* @Description: 查询回退节点
	* @author zhangnx
	* @date 2019/8/25 11:00
	*/
	public List<MenuBackSet>  queryMenuBackSetList(String projStatusId,String menuId,String corpId,String projectType,String contributionMode){
		FallbackApplyReq req=new FallbackApplyReq();

		if(StringUtil.isNotBlank(projStatusId)){//根据工程状态找到相应步骤的菜单ID，获取相应菜单下的回退配置
			String stepMenuId = menuBackSetService.findMenuIdByProjStatusId(projStatusId);
			if(StringUtil.isNotBlank(stepMenuId)){
				menuId = stepMenuId;
			}
		}
		req.setMenuId(menuId);
		req.setProjectType(projectType);
		req.setContributionCode(contributionMode);
		req.setCorpId(corpId);
		return menuBackSetService.queryRollBackNode(req);
	}


	
}