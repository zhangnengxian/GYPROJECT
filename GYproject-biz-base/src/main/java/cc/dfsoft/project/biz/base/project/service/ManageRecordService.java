package cc.dfsoft.project.biz.base.project.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.project.dto.ManageRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.FallbackApply;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 管理记录服务接口
 * @author pengtt
 * @createTime 2016-06-27
 *
 */
public interface ManageRecordService {

	/**
	 * 查询管理记录
	 * @author pengtt
	 * @createTime 2016-06-27
	 * @param manageRecordQueryReq 查询对象
	 * @return
	 */
	public Map<String,Object> queryManageRecord(ManageRecordQueryReq manageRecordQueryReq);
	
	/**
	 * 产生审核记录
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	public void auditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );
	
	
	/**
	 * 现场踏勘审核
	 * @author fuliwei
	 * @createTime 2017年9月23日
	 * @param 
	 * @return
	 */
	public void auditConnectGasAudit(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );


	/**
	 * 施工合同审核
	 * @author
	 * @createTime 2
	 * @param
	 * @return
	 * @throws ParseException 
	 */
	public void subContractAudit(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants ) throws ParseException;

	/**
	 * 保存受理审核信息
	 * @param manageRecord
	 * @param docTypeID
	 * @param auditLevel
	 * @param stepID
	 * @param constants
	 */
	public void accepAudit(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants);
	
	/**
	 * 产生审核记录(此方法只用于图纸审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	public String auditDrawingSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );
	
	
	/**
	 * 获取数据库时间
	 * @return
	 */
	public java.util.Date getDatabaseDate();
	
