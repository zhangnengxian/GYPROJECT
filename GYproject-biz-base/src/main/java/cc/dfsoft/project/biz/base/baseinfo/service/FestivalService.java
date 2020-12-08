package cc.dfsoft.project.biz.base.baseinfo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.FestivalReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Festival;


/**
 * 节假日补班service接口
 * @author liaoyq
 * @createTime 2018年5月7日
 */
public interface FestivalService {

	/**
	 * 查询节假日信息列表
	 * @author liaoyq
	 * @createTime 2018年5月8日
	 * @param festivalReq
	 * @return
	 */
	Map<String, Object> queryList(FestivalReq festivalReq);

	/**
	 * 更新保存节假日信息
	 * @author liaoyq
	 * @createTime 2018年5月8日
	 * @param festival
	 * @return
	 */
	String saveFestival(Festival festival);

	/**
	 * 根据ID查询节假日信息
	 * @author liaoyq
	 * @createTime 2018年5月8日
	 * @param id
	 * @return
	 */
	Festival findById(String id);

	/**
	 * 删除节假日配置信息
	 * @author liaoyq
	 * @createTime 2018年5月8日
	 * @param id
	 */
	void delFestival(String id);

	/**
	 * 查询所有节假日日期列表
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 * @return
	 */
	List<Date> queryHolidays();

	/**
	 * 查询所有工作日列表
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 * @return
	 */
	List<Date> queryWorkdays();

	/**
	 * 清除cache
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 */
	void clearCacheMap();

	List<Date> getCacheMap(String key);

}
