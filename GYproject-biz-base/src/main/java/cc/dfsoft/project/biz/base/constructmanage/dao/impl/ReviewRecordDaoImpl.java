package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.ReviewRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ReviewRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ReviewRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ReviewRecordDaoImpl extends NewBaseDAO<ReviewRecord,String> implements ReviewRecordDao{

	@Override
	public Map<String, Object> queryReviewRecord(ReviewRecordQueryReq reviewRecordQueryReq) {
		 Criteria c = super.getCriteria();
		 //会审id
		 if(StringUtils.isNotBlank(reviewRecordQueryReq.getDrId())){
			 c.add(Restrictions.eq("drId",reviewRecordQueryReq.getDrId()));
		 }
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<ReviewRecord> reviewRecordList = this.findBySortCriteria(c, reviewRecordQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, reviewRecordQueryReq.getDraw(),reviewRecordList);
	}

	@Override
	public List<ReviewRecord> findByDrawingNo(String drawingNo) {
		StringBuffer hql = new StringBuffer("from ReviewRecord where drawingNo = '").append(drawingNo).append("'");
		return super.findByHql(hql.toString());
	}

}
