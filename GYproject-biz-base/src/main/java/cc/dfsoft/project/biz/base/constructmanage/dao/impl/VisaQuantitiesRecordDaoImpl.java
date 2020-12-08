package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.VisaQuantitiesRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.EngineeringVisaQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.VisaQuantitiesRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class VisaQuantitiesRecordDaoImpl extends NewBaseDAO<VisaQuantitiesRecord, String> implements VisaQuantitiesRecordDao{
	
	/**
	 * 签证标准记录列表查询
	 * @author fuliwei
	 * @createTime 2017年2月4日
	 * @param  EngineeringVisaQueryReq
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryVisaQuantitiesRecord(EngineeringVisaQueryReq req) {
		Criteria c = super.getCriteria();
		//根据碰口内容Id进行查询
		if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		}
		
		if(StringUtils.isNotBlank(req.getEvId())){
			 c.add(Restrictions.eq("evId",req.getEvId()));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<VisaQuantitiesRecord> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult( filterCount, req.getDraw(),list);
	}
	
	/**
	 * 根据id删除原来的签证工程量
	 * @author fuliwei
	 * @createTime 2017年2月9日
	 * @param evId
	 * @return
	 */
	@Override
	public void deleteById(String evId) {
		StringBuffer sql=new StringBuffer("delete from VisaQuantitiesRecord where evId='").append(evId).append("'");
		this.executeHql(sql.toString());
	}

}
