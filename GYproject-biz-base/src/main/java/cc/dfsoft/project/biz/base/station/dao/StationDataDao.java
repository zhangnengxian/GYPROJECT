package cc.dfsoft.project.biz.base.station.dao;

import cc.dfsoft.project.biz.base.station.entity.StationData;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;

public interface StationDataDao extends CommonDao<StationData, String>{

	/**
	 * 根据类型查场站数据
	 * @auther cui by 2017-9-1
	 * @param value
	 * @return
	 */
	List<StationData> findByType(String value);
}
