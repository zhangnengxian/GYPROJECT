package cc.dfsoft.project.biz.base.complete.controller;

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

import cc.dfsoft.project.biz.base.complete.dto.CheckItemReq;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.project.biz.base.complete.enums.TypeEnum;
import cc.dfsoft.project.biz.base.complete.service.CheckItemService;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionListService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;

@Controller
@RequestMapping(value="/checkItems")
public class CheckItemController {

	private static Logger logger = LoggerFactory.getLogger(CheckItemController.class);

	/** 自检单服务接口*/
	@Resource
	SelfInspectionListService selfInspectionListService;
	
	/** 自检项服务接口*/
	@Resource
	CheckItemService checkItemService;
	

	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;

	/**
	 * 打开主页面
	 * @author ht.hu
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView CheckItem(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("complete/checkItem");
		return modelView;
	}
	
	/**
	 * 打开右侧页面
	 * @author ht.hu
	 * @return
	 */
	@RequestMapping(value="/viewCheckItemPage")
	public ModelAndView viewCheckItemPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelView.setViewName("complete/checkItemRight");
		return modelView;
	}
	
	
	/**
	 * 弹出搜索屏
	 * @author zhangmeng
	 * @createTime 2016-7-15
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/checkItemSearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.setViewName("complete/checkItemSearchPopPage");
		return modelview;
	}
	
	/**
	 * 自检列表
	 * @author ht.hu
	 * @param request
	 * @param checkItem
	 * @return
	 */
	@RequestMapping(value="queryCheckItems")
	@ResponseBody
	public Map<String, Object> queryCheckItems(HttpServletRequest request,CheckItemReq checkItem){
		try {
			if(StringUtils.isBlank(checkItem.getCorpId())){
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				checkItem.setCorpId(loginInfo.getCorpId());
			}
			checkItem.setSortInfo(request);
			Map<String, Object> ci = checkItemService.queryCheckItems(checkItem);
			return ci;
		} catch (Exception e) {
			logger.error("自检项查询失败！",e);
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 详述
	 * @author ht.hu
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewCheckItem")
	@ResponseBody
	public CheckItem viewCheckItemPage(@RequestParam(required=true) String id){
		CheckItem checkItem = checkItemService.findCheckItem(id);
		return checkItem;
	}
	
	/**
	 * 删除
	 * @author ht.hu
	 * @param id
	 */
	@RequestMapping(value="/delCheckItem")
	public void delCheckItem(@RequestParam(required=true) String id){
		try {
			checkItemService.delCheckItem(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存
	 * @author ht.hu
	 * @param checkItem
	 */
	@RequestMapping(value="/saveCheckItem")
	@ResponseBody
	public void saveCheckItem(@RequestBody CheckItem checkItem){
		try {
			if(TypeEnum.DATA.values().equals(checkItem.getType())){
				checkItem.setCheckType("");
			}
			checkItemService.saveCheckItem(checkItem);
		} catch (Exception e) {
			logger.error("自检信息保存失败！", e);
		}
	}
	
	
}
