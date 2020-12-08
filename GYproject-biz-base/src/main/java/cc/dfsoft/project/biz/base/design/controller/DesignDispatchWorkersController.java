package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassDesc: 设计派工
 * @author: zhangnx
 * @date: 11:36 2018/11/19
 * @version: V1.0
 */
@Controller
@RequestMapping(value="/designDispatchWorkers")
public class DesignDispatchWorkersController {

	@Resource
	ProjectService projectService;
	@Resource
	DesignInfoService designInfoService;

	private final String menuId="1102102";

/**
 * @methodDesc: 主页面
 * @author: zhangnx
 * @date: 10:16 2018/11/28
 */
	@RequestMapping(value="/main")
	public ModelAndView main(ModelAndView modelView){
		modelView.setViewName("design/designDispatchWorkers");
		return modelView;
	}


	/**
	 * @methodDesc: 查询工程列表
	 * @author: zhangnx
	 * @date: 10:17 2018/11/28
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq) throws Exception {
		projectQueryReq.setSortInfo(request);
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		List<String> list = new ArrayList<>();

		List<DataFilerSetUpDto> projStatusList = Constants.getDataFilterMapByKey(loginInfo.getCorpId() + "_" + menuId);
		if (projStatusList!=null&&projStatusList.size()>0) {
			DataFilerSetUpDto dataFilerSetUpDto = projStatusList.get(0);
			String[] split = dataFilerSetUpDto.getSupSql().split(",");
			list = Arrays.asList(split);
		}else {
			list.add(ProjStatusEnum.TO_DESIGN_DISPATCH.getValue());//待设计派工状态查询
		}
		projectQueryReq.setProjStuList(list);
		return projectService.toDesignDispatchProjectList(projectQueryReq);
		
	}




	/**
	 * @methodDesc: 右侧详细页面
	 * @author: zhangnx
	 * @date: 10:16 2018/11/28
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(String projId,String menuId,ModelAndView modelView){

		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Project pro=projectService.queryProjectById(projId);

		List<DataFilerSetUpDto> projStatusList = Constants.getDataFilterMapByKey(loginInfo.getCorpId() + "_" + menuId);

		//corpId_projType_menuId
		String ctmKey=(pro!=null?pro.getCorpId():loginInfo.getCorpId())+"_"+(pro!=null?pro.getProjectType(): ProjLtypeEnum.CIVILIAN.getValue())+"_"+menuId;
		//corpId_menuId
		String cmKey=(pro!=null?pro.getCorpId():loginInfo.getCorpId())+"_"+menuId;

		String viewPageUrl= "designDispatchWorkersRight";//默认
		String resultPage=sysConfig(ctmKey)!=null?sysConfig(ctmKey).toString():sysConfig(cmKey)!=null?sysConfig(cmKey).toString():viewPageUrl;

		modelView.addObject("isFlag",projStatusList!=null&&projStatusList.size()>0?false:true);
		modelView.setViewName("design/"+resultPage);
		return modelView;
	}




	public Object sysConfig(String key){
		return Constants.getSysConfigByKey(key);
	}




	/**
	 * @methodDesc: 查询详细
	 * @author: zhangnx
	 * @date: 10:17 2018/11/28
	 */
	@RequestMapping(value="/queryProjectDetail")
	@ResponseBody
	public Project queryDetail(@RequestParam(required=true) String id){
		Project pro=projectService.viewProject(id);
		return pro;
	}

	/**
	 * @methodDesc: 派工更新工程
	 * @author: zhangnx
	 * @date: 10:18 2018/11/28
	 */
	@RequestMapping(value = "/updateProject")
	@ResponseBody
	public String saveSurveyInfo(@RequestBody(required = true) DesignDispatchDto designDispatchReq){
		try {
			designInfoService.designDispatchUpdateProject(designDispatchReq);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * @methodDesc: 查询设计员
	 * @author: zhangnx
	 * @date: 10:18 2018/11/28
	 */
	@RequestMapping(value = "/queryDesigner")
	@ResponseBody
	public Map<String,Object> queryDesigner(DesignerQueryReq req){

		LoginInfo login=SessionUtil.getLoginInfo();
		Project project=projectService.queryProjectById(req.getProjId()!=null?req.getProjId():"");
		try {
			req.setPost(PostTypeEnum.DESIGNER.getValue());  //设计员
			req.setProjStatus(ProjStatusEnum.TO_RESULT_REGISTRY.getValue());	//待现场踏勘
			req.setCorpId(project!=null?project.getCorpId():login.getCorpId());
			return designInfoService.queryDesigner(req);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@RequestMapping(value = "/projectSearchPopPage")
	public ModelAndView projectSearchPopPage(ModelAndView modelview) {
		modelview.addObject("post", PostTypeEnum.DESIGNER.getValue());
		modelview.setViewName("design/designDispatchSearchPopPage");
		return modelview;
	}
}
