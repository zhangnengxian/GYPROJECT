package cc.dfsoft.uexpress.biz.sys.auth.converter;

import java.util.ArrayList;
import java.util.List;

import cc.dfsoft.uexpress.biz.sys.auth.dto.RoleDto;
import cc.dfsoft.uexpress.biz.sys.auth.entity.NoticeRole;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Role;

/**
 * 数据模型转换器
 * @author 1919
 *
 */
public final class NoticeRoleDtoConverter {

	/**
     * 禁用构造函数
     */
    private NoticeRoleDtoConverter() {
        //禁用构造函数
    }
    
    /**
     * 数据模型列表转换为页面模型列表
     * @param roleList
     * @return
     */
    public static List<RoleDto> convert(List<NoticeRole> roleList) {
        if (roleList == null) {
            return null;
        }

        List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
        for (NoticeRole role : roleList) {
        	roleDtoList.add(convert(role));

        }
        return roleDtoList;
    }
    
    /**
     * 数据模型转换为页面模型
     * @param role
     * @return
     */
    public static RoleDto convert(NoticeRole role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getNrId());
        roleDto.setRoleCode(role.getNrCode());
        roleDto.setRoleName(role.getNrName());
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
    public static NoticeRole convert(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        NoticeRole role = new NoticeRole();
        role.setNrId(roleDto.getNrId());
        role.setNrCode(roleDto.getNrCode());
        role.setNrName(roleDto.getNrName());
        role.setCreateTime(roleDto.getCreateTime());
        role.setCreateStaffId(roleDto.getCreateStaffId());
        role.setCorpId(roleDto.getCorpId());
        return role;
    } 
    
}
