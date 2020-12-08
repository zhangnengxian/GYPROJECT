package cc.dfsoft.project.biz.base.project.dao.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.ScaleDetailDao;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ScaleDetailDaoImpl extends NewBaseDAO<ScaleDetail, String> implements ScaleDetailDao {

	@Override
	public Map<String, Object> queryScaleDetail(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException {
		 Criteria c = super.getCriteria();
		 //工程大类
		 if(StringUtils.isNotBlank(scaleDetailQueryReq.getProjLtypeId())){
			 c.add(Restrictions.like("projLtypeId","%"+scaleDetailQueryReq.getProjLtypeId()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(scaleDetailQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",scaleDetailQueryReq.getProjId()));
		 }
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<ScaleDetail> scaleDetailList = this.findBySortCriteria(c, scaleDetailQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, scaleDetailQueryReq.getDraw(),scaleDetailList);
		}

	@Override
	public void deleteByProjId(String projId) {
		StringBuffer hql = new StringBuffer();
		hql.append("delete from ScaleDetail where projId = ?");
		super.executeHql(hql.toString(), new Object[]{projId});
	}

	@Override
	public List<ScaleDetail> findByprojId(String projId) {
		 Criteria c = super.getCriteria();
		 c.add(Restrictions.eq("projId",projId));
		// 根据条件获取查询信息
		List<ScaleDetail> scaleDetailList = this.findByCriteria(c);
		// 返回结果
		return scaleDetailList;
	}
	/**
	 * 查询工程项目对应的工程规模数量
	 * @param proID 工程项目ID
	 * @return
	 */
	public Map<String,Object>queryNumOfProId(String proID)
	{
		StringBuffer hql = new StringBuffer();
		hql.append("select sum(s.houseNum),sum(s.searNum),sum(s.num),sum(s.tonnage),sum(s.gasConsumption)  from ScaleDetail s where s.projId = ?");
		List list = super.findByHql(hql.toString(),new Object[]{proID});
		
		if(list == null || list.size() == 0)
		{
			return null;
		}
		
		Object[]obj = (Object[])list.get(0);
		
		Map retMap = new HashMap();
		
		//户数
		retMap.put("houseNum", "0");
		
		//座数
		retMap.put("searNum", "0");
		
		//台数
		retMap.put("num", "0");
		
		//吨位
		retMap.put("tonnage", "0");
		
		//预计用气量（Nm3/h）
		retMap.put("gasConsumption", "0");
		
		return retMap;
	}
}
