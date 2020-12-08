package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.NdeRecordBcDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.BcType;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.project.biz.base.constructmanage.enums.NdeTestItemEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.NdeWeldMethodEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.TestResultEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.BcTypeService;
import cc.dfsoft.project.biz.base.constructmanage.service.BusinessCommunicationService;
import cc.dfsoft.project.biz.base.constructmanage.service.DailyLogService;
import cc.dfsoft.project.biz.base.constructmanage.service.NdeRecordService;
import cc.dfsoft.project.biz.base.project.dto.ProjectViewDto;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 业务沟通
 * @author Yuanyx
 *
 */
@Controller
@RequestMapping(value="/businessCommunication")
public class BusinessCommunicationController {
	
	/**输出日志实例*/
	private static Logger logger = LoggerFactory.getLogger(BusinessCommunicationController.class);
	
	/**日志服务接口*/
	@Resource
	DailyLogService dailyLogService;
	
	/**业务沟通服务接口*/
	@Resource
	BusinessCommunicationService businessCommunicationService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**通知类型接口*/
	@Resource
	BcTypeService bcTypeService;
	/** 附件记录服务接口 */
	@Resource 
	AccessoryService accessoryService;
	
	@Resource
	NdeRecordService ndeRecordService;
	@Resource
	ManageRecordService manageRecordService;
	
