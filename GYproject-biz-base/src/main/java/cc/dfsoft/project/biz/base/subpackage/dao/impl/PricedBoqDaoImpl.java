package cc.dfsoft.project.biz.base.subpackage.dao.impl;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import antlr.Version;

import java.util.Date;

import cc.dfsoft.project.biz.base.subpackage.dao.PricedBoqDao;
import cc.dfsoft.project.biz.base.subpackage.dto.PricedBoqReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PricedBoq;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class PricedBoqDaoImpl extends NewBaseDAO<PricedBoq,String> implements PricedBoqDao{

	@Override
	public Map<String, Object> queryPricedBoq(PricedBoqReq pricedBoqReq) throws ParseException {
		Criteria c = super.getCriteria();
		StringBuffer sql = new StringBuffer();
		
		//
		if (StringUtils.isNotBlank(pricedBoqReq.getCorpId())) {
			c.add(Restrictions.like("corpId", "%"+pricedBoqReq.getCorpId()+"%"));
		}
		
		//根据Id进行查询
		if(StringUtils.isNotBlank(pricedBoqReq.getQsId())){
			 c.add(Restrictions.eq("qsId",pricedBoqReq.getQsId()));	
		}
		//根据版本号进行查询
		if(StringUtils.isNotBlank(pricedBoqReq.getVeId())){
			 c.add(Restrictions.eq("veId",pricedBoqReq.getVeId()));	
		}else{
//			 c.add(Restrictions.eq("veId","max(veId)"));
//			 c.setProjection(Projections.max("veId"));
				sql.append("ve_Id = (SELECT MAX(VE_Id) FROM Priced_Boq)");
				c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		//根据造价类型进行查询
		if(StringUtils.isNotBlank(pricedBoqReq.getCostType())){
			 c.add(Restrictions.eq("costType",pricedBoqReq.getCostType()));
		}
		//分部分项名称
		if(StringUtils.isNotBlank(pricedBoqReq.getSubitemName())){
			 c.add(Restrictions.like("subitemName","%"+pricedBoqReq.getSubitemName()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<PricedBoq> list = this.findBySortCriteria(c, pricedBoqReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, pricedBoqReq.getDraw(),list);
	}


	@Override
	public PricedBoq viewPricedBoq(String id) {
		return this.get(id);
	}


	@Override
	public List<PricedBoq> findByVeid(String corpId,String versionOfProj,String incIncraMode) {
		Criteria c = super.getCriteria();
		//根据Id进行查询
		if(StringUtils.isNotBlank(corpId)){
			 c.add(Restrictions.eq("corpId",corpId));	
		}
		//根据版本查询
	    if(StringUtils.isNotBlank(versionOfProj)){
	      c.add(Restrictions.eq("version",versionOfProj));  
	    }
	    //根据增收方式
	    if(StringUtils.isNotBlank(incIncraMode)){
		      c.add(Restrictions.eq("incIncraMode",incIncraMode));  
		    }
	    
		return this.findByCriteria(c);
	}

}
