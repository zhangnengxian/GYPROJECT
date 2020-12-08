package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 业务沟通Dao
 * @author Yuanyx
 *
 */
public interface BusinessCommunicationDao extends CommonDao<BusinessCommunication,String>{

	/**
	 * 业务沟通通知条件查询
	 * @param businessCommunicationReq
	 * @return
	 */
	Map<String, Object> queryBusinessCommunication(BusinessCommunicationReq businessCommunicationReq);
	
	/**
	 * 查询业务通知单列表
	 * @author fuliwei
	 * @createTime 2017年9月21日
	 * @param 
	 * @return
	 */
	List<BusinessCommunication> queryBusinessCommunicationList(BusinessCommunicationReq businessCommunicationReq);

	void updateVersionByBcId(BusinessCommunication bc);

	/**
	 * 查询探伤委托信息
	 * @param projId
	 * @param bcTypeDetail
	 * @return
	 */
	List<BusinessCommunication> queryByProjIdAndTypeDetail(String projId,
			String bcTypeDetail);

	void updateBuscomNotice(String projId, String origsuJgjId, String currSuJgjId,String currSuJgj);

	/**
	 * 查询业务沟通通知
	 * @author fuliwei
	 * @date 2019/8/28
	 * @param
	 * @return
	*/
	public List<Map<String, Object>> queryBcNotice();
}
