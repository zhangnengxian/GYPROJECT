package cc.dfsoft.project.biz.base.charge.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;

/**
 * 应收应付服务接口
 * @author songqn
 *
 */
public interface AccrualsRecordService {

	/**
	 * 保存应收应付记录
	 * @param project
	 * @param arTypeID
	 * @param arFlag
	 * @param amount
	 */
	public void insertAccrualsRecord(Project project, String arType ,int arFlag , BigDecimal amount);
	
	/**
	 * 保存应收应付记录
	 * 添加该方法目的：用于施工合同、分包合同调用时，获取此次操作时所形成的收付流水
	 * 签订智能表时也生成应收应付流水
	 * @author pengtt
	 * @param project  工程
	 * @param arId     收付流水id
	 * @param arType   收款类型
	 * @param arFlag   收付标志
	 * @param amount   金额
	 * @param contractType  合同类型
	 * @param conNo    合同编号
	 */
	public void insertAccrualsRecord(Project project, String arId, String arType,
			int arFlag, BigDecimal amount, String contractType,String conNo);
	
	/**
	 * 根据主键id获取收付流水对象
	 * @author pengtt
	 * @createTime 2016-08-11
	 * @param arId
	 * @return
	 */
	public AccrualsRecord get(String arId);
	
	/**
	 * @author pengtt
	 * @createTime 2016-08-17
	 * @param projId  工程id
	 * @param projNo  工程编号
	 * @param arFlag  收付标志
	 * @return
	 */
	public List<AccrualsRecord> findbyProjIdType(String projId,String projNo,String arFlag, String conNo);
	
	/**
	 * @author zhangmeng
	 * @createTime 2016-09-26
	 * @param projId  工程id
	 * @param projNo  工程编号
	 * @param arFlag  收付标志
	 * @return
	 */
	public List<AccrualsRecord> findbyProjIdType(String projNo);
	
	/**
	 * 保存应付记录
	 * 添加该方法目的：资料交接确认后形成尾款记录
	 * @author pengtt
	 * @param project
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public void insertAccrualsRecord(Project project) throws ParseException, Exception;

	/**
	 * 合同修改时查询需要更新的流水
	 * @param projId
	 * @param arFlag
	 * @param contractType
	 * @return
	 */
	public List<AccrualsRecord> findForUpdate(String projId, String arFlag, String contractType);
	
	/**
	 * 批量生成付款金额
	 * @author fuliwei
	 * @createTime 2017年12月25日
	 * @param 
	 * @return
	 */
	public void insertPaAccrualsRecord(String paId);

	/**
	 * 根据政府结算审定价调整应收流水
	 * @author liaoyq
	 * @createTime 2018年5月21日
	 * @param govAuditCost
	 */
	public void updateByGovAuditCost(GovAuditCost govAuditCost);
	
	public List<AccrualsRecord> getDataByProj(String projId,String arType,String arFlag,String arStatus);
}
