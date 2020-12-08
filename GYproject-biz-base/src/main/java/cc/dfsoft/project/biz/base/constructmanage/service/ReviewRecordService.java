package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ReviewRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ReviewRecord;

/**
 * 会审记录service
 * @author pengtt
 * @createTime 2016-06-30
 *
 */
public interface ReviewRecordService {
	
	/**
	 * @author pengtt
	 * 会审记录列表查询
	 * @param reviewRecordQueryReq
	 * @return
	 */
	public Map<String,Object> queryReviewRecord(ReviewRecordQueryReq reviewRecordQueryReq);
	
	/**
	 * @author pengtt
	 * 会审记录保存
	 * @param reviewRecord
	 * @return 
	 */
	public String reviewRecordSave(ReviewRecord reviewRecord);
	
}
