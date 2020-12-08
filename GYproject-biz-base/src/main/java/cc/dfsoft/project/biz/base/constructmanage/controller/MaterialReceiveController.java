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
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialCollar;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialCollarService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 材料领用
 * @author pengtt
 */
@Controller
@RequestMapping(value="/materialReceive")
public class MaterialReceiveController {
	
	/**日志实例*/
	private static Logger logger = LoggerFactory.getLogger(MaterialReceiveController.class);
	
	/**材料清单服务接口*/
	@Resource
	MaterialListService materialListService;
	
	/**材料领用记录服务接口*/
	@Resource
	MaterialCollarService materialCollarService;
  
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		modelView.addObject("loginPost", loginInfo.getPost());//当前用户职务
		modelView.addObject("budgeterPost", PostTypeEnum.BUDGET_MEMBER.getValue());//预算员
		modelView.setViewName("constructmanage/materialReceive");
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
			logger.error("材料领用---材料清单列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 材料记录领用 
	 * @param materialLists
	 * @return
	 */
	@RequestMapping(value = "/saveMaterialLists")
	@ResponseBody
	public String saveMaterialLists(@RequestBody List<MaterialCollar> materialCollars){
		try {
			materialCollarService.saveMaterialCollars(materialCollars);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("材料领用---材料记录领用保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
}