	/**
	 * 产生审核记录(此方法只用于合同审核)
	 * @author pengtt
	 * @createTime 2016-08-30
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 * @return arId          应收应付流水Id
	 * @throws Exception 
	 */
	public String contractAuditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID) throws Exception;
	
	/**
	 * 查询管理记录，记录中包含人员签字
	 * @author zhangjj
	 * @createTime 2016-09-21
	 * @param manageRecordQueryReq 查询对象
	 * @return
	 */
	public Map<String, Object> queryManageRecordSign(ManageRecordQueryReq manageRecordQueryReq);
	
	/**
	 * 查询审核信息结果
	 * @author ht.hu
	 * @param projId
	 * @param StepId
	 * @return
	 */
	public ManageRecord queryAuditInformation(String projId, String StepId);


	public ManageRecord queryAuditInsInformation(String businessOrderId);
	
	

	/**
	 * 产生审核记录(此方法只用于分包合同审核)
	 */
	public String subContractAuditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID) throws Exception;
	
	/**
	 * 产生审核记录（用于签证审核)
	 * @param manageRecord
	 * @param string
	 * @param string2
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public String engineeringAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws ParseException, Exception;

	/**
	 * 产生审核记录
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public String auditPlanSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants) throws ParseException, Exception;
	
	/**
	 * 审核安装任务
	 * @param manageRecord
	 * @return
	 */
	public String auditInstTask(ManageRecord manageRecord);
	
	
	/**
	 * 产生审核记录(此方法只用于资料审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	public void auditDataSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );
	
	
	/**
	 * 产生审核记录(此方法只用于电子图审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	public void auditDrawingApplySave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );
	
	/**
	 * 产生审核记录(此方法只用于分部验收审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	public void auditDivisionalAcceptanceSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );

	/**
	 * 保存审核记录
	 */
	public void save(ManageRecord manageRecord);

	/**
	 * 带气工程审核保存
	 * @auther cui by 2017-8-16
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 * @throws ParseException 
	 */
	void gasAuditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants) throws ParseException;

	/**
	 * 产生审核记录(工程预算审核)
	 * @author liaoyq
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 * @param constants      
	 */
	public void auditBudgetSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants);
	
	
	/**
	 * 产生审核记录(此方法只用于付款审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	public void auditPaymentApplySave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );


	/**
	 * 产生审核记录(工程造价审核)
	 * @author liaoyq
	 * @createTime 2017-8-17
	 * @param manageRecord 审核记录
	 * @param docTypeId    单据类型
	 * @param auditLevel   审核级别
	 * @param stepId       步骤ID
	 * @param constants
	 */
	public void auditProjectCostSave(ManageRecord manageRecord, String docTypeId ,String auditLevel ,String stepID,String constants);

	/**
	 * 产生结算终审审核记录
	 * @param manageRecord
	 * @param docTypeID
	 * @param auditLevel
	 * @param stepID
	 * @param constants
	 * @throws Exception 
	 */
	void auditSettlementEndSave(ManageRecord manageRecord, String docTypeID,String auditLevel, String stepID, String constants) throws Exception;
	
	/**
	 * 产生审核记录(此方法只用于延期审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	public void auditApplyDelaySave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );

	/**
	 * 合同修改审核保存
	 * @param manageRecord
	 * @param string
	 * @param string2
	 * @param actionCode
	 * @return
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public String contractModifyAuditSave(ManageRecord manageRecord, String string, String string2, String actionCode) throws ParseException, Exception;

	/**
	 * 根据工程id、步骤id、审核人id查询审核通过的信息
	 * @param projId
	 * @param stepId
	 * @param mrCsr
	 */
	public ManageRecord findByMrCsrId(String projId, String stepId,
			String mrCsr);

	/**
	 * 根据工程id、步骤id、审核级别查询审核通过的信息
	 * @param projId
	 * @param stepId
	 * @param level
	 */
	public ManageRecord findFirstMrCsr(String projId, String stepId,
			String level);


	/**
	 * 智能表合同修改审核保存
	 * @param manageRecord
	 * @param s
	 * @param s1
	 * @param value
	 * @return
	 * @throws ParseException 
	 * @throws Exception 
	 */
	String imcModifyAuditSave(ManageRecord manageRecord, String s, String s1, String value) throws ParseException, Exception;
	
	
	/**
	 * 查询业务单审核信息结果
	 * @author fuliwei
	 * @createTime 2017年12月1日
	 * @param 
	 * @return
	 */
	public ManageRecord queryBusAuditInformation(String businessOrderId, String StepId);
	
	/**
	 * 保存回退审核
	 * @author fuliwei
	 * @createTime 2017年12月5日
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	public void auditFallbackSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants ) throws Exception;
	
	/**
	 * 退回到设计出图
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 */
	public String fallBackDesign(String projId,FallbackApply fa);
	
	/**
	 * 退回到预算记录
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 */
	public String fallBackBudget(String projId,FallbackApply fa);
	
	/**
	 * 退回到施工预算
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 */
	public String fallBackConsBudget(String projId,FallbackApply fa);
	
	/**
	 * 退回到施工结算
	 * @author fuliwei
	 * @createTime 2017年12月15日
	 * @param 
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 */
	public String fallBackSettlement(String projId,FallbackApply fa) throws SerialException, SQLException;
	/**
	 * 协议审核
	 * @author wmy
	 * @param manageRecord
	 * @param docTypeID
	 * @param auditLevel
	 * @param stepID
	 * @return
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public String supplementalContractAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws ParseException, Exception;

	/**
	 * 分合同修改审核
	 * @param manageRecord
	 * @param string
	 * @param string2
	 * @param value
	 * @return
	 * @throws Exception 
	 */
	public String suContractModifyAuditSave(ManageRecord manageRecord,
			String docTypeID, String auditLevel, String stepID) throws Exception;

	/**
	 * 回退不需要审核，直接通过修改工程状态、生成审核历史
	 * @author liaoyq
	 * @createTime 2018年4月16日
	 * @param pro
	 * @param fallbackApply
	 * @return
	 * @throws Exception 
	 */

	public String fallBack(Project pro, FallbackApply fallbackApply) throws Exception;

	/**
	 * 回退不需要审核，直接通过修改工程状态、生成审核历史-新方法
	 * * @author fulw
	 * @param pro
	 * @param fallbackApply
	 * @return
	 * @throws Exception
	 */
	public String fallBackNew(Project pro, FallbackApply fallbackApply) throws Exception;

	/**
	 * 回退到安装合同签订，增加回退时已增加未收款的判断，所以回退时需要删除应收流水
	 * @author liaoyq
	 * @createTime 2018年5月9日
	 * @param projId
	 * @param fa
	 * @throws ParseException 
	 */
	String fallBackContract(String projId, FallbackApply fa) throws ParseException;
	/**
	 * 补充协议修改审核
	 * @author wang.hui.jun
	 */
	public String supplementalContractModifyAuditSave(ManageRecord manageRecord, String docTypeID, String auditLevel, String stepID) throws Exception;
	/**
	 * 保存结算初审
	 * @param manageRecord
	 * @param docTypeID
	 * @param auditLevel
	 * @param stepID
	 * @param constants
	 */
	public void auditSettlementStartSave(ManageRecord manageRecord, String docTypeID,String auditLevel, String stepID, String constants);

	/**
	 * 联合验收审核
	 * @param manageRecord
	 * @param docTypeID
	 * @param auditLevel
	 * @param stepID
	 * @param constants
	 */
	public void auditJointAcceptanceSave(ManageRecord manageRecord, String docTypeID,String auditLevel, String stepID, String constants);

	/**
	 * 获取最后一次审批的信息
	 * @author liaoyq
	 * @createTime 2018-10-11
	 * @param queryReq
	 * @return
	 */
	public ManageRecord findEndMrCsr(ManageRecordQueryReq queryReq);

	/**
	 * 注释：预算记录回退到预算派工
	 * @author liaoyq
	 * @createTime 2018年12月7日
	 * @param projId
	 * @param fa
	 * @return
	 *
	 */
	String fallBackBudgetDis(String projId, FallbackApply fa);

	public void saveAuditIns(ManageRecord manageRecord,String constants);

	/**
	 * 产生审核记录(结算汇签审核)
	 * @param manageRecord   审核记录
	 * @param docTypeID      单据类型
	 * @param auditLevel     审核级别
	 * @param stepID         步骤ID
	 */
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED)
	public void auditSettlementJonintSignAuditSave(ManageRecord manageRecord, String docTypeID ,String auditLevel ,String stepID,String constants );
   
	/**
	 * 智能表合同修改通过时改变流水
	 * @return
	 * @throws ParseException 
	 * 王会军  2019-03-26
	 */
	public String imcModifySaveTrsnRd(IntelligentMeterContract imContract) throws ParseException;
	/**
	 * 补充协议推送时无需审核
	 * @param supplementalContract
	 * 王会军  2019-03-27
	 */
	public void supplementalContractSave(SupplementalContract supplementalContract) throws Exception;
	/**
	 * 补充协议修改无需审核
	 */
	public void supplementalContractModifySave(SupplementalContract supplementalContract) throws Exception;
	
	/**
	 * 查询当前审核级别是否已审核
	 * @param manageRecord
	 * @return
	 */
	public ManageRecord queryManRdHistory(String projId,String businssId,String stepId,String MrAuditLevel,String flag);

    String isAuditSave(ManageRecord manageRecord, String s, String s1, String stepId);

    /**
     * 
     * 注释：签证量审核
     * @author liaoyq
     * @createTime 2019年9月3日
     * @param manageRecord
     * @param docTypeID
     * @param auditLevel
     * @param stepID
     * @return
     * @throws Exception
     *
     */
	String engineeringQuantyAuditSave(ManageRecord manageRecord,
			String docTypeID, String auditLevel, String stepID)
			throws Exception;

	List<Map<String, Object>> queryStepIdByProjId(String projId);
}
