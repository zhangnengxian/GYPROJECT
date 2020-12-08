package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.inspection.dao.PipeWeldingDao;
import cc.dfsoft.project.biz.base.inspection.dto.PipeWeldingReq;
import cc.dfsoft.project.biz.base.inspection.entity.PipeLineInstall;
import cc.dfsoft.project.biz.base.inspection.entity.PipeWelding;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class PipeWeldingDaoImpl extends NewBaseDAO<PipeWelding, String>  implements PipeWeldingDao{

	@Override
	public Map<String, Object> queryPipeWelding(PipeWeldingReq pipeWeldingReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(pipeWeldingReq.getPcId())){
			c.add(Restrictions.eq("pcId",pipeWeldingReq.getPcId()));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<PipeWelding> list = this.findBySortCriteria(c, pipeWeldingReq);
		 
		// 返回结果
		return ResultUtil.pageResult(filterCount, pipeWeldingReq.getDraw(),list);
	}
	
	/**
	 * 删除所有的钢管焊接记录
	 * @author fuliwei
	 * @createTime 2016-08-29
	 * @param pcId
	 * @return
	 */
	@Override
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from PipeWelding where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	@Override
	public Map<String, Object> queryRecords(PipeWeldingReq pipeWeldingReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(pipeWeldingReq.getProjId())){
			c.add(Restrictions.eq("projId",pipeWeldingReq.getProjId()));
		}
		//报验id
		if(StringUtils.isNotBlank(pipeWeldingReq.getPcId())){
			String sqlFilter ="";
			if(pipeWeldingReq.getFlag()!=null && pipeWeldingReq.getFlag()==1){
				sqlFilter += " PC_ID='"+pipeWeldingReq.getPcId()+"'";//已完成的报验，只查询报验单下的记录
			}else{
				sqlFilter = "(PC_ID='' or PC_ID is null";
				if(!pipeWeldingReq.getPcId().equals("-1")){//
					sqlFilter += " or PC_ID='"+pipeWeldingReq.getPcId()+"'";
				}
				sqlFilter +=")";
			}
			c.add(Restrictions.sqlRestriction(sqlFilter));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PipeWelding> list = this.findBySortCriteria(c, pipeWeldingReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pipeWeldingReq.getDraw(),list);
	
	}

	@Override
	public void updatePcIdByRecordId(String id, String pcId) {
		StringBuffer hql = new StringBuffer("update  PipeWelding set pcId='").append(pcId).append("' where pipeId='").append(id).append("'");
		this.executeHql(hql.toString());
	}

	@Override
	public void updateByPcId(String pcId) {
		StringBuffer hql  =  new StringBuffer("update  PipeWelding set pcId=null where pcId='").append(pcId).append("'");
		this.executeHql(hql.toString());
	}
}
