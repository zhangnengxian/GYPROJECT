package cc.dfsoft.project.biz.base.settlement.service;

import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;

import java.text.ParseException;
import java.util.Map;

public interface SettlementDeclarationService {

	
	/**
	 * 查询计划表
	 * @param projId
	 * @return
	 */
	ConstructionPlan constructionPlan(String projId);
	
	Budget budGet(String projId);
	
	SubContract subContract(String projId);
	
	SettlementDeclaration getSettlementDeclaration(String projId);
	
	String saveSettlementDeclaration(SettlementDeclaration sd) throws Exception;

	
	/**
	 * 批量添加分包工程量
	 */
	String batInsertSubQualities(SubQuantitiesDto qdto);
	
	Map<String,Object> querySettlement(SettlementDeclarationReq req) throws ParseException;
	public Map<String, Object> querySettlementForAudit(SettlementDeclarationReq req)throws ParseException;

	public Map<String, Object> querySettlementForEnd(SettlementDeclarationReq req,String grade)throws ParseException;

	/**
	 * 初审派遣保存
	 * @param designDispatchReq
	 */
	void update(DesignDispatchDto designDispatchReq);

	/**
	 * 根据工程ID获取分包补充协议
	 * @param projId
	 * @return
	 */
	SubSupplyContract subSupplyContract(String projId);

	/**
	 * 推送结算报审
	 * @return
	 */

	String pushSettlement(SettlementDeclaration sd) throws Exception;

	/**
	 * 根据工程ID、菜单ID查询报表版本
	 * @author wanghuijun
	 * @createTime 2018年10月8日
	 * @param projId
	 * @return
	 */
	public String findPrintDataByProjId(String projId,String type);

	/**
	 * 查询待结算初审的工程
	 * @param req
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> querySettlementAuditStart(SettlementDeclarationReq req) throws ParseException;

    boolean rollBackContainsSettlementDeclaration(String projId, String rollBackReason);
}
