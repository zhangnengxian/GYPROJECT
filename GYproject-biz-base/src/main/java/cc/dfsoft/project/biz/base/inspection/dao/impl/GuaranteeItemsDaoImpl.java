package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.GuaranteeItemsDao;
import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.GuaranteeItems;
import cc.dfsoft.project.biz.base.inspection.entity.GuaranteeItemsList;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class GuaranteeItemsDaoImpl extends NewBaseDAO<GuaranteeItems, String> implements GuaranteeItemsDao{
	
	/**
	 * 保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryGuaranteeItems(ThreadingPipeReq req) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(req.getItemType())){
			c.add(Restrictions.eq("tbType", req.getItemType()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<GuaranteeItems> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
	}
	
}
