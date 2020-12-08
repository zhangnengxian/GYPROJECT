package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.PressureDao;
import cc.dfsoft.project.biz.base.inspection.dto.PressureQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.Pressure;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class PressureDaoImpl extends NewBaseDAO<Pressure, String> implements PressureDao{
	
	/**
	 * 试压记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param pressureQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryPressure(PressureQueryReq pressureQueryReq) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(pressureQueryReq.getPcId())){
			c.add(Restrictions.eq("pcId", pressureQueryReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Pressure> list = this.findBySortCriteria(c, pressureQueryReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pressureQueryReq.getDraw(),list);
	}
	
	/**
	 * 删除试压记录
	 * @author
	 * @createTime 2016-09-08
	 * @param
	 * @return
	 */
	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql=new StringBuffer("delete from Pressure where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

}
