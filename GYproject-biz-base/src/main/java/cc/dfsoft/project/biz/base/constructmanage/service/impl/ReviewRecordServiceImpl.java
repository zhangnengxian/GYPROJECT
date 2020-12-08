package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.ReviewRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ReviewRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ReviewRecord;
import cc.dfsoft.project.biz.base.constructmanage.service.ReviewRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ReviewRecordServiceImpl implements ReviewRecordService{
	
	@Resource
	ReviewRecordDao reviewRecordDao;
	
	@Override
	public Map<String, Object> queryReviewRecord(ReviewRecordQueryReq reviewRecordQueryReq) {
		
		return reviewRecordDao.queryReviewRecord(reviewRecordQueryReq);
	}
	/**
	 * 会审记录保存
	 * zm
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String reviewRecordSave(ReviewRecord reviewRecord) {
		//判断会审记录表中是否有对应的ID
		if(StringUtil.isBlank(reviewRecord.getRrId())){
			reviewRecord.setRrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
		}
		reviewRecordDao.saveOrUpdate(reviewRecord);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

}
