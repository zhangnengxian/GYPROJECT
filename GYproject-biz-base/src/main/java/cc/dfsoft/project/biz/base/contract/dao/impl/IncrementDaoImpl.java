package cc.dfsoft.project.biz.base.contract.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dto.IncrementReg;
import cc.dfsoft.project.biz.base.contract.dao.IncrementDao;
import cc.dfsoft.project.biz.base.contract.entity.Increment;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 
 * 描述:税率dao实现类
 * @author liaoyq
 * @createTime 2017年11月26日
 */
@Repository
public class IncrementDaoImpl extends NewBaseDAO<Increment, String> implements IncrementDao {

	/**
	 * 查询所有税率信息
	 */
	@Override
	public List<Increment> queryAll() {
		Criteria c = super.getCriteria();
		List<Increment> list=this.findByCriteria(c);
		return list;
	}

	@Override
	public Map<String, Object> queryIncrementInfo(IncrementReg incrementReg){
			Criteria c = super.getCriteria();
			if(StringUtil.isNotBlank(incrementReg.getIncrement())){
				c.add(Restrictions.eq("increment", incrementReg.getIncrement()));
			}
			if(StringUtil.isNotBlank(incrementReg.getIncrementCode())){
				c.add(Restrictions.eq("incrementCode", incrementReg.getIncrementCode()));
			}
			// 数据库根据条件过滤记录数
			int filterCount = this.countByCriteria(c);
			// 根据条件获取查询信息
			List<Increment> list = this.findBySortCriteria(c, incrementReg);
			return ResultUtil.pageResult(filterCount, incrementReg.getDraw(),list);
			
	}
	
	@Override
	public List<Increment> queryIncrement(IncrementReg incrementReg){
		Criteria c = super.getCriteria();
		if(StringUtil.isNotBlank(incrementReg.getIncrement())){
			c.add(Restrictions.eq("increment", incrementReg.getIncrement()));
		}
		if(StringUtil.isNotBlank(incrementReg.getIncrementCode())){
			c.add(Restrictions.eq("incrementCode", incrementReg.getIncrementCode()));
		}
		return this.findBySortCriteria(c, incrementReg);
	}

}
