package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownRecordReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutDownRecord;

/**
 * 停复工业务接口
 * @author liaoyq
 *
 */
public interface ShutdownRecordService {
	/**
	 * 分页查询工程停复工列表
	 * @param shutdownRecordReq
	 * @return
	 */

	Map<String, Object> queryShutdownRecord(ShutdownRecordReq shutdownRecordReq);

	/**
	 * 根据id查询停复工详述
	 * @param id
	 * @return
	 */
	ShutDownRecord queryById(String id);

	/**
	 * 保存停复工记录
	 * @param shutDownRecord
	 * @return sdrId
	 */
	String saveShutdownRecord(ShutDownRecord shutDownRecord) throws Exception;

	/**
	 * 根据工程Id、工序、停工编号、停复工状态查询记录数
	 * @param shutDownRecord
	 * @return
	 */

	Integer queryByCondition(ShutDownRecord shutDownRecord);

	/**
	 * 根据id修改停复工状态
	 * @param sdrId
	 */
	boolean updateStatusById(String sdrId,Integer sdrStatus);

	/**
	 * 根据停工编号修改停工状态
	 * @param sdrNo
	 * @param sdrType
	 */
	void updateStatusBySdrNo(String sdrNo, String sdrType,Integer sdrStatus);

	/**
	 * 推送停复工令
	 * @author liaoyq
	 * @createTime 2017-8-22
	 * @param shutDownRecord
	 */
	void pushShutdownRecord(ShutDownRecord shutDownRecord);
	
	/**
	 * 删除停复工记录
	 * create person wanghuijun
	 * 2019年5月31
	 * @return
	 */
	public String  deleteSDRecordBySDRId(ShutDownRecord shutDownRecord) throws  Exception;

}
