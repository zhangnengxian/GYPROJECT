package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.ReviewRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ReviewRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 会审记录Dao
 * @author pengtt
 * @createTime 2016-06-30
 *
 */
public interface ReviewRecordDao extends CommonDao<ReviewRecord,String>{
	
	/**
	 * 会审记录
	 * @param reviewRecordQueryReq
	 * @return
	 */
	public Map<String, Object> queryReviewRecord(ReviewRecordQueryReq reviewRecordQueryReq);
	
	
	/**
	 * 按设计图编号查询
	 * @param drawingNo
	 * @return
	 */
	public List<ReviewRecord> findByDrawingNo(String drawingNo);
	
}
