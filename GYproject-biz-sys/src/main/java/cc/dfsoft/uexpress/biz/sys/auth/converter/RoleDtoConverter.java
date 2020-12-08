package cc.dfsoft.uexpress.biz.sys.auth.converter;

import java.util.ArrayList;
import java.util.List;

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;

/**
 * 数据模型转换器
 * @author 1919
 *
 */
public final class RoleDtoConverter {

	/**
     * 禁用构造函数
     */
    private RoleDtoConverter() {
        //禁用构造函数
    }
    
    /**
     * 数据模型列表转换为页面模型列表
     * @param roleList
     * @return
     */
    public static List<RoleDto> convert(List<Role> roleList) {
        if (roleList == null) {
            return null;
        }

        List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
        for (Role role : roleList) {
        	roleDtoList.add(convert(role));

        }
        return roleDtoList;
    }
    
    /**
     * 数据模型转换为页面模型
     * @param role
     * @return
     */
    public static RoleDto convert(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setRoleCode(role.getRoleCode());
        roleDto.setRoleName(role.getRoleName());
        roleDto.setCreateTime(role.getCreateTime());
        roleDto.setCreateStaffId(role.getCreateStaffId());
       // roleDto.setTenantId(role.getTenantId());
        return roleDto;
    }
    
    /**
     * 页面模型转换为数据模型
     * @param roleDto
     * @return
     */
    public static Role convert(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleId(roleDto.getRoleId());
        role.setRoleCode(roleDto.getRoleCode());
        role.setRoleName(roleDto.getRoleName());
        role.setCreateTime(roleDto.getCreateTime());
        role.setCreateStaffId(roleDto.getCreateStaffId());
        role.setCorpId(roleDto.getCorpId());
        //role.setTenantId(roleDto.getTenantId());
        return role;
    } 
    
}
