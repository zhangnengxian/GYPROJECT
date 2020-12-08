package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.common.enums.VersionTypeEnum;
import cc.dfsoft.project.biz.base.common.service.VersionService;
import cc.dfsoft.project.biz.base.project.enums.CostTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.dto.PriceVersionDto;
import cc.dfsoft.project.biz.base.subpackage.dto.PricedBoqReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PricedBoq;
import cc.dfsoft.project.biz.base.subpackage.service.PricedBoqService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;

@Controller
@RequestMapping(value="/pricedBoq")
public class PricedBoqController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ConstructionUnitController.class);
	
	@Resource
	PricedBoqService pricedBoqService;
	
	@Resource
	VersionService versionService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 打开工程量标准主页面
	 * @author 刘博
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView PricedBoq(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("costTypeDes", CostTypeEnum.values());
		modelView.setViewName("baseinfo/pricedBoq/pricedBoq");
		return modelView;
	}
	
	/**
	 * 工程量标准列表查询
	 * @author 刘博
	 * @param PageSortReq
	 * @return
	 */
	@RequestMapping(value = "/queryPricedBoq")
	@ResponseBody
	public Object queryPricedBoq(HttpServletRequest request,PricedBoqReq pricedBoqReq) {
		try {
			pricedBoqReq.setSortInfo(request);
			if(StringUtils.isBlank(pricedBoqReq.getCorpId())){
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				pricedBoqReq.setCorpId(loginInfo.getCorpId());
			}
			Map<String, Object> map = pricedBoqService.queryPricedBoq(pricedBoqReq);
		    return map.get("data");
		} catch (Exception e) {
			logger.error("工程量标准列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 打开工程量标准右侧详述页面
	 * @author 刘博
	 * @return
	 */
	@RequestMapping(value = "/viewPagePricedBoqRight")
	public ModelAndView viewPagePricedBoq() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("costTypeDes", CostTypeEnum.values());
		modelview.addObject("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.setViewName("baseinfo/pricedBoq/pricedBoqRight");
		return modelview;
	}
	
	/**
	 * 详述
	 * @author 刘博
	 * @createTime 2016-7-11
	 * @param id 
	 * @return QuantityStandard
	 */
	@RequestMapping(value="/viewPricedBoq")
	@ResponseBody
	public PricedBoq viewPricedBoq(@RequestParam(required=true) String id){
		try{
			PricedBoq pricedBoq = pricedBoqService.viewPricedBoq(id);
			return pricedBoq;
		}catch(Exception e){
			logger.error("资料标准信息查询失败！", e);
			return null;
		}
	}
	/**
	 * 保存工程量标准
	 * @author 刘博
	 * @createTime 2016-7-15
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdatePricedBoq")
	public String saveOrUpdatePricedBoq(@RequestBody(required = true) PricedBoq pricedBoq){
		try{
			pricedBoqService.saveOrUpdatePricedBoq(pricedBoq);
		}catch(Exception e){
			logger.error("工程量标准保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	/**
	 * 新增版本（批量保存工程量）
	 * @author zhangjingjing
	 * @createTime 2016-11-22
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdatePricedBoqBat")
	public String saveOrUpdatePricedBoqBat(@RequestBody(required = true) PriceVersionDto dto){
		try{
			 pricedBoqService.saveOrUpdatePricedBoqBat(dto);
		}catch(Exception e){
			logger.error("工程量标准保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
		return Constants.OPERATE_RESULT_SUCCESS;
		
	}
	
	
	/**
	 * 删除工程量标准
	 * @author 刘博
	 * @createTime 2016-7-15
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePricedBoq")
	public String deletePricedBoq(String id){
		try{
			pricedBoqService.deletePricedBoq(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("工程量标准删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 工程量标准查询弹屏
	 * @author 刘博
	 * @createTime 2016-7-15
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewPricedBoqPop")
	public ModelAndView viewConstructionUnit(){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("versions",versionService.findByType(VersionTypeEnum.PRICED_BOQ.getValue()));
		modelview.setViewName("baseinfo/pricedBoq/pricedBoqSearchPopPage");
		return modelview;
	}
	
	/**
	 * 发布工程量弹屏
	 * @author cui
	 * @createTime 2016-11-29
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/releasePricedBoq")
	public ModelAndView releasePricedBoq(){
		ModelAndView modelview = new ModelAndView();
//		modelview.addObject("versions",versionService.findByType(VersionTypeEnum.PRICED_BOQ.getValue()));
		modelview.setViewName("baseinfo/pricedBoq/releasePricedBoq");
		return modelview;
	}
	
	/**
	 * 增加新版本的工程量标准项
	 * @return
	 */
	@RequestMapping(value = "/newPricedBoqPop")
	public ModelAndView newConstructionUnit(){
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("costTypeDes", CostTypeEnum.values());
		modelview.setViewName("baseinfo/pricedBoq/pricedBoqNewPopPage");
		return modelview;
	}
}
