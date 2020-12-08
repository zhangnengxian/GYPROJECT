package cc.dfsoft.project.biz.base.settlement.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.settlement.dto.TurnFixedApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.TurnFixedApply;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface TurnFixedApplyDao extends CommonDao<TurnFixedApply, String>{

	/**
	 * 根据工程ID查转固表
	 * @param projId
	 * @return
	 */
	List<TurnFixedApply> findByProjId(String projId);

	/**
	 * 条件查询转固申请列表
	 * @param turnFixedApplyReq
	 * @return
	 */
	Map<String, Object> queryTurnFixedApply(TurnFixedApplyReq turnFixedApplyReq)throws ParseException;

	/**
	 * 转固申请列表查询
	 * @param turnFixedApplyReq
	 * @return
	 */
	Map<String, Object> queryTurnFixedApplyPrint(TurnFixedApplyReq turnFixedApplyReq)throws ParseException;

}
