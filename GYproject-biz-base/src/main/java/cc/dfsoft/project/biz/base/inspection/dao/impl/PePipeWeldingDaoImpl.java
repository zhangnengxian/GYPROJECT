package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.PePipeWeldingDao;
import cc.dfsoft.project.biz.base.inspection.dto.PePipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.entity.PepipeWelding;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class PePipeWeldingDaoImpl  extends NewBaseDAO<PepipeWelding, String>implements PePipeWeldingDao {
	
	
	@Override
	public Map<String, Object> queryPePipeWelding(PePipeWeldingReq pePipeWeldingReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(pePipeWeldingReq.getPcId())){
			c.add(Restrictions.eq("pcId",pePipeWeldingReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PepipeWelding> list = this.findBySortCriteria(c, pePipeWeldingReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pePipeWeldingReq.getDraw(),list);
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from PepipeWelding where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(PePipeWeldingReq pepipeWeldingReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(pepipeWeldingReq.getProjId())){
			c.add(Restrictions.eq("projId",pepipeWeldingReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(pepipeWeldingReq.getPcId())){
			String sqlFilter ="";
			if(pepipeWeldingReq.getFlag()!=null && pepipeWeldingReq.getFlag()==1){
				sqlFilter += " PC_ID='"+pepipeWeldingReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!pepipeWeldingReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+pepipeWeldingReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PepipeWelding> list = this.findBySortCriteria(c, pepipeWeldingReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pepipeWeldingReq.getDraw(),list);
	
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  PepipeWelding set pcId='").append(pcId).append("' where peId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  PepipeWelding set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}
}
