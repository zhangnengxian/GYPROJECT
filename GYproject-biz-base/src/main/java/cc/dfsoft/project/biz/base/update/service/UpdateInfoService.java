package cc.dfsoft.project.biz.base.update.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.project.dto.ProjectViewDto;
import cc.dfsoft.project.biz.base.update.dto.UpdateInfoReq;
import cc.dfsoft.project.biz.base.update.entity.UpdateInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.UploadResult;

public interface UpdateInfoService {
	/**
	 * 根据条件查询更新信息
	 * @param updateNumber
	 * @param updateTime
	 * @return
	 */
	List<UpdateInfo> findInfo(String updateNumber,Date updateTime);
	
	/**
	 * 查询全部
	 * @param updateNumber
	 * @param updateTime
	 * @return
	 */
	List<UpdateInfo> findAllInfo();
	
	/**
	 * 增加更新信息
	 * @param updateInfo
	 */
	public void insertUpdateInfo(UpdateInfo updateInfo);
	
	/**
	 * 修改更新信息
	 * @param updateInfo
	 */
	public void updateUpdateInfo(UpdateInfo updateInfo);

	public UpdateInfo findById(String id);

	Map<String, Object> queryUpdateInfo(UpdateInfoReq updateInfo) throws ParseException;
	
	/**
	 * 查询基础信息
	 * @author fuliwei
	 * @createTime 2017年6月2日
	 * @param 
	 * @return
	 */
	public ProjectViewDto queryBaseInfo();
	
	/**
	 * 删除更新信息
	 * @author fuliwei
	 * @createTime 2017年6月2日
	 * @param 
	 * @return
	 */
	public void deleteById(String id);

	/**
	 * 更新信息保存（有图片）
	 * @param request
	 * @param updateInfo
	 * @param files
	 */
	void updateInfoSaveFile(HttpServletRequest request, UploadResult ur, MultipartFile[] files)throws IllegalStateException, IOException;

}
