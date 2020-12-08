package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import cc.dfsoft.project.biz.base.complete.dao.CheckItemDao;
import cc.dfsoft.project.biz.base.complete.dto.CheckItemReq;
import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class CheckItemDaoImpl extends NewBaseDAO<CheckItem, String> implements CheckItemDao{

	private static final PageSortReq checkItem = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckItem> findByType(String type, String checkType,String corpId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(type)){
			 c.add(Restrictions.eq("type",type));
		 }
		if(StringUtils.isNotBlank(checkType)){
			 c.add(Restrictions.eq("checkType",checkType));
		 }
		if(StringUtils.isNotBlank(corpId)){
			 c.add(Restrictions.like("corpId","%"+corpId+"%"));
		 }
		 List<CheckItem> checkItems = c.list();
		return checkItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CheckItem> findByType(String type) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(type)){
			 c.add(Restrictions.eq("type",type));
		 }
		 List<CheckItem> checkItems = c.list();
		return checkItems;
	}

	@Override
	public Map<String, Object> queryCheckItems(CheckItemReq checkItem) {
		Criteria c = super.getCriteria();
		
		//查询
		if (StringUtils.isNotBlank(checkItem.getCorpId())) {
			c.add(Restrictions.like("corpId", "%"+checkItem.getCorpId()+"%"));
		}
		
		//根据ID进行查询
		if(StringUtils.isNotBlank(checkItem.getId())){
			c.add(Restrictions.eqOrIsNull("id", checkItem.getId()));
		}
		//根据自检类型
		if(StringUtils.isNotBlank(checkItem.getType())){
			c.add(Restrictions.eqOrIsNull("type", checkItem.getType()));
		}
		//根据检查类型
		if(StringUtils.isNotBlank(checkItem.getCheckType())){
			c.add(Restrictions.eqOrIsNull("checkType", checkItem.getCheckType()));
		}
		//根据描述
		if(StringUtils.isNotBlank(checkItem.getDes())){
			c.add(Restrictions.eqOrIsNull("des", checkItem.getDes()));
			c.add(Restrictions.like("des", "%"+checkItem.getDes()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<CheckItem> list = this.findBySortCriteria(c, checkItem);
		return ResultUtil.pageResult(filterCount, checkItem.getDraw(), list);
	}
}
