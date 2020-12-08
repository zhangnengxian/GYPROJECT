package cc.dfsoft.project.biz.ifs.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.withgas.controller.DeliverDateRegisterController;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;
import cc.dfsoft.uexpress.common.constant.Constants;
/**
 * 
 * 描述:手动同步接口信息
 * @author liaoyq
 * @createTime 2019年9月30日
 */
@Controller
@RequestMapping(value="/synchroService")
public class SynchroServiceController {

	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SynchroServiceController.class);
	
	
	@Resource
	ProjectService projectService;
	
	/**
	 * 
	 * 注释：
	 * @author liaoyq
	 * @createTime 2019年9月30日
	 * @param gasProject
	 * @return
	 *
	 */
	@RequestMapping(value = "/synchroServiceInfo")
	@ResponseBody
	public String synchroServiceInfo(@RequestBody(required = true) WebserviceLog webserviceLog){
		try {
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("手动同步信息失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
}
