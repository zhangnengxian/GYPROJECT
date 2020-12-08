package cc.dfsoft.project.biz.base.project.controller;

import java.math.BigDecimal;
import java.util.List;
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

import cc.dfsoft.project.biz.base.baseinfo.dao.TimeLimitDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.DelayReason;
import cc.dfsoft.project.biz.base.baseinfo.entity.TimeLimit;
import cc.dfsoft.project.biz.base.baseinfo.enums.TimeLimitTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.DelayReasonService;
import cc.dfsoft.project.biz.base.baseinfo.service.SystemSetService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ApplyDelayReq;
import cc.dfsoft.project.biz.base.project.entity.ApplyDelay;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ApplyDelayService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.settlement.dao.SettlementDeclarationDao;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 延期申请
 * @author fuliwei
 *
 */

@Controller
@RequestMapping(value="/applyDelay")
public class ApplyDelayController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ApplyDelayController.class);
	
	/**
	 * 延期申请
	 */
	@Resource
	ApplyDelayService applyDelayService;
	
	/**延期原因*/
	@Resource
	DelayReasonService delayReasonService;
	
	/**设计出图*/
	@Resource
	DesignInfoService designInfoService;
	
	/**系统设置*/
	@Resource
	SystemSetService systemSetService;
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	/**时限Dao*/
	@Resource
	TimeLimitDao timeLimitDao;
	/**终审Dao*/
	@Resource
	SettlementDeclarationDao settlementDeclarationDao;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(String stepId,String projId){
		ModelAndView modelView=new ModelAndView();
		//StepEnum st=StepEnum.valueof(stepId);
		
		//如果是设计出图 查设计表
		if(StepEnum.DESIGN_DRAWING.getValue().equals(stepId)){
			DesignInfo di=designInfoService.queryById(projId);
			modelView.addObject("originalPeriod", di.getAcquisitionDays());
		}else if(StepEnum.QUALITIES_JUDGEMENT.getValue().equals(stepId)){
			Project pro = projectService.viewProject(projId);
			String timeLimitType = null;
			if(pro.getSubmitAmount().compareTo(new BigDecimal(50000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT13.getValue();
			}else if(pro.getSubmitAmount().compareTo(new BigDecimal(100000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT14.getValue();
			}else if(pro.getSubmitAmount().compareTo(new BigDecimal(500000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT15.getValue();
			}else if(pro.getSubmitAmount().compareTo(new BigDecimal(1000000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT16.getValue();
			}else{
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT17.getValue();
			}
			TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
			if(null!=timeLimit){
				if(timeLimit.getTlDuration().compareTo(BigDecimal.ZERO)>0){
					long days = timeLimit.getTlDuration().setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
					modelView.addObject("originalPeriod",days);
				}
			}
			
		}else if(StepEnum.REPORT_DONE_CONFIRM.getValue().equals(stepId)){
			SettlementDeclaration con = settlementDeclarationDao.findByProjId(projId);
			String timeLimitType = null;
			if(con.getSendDeclarationCost().compareTo(new BigDecimal(50000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT13.getValue();
			}else if(con.getSendDeclarationCost().compareTo(new BigDecimal(100000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT14.getValue();
			}else if(con.getSendDeclarationCost().compareTo(new BigDecimal(500000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT15.getValue();
			}else if(con.getSendDeclarationCost().compareTo(new BigDecimal(1000000))<0){
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT16.getValue();
			}else{
				timeLimitType = TimeLimitTypeEnum.TIME_LIMIT17.getValue();
			}
			TimeLimit  timeLimit = timeLimitDao.findByType(timeLimitType);
			if(null!=timeLimit){
				if(timeLimit.getTlDuration().compareTo(BigDecimal.ZERO)>0){
					long days = timeLimit.getTlDuration().setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
					modelView.addObject("originalPeriod",days);
				}
			}
		}else{
			SystemSet systemSet=systemSetService.querySystemSetByStepId(stepId);
			modelView.addObject("originalPeriod", systemSet.getTimeSpan());
		}
		
		List<DelayReason> list=delayReasonService.findDelayReason();
		modelView.addObject("delayReason", list);
		modelView.addObject("stepId", stepId);
		modelView.setViewName("common/applyDelayPop");
		return modelView;
	}
	
	
	
	/**
	 * 延期保存
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveApplyDelay")
	@ResponseBody
	public String saveApplyDelay(@RequestBody ApplyDelay applyDelay){
		try {
			applyDelayService.saveApplyDelay(applyDelay);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	
	@RequestMapping(value = "/findApplyDelay")
	@ResponseBody
	public List<ApplyDelay> findApplyDelay(String stepId,String projectId){
		try {
			return applyDelayService.findADelayByStepId(stepId,projectId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 延期申请列表
	 * @author fuliwei
	 * @createTime 2017年8月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryApplyDelay")
	@ResponseBody
	public Map<String,Object> queryApplyDelay(HttpServletRequest request,ApplyDelayReq req){
		try {
			req.setSortInfo(request);
			Map<String,Object> map=applyDelayService.queryApplyDelay(req);
			return map;
		} catch (Exception e) {
			logger.error("延期申请列表查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年9月1日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	public ApplyDelay findById(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			return applyDelayService.findById(id);
		} catch (Exception e) {
			logger.error("延期申请查询详述失败！", e);
			return null;
		}
	}
	
	
}


