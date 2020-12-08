package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import cc.dfsoft.project.biz.base.baseinfo.entity.ConstructionUnit;
import cc.dfsoft.project.biz.base.baseinfo.dao.ConstructionUnitDao;
import cc.dfsoft.project.biz.base.subpackage.dto.ConstructionUnitReq;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class ConstructionUnitDaoImpl extends NewBaseDAO<ConstructionUnit,String> implements ConstructionUnitDao{

	/**
	 * 分包单位列表查询
	 * @param constructionUnitReq
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> queryConstructionUnit(ConstructionUnitReq constructionUnitReq) throws ParseException {
		
		Criteria c = super.getCriteria();
		//根据主键Id进行查询
		if(StringUtils.isNotBlank(constructionUnitReq.getCuId())){
			 c.add(Restrictions.eq("cuId",constructionUnitReq.getCuId()));
		}
		//分包名称
		if(StringUtils.isNotBlank(constructionUnitReq.getCuName())){
			 c.add(Restrictions.like("cuName","%"+constructionUnitReq.getCuName()+"%"));
		}
		//资质
		if(StringUtils.isNotBlank(constructionUnitReq.getCuQualification())){
			 c.add(Restrictions.eq("cuQualification",constructionUnitReq.getCuQualification()));
		}
		//负责人
		if(StringUtils.isNotBlank(constructionUnitReq.getCuDirector())){
			 c.add(Restrictions.eq("cuDirector",constructionUnitReq.getCuDirector()));
		}
		//简称
		if(StringUtils.isNotBlank(constructionUnitReq.getShortName())){
			 c.add(Restrictions.eq("shortName",constructionUnitReq.getShortName()));
		}
		//分包单位查询开始日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(constructionUnitReq.getCuFoundDateStart())){
			 c.add(Restrictions.ge("cuFoundDate", sdf.parseObject(constructionUnitReq.getCuFoundDateStart())));
		}
		 //分包单位查询结束日期
		if(StringUtils.isNotBlank(constructionUnitReq.getCuFoundDateEnd())){
			 c.add(Restrictions.le("cuFoundDate", sdf.parse(constructionUnitReq.getCuFoundDateEnd())));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ConstructionUnit> list = this.findBySortCriteria(c, constructionUnitReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, constructionUnitReq.getDraw(),list);
	}

	/**
	 * 分包单位详述查询
	 * @param id
	 * @return ConstructionUnit
	 */
	@Override
	public ConstructionUnit viewConstructionUnitById(String id) {
		ConstructionUnit constructionUnit = this.get(id);
		return constructionUnit;
	}
}
