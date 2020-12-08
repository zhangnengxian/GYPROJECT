package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.SafetyPunishReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SafetyPunish;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SafetyPunishDao extends CommonDao<SafetyPunish,String>{

	/**
	 * 安全质量细则列表查询
	 * @author cui
	 * @param businessPartnersReq
	 * @return
	 */
	public Map<String, Object> querySafetyPunish(SafetyPunishReq safetyPunishReq) throws ParseException;
    /**
     * 获取最大id值
     * @param id
     * @return
     */
	public String findId(String id);
	
	/**
	 * 获取小项最大ID
	 * @param id
	 * @return
	 */
	public String findSmId(String id);
	
	 /**
     * 获取小类
     * @param safetyPunishReq
     * @return
     */
	public Map<String, Object> querySafetyPunishMin(SafetyPunishReq safetyPunishReq);
	
	/**
     * 删除大类及小类
     * @param id
     * @return
     */
	public void deleteAll(String id);
	/**
	 * 根据类型查找
	 * @param type
	 * @return
	 */
	public List<SafetyPunish> getByType(String type,String corpId);
	
}
