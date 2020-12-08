package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.DataAcceptanceRecordDao;
import cc.dfsoft.project.biz.base.complete.entity.DataAcceptance;
import cc.dfsoft.project.biz.base.complete.entity.DataAcceptanceRecord;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class DataAcceptanceRecordDaoImpl  extends NewBaseDAO<DataAcceptanceRecord, String> implements DataAcceptanceRecordDao{
	
	/**
	 * 查询资料验收记录
	 * @author fuliwei
	 * @createTime 2017年8月3日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryDataAcceptanceRecord(AccessoryQueryReq req) {
		
		Criteria c = super.getCriteria();
		
		//根据ID进行查询
		if(StringUtils.isNotBlank(req.getDataType())){
			c.add(Restrictions.eq("dataType", req.getDataType()));
		}
		
		//根据自检类型
		if(StringUtils.isNotBlank(req.getProjTypeId())){
			c.add(Restrictions.eq("projTypeId", req.getProjTypeId()));
		}
		
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId", req.getProjId()));
		}
		
		
		
		int filterCount = this.countByCriteria(c);
		
		// 根据条件获取查询信息
		List<DataAcceptanceRecord> list = this.findBySortCriteria(c, req);
		
		return ResultUtil.pageResult(filterCount, req.getDraw(), list);
		
	}
	
	/**
	 * 资料验收申请页面删除
	 * @author fuliwei
	 * @createTime 2017年8月8日
	 * @param 
	 * @return
	 */
	@Override
	public void deleteByDaId(String daId) {
		String hql="from DataAcceptanceRecord a where a.daId='"+daId+"'";
		List<DataAcceptanceRecord> list=super.findByHql(hql);
		if(list!=null&&list.size()>0){
			this.delete(list.get(0));
		}
		
	}

}
