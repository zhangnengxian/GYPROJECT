package cc.dfsoft.project.biz.base.complete.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.complete.dao.CheckItemDao;
import cc.dfsoft.project.biz.base.complete.dao.SelfInspectionRecordDao;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;
import cc.dfsoft.project.biz.base.complete.service.SelfInspectionRecordService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SelfInspectionRecordServiceImpl implements SelfInspectionRecordService {
	
	@Resource
	SelfInspectionRecordDao selfInspectionRecordDao;
	
	
	@Resource
	CheckItemDao checkItemDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSelfInspectionRecords(List<SelfInspectionRecord> selfInspectionRecords) {
		
		selfInspectionRecordDao.batchInsertObjects(selfInspectionRecords);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateSelfInspectionRecords(List<SelfInspectionRecord> selfInspectionRecords) {
		selfInspectionRecordDao.batchUpdateObjects(selfInspectionRecords);
		
	}
	
	/**
	 * 质量自检
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public Map<String, String> viewSelfInspectionRecordQuqlity(String projId) {
		//List<SelfInspectionRecord> selfInspectionRecords = selfInspectionRecordDao.findQuqlityByProjId(projId);
		//燃气干管工程
		List<SelfInspectionRecord> mainPipeRecords =selfInspectionRecordDao.findQuqlityByProjIdType(projId,"1");
		//户内燃气工程
		List<SelfInspectionRecord>  indoorRecords =selfInspectionRecordDao.findQuqlityByProjIdType(projId,"2");
		
		Project pro=projectDao.get(projId);
		
		
		//查检查项
		List<CheckItem> checkItems1 = checkItemDao.findByType("1","1",pro.getCorpId());
		
		Map<String,String> map = new HashMap<String,String>();
		
		if(mainPipeRecords!=null&&mainPipeRecords.size()>0){
			//如果只保存部分
			for(SelfInspectionRecord ser :mainPipeRecords){
				if(ser.getSirResult()!=null){
					map.put(ser.getCiId()+"_sirId", ser.getSirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult()); 
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
				}else{
					map.put(ser.getCiId()+"_ciId",ser.getCiId());
					map.put(ser.getCiId()+"_sirType",ser.getSirType());
				}
				
			}
		}else{
			//初次加载没有保存质量自检项
			for(int i=0;i<checkItems1.size();i++){
				map.put(checkItems1.get(i).getId()+"_ciId", checkItems1.get(i).getId());
				map.put(checkItems1.get(i).getId()+"_sirType", checkItems1.get(i).getCheckType());
			}
		}
		//查检查项
		List<CheckItem> checkItems2 = checkItemDao.findByType("1","2",pro.getCorpId());
		
		
		
		if(indoorRecords!=null&&indoorRecords.size()>0){
			for(SelfInspectionRecord ser :indoorRecords){
				//部分保存
				if(ser.getSirResult()!=null||ser.getSirRemark()!=null){
					map.put(ser.getCiId()+"_sirId", ser.getSirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult()); 
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
				}else{
					map.put(ser.getCiId()+"_ciId",ser.getCiId());
					map.put(ser.getCiId()+"_sirType",ser.getSirType());
				}
				
			}
		}else{
			//初次加载没有保存质量
			for(int i=0;i<checkItems2.size();i++){
				map.put(checkItems2.get(i).getId()+"_ciId", checkItems2.get(i).getId());
				map.put(checkItems2.get(i).getId()+"_sirType", checkItems2.get(i).getCheckType());
			}
		}
		return map;
	}

	@Override
	public Map<String, String> viewSelfInspectionRecordMaterial(String projId) {
		//资料自检
		List<SelfInspectionRecord> dataRecords =selfInspectionRecordDao.findQuqlityByProjIdType(projId,"3");
		
		List<CheckItem> checkItems3 =  checkItemDao.findByType("2");
		Map<String,String> map = new HashMap<String,String>();
		if(dataRecords!=null&&dataRecords.size()>0){
			for(SelfInspectionRecord ser :dataRecords){
				//已保存过
				if(ser.getSirResult()!=null||ser.getSirRemark()!=null||ser.getSirNum()!=null){
					map.put(ser.getCiId()+"_sirId", ser.getSirId());
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
					map.put(ser.getCiId()+"_sirResult", ser.getSirResult());
					map.put(ser.getCiId()+"_sirRemark", ser.getSirRemark());
					map.put(ser.getCiId()+"_sirNum", ser.getSirNum());
				}else{
					//未保存过
					map.put(ser.getCiId()+"_ciId", ser.getCiId());
					map.put(ser.getCiId()+"_sirType", ser.getSirType());
				}
			}
		}else{
			//初次加载没有保存资料自检
			for(int i=0;i<checkItems3.size();i++){
				map.put(checkItems3.get(i).getId()+"_ciId", checkItems3.get(i).getId());
				map.put(checkItems3.get(i).getId()+"_sirType", checkItems3.get(i).getCheckType());
			}
		}
		
		
		return map;
	}

}
