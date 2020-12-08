package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.InstallSummaryDao;
import cc.dfsoft.project.biz.base.inspection.dto.InstallSummaryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListISReq;
import cc.dfsoft.project.biz.base.inspection.entity.ElectrodeRecord;
import cc.dfsoft.project.biz.base.inspection.entity.InstallSummary;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.project.biz.base.inspection.service.InstallSummaryService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 安装汇总报验业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class InstallSummaryServiceImpl implements InstallSummaryService {
	
	@Resource
	private InstallSummaryDao installSummaryDao;
	
	@Resource
	AccessoryDao accessoryDao;
	@Resource
	AccessoryService accessoryService;

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveInstallSummary(ProjectCheckListISReq checkListISReq) {
		if(checkListISReq.getList().size()>0){
			//删除所有的记录
			installSummaryDao.deleteByPcId(checkListISReq.getPcId());
			//批量插入记录数据
			List<InstallSummary> list = checkListISReq.getList();
			List<InstallSummary> listNew = new ArrayList<InstallSummary>();
			for(InstallSummary is : list){
				//生成isId;
				is.setIsId(IDUtil.getUniqueId(Constants.MODULE_CODE_INSTALL_SUMMARY));
				listNew.add(is);
			}
			installSummaryDao.batchInsertObjects(listNew);
		}else{
			installSummaryDao.deleteByPcId(checkListISReq.getPcId());
		}
	}

	@Override
	public Map<String, Object> queryInstallSummary(
			InstallSummaryReq installSummaryReq) {
		return installSummaryDao.queryInstallSummary(installSummaryReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		installSummaryDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			InstallSummary record = installSummaryDao.get(id);
			if(record!=null){
				installSummaryDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListISReq checkListReq) {
		//有记录
				if(checkListReq.getList().size()>0){
					//先删除记录
					deleteRecord(checkListReq);
					//批量保存记录
					List<InstallSummary> listNew = new ArrayList<InstallSummary>();
					for(InstallSummary is : checkListReq.getList()){
						is.setIsId(IDUtil.getUniqueId(Constants.MODULE_CODE_INSTALL_SUMMARY));
						listNew.add(is);
					}
					installSummaryDao.batchInsertObjects(listNew);
					
				}else {
					deleteRecord(checkListReq);
				}
	}
	private void deleteRecord(ProjectCheckListISReq checkListReq){
		//已报验过
		if(StringUtils.isNotBlank(checkListReq.getPcId())){
			//有pcId,则根据pcId删除记录
			installSummaryDao.deletePcIdIsNull(checkListReq.getPcId());
		}else{
			installSummaryDao.deletePcIdIsNull();
		}
	}
	@Override
	public Map<String, Object> queryRecords(InstallSummaryReq req) {
		return installSummaryDao.queryRecords(req);
	}

	@Override
	public InstallSummary viewRecordById(String id) {
		return installSummaryDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(InstallSummary is) {
		if(StringUtils.isBlank(is.getIsId())){
			is.setIsId(IDUtil.getUniqueId(Constants.MODULE_CODE_INSTALL_SUMMARY));
		}
		installSummaryDao.saveOrUpdate(is);
		return is.getIsId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String isId) {
		installSummaryDao.delete(isId);
	}
}
