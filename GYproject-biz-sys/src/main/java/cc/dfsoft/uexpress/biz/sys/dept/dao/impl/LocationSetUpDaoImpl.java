package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.uexpress.biz.sys.dept.dao.LocationSetUpDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LocationSetUpQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.LocationSetUp;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class LocationSetUpDaoImpl extends NewBaseDAO<LocationSetUp, String> implements LocationSetUpDao{
	
	/**
	 * 查询定位设置列表
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> quertLocationSetUpList(LocationSetUpQueryReq req) {
		Criteria c = super.getCriteria();
		
		if (StringUtils.isNotBlank(req.getDeptId())) {
			c.add(Restrictions.eq("deptType", req.getDeptId()));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<LocationSetUp> scoreStandardList = this.findBySortCriteria(c, req);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, req.getDraw(),scoreStandardList);
	}
	
	/**
	 * 通过deptType查询
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	@Override
	public LocationSetUp queryByDeptType(String deptType) {
		Criteria c = super.getCriteria();
		
		if (StringUtils.isNotBlank(deptType)) {
			c.add(Restrictions.eq("deptType", deptType));
		}
		List<LocationSetUp> list=this.findByCriteria(c);
		
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
