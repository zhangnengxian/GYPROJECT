package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.inspection.dto.BallRecordReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListBRReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.BallRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.BallRecordService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
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
 * 通球记录
 * @author liaoyq
 */
@Controller
@RequestMapping(value="/ballRecord")
public class BallRecordController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(InstallSummaryController.class);
	
	/** 工程报验服务接口 */
	@Resource
	ProjectChecklistService proCheckListService;
	
	@Resource
	BallRecordService ballRecordService;

	@Resource
	SignNoticeService signNoticeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionName","ballRecord");//controller路径名
		modelAndView.addObject("checkType",ProjectChecklistTypeEnum.BALL_RECORD.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("testLeader",PostTypeEnum.TEST_LEADER.getValue());//班组长
		modelAndView.addObject("custRepresentPost",PostTypeEnum.BUILDER.getValue());//甲方代表
		modelAndView.addObject("construction",PostTypeEnum.CONSTRUCTION.getValue());//施工员
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		//报验单升级时间
		modelAndView.addObject("upgradeDate",Constants.UPGRADE_DATE);
		modelAndView.setViewName("inspection/ballRecord");
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
			proCheckListReq.setPcDesId(ProjectChecklistTypeEnum.BALL_RECORD.getValue());
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
			checkList.setProcess(ProjectChecklistTypeEnum.BALL_RECORD.getMessage());
			String pcId = proCheckListService.saveProCheckList(checkList, Constants.MODULE_CODE_BALL_RECORD);
			//回写记录表中pcId
			ballRecordService.updatePcIdByRecordsId(checkList.getRecordsId(),pcId);
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
			logger.error("通球记录 签字区保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询通球记录记录列表
	 * @return
	 */
	@RequestMapping(value="/queryBallRecord")
	@ResponseBody
	public Map<String, Object> queryBallRecord(HttpServletRequest request , BallRecordReq ballRecordReq){
		try{
			ballRecordReq.setSortInfo(request);
			//根据通球记录记录信息
			Map<String,Object> map = ballRecordService.queryBallRecord(ballRecordReq);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("通球记录信息查询失败！",e);
			return null;
		}
	}
	

	/**
	 * 查询通球记录列表
	 * @return
	 */
	@RequestMapping(value="/queryRecords")
	@ResponseBody
	public Map<String, Object> queryRecords(HttpServletRequest request , BallRecordReq req){
		try{
			req.setSortInfo(request);
			//根据通球记录信息
			Map<String,Object> map = ballRecordService.queryRecords(req);
			return map;
		//return Constants.OPERATE_RESULT_SUCCESS;
		} catch(Exception e){
			logger.error("通球信息查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存记录(批量)
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveRecords")
	@ResponseBody
	public String saveRecords(HttpServletRequest request,@RequestBody(required=true) ProjectCheckListBRReq checkListReq){ 
		try {
			ballRecordService.saveRecords(checkListReq);	
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("通球检查记录保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	/**
	 * 根据记录ID查询记录详述
	 */
	@RequestMapping(value="/viewRecordById")
	@ResponseBody
	public BallRecord viewRecordById(HttpServletRequest request,@RequestParam(required=true) String id){
		BallRecord ballRecord = ballRecordService.viewRecordById(id);
		return ballRecord;
	}
	/**
	 * 保存记录(一条记录)
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @return
	 */
	@RequestMapping(value="/saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request,@RequestBody(required = true)  BallRecord br){
		try {
			return ballRecordService.saveRecord(br);
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
			logger.error("通球记录保存失败！",e);
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
	public String deleteRecordById(@RequestBody(required = true) BallRecord br){
		try {
			ballRecordService.deleteRecordById(br.getBrId());
			signNoticeService.deleteByBsId(br.getBrId(),ProjectChecklistTypeEnum.BALL_RECORD.getValue());   //删除签字通知
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("通球记录删除失败！",e);
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
			ballRecordService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
}
