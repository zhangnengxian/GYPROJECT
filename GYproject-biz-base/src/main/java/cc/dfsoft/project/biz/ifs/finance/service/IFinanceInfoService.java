package cc.dfsoft.project.biz.ifs.finance.service;

import java.text.ParseException;

import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.ifs.log.entity.WebserviceLog;

/**
 * 
 * 描述:调用用友财务接口，组装参数
 * @author liaoyq
 * @createTime 2017年11月15日
 */
public interface IFinanceInfoService {
	
	
	/**
	 * 同步工程信息的客户端实现功能
	 * @param proID 工程项目ID
	 * @param opType 操作类型
	 * @param updateType 更新类型
	 * @return
	 */
	public String synProjectInfoClient(String proID,String opType,String updateType,String contractType)throws ParseException;
	
	/**
	 * 收款功能客户端实现功能
	 * @param project
	 * @param cashFlow
	 * @return
	 */
	public String gatherClient(Project project,CashFlow cashFlow,String updateType,String serviceType);
	
	/**
	 * 付款功能客户端实现功能
	 * @param project
	 * @param cashFlow
	 * @return
	 */
	public String paymentClient(Project project,CashFlow cashFlow,String updateType,String serviceType);
	

	/**
	 * 获取客户编码
	 * @param custId
	 * @param value
	 * @return
	 */
	public String getUnitCode(String custId, String value);

	/**
	 * 增加接口调用日志
	 * @param webserviceLog
	 */
	public void saveWebserviceLog(WebserviceLog webserviceLog);
}
