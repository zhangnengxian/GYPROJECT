package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.dto.DealyReasonQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DelayReason;
import cc.dfsoft.project.biz.base.baseinfo.service.DelayReasonService;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/delayReason")
public class DelayReasonController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(DelayReasonController.class);
	
	@Resource
	DelayReasonService delayReasonService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/delayReason/delayReason");
		return modelView;
	}
	
	/**
	 * 打开右侧
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/delayReason/delayReasonRight");
		return modelView;
	}
	
	/**
	 * 资料标准列表条件查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryDelayReasonList")
	@ResponseBody
	public Map<String,Object> queryDelayReasonList(HttpServletRequest request,DealyReasonQueryReq dealyReasonQueryReq){
		try {
			dealyReasonQueryReq.setSortInfo(request);
			Map<String,Object> map = delayReasonService.queryDealyReasonList(dealyReasonQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("延期原因查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 弹出搜索屏
	 * @author zhangjj
	 * @createTime 2016-11-28
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectTypeSearchPop")
	public ModelAndView projectTypeSearchPop() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("baseinfo/delayReason/delayReasonSearchPop");
		return modelview;
	}
	
	/**
	 * 保存延期原因
	 * @author
	 * @createTime 2016-12-8
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateDealyReason")
	public String saveOrUpdateDealyReason(@RequestBody(required = true) DelayReason dealyReason){
		try{
			 delayReasonService.saveOrUpdateDealyReason(dealyReason);
			 return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("延期原因保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 删除延期原因
	 * @author
	 * @createTime 2016-12-8
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delDelayReason")
	public String  delDelayReason(String id){
		try{
			delayReasonService.delDealyReason(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("延期原因删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
