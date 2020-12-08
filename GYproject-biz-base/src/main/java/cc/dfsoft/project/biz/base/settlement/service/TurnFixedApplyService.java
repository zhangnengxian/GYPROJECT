package cc.dfsoft.project.biz.base.settlement.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.settlement.dto.TurnFixedApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.TurnFixedApply;

public interface TurnFixedApplyService {
 
	/**
	 * 根据工程ID查转固详述
	 * @param id
	 * @return
	 */
	TurnFixedApply viewTurnFixedApply(String projId)throws ParseException;

	/**
	 * 保存转固信息
	 * @param turnFixedApply
	 * @return
	 */
	String saveTurnFixedApply(TurnFixedApply turnFixedApply) throws Exception;

	/**
	 * 转固申请审核列表
	 * @param turnFixedApplyReq
	 * @return
	 */
	Map<String, Object> queryTurnFixedApply(TurnFixedApplyReq turnFixedApplyReq)throws ParseException;

	/**
	 * 根据工程ID查转固申请信息
	 * @param projId
	 * @return
	 */
	TurnFixedApply findByProjId(String projId);

	/**
	 * 转固申请打印条件查询
	 * @param turnFixedApplyReq
	 * @return
	 */
	Map<String, Object> queryTurnFixedApplyPrint(TurnFixedApplyReq turnFixedApplyReq)throws ParseException;

	/**
	 * 标记打印
	 * @param projId
	 */
	void signTurnFixedPrint(String projId);
}
