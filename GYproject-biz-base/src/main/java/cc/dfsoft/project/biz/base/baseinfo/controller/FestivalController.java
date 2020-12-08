package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.FestivalReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Festival;
import cc.dfsoft.project.biz.base.baseinfo.enums.FestivalTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.IsValidEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 节假日配置控制类
 * @author liaoyq
 * @createTime 2018年5月8日
 */
@Controller
@RequestMapping("/festival")
public class FestivalController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(FestivalController.class);
	
	/** 节假日服务接口 */
	@Resource
	private FestivalService festivalService;
	/**
	 * 初始化节假日管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("festival/festival");
		return modelview;
	}
	
	/**
	 * 业务操作页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewPage")
	public ModelAndView viewPage(Model model) {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("FestivalTypeEnum",FestivalTypeEnum.values());
		modelview.addObject("IsValidEnum",IsValidEnum.values());
		modelview.setViewName("/festival/festivalRight");
		return modelview;
	}
	
	/**
	 * 查询节假日列表信息
	 * @return
	 */
	@RequestMapping(value = "/queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request, FestivalReq festivalReq){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			//设置排序信息
			festivalReq.setSortInfo(request);
			festivalReq.setIsDel("0");
			 resultMap = festivalService.queryList(festivalReq);
			return resultMap;
		}catch(Exception e){
			logger.error("查询节假日信息发生异常。", e);
			return resultMap;
		}
	}
	/**
	 * 保存节假日配置
	 * @return
	 */
	@RequestMapping(value = "/saveFestival")
	@ResponseBody
	public String saveFestival(@RequestBody(required = true)Festival festival){
		try{
			return festivalService.saveFestival(festival);
		}catch(Exception e){
			logger.error("保存节假日信息发生异常。", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 查询节假日配置详述
	 * @return
	 */
	@RequestMapping(value = "/viewDetail")
	@ResponseBody
	public Festival saveFestival(String id){
		try{
			return festivalService.findById(id);
		}catch(Exception e){
			logger.error("查询节假日配置详述发生异常。", e);
			return null;
		}
	}
	/**
	 * 删除节假日配置
	 * @return
	 */
	@RequestMapping(value = "/delFestival")
	@ResponseBody
	public String delFestival(@RequestBody(required = true)String id){
		try{
			festivalService.delFestival(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("删除节假日配置发生异常。", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
