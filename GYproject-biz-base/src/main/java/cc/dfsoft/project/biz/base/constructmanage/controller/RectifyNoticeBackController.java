package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import cc.dfsoft.project.biz.base.constructmanage.enums.RnStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.RectifyNoticeService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 通知回复
 * @author liaoyq
 */
@Controller
@RequestMapping("/rectifyNoticeBack")
public class RectifyNoticeBackController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(RectifyNoticeBackController.class);
	
	/**整改通知业务接口*/
	@Resource
	private RectifyNoticeService  rectifyNoticeService;
	/**
	 * 打开整改通知主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("drawUrl1",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH); 	//图片 url
		
		modelAndView.addObject("cuPm",PostTypeEnum.CU_PM.getValue()); 	//项目经理
		modelAndView.addObject("suJuj",PostTypeEnum.SUJGJ.getValue()); 	//现场监理
		modelAndView.setViewName("constructmanage/rectifyNoticeBack");
		return modelAndView;
	}
	/**
	 * @author liaoyq
	 * @param request
	 * @param rectifyNoticeReq 整改通知查询dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value="/queryRectifyNotices")
	@ResponseBody
	public Map<String, Object> queryRectifyNotices(HttpServletRequest request,RectifyNoticeReq rectifyNoticeReq){
		try {
			rectifyNoticeReq.setSortInfo(request);
			List<String> rnStatus = new ArrayList<String>();
			rnStatus.add(RnStatusEnum.TO_BACK.getValue());
			rnStatus.add(RnStatusEnum.BACKED.getValue());
			rectifyNoticeReq.setRnStatus(rnStatus);
			return rectifyNoticeService.queryRectifyNotice(rectifyNoticeReq);
		} catch (Exception e) {
			logger.error("整改通知列表查询失败！", e);
			return null;
		}
	}
	/**
	 * 弹出查询屏
	 * @return
	 */
	@RequestMapping(value="/rectifyNoticeSearchPopPage")
	public ModelAndView viewRectifyNoticeSearchPopPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("constructmanage/rectifyNoticeBackSearchPopPage");
		return modelAndView; 
	}
	/**
	 * 查询详述
	 * @return
	 */
	@RequestMapping(value="/viewRectifyNotice")
	@ResponseBody
	public RectifyNotice viewRectifyNotice(HttpServletRequest request,@RequestParam(required=true)String id){
		try {
			return rectifyNoticeService.viewRectifyNotice(id);
		} catch (Exception e) {
			logger.error("整改信息详述查询失败！",e);
			return null;
		}
	}
	/**
	 * 保存通知
	 * @param rectifyNotice
	 * @return
	 */
	@RequestMapping(value="/saveRectifiyNotice")
	@ResponseBody
	public String saveRectifiyNotice(HttpServletRequest request,@RequestBody(required = true)RectifyNotice rectifyNotice){
		try {
			return rectifyNoticeService.saveRectifiyNotice(rectifyNotice);
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
			logger.error("保存整改信息失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 回复签证单
	 * @param evId
	 * @return
	 */
	@RequestMapping(value = "/replyRectifyNotice")
	@ResponseBody
	public String replyRectifyNotice(@RequestBody(required = true) String rnId){
		try {
			return rectifyNoticeService.replyRectifyNotice(rnId);
		} catch (Exception e) {
			logger.error("整改通知推送失败！", e);
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
			rectifyNoticeService.saveSignNotice(cwId);
		} catch (Exception e) {
			logger.error("保存失败！", e);
		}
	}
}
