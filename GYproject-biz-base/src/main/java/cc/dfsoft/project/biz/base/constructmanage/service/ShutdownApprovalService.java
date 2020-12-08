package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownApprovalReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownRecordReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;

/**
 * 复工申报业务接口
 * @author liaoyq
 *
 */
public interface ShutdownApprovalService {
	
	/**
	 * 根据id查询详述
	 * @param id
	 * @return
	 */

	ShutdownApproval queryById(String id);

	/**
	 * 分页查询记录
	 * @param shutdownApprovalReq
	 * @return
	 */
	Map<String, Object> queryList(ShutdownApprovalReq shutdownApprovalReq);

	/**
	 * 根据停工令Id查询复工申报详述
	 * @param id
	 * @return
	 */
	ShutdownApproval queryBySdrId(String id);

	/**
	 * 保存复工报审信息
	 * @param shutdownApproval
	 */
	void saveShutdownApproval(ShutdownApproval shutdownApproval) throws Exception;

	/**
	 * 推送复工报审信息
	 * @param shutdownApproval
	 */
	void pushShutdownApprovl(ShutdownApproval shutdownApproval);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
