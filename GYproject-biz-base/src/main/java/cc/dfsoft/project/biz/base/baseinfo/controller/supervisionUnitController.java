package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.SupervisionQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SupervisionUnit;
import cc.dfsoft.project.biz.base.baseinfo.service.SupervisionUnitService;
import cc.dfsoft.project.biz.base.design.controller.SurveyResultRegisterController;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 监理单位
 * @author cui
 *
 */
@Controller
@RequestMapping(value="/supervisionUnit")
public class supervisionUnitController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SurveyResultRegisterController.class);
	
	/**监理单位服务接口*/
	@Resource
	SupervisionUnitService supervisionUnitService;

	
	/**
	 * 打开主页面
	 * @author cui
	 *  监理单位
	 */
	@RequestMapping(value="/main")
	public ModelAndView mainSupervision(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/supervisionUnit/supervisionUnit");
		return modelView;
	}
	
	/**
	 * @author cui
	 * 打开右侧编制计划页面
	 * @return
	 */
	@RequestMapping(value = "/viewSupervisionUnitPage")
	public ModelAndView viewSupervisionUnitPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("baseinfo/supervisionUnit/SupervisionRight");
		return modelview;		
	}
	
	/**
	 * 监理单位条件查询
	 * @author cui
	 * @createTime
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/querySupervision")
	@ResponseBody
	public Map<String,Object> querySupervisionUnit(SupervisionQueryReq supervisionQueryReq){
		try {
			Map<String,Object> map=supervisionUnitService.querySupervisionUnit(supervisionQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("监理单位信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author cui
	 * @createTime 2016-7-11
	 * @param id 监理单位id
	 * @return Contract
	 * @throws ParseException 
	 */
	@RequestMapping(value="/viewSupervision")
	@ResponseBody
	public SupervisionUnit viewSupervision(@RequestParam(required=true) String id) {
		SupervisionUnit supervisionUnit=supervisionUnitService.viewSupervisionById(id);
		return supervisionUnit;
	}
	
	/**
	 * 弹出搜索屏
	 * @author cui
	 * @createTime 2016-7-11
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/SupervisionSearchPopPage")
	public ModelAndView SupervisionSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("baseinfo/supervisionUnit/SupervisionSearchPopPage");
		return modelview;
	}
	
	/**
	 * 保存监理单位
	 * @author cui
	 * @createTime 2016-7-11
	 * @param  
	 * @return 
	 */
	@RequestMapping(value = "/saveSupervision")
	@ResponseBody
	public String saveSupervision(@RequestBody(required = true) SupervisionUnit supervisionUnit){
		try {
			return supervisionUnitService.saveSupervision(supervisionUnit);
		} catch (Exception e) {
			logger.error("监理单位信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 删除监理单位
	 * @author cui
	 * @createTime 2016-7-11
	 * 
	 */
	@RequestMapping(value = "/delSupervision")
	public void delSupervision(@RequestParam(required=true) String id) {
		try{
			supervisionUnitService.delSupervision(id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
	
	
	
	
	
	