package cc.dfsoft.project.biz.base.constructmanage.controller;

import cc.dfsoft.project.biz.base.constructmanage.dto.ReviewRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionOrganization;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionOrganizationService;
import cc.dfsoft.project.biz.base.constructmanage.service.ReviewRecordService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 施工组织
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/constructionOrganization")
public class ConstructionOrganizationController {

	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(ConstructionOrganizationController.class);

	/**图纸会审服务接口*/
	@Resource
	ConstructionOrganizationService constructionOrganizationService;

	/**会审记录服务接口*/
	@Resource
	ReviewRecordService reviewRecordService;

	@Resource
	OperateRecordService operateRecordService;
	@Resource
	ProjectService projectService;
	@Resource
	SignNoticeService signNoticeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(ModelAndView modelView){

		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("cuManager",PostTypeEnum.CONSTRUCTION.getValue()); //施工代表
		modelView.addObject("suCseost",PostTypeEnum.SUCSE.getValue()); //总监
		modelView.addObject("suJgjPost",PostTypeEnum.SUJGJ.getValue()); // 现场监理职务					
		modelView.addObject("projectLeaderPost",PostTypeEnum.PROJECT_LEADER.getValue()); //项目负责人
		modelView.addObject("technialLeaderPost",PostTypeEnum.CENERAL_ENGINEER.getValue()); //技术负责人
		modelView.addObject("auditOfficerPost",PostTypeEnum.CU_PM.getValue()); //审核负责人
		modelView.addObject("loginPost",loginInfo.getPost()); //登录人员
		modelView.addObject("dataAdmin",PostTypeEnum.DATAMANAGER.getValue()); //数据管理员
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); //img url
		modelView.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//部门划分:客服中心
		modelView.setViewName("constructmanage/constructionOrganization");
		return modelView;

	}

	/**
	 * 弹出会审信息
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/auditInfoPopPage")
	public ModelAndView auditPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("constructmanage/auditInfoPopPage");
		return modelview;
	}

	/**
	 * 施工组织左侧详情
	 * @return
	 */
	@RequestMapping(value = "/constructionOrganizationDetail")
	@ResponseBody
	public ConstructionOrganization constructionOrganizationDetail(@RequestParam(required=true) String id) throws Exception {
			return constructionOrganizationService.constructionOrganizationDetail(id);

	}

	/**
	 * 会审记录列表
	 */
	@RequestMapping(value = "/queryReviewRecord")
	@ResponseBody
	public  Map<String,Object> queryReviewRecord(ReviewRecordQueryReq reviewRecordQueryReq) throws Exception {
		return reviewRecordService.queryReviewRecord(reviewRecordQueryReq);
	}



	/**
	 * 施工组织保存（有附件）
	 * @param constructionOrganization
	 * @return
	 */
	@RequestMapping(value = "/saveFileConstructionOrganization")
	@ResponseBody
	public Object constructionOrganizationSaveFile(HttpServletRequest request, UploadResult constructionOrganization, @RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			String coId = constructionOrganizationService.saveConstructionOrganization(request,constructionOrganization,files);
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("operateId", coId);
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			return json;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			json.put("result", "already");
			return json;
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("变更信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}

	/**
	 * 施工组织保存(无附件)
	 * @param cOrg
	 * @return
	 */
	@RequestMapping(value = "/saveConstructionOrganization")
	@ResponseBody
	public String constructionOrganizationSave(HttpServletRequest request,@RequestBody(required=true) UploadResult cOrg){

		JSONObject jo = new JSONObject();
		ConstructionOrganization co = JSON.parseObject(cOrg.getResult(), ConstructionOrganization.class);
		try {
			if ("1".equals(co.getCheckResult())){
				String s = constructionOrganizationService.saveConstructionOrganization(request, cOrg, null);
				handleNotice(cOrg, s);//待办
				return s;
			}else {//修改后报或重新编制
				constructionOrganizationService.delBackupsConstructionOrganization(co.getProjId(),co.getSuAdvice());
				return co.getCoId();
			}

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
			logger.error("施工组织保存失败!",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}


	public  boolean handleNotice(UploadResult cOrg,String s) throws Exception {
		//将会施工组织待办通知置为无效
		ConstructionOrganization co = JSON.parseObject(cOrg.getResult(), ConstructionOrganization.class);
		operateRecordService.noticeSetInvalid(co.getProjId(), StepEnum.CONSTRUCTIONORGANIZATION.getValue(), "2");

		Project project = projectService.findById(co.getProjId());
		ConstructionOrganization  c=constructionOrganizationService.findById(s);

		if (project==null) return false;

		String operaterId=","+project.getManagementQaeId()+",";
		String operater=project.getManagementQae();

		if (c!=null && "1".equals(c.getSignState())){//激活开工报告待办通知
			operateRecordService.nextDealtNotice(project, StepEnum.CONSTRUCTION.getValue(), StepEnum.CONSTRUCTION.getMessage(), operaterId, operater);
		}

		if (!"1".equals(co.getCheckResult())) {//修改后报或重新编制(重新生成代办)
			operateRecordService.nextDealtNotice(project, StepEnum.CONSTRUCTIONORGANIZATION.getValue(), StepEnum.CONSTRUCTIONORGANIZATION.getMessage(), operaterId, operater);

			//签字通知置为无效
			boolean b=signNoticeService.signNoticeSetInvalid(co.getCoId(),co.getProjId(), SignDataTypeEnum.CONSTRUCTION_ORGANIZATION.getValue(),null,null);
		}



		return true;
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
	public void saveSignNotice(@RequestBody(required = true) String cwId) throws Exception {
			constructionOrganizationService.saveSignNotice(cwId);
	}

}
