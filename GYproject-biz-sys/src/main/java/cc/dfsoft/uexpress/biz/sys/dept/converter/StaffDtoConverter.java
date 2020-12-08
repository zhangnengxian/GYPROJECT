package cc.dfsoft.uexpress.biz.sys.dept.converter;

import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据模型转换器
 * @author 1919
 *
 */
public final class StaffDtoConverter {

	/**
     * 禁用构造函数
     */
    private StaffDtoConverter() {
        //禁用构造函数
    }
    
    /**
     * 数据模型列表转换为页面模型列表
     * @param staffList
     * @return
     */
    public static List<StaffDto> convert(List<Staff> staffList) {
        if (staffList == null) {
            return null;
        }

        List<StaffDto> staffDtoList = new ArrayList<StaffDto>();
        for (Staff staff : staffList) {
        	staffDtoList.add(convert(staff));

        }
        return staffDtoList;
    }
    
    /**
     * 数据模型转换为页面模型
     * @param staff
     * @return
     */
    public static StaffDto convert(Staff staff) {
        if (staff == null) {
            return null;
        }
        StaffDto staffDto = new StaffDto();
        staffDto.setStaffId(staff.getStaffId());
        staffDto.setStaffNo(staff.getStaffNo());
        staffDto.setIdCardNo(staff.getIdCardNo());
        staffDto.setLoginAccount(staff.getLoginAccount());
        staffDto.setPassword(staff.getPassword());
        staffDto.setStaffName(staff.getStaffName());
        staffDto.setEmail(staff.getEmail());
        staffDto.setQq(staff.getQq());
        staffDto.setWechat(staff.getWechat());
        staffDto.setPost(staff.getPost());
        staffDto.setAddr(staff.getAddr());
        staffDto.setMobile(staff.getMobile());
        staffDto.setHomePhone(staff.getHomePhone());
        staffDto.setPhone(staff.getPhone());
        staffDto.setCreateStaffId(staff.getCreateStaffId());
        staffDto.setCreateTime(staff.getCreateTime());
        staffDto.setCorpId(staff.getCorpId());
        staffDto.setDeptId(staff.getDeptId());
        staffDto.setDeptTransfer(staff.getDeptTransfer());
        staffDto.setPostDes(staff.getPostDes());
        staffDto.setUnitType(staff.getUnitType());
        staffDto.setPostName(staff.getPostName());
        staffDto.setTenantId(staff.getTenantId());
        staffDto.setSignPicturePath(staff.getSignPicturePath());
        staffDto.setConstructionQaeUrlPath("attachments/"+Constants.SIGN_DISK_PATH+staff.getSignPicturePath());
        staffDto.setOrgId(staff.getOrgId());
        staffDto.setBelongCorpId(staff.getBelongCorpId());
        staffDto.setBelongCorpName(staff.getBelongCorpName());
        staffDto.setMarkOfDelet(staff.getMarkOfDelet());
        return staffDto;
    }
    
    /**
     * 页面模型转换为数据模型
     * @param staffDto
     * @return
     */
    public static Staff convert(StaffDto staffDto) {
        if (staffDto == null) {
            return null;
        }
        Staff staff = new Staff();
        staff.setStaffId(staffDto.getStaffId());
        staff.setStaffNo(staffDto.getStaffNo());
        staff.setIdCardNo(staffDto.getIdCardNo());
        staff.setLoginAccount(staffDto.getLoginAccount());
        staff.setPassword(staffDto.getPassword());
        staff.setStaffName(staffDto.getStaffName());
        staff.setEmail(staffDto.getEmail());
        staff.setQq(staffDto.getQq());
        staff.setWechat(staffDto.getWechat());
        staff.setPost(staffDto.getPost());
        staff.setAddr(staffDto.getAddr());
        staff.setMobile(staffDto.getMobile());
        staff.setHomePhone(staffDto.getHomePhone());
        staff.setPhone(staffDto.getPhone());
        staff.setCreateStaffId(staffDto.getCreateStaffId());
        staff.setCreateTime(staffDto.getCreateTime());
        staff.setCorpId(staffDto.getCorpId());
        staff.setDeptId(staffDto.getDeptId());
        staff.setDeptTransfer(staffDto.getDeptTransfer());
        staff.setUnitType(staffDto.getUnitType());
        staff.setPhotoStr(staffDto.getPhotoStr());
        staff.setPhotoUrl(staffDto.getPhotoUrl());
        staff.setSignPictureStr(staffDto.getSignPictureStr());
        staff.setPostName(staffDto.getPostName());
        staff.setTenantId(staffDto.getTenantId());
        staff.setSignPictureStr(staffDto.getSignPictureStr());
        staff.setSignPicturePath(staffDto.getSignPicturePath());
        staff.setOrgId(staffDto.getOrgId());
        
        staff.setBelongCorpId(staffDto.getBelongCorpId());
        staff.setBelongCorpName(staffDto.getBelongCorpName());
        staff.setMarkOfDelet(staffDto.getMarkOfDelet());
        return staff;
    } 
    
}
