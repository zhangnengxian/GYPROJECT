package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.StaleObjectStateException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper.StandardWarningHandler;
import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fr.function.MAP;

import cc.dfsoft.project.biz.base.inspection.dto.MeasurementReq;
import cc.dfsoft.project.biz.base.inspection.entity.Measurement;
import cc.dfsoft.project.biz.base.inspection.service.MeasurementService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 计量记录表controller
 * @author wanghuijun
 * @createTime 2018年9月7日
 */
@Controller
@RequestMapping(value = "/measurementTable")
public class measurementTableController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(measurementTableController.class);
	
	@Resource
	MeasurementService measurementService;

	/**
	 * 打开主页面
	 * 
	 * @author wanghuijun
	 * @createTime 2018年9月7日
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //取得当前登录人信息
		modelAndView.addObject("loginInfo", loginInfo);	   //将当前登录人信息传递到前台页面
		modelAndView.setViewName("inspection/measurementTable");
		return modelAndView;

	}

	/**
	 * 根据工程Id查询计量表
	 * 
	 * @author wanghuijun
	 * @createTime 2018年9月17日
	 * @return
	 */
	@RequestMapping(value = "/queryMeasurement")
    @ResponseBody
	public Map<String, Object> queryMeasurement(HttpServletRequest request,MeasurementReq measurementReq) {
    try {
    	 Map<String,Object> map = measurementService.queryMeasurement(measurementReq);
         return map;
	} catch (Exception e) {
		logger.error("计量表信息查询失败！", e);
		return null;
	}

	}
	
	
   /**
     * 保存计量表录入
     * @author wanghuijun
     * @createTime 2018年9月18日
     * @return
    */
	@RequestMapping(value = "/savaMeasurement")
	@ResponseBody
	public String savaMeasurement(HttpServletRequest request,@RequestBody(required=true) Measurement measurement){
		try{
			measurementService.savaMeasurement(measurement);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(HibernateOptimisticLockingFailureException e ){
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", e);
			return "already";
		}catch(Exception e){
			logger.error("计量表录入保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 根据计量表id查询计量表详述
	 * @author wanghuijun
	 * @createTime 2018年9月18日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewMeasurement")
	@ResponseBody
	public  Measurement viewMeasurement(@RequestParam(required=true) String id) {
		try {
			return measurementService.viewMeasurement(id);
		} catch (Exception e) {
			logger.error("计量表详情查询失败！",e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据计量表msId删除记录
	 * @author wanghuijun
	 * @createTime 2018年9月19日
	 * @param msId
	 * @return
	 */
	@RequestMapping(value = "/byMsIdDeleteMeasurement")
	@ResponseBody
	public String byMsIdDeleteMeasurement(@RequestParam(required=true) String msId){   
		try {
			measurementService.byMsIdDeleteMeasurement(msId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

}
