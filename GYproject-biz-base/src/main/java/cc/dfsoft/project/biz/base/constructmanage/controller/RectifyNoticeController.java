package cc.dfsoft.project.biz.base.constructmanage.controller;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import cc.dfsoft.project.biz.base.constructmanage.enums.RectifyNoticeTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.RectifyNoticeService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
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
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 整改通知
 * 
 * @author liaoyq
 */
@Controller
@RequestMapping("/rectifyNotice")
public class RectifyNoticeController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(RectifyNoticeController.class);

	/** 整改通知业务接口 */
	@Resource
	private RectifyNoticeService rectifyNoticeService;
	@Resource
	ManageRecordService manageRecordService;
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	ProjectService projectService;

	private final String menuId="120209";

	/**
	 * 打开整改通知主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(HttpSession session) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Object projId = session.getAttribute("projId");
		Project project = projectService.queryProjectById(projId != null ? projId.toString() : "");
		String key = (project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+menuId;
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("IsSignature", sysConfig(key)!=null?sysConfig(key):"false");    //传回页面判断该公司是否有签字
		modelAndView.addObject("imgUrl", Constants.DISK_PATH + Constants.SIGN_DISK_PATH); // img
		modelAndView.addObject("drawUrl1", Constants.DISK_PATH + Constants.DIAGRAM_DISK_PATH); // 图片
		modelAndView.addObject("rectifyNoticeType", RectifyNoticeTypeEnum.values());
		modelAndView.setViewName("constructmanage/rectifyNotice");
		return modelAndView;
	}


    /**
     * @methodDesc: 查询整改信息页面
     * @author: zhangnx
     * @date: 19:38 2018/12/17
     */
	@RequestMapping(value = "/queryJsp")
	public ModelAndView queryJsp(String projId,String menuId,String rnType,ModelAndView model) {
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		Project project = projectService.queryProjectById(projId);
		//配置规则：corpId__projType__rnType__menuId_jsp   按工程类型配置
		String cttmJspKey = (project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+(project!=null?project.getProjectType().toString():ProjLtypeEnum.CIVILIAN.getValue())+"_"+rnType+"_"+menuId+"_jsp";
		//配置规则：corpId__rnType__menuId_jsp   无工程类型配置
		String ctmJspKey = (project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+rnType+"_"+menuId+"_jsp";
		String defaultPage ="all_rectifyNotice";//全局默认

		//如果cttmJspKey为空 查询ctmJspKey 如果查询ctmJspKey为空 返回defaultPage
		String resultPage=sysConfig(cttmJspKey)!=null?sysConfig(cttmJspKey).toString():sysConfig(ctmJspKey)!=null?sysConfig(ctmJspKey).toString():defaultPage;
		model.setViewName("constructmanage/"+resultPage);
		return model;
	}

	public Object sysConfig(String key){
		return Constants.getSysConfigByKey(key);
	}












	@RequestMapping(value = "/rectifyPop")
	public ModelAndView rectifyPop(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("sysDate", manageRecordService.getDatabaseDate());
		modelAndView.addObject("loginInfo", SessionUtil.getLoginInfo());
		modelAndView.addObject("projId", request.getParameter("projId"));
		modelAndView.addObject("busOrderId", request.getParameter("busOrderId"));
		modelAndView.setViewName("constructmanage/rectifyPop");
		return modelAndView;
	}





	/**
	 * @author liaoyq
	 * @param request
	 * @param rectifyNoticeReq
	 *            整改通知查询dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryRectifyNotices")
	@ResponseBody
	public Map<String, Object> queryRectifyNotices(HttpServletRequest request, RectifyNoticeReq rectifyNoticeReq) {
		try {
			rectifyNoticeReq.setSortInfo(request);
			Map<String, Object> stringObjectMap = rectifyNoticeService.queryRectifyNotice(rectifyNoticeReq);

			return stringObjectMap;
		} catch (Exception e) {
			logger.error("整改通知列表查询失败！", e);
			return null;
		}
	}

	/**
	 * 弹出查询屏
	 *
	 * @return
	 */
	@RequestMapping(value = "/rectifyNoticeSearchPopPage")
	public ModelAndView viewRectifyNoticeSearchPopPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("constructmanage/rectifyNoticeSearchPopPage");
		return modelAndView;
	}

	/**
	 * 查询详述
	 *
	 * @return
	 */
	@RequestMapping(value = "/viewRectifyNotice")
	@ResponseBody
	public RectifyNotice viewRectifyNotice(HttpServletRequest request, @RequestParam(required = true) String id) {
		try {
			RectifyNotice rectifyNotice = rectifyNoticeService.viewRectifyNotice(id);
			return rectifyNotice;
		} catch (Exception e) {
			logger.error("整改信息详述查询失败！", e);
			return null;
		}
	}

	/**
	 * 保存整改通知
	 * 
	 * @param rectifyNotice
	 * @return
	 */
	@RequestMapping(value = "/saveRectifiyNotice")
	@ResponseBody
	public String saveRectifiyNotice(HttpServletRequest request,@RequestBody(required = true) RectifyNotice rectifyNotice) {
		try {
			return rectifyNoticeService.saveRectifiyNotice(rectifyNotice);
		} catch (HibernateOptimisticLockingFailureException e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存整改信息失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 推送签证单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/pushRectifyNotice")
	@ResponseBody
	public String pushRectifyNotice(@RequestBody(required = true) String rnId) {
		try {
			return rectifyNoticeService.pushRectifyNotice(rnId);
		} catch (Exception e) {
			logger.error("整改通知推送失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	@RequestMapping(value = "/deletById")
	@ResponseBody
	public String deletById(String rnId) {
		try {
			return rectifyNoticeService.deletById(rnId);
		} catch (Exception e) {
			logger.error("整改通知推送失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 根据公司ID、菜单ID、整改类型获取相应的报表
	 * 
	 * @author 王会军
	 * @createTime 2018年8月29日
	 * @return
	 */
	@RequestMapping(value = "/queryCptType")
	@ResponseBody
	public String queryCptType(String corpId, String menuId, String rnType,HttpSession session) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo(); // 取得当前登录人信息
		Object projId = session.getAttribute("projId");
		Project project = projectService.queryProjectById(projId != null ? projId.toString() : "");

		//key规则：corpId__rectifyNoticeType___projType__menuId
		String cttmKey=(project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+(StringUtil.isBlank(rnType)?1:rnType)+"_"+(project!=null?project.getProjectType():ProjLtypeEnum.CIVILIAN.getValue())+"_"+menuId;
		//key规则：corpId__rectifyNoticeType__menuId
		String ctmKey=(project!=null?project.getCorpId():loginInfo.getCorpId())+"_"+(StringUtil.isBlank(rnType)?1:rnType)+"_"+menuId;
		String defaultCpt="rectifyNotice.cpt";

		return sysConfig(cttmKey)!=null?sysConfig(cttmKey).toString():sysConfig(ctmKey)!=null?sysConfig(ctmKey).toString():defaultCpt;
	}




/**
 * @methodDesc: 将projId 存入session中
 * @author: zhangnx
 * @date: 16:18 2018/12/11
 */
	@RequestMapping(value = "/saveSessionProjId")
	@ResponseBody
	public String saveSessionProjId(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("projId", request.getParameter("projId"));
		return null;
	}
}
