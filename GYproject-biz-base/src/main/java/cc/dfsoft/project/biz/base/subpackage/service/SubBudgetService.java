package cc.dfsoft.project.biz.base.subpackage.service;

import cc.dfsoft.project.biz.base.budget.dto.SubBudgetReq;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.subpackage.entity.SubBudget;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

public interface SubBudgetService {

	/**
	 * 根据工程ID查施工预算详述
	 * @param id
	 * @return
	 */
	SubBudget viewSubBudget(String projId);

	/**
	 * 保存施工预算
	 * @param subBudget
	 * @return
	 */
	String saveSubBudget(SubBudget subBudget) throws Exception;

	/**
	 * 施工预算审核保存
	 * @param manageRecord
	 * @param string
	 * @param string2
	 * @param actionCode
	 * @param moduleCodeSubcontract
	 */
	void auditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants);

	/**
	 * 初审保存信息
	 * @param manageRecord
	 * @param docTypeID
	 * @param auditLevel
	 * @param stepID
	 * @param constants
	 */
	void firstAuditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants);

	/**
	 * 施工预算打印查询
	 * @param subBudgetReq
	 * @return
	 */
	Map<String, Object> querySubBudgetPrint(SubBudgetReq subBudgetReq)throws ParseException;

	/**
	 * 施工预算打印标记
	 * @param sbId
	 * @return
	 */
	String signBudgetPrint(String sbId);

	/**
	 * 施工预算派遣预算员列表加载
	 * @param designerQueryReq
	 * @return
	 */
	Map<String, Object> queryBudgeter(DesignerQueryReq designerQueryReq)throws ParseException;

	/**
	 * 根据工程ID、工程类型打印报表
	 * @author wanghuijun
	 * @createTime 2018年10月8日
	 * @param projId
	 * @param type
	 * @return
	 */
	public String findPrintDataByProjId(String projId,String type);

	/**
	 * 获取数据库服务器时间
	 * @return
	 */
	Date getDatabaseDate();

	/**
	 * 根据工程编号查询预算信息
	 * @param projId
	 * @return
	 */
	SubBudget findSubBudgetByProjID(String projId);

	/**
	 * 
	 * 注释：保存施工预算工程量清单分项
	 * @author liaoyq
	 * @createTime 2019年8月7日
	 * @param subBudget
	 *
	 */
	void saveQuantities(SubBudget subBudget);

	boolean rollBackContainsSubBudget(String projId,String rollBackReason);
}
