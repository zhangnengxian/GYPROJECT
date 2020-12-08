package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.DigRoadQueryReq;

/**
 * 开挖路况service
 * @author pengtt
 * @createTime 2016-08-203
 *
 */
public interface DigRoadService {

	/**
	 * 显示出该工程碰口开挖路况详述
	 * @param tpId
	 * @return
	 */
	public Map<String,Object> digRoadDetail(DigRoadQueryReq digRoadQueryReq);
}
