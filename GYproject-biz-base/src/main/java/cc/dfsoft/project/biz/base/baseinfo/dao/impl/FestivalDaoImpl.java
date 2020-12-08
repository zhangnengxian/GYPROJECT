package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.FestivalDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.FestivalReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Festival;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.DateUtil;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 节假日、补班配置dao实现
 * @author liaoyq
 * @createTime 2018年5月7日
 */
@Repository
public class FestivalDaoImpl extends NewBaseDAO<Festival, String> implements FestivalDao {

	/**
	 * 查询节假日列表
	 */
	@Override
	public Map<String, Object> queryList(FestivalReq festivalReq) {
		Criteria c = super.getCriteria();
		if(StringUtil.isNotBlank(festivalReq.getFestivalName())){
			c.add(Restrictions.like("festivalName", festivalReq.getFestivalName()));
		}
		if(StringUtil.isNotBlank(festivalReq.getDayType())){
			c.add(Restrictions.like("dayType", festivalReq.getDayType()));
		}
		if(StringUtil.isNotBlank(festivalReq.getIsValid())){
			c.add(Restrictions.like("isValid", festivalReq.getIsValid()));
		}
		if(StringUtil.isNotBlank(festivalReq.getIsDel())){
			c.add(Restrictions.like("isDel", festivalReq.getIsDel()));
		}
		if(StringUtil.isNotBlank(festivalReq.getOrgId())){
			c.add(Restrictions.like("orgId", festivalReq.getOrgId()));
		}
		c.addOrder(Order.desc("festivalStartDate"));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<Festival> projectTypeList = this.findBySortCriteria(c, festivalReq);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, festivalReq.getDraw(),projectTypeList);
		
	}

	/**
	 * 查询节假日
	 */
	@Override
	public List<Festival> queryFestivals(FestivalReq festivalReq) {
		
		Criteria c = super.getCriteria();
		if(StringUtil.isNotBlank(festivalReq.getFestivalName())){
			c.add(Restrictions.like("festivalName", festivalReq.getFestivalName()));
		}
		if(StringUtil.isNotBlank(festivalReq.getDayType())){
			c.add(Restrictions.like("dayType", festivalReq.getDayType()));
		}
		if(StringUtil.isNotBlank(festivalReq.getIsValid())){
			c.add(Restrictions.like("isValid", festivalReq.getIsValid()));
		}
		if(StringUtil.isNotBlank(festivalReq.getIsDel())){
			c.add(Restrictions.like("isDel", festivalReq.getIsDel()));
		}
		if(StringUtil.isNotBlank(festivalReq.getOrgId())){
			c.add(Restrictions.like("orgId", festivalReq.getOrgId()));
		}
		c.addOrder(Order.desc("festivalStartDate"));
		return this.findByCriteria(c);
	}
}
