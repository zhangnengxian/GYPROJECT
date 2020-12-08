package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.complete.dto.IgniteReq;
import cc.dfsoft.project.biz.base.complete.service.IgniteService;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 点火单打印
 * @author cui
 * @createTime 2017-11-16
 *
 */

@Controller
@RequestMapping(value="/ignitePrint")
public class IgnitePrintController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(IgnitePrintController.class);

	/**
	 * 点火单接口服务
	 */
	@Resource
	IgniteService igniteService;
	/**
	 * 打开主页面
	 * @author cui
	 * @createTime 2017-11-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
		modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
		modelView.setViewName("complete/ignitePrint");
		return modelView;
	}

	/**
	 * 弹屏查询
	 * @return
	 */
	@RequestMapping(value = "/igniteSearchPopPage")
	public ModelAndView igniteSearchPopPage() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
		modelView.setViewName("complete/igniteSearchPop");
		return modelView;
	}

	/**
	 * 点火工程列表条件查询
	 * @author cui
	 * @createTime 2017-8-10
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryIgnite")
	@ResponseBody
	public Map<String,Object> queryIgnite(HttpServletRequest request, IgniteReq igniteReq){
		try {
			return igniteService.queryIgnite(igniteReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 点火单打印标记
	 * @author cui
	 * @createTime 2017-11-16
	 * @param  igniteId
	 * @return String
	 */
	@RequestMapping(value = "/signIgnitePrint")
	@ResponseBody
	public String signIgnitePrint(@RequestBody(required = true) String igniteId){
		try {
			igniteService.signIgnitePrint(igniteId);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("打印标记失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
