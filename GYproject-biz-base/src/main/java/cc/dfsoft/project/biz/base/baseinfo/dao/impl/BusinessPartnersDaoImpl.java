package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class BusinessPartnersDaoImpl extends NewBaseDAO<BusinessPartners,String> implements BusinessPartnersDao{

	@Override
	public Map<String, Object> queryBusinessPartners(BusinessPartnersReq businessPartnersReq) throws ParseException {
		
		Criteria c = super.getCriteria();
		
		//按公司查询
		if(StringUtils.isNotBlank(businessPartnersReq.getCorpId())){
			 c.add(Restrictions.like("corpId","%"+businessPartnersReq.getCorpId()+"%"));
		}
		
		//根据主键Id进行查询
		if(StringUtils.isNotBlank(businessPartnersReq.getUnitId())){
			 c.add(Restrictions.eq("unitId",businessPartnersReq.getUnitId()));
		}
		//单位类型
		if(StringUtils.isNotBlank(businessPartnersReq.getUnitType())){
			c.add(Restrictions.eq("unitType",businessPartnersReq.getUnitType()));
		}
		//单位名称
		if(StringUtils.isNotBlank(businessPartnersReq.getUnitName())){
			 c.add(Restrictions.like("unitName","%"+businessPartnersReq.getUnitName()+"%"));
		}
		//资质
		if(StringUtils.isNotBlank(businessPartnersReq.getUnitQualification())){
			 c.add(Restrictions.eq("unitQualification",businessPartnersReq.getUnitQualification()));
		}
		//负责人
		if(StringUtils.isNotBlank(businessPartnersReq.getUnitDirector())){
			 c.add(Restrictions.eq("unitDirector",businessPartnersReq.getUnitDirector()));
		}
		//简称
		if(StringUtils.isNotBlank(businessPartnersReq.getShortName())){
			 c.add(Restrictions.like("shortName","%"+businessPartnersReq.getShortName()+"%"));
		}
		//单位查询开始日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(businessPartnersReq.getUnitFoundDateStart())){
			 c.add(Restrictions.ge("unitFoundDate", sdf.parseObject(businessPartnersReq.getUnitFoundDateStart())));
		}
		 //单位查询结束日期
		if(StringUtils.isNotBlank(businessPartnersReq.getUnitFoundDateEnd())){
			 c.add(Restrictions.le("unitFoundDate", sdf.parse(businessPartnersReq.getUnitFoundDateEnd())));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<BusinessPartners> list = this.findBySortCriteria(c, businessPartnersReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, businessPartnersReq.getDraw(),list);
	}
	
	/**
	 * 查找分包单位
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public List getCuUnit() {
		StringBuffer sql=new StringBuffer("select * from Business_Partners where unit_type='").append(UnitTypeEnum.CONSTRUCTION_UNIT.getValue()).append("'");
		List list= this.findBySql(sql.toString());
		return list;
	}
}
