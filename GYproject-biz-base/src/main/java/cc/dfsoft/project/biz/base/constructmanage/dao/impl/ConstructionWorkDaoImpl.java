package cc.dfsoft.project.biz.base.constructmanage.dao.impl;


import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionWorkDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class ConstructionWorkDaoImpl extends NewBaseDAO<ConstructionWork, String> implements ConstructionWorkDao {
	
	/**
	 * 根据工程id 和资料类型查询工程交底
	 * @author 
	 * @createTime 2017-1-27
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ConstructionWork> findByProjId(String projId,String dataType) {
		StringBuffer hql = new StringBuffer();
		//hql.append("from ConstructionWork where projId = '").append(projId).append("'").append(" and dataType='").append(dataType).append("'");
		hql.append("from ConstructionWork where projId = '").append(projId).append("'");
		hql.append(" and delFlag=0");
		return super.findByHql(hql.toString());
	}

	/**
	 * 根据条件分页查询会审交底记录
	 * @author liaoyq
	 * @createTime 2017-7-28
	 */
	@Override
	public Map<String, Object> queryList(ConstructionWorkReq constructionWorkReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(constructionWorkReq.getProjId())){
			c.add(Restrictions.eq("projId",constructionWorkReq.getProjId()));
		}
		//图号id
		if(StringUtils.isNotBlank(constructionWorkReq.getDrawingNo())){
			c.add(Restrictions.eq("drawingNo",constructionWorkReq.getDrawingNo()));
		}
		
		//完成状态
		if(StringUtils.isNotBlank(constructionWorkReq.getSignState())){
			c.add(Restrictions.eq("signState",constructionWorkReq.getSignState()));
		}

		c.add(Restrictions.eq("delFlag",0));

		c.addOrder(Order.desc("cwDate"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ConstructionWork> list = this.findBySortCriteria(c, constructionWorkReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, constructionWorkReq.getDraw(),list);
	}

	/**
	 * 获取第一次交底信息
	 */
	@Override
	public ConstructionWork queryFirstCw(ConstructionWorkReq constructionWorkReq) {
		Criteria c = super.getCriteria();	
		//工程id
		if(StringUtils.isNotBlank(constructionWorkReq.getProjId())){
			c.add(Restrictions.eq("projId",constructionWorkReq.getProjId()));
		}
		//图号id
		if(StringUtils.isNotBlank(constructionWorkReq.getDrawingNo())){
			c.add(Restrictions.eq("drawingNo",constructionWorkReq.getDrawingNo()));
		}
		
		//完成状态
		if(StringUtils.isNotBlank(constructionWorkReq.getSignState())){
			c.add(Restrictions.eq("signState",constructionWorkReq.getSignState()));
		}
		c.add(Restrictions.eq("delFlag",0));
		// 根据条件获取查询信息
		List<ConstructionWork> list = this.findBySortCriteria(c, constructionWorkReq);
		// 返回结果
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Integer countSignedByProjId(String projId, String signStatus) {
		String sql = "select count(CW_ID) from construction_work  where PROJ_ID=? and SIGN_STATE=?";
		List<Object> params = new ArrayList<>();
		params.add(projId);
		params.add(signStatus);
		return this.getCountBySql(sql, params.toArray());
	}

	@Override
	public List<ConstructionWork> findByProjId(String projId) {

		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("projId",projId));
		List<ConstructionWork> cwList = this.findByCriteria(c);
		return cwList;
	}
}
