package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceApplyService;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
import edu.emory.mathcs.backport.java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fr.stable.collections.utils.reflect.Method;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl.NamespaceWildcardIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Map;
/**
 * 分部验收申请
 * @author fuliwei
 *
 */
import java.util.concurrent.locks.Lock;
@Controller
@RequestMapping(value="/divisionalAcceptanceApply")
public class DivisionalAcceptanceApplyController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DivisionalAcceptanceApplyController.class);
	/**锁*/
	static ReentrantLock lock = new ReentrantLock();
	/**分期申请服务接口*/
	@Resource
	DivisionalAcceptanceApplyService  divisionalAcceptanceApplyService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	OperateRecordService operateRecordService;
	
	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("notThrough",Constants.MODULE_CODE_COMPLETE);
		modelView.addObject("stepId",StepOutWorkflowEnum.DIVISIONAL_ACCEPTANCE_AUDIT.getValue());	
		modelView.addObject("sysDate",manageRecordService.getDatabaseDate());	
		modelView.setViewName("complete/divisionalAcceptanceApply");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年6月27日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage(String projId,String projectType,String corpId,String menuId) {
		LoginInfo info = SessionUtil.getLoginInfo();
		//规则corpId+menuId
		if(StringUtil.isBlank(corpId)){
			corpId = info.getCorpId();
		}
		String key = corpId+"_"+menuId;
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "";
		if(obj !=null){
			viewUrl = obj.toString();
		}else{
			viewUrl = "divisionalAcceptanceApplyRight";
		}
		ModelAndView modelview = new ModelAndView();
		//根据不同的配置，加载不同的页面信息（form表单中的不同处）
		modelview.setViewName("complete/"+viewUrl);
		return modelview;
	}
	
	/**
	 * 工程列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq req){
		try {
			req.setSortInfo(request);
			Map<String,Object> map=divisionalAcceptanceApplyService.queryProject(req);
			return map;
		} catch (Exception e) {
			logger.error("工程列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewDivisionalAcceptanceApply")
	@ResponseBody
	public DivisionalAcceptanceApply viewDivisionalAcceptanceApply(@RequestParam(required=true) String projId,String daaId) throws ParseException {
		return divisionalAcceptanceApplyService.findById(projId, daaId);
	}
	
	/**
	 * 保存分部验收申请
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/saveDivisionalAcceptanceApply")
	@ResponseBody
	public String saveDivisionalAcceptanceApply(@RequestBody(required = true) DivisionalAcceptanceApply divisionalAcceptanceApply){
		try{
			divisionalAcceptanceApplyService.saveDivisionalAcceptanceApply(divisionalAcceptanceApply);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("保存分部验收申请失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 推送分部验收申请到审核中
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/pushDivisionalAcceptanceApply")
	@ResponseBody
	public String pushDivisionalAcceptanceApply(@RequestBody(required = true) String  daaId){
		try{
			divisionalAcceptanceApplyService.pushDivisionalAcceptanceApply(daaId);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("保存分部验收申请失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 分部验收申请列表查询
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryDivisionalAcceptance")
	@ResponseBody
	public Map<String,Object> queryDivisionalAcceptance(HttpServletRequest request,DivisionalAcceptanceReq req){
		try {
			req.setSortInfo(request);
			return divisionalAcceptanceApplyService.queryDivisionalAcceptanceApply(req);
		} catch (Exception e) {
			logger.error("分部验收申请列表查询失败！",e);
			return null;
		}
	}


	/**
	 *  @MethodDesc: 审核记录查询
	 * @Author zhangnx
	 * @Date 2019/09/18 18:21
	 */
	@RequestMapping(value = "/queryTodoer")
	@ResponseBody
	public String queryTodoer(String businessOrderId){
		try {
			String todoer=operateRecordService.queryTodoer(businessOrderId);
			return todoer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 统计分部验收申请记录数
	 *creater wang.hui.jun
	 *@version 2019年11月7日
	 *@param projId
	 *@return null (查询无记录时返回null)
	 *@throws Exception
	 */
	@RequestMapping(value="/countDivisonalAcceptanceApplyRecord",method = RequestMethod.POST)
	@ResponseBody
	public  Integer  countDivisonalAcceptanceApplyRecord(@RequestParam(required = true) String projId) throws  Exception {
		// 加锁,高并发时需要排队执行，否则会造成业务混乱，只适用单机部署情况，多机部署需用分布式锁。
		lock.lock();
		try {
			Thread.sleep(2000); //睡眠两秒
			return divisionalAcceptanceApplyService.countDivisonalAcceptanceRecord(projId);
		} catch (Exception e) {
			throw new Exception(); 
		} finally {
			// 释放锁
			lock.unlock();
		}
	
	}
	
}
