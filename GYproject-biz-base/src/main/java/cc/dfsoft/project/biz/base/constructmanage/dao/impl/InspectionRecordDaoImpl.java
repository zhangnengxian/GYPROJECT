package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.InspectionRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionRecordQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
@Repository
public class InspectionRecordDaoImpl extends NewBaseDAO<InspectionRecord, String> implements InspectionRecordDao {

	@Override
	public Map<String, Object> queryInspectionRecord(InspectionRecordQueryReq inspectionRecordQueryReq)
			throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(inspectionRecordQueryReq.getIlId())){
			c.add(Restrictions.eq("ilId",inspectionRecordQueryReq.getIlId()));
		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<InspectionRecord> list = this.findBySortCriteria(c, inspectionRecordQueryReq);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, inspectionRecordQueryReq.getDraw(),list);
	}

	@Override
	public void deleByIlId(String ilId) {
		StringBuffer hql = new StringBuffer();
		hql.append("delete from InspectionRecord where ilId = ?");
		super.executeHql(hql.toString(), new Object[]{ilId});
		
	}

	@Override
	public List<Map<String, Object>> queryInspectionRecordTop() {
		Criteria c = super.getCriteria();
		/*StringBuffer hql = new StringBuffer();
		hql.append("select * from (select unqualityPointId,count(unqualityPointId) as num from InspectionRecord group by unqualityPointId order by num desc)");
		hql.append(" where rownum <= 10");
		List<Map<String, Object>> ls = super.findByHql(hql.toString());*/
		StringBuffer sql = new StringBuffer();
		sql.append("select unquality_point_id upId,num from (");
		sql.append(" select unquality_point_id,count(unquality_point_id) num from inspection_record group by unquality_point_id order by num desc) S");
		sql.append(" order by num LIMIT 10");
		List<Map<String, Object>> ls = super.findListBySql(sql.toString());
		return ls;
	}
}
