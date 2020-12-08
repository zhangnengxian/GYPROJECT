package cc.dfsoft.project.biz.base.contract.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.dto.supplementalContractModifyHistoryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContractModifyhistory;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/*
 *补充协议修改Dao
 *wang.hui.jun 
 */
public interface SupplementalContractModifyHistoryDao extends CommonDao<SupplementalContractModifyhistory, String>{
    /**
     * 查询补充协议修改历史
     * 根据补充协议scId查询
     */
	public Map<String, Object> queryModifyHistory(String scId,supplementalContractModifyHistoryReq modifyhistoryReq) throws Exception;

	/**
	 * 根据修改操作日志ID和修改状态查询日志
	 * @author liaoyq
	 * @createTime 2018年6月26日
	 * @param modifyState
	 * @param orlId
	 * @return
	 */
	public SupplementalContractModifyhistory findByModifystate(String modifyState,
			String orlId);
	
}
