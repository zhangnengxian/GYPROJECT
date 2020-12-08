package cc.dfsoft.project.biz.base.inspection.dao.impl;

import cc.dfsoft.project.biz.base.inspection.dao.GrooveRecordDao;
import cc.dfsoft.project.biz.base.inspection.dao.PressureRecordsDao;
import cc.dfsoft.project.biz.base.inspection.dto.GrooveRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.PressureRecords;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class PressureRecordsDaoImpl extends NewBaseDAO<PressureRecords, String> implements PressureRecordsDao {

	@Override
	public Map<String, Object> queryPressureRecords(GrooveRecordReq dtoReq) {
		Criteria c = super.getCriteria();			
		 //工程id
		 if(StringUtils.isNotBlank(dtoReq.getPcId())){
			 c.add(Restrictions.eq("pcId",dtoReq.getPcId()));
		 }
		
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<PressureRecords> list = this.findBySortCriteria(c, dtoReq);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, dtoReq.getDraw(),list);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from PressureRecords where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(GrooveRecordReq grReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(grReq.getProjId())){
			c.add(Restrictions.eq("projId",grReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(grReq.getPcId())){
			String sqlFilter ="";
			if(grReq.getFlag()!=null && grReq.getFlag()==1){
				sqlFilter += " PC_ID='"+grReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!grReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+grReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PressureRecords> list = this.findBySortCriteria(c, grReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, grReq.getDraw(),list);
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  PressureRecords set pcId='").append(pcId).append("' where id='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  PressureRecords set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	
}
