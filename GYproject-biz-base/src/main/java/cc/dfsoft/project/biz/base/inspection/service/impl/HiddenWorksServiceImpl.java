package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dao.HiddenWorksDao;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.HiddenWorksReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListHWReq;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.HiddenWorksService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class HiddenWorksServiceImpl implements HiddenWorksService{
	
	/**隐蔽工程检查记录*/
	@Resource
	HiddenWorksDao hiddenWorksDao;
	
	@Resource
	ProjectChecklistDao projectChecklistDao;
	
	@Resource
	SignatureService signatureService;
	
	@Resource
	AccessoryDao accessoryDao;
	@Resource
	AccessoryService accessoryService;
	
	/**报验单*/
	@Resource
	ProjectChecklistDao projectCheckListDao;


	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	/**
	 * 保存隐蔽工程检查记录
	 * @author fuliwei
	 * @createTime 2016-7-28
	 * @param hiddenWorks
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveHiddenWorks(HiddenWorks hiddenWorks, String uniqueId) {
		HiddenWorks hWRecord=hiddenWorks;
		/*if(StringUtils.isNotBlank(hiddenWorks.getHwId())){
			//修改
			hWRecord.setHwItem(hiddenWorks.getHwItem());		//分项工程名称
			hWRecord.setHwHiding(hiddenWorks.getHwHiding());	//隐蔽部位
			hWRecord.setHwDrawNo(hiddenWorks.getHwDrawNo());	//设计图号
			hWRecord.setHwMaterial(hiddenWorks.getHwMaterial());//材质
			hWRecord.setHwSpec(hiddenWorks.getHwSpec());    	//规格
			hWRecord.setHwUnit(hiddenWorks.getHwUnit());		//单位
			hWRecord.setHwNum(hiddenWorks.getHwNum());  		//数量
			hWRecord.setHwCheckResult(hiddenWorks.getHwCheckResult());	//检查内容结果
			hWRecord.setHwExplain(hiddenWorks.getHwExplain());		  	//说明简图
			hWRecord.setDrawName(hiddenWorks.getDrawName());     //隐蔽简图
			hWRecord.setHwOtherPerson(hiddenWorks.getHwOtherPerson());	//其他人员
		}else{
			//增加
			hWRecord.setHwId(IDUtil.getUniqueId(uniqueId));
		}
		hiddenWorksDao.saveOrUpdate(hWRecord);*/
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public HiddenWorks viewHiddenWorks(String id,String menuDes) {
		HiddenWorks  hiddenWorks = hiddenWorksDao.viewHiddenWorks(id);
		/*if(hiddenWorks!=null){
		String hwId = hiddenWorks.getHwId();
		Signature signature=signatureService.selectImg(hwId, menuDes);
		if(signature!=null){
			hiddenWorks.setDrawUrl(Constants.DIAGRAM_DISK_PATH+signature.getImgUrl());
		}
		hiddenWorks.setMenuDes(menuDes);
		}*/
		return hiddenWorks;
	}

	/**
	 * 保存隐蔽工程记录
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveHiddenWorksRecordFile(HttpServletRequest request, UploadResult hiddenWorks,
			MultipartFile[] files) throws IllegalStateException, IOException, SerialException, SQLException {
		/*String result = hiddenWorks.getResult();
		HiddenWorks cmt = JSON.parseObject(result, HiddenWorks.class);
		String name=cmt.getDrawName();
		if(files!=null){
			 cmt.setDrawName(files[0].getOriginalFilename());
		}
		String id = cmt.getPcId();
		List<ProjectChecklist> pChecklist=projectChecklistDao.getByPcId(id);
		String projId = pChecklist.get(0).getProjId();
		String projNo = pChecklist.get(0).getProjNo();
		this.saveHiddenWorks(cmt,Constants.MODULE_CODE_HIDDEN_WORKS);
		hiddenWorks.setOperateId(cmt.getHwId());		
		if(StringUtil.isBlank(name)){		   
			signatureService.saveImage(request, files, projId, projNo, cmt.getHwId(), cmt.getMenuDes());
		}*/
		
		
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId,String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		hiddenWorksDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			HiddenWorks record = hiddenWorksDao.get(id);
			if(record!=null){
				hiddenWorksDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	public Map<String, Object> queryHiddenWorks(HiddenWorksReq hiddenWorksReq) {
		return hiddenWorksDao.queryTrenchBackfill(hiddenWorksReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListHWReq checkListHWReq) {
		//有记录
		if(checkListHWReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListHWReq);
			//批量保存记录
			List<HiddenWorks> listNew = new ArrayList<HiddenWorks>();
			for(HiddenWorks tb : checkListHWReq.getList()){
				tb.setHwId(IDUtil.getUniqueId(Constants.MODULE_CODE_HIDDEN_WORKS));
				listNew.add(tb);
			}
			hiddenWorksDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListHWReq);
		}
	}

	private void deleteRecord(ProjectCheckListHWReq checkListHWReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListHWReq.getPcId())){
			//有pcId,则根据pcId删除记录
			hiddenWorksDao.deletePcIdIsNull(checkListHWReq.getPcId());
		}else{
			hiddenWorksDao.deletePcIdIsNull();
		}
	}

	@Override
	public Map<String, Object> queryRecords(HiddenWorksReq hiddenWorksReqt) {
		return hiddenWorksDao.queryRecords(hiddenWorksReqt);
	}

	@Override
	public HiddenWorks viewRecordById(String id) {
		return hiddenWorksDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public String saveRecord(HiddenWorks hw) {
		if(StringUtils.isBlank(hw.getHwId())){
			hw.setHwId(IDUtil.getUniqueId(Constants.MODULE_CODE_HIDDEN_WORKS));
		}
		hiddenWorksDao.saveOrUpdate(hw);
		return hw.getHwId();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void deleteRecordById(String hwId) {
		hiddenWorksDao.delete(hwId);
	}
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		
		//隐蔽工程
		//现场代表 fieldsRepresent
		//现场监理人suJgj
		//施工员builder
		//质检员constructionQae
		//安全员safetyOfficer
		if(StringUtils.isNotBlank(cwId)){
			ProjectChecklist list=projectCheckListDao.get(cwId);
			if(list!=null){
				
				if(StringUtils.isNotBlank(list.getBuilder())){
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.HIDDEN_WORKS.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSafetyOfficer())){
					signNoticeService.saveThisSignNotice(SignPostEnum.SAFTYOFFICER.getValue(), SignDataTypeEnum.HIDDEN_WORKS.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				
				if(StringUtils.isNotBlank(list.getConstructionQc())){
					signNoticeService.saveThisSignNotice(SignPostEnum.QUALITATIVE_CHECK_MEMBER.getValue(), SignDataTypeEnum.HIDDEN_WORKS.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getSuJgj())){
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.HIDDEN_WORKS.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(list.getFieldsRepresent())){
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.HIDDEN_WORKS.getValue(),
							list.getPcId(), list.getProjId(),signState);
				}
			}
		}
		
	}
}
