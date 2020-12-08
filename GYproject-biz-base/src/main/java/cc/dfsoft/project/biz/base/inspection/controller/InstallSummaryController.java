package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.inspection.dto.InstallSummaryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListISReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.InstallSummary;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.InstallSummaryService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 安装汇总
 * @author liaoyq
 */
@Controller
@RequestMapping(value="/installSummary")
public class InstallSummaryController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(InstallSummaryController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	
	@Resource
	InstallSummaryService installSummaryService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","installSummary");//controller路径名
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.INSTALL_SUMMARY.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("constructionQaePost",PostTypeEnum.MANAGEMENTQAE.getValue());//质保师
		modelAndView.addObject("constructionQcPost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelAndView.setViewName("inspection/installSummary");
		return modelAndView;
	}
	
	/**
	 * 查询报验列表
	 * @param request
	 * @param proCheckListReq
	 * @return
	 */
	@RequestMapping(value="/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.INSTALL_SUMMARY.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 报验单详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist checklist = proCheckListService.viewProjectCheckList(id);
		return checklist;
	}
	/**
	 * 保存签字信息
	 * @return
	 */
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.INSTALL_SUMMARY.getMessage());
			String pcId = proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_INSTALL_SUMMARY);
			//回写记录表中pcId
			installSummaryService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			return pcId;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch (Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("安装汇总 签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询安装汇总记录列表
	 * @return
	 */
	@RequestMapping(value="/queryInstallSummary")
	@ResponseBody
	public Map<String, Object> queryInstallSummary(HttpServletRequest request , InstallSummaryReq installSummaryReq){
		System.err.println("queryEclectrodeRecords");
		try{
			installSummaryReq.setSortInfo(request);
			//根据安装汇总记录信息
			Map<String,Object> map = installSummaryService.queryInstallSummary(installSummaryReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("安装汇总记录信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存安装汇总记录(不包括图片)
	 * @author 张萌
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListISReq checkListReq){ 
		try {
			installSummaryService.saveRecords(checkListReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("安装汇总检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	
	/**
	 * 查询安装汇总记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , InstallSummaryReq req){
		try{
			req.setSortInfo(request);
			//根据安装汇总记录信息
			Map<String,Object> map = installSummaryService.queryRecords(req);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("安装汇总信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public InstallSummary viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		InstallSummary is = installSummaryService.viewRecordById(id);
		return is;
	}
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param request
	 * @param preservative
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  InstallSummary is){
		try {
			return installSummaryService.saveRecord(is);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("安装汇总记录保存失败！",e);
			return null;
		}
	}
	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param request
	 * @param id 记录ID
	 * @return
	 */
	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true) InstallSummary is){
		try {
			installSummaryService.deleteRecordById(is.getIsId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("安装汇总记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 完成报验单
	 * 修改flag
	 * @param checkList
	 * @return
	 */
	@RequestMapping(value="/completed")
	@ResponseBody
	public String completed(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			proCheckListService.updateFlag(checkList.getPcId(),checkList.getFlag());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
