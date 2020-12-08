package cc.dfsoft.project.biz.base.charge.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 应收应付记录DAO处理接口
 * @author 
 *
 */
public interface AccrualsRecordDao  extends CommonDao<AccrualsRecord, String>{
	
	public Map<String, Object> queryAccrualsRecord(ChargeReq chargeReq);
	
	/**
	 * @author pengtt
	 * @createTime 2016-08-17
	 * @param projId 
	 * @param projNo
	 * @param arFlag
	 * @return
	 */
	public List<AccrualsRecord> findbyProjIdType(String projId, String projNo, String arFlag,String conNo);
	
	public List<AccrualsRecord> getDataByProj(String projId, String arType, String arFlag, String arStatus);

	/**
	 * @author wangang
	 * @createTime 2016-12-14
	 * @param projId
	 * @param arType
	 * @return
	 */
	public List<AccrualsRecord> getDataByProjNew(String projId, String arType);
	
	/**
	 * @author zhangmeng
	 * @createTime 2016-09-26
	 * @return
	 */
	public List<AccrualsRecord> findbyProjIdType(String projNo);

	/**
	 * 根据工程ID、款项类型获取应收应付流水
	 * @param projId
	 * @param arType
	 * @return
	 */
	public AccrualsRecord findByType(String projId, String arType);
	
	public Map<String, Object> queryAccrualsRecordNew(ChargeReq chargeReq);
	
	
	/**
	 * 查询应收大于实收记录数
	 * @author fuliwei
	 * @createTime 2017年3月5日
	 * @param 
	 * @return
	 */
	public List<AccrualsRecord> queryAccrualsRecordLength();

	/**
	 * 合同修改查询需要更新的流水
	 * @param projId
	 * @param arFlag
	 * @param contractType
	 * @return
	 */
	public List<AccrualsRecord> findForUpdate(String projId, String arFlag, String contractType);
	
	/**
	 * 查询收费通知
	 * @author fuliwei
	 * @createTime 2017年11月24日
	 * @param 
	 * @return
	 */
	public List<AccrualsRecord> findAmountNotice(String arType,String arFlag,String deptId);

	/**
	 * 删除工程的应收、应付流水
	 * @author liaoyq
	 * @createTime 2018年5月7日
	 * @param projId
	 */
	void deleteArList(String projId, String arFlag, String conNo);
	
}
