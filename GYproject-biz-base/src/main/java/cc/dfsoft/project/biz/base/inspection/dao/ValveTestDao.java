package cc.dfsoft.project.biz.base.inspection.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.inspection.dto.ValveTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.ValveTest;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 阀门试验dao层
 * @author liaoyq
 *
 */
public interface ValveTestDao extends CommonDao<ValveTest, String> {

	/**
	 * 根据报验单ID 分页查询阀门试验记录
	 * @param valveTestReq
	 * @return
	 */
	Map<String, Object> queryValveTest(ValveTestReq valveTestReq);

	/**
	 * 根基报验单ID删除阀门试验记录
	 * @param pcId
	 */
	void deleteByPcId(String pcId);

	/**
	 * 置空记录pcID
	 * @param pcId
	 */
	void updateByPcId(String pcId);

	/**
	 * 回写报验单ID
	 * @param id
	 * @param pcId
	 */
	void updatePcIdByRecordId(String id, String pcId);

	/**
	 * 分页查询记录
	 * @param valveTestReq
	 * @return
	 */
	Map<String, Object> queryRecords(ValveTestReq valveTestReq);

}
