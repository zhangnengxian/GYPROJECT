package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.project.dto.RelationShipReq;
import cc.dfsoft.project.biz.base.project.entity.RelationShip;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface RelationShipDao extends CommonDao<RelationShip, String>{
	
	/**
	 * 查询关联关系
	 * @author fuliwei
	 * @createTime 2017年7月19日
	 * @param 
	 * @return
	 */
	public List<RelationShip> queryRelationShip();
}
