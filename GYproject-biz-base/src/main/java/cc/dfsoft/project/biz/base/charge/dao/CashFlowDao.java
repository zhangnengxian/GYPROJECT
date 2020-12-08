package cc.dfsoft.project.biz.base.charge.dao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CashFlowDao  extends CommonDao<CashFlow,String>{
	public Map<String,Object> queryCashFlow(ChargeReq chargeReq);
	
	public Map<String,Object> queryCashFlowNew(ChargeReq chargeReq);
	
	/**
	 * 根据工程ID获得执行类型的收付流失
	 * @param projID
	 * @param typeID
	 * @return
	 */
	public BigDecimal getDownPayment(String projID ,String typeID);
	
	/**
	 * 根据工程ID获得收付流失
	 * @param projID
	 * @param typeID
	 * @return
	 */
	public List queryCashFlawByProjID(String projID );
	
	/**
	 * 查询是否满足全额收款的条件
	 * @author fulw
	 * @createTime 2017-1-11
	 * @param projId
	 * @return String
	 */
	//public String queryIsFullAmount(String id,String cfFlag);
	
	
	/**
	 * 查询已付合同款或工程款-所有的
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public BigDecimal queryPayAmount(String projId,String type);

	/**
	 * 根据实收付单id修改收付成功标记
	 * @param cfId
	 * @param resultFlag
	 * @author liaoyq
	 * @createTime 2017-11-09
	 * @return
	 */
	boolean modifyCashFlowById(String cfId, String resultFlag);

	/**
	 * 查询付款单信息
	 * @param cfId
	 * @param projNo
	 * @return
	 */
	public CashFlow queryById(String cfId, String projNo);

	/**
	 * 查询虚拟发票的记录
	 * @param projId
	 * @param billStatus
	 * @return
	 */
    List<CashFlow> queryCashFlawOnly(String projId, String billStatus);

    /**
     * 根据 工程ID查询实收有效流水
     * @param projId
     * @param cfFlag
     * @param conNo 
     * @return
     */
	public List<CashFlow> queryCashFlowByProjIdType(String projId, String cfFlag, String conNo);

    boolean isHaveVirtualInvoice(String projId);
}
