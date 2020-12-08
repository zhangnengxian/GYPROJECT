package cc.dfsoft.project.biz.base.settlement.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementJonintlySign;

public interface SettlementJonintlySignService {
	
	/**
	 * 查询左侧工程列表
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryProject(ProjectQueryReq req)throws ParseException;
	
	/**
	 * 结算汇签单列表
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySettlementJonintlySign(SettlementDeclarationReq req)throws ParseException;

	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年9月26日
	 * @param 
	 * @return
	 */
	public SettlementJonintlySign findById(String projId);
	
	
	/**
	 * 结算汇签单保存
	 * @author fuliwei
	 * @createTime 2017年8月9日
	 * @param 
	 * @return
	 */
	public void saveSettlementJonintlySign(SettlementJonintlySign SettlementJonintlySign) throws Exception;

	/**
	 * 标记结算汇签单
	 * @author liaoyq
	 * @createTime 2018年4月18日
	 * @param sjsId
	 */
	public void signSettlementJonintly(String sjsId);
	
}
