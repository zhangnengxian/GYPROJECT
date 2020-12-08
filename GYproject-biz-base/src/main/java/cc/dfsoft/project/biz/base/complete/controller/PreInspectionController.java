package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.project.biz.base.complete.enums.CheckResultEnum;
import cc.dfsoft.project.biz.base.complete.enums.PreInspectionCheckTypeEnum;
import cc.dfsoft.project.biz.base.complete.enums.PreQuantityCheckTypeEnum;
import cc.dfsoft.project.biz.base.complete.service.CheckItemService;
import cc.dfsoft.project.biz.base.complete.service.JointAcceptanceService;
import cc.dfsoft.project.biz.base.complete.service.PreinspectionService;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;
import cc.dfsoft.project.biz.base.constructmanage.service.CompleteReportService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.QualifiedEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeStandardService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fr.function.SIGN;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 预验收
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/preinspection")
public class PreInspectionController {
	
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(PreInspectionController.class);
	
	/**预验收服务接口*/
	@Resource
	PreinspectionService preinspectionService;
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/** 自检项服务接口*/
	@Resource
	CheckItemService checkItemService;

	@Autowired
	CompleteReportService completeReportService;
	
	@Resource
	ManageRecordService manageRecordService;
	
	@Autowired
	JointAcceptanceService jointAcceptanceService;
	
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	SignNoticeStandardService signNoticeStandardService;

