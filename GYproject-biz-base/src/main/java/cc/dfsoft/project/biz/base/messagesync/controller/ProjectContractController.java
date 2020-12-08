package cc.dfsoft.project.biz.base.messagesync.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.dfsoft.project.biz.base.messagesync.conrtact.ProjectQueryInfo;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.service.ProjectService;

/**
 * 
 * 描述:为合同管理系统提供的获取数据信息接口
 * @author liaoyq
 * @createTime 2019年12月5日
 */
@Controller
@RequestMapping(value="/project/api/projectContract")
public class ProjectContractController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectContractController.class);
	@Resource
	ProjectService projectService;
	
	@RequestMapping(value="/queryProjectContracts",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryProjectContracts(@RequestBody ProjectQueryInfo queryReqInfo){
		try {
			ProjectQueryReq queryReq = queryReqInfo!=null?queryReqInfo.getProjectQueryReq():new ProjectQueryReq();
			Map<String,Object> map= projectService.queryProjectContractService(queryReq);
			Map<String,Object> mapResult = new HashMap<String, Object>();
			mapResult.put("recordsTotal",map.get("recordsTotal"));
			mapResult.put("data",map.get("data"));
	        return mapResult;
		} catch (Exception e) {
			logger.error("合同管理系统信息查询失败！", e);
			return null;
		}
	}
}
