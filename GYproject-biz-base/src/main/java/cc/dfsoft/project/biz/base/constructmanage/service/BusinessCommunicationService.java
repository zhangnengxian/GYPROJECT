package cc.dfsoft.project.biz.base.constructmanage.service;

import java.text.ParseException;
import java.util.Map;

import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.NdeRecordBcDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;

/**
 * 业务沟通接口
 * @author Yuanyx
 *
 */
public interface BusinessCommunicationService {

	/**
	 * 业务沟通通知条件查询
	 * @param businessCommunicationReq
	 * @return
	 */
	Map<String, Object> queryBusinessCommunication(BusinessCommunicationReq businessCommunicationReq);

	/**
	 * 业务沟通保存
	 * @param businessCommunication
	 * @return
	 * @throws ParseException 
	 */
	String saveBusinessCommunication(BusinessCommunication businessCommunication) throws Exception;

	/**
	 * 获取当前日期
	 * @return
	 */
	String findReplyDate();

	/**
	 * 保存回复信息
	 * @param businessCommunication
	 * @return
	 * @throws Exception 
	 */
	String replyBusinessCommunication(BusinessCommunication businessCommunication)throws HibernateOptimisticLockingFailureException, Exception;

	/**
	 * 删除通知记录
	 * @param bcId
	 * @return
	 */
	String delBusinessCommunication(String bcId)throws HibernateOptimisticLockingFailureException;

	/**
	 * 根据业务沟通单ID查询信息
	 * @param bcId
	 * @return
	 */
	NdeRecordBcDto viewByBcId(String bcId);

	/**
	 * 更新实体
	 * @param bc
	 */

	void updateByBcId(BusinessCommunication bc);
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

	/**
	 * 注释：根据返修的无损检测单ID查询返修信息
	 * @author liaoyq
	 * @createTime 2018年12月26日
	 * @param nrId
	 * @return
	 *
	 */
	BusinessCommunication viewByTestResult(String nrId);

	void updateBuscomNotice(String projId, String origsuJgjId, String currSuJgjId,String currSuJgj);
}
