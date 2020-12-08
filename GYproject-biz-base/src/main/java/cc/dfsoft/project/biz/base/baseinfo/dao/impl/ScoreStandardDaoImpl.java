package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.ScoreStandardDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.ScoreStandardQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.ScoreStandard;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ScoreStandardDaoImpl extends NewBaseDAO<ScoreStandard,String> implements ScoreStandardDao{

	@Override
	public Map<String, Object> queryScoreStandard(ScoreStandardQueryReq scoreStandardQueryReq) {
        Criteria c = super.getCriteria();
		
		if (StringUtils.isNotBlank(scoreStandardQueryReq.getDeptId())) {
			c.add(Restrictions.eq("dept.deptId", scoreStandardQueryReq.getDeptId()));
		}
		//分数
		if (StringUtils.isNotBlank(scoreStandardQueryReq.getSsScore())) {
			c.add(Restrictions.eq("ssScore", scoreStandardQueryReq.getSsScore()));
		}
		//描述
		if(StringUtils.isNotBlank(scoreStandardQueryReq.getSsDes())){
			c.add(Restrictions.like("ssDes","%"+scoreStandardQueryReq.getSsDes()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<ScoreStandard> scoreStandardList = this.findBySortCriteria(c, scoreStandardQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, scoreStandardQueryReq.getDraw(),scoreStandardList);
	}

	@Override
	public ScoreStandard queryBySsDes(String ssDes) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("ssDes", ssDes));
		// 根据条件获取查询信息
		List<ScoreStandard> scoreStandard = this.findByCriteria(c);
		if(scoreStandard.size() > 0){
			return scoreStandard.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public Boolean queryScore(ScoreStandard scoreStandard) {
		StringBuffer sql=new StringBuffer();
		//List sql=new ArrayList();
		sql.append("select sum from (");
		sql.append("select sum(ss_score) sum from score_standard where dept_id='").append(scoreStandard.getDept().getDeptId()).append("'");
		sql.append(") where sum>100");
		List list= this.findBySql(sql.toString());
		
		if(list!=null && list.size()>0){
			//大于90
			return true;
		}else{
			//小于90 
			return false;
		}
	}

	@Override
	public ScoreStandard queryBySsId(String ssId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("ssId", ssId));
		// 根据条件获取查询信息
		List<ScoreStandard> scoreStandard = this.findByCriteria(c);
		if(scoreStandard.size() > 0){
			return scoreStandard.get(0);
		}else{
			return null;
		}
	}
	
}
