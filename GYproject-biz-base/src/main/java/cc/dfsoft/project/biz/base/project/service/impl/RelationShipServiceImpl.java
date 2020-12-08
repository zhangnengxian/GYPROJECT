package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.RelationShipDao;
import cc.dfsoft.project.biz.base.project.entity.RelationShip;
import cc.dfsoft.project.biz.base.project.service.RelationShipService;

@Service
@Transactional(readOnly =true ,propagation= Propagation.REQUIRED)
public class RelationShipServiceImpl implements RelationShipService{
	
	/**关联关系*/
	@Resource
	RelationShipDao relationShipDao;
	
	/**
	 * 查询关联关系
	 * @author fuliwei
	 * @createTime 2017年7月19日
	 * @param 
	 * @return
	 */
	@Override
	public List<RelationShip> queryRelationShip() {
		return relationShipDao.queryRelationShip();
	}

}
