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
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.service.ProjectScaleService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Controller
@RequestMapping(value="/projectScale")
public class ProjectScaleController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectScaleController.class);
	
	/**
	 * 工程规模
	 */
	@Resource
	ProjectScaleService projectScaleService;
	/**
	 * 工程类型
	 */
	@Resource
	ProjectTypeService projectTypeService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("projectscale/projectScale");
		return modelView;
	}
	
	/**
	 * 打开右侧
	 * @return
	 */
	@RequestMapping(value="/projectScaleView")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelView.addObject("projTypeList", list);
		modelView.setViewName("projectscale/projectScaleRight");
		return modelView;
	}
	
	/**
	 * 工程规模表条件查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectScale")
	@ResponseBody
	public Map<String,Object> queryprojectScale(HttpServletRequest request,ProjectScaleReq projectScaleReq){
		try {
			projectScaleReq.setSortInfo(request);
			Map<String,Object> map = projectScaleService.queryProjectScaleList(projectScaleReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
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
	@RequestMapping(value = "/projectScaleSearchPop")
	public ModelAndView projectScaleSearchPop() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("projectScale/projectScaleSearchPop");
		return modelview;
	}
	
	/**
	 * 保存工程规模
	 * @author zhangjj
	 * @createTime 2016-11-28
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateProjectScale")
	public String saveOrUpdateProjectScale(@RequestBody(required = true) ProjectScale projectScale){
		try{
			if(StringUtil.isNotBlank(projectScale.getProjTypeId())){
				ProjectType pt=new ProjectType();
				pt.setProjTypeId(projectScale.getProjTypeId());
				projectScale.setProjType(pt);
			}
			 projectScaleService.saveOrUpdateProjectScale(projectScale);
			 return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("工程类型保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 删除工程规模
	 * @author zhangjj
	 * @createTime 2016-11-28
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteProjectScale")
	public String deleteprojectScale(String id){
		try{
			projectScaleService.delProjectScale(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("工程类型删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}


