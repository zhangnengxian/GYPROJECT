package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.ConnectContentDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConnectRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConnectRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecord;
import cc.dfsoft.project.biz.base.constructmanage.service.TouchRecordService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class TouchRecordServiceImpl implements TouchRecordService{

	/**碰口内容dao*/
	@Resource
	ConnectContentDao connectContentDao;
	
	/**碰口记录dao*/
	@Resource
	ConnectRecordDao connectRecordDao;
	
	@Override
	public List<ConnectContent> queryConnectContent(String type) throws ParseException {
		return connectContentDao.queryConnectContent(type);
	}

	/**
	 * 查碰口记录显示内容
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> queryConnectRecord(ConnectRecordQueryReq connectRecordReq) throws ParseException {
		Map<String, Object> result = new HashMap();
		// 查询碰口记录map集合
		result = connectRecordDao.queryUnitContent(connectRecordReq);
		// 新的碰口记录list
		List<ConnectRecord> newList = new ArrayList();
		// 如果存在项目id
		if (StringUtils.isNotBlank(connectRecordReq.getProjId())) {
			// 查出碰口记录
			List<ConnectRecord> records = (List<ConnectRecord>) result.get("data");
			// 设置des值
			for (ConnectRecord record : records) {
				newList = records;
				for (ConnectRecord newRecord : newList) {
					newRecord.setDes(connectContentDao.get(record.getConnectContentId()).getDes());
					newList.add(newRecord);
				}
			}
		} else {
			List<ConnectContent> contents = (List<ConnectContent>) connectContentDao.queryConnectContent(connectRecordReq.getProjId());
			for (int i = 0; i < contents.size(); i++) {
				ConnectContent connectContent = contents.get(i);
				for (ConnectRecord newRecord : newList) {
					newRecord.setDes(connectContent.getDes());
					newRecord.setUnitType(connectContent.getType());
					newRecord.setConnectContentId(connectContent.getId());
					newList.add(newRecord);
				}
			}
			
		}
		result.put("data",newList);
		return result;
	}

	@Override
	public Map<String, Object> queryConnectContent(ConnectRecordQueryReq connectRecordReq) throws ParseException {
		Map<String, Object> result = new HashMap();
		// 查询碰口记录map集合
		result = connectRecordDao.queryUnitContent(connectRecordReq);
		// 新的碰口记录list
		List<ConnectRecord> newList = new ArrayList();
		// 查出碰口记录
		List<ConnectRecord> records = (List<ConnectRecord>) result.get("data");
		if(records!=null && records.size()>0){
			// 设置des值
			for(int i=0;i<records.size();i++){
				ConnectRecord record = records.get(i);
				record.setDes(connectContentDao.get(record.getConnectContentId()).getDes());
				newList.add(record);
			}
		}else{
			Map<String, Object> map = connectContentDao.queryUnitContent(connectRecordReq);
			List<ConnectContent> contents = (List<ConnectContent>) map.get("data");
			for (int i = 0; i < contents.size(); i++) {
				ConnectContent connectContent = contents.get(i);
				ConnectRecord newRecord = new ConnectRecord();
					newRecord.setDes(connectContent.getDes());
					newRecord.setUnitType(connectContent.getType());
					newRecord.setConnectContentId(connectContent.getId());
					newList.add(newRecord);
				
			}
		}
		result.put("data",newList);
		return result;
	}
}
