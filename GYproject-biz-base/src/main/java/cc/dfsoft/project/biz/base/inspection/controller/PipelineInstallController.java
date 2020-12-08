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

import cc.dfsoft.project.biz.base.inspection.dto.PipelineInstallReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPLIReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.InspectionClumEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.PipeLineInstallService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 管道安装
 * @author liaoyq
 */

@Controller
@RequestMapping(value="/pipelineInstall")
public class PipelineInstallController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PipelineInstallController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	/**管道安装业务接口*/
	@Resource
	PipeLineInstallService pipeLineInstallService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","pipelineInstall");//controller路径名
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.PIPELINE_INSTALL.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("inspectionClumEnum",InspectionClumEnum.values()); //报检状态
		modelAndView.addObject("welderPost",PostTypeEnum.WELDER.getValue()); //焊工
		modelAndView.addObject("technicalLeaderPost",PostTypeEnum.CONSTRUCTION.getValue()); //技术负责人
		modelAndView.addObject("auditOfficerPost",PostTypeEnum.CU_PM.getValue()); //审核负责人
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
				
		modelAndView.setViewName("inspection/pipelineInstall");
		return modelAndView;
	}
	
	/**
	 * 工程报验单列表查询
	 * @param request
	 * @param proCheckListReq
	 * @return
	 */
	@RequestMapping(value="/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.PIPELINE_INSTALL.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @return 
	 */
	@RequestMapping(value="viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewPipelineInstall(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=proCheckListService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 查询管道安装记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , PipelineInstallReq pipelineInstallReq){
		try{
			pipelineInstallReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = pipeLineInstallService.queryRecords(pipelineInstallReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("管道安装记录查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存管道安装记录信息
	 * @return
	 */
	@RequestMapping(value="saveRecords")
	@ResponseBody
	public String saveRecords(@RequestBody(required = true) ProjectCheckListPLIReq checkListReq){
		try{
			pipeLineInstallService.saveRecords(checkListReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存管道安装记录失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 保存签字区信息
	 */
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.PIPELINE_INSTALL.getMessage());
			String pcId= proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_PIPELINE_INSTALL);
			//回写记录表中pcId
			pipeLineInstallService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("管道安装签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 管道安装记录ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public PipeLineInstall viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		PipeLineInstall pli= pipeLineInstallService.viewRecordById(id);
		return pli;
	}
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @param request
	 * @param preservative
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  PipeLineInstall pli){
		try {
			return pipeLineInstallService.saveRecord(pli);
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
			logger.error("管道安装记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true)  PipeLineInstall pli){
		try {
			pipeLineInstallService.deleteRecordById(pli.getPliId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("管道安装记录删除失败！",e);
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
			return proCheckListService.updateFlag(checkList.getPcId(),checkList.getFlag());
			//return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveSignNotice")
	@ResponseBody
	public void saveSignNotice(@RequestBody(required = true) String cwId){
		try {
			pipeLineInstallService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
	
}
