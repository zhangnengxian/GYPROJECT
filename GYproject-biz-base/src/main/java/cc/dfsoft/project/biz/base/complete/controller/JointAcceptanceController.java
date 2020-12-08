package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.dto.JointAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.complete.service.DivisionalAcceptanceService;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.constructmanage.enums.DataTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.RectifyNoticeTypeEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * 联合验证
 * @createTime 2016-06-29
 * @author pengtt
 *
 */
@Controller
@RequestMapping(value="/jointAcceptance")
public class JointAcceptanceController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(JointAcceptanceController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/*联合验收服务接口*/
	@Resource
	JointAcceptanceService jointAcceptanceService;

	/*分布验收服务接口*/
	@Resource
	DivisionalAcceptanceService divisionalAcceptanceService;
	
	/*部门服务接口*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("deptDivide",loginInfo.getDeptDivide()); //部门组别
		modelView.addObject("qualitySafetyGroup",DeptDivideEnum.QUALITY_SAFETY_GROUP.getValue()); //质量安全组
		modelView.addObject("loginPost",loginInfo.getPost()); //登录职位
		modelView.addObject("corpId",loginInfo.getCorpId()); //公司ID
		modelView.addObject("inPost",PostTypeEnum.CONSTRUCTION.getValue()); //职位
		modelView.addObject("curStepId",StepEnum.UNION_PRE_INSPECTION.getValue()); //
		modelView.addObject("projNo", SessionUtils.getProjNo());
		modelView.setViewName("complete/jointAcceptance");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPlanningPage(String corpId,String projectType,String menuId,String projId,String contributionMode) {

		menuId= StringUtils.isNotBlank(menuId)?menuId:"110703";

		ModelAndView modelview = new ModelAndView();
		String key = corpId+"_"+projectType+"_"+contributionMode+"_"+menuId;   //根据当前工程所在公司ID，工程类型,菜单ID,工程Id加载右侧页面
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = null;
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息
		if(obj != null){
	    	 viewUrl = obj.toString();
	    }else{
			 key = loginInfo.getCorpId()+"_11_1_"+menuId;
			    obj = Constants.getSysConfigByKey(key);
			    if(obj != null){
			    	 viewUrl = obj.toString();
			    }else{
			    	 //若登录人为第三方人员，默认加载所在第一个公司下的第一个右侧页面
			    	if(StringUtils.isNotBlank(loginInfo.getBelongCorpId())){
						String[] bpCorpId = loginInfo.getBelongCorpId().split(",");
						key = bpCorpId[0]+"_11_1_"+menuId;
						obj = Constants.getSysConfigByKey(key);
						if(obj != null){
						    viewUrl = obj.toString();
						}
			    	}
			    }
	    }
		//若无则通过根据当前工程所在公司ID，工程类型,出资方式，菜单ID,工程Id加载右侧页面
		if(StringUtils.isBlank(viewUrl)){
			key = corpId+"_"+projectType+"_"+menuId;   //根据当前工程所在公司ID，工程类型,菜单ID,工程Id加载右侧页面
			obj = Constants.getSysConfigByKey(key);
			if(obj !=null){  //若不为空返回右侧页面jsp
				viewUrl = obj.toString();
			}else{//若为空则默认加载该公司下的第一个右侧jsp页面
				key = loginInfo.getCorpId()+"_11_"+menuId;
			    obj = Constants.getSysConfigByKey(key);
			    if(obj != null){
			    	 viewUrl = obj.toString();
			    }else{
			    	if(StringUtils.isNotBlank(loginInfo.getBelongCorpId())){
						String[] bpCorpId = loginInfo.getBelongCorpId().split(",");
						key = bpCorpId[0]+"_11_"+menuId;
						obj = Constants.getSysConfigByKey(key);
						if(obj != null){
							viewUrl = obj.toString();
						}
					}
			    }
			}
		}



		if(StringUtils.isBlank(viewUrl)){
			viewUrl = "jointAcceptanceRight";
		}
		modelview.addObject("dataType",DataTypeEnum.values()); //资料类型-报警、工艺
		modelview.addObject("post", loginInfo.getPost());
		modelview.addObject("projId", projId);
		modelview.addObject("cuSpdPrincipal",PostTypeEnum.BUILDER.getValue()); //现场代表
		modelview.addObject("duDeputy",PostTypeEnum.DESIGNER.getValue()); //设计员
		modelview.addObject("suFieldJgj",PostTypeEnum.SUJGJ.getValue()); //监理
		modelview.addObject("managementQae",PostTypeEnum.CONSTRUCTION.getValue()); //施工员
		modelview.addObject("organizationMan",PostTypeEnum.TECHNICIAN.getValue()); //治安组
		modelview.addObject("custCenterSign",PostTypeEnum.INSPECTOR.getValue()); //客服中心
		modelview.addObject("transCompanySign",PostTypeEnum.PROCESS_TECHNICIAN.getValue()); //输配签字
		modelview.addObject("measurementSign",PostTypeEnum.PRODUCTION_STATISTICIANS.getValue()); //计量所签字
		modelview.addObject("deptId",loginInfo.getDeptId()); //当前人所属部门ID
		modelview.addObject("techEquipmentDevice",PostTypeEnum.EQUIPMENT_TECHNICIAN.getValue()); //技装部
		modelview.addObject("informCenterSign",PostTypeEnum.DATA_HANDLE.getValue()); //官网数据中心签字
		modelview.addObject("ceneralEngineerSign",PostTypeEnum.CENERAL_ENGINEER.getValue()); //总工程师
		modelview.addObject("marketDevSign",PostTypeEnum.SALESMA.getValue()); //市场部
		modelview.addObject("viceMinister", PostTypeEnum.VICE_MINISTER.getValue());   //副部长
		modelview.addObject("minister", PostTypeEnum.MINISTER.getValue());   //部长
		//整改类型
		modelview.addObject("rectifyNoticeType",RectifyNoticeTypeEnum.ACCEPTANCE.getValue()); //验收整改
		modelview.setViewName("complete/"+viewUrl);
		return modelview;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author cui
	 * @createTime 2016-08-16 
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryJointAcceptancer")
	@ResponseBody
	public Map<String,Object> queryJointAcceptancer(ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.UNION_PRE_INSPECTION.getValue());				  //待联合验收工程列表
			projectQueryReq.setStepId(StepEnum.UNION_PRE_INSPECTION.getValue());
			//projectService.queryProjectByTime(projectQueryReq);
			return projectService.queryProjectByTimeForJoint(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 联合验收列表条件查询
	 * @param jointAcceptanceReq
	 * @author cui
	 * @createTime 2017-08-11
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryJointList")
	@ResponseBody
	public Map<String,Object> queryJointList(JointAcceptanceReq jointAcceptanceReq){
		try {
			return jointAcceptanceService.queryJointAcceptance(jointAcceptanceReq);
		} catch (Exception e) {
			logger.error("联合验收信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 分布验收列表条件查询
	 * @param divisionalAcceptanceReq
	 * @author cui
	 * @createTime 2017-08-11
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryDivisionalList")
	@ResponseBody
	public Map<String,Object> queryDivisionalList(DivisionalAcceptanceReq divisionalAcceptanceReq){
		try {
			return divisionalAcceptanceService.queryDivisionalAcceptance(divisionalAcceptanceReq);
		} catch (Exception e) {
			logger.error("分布验收信息查询失败！", e);
			return null;
		}
	}

	/**
	 * 弹出搜索
	 * @author cui
	 * @createTime 2016-8-16
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("complete/jointSearchPop");
		return modelview;
	}

	/**
	 * 联合验收单详述
	 * @return
	 */
	@RequestMapping(value = "/jointView")
	@ResponseBody
	public JointAcceptance jointView(@RequestParam(required=true) String id){
		try {
			return jointAcceptanceService.jointView(id);//默认为工艺
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 联合验收单详述
	 * @return
	 */
	@RequestMapping(value = "/jointDetail")
	@ResponseBody
	public JointAcceptance jointDetail(@RequestParam(required=true) String id){
		try {
			return jointAcceptanceService.jointDetailByType(id,null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 保存联合验收单 不改状态
	 * @author cui
	 * @createTime 2016-08-17
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/saveJoint")
	@ResponseBody
	public String saveJoint(HttpServletRequest request, @RequestBody(required = true) JointAcceptance jointAcceptance){
		try{//String projId, String businId, String menuId, String stepId, String dataType
			String identifier = jointAcceptanceService.saveJoint(jointAcceptance);
			// 联合验收签字完成，通知代办人签字已完成，请及时推送此条通知
			jointAcceptanceService.finshSignCreateNotice(jointAcceptance.getProjId(), jointAcceptance.getJaId(), null, StepEnum.UNION_PRE_INSPECTION.getValue(),
					SignDataTypeEnum.JOINT_ACCEPTANCE.getValue());
			return identifier;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存联合验收失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 推送联合验收单
	 * @author cui
	 * @createTime 2016-08-17
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/entJoint")
	@ResponseBody
	public String entJoint(HttpServletRequest request,@RequestBody(required = true) JointAcceptance jointAcceptance){
		try {
			jointAcceptanceService.entJoint(jointAcceptance);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("联合验收信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 详述
	 * @param id
	 * @param dataType
	 * @return
	 */
//	@RequestMapping(value = "/jointDetailByType")
//	@ResponseBody
//	public JointAcceptance jointDetailByType(@RequestParam(required=true) String id,String dataType){
//		try {
//			return jointAcceptanceService.jointDetailByType(id,dataType);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
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
			jointAcceptanceService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
}
