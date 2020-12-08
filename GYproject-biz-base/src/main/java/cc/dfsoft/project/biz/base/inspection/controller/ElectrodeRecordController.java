package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListERReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.InspectionClumEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ElectrodeRecordService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Map;

/**
 * 焊条领用
 * @author liaoyq
 */
@Controller
@RequestMapping(value="/electrodeRecord")
public class ElectrodeRecordController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ElectrodeRecordController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	
	@Resource
	ElectrodeRecordService electrodeRecordService;
	
	@Resource
	SignatureService signatureService;
	
	@Resource
	SignNoticeService signNoticeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","electrodeRecord");//controller路径名
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.ELECTRODE_RECORD.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("inspectionClumEnum",InspectionClumEnum.values()); //报检状态
		modelAndView.addObject("WELDER",PostTypeEnum.WELDER.getValue()); //焊工
		modelAndView.addObject("CONSTRUCTION",PostTypeEnum.CONSTRUCTION.getValue()); //施工员
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
		modelAndView.addObject("dataAdmin",PostTypeEnum.DATAMANAGER.getValue());	//数据管理员

		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理

		modelAndView.setViewName("inspection/electrodeRecord");
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
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.ELECTRODE_RECORD.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 查询焊条领用报验详述
	 * @return 
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewElectrodeRecord(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			ProjectChecklist checklist = proCheckListService.viewProjectCheckList(id);
			return checklist;
		} catch (Exception e) {
			logger.error("焊条零用报验单查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 查询焊条领用记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryEclectrodeRecords(HttpServletRequest request , ElectrodeRecordReq electrodeRecordReq){
		try{
			electrodeRecordReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = electrodeRecordService.queryRecords(electrodeRecordReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("焊条领用记录信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存记录区信息
	 * @return
	 */
	@RequestMapping(value="/saveRecords")
	@ResponseBody
	public String saveElectrodeRecord(@RequestBody ProjectCheckListERReq checkListERReq){
		try{
			electrodeRecordService.saveElectrodeRecords(checkListERReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存焊条领用记录失败！",e);
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
			checkList.setProcess(ProjectChecklistTypeEnum.ELECTRODE_RECORD.getMessage());
			String pcId= proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_ELECTRODE_RECORD);
			//回写记录表中pcId
			electrodeRecordService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("焊条领用签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
	
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-8-29
	 * @param id 焊条领用ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public ElectrodeRecord viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		ElectrodeRecord electrodeRecord = electrodeRecordService.viewRecordById(id);
		return electrodeRecord;
	}
	
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  ElectrodeRecord record){
		try {
			return electrodeRecordService.saveRecord(record);
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
			logger.error("焊条领用保存失败！",e);
			return null;
		}
	}
	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true) ElectrodeRecord record){
		try {
			electrodeRecordService.deleteRecordById(record.getErId());
			signatureService.deleteImgByBIdAndMenuId(record.getErId(), record.getMenuId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("焊条领用删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**@author wang.hui.jun
	 * 报验列表删除（根据列表pcId删除）.此方法是公用方法
	 * 删除报验列表签字通知
	 * @param 
	 * @return
	 */
	@RequestMapping("/deleteList/{pcDesId}")
	@ResponseBody
	public String deleteList(String pcId,@PathVariable(value="pcDesId") String pcDesId){
		try{
			proCheckListService.deleteListById(pcId);  //删除报验列表
			signNoticeService.deleteByBsId(pcId,pcDesId);   //删除签字通知
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("报验列表删除失败！",e);
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
			electrodeRecordService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}

	/**
	 * 获取系统时间
	 * @return
	 */
	@RequestMapping(value="/getSysDate")
	@ResponseBody
	public Date getSysDate(HttpServletRequest request){
		try {
			return proCheckListService.getSysDate();
		} catch (Exception e) {
			logger.error("查询系统时间失败！",e);
			return null;
		}
	}
	
}
