package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.DelayReasonDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.DealyReasonQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DelayReason;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class DelayReasonDaoImpl extends NewBaseDAO<DelayReason, String> implements DelayReasonDao{

	@Override
	public Map<String, Object> queryDealyReasonList(DealyReasonQueryReq dealyReasonQueryReq) {
		 Criteria c = super.getCriteria();
			
		 //工程大类
		 if(StringUtils.isNotBlank(dealyReasonQueryReq.getDelayReasonDes())){
			 c.add(Restrictions.like("delayReasonDes","%"+dealyReasonQueryReq.getDelayReasonDes()+"%"));
		 }
		
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<DelayReason> projectTypeList = this.findBySortCriteria(c, dealyReasonQueryReq);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, dealyReasonQueryReq.getDraw(),projectTypeList);
	}

	@Override
	public void delDealyReason(String id) {
		DelayReason pt=this.get("delayReasonId", id);
		if(pt!=null){
			this.delete(pt);
		}
	}
	
	/**
	 * 延期原因列表
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public List<DelayReason> findDelayReason() {
		Criteria c = super.getCriteria();
	    c.addOrder(Order.asc("delayReasonId"));
	    // 根据条件获取查询信息
		return c.list();
	}
	
	
}
