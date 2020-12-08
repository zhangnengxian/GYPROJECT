package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ValveTestDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListVTReq;
import cc.dfsoft.project.biz.base.inspection.dto.ValveTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.ValveTest;
import cc.dfsoft.project.biz.base.inspection.service.ValveTestService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 阀门试验业务实现层
 * @author liaoyq
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ValveTestServiceImpl implements ValveTestService {

	@Resource
	private ValveTestDao valveTestDao;
	
	@Resource
	AccessoryDao accessoryDao;
	@Resource 
	AccessoryService accessoryService;
	
	@Override
	public Map<String, Object> queryValveTest(ValveTestReq valveTestReq) {
		return valveTestDao.queryValveTest(valveTestReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveValveTest(ProjectCheckListVTReq checkListVTReq) {
		if(checkListVTReq.getList().size()>0){
			//删除所有的记录
			valveTestDao.deleteByPcId(checkListVTReq.getPcId());
			//批量插入记录数据
			List<ValveTest> list = checkListVTReq.getList();
			List<ValveTest> listNew = new ArrayList<ValveTest>();
			for(ValveTest vt : list){
				//生成vtId;
				vt.setVtId(IDUtil.getUniqueId(Constants.MODULE_CODE_VALVE_TEST));
				listNew.add(vt);
			}
			valveTestDao.batchInsertObjects(listNew);
			
		}else{
			valveTestDao.deleteByPcId(checkListVTReq.getPcId());
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		valveTestDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			ValveTest record = valveTestDao.get(id);
			if(record!=null){
				valveTestDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	@Override
	public Map<String, Object> queryRecords(ValveTestReq valveTestReq) {
		return valveTestDao.queryRecords(valveTestReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveRecords(ProjectCheckListVTReq checkListReq) {
		
	}

	@Override
	public ValveTest viewRecordById(String id) {
		return valveTestDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveRecord(ValveTest vt) {
		if(StringUtils.isBlank(vt.getVtId())){
			vt.setVtId(IDUtil.getUniqueId(Constants.MODULE_CODE_VALVE_TEST));
		}
		valveTestDao.saveOrUpdate(vt);
		return vt.getVtId();
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteRecordById(String vtId) {
		valveTestDao.delete(vtId);
	}

}
