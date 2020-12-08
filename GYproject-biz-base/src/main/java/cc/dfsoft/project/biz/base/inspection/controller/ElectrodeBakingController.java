package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.inspection.dto.ElectrodeBakingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListEBReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeBaking;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.InspectionClumEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ElectrodeBakingService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 焊条烘烤
 * @author liaoyq
 * @todo 后台逻辑未完成
 */
@Controller
@RequestMapping(value="/electrodeBaking")
public class ElectrodeBakingController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ElectrodeBakingController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	/**焊条烘烤服务类*/
	@Resource
	ElectrodeBakingService electrodeBakingService;
	
	@Resource
	SignatureService signatureService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","electrodeBaking");//controller路径名
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.ELECTRODE_BAKING.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("inspectionClumEnum",InspectionClumEnum.values()); //报检状态
		modelAndView.addObject("WELDER",PostTypeEnum.WELDER.getValue()); //焊工人
		modelAndView.addObject("CONSTRUCTION",PostTypeEnum.CONSTRUCTION.getValue()); //施工员
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		
		modelAndView.setViewName("inspection/electrodeBaking");
		return modelAndView;
	}
	/**
	 * 查询焊条烘烤报验列表
	 * @param request
	 * @param proCheckListReq
	 * @return
	 */
	@RequestMapping(value="/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		try {
			proCheckListReq.setSortInfo(request);
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.ELECTRODE_BAKING.getValue());
			Map<String,Object> map= proCheckListService.queryPrProjectChecklist(proCheckListReq);
	        return map;
		} catch (Exception e) {
			logger.error("工程报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 根据报验单ID 查询焊条烘干报验详述
	 * @return
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewElectrodeBaking(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist projectChecklist =  proCheckListService.viewProjectCheckList(id);
		return projectChecklist;
	}
	/**
	 * 查询焊条烘干记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryEclectrodeRecords(HttpServletRequest request , ElectrodeBakingReq ebReq ){
		try{
			ebReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = electrodeBakingService.queryRecords(ebReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("焊条烘干记录信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存记录区信息
	 * @return
	 */
	@RequestMapping(value="/saveRecords")
	@ResponseBody
	public String saveElectrodeRecord(@RequestBody ProjectCheckListEBReq checkListEBReq){
		try{
			electrodeBakingService.saveRecords(checkListEBReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存焊条烘烤记录失败！",e);
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
			checkList.setProcess(ProjectChecklistTypeEnum.ELECTRODE_BAKING.getMessage());
			String pcId= proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_ELECTRODE_BAKING);
			//回写记录表中pcId
			electrodeBakingService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
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
			logger.error("焊条烘烤签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 焊条烘烤记录ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public ElectrodeBaking viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		ElectrodeBaking eb = electrodeBakingService.viewRecordById(id);
		return eb;
	}
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @param request
	 * @param eb
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  ElectrodeBaking eb){
		try {
			return electrodeBakingService.saveRecord(eb);
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
			logger.error("焊条烘烤记录保存失败！",e);
			return null;
		}
	}
	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param eb
	 * @return
	 */
	@RequestMapping(value="/deleteRecord")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true)  ElectrodeBaking eb){
		try {
			electrodeBakingService.deleteRecordById(eb.getEbId());
			signatureService.deleteImgByBIdAndMenuId(eb.getEbId(), eb.getMenuId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("焊条烘烤记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	@RequestMapping(value="/saveElectrodeBakings")
	@ResponseBody
	public String saveElectrodeBakings(@RequestBody ProjectCheckListEBReq checkListERReq){
		try {	
			electrodeBakingService.saveElectrodeBaking(checkListERReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("保存焊条烘烤记录失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 查询焊条烘烤记录列表
	 * @return
	 */
	@RequestMapping(value="/queryEclectrodeBakings")
	@ResponseBody
	public Map<String, Object> queryEclectrodeBakings(HttpServletRequest request , ElectrodeBakingReq electrodeBakingReq){
		try{
			electrodeBakingReq.setSortInfo(request);
			//根据条件查询记录信息
			Map<String,Object> map = electrodeBakingService.queryEclectrodeBakings(electrodeBakingReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("记录信息查询失败！",e);
			return null;
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
			electrodeBakingService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