	/**
	 * 业务沟通主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("loginId",SessionUtil.getLoginInfo().getStaffId());
		modelView.addObject("postType",PostTypeEnum.SUB_FIELDPRINCIPAL);
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.addObject("unitTypes", UnitTypeEnum.values());
		List<BcType> bcTypeList = bcTypeService.findBcType("","");
		List<BcType> bcTypeDetailList = bcTypeService.findAllBcType();
		modelView.addObject("bcType",bcTypeList);
		modelView.addObject("bcTypeDetail",bcTypeDetailList);
		modelView.addObject("ndeTestItem",NdeTestItemEnum.values());//无损检测项目
		modelView.addObject("ndeWeldMethod",NdeWeldMethodEnum.values());//无损检测焊接方法
		modelView.addObject("testResultEnum",TestResultEnum.values());//无损检测结果
		
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("loginPost",loginInfo.getPost()); 			     	//当前登录人职务
		modelView.addObject("bcInitiatorPost",loginInfo.getPost()); 		    //通知人职务
		modelView.addObject("receivePost",PostTypeEnum.CHECKER.getValue()); 		//接收人职位-检测职务
		
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+"120214");
	    if(list != null && list.size() > 0){
	    	modelView.addObject("sujgjPost",PostTypeEnum.BUILDER.getValue());  	//现场代表职务
	    	modelView.addObject("suCsePost",PostTypeEnum.PROJECT_LEADER.getValue());  	//工程负责人职务
	    	modelView.addObject("bcRecipientPost",PostTypeEnum.BUILDER.getValue()); 		//接收人职位-现场代表职务
	    }else{
	    	modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 		//现场监理
			modelView.addObject("suCsePost",PostTypeEnum.SUCSE.getValue()); 		//总监
			modelView.addObject("bcRecipientPost",PostTypeEnum.SUJGJ.getValue()); 		//接收人职位-监理职务
	    }
		modelView.setViewName("constructmanage/businessCommunication");
		return modelView;
	}
	/**
	 * 业务沟通主右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		
		modelview.setViewName("constructmanage/businessCommunicationRight");
		return modelview;
	}
	/**
	 * 业务沟通--通知列表查询
	 * @param request
	 * @param dailyLogQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryBusinessCommunication")
	@ResponseBody
	public Map<String,Object> queryBusinessCommunication(HttpServletRequest request,BusinessCommunicationReq businessCommunicationReq){
		try {
			businessCommunicationReq.setSortInfo(request);
			Map<String,Object> result = businessCommunicationService.queryBusinessCommunication(businessCommunicationReq);
			return result;
		} catch (Exception e) {
			logger.error("业务沟通--通知列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 保存对象
	 * @param businessCommunication
	 * @return
	 */
	@RequestMapping(value = "/saveBusinessCommunication")
	@ResponseBody
	public String saveBusinessCommunication(HttpServletRequest request,@RequestBody(required = true) BusinessCommunication businessCommunication){
		try {
			businessCommunicationService.saveBusinessCommunication(businessCommunication);
			NdeRecordBcDto ndeRecordBcDto = businessCommunicationService.viewByBcId(businessCommunication.getBcId());
			//统一版本
			if(ndeRecordBcDto!=null && ndeRecordBcDto.getbCommunication()!=null && ndeRecordBcDto.getNdeRecord()!=null){
				BusinessCommunication bc = ndeRecordBcDto.getbCommunication();
				NdeRecord nd = ndeRecordBcDto.getNdeRecord();
				Integer version=bc.getVersion().compareTo(nd.getVersion())>0?bc.getVersion():nd.getVersion();
				bc.setVersion(version);
				businessCommunicationService.updateByBcId(bc);
				nd.setVersion(version);
				ndeRecordService.updateByNdId(nd);
				//返修版本
				BusinessCommunication bcRe = businessCommunicationService.viewByTestResult(nd.getNrId());
				if(bcRe!=null && StringUtil.isNotBlank(bcRe.getBcId())){
					bcRe.setVersion(version);
				}
			}
			return Constants.OPERATE_RESULT_SUCCESS;
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
			logger.error("业务沟通保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 通过工程id查询工程对象
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/findByProjId")
	@ResponseBody
	public ProjectViewDto findByProjId(String projId){
		try {
			return projectService.diaryViewProject(projId);
		} catch (Exception e) {
			logger.error("工程id查询工程对象失败！", e);
			return null;
		}
	}
	/**
	 * 查通知细类
	 * @param corType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryBcTypeDetail")
	public List<BcType> queryBcTypeDetail(String bcType){
		try{
			return bcTypeService.findBcType("",bcType);
		}catch(Exception e){
			logger.error("通知细类查询失败！",e);
			return null;
		}
	}
	/**
	 * 初始化接收人部门
	 * @param model
	 * @return
	 */
	@RequestMapping("/staffDeptTree")
	public String staffDeptTree(Model model) {
		return "/constructmanage/staffDeptTree";
	}
	/**
	 * 通过工程id查询工程对象
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/findReplyDate")
	@ResponseBody
	public String findReplyDate(){
		try {
			return businessCommunicationService.findReplyDate();
		} catch (Exception e) {
			logger.error("工程id查询工程对象失败！", e);
			return null;
		}
	}
	/**
	 * 回复保存
	 * @param businessCommunication
	 * @return
	 */
	@RequestMapping(value = "/replyBusinessCommunication")
	@ResponseBody
	public String replyBusinessCommunication(HttpServletRequest request,@RequestBody(required = true) BusinessCommunication businessCommunication){
		try {
			//return  businessCommunicationService.replyBusinessCommunication(businessCommunication);
			//版本问题先查询通知，得到版本号
			NdeRecordBcDto ndeRecordBcDto = businessCommunicationService.viewByBcId(businessCommunication.getBcId());
			if(ndeRecordBcDto!=null && ndeRecordBcDto.getbCommunication()!=null && ndeRecordBcDto.getbCommunication().getVersion()!=null){
				businessCommunication.setVersion(ndeRecordBcDto.getbCommunication().getVersion());
			}
			
			businessCommunicationService.replyBusinessCommunication(businessCommunication);
			//统一版本
			if(ndeRecordBcDto!=null && ndeRecordBcDto.getbCommunication()!=null && ndeRecordBcDto.getNdeRecord()!=null){
				BusinessCommunication bc = ndeRecordBcDto.getbCommunication();
				NdeRecord nd = businessCommunication.getNdeRecord();
				Integer version=bc.getVersion().compareTo(nd.getVersion())>0?bc.getVersion():nd.getVersion();
				bc.setVersion(version);
				businessCommunicationService.updateByBcId(bc);
				nd.setVersion(version);
				ndeRecordService.updateByNdId(nd);
			}
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("业务沟通回复失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 删除记录
	 * @param businessCommunication
	 * @return
	 */
	@RequestMapping(value = "/delBusinessCommunication")
	@ResponseBody
	public String delBusinessCommunication(String bcId){
		try {
			return  businessCommunicationService.delBusinessCommunication(bcId);
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			logger.error("业务沟通删除失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/saveUploadFile")
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request,AccessoryList accDto,@RequestParam(value = "files[]", required = false) MultipartFile[] files) {
		
		try {
			 if(StringUtils.isBlank(accDto.getBusRecordId())){
				 JSONObject json = new JSONObject();
				 json.put("result","noBusiness");
				 return json;
			 }
			accessoryService.uploadAccessory(request, accDto, files);
			JSONArray js = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject json = new JSONObject();
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);

			return json;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			// TODO Auto-generated catch block
			//System.out.println(accDto.getAlPath());
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
		} 
	 
		return null;
		
	}
	/**
	 * 通过通知业务当id查询详述
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewByBcId")
	@ResponseBody
	public NdeRecordBcDto viewByBcId(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			return businessCommunicationService.viewByBcId(id);
		} catch (Exception e) {
			logger.error("通知业务单对象查询失败！", ExceptionUtil.getMessage(e));
			return null;
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
			businessCommunicationService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
}
