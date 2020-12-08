package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.inspection.dao.HiddenWorksDao;
import cc.dfsoft.project.biz.base.inspection.dto.HiddenWorksReq;
import cc.dfsoft.project.biz.base.inspection.dto.TrenchBackfillReq;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.project.biz.base.inspection.entity.TrenchBackfill;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class HiddenWorksDaoImpl extends NewBaseDAO<HiddenWorks, String> implements HiddenWorksDao{
	
	/**
	 * 按报验单ID查询
	 * @author fuliwei
	 * @createTime 2016-7-29
	 * @param pcId
	 * @return HiddenWorks
	 */
	@Override
	public HiddenWorks viewHiddenWorks(String id) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(id)){
			c.add(Restrictions.eq("pcId", id));
			List<HiddenWorks> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from HiddenWorks where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNull() {
		StringBuffer hql = new StringBuffer("delete from HiddenWorks where pcId='' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  HiddenWorks set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  HiddenWorks set pcId='").append(pcId).append("' where hwId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from HiddenWorks where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryHiddenWorks(HiddenWorksReq hiddenWorksReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(hiddenWorksReq.getProjId())){
			c.add(Restrictions.eq("projId",hiddenWorksReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(hiddenWorksReq.getPcId())){
			String sqlFilter = "(PC_ID='' or PC_ID is null";
			if(!hiddenWorksReq.getPcId().equals("-1")){
				sqlFilter += " or PC_ID='"+hiddenWorksReq.getPcId()+"'";
			}
			sqlFilter +=")";
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<HiddenWorks> list = this.findBySortCriteria(c, hiddenWorksReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, hiddenWorksReq.getDraw(),list);
	}

	@Override
	public Map<String, Object> queryTrenchBackfill(HiddenWorksReq hiddenWorksReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(hiddenWorksReq.getProjId())){
			c.add(Restrictions.eq("projId",hiddenWorksReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(hiddenWorksReq.getPcId())){
			if(hiddenWorksReq.getPcId().equals("-1")){
				//未报验的记录
				String sqlFilter = " PC_ID='' or PC_ID is null";
				c.add(Restrictions.sqlRestriction(sqlFilter));
			}else{
				String sqlFilter = " PC_ID='"+hiddenWorksReq.getPcId()+"' or PC_ID is null";
				//获取已报验和未报验的记录
				c.add(Restrictions.sqlRestriction(sqlFilter));
			}
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<HiddenWorks> list = this.findBySortCriteria(c, hiddenWorksReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, hiddenWorksReq.getDraw(),list);
	}

	@Override
	public Map<String, Object> queryRecords(HiddenWorksReq hiddenWorksReqt) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(hiddenWorksReqt.getProjId())){
			c.add(Restrictions.eq("projId",hiddenWorksReqt.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(hiddenWorksReqt.getPcId())){
			String sqlFilter ="";
			if(hiddenWorksReqt.getFlag()!=null && hiddenWorksReqt.getFlag()==1){
				sqlFilter += " PC_ID='"+hiddenWorksReqt.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!hiddenWorksReqt.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+hiddenWorksReqt.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<HiddenWorks> list = this.findBySortCriteria(c, hiddenWorksReqt);
		// 返回结果
		return ResultUtil.pageResult(filterCount, hiddenWorksReqt.getDraw(),list);
	}

}
