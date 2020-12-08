package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.BallRecordDao;
import cc.dfsoft.project.biz.base.inspection.dto.BallRecordReq;
import cc.dfsoft.project.biz.base.inspection.entity.BallRecord;
import cc.dfsoft.project.biz.base.inspection.entity.BallRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 通球记录dao实现层
 * @author liaoyq
 *
 */
@Repository
public class BallRecordDaoImpl extends NewBaseDAO<BallRecord, String> implements
		BallRecordDao {

	@Override
	public Map<String, Object> queryBallRecord(BallRecordReq ballRecordReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(ballRecordReq.getPcId())){
			c.add(Restrictions.eq("pcId",ballRecordReq.getPcId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<BallRecord> list = this.findBySortCriteria(c, ballRecordReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, ballRecordReq.getDraw(),list);
	}

	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from BallRecord where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  BallRecord set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  BallRecord set pcId='").append(pcId).append("' where brId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(BallRecordReq req) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(req.getProjId())){
			c.add(Restrictions.eq("projId",req.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(req.getPcId())){
			String sqlFilter ="";
			if(req.getFlag()!=null && req.getFlag()==1){
				sqlFilter += " PC_ID='"+req.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!req.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+req.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<BallRecord> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
	}

	@Override
	public void deletePcIdIsNull(String pcId) {
		StringBuffer hql = new StringBuffer("delete from BallRecord where pcId='").append(pcId).append("' or pcId is null");
		this.executeHql(hql.toString());
	}

	@Override
	public void deletePcIdIsNull() {
		StringBuffer hql = new StringBuffer("delete from BallRecord where pcId='' or pcId is null");
		this.executeHql(hql.toString());
	}

}
