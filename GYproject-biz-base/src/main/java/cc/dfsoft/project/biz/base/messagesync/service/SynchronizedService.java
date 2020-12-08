package cc.dfsoft.project.biz.base.messagesync.service;

import cc.dfsoft.project.biz.base.messagesync.hongju.pojo.*;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;

import java.util.List;

/**
 *@ Description: 接口信息同步
 *@ author: zhangnx
 *@ Date: 2019/11/22 10:32
 *@ Version:1.0
 * */
public interface SynchronizedService {

	/**
	* @ Description: 同步施工任务单信息（鸿巨）
	* @ author zhangnx
	* @param  projId 工程ID
	* @ date 2019/11/22 10:31
	**/
	ResultMsg callSynchronizedConstructionTask(String projId, String interfaceNo);



	/**
	 * @Description: 同步交底信息（鸿巨）
	 * @author zhangnx
	 * @param  projId 工程ID
	 * @param  cwId 交底ID
	 * @date 2019/11/28 13:49
	 **/
	ResultMsg callSynchronizedConstructionWork(String projId, String cwId,String interfaceNo);


	/**
	* @Description: 同步开工报告信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 15:08
	**/
	ResultMsg callSynchronizedWorkReport(String projId,String interfaceNo);



	/**
	* @Description: 同步预结算信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 13:50
	**/
	ResultMsg callSynchronizedPreSettlement(String projId,String interfaceNo);


	/**
	* @Description: 同步施工合同信息（鸿巨）
	* @author zhangnx
	* @param  projId 工程ID
	* @date 2019/11/28 13:53
	**/
	ResultMsg callSynchronizedSubContract(String projId,String interfaceNo);



	/**
	* @Description: 同步自检信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 13:56
	**/
	ResultMsg callSynchronizedSelfCheck(String projId,String interfaceNo);

	/**
	* @Description: 同步预验收信息（鸿巨）
	* @author zhangnx
	* @param projId 工程ID
	* @date 2019/11/28 14:54
	**/
	ResultMsg callSynchronizedPreinspection(String projId,String interfaceNo);


	/**
	* @Description: 同步联合验收信息（鸿巨）
	* @author zhangnx
	* @param  projId 工程ID
	* @date 2019/11/28 14:54
	**/
	ResultMsg callSynchronizedJointAcceptance(String projId,String interfaceNo);




	}
