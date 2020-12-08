package cc.dfsoft.project.biz.base.station.service;

import cc.dfsoft.project.biz.base.station.entity.StationData;

import java.util.List;

/**
 * 场站资料服务接口
 * @author cui
 * @createTime 2017-08-21
 *
 */
public interface StationDataService {


	/**
	 * 根据类型查场站资料
	 * @author cui
	 * @createTime 2017-08-22
	 * @param value
	 */
	List<StationData> findByType(String value);
}
