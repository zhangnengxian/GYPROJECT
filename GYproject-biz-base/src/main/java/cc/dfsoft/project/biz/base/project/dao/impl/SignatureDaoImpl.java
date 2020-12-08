package cc.dfsoft.project.biz.base.project.dao.impl;

import cc.dfsoft.project.biz.base.project.dao.SignatureDao;
import cc.dfsoft.project.biz.base.project.dto.SignatureQueryDto;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SignatureDaoImpl extends NewBaseDAO<Signature, String> implements SignatureDao {

	@Override
	public List<Signature> findByProperties(String entityName, String fieldName, String boId,String projId) {
		
		Criteria c = super.getCriteria();
		// 实体类名称
		if (StringUtils.isNotBlank(entityName)) {
			c.add(Restrictions.eq("entityName", entityName));
		}
		// 字段名称
		if (StringUtils.isNotBlank(fieldName)) {
			c.add(Restrictions.eq("fieldName", fieldName));
		}
		// 业务操作id
		if (StringUtils.isNotBlank(boId)) {
			c.add(Restrictions.eq("businessOrderId", boId));
		}
		//工程id
		if (StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		// 根据条件获取查询信息
		List<Signature> signaturelList = this.findByCriteria(c);
		return signaturelList;
	}

	@Override
	public List<Signature> findByProperties(String entityName, String fieldName, String boId, String projId,
			boolean flag) {
		Criteria c = super.getCriteria();
		// 实体类名称
		if (StringUtils.isNotBlank(entityName)) {
			c.add(Restrictions.eq("entityName", entityName));
		}
		// 字段名称
		if (StringUtils.isNotBlank(fieldName)) {
			c.add(Restrictions.eq("fieldName", fieldName));
		}
		// 业务操作id
		if (StringUtils.isNotBlank(boId)) {
			c.add(Restrictions.eq("businessOrderId", boId));
		}
		//工程id
		if (StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		if(flag){
			c.add(Restrictions.sqlRestriction("latitude is not null and longitude is not null"));
		}
		// 根据条件获取查询信息
		List<Signature> signaturelList = super.findByCriteria(c);
		return signaturelList;
	}

	@Override
	public List<Map<String, Object>> findSignStep() {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct s.menu_des menuDes,s.menu_id menuId from signature s where s.menu_id is not null");
		return super.findListBySql(sql.toString());
	}

	@Override
	public List<Signature> findByProperties(SignatureQueryDto signatureQueryDto) {
		Criteria c = super.getCriteria();
		// 实体类名称
		if (StringUtils.isNotBlank(signatureQueryDto.getEntityName())) {
			c.add(Restrictions.eq("entityName", signatureQueryDto.getEntityName()));
		}
		// 字段名称
		if (StringUtils.isNotBlank(signatureQueryDto.getFieldName())) {
			c.add(Restrictions.eq("fieldName", signatureQueryDto.getFieldName()));
		}
		// 业务操作id
		if (StringUtils.isNotBlank(signatureQueryDto.getBusinessOrderId())) {
			c.add(Restrictions.eq("businessOrderId", signatureQueryDto.getBusinessOrderId()));
		}
		//工程id
		if (StringUtils.isNotBlank(signatureQueryDto.getProjId())){
			c.add(Restrictions.eq("projId", signatureQueryDto.getProjId()));
		}
		//菜单id（环节）
		if(StringUtils.isNotBlank(signatureQueryDto.getMenuId())){
			c.add(Restrictions.eq("menuId", signatureQueryDto.getMenuId()));
		}
		c.add(Restrictions.sqlRestriction("latitude is not null and longitude is not null"));
		// 根据条件获取查询信息
		List<Signature> signaturelList = super.findByCriteria(c);
		return signaturelList;
	}

	@Override
	public Signature selectImg(String boId,String menuDes) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Signature s where s.fieldName is null and s.entityName is null and s.businessOrderId='").append(boId).
		append("' and  s.menuDes='").append(menuDes).append("'");
		List<Signature> list=super.findByHql(hql.toString());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteByPcId(String fieldName, String pcId) {
		StringBuffer hql = new StringBuffer("delete from Signature where fieldName='").append(fieldName).append("' and businessOrderId='").append(pcId).append("'");
		super.executeHql(hql.toString());
		
	}

	@Override
	public Signature queryImg(String boId, String menuId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Signature s where s.fieldName is null and s.entityName is null and s.businessOrderId='").append(boId).
		append("' and  s.menuId='").append(menuId).append("'");
		List<Signature> list=super.findByHql(hql.toString());
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Signature> findByBIdAndMenuId(String businessOrderId,
			String menuId) {
		String hql = "from Signature where businessOrderId='"+businessOrderId+"'";
		if(StringUtil.isNotBlank(menuId)){
			hql +=" and menuId='"+menuId+"'";
		}
		return super.findByHql(hql);
	}
	
	@Override
	public void deleteByBId(String buId) {
		String sql = "delete from SIGNATURE where BUSINESS_ORDER_ID = ? and FIELD_NAME like '"+Constants.AUDIT_INS_POST+"%'";
		this.executeSql(sql, new Object[]{buId});
	}

	@Override
	public List<Signature> findListByBusId(String busId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("businessOrderId", busId));
		return super.findByCriteria(c);
	}
}
