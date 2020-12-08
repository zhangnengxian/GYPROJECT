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

import cc.dfsoft.project.biz.base.inspection.dto.PeWeldLineInpectReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPEWLIReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.PeWeldLineInspect;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.MeltConnectTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.PeWeldLineInspectService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * PE管焊缝检查
 * @author liaoyq
 * @todo
 */
@Controller
@RequestMapping(value="peWeldLineInspect")
public class PeWeldLineInspectController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(WeldLineInpectController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	
	@Resource
	PeWeldLineInspectService peWeldLineInspectService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","peWeldLineInspect");//controller路径名
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("meltConnectTypeEnum",MeltConnectTypeEnum.values()); 	//热熔、电熔连接类型
		
		modelAndView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelAndView.addObject("constructionQcPost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelAndView.addObject("projectLeaderPost",PostTypeEnum.CU_PM.getValue());//项目负责人
		modelAndView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue());//现场代表
		modelAndView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue());//监理员
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelAndView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelAndView.setViewName("inspection/peWeldLineInspect");
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
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getValue());
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
	 * 保存签字信息（先记录后报验）
	 * @return
	 */
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getMessage());
			String pcId = proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_PE_WELD_LINE_INPECT);
			//回写记录表中pcId
			peWeldLineInspectService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			return pcId;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("pe管焊缝检查 签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询pe管焊缝检查记录列表
	 * @return
	 */
	@RequestMapping(value="/queryPeWeldLineInpect")
	@ResponseBody
	public Map<String, Object> queryPeWeldLineInpect(HttpServletRequest request , PeWeldLineInpectReq peWeldLineInpectReq){
		try{
			peWeldLineInpectReq.setSortInfo(request);
			//根据pe管焊缝检查记录信息
			Map<String,Object> map = peWeldLineInspectService.queryPeWeldLineInpect(peWeldLineInpectReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("pe管焊缝检查信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 保存记录信息(先记录后报验)
	 * @return
	 */
	@RequestMapping(value="/savePeWeldLineInpect")
	@ResponseBody
	public String savePeWeldLineInpect(@RequestBody ProjectCheckListPEWLIReq checkListPEWLIReq){
		try{
			peWeldLineInspectService.savePeWeldLineInpect(checkListPEWLIReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch (Exception e){
			logger.error("pe管焊缝检查记录区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 保存PE管焊缝检查记录(不包括图片)
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListPEWLIReq checkListReq){ 
		try {
			peWeldLineInspectService.saveRecords(checkListReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PE管焊缝检查检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	/**
	 * 查询PE管焊缝检查记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , PeWeldLineInpectReq req){
		try{
			req.setSortInfo(request);
			//根据PE管焊缝检查记录信息
			Map<String,Object> map = peWeldLineInspectService.queryRecords(req);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("PE管焊缝检查信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public PeWeldLineInspect viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		PeWeldLineInspect record = peWeldLineInspectService.viewRecordById(id);
		return record;
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
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  PeWeldLineInspect record){
		try {
			return peWeldLineInspectService.saveRecord(record);
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}  catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("PE管焊缝检查记录保存失败！",e);
			return null;
		}
	}
	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param pewi
	 * @return
	 */
	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true) PeWeldLineInspect pewi){
		try {
			peWeldLineInspectService.deleteRecordById(pewi.getPeWliId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("PE管焊缝检查记录删除失败！",e);
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
			peWeldLineInspectService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
}
