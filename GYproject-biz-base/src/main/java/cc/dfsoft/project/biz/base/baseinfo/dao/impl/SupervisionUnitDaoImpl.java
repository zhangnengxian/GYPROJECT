package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.SupervisionUnitDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.SupervisionQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SupervisionUnit;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class SupervisionUnitDaoImpl extends NewBaseDAO<SupervisionUnit,String> implements SupervisionUnitDao{

	/**
	 * 监理单位列表查询
	 * @author cui
	 * @param supervisionQueryReq
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> querySupervisionUnit(SupervisionQueryReq supervisionUnitReq) {
		
		Criteria c = super.getCriteria();
		
		
		//根据监理单位名称进行查询
		if(StringUtils.isNotBlank(supervisionUnitReq.getSuName())){
			 c.add(Restrictions.like("suName","%"+supervisionUnitReq.getSuName()+"%"));
		}		
		//简称
		if(StringUtils.isNotBlank(supervisionUnitReq.getShortName())){
			 c.add(Restrictions.eq("shortName",supervisionUnitReq.getShortName()));
		}
		//联系人
		if(StringUtils.isNotBlank(supervisionUnitReq.getSuDirector())){
			 c.add(Restrictions.eq("suDirector",supervisionUnitReq.getSuDirector()));
		}
		//手机
		if(StringUtils.isNotBlank(supervisionUnitReq.getSuMobile())){
			 c.add(Restrictions.eq("suMobile",supervisionUnitReq.getSuMobile()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<SupervisionUnit> list = this.findBySortCriteria(c, supervisionUnitReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, supervisionUnitReq.getDraw(),list);
	}
}
