package cc.dfsoft.project.biz.base.update.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cc.dfsoft.project.biz.base.project.dto.ProjectViewDto;
import cc.dfsoft.project.biz.base.project.service.ManageRecordService;
import cc.dfsoft.project.biz.base.update.dao.UpdateInfoDao;
import cc.dfsoft.project.biz.base.update.dto.UpdateInfoReq;
import cc.dfsoft.project.biz.base.update.entity.UpdateInfo;
import cc.dfsoft.project.biz.base.update.service.UpdateInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.EnclosureDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.UploadResult;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Enclosure;
import cc.dfsoft.uexpress.biz.sys.dept.enums.EnclosureSourceEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class UpdateInfoServiceImpl implements UpdateInfoService {

	@Resource
	UpdateInfoDao updateInfoDao;
	
	/**审核记录服务接口*/
	@Resource
	ManageRecordService manageRecordService;
	
	@Resource
	EnclosureDao enclosureDao;
	
	@Override
	public List<UpdateInfo> findInfo(String updateNumber,Date updateTime) {
		return updateInfoDao.findInfo(updateNumber, updateTime);
	}

	@Override
	public List<UpdateInfo> findAllInfo() {
		return updateInfoDao.findAllInfo();
	}

	@Override
	public void insertUpdateInfo(UpdateInfo updateInfo) {
		updateInfoDao.insertUpdateInfo(updateInfo);
	}
	

	@Override
	public Map<String, Object> queryUpdateInfo(UpdateInfoReq updateInfo) throws ParseException {
		
		return updateInfoDao.queryUpdateInfo(updateInfo);
	}
   
	public UpdateInfo findById(String id){
		return updateInfoDao.findById(id);
	}
	
	/**
	 * 查询基础信息
	 * @author fuliwei
	 * @createTime 2017年6月2日
	 * @param 
	 * @return
	 */
	@Override
	public ProjectViewDto queryBaseInfo() {
		LoginInfo login=SessionUtil.getLoginInfo();
		ProjectViewDto dto=new ProjectViewDto();
		dto.setDlDate(String.valueOf(manageRecordService.getDatabaseDate().getTime())); //操作时间
		dto.setDlRecorder(login.getStaffName());										//操作人
		dto.setStaffId(login.getStaffId());												//操作人id
		return dto;
	}
	
	
	/**
	 * 修改更新信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUpdateInfo(UpdateInfo updateInfo) {
		if(StringUtil.isBlank(updateInfo.getUpdateId())){
			updateInfo.setUpdateId(IDUtil.getUniqueId(Constants.STANDARD));//设置主键id
		}
		updateInfoDao.saveOrUpdate(updateInfo);
	}
	
	
	/**
	 * 删除更新信息
	 * @author fuliwei
	 * @createTime 2017年6月2日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(String id) {
		List<Enclosure> accs =enclosureDao.queryEnuclosureByBus(id,EnclosureSourceEnum.UPDATE.getValue());
		if(accs!=null&&accs.size()>0){
			FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());
			enclosureDao.delete(accs.get(0));
		}
		updateInfoDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateInfoSaveFile(HttpServletRequest request, UploadResult ur, MultipartFile[] files) throws IllegalStateException, IOException {
		String result = ur.getResult();
		UpdateInfo updateInfo = JSON.parseObject(result, UpdateInfo.class);
		String name=updateInfo.getDrawName();
		if(files!=null){
			updateInfo.setDrawName(files[0].getOriginalFilename());
		}
		this.updateUpdateInfo(updateInfo);
		if(StringUtil.isBlank(name)){
			List<Enclosure> accs =enclosureDao.queryEnuclosureByBus(updateInfo.getUpdateId(),EnclosureSourceEnum.UPDATE.getValue());
			if(accs!=null&&accs.size()>0){
				if(files!=null){
					FileUtil.deleteFile(Constants.DISK_PATH+accs.get(0).getAlPath());
				}
				enclosureDao.delete(accs.get(0));
			}
			if(files!=null){
				Enclosure acc = new Enclosure();
				String path=FileUtil.uploadFile(request, updateInfo.getUpdateNo(), files);		
				String fileName = files[0].getOriginalFilename();
				//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
				String filePath= Constants.FIRST_DISK_PATH + path ;
				String name1 = fileName.substring(0,fileName.lastIndexOf("."));	
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());	
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				acc.setEnId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
				acc.setAlTypeId(fileType);
				acc.setAlName(name1);
				acc.setAlPath(filePath);
				acc.setAlOperateCsrId(loginInfo.getStaffId());
				acc.setAlOperateCsr(loginInfo.getStaffName());
				acc.setAlOperateTime(enclosureDao.getDatabaseDate());
				acc.setSourceType(EnclosureSourceEnum.UPDATE.getValue());
				acc.setBusRecordId(updateInfo.getUpdateId());
				enclosureDao.save(acc);
			}
		}
	}
	
}
