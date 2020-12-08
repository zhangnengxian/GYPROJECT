package cc.dfsoft.project.biz.base.project.controller;


import java.util.List;
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

import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 出资方式
 * @author fuliwei
 *
 */
@Controller
@RequestMapping(value="contributionMode")
public class ContributionModeController {
	
	/**日志实例*/
	private static Logger logger=LoggerFactory.getLogger(ContributionModeController.class);
	
	/**工程类型服务接口*/
	@Resource
	ProjectTypeService projectTypeService;
	
	/**出资方式*/
	@Resource
	ContributionModeService contributionModeService;
	
	
	/**
	 * 打开主页面
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/contributionMode/contributionMode");
		return modelView;
	}
	
	/**
	 *  打开右侧
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/projectScaleView")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		/*List<ProjectType> list=projectTypeService.queryAllList();
		modelView.addObject("projTypeList", list);*/
		modelView.setViewName("baseinfo/contributionMode/contributionModeRight");
		return modelView;
	}
	
	/**
	 * 出资方式
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryContributionMode")
	public Map<String,Object> queryContributionModeList(HttpServletRequest request,ProjectScaleReq req){
		try{
			req.setSortInfo(request);
			return contributionModeService.queryContributionMode(req);
		}catch(Exception e){
			logger.error("出资方式列表查询失败！");
			return null;
		}
	}
	
	

	/**
	 * 保存出资方式
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveContributionMode")
	public String saveContributionMode(@RequestBody(required = true) ContributionMode projectScale){
		try{
			
			contributionModeService.saveContributionMode(projectScale);
			 return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("工程类型保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 删除出资方式
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteContributionMode")
	public String deleteContributionMode(String id){
		try{
			contributionModeService.deleteById(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("删除出资方式失败");
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
