package cc.dfsoft.project.biz.base.complete.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.complete.dao.DataAcceptanceRecordDao;
import cc.dfsoft.project.biz.base.complete.dto.DataAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DataAcceptanceRecord;
import cc.dfsoft.project.biz.base.complete.service.DataAcceptanceRecordService;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.project.biz.base.project.enums.DataCollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DataAcceptanceRecordServiceImpl implements DataAcceptanceRecordService{
	
	/**资料验收记录*/
	@Resource
	DataAcceptanceRecordDao dataAcceptanceRecordDao;
	
	@Resource
	AccessoryService accessoryService;
	/**
	 * 查询资料验收记录
	 * @author fuliwei
	 * @createTime 2017年8月3日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryDataAcceptanceRecord(HttpServletRequest request,AccessoryQueryReq req) {
		
		Map<String, Object> map =dataAcceptanceRecordDao.queryDataAcceptanceRecord(req);
		List <DataAcceptanceRecord> recordList=(List<DataAcceptanceRecord>) map.get("data");
		
		//已保存过
		if(recordList!=null && recordList.size()>0){
			List<String> caiIdList=new ArrayList<String>();
			for(DataAcceptanceRecord da:recordList){
				caiIdList.add(da.getCaiId());
			}
			AccessoryQueryReq accessoryQueryReq=new AccessoryQueryReq();
			accessoryQueryReq.setCaiIdList(caiIdList);
			accessoryQueryReq.setDataType(DataCollectionTypeEnum.ACCEPTANCE_DATA.getValue());
			Map<String, Object> caiMap=accessoryService.queryAccessoryItemList(accessoryQueryReq);
			List <CollectAccessoryItem> caiList=(List<CollectAccessoryItem>) caiMap.get("data");
			DataAcceptanceRecord newRecord;
			if(caiList!=null && caiList.size()>0){
				for(CollectAccessoryItem cai:caiList){
					newRecord=new DataAcceptanceRecord();
					newRecord.setCaiId(cai.getCaiId());				//资料标准id
					newRecord.setDataName(cai.getAccessoryName());  //
					newRecord.setDataType(cai.getDataType());
					newRecord.setProjectTypeId(cai.getProjTypeId());
					recordList.add(newRecord);
				}
			}
			map.put("data", recordList);
			map.put("recordsFiltered", recordList.size());
			map.put("recordsTotal",recordList.size());
			return map;
		}
		
		
		//未保存过
		req.setDataType(DataCollectionTypeEnum.ACCEPTANCE_DATA.getValue());
		Map<String, Object> mapResult=accessoryService.queryAccessoryItemList(req);
		List <CollectAccessoryItem> collecList=(List<CollectAccessoryItem>) mapResult.get("data");
		
		List <DataAcceptanceRecord> newList=new ArrayList<DataAcceptanceRecord>();
		DataAcceptanceRecord newRecord;
		
		if(collecList!=null && collecList.size()>0){
			for(CollectAccessoryItem cai:collecList){
				newRecord=new DataAcceptanceRecord();
				newRecord.setCaiId(cai.getCaiId());				//资料标准id
				newRecord.setDataName(cai.getAccessoryName());  //
				newRecord.setDataType(cai.getDataType());
				newRecord.setProjectTypeId(cai.getProjTypeId());
				newList.add(newRecord);
			}
		}
		mapResult.put("data", newList);
		return mapResult;
	}

}
