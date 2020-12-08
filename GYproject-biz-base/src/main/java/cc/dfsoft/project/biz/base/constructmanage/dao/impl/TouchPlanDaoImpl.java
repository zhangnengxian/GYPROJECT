package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.TouchPlanDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.TouchPlanQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.project.biz.base.constructmanage.entity.TouchPlan;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class TouchPlanDaoImpl extends NewBaseDAO<TouchPlan, String> implements TouchPlanDao {

	@Override
	public List<TouchPlan> findbyProjId(String projId) {
		StringBuffer hql = new StringBuffer("from TouchPlan where projId='").append(projId).append("'");
		List<TouchPlan> result = super.findByHql(hql.toString());
		return result;
	}

	@Override
	public List<DigRoad> findbyTpId(String tpId) {
		StringBuffer hql = new StringBuffer("from DigRoad where tpId='").append(tpId).append("'");
		List<DigRoad> result = super.findByHql(hql.toString());
		return result;
	}

	@Override
	public Map<String, Object> queryTouchPlanAudit(TouchPlanQueryReq touchPlanQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		//根据碰口内容Id进行查询
		if(StringUtils.isNotBlank(touchPlanQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",touchPlanQueryReq.getProjId()));
		}
		
		if(StringUtils.isNotBlank(touchPlanQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",touchPlanQueryReq.getProjNo()));
		}
		
		
		if(StringUtils.isNotBlank(touchPlanQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+touchPlanQueryReq.getProjName()+"%"));
		}
		if(StringUtils.isNotBlank(touchPlanQueryReq.getConstructionUnit())){
			 c.add(Restrictions.eq("constructionUnit",touchPlanQueryReq.getConstructionUnit()));
		}
		
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//申请碰口日期开始日期
	    if(StringUtils.isNotBlank(touchPlanQueryReq.getApplyTpDateStart())){
	    	c.add(Restrictions.ge("applyTpDate", sdf.parse(touchPlanQueryReq.getApplyTpDateStart())));
	    }
	    
	    //申请碰口日期结束日期
	    if(StringUtils.isNotBlank(touchPlanQueryReq.getApplyTpDateEnd())){
	    	c.add(Restrictions.le("applyTpDate", sdf.parse(touchPlanQueryReq.getApplyTpDateEnd())));
	    }
		
	    //确认碰口日期开始日期
	    if(StringUtils.isNotBlank(touchPlanQueryReq.getTpDateStart())){
	    	c.add(Restrictions.ge("tpDate", sdf.parse(touchPlanQueryReq.getTpDateStart())));
	    }
	    
	    //确认碰口日期结束日期
	    if(StringUtils.isNotBlank(touchPlanQueryReq.getTpDateEnd())){
	    	c.add(Restrictions.le("tpDate", sdf.parse(touchPlanQueryReq.getTpDateEnd())));
	    }
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<TouchPlan> list = this.findBySortCriteria(c, touchPlanQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, touchPlanQueryReq.getDraw(),list);
	}

	
}
