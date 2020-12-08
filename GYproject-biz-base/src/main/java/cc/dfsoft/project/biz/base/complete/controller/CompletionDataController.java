package cc.dfsoft.project.biz.base.complete.controller;

import cc.dfsoft.project.biz.base.complete.service.CompletionDataService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 工程结算
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="/completionData")
public class CompletionDataController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(CompletionDataController.class);
	
	@Resource
	ProjectService projectService;
	/**
	 * @return
	 */
	@Resource
	CompletionDataService completionDataService;
	
	/**
	 *  打开主页面
	 * @author fuliwei
	 * @createTime 2017年6月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("complete/completionData");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @author fuliwei
	 * @createTime 2017年6月22日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("complete/completionDataRight");
		return modelview;
	}
	/**
	 * 查询待竣工资料打印的工程列表
	 * @return
	 */
	@RequestMapping(value="/queryProject")
	@ResponseBody
	public Map<String, Object> queryProject(HttpServletRequest request,ProjectQueryReq req){
		try {
			List<String> projStuList = new ArrayList<String>();
			List<ProjStatusEnum> enums = ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue());
			for(ProjStatusEnum projStatusEnum :enums){
				projStuList.add(projStatusEnum.getValue());
			}
			req.setProjStuList(projStuList);//工程状态,待施工-已竣工之间
			return projectService.queryProject(req);
		} catch (Exception e) {
			logger.error("待竣工资料打印的工程列表查询失败！", ExceptionUtil.getMessage(e));
			return null;
		}
	}


	/**
	 * @MethodDesc: 弹出搜索
	 * @Author zhangnx
	 * @Date 2019/3/28 15:56
	 */
	@RequestMapping(value = "/searchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("projStatus", ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.ALREADY_COMPLETED.getValue()));
		modelview.setViewName("complete/completionDataPopPage");
		return modelview;
	}


	/**
	 * 查询工程详述
	 * @return
	 */
	@RequestMapping(value="/viewProject")
	@ResponseBody
	public Project queryProject(@RequestParam(required=true) String id){
		try {
			Project proj = projectService.viewProject(id);
			return proj;
		} catch (Exception e) {
			logger.error("工程详述查询失败！", ExceptionUtil.getMessage(e));
			return null;
		}
	}
	/**
	 * 根据页面复选按钮组装，批量打印竣工资料
	 */
	@RequestMapping(value="/printCompletionData")
	@ResponseBody
	public List<Object> printCompletionData(@RequestParam(required=true) String projId,String dataTypes){
		try {
			return completionDataService.printCompletionData(dataTypes,projId);
		} catch (Exception e) {
			logger.error("竣工资料打印打印资料组装失败！", ExceptionUtil.getMessage(e));
			return null;
		}
	}
}
