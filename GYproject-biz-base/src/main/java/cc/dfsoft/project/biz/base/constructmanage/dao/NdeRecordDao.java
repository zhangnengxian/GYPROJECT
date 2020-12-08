package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * 描述:无损检测dao接口
 * @author liaoyq
 * @createTime 2017年9月27日
 */
public interface NdeRecordDao extends CommonDao<NdeRecord, String> {

	/**
	 * 根据通知业务单ID查询无损检测信息
	 * @param bcId
	 * @return
	 */
	NdeRecord findByBcId(String bcId);
	
	/**
	 * 通过工程id查询探伤委托
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param  projId
	 * @return NdeRecord
	 */
	public NdeRecord findBypProjId(String projId);

	void updateVersionByNdId(NdeRecord nd);
	
	/**
	 * 通过工程ID查找探伤委托
	 * @param projId
	 * @return
	 * @throws Exception
	 */
	public List<NdeRecord> findNdeBypProjId(String projId);

}
