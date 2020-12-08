package cc.dfsoft.uexpress.biz.sys.auth.converter;

import cc.dfsoft.uexpress.biz.sys.auth.dto.MenuTenantDto;
import cc.dfsoft.uexpress.biz.sys.auth.entity.MenuTenant;

/**
 * 数据模型转换器
 * @author 1919
 *
 */
public final class MenuTenantDtoConverter {

	/**
     * 禁用构造函数
     */
    private MenuTenantDtoConverter() {
        //禁用构造函数
    }
    
    /**
     * 数据模型转换为页面模型
     * @param menuTenant
     * @return
     */
    public static MenuTenantDto convert(MenuTenant menuTenant) {
        if (menuTenant == null) {
            return null;
        }
        MenuTenantDto menuTenantDto = new MenuTenantDto();
        menuTenantDto.setMenuIds(menuTenant.getMenuIds());
        menuTenantDto.setTenantId(menuTenant.getTenantId());
        return menuTenantDto;
    }
    
    /**
     * 页面模型转换为数据模型
     * @param menuTenantDto
     * @return
     */
    public static MenuTenant convert(MenuTenantDto menuTenantDto) {
        if (menuTenantDto == null) {
            return null;
        }
        MenuTenant menuTenant = new MenuTenant();
        menuTenant.setMenuIds(menuTenantDto.getMenuIds());
        menuTenant.setTenantId(menuTenantDto.getTenantId());
        return menuTenant;
    } 
}
