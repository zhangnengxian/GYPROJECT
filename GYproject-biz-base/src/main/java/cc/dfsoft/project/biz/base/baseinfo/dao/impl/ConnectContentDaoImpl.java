package cc.dfsoft.project.biz.base.baseinfo.dao.impl;


import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.ConnectContentDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConnectRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ConnectContentDaoImpl extends NewBaseDAO<ConnectContent,String> implements ConnectContentDao {

	@Override
	public Map<String, Object> queryConnectContent(ConnectContentReq connectContentReq) throws ParseException {
		Criteria c = super.getCriteria();
		//根据碰口内容Id进行查询
		if(StringUtils.isNotBlank(connectContentReq.getId())){
			 c.add(Restrictions.eq("cId",connectContentReq.getId()));
		}
		//单位类型
		if(StringUtils.isNotBlank(connectContentReq.getType())){
			 c.add(Restrictions.eq("type",connectContentReq.getType()));
		}
		//描述
		if(StringUtils.isNotBlank(connectContentReq.getDes())){
			 c.add(Restrictions.like("des","%"+connectContentReq.getDes()+"%"));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ConnectContent> list = this.findBySortCriteria(c, connectContentReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, connectContentReq.getDraw(),list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String queryNum(String type) {
		String sql = "select max(id) from ConnectContent where type = " + type;
		List<String> cc = this.findByHql(sql);
		if(cc.size() > 0){
			return cc.get(0).toString();
		}else{
			return Integer.parseInt(type)*10 + "";
		}
		
	}

	@Override
	public List<ConnectContent> queryConnectContent(String type) {
			StringBuffer hql = new StringBuffer("from ConnectContent where type='").append(type).append("'");
			List<ConnectContent> results = super.findByHql(hql.toString());
			return results;
	}

	@Override
	public Map<String, Object> queryUnitContent(ConnectRecordQueryReq connectRecordReq) throws ParseException {
		Criteria c = super.getCriteria();
		//根据碰口内容Id进行查询
		if(StringUtils.isNotBlank(connectRecordReq.getConnectContentId())){
			 c.add(Restrictions.eq("cId",connectRecordReq.getConnectContentId()));
		}
		//单位类型
		if(StringUtils.isNotBlank(connectRecordReq.getUnitType())){
			 c.add(Restrictions.eq("type",connectRecordReq.getUnitType()));
		}
//		//工程id
//		if(StringUtils.isNotBlank(connectRecordReq.getProjId())){
//			 c.add(Restrictions.like("projId",connectRecordReq.getProjId()));
//		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ConnectContent> list = this.findBySortCriteria(c, connectRecordReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, connectRecordReq.getDraw(),list);
	}


}
