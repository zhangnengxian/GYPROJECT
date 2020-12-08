package cc.dfsoft.project.biz.base.baseinfo.controller;

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

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.AcceptTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.CorrelationTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.ScaleTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/correlation")
public class CorrelationController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(CorrelationController.class);
	
	/**关联关系服务接口*/
	@Resource
	CorrelationService correlationService;
	

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	

	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("baseinfo/correlation/correlation");
		return modelView;
	}
	
	/**
	 * 弹出搜索屏
	 * @author zhangmeng
	 * @createTime 2016-7-15
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/correlationSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.addObject("correlationType",CorrelationTypeEnum.values());//关联类型
		modelview.addObject("acceptType",AcceptTypeEnum.values());			//立项方式类型
		modelview.addObject("scaleType",ScaleTypeEnum.values());			//规模类型
		modelview.setViewName("baseinfo/correlation/correlationSearchPopPage");
		return modelview;
	}
	
	/**
	 * 打开右侧页面
	 * @return
	 */
	@RequestMapping(value="/correlationView")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("correlationType",CorrelationTypeEnum.values());//关联类型
		modelView.addObject("acceptType",AcceptTypeEnum.values());			//立项方式类型
		modelView.addObject("scaleType",ScaleTypeEnum.values());			//规模类型
		modelView.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelView.setViewName("baseinfo/correlation/correlationRight");
		return modelView;
	}
	
	/**
	 * 关联关系列表查询
	 * @param request
	 * @param correlationReq
	 * @return
	 */
	@RequestMapping(value = "/queryCorrelation")
	@ResponseBody
	public Map<String,Object> queryCorrelation(HttpServletRequest request,CorrelationReq correlationReq){
		try {
			correlationReq.setSortInfo(request);
			if(StringUtils.isBlank(correlationReq.getCorpId())){
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				correlationReq.setCorpId(loginInfo.getCorpId());
			}
			Map<String,Object> map = correlationService.queryCorrelation(correlationReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 *保存关联关系
	 * @param correlation
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateCorrelation")
	public String saveOrUpdateCorrelation(@RequestBody(required = true) Correlation correlation){
		try{
			correlationService.saveOrUpdateCorrelation(correlation);
			 return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("工程类型保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 删除关联关系
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCorrelation")
	public String deleteCorrelation(String corId){
		try{
			return correlationService.deleteCorrelation(corId);
		}catch(Exception e){
			logger.error("关联关系删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 根据关联类型查相关信息及关联信息
	 * @param corType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCorrelateInfoId")
	public Map<String, List<Map<String, String>>> queryProjectTypeByDeptId(String corType){
		try{
			return correlationService.queryCorrelateInfoId(corType);
		}catch(Exception e){
			logger.error("查询工程类型失败！",e);
			return null;
		}
	}

	/**
	 * 立项页面查关联关系
	 * @param corType
	 * @param correlateInfoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCorrelationList")
	public List<Correlation> queryCorrelationList(String corType,String correlateInfoId,String acceptType,String scaleType,String correlatedInfoId){
		try{
			CorrelationReq req=new CorrelationReq();
			req.setAcceptType(acceptType);
			req.setCorType(corType);
			req.setCorrelateInfoId(correlateInfoId);
			req.setScaleType(scaleType);
			req.setCorrelatedInfoId(correlatedInfoId);
			return correlationService.findCorType(req);
		}catch(Exception e){
			logger.error("关联查询失败！",e);
			return null;
		}
	}
}


