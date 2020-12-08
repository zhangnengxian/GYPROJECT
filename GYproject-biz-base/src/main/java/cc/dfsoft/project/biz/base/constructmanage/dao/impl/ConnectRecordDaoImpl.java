package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.ConnectRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConnectRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class ConnectRecordDaoImpl extends NewBaseDAO<ConnectRecord, String> implements ConnectRecordDao {

	@Override
	public Map<String, Object> queryUnitContent(ConnectRecordQueryReq connectRecordReq) throws ParseException {
		Criteria c = super.getCriteria();
		//根据工程id查询
		if(StringUtils.isNotBlank(connectRecordReq.getProjId())){
			 c.add(Restrictions.eq("projId",connectRecordReq.getProjId()));
		}
		//单位类型
		if(StringUtils.isNotBlank(connectRecordReq.getUnitType())){
			 c.add(Restrictions.eq("unitType",connectRecordReq.getUnitType()));
		}
		//根据碰口内容Id进行查询
		if(StringUtils.isNotBlank(connectRecordReq.getConnectContentId())){
			 c.add(Restrictions.like("connectContentId",connectRecordReq.getConnectContentId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<ConnectRecord> list = this.findBySortCriteria(c, connectRecordReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, connectRecordReq.getDraw(),list);
	}

	@Override
	public List<ConnectRecord> queryContent(String projId) throws ParseException {
		StringBuffer hql = new StringBuffer("from ConnectRecord where projId='").append(projId).append("'");
		List<ConnectRecord> results = super.findByHql(hql.toString());
		return results;
}

	@Override
	public void deleteByCroId(String croId) {
		StringBuffer hql = new StringBuffer("delete from ConnectRecord where croId='").append(croId).append("'");
		super.executeHql(hql.toString());
	}

}
