package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.BasicProjectCheckItemDao;
import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.BasicProjectCheckItem;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class BasicProjectCheckItemDaoImpl extends NewBaseDAO<BasicProjectCheckItem, String> implements BasicProjectCheckItemDao{
	
	/**
	 * 基本项目和允许偏差项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryBasicProjectCheckItem(ThreadingPipeReq req) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(req.getPcId())){
			c.add(Restrictions.eq("pcId", req.getPcId()));
		}
		if(StringUtils.isNotBlank(req.getItemType())){
			c.add(Restrictions.eq("itemType", req.getItemType()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<BasicProjectCheckItem> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
	}
	
	/** 根据pcId删除所有的记录
	 * @author fuliwei
	 * @createTime 2017年2月8日
	 * @param pcId
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteById(String pcId, String type) {
		StringBuffer hql = new StringBuffer("delete from BasicProjectCheckItem where pcId='").append(pcId).append("' and itemType='").append(type).append("'");
		super.executeHql(hql.toString());
	}

}
