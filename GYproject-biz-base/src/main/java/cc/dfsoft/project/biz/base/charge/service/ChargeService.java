package cc.dfsoft.project.biz.base.charge.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;

/**
 * 收费服务接口
 * @author zhangjj
 *
 */
public interface ChargeService {
	/**
	 * 查询应收应付记录
	 * @param chargeReq
	 * @return
	 */
	public Map<String,Object> queryAccrualsRecord(ChargeReq chargeReq);
	/**
	 * 查询应付流水
	 * @param chargeReq
	 * @return
	 */
	public Map<String, Object> queryCashFlow(ChargeReq chargeReq);
	
	public Map<String, Object> queryCashFlowNew(ChargeReq chargeReq);
	/**
	 *添加应付流水
	 * @param chargeReq
	 * @return
	 * @throws Exception 
	 */
	public String insertCashFlow(String accrualsId,CashFlow cashFlow,String overChange) throws Exception;
	
	public void updateAccrualsRecord(AccrualsRecord accrualsRecord);
	/**
	 * 取消收款
	 * @param arId 应收应付流水id
	 * @param cfId 收付流水id
	 * @throws ParseException 
	 */
	public void cancleCharge(String arId,String cfId) throws ParseException;
	
	public Map<String, Object> queryAccrualsRecordNew(ChargeReq chargeReq);
	
	/**
	 * 查询右侧详述
	 * @param id
	 * @return
	 */
	public Map<String,Object> queryProject(String id);
	
	/**
	 * 查询是否满足全额收款的条件
	 * @author fulw
	 * @createTime 2017-1-11
	 * @param projId
	 * @return String
	 */
	//public String queryIsFullAmount(String id,String cfFlag);
	
	
	/**
	 * 查询已付合同款或工程款
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public BigDecimal queryPayAmount(String projId,String type);
	/**
	 * 付款成功修改实付流水成功标记
	 * @param cashFlow
	 * @return
	 */
	public boolean modifyCashFlowById(CashFlow cashFlow);
	/**
	 * 查询付款单
	 * @param cfId
	 * @param projNo
	 * @return
	 */
	public CashFlow queryById(String cfId, String projNo);
	/**
	 * 根据工程
	 * @param projId
	 * @param cfFlag
	 * @param conNo 
	 * @return
	 */
	public List<CashFlow> queryCashFlowByProjIdType(String projId, String cfFlag, String conNo);
	/**
	 * 首付款未交清的标红工程
	 * @author liaoyq
	 * @createTime 2018年6月12日
	 * @param projId
	 */
	void projSign(String projId);
	/**
	 * 补充协议款未收的标红工程
	 * @author liaoyq
	 * @createTime 2018年6月12日
	 * @param projId
	 */
	public void projSignSup(String projId);

}
