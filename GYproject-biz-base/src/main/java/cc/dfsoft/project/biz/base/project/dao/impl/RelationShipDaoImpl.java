package cc.dfsoft.project.biz.base.project.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.dao.RelationShipDao;
import cc.dfsoft.project.biz.base.project.entity.RelationShip;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class RelationShipDaoImpl extends NewBaseDAO<RelationShip, String> implements RelationShipDao {
	
	/**
	 * 查询关联关系
	 * @author fuliwei
	 * @createTime 2017年7月19日
	 * @param 
	 * @return
	 */
	@Override
	public List<RelationShip> queryRelationShip() {
		Criteria c = super.getCriteria();
		List<RelationShip> list=this.findByCriteria(c);
		return list;
	}

}
