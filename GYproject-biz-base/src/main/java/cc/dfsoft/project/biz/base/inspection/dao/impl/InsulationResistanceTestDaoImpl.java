package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.InsulationResistanceTestDao;
import cc.dfsoft.project.biz.base.inspection.dto.InsulationResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.InsulationResistanceTest;
import cc.dfsoft.project.biz.base.inspection.entity.Pressure;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 电器绝缘电阻
 * @author fuliwei
 *
 */
@Repository
public class InsulationResistanceTestDaoImpl extends NewBaseDAO<InsulationResistanceTest, String> implements InsulationResistanceTestDao{
	
	/**
	 * 电器绝缘电阻记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryInsulationResistanceTest(InsulationResistanceTestReq req) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(req.getPcId())){
			c.add(Restrictions.eq("pcId", req.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<InsulationResistanceTest> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
	}
	
	/**
	 * 删除所有的电器绝缘电阻记录
	 * @author fuliwei
	 * @createTime 2017-02-06
	 * @param pcId
	 * @return void
	 */
	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from InsulationResistanceTest where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

}
