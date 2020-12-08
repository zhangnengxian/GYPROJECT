package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.service.ConnectContentService;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConnectRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecordOrder;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchRecordOrderService;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 碰口记录
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/touchRecord")
public class TouchRecordController {
   
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(TouchRecordController.class);
	
	/**工程服务接口*/
	   @Resource
	   ProjectService projectService;
	   
    /**碰口记录单接口*/
	   @Resource
	   TouchRecordOrderService touchRecordOrderService; 
	   
	/**碰口记录接口*/
	   @Resource
	   TouchRecordService touchRecordService;
	   
	/**碰口内容接口*/
	   @Resource   
	   ConnectContentService connectContentService;
	   
	/**
	 * 碰口记录主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("fieldJgjC",PostTypeEnum.SUJGJ.getValue());				//现场监理师
		modelView.addObject("fieldPrincipal",PostTypeEnum.SUB_FIELDPRINCIPAL.getValue());//现场负责人
		modelView.addObject("ongcDeputy",PostTypeEnum.WEBSITE_ENGINEER.getValue()); 	//管网工艺员
		modelView.addObject("welder",PostTypeEnum.WELDER.getValue()); 			//焊工
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); //img url
		modelView.setViewName("constructmanage/touchRecord");
		return modelView;
	}
	
	/**
	 * 施工单位内容
	 * @return
	 */
	@RequestMapping(value = "/queryConContent")
	@ResponseBody
	public Map<String, Object> queryConContent(HttpServletRequest request,ConnectRecordQueryReq connectRecordReq){
		try {
			//connectRecordReq.setUnitType(UnitTypeEnum.CONNECT_CONTENT1.getValue());
			connectRecordReq.setUnitType("6");
			connectRecordReq.setSortInfo(request);
			return touchRecordService.queryConnectContent(connectRecordReq);
		} catch (Exception e) {
			logger.error("信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 天然气内容
	 * @return
	 */
	@RequestMapping(value = "/queryNoContent")
	@ResponseBody
	public Map<String, Object> queryNoContent(HttpServletRequest request,ConnectRecordQueryReq connectRecordReq){
		try {
			//connectRecordReq.setUnitType(UnitTypeEnum.CONNECT_CONTENT2.getValue());
			connectRecordReq.setUnitType("7");
			connectRecordReq.setSortInfo(request);
			return touchRecordService.queryConnectContent(connectRecordReq);
		} catch (Exception e) {
			logger.error("信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 监理内容
	 * @return
	 */
	@RequestMapping(value = "/querySuContent")
	@ResponseBody
	public Map<String, Object> querySuContent(HttpServletRequest request,ConnectRecordQueryReq connectRecordReq){
		try {
			//connectRecordReq.setUnitType(UnitTypeEnum.CONNECT_CONTENT2.getValue());
			connectRecordReq.setUnitType("4");
			connectRecordReq.setSortInfo(request);
			return touchRecordService.queryConnectContent(connectRecordReq);
		} catch (Exception e) {
			logger.error("信息查询失败！", e);
			return null;
		}
	}
	
//	/**
//	 * 查碰口记录
//	 * @return
//	 */
//	@RequestMapping(value = "/queryUnitContent")
//	@ResponseBody
//	public Map<String, Object> queryUnitContent(HttpServletRequest request,ConnectRecordQueryReq connectRecordReq){
//		try {
//			connectRecordReq.setSortInfo(request);
//			return touchRecordService.queryConnectRecord(connectRecordReq);
//		} catch (Exception e) {
//			logger.error("要求内容信息查询失败！", e);
//			return null;
//		}
//	}
	
	/**
	 * 碰口记录单详述
	 * @return
	 */
	@RequestMapping(value = "/touchRecordOrderDetail")
	@ResponseBody
	public ConnectRecordOrder touchRecordOrderDetail(@RequestParam(required=true) String id){
		try {
			return touchRecordOrderService.touchRecordOrderDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 碰口记录保存
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/touchRecordSave")
	@ResponseBody
	public String touchRecordSave(@RequestBody(required=true) ConnectRecordOrder connectRecordOrder){
		try {
			touchRecordOrderService.touchRecordOrderSave(connectRecordOrder);
		    return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
}
