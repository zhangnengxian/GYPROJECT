package cc.dfsoft.project.biz.base.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;

import com.alibaba.fastjson.JSONArray;
/**
 * 工程分布
 * @author pengtt
 * @createTime 2016-06-21
 */
@Controller
@RequestMapping(value="/projectMap")
public class ProjectMapController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ProjectSummaryController.class);
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	@Resource
	SignatureService signatureService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/map/projectMap");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(@RequestBody ProjectQueryReq projectQueryReq){
		try {
			Map<String,Object> map = projectService.queryDistributionProject(projectQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 工程及签字信息列表条件查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryProjectSign")
	@ResponseBody
	public List<Project> queryProjectSign(@RequestBody ProjectQueryReq projectQueryReq){
		try {
			//List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
			List<ProjStatusEnum> list=ProjStatusEnum.getThanValueNew(ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue(),ProjStatusEnum.TO_DETERMINE_AMOUNT.getValue());
			List<String> statusList=new ArrayList<String>();
			
			for(ProjStatusEnum pe:list){
				statusList.add(pe.getValue());
			}
			projectQueryReq.setProjStuList(statusList);
			List<Project> result = projectService.queryProjectSign(projectQueryReq);
			return result;
		} catch (Exception e) {
			logger.error("工程及签字信息查询失败！", e);
			return null;
		}
	} 
	
	@RequestMapping(value = "/echartsMapData")
	@ResponseBody
	public Map<Object,Object> echartsMapData(@RequestBody ProjectQueryReq projectQueryReq){
		try {
			List<Project> result = projectService.queryProjectSign(projectQueryReq);
			Map<Object,Object> map = new HashMap<Object,Object>();
			JSONArray data = new JSONArray();
			Map<Object,Object> geoCoordMap = new HashMap<Object,Object>();
		    for(int i=0;i<result.size();i++){
		    	Project pro=result.get(i);
		    	Map<String,Object> m = new HashMap<String,Object>();
		    	m.put("name", pro.getProjName());
		    	if(null == pro.getConfirmTotalCost()){
					m.put("value", "暂无");
				}else{
		    		m.put("value", pro.getConfirmTotalCost());
				}
		    	data.add(m);
		    	List<String> dl = new ArrayList<String>();
		    	dl.add(pro.getProjLongitude());
		    	dl.add(pro.getProjLatitude());
		    	geoCoordMap.put(pro.getProjName(),dl);
		    }
			map.put("data", data);
			map.put("geoCoordMap", geoCoordMap);
			return map;
		} catch (Exception e) {
			logger.error("工程及签字信息查询失败！", e);
			return null;
		}
	} 
	
	@RequestMapping(value="/signMain")
	public ModelAndView signMain(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/view/signMonitoring");
		return modelView;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @return
	 */
	@RequestMapping(value = "/querySignature")
	@ResponseBody
	public List<Signature> querySignature(){
		try {
			List<Signature> signs = signatureService.getAll();
			return signs;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	@RequestMapping(value="/queryDetail")
	public ModelAndView queryDetail(HttpServletRequest request){
		String businessOrderId=request.getParameter("businessOrderId");
		String menuId=request.getParameter("menuId");
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("project/map/projectMapDetail");
		return modelView;
	}
}
