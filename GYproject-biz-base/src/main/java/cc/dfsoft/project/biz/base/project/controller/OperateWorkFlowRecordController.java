package cc.dfsoft.project.biz.base.project.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
@Controller
@RequestMapping(value="/operateWorkFlowRecord")
public class OperateWorkFlowRecordController {
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(OperateWorkFlowRecordController.class);
	
	/**操作记录*/
	@Resource
	OperateRecordService operateRecordService;
	
	/**
	 * 关联操作-弹出屏
	 * @author fuliwei  
	 * @date 2018年9月28日  
	 * @version 1.0
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(String projId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("projId", projId);
		modelView.setViewName("baseinfo/operateWorkFlow/operateWorkFlowRecordPop");
		return modelView;
	}
	
	/**
	 * 查询操作历史
	 * @author fuliwei  
	 * @date 2018年9月28日  
	 * @version 1.0
	 */
	@RequestMapping(value = "/queryOperateRecordList")
	@ResponseBody
	public Map<String,Object> queryOperateRecordList(HttpServletRequest request,OperateRecordQueryReq req){
		try {
			req.setSortInfo(request);
			req.setIsValid("1");
			Map<String,Object> map = operateRecordService.queryOperateRecordList(req);
			return map;
		} catch (Exception e) {
			logger.error("操作历史查询失败！", e);
			return null;
		}
	}
}
