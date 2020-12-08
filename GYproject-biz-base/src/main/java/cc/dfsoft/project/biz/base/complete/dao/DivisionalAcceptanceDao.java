package cc.dfsoft.project.biz.base.complete.dao;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptance;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DivisionalAcceptanceDao extends CommonDao<DivisionalAcceptance, String>{
	/**
	 * 查询分部验收申请列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDivisionalAcceptance(DivisionalAcceptanceReq req)throws ParseException;
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月10日
	 * @param 
	 * @return
	 */
	public DivisionalAcceptance viewByDaaId(String daaId);

	/**
	 * 根据工程ID查询分部验收信息
	 * @param projId
	 * @return
	 */
	public List<DivisionalAcceptance> findByprojectId(String projId);
}
