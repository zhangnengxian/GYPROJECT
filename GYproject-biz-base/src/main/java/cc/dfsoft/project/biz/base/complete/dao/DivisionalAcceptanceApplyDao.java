package cc.dfsoft.project.biz.base.complete.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.DivisionalAcceptanceReq;
import cc.dfsoft.project.biz.base.complete.entity.DivisionalAcceptanceApply;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DivisionalAcceptanceApplyDao extends CommonDao<DivisionalAcceptanceApply, String>{
	
	/**
	 * 查询分部验收申请列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDivisionalAcceptanceApply(DivisionalAcceptanceReq req)throws ParseException;
	
	/**
	 * 统计分部验收申请记录数
	 *creater wang.hui.jun
	 *@version 2019年11月7日
	 *@param projId
	 *@return null (查询无记录时返回null)
	 *@throws Exception
	 */
	Integer countDivisonalAcceptanceRecord(String projId) throws  Exception;
	
}
