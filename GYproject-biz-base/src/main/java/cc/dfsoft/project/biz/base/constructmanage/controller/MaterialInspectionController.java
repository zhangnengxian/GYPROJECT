package cc.dfsoft.project.biz.base.constructmanage.controller;

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

import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialInspection;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialInspectionService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 材料报验
 * @author pengtt
 * @createTime 2016-07-20
 *
 */
@Controller
@RequestMapping(value="/materialInspection")
public class MaterialInspectionController {
	
	/**日志实例*/
	private static Logger logger = LoggerFactory.getLogger(MaterialInspectionController.class);
	
	/**材料清单服务接口*/
	@Resource
	MaterialListService materialListService;
	
	/**材料报验服务接口*/
	@Resource
	MaterialInspectionService materialInspectionService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("constructmanage/materialInspection");
		return modelView;
	}
	
	/***
	 * 材料清单列表查询
	 * @param request
	 * @param materialListQueryReq
	 * @return
	 */
	@RequestMapping(value = "/queryMaterialList")
	@ResponseBody
	public Map<String,Object> queryMaterialList(HttpServletRequest request,MaterialListQueryReq materialListQueryReq){
		try {
			materialListQueryReq.setSortInfo(request);
			return materialListService.queryMaterialList(materialListQueryReq);
		} catch (Exception e) {
			logger.error("材料报验---材料清单列表查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 材料报验批量保存 
	 * @author pengtt
	 * @createTime 2016-07-20
	 * @param materialInspections
	 * @return
	 */
	@RequestMapping(value = "/saveMaterialInspections")
	@ResponseBody
	public String saveMaterialInspections(@RequestBody List<MaterialInspection> materialInspections){
		try { 
			materialInspectionService.saveMaterialInspections(materialInspections);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("材料报验---材料记录报验保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
