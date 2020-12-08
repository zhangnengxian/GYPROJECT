package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.PurgeDao;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PurgeReq;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class PurgeDaoImpl extends NewBaseDAO<Purge, String> implements PurgeDao{
	
	@Resource
	AccessoryDao accessoryDao;
	
	/**
	 * 吹扫记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryPurge(PurgeQueryReq purgeQueryReq) {
		Criteria c = super.getCriteria();

		if(StringUtils.isNotBlank(purgeQueryReq.getPcId())){
			c.add(Restrictions.eq("pcId", purgeQueryReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Purge> list = this.findBySortCriteria(c, purgeQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, purgeQueryReq.getDraw(),list);
	}
	
	/**
	 * 删除吹扫记录
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from Purge where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  Purge set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  Purge set pcId='").append(pcId).append("' where purgeId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from Purge where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNull() {
		StringBuffer hql = new StringBuffer("delete from Purge where pcId='' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(PurgeReq purgeReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(purgeReq.getProjId())){
			c.add(Restrictions.eq("projId",purgeReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(purgeReq.getPcId())){
			String sqlFilter ="";
			if(purgeReq.getFlag()!=null && purgeReq.getFlag()==1){
				sqlFilter += " PC_ID='"+purgeReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!purgeReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+purgeReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<Purge> list = this.findBySortCriteria(c, purgeReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, purgeReq.getDraw(),list);
	}

	@Override
	public void clearPurgeId(String projId, String projNo, String busRecordId) {
		String hql="update AccessoryList set busRecordId=null where projId='"+projId+"'"+" and projNo !='"+projNo+"' and busRecordId='"+busRecordId+"'";
		accessoryDao.executeHql(hql);
	}

	@Override
	public void updateBIdByProjIdAndNo(String projId, String projNo, String busRecordId) {
		String hql = "update AccessoryList set busRecordId='"+busRecordId+"' where projId='"+projId+"' and projNo='"+projNo+"'";
		accessoryDao.executeHql(hql);
	}

}
