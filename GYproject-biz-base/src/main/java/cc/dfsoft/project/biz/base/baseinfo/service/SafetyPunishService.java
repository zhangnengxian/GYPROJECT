package cc.dfsoft.project.biz.base.baseinfo.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.SafetyPunishReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SafetyPunish;

public interface SafetyPunishService {

	/**
	 * 安全质量细则列表查询
	 * @author cui
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> querySafetyPunish(SafetyPunishReq safetyPunishReq) throws ParseException;
	
	/**
	 * 安全质量细则详述查询
	 * @author cui
	 * @param id
	 * @return
	 */
	public SafetyPunish viewSafetyPunishById(String id);
	
	/**
	 * 保存，更新安全质量细则
	 * @param SafetyPunish
	 */
	public void saveSafetyPunish(SafetyPunish safetyPunish);
	
	/**
	 * 批量保存细则小类
	 * @param safetyPunish
	 */
	public void saveALLSmSafetyPunish(List<SafetyPunish> safetyPunish);
	
	/**
	 * 删除安全质量细则
	 * @author cui
	 * @param Id
	 */
	public void deleteSafetyPunish(String Id);

	/**
	 * 查询最大id
	 * @param id
	 * @return
	 */
	public String findId(String id);
	
	/**
	 * 查询小项最大ID
	 * @param
	 * @return
	 */
	public String findSmId(String id);

	/**
	 * 大类下属小类
	 * @param safetyPunishReq
	 * @return
	 */
	public Map<String, Object> querySafetyPunishMin(SafetyPunishReq safetyPunishReq);
	/**
	 * 安全处罚工程（返回节点树）
	 * @return
	 */
	public List<Map<String, Object>> querySafetyPunishTree(String type,String corpId);


}
