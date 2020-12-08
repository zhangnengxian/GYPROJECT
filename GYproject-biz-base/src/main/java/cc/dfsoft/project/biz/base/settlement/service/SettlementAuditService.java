package cc.dfsoft.project.biz.base.settlement.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;

public interface SettlementAuditService {

	/**
	 * 查询结算报审
	 * @param id
	 * @return
	 */
	SettlementDeclaration auditStartDetail(String projId) throws ParseException;

	/**
	 * 保存初审
	 * @param 
	 * @return
	 */
	void saveAuditStart(SettlementDeclaration settlementDeclaration) throws Exception;

	/**
	 * 结算审核条件列表查询
	 * @param settlementDeclarationReq
	 * @return
	 */
	Map<String, Object> querySettlementDeclaration(SettlementDeclarationReq settlementDeclarationReq)throws ParseException;

	/**
	 * 保存终审
	 * @param 
	 * @return
	 * @throws ParseException 
	 */
	String saveAuditDone(SettlementDeclaration settlementDeclaration) throws Exception;

	/**
	 * 保存初审工程量
	 * @param 
	 * @return
	 */
	void batInsertSubQualities(SubQuantitiesDto qdto);

	/**
	 * 结算员列表查询
	 * @param designerQueryReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryBudgetMember(DesignerQueryReq designerQueryReq) throws ParseException;

	/**
	 * 造价经理列表查询
	 * @param designerQueryReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryMember(DesignerQueryReq designerQueryReq);

	/**
	 * 是否打印标记
	 * @param projId
	 */
	void signSettlementAuditPrint(String projId);

	/**
	 * 查询结算员
	 * @param designerQueryReq 查询辅助类
	 * @return Map<String, Object>
	 */
	Map<String, Object> querySettleMember(DesignerQueryReq designerQueryReq);

	/**
	 * 根据工程ID查询结算信息
	 * @param projId
	 * @return
	 */
	SettlementDeclaration findByProjId(String projId);
	
	
	/**
	 * 保存监检报告登记
	 * @author fuliwei  
	 * @date 2018年11月14日  
	 * @version 1.0
	 */
	public void saveMonitoringReport(SubBudget subBudget);
}
