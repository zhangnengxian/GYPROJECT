package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import cc.dfsoft.project.biz.base.inspection.dto.DerustingPreservativeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PreservativeReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListAntisepsisSpecReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListDerustingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.AntisepsisSpec;
import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.DpResultEnum;
import cc.dfsoft.project.biz.base.inspection.enums.PreservativeTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.AntisepsisSpecService;
import cc.dfsoft.project.biz.base.inspection.service.DerustingPreservativeService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 防腐工序
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/preservative")
public class PreservativeController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(PreservativeController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**除锈防腐记录*/
	@Resource
	DerustingPreservativeService  derustingPreservativeService;
	/**除锈防腐检查规格*/
	@Resource
	AntisepsisSpecService antisepsisSpecService;
	@Resource
	SignatureService signatureService;
	
	@Resource
	SignNoticeService signNoticeService;
	/**
	 * 防腐工序主页面
	 * @return ModelAndView
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("checkType",ProjectChecklistTypeEnum.PRESERVATIVE.getValue());//除锈记录
		modelView.addObject("actionName","preservative");//controller路径名
		modelView.addObject("dpResult",DpResultEnum.values());//检查结果
		modelView.addObject("preservativeTypeEnum",PreservativeTypeEnum.values());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("CUST_REPRESENTPOST",PostTypeEnum.BUILDER.getValue());//甲方
		modelView.addObject("CONSTRUCTIONPOST",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("SUJGJPOST",PostTypeEnum.SUJGJ.getValue()); //监理
		
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelView.setViewName("inspection/preservative");
		return modelView;
	}
	/**
	 * 右侧页面
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2016-07-25
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryCheckList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.PRESERVATIVE.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param id 工程id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewProjectCheckList")
	@ResponseBody
	public ProjectChecklist viewProjectCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			ProjectChecklist checklist = projectChecklistService.viewProjectCheckList(id);
			return checklist;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 防腐报验单保存
	 * @author fuliwei
	 * @createTime  2016-7-27
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/savePreservative")
	@ResponseBody
	public String savePreservative(HttpServletRequest request, ProjectChecklist checkList){
		try {
			//增加报验单工序
			checkList.setProcess(ProjectChecklistTypeEnum.PRESERVATIVE.getMessage());
			String pcId= projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_PRESERVATIVE);
			/*if(pcId!=null){
				derustingPreservativeService.updatePcIdById(checkList.getRecordsId(),pcId);
			}*/
			return pcId;
		} catch (Exception e) {
			logger.error("防腐工序报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 除锈防腐列表查询
	 * @author fuliwei
	 * @createTime 2016-7-27
	 * @param dpQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryDerusting")
	@ResponseBody
	public Map<String, Object> queryPressure(HttpServletRequest request,DerustingPreservativeQueryReq dpQueryReq) {
		try {
			dpQueryReq.setSortInfo(request);
		    return derustingPreservativeService.queryDerusting(dpQueryReq);
		} catch (Exception e) {
			logger.error("除锈防腐列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存除锈防腐
	 * @author fuliwei
	 * @createTime 2016-7-27
	 * @param list
	 * @return String
	 */
	@RequestMapping(value = "/savePreservativeRecord")
	@ResponseBody
	public String savePreservativeRecord(@RequestBody ProjectCheckListDerustingReq req){ 
		try {
			derustingPreservativeService.saveDerustingRecord(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存除锈防腐结果失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 除锈防腐检查规格列表查询
	 * @author zhangjj
	 * @createTime 2016-8-23
	 * @param pcId 报验单id,asType 规格类型 暂时不用
	 * @return List<AntisepsisSpec>
	 */
	@RequestMapping(value = "/queryAntSpec")
	@ResponseBody
	public List<AntisepsisSpec> queryAntSpec(String pcId,String asType){
		 try {
			return antisepsisSpecService.queryAntSpecByPcId(pcId, asType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 保存除锈防腐检查规格
	 * @author zhangjj
	 * @createTime 2016-8-23
	 * @param list 
	 */
	@RequestMapping(value = "/saveAntSpecList")
	@ResponseBody
	public String saveAntSpecList(@RequestBody ProjectCheckListAntisepsisSpecReq req){
		 try {
			 antisepsisSpecService.saveAntSpecList(req);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 分页查询防腐记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param request
	 * @param preservativeReq
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , PreservativeReq preservativeReq){
		try{
			preservativeReq.setSortInfo(request);
			//防腐记录信息
			Map<String,Object> map = derustingPreservativeService.queryRecords(preservativeReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("防腐记录信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存防腐记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListDerustingReq checkListDReq){ 
		try {
			derustingPreservativeService.saveDerustingPreservativeRecord(checkListDReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("防腐记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	/**
	 * 保存防腐记录签字区
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param checkList
	 * @return pcId
	 */
	@RequestMapping(value="saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true) ProjectChecklist checkList){
		try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.PRESERVATIVE.getMessage());
			String pcId= projectChecklistService.saveProCheckList(checkList, Constants.MODULE_CODE_PRESERVATIVE);
			//回写记录表中pcId
			derustingPreservativeService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId, checkList.getProjId(), checkList.getProjNo());
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
			logger.error("防腐记录  签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 防腐记录ID
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public DerustingPreservative viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		DerustingPreservative derustingPreservative = derustingPreservativeService.viewRecordById(id);
		return derustingPreservative;
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
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  DerustingPreservative preservative){
		try {
			return derustingPreservativeService.saveRecord(preservative);
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
			logger.error("防腐记录保存失败！",e);
			return null;
		}
	}
	/**
	 * 根据记录ID删除记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value="/deleteRecord/{pcDesId}")
	@ResponseBody
	public String deleteRecordById(@RequestBody(required = true)  DerustingPreservative preservative,@PathVariable(value="pcDesId") String pcDesId){
		try {
			derustingPreservativeService.deleteRecordById(preservative.getDpId());
			signatureService.deleteImgByBIdAndMenuId(preservative.getDpId(), preservative.getMenuId());
			signNoticeService.deleteByBsId(preservative.getDpId(),pcDesId);   //删除签字通知
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("防腐记录删除失败！",e);
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
			return projectChecklistService.updateFlag(checkList.getPcId(),checkList.getFlag());
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
			derustingPreservativeService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}

}
