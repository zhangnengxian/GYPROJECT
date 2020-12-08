package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.DigRoadQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 开挖路况dao
 * @author pengtt
 * @createTime 2016-08-03
 *
 */
public interface DigRoadDao  extends CommonDao<DigRoad,String>{
	
	/**
	 * 路况查询
	 * @author pengtt
	 * @createTime 2016-08-03
	 * @param tpId
	 * @return
	 */
	List<DigRoad> findbyTpId(String tpId);
	
	Map<String,Object> queryDigRoad(DigRoadQueryReq digroadqueryreq);
	
	/**
	 * 根据碰口方案id删除开挖路况
	 * @author pengtt
	 * @createTime 2016-08-22
	 * @param tpId 碰口方案id
	 */
	void deleteByTpId(String tpId);
}
