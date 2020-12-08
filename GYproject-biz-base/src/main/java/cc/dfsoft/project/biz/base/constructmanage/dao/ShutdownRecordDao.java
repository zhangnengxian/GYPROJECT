package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.Date;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownRecordReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutDownRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 停复工dao接口
 * @author liaoyq
 *
 */
public interface ShutdownRecordDao extends CommonDao<ShutDownRecord, String> {

	/**
	 * 分页查询停复工列表
	 * @param shutdownRecordReq
	 * @return
	 */
	Map<String, Object> queryShutdownRecord(ShutdownRecordReq shutdownRecordReq);

	/**
	 * 根据停复工记录id查询停复工详述
	 * @param id
	 * @return
	 */
	ShutDownRecord queryById(String id);

	/**
	 * 根据工程id、工序、停工编号、停复工状态查询记录数量
	 * @param shutDownRecord
	 * @return
	 */
	
	Integer queryByCondition(ShutDownRecord shutDownRecord);

	/**
	 * 根据工程id、工序、停工编号、停复工状态查询记录数量
	 * @param shutDownRecord
	 * @return
	 */
	
	boolean updateStatusById(String sdrId, Integer sdrStatus);

	/**
	 * 根据停工编号修改停工令状态
	 * @param sdrNo
	 * @param sdrType
	 */
	void updateStatusBySdrNo(String sdrNo, String sdrType,Integer sdrStatus);

	/**
	 * 查询未复工的停工令（停工令已推送，但还没有复工令）
	 * @param projId
	 * @return
	 */
	Integer findSRByProjdId(String projId);
	

	/**
	 * 删除停复工记录
	 * create person wanghuijun
	 * 2019年5月31
	 * @return
	 */
	public void  deleteSDRecordBySDRId(ShutDownRecord shutDownRecord) throws  Exception;
}
