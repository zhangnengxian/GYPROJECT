package cc.dfsoft.project.biz.base.baseinfo.controller;

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
import cc.dfsoft.project.biz.base.baseinfo.entity.ConstructionUnit;
import cc.dfsoft.project.biz.base.baseinfo.service.ConstructionUnitService;
import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 分包单位
 * @author 刘博
 *
 */

@Controller
@RequestMapping(value="/constructionUnit")
public class ConstructionUnitController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ConstructionUnitController.class);
	
	/**分包单位服务接口*/
	@Resource
	ConstructionUnitService constructionUnitService;  
		
	/**
	 * 打开分包主页面
	 * @author 刘博
	 * @return
	 */
	@RequestMapping(value="/constructionUnit")
	public ModelAndView constructionUnit(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/constructionUnit/constructionUnit");
		return modelView;
	}
	
	/**
	 * 打开分包右侧详述页面
	 * @author 刘博
	 * @return
	 */
	@RequestMapping(value = "/viewPageConstructionUnit")
	public ModelAndView viewPageConstructionUnit() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("baseinfo/constructionUnit/constructionUnitRight");
		return modelview;
		
	}
	
	
	/**
	 * 详述
	 * @author 刘博
	 * @createTime 2016-7-11
	 * @param id 分包单位id
	 * @return ConstructionUnit
	 */
	@RequestMapping(value="/viewConstructionUnit")
	@ResponseBody
	public ConstructionUnit viewConstructionUnit(@RequestParam(required=true) String id){
		ConstructionUnit constructionUnit = constructionUnitService.viewConstructionUnitById(id);
		return constructionUnit;
	}
	
	/**
	 * 保存分包单位
	 * @author 刘博
	 * @createTime 2016-7-12
	 * @return ModelAndView
	 */
	@ResponseBody
	@RequestMapping(value = "/saveConstructionUnit")
	public String saveConstructionUnit(@RequestBody(required = true) ConstructionUnit constructionUnit){
		try{
			return constructionUnitService.saveConstructionUnit(constructionUnit);
		}catch(Exception e){
			logger.error("分包单位保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
	}
	
	/**
	 * 删除分包单位
	 * @author 刘博
	 * @createTime 2016-7-12
	 * @return ModelAndView
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteConstructionUnit")
	public String deleteConstructionUnit(String id){
		try{
			constructionUnitService.deleteConstructionUnit(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("分包单位删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 查询弹屏分包单位
	 * @author 刘博
	 * @createTime 2016-7-12
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewConstructionUnitPop")
	public ModelAndView viewConstructionUnit(){
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("baseinfo/constructionUnit/constructionUnitSearchPopPage");
		return modelview;
	}
	
	/**
	 * 分包单位列表查询
	 * @author 刘博
	 * @param PageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryConstructionUnit")
	@ResponseBody
	public Map<String, Object> queryConstructionUnit(HttpServletRequest request,ConstructionUnitReq constructionUnitReq) {
		try {
			constructionUnitReq.setSortInfo(request);
			Map<String, Object> map = constructionUnitService.queryConstructionUnit(constructionUnitReq);
		    return map;
		} catch (Exception e) {
			logger.error("分包单位列表查询失败！", e);
			return null;
		}
	}
}
