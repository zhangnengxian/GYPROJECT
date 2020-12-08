package cc.dfsoft.project.biz.base.project.controller;

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

import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.DataCollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Controller
@RequestMapping(value="/accessoryItem")
public class AccessoryItemController {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(AccessoryItemController.class);
	
	/**
	 * 资料标准
	 */
	@Resource
	AccessoryService accessoryService;
	/**
	 * 工程类型
	 */
	@Resource
	ProjectTypeService projectTypeService;
	
	
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
		List<ProjectType> list=projectTypeService.queryAllList();
		modelView.addObject("projLtypeIdDes", list);
		modelView.setViewName("standard/accessoryItem");
		return modelView;
	}
	
	/**
	 * 打开右侧
	 * @return
	 */
	@RequestMapping(value="/accessoryItemView")
	public ModelAndView accessoryItemView(){
		ModelAndView modelView=new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelView.addObject("projTypeList", list);
		modelView.addObject("dataType", DataCollectionTypeEnum.values());//资料类型
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("corpId", loginInfo.getCorpId());
		modelView.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelView.setViewName("standard/accessoryItemRight");
		return modelView;
	}
	
	/**
	 * 资料标准列表条件查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-12
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryItem")
	@ResponseBody
	public Map<String,Object> queryAccessory(HttpServletRequest request,AccessoryQueryReq accessoryQueryReq){
		try {
			accessoryQueryReq.setSortInfo(request);
			
			if(StringUtils.isBlank(accessoryQueryReq.getCorpId())){
				LoginInfo loginInfo=SessionUtil.getLoginInfo();
				accessoryQueryReq.setCorpId(loginInfo.getCorpId());
			}
			
			Map<String,Object> map = accessoryService.queryAccessoryItemList(accessoryQueryReq);
			return map;
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	
	/**
	 * 弹出搜索屏
	 * @author zhangmeng
	 * @createTime 2016-7-15
	 * @param  
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/accessorySearchPopPage")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.setViewName("standard/accessorySearchPopPage");
		return modelview;
	}
	
	/**
	 * 保存资料标准
	 * @author 刘博
	 * @createTime 2016-7-15
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateCollectAccessoryItem")
	public String saveOrUpdateCollectAccessoryItem(@RequestBody(required = true) CollectAccessoryItem collectAccessoryItem){
		try{
			if(StringUtil.isNotBlank(collectAccessoryItem.getProjTypeId())){
				ProjectType pt=new ProjectType();
				pt.setProjTypeId(collectAccessoryItem.getProjTypeId());
				collectAccessoryItem.setProjType(pt);
			}
			return accessoryService.saveOrUpdateCollectAccessoryItem(collectAccessoryItem);
		}catch(Exception e){
			logger.error("资料标准保存失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 删除资料标准
	 * @author 刘博
	 * @createTime 2016-7-15
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCollectAccessoryItem")
	public String deleteCollectAccessoryItem(String id){
		try{
			accessoryService.deleteCollectAccessoryItem(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("工程量标准删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}


