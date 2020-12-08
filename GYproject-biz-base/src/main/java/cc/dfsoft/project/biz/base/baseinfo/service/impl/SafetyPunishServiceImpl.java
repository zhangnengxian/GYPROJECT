package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.SafetyPunishDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.SafetyPunishReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SafetyPunish;
import cc.dfsoft.project.biz.base.baseinfo.service.SafetyPunishService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SafetyPunishServiceImpl implements SafetyPunishService{
	
	/**安全质量细则dao*/
	@Resource
	SafetyPunishDao safetyPunishDao;
	
	@Override
	public Map<String, Object> querySafetyPunish(SafetyPunishReq safetyPunishReq) throws ParseException {
		
		return safetyPunishDao.querySafetyPunish(safetyPunishReq);
	}

	@Override
	public SafetyPunish viewSafetyPunishById(String id) {
		return safetyPunishDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSafetyPunish(SafetyPunish safetyPunish) {
		safetyPunishDao.saveOrUpdate(safetyPunish);
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveALLSmSafetyPunish(List<SafetyPunish> safetyPunish){
		safetyPunishDao.batchInsertOrUpdateObjects(safetyPunish);
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteSafetyPunish(String id) {
		safetyPunishDao.deleteAll(id);
	}

	@Override
	public String findId(String id) {
		return safetyPunishDao.findId(id);
	}
	
	@Override
	public String findSmId(String id){
		return safetyPunishDao.findSmId(id);
	}

	@Override
	public Map<String, Object> querySafetyPunishMin(SafetyPunishReq safetyPunishReq) {
		return safetyPunishDao.querySafetyPunishMin(safetyPunishReq);
	}
	/**
	 * 安全处罚工程（返回节点树）
	 */
	@Override
	public List<Map<String, Object>> querySafetyPunishTree(String type,String corpId) {
		List<SafetyPunish> splist = safetyPunishDao.getByType(type,corpId);
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		for(SafetyPunish sp:splist){
			Map<String, Object> map=new HashMap<String, Object>();
			if(sp.getId().length()==7){
				map.put("id", sp.getId());
			}else{
				map.put("id", sp.getId()+"@@"+sp.getDes()+"@@"+sp.getDeduction()+"@@"+safetyPunishDao.get(sp.getId().substring(0, 7)).getDes());
			}
			if((sp.getId().length())==7){
				map.put("parent", "#");
			}else{
				map.put("parent", sp.getId().substring(0, 7));
			}
			map.put("text", sp.getDes());
			result.add(map);
		}
		return result;
	}

	

}
