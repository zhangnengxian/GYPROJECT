package cc.dfsoft.uexpress.biz.sys.auth.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.auth.service.MenuService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;

/**
 * 菜单管理控制器
 * @author 1919
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	/** 菜单服务接口 */
	@Resource
	private MenuService menuService;
	/** 员工服务接口 */
	@Resource
	private StaffService staffService;
	
	/**
	 * 菜单信息
	 * @return
	 */
	@RequestMapping(value = "/info")
	@ResponseBody
	public Map<String,Object> menuInfo(){
		try {
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			Map<String,Object> map = staffService.getStaffMenuList(loginInfo.getTenantId(), loginInfo.getStaffId());
			/*List<Menu> list = menuService.queryAll();*/
			return map;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取菜单树结构信息
	 * @return
	 */
	@RequestMapping(value = "/getMenuTreeData")
	@ResponseBody
	public List<Map<String, String>> getMenuTreeData(){
		try{
			List<Map<String, String>> maplist = menuService.getMenurelationDocTreeData();
			return maplist;
		}catch(Exception e){
			logger.error("获取菜单树结构信息发生异常。", e);
			return null;
		}
	}
	/**
	 * 获取菜单树结构信息(用于系统设置)
	 * @return
	 */
	@RequestMapping(value = "/getMenuTreeForSet")
	@ResponseBody
	public List<Map<String, String>> getMenuTreeForSet(){
		try{
			List<Map<String, String>> maplist = menuService.getMenuTreeForSet();
			return maplist;
		}catch(Exception e){
			logger.error("获取菜单树结构信息发生异常。", e);
			return null;
		}
	}
	
}
