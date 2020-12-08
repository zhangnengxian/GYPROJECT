package cc.dfsoft.project.biz.base.constructmanage.controller;

import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Delay;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import cc.dfsoft.project.biz.base.constructmanage.service.DelayService;
import cc.dfsoft.uexpress.common.constant.Constants;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 延期申请
 */
@Controller
@RequestMapping(value="/delay")
public class DelayController {
    /** 日志实例 */
    private static Logger logger = LoggerFactory.getLogger(DelayController.class);
    @Autowired
    private DelayService delayService;
    @RequestMapping(value = "/main")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("imgUrl", Constants.DISK_PATH + Constants.SIGN_DISK_PATH); // img
        modelAndView.setViewName("constructmanage/delay");
        return modelAndView;
    }

    @RequestMapping(value = "/getDateList")
    @ResponseBody
    public Map<String, Object> getDateList(HttpServletRequest request, RectifyNoticeReq rectifyNoticeReq) {
        try {
            rectifyNoticeReq.setSortInfo(request);
            return delayService.getDataList(rectifyNoticeReq);
        } catch (Exception e) {
            logger.error("延期申请列表查询失败！", e);
            return null;
        }
    }

    @RequestMapping(value = "/saveDelay")
    @ResponseBody
    public String saveDelay(HttpServletRequest request,@RequestBody(required = true) Delay delay) {
        try {
            String str = delayService.saveDelay(delay);
            return str;
        } catch (HibernateOptimisticLockingFailureException e) {
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
     * 查询详述
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/viewDelay")
    @ResponseBody
    public Delay viewDelay(HttpServletRequest request, @RequestParam(required = true) String id) {
        try {
            return delayService.viewDelay(id);
        } catch (Exception e) {
            logger.error("整改信息详述查询失败！", e);
            return null;
        }
    }


    /**
     * 弹出查询屏
     *
     * @return
     */
    @RequestMapping(value = "/delaySearchPopPage")
    public ModelAndView delaySearchPopPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("constructmanage/delaySearchPopPage");
        return modelAndView;
    }

    /**
     * 推送
     * @param adId
     * @return
     */
    @RequestMapping(value = "/push")
    @ResponseBody
    public String push(@RequestBody(required = true) String adId) {
        try {
            return delayService.push(adId);
        } catch (Exception e) {
            logger.error("延期申请推送失败！", e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }
}
