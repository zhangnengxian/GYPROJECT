package cc.dfsoft.project.biz.base.project.service;

import java.util.List;

import cc.dfsoft.project.biz.base.project.entity.RelationShip;

public interface RelationShipService {
	
	/**
	 * 查询关联关系
	 * @author fuliwei
	 * @createTime 2017年7月19日
	 * @param 
	 * @return
	 */
	public List<RelationShip> queryRelationShip();
}
