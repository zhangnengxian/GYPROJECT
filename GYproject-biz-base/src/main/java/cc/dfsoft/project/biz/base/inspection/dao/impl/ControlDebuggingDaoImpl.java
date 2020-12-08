package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.ControlDebuggingDao;
import cc.dfsoft.project.biz.base.inspection.dto.ControlDebuggingReq;
import cc.dfsoft.project.biz.base.inspection.entity.ControlDebugging;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class ControlDebuggingDaoImpl extends NewBaseDAO<ControlDebugging, String> implements  ControlDebuggingDao {
	
	/**
	 * 控制系统调试记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryControlDebugging(ControlDebuggingReq req) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(req.getPcId())){
			c.add(Restrictions.eq("pcId", req.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ControlDebugging> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
	}
	
	/**
	 * 删除所有的控制系统调试记录
	 * @author fuliwei
	 * @createTime 2017-02-06
	 * @param pcId
	 * @return
	 */
	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from ControlDebugging where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}
	
	

}
