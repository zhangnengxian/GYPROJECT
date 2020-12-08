package cc.dfsoft.uexpress.biz.sys.auth.service.impl;

import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuTenantDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.auth.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单服务接口实现
 * 
 * @author 1919
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService {

	/** 菜单处理DAO */
	@Resource
	private MenuDao menuDao;

	/** 租户菜单处理DAO */
	@Resource
	private MenuTenantDao menuTenantDao;

	@Override
	public List<Menu> queryAll() {
		List<Menu> list=menuDao.queryAll();
		return list;
	}

	@Override
	public List<Map<String, String>> getMenuTreeData() {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<Menu> menuList = menuDao.queryAll();
		if (menuList != null && menuList.size() > 0) {
			for (Menu menu : menuList) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", menu.getMenuId());
				map.put("parent", menu.getParentId() != null ? menu.getParentId() : "#");
				map.put("text", menu.getMenuName());
				mapList.add(map);
			}
		}
		return mapList;
	}

	@Override
	public List<Map<String, String>> getMenurelationDocTreeData() {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<Map<String, Object>> menuList = menuDao.menuRelationDoc();
		if (menuList != null && menuList.size() > 0) {
			for (Map<String, Object> menu : menuList) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", (String)menu.get("MENU_ID"));
					map.put("parent", (String)menu.get("PARENT_ID") != null ? (String)menu.get("PARENT_ID") : "#");
					map.put("text", (String)menu.get("MENU_NAME"));
					mapList.add(map);
					if(menu.get("GRADE")!=null){
						int i=0 ;
						for(;i<Integer.parseInt(String.valueOf(menu.get("GRADE")));i++){
							Map<String, String> map1 = new HashMap<String, String>();
							int j =i+1;
							map1.put("id", "btn-"+(String)menu.get("MENU_ID")+"0"+j);
							map1.put("parent", (String)menu.get("MENU_ID"));
							map1.put("text", j+"级按钮");
							mapList.add(map1);
						}
						if(!menu.get("MENU_LEVEL").toString().equals("-1")){
						Map<String, String> map2 = new HashMap<String, String>();
						map2.put("id", "check-"+(String)menu.get("MENU_ID"));
						map2.put("parent", (String)menu.get("MENU_ID"));
						map2.put("text", "查看");
						mapList.add(map2);
						}
					}
			}
		}
		return mapList;
	}
	/*@Override
	public void insertMenuTenant(MenuTenantDto menuTenantDto) {
		MenuTenant menuTenant = MenuTenantDtoConverter.convert(menuTenantDto);
		menuTenantDao.insertMenuTenant(menuTenant);
	}

	@Override
	public MenuTenantDto queryMenuTenantDto(String tenantId) {
		MenuTenant menuTenant = menuTenantDao.queryMenuTenant(tenantId);
		return MenuTenantDtoConverter.convert(menuTenant);
	}*/

	@Override
	public List<Map<String, String>> getMenuTreeForSet() {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		List<Map<String, Object>> menuList = menuDao.getMenuTreeForSet();
		if (menuList != null && menuList.size() > 0) {
			for (Map<String, Object> menu : menuList) {
			   if(menu.get("MENU_ID")!="14"&&menu.get("PARENT_ID")!="14"){
				    Map<String, String> map = new HashMap<String, String>();
				    if(menu.get("MENU_LEVEL").toString().equals("3")){
				       map.put("id", (String)menu.get("MENU_ID")+"@@"+((String)menu.get("STEP_ID")!=null?(String)menu.get("STEP_ID"):""));
				    }else{
					   map.put("id", (String)menu.get("MENU_ID"));
				    }
				    map.put("parent", (String)menu.get("PARENT_ID") != null ? (String)menu.get("PARENT_ID") : "#");
				    map.put("text", (String)menu.get("MENU_NAME"));
				    mapList.add(map);
			  }
					
			}
		}
		return mapList;
	}

	@Override
	public List<Menu> findlevel2MenuList() {
		return menuDao.findlevel2MenuList();
	}

	@Override
	public List<Menu> findChildMenuList(String menuId) {
		return menuDao.findChildMenuList(menuId);
	}

}
