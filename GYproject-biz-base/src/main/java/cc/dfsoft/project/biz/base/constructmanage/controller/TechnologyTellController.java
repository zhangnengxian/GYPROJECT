package cc.dfsoft.project.biz.base.constructmanage.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.project.biz.base.constructmanage.enums.DataTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionWorkService;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
/**
 * 工程交底
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/technologyTell")
public class TechnologyTellController {

	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(TechnologyTellController.class);

 	/**工程计划服务接口*/
    @Resource
    ConstructionPlanService constructionPlanService;

    /**交底记录服务接口*/
    @Resource
    ConstructionWorkService constructionWorkService;
    @Resource
	ProjectService projectService;
    @Resource
	OperateRecordService operateRecordService;


    @Resource
    BusinessPartnersDao businessPartnersDao;
	/**
	 * 打开主页面
	 * @author liaoyq
	 * @createTime 2017-7-28
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request, HttpSession session){

		Project project = projectService.queryProjectById(session.getAttribute("projId").toString());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		//分公司ID_菜单ID_XXX
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("loginPost",loginInfo.getPost());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.addObject("fieldRepresentPost",PostTypeEnum.BUILDER.getValue());	//现场代表
		modelView.addObject("designer",PostTypeEnum.DESIGNER.getValue());			//设计人员
		modelView.addObject("cuManager",PostTypeEnum.CONSTRUCTION.getValue());	//施工代表
		modelView.addObject("sujgj",PostTypeEnum.SUJGJ.getValue());					//现场监理师
		modelView.addObject("testLeaderPost",PostTypeEnum.TEST_LEADER.getValue());	//施工班组长
		modelView.addObject("dataAdmin",PostTypeEnum.DATAMANAGER.getValue());	//数据管理员
		modelView.addObject("userSignPage",getUserSignPage(project));//用户签字jsp页面
		modelView.addObject("testLeaderSignPage",getTestLeaderSignPage(project));//施工班组签字jsp页面

		modelView.addObject("sysDate",constructionWorkService.getDataBaseDate());	//系统时间
		modelView.addObject("customerServiceCenter",DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue());//部门划分:客服中心
		modelView.setViewName("constructmanage/technologyTell");

		return modelView;
	}



	/**
	 * @MethodDesc: 用户签字jsp页面
	 * @Author zhangnx
	 * @Date 2019/3/22 12:01
	 */
	public Object getUserSignPage(Project project){

		String corpId=project!=null?project.getCorpId():"1101";
		String projType=project!=null?project.getProjectType(): ProjLtypeEnum.CIVILIAN.getValue();
		String contributionMode=project!=null?project.getContributionMode():"1";

		//配置规则：分公司ID__工程类型__出资方式_菜单ID__userSignPage
		StringBuilder ctcKey=new StringBuilder(corpId).append("_").append(projType).append("_").append(contributionMode).append("_").append(Constants.TECHNOLOGYTELL_MENUID).append("_userSignPage");

		//配置规则：分公司ID__工程类型__菜单ID__userSignPage
		StringBuilder ctKey=new StringBuilder(corpId).append("_").append(projType).append("_").append(Constants.TECHNOLOGYTELL_MENUID).append("_userSignPage");

		//配置规则：分公司ID__菜单ID__userSignPage
		StringBuilder cKey=new StringBuilder(corpId).append("_").append(Constants.TECHNOLOGYTELL_MENUID).append("_userSignPage");

		return sysConfig(ctcKey)!=null?sysConfig(ctcKey):sysConfig(ctKey)!=null?sysConfig(ctKey):sysConfig(cKey);
	}



	/**
	 * @MethodDesc: 施工班组签字jsp页面
	 * @Author zhangnx
	 * @Date 2019/3/22 12:01
	 */
	public Object getTestLeaderSignPage(Project project){
		String corpId=project!=null?project.getCorpId():"1101";
		String projType=project!=null?project.getProjectType(): ProjLtypeEnum.CIVILIAN.getValue();
		String contributionMode=project!=null?project.getContributionMode():"1";

		//配置规则：分公司ID__工程类型__出资方式_菜单ID__testLeaderSignPage
		StringBuilder ctcKey=new StringBuilder(corpId).append("_").append(projType).append("_").append(contributionMode).append("_").append(Constants.TECHNOLOGYTELL_MENUID).append("_testLeaderSignPage");

		//配置规则：分公司ID__工程类型__菜单ID__testLeaderSignPage
		StringBuilder ctKey=new StringBuilder(corpId).append("_").append(projType).append("_").append(Constants.TECHNOLOGYTELL_MENUID).append("_testLeaderSignPage");

		//配置规则：分公司ID__菜单ID__testLeaderSignPage
		StringBuilder cKey=new StringBuilder(corpId).append("_").append(Constants.TECHNOLOGYTELL_MENUID).append("_testLeaderSignPage");

		return sysConfig(ctcKey)!=null?sysConfig(ctcKey):sysConfig(ctKey)!=null?sysConfig(ctKey):sysConfig(cKey);
	}



	public Object sysConfig(StringBuilder key){
		return Constants.getSysConfigByKey(key.toString());
	}





	/**
	 * 保存交底记录
	 * @param constructionWork
	 * @return
	 */
	@RequestMapping(value = "/saveConstructionWork")
	@ResponseBody
	public String technologyTellSave(HttpServletRequest request,@RequestBody(required=true) ConstructionWork constructionWork){
		try {
			String s = constructionWorkService.constructionWorkSave(constructionWork);

			handleNotice(constructionWork);//待办

			return s;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}


	public  void handleNotice(ConstructionWork constructionWork) {
		//将会审交底待办通知置为无效
		operateRecordService.noticeSetInvalid(constructionWork.getProjId(), StepEnum.TECHNOLOGYTELL.getValue(), "2");

		String isSign=constructionWorkService.firstSignStatusByProjId(constructionWork.getProjId());
		if ("1".equals(isSign)){//施工组织待办通知
			Project project = projectService.findById(constructionWork.getProjId());
			if (project!=null) {
				String operaterId=","+project.getManagementQaeId()+",";
				String operater=project.getManagementQae();
				operateRecordService.nextDealtNotice(project, StepEnum.CONSTRUCTIONORGANIZATION.getValue(), StepEnum.CONSTRUCTIONORGANIZATION.getMessage(), operaterId, operater);
			}
		}
	}




		/**
         * 详述
         * @param id
         * @return
         */
	@RequestMapping(value = "/constructionWorkDetail")
	@ResponseBody
	public ConstructionWork constructionWorkDetail(@RequestParam(required=true) String id,String dataType){
		try {
			if(StringUtils.isBlank(dataType)){
				dataType=DataTypeEnum.ALARM_PROJ.getValue();//默认为报警
			}
			return constructionWorkService.constructionWorkDetail(id,dataType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * @author liaoyq
	 * @createTime 2017年7月28日
	 * @param request
	 * @param constructionWorkReq 会审交底查询辅助类
	 * @return  Map<String, Object> 会审交底记录及分页信息
	 */
	@RequestMapping(value="/queryList")
	@ResponseBody
	public Map<String, Object> queryList (HttpServletRequest request , ConstructionWorkReq constructionWorkReq){
		try {
			Map<String, Object> map = constructionWorkService.queryList(constructionWorkReq);
			return map;
		} catch (Exception e) {
			logger.error("会审交底记录查询失败!",e);
			return null;
		}
	}
	/**
	 * 根据会审交底ID查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/findDetailById")
	@ResponseBody
	public ConstructionWork findDetailById(@RequestParam(required=true) String id){
		ConstructionWork wr= constructionWorkService.findDetailById(id);
		Project project=projectService.queryProjectById(wr.getProjId());
		if (project!=null){
			wr.setCorpId(project.getCorpId());
		}
		String key=wr.getCorpId()+"_"+Constants.TECHNOLOGYTELL_MENUID;
		List<DataFilerSetUpDto> dfsuList=Constants.getDataFilterMapByKey(key);

		if (dfsuList!=null&&dfsuList.size()>0) {
			wr.setHiddenConfig(dfsuList.get(0).getSupSql());
		}
		return wr;
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
			constructionWorkService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}



	/**
	 * @MethodDesc: 删除会审交底
	 * @Author zhangnx
	 * @Date 2019/5/6 10:28
	 */
	@RequestMapping(value = "/delConstrctWorkById")
	@ResponseBody
	public boolean delConstrctWorkById(String cwId) {
		try {
			return constructionWorkService.updateConstrctWorkById(cwId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

		/**
		 * 根据公司ID,工程类型，菜单ID查找cpt类型
		 * 参数不是必须的
		 * @author wanghuijun
		 * @createTime 2018年9月7日
		 * @param corpId
		 * @param projectType
		 * @param menuId
		 * @return
		 */
		@RequestMapping(value="/queryCptType")
		@ResponseBody
		public String queryCptType (String corpId,String projectType,String menuId){

			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
			Project project = projectService.queryProjectById(session.getAttribute("projId").toString());

			 corpId=project!=null?project.getCorpId():"1101";
			 projectType=project!=null?project.getProjectType(): ProjLtypeEnum.CIVILIAN.getValue();
			String contributionMode=project!=null?project.getContributionMode():"1";

			String resultCpt = "technologyTell.cpt";

			//配置规则：分公司ID__工程类型__出资方式__菜单ID
			StringBuilder ctcKey=new StringBuilder(corpId).append("_").append(projectType).append("_").append(contributionMode).append("_").append(Constants.TECHNOLOGYTELL_MENUID);

			//配置规则：分公司ID__工程类型__菜单ID
			StringBuilder ctKey=new StringBuilder(corpId).append("_").append(projectType).append("_").append(Constants.TECHNOLOGYTELL_MENUID);

			//配置规则：分公司ID__菜单ID
			StringBuilder cKey=new StringBuilder(corpId).append("_").append(Constants.TECHNOLOGYTELL_MENUID);

			return sysConfig(ctcKey)!=null?sysConfig(ctcKey).toString():sysConfig(ctKey)!=null?sysConfig(ctKey).toString():sysConfig(cKey)!=null?sysConfig(cKey).toString():resultCpt;

		}
	
	
}
