package cc.dfsoft.project.biz.base.budget.dao;

import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
/**
 * 变更记录Dao
 * @author Administrator
 *
 */
public interface ChangeManagementDao extends CommonDao<ChangeManagement, String>{
	
	/**
	 * 变更记录列表条件查询
	 * @author fuliwei
	 * @createTime 2016-07-12
	 * @param changeManagementQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> queryChangeManagement(ChangeManagementQueryReq changeManagementQueryReq) throws ParseException;
	
	ChangeManagement queryByprojId(String projId);
	/**
	 * 更新变更状态为1已处理
	 * @author zhangjj
	 * @createTime 2016-08-08
	 */
	public void updateChangeState(String id);

	/**
	 * 获取自增编号
	 * @author cui
	 * @createTime 2017-09-09
	 */
	String getMaxCmNo(String date,String projId);
	/**
	 * 根据工程ID查设计变更记录
	 * @param projId
	 * @param designChangeType 
	 * @return
	 */
	List<ChangeManagement> findByProjId(String projId, String designChangeType,
			String changeType);

	/**
	 * 查询未终止的变更
	 * @author liaoyq
	 * @createTime 2018年4月26日
	 * @param projId
	 * @param changeType
	 * @return
	 */
	List<ChangeManagement> findNoCancelCM(String projId,String changeType);

	/**
	 * 查询变更记录存在要上传材料的通知信息
	 * @author liaoyq
	 * @createTime 2018年6月28日
	 * @return
	 */
	List<ChangeManagement> queryChangementNotice();

	/**
	 * 查询待审核的变更记录
	 * @author liaoyq
	 * @createTime 2018年6月28日
	 * @return
	 */
	List<ChangeManagement> queryChangementAuditNotice(String changeType);
}
