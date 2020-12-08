package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.VisaQuantitiesDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.VisaQuantities;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

/**
* 签证工程量标准
* @author fuliwei
*
*/
@Repository
public class VisaQuantitiesDaoImpl extends NewBaseDAO<VisaQuantities,String> implements VisaQuantitiesDao{
	
	/**
	 * 查询签证工程量标准列表
	 * @author fuliwei
	 * @createTime 2017-2-4
	 * @param ConnectContentReq
	 * @return Map<String,Object> 
	 */
	@Override
	public Map<String, Object> queryVisaQuanlities(ConnectContentReq connectContentReq) {
		Criteria c = super.getCriteria();
		//根据碰口内容Id进行查询
		if(StringUtils.isNotBlank(connectContentReq.getId())){
			 c.add(Restrictions.eq("cId",connectContentReq.getId()));
		}
		//描述
		if(StringUtils.isNotBlank(connectContentReq.getDes())){
			 c.add(Restrictions.like("des","%"+connectContentReq.getDes()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<VisaQuantities> list = this.findBySortCriteria(c, connectContentReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, connectContentReq.getDraw(),list);
	}

}
