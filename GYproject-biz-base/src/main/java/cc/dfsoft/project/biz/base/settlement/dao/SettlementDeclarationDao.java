package cc.dfsoft.project.biz.base.settlement.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.settlement.dto.SettlementDeclarationReq;
import cc.dfsoft.project.biz.base.settlement.entity.SettlementDeclaration;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SettlementDeclarationDao extends CommonDao<SettlementDeclaration, String>{

	/**
	 * 查报审记录单
	 * @param projId
	 * @return
	 */
	SettlementDeclaration findByProjId(String projId);
	
	/**
	 * 查询结算材料扣款
	 * @param projId
	 * @return
	 * @throws ParseException
	 */
	
    SettlementDeclaration findSByprojId(String projId)throws ParseException;

	/**
	 * 结算审核条件列表查询
	 * @param settlementDeclarationReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> querySettlementDeclaration(SettlementDeclarationReq settlementDeclarationReq)throws ParseException;

	Map<String, Object> querySettlement(SettlementDeclarationReq req,List list)throws ParseException;

	/**
	 * 结算员查询
	 * @param designerQueryReq
	 * @return
	 */
	Map<String, Object> queryBudgetMember(DesignerQueryReq designerQueryReq);

	/**
	 * 造价经理查询
	 * @param designerQueryReq
	 * @return
	 */
	Map<String, Object> queryCostManager(DesignerQueryReq designerQueryReq);

	/**
	 * 查询结算员
	 * @author liaoyq
	 * @createTime 2017-8-11
	 * @param designerQueryReq
	 * @return
	 */
	Map<String, Object> querySettleMember(DesignerQueryReq designerQueryReq);
}
