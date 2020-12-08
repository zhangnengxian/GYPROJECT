package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListSTReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.StrengthTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.entity.StrengthTest;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.StrTestPipeTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.StrTestTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.inspection.service.StrengthTestService;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 强度测试
 * @author liaoyq
 */
@Controller
@RequestMapping(value="/strengthTest")
public class StrengthTestController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(StrengthTestController.class);
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	
	@Autowired
	JointAcceptanceService jointAcceptanceService;
	
	@Resource
	StrengthTestService strengthTestService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","strengthTest");//controller路径名
		modelAndView.addObject("StrTestPipeTypeEnum",StrTestPipeTypeEnum.values()); //管道类型枚举
		modelAndView.addObject("StrTestTypeEnum",StrTestTypeEnum.values()); //试验类型枚举
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.STRENGTH_TEST.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("fieldsRepresentPost",PostTypeEnum.BUILDER.getValue());//现场代表
		modelAndView.addObject("projectLeaderPost",PostTypeEnum.PROJECT_LEADER.getValue());//项目负责人
		
		modelAndView.addObject("builderPost",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelAndView.addObject("constructionQcPost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());//质检员
		modelAndView.addObject("saftyOfficerPost",PostTypeEnum.SAFTYOFFICER.getValue());//安全员
		modelAndView.addObject("CU_PM",PostTypeEnum.CU_PM.getValue()); 					  //项目经理
		
		modelAndView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue());//监理员
		
		modelAndView.addObject("projStatus",ProjStatusEnum.TO_CONSTRUCTION.getValue());//待施工的状态
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); //当前登录人职务
		modelAndView.addObject("dataAdmin",PostTypeEnum.DATAMANAGER.getValue());  //数据处理员

		modelAndView.setViewName("inspection/strengthTest");
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
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.STRENGTH_TEST.getValue());
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
        Integer flag = checkList.getFlag();
        try{
			//自动保存施工工序process
			checkList.setProcess(ProjectChecklistTypeEnum.STRENGTH_TEST.getMessage());
			String pcId = proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_STRENGTH_TEST);
			//回写记录表中pcId
			strengthTestService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId,checkList.getProjId(),checkList.getProjNo());
			jointAcceptanceService.finshSignCreateNotice(checkList.getProjId(), checkList.getPcId(), null, StepEnum.DURING_CONSTRUCTION.getValue(),
					SignDataTypeEnum.STRENGTH_TEST.getValue());
			return pcId;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", ExceptionUtil.getMessage(e));
			return "already";
		}catch (Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("强度试验 签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询强度试验记录记录列表
	 * @return
	 */
	@RequestMapping(value="/queryStrengthTest")
	@ResponseBody
	public Map<String, Object> queryStrengthTest(HttpServletRequest request , StrengthTestReq StrengthTestReq){
		try{
			StrengthTestReq.setSortInfo(request);
			//根据强度试验记录记录信息
			Map<String,Object> map = strengthTestService.queryStrengthTest(StrengthTestReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("强度试验信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存强度试验记录(不包括图片)
	 * @author 张萌
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListSTReq checkListReq){ 
		try {

			strengthTestService.saveRecords(checkListReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("强度试验检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}

	/**
	 * 查询强度试验记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , StrengthTestReq req){
		try{
			req.setSortInfo(request);
			//根据强度试验记录信息
			Map<String,Object> map = strengthTestService.queryRecords(req);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("强度试验信息查询失败！",e);
			return null;
		}
	}
	
	/**
	 * 根据记录ID查询记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public StrengthTest viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		StrengthTest st = strengthTestService.viewRecordById(id);
		return st;
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
	public String saveRecord(@RequestBody(required = true)  StrengthTest st){
		try {
			return strengthTestService.saveRecord(st);
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			logger.error("强度试验记录保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
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
	public String deleteRecordById(@RequestBody(required = true) StrengthTest st){
		try {
			strengthTestService.deleteRecordById(st.getStId());
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("强度试验记录删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 确认完成强度测试
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/finishStrengthTest")
	@ResponseBody
	public String finishStrengthTest(@RequestBody(required = true) String projId){
		try {
			return strengthTestService.finishStrengthTest(projId);
		} catch (Exception e) {
			logger.error("确认完成强度测试失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}


	/**
	 * @ Description: 试压推送到审核
	 * @ author zhangnx
	 * @ date 2019/11/14 16:54
	 **/
	@RequestMapping(value="/pushAudit")
	@ResponseBody
	public String pushAudit(String pcId,Integer flag){
		try {
			return proCheckListService.updateFlag(pcId,flag);
		} catch (Exception e) {
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
			proCheckListService.strengthTestUpdateFlag(checkList.getPcId(),checkList.getFlag(),checkList.getResetReason());
			return Constants.OPERATE_RESULT_SUCCESS;
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
			strengthTestService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
}