	/**
	 * 主页面
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息
		modelView.addObject("post", loginInfo.getPost()); 
		modelView.addObject("sujgj", PostTypeEnum.SUJGJ.getValue());
		modelView.addObject("cuPm", PostTypeEnum.CU_PM.getValue());//项目经理
		modelView.addObject("construction", PostTypeEnum.CONSTRUCTION.getValue());//施工员
		modelView.addObject("cmoDirectorSign", PostTypeEnum.BUILDER.getValue()); //现场代表
		modelView.addObject("sysDate",manageRecordService.getDatabaseDate()); 	 //系统时间
		modelView.setViewName("complete/preinspection");
		return modelView;
	}
	
	/**
	 * 加载右屏
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(String corpId,String projectType,String menuId){
		ModelAndView modelView=new ModelAndView();
		String key = corpId+"_"+projectType+"_"+menuId;   //根据当前工程所在公司ID，工程类型，菜单ID,工程Id加载右侧页面
		Object obj = Constants.getSysConfigByKey(key);
		String viewUrl = "jituanPreinspectionRight";   //默认加载集团预验收页面
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息
		if(obj !=null){  //若不为空返回右侧页面jsp
			viewUrl = obj.toString();
		}
		if(obj==null){  //若为空则默认加载该公司下的第一个右侧jsp页面
			key = loginInfo.getCorpId()+"_"+11+"_"+"110702";
		    obj = Constants.getSysConfigByKey(key);
		    if(obj != null){
		    	 viewUrl = obj.toString();
		    }
		}
		if(obj == null){  //若登录人为第三方人员，默认加载所在第一个公司下的第一个右侧页面
			String detptId = loginInfo.getDeptId();  //得到当前登录人部门ID
			BusinessPartners businessPartners = businessPartnersDao.get(detptId);
			if(businessPartners!=null){
				String[] bpCorpId = businessPartners.getCorpId().split(",");
				key = bpCorpId[0]+"_"+"11"+"_"+"110702";
				  obj = Constants.getSysConfigByKey(key);
				    if(obj != null){
				    	 viewUrl = obj.toString();
				    }
			}
		}
		String keyl = loginInfo.getCorpId()+"_"+Constants.PREINSPECTIONID_MENUID+"_cmoDirectorSignPage";
		modelView.addObject("post", loginInfo.getPost()); 
		modelView.addObject("sujgj", PostTypeEnum.SUJGJ.getValue());
		modelView.addObject("cuPm", PostTypeEnum.CU_PM.getValue());//项目经理
		modelView.addObject("cmoDirectorSign", PostTypeEnum.BUILDER.getValue()); //现场代表
		modelView.addObject("sysDate",manageRecordService.getDatabaseDate()); 	 //系统时间
		modelView.addObject("cmoDirectorSignPage",Constants.getSysConfigByKey(keyl));//现场代表签字jsp页面
		modelView.setViewName("complete/"+viewUrl);
		return modelView;
	}
	
	/**
	 * 工程列表查询
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String, Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq) {
		try {
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_PRE_INSPECTION.getValue());
			projectQueryReq.setStepId(StepEnum.PRE_INSPECTION.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("待预验收列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/viewPreInspection")
	@ResponseBody
	public Preinspection viewSurveyResult(HttpServletRequest request,@RequestParam(required=true) String id)throws ParseException {
		Project project = projectService.queryProjectById(id);
		String keyl = (project!=null?project.getCorpId():"")+"_"+Constants.PREINSPECTIONID_MENUID+"_preInspection";
		Preinspection preinspection = preinspectionService.findByProjId(id);
		Object obj = Constants.getSysConfigByKey(keyl);
		if(obj!=null){
			preinspection.setIsBuilderSign(obj.toString() );
		}
		//查询签字通知标准配置的施工单位签字职务
		List<SignNoticeStandard> list = signNoticeStandardService.findSignNoticeStandardList(Constants.PREINSPECTIONID_MENUID, project.getProjId());
		if(list!=null && list.size()>0){
			preinspection.setSignNoticeStandards(list);
		}
		return preinspection;
	}
	
	
	/**
	 * 打开质量之间
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/qualityCheck")
	public ModelAndView qualityCheck(String corpId){
		ModelAndView modelView;
		modelView = new ModelAndView();
		try {
			List<CheckItem> checkItems1 = checkItemService.findByType("3","3",corpId);//庭院
			List<CheckItem> checkItems2 = checkItemService.findByType("3","4",corpId);//户内
			List<CheckItem> checkItems3 = checkItemService.findByType("3","5",corpId);//调压箱
			List<CheckItem> checkItems4 = checkItemService.findByType("3","9",corpId);//计量设备
			modelView.addObject("checkItems1", checkItems1);
			modelView.addObject("checkItems2", checkItems2);
			modelView.addObject("checkItems3", checkItems3);
			modelView.addObject("checkItems4", checkItems4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelView.addObject("cRMaterial",QualifiedEnum.values());
		
		modelView.addObject("checkType",PreInspectionCheckTypeEnum.values());//检查类型
		modelView.setViewName("complete/preQualityCheck");
		return modelView;
	}
	
	
	/**
	 * 打开资料自检
	 * @return
	 */
	@RequestMapping(value="/materialCheck")
	public ModelAndView materialCheck(String corpId){
		ModelAndView modelView;
		modelView = new ModelAndView();
		try {
			List<CheckItem> checkItems1 = checkItemService.findByType("4","6",corpId);//封面
			List<CheckItem> checkItems2 = checkItemService.findByType("4","7",corpId);//竣工图
			List<CheckItem> checkItems3 = checkItemService.findByType("4","8",corpId);//竣工资料
			modelView.addObject("checkItems1", checkItems1);
			modelView.addObject("checkItems2", checkItems2);
			modelView.addObject("checkItems3", checkItems3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelView.addObject("cRMaterial",QualifiedEnum.values());
		modelView.addObject("checkType",PreQuantityCheckTypeEnum.values());//检查类型
		modelView.setViewName("complete/preMaterialCheck");
		return modelView;
	}
	
	/**
	 * 打开材料预验
	 * @author fuliwei
	 * @createTime 2017年8月15日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/dataCheck")
	public ModelAndView dataCheck(String corpId){
		ModelAndView modelView;
		modelView = new ModelAndView();
		try {
			List<CheckItem> checkItems1 = checkItemService.findByType("5","9",corpId);//材料
			modelView.addObject("checkItems1", checkItems1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelView.addObject("cRMaterial",CheckResultEnum.values());//是否
		modelView.addObject("checkType","材料");//检查类型
		modelView.setViewName("complete/preDataCheck");
		return modelView;
	}
	
	/**
	 * 保存预验收
	 * @author fuliwei
	 * @createTime 2017年6月24日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/savePreinspection")
	public String savePreinspection(HttpServletRequest request,@RequestBody(required = true) Preinspection preinspection){
		try{
			String result = preinspectionService.savePreinspection(preinspection,WorkFlowActionEnum.PRE_INSPECTION.getActionCode());
			jointAcceptanceService.finshSignCreateNotice(preinspection.getProjId(), preinspection.getPiId(), "110702", StepEnum.PRE_INSPECTION.getValue(), SignDataTypeEnum.PRE_INSPECTION.getValue());
			return result;
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("预验收保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 查质量预验收记录详述
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/preInspectionRecordQuqlity")
	@ResponseBody
	public Map<String, String> viewSelfInspectionRecordQuqlity(HttpServletRequest request,@RequestParam(required=true) String id){
		return preinspectionService.viewPreInspectionRecordQuqlity(id);
	}
	
	
	/**
	 * 查资料预验收记录详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/preInspectionRecordMaterial")
	@ResponseBody
	public Map<String, String> viewpreInspectionRecordMaterial(HttpServletRequest request,@RequestParam(required=true) String id){
		return preinspectionService.viewPreInspectionRecordMaterial(id);
	}
	
	/**
	 * 查材料预验收记录详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/preInspectionRecordData")
	@ResponseBody
	public Map<String, String> viewPreInspectionRecordData(HttpServletRequest request,@RequestParam(required=true) String id){
		return preinspectionService.viewPreInspectionRecordData(id);
	}
	/**
	 * 弹出搜索屏
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("complete/searchPreInspectionPopPage");
		return modelview;
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
			preinspectionService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
	
	/**
	 * 查询是否有竣工报告
	 *creater wanghuijun
	 *@version 2019年11月27日
	 *@return boolean
	 *@throws Exception
	 */
	@RequestMapping (value = "/getCompleteReport")
	@ResponseBody
	public boolean getCompleteReport(@RequestParam(required = true) String projId) throws Exception {
		CompleteReport com = completeReportService.findByProjId(projId, "1");
		if (com != null) {
			return true;
		}
		return false;
	}
	
}
