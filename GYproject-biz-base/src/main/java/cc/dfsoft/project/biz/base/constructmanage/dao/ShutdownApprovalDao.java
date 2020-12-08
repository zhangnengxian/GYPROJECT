package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownApprovalReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 复工申报dao接口
 * @author liaoyq
 *
 */
public interface ShutdownApprovalDao extends CommonDao<ShutdownApproval,String>{

	/**
	 * 分页查询记录
	 * @param shutdownApprovalReq
	 * @return
	 */
	Map<String, Object> queryShutdownRecord(
			ShutdownApprovalReq shutdownApprovalReq);

	/**
	 * 根据停工令id查询复工申报详述
	 * @param sdrId
	 * @return
	 */
	ShutdownApproval queryBySdrId(String sdrId);

	/**
	 * 查询签字是否完成
	 * @param sdaId
	 * @return
	 */
	boolean querySignComplete(String sdaId);

}
