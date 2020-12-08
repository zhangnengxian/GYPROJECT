package cc.dfsoft.project.biz.base.constructmanage.service;

import java.text.ParseException;

import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;

/**
 * 
 * 描述:无损检测service接口层
 * @author liaoyq
 * @createTime 2017年9月27日
 */
public interface NdeRecordService {

	/**
	 * 保存无损检测信息
	 * @param ndeRecord
	 * @return 
	 * @throws ParseException 
	 */
	String saveNdeRecord(NdeRecord ndeRecord) throws Exception;

	/**
	 * 根据通知业务单ID查询信息
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

	/**
	 * 更新实体
	 * @param nd
	 */
	void update(NdeRecord nd);

	void updateByNdId(NdeRecord nd);

	/**
	 * 修改无损检测
	 * @param ndeRecordOld
	 * @throws Exception 
	 */
	void updateNdeRecord(NdeRecord ndeRecordOld) throws Exception;

	/**
	 * 探伤委托推送时，增加检测员的签字通知
	 * @author liaoyq
	 * @createTime 2019-4-12
	 * @param ndeRecord
	 */
	void addCheckerSignNotice(NdeRecord ndeRecord, Project project);
}
