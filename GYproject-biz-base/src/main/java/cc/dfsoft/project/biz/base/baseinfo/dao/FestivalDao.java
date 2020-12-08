package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.FestivalReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Festival;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 节假日、补班配置dao接口
 * @author liaoyq
 * @createTime 2018年5月7日
 */
public interface FestivalDao extends CommonDao<Festival, String> {

	/**
	 * 查询节假日列表
	 * @author liaoyq
	 * @createTime 2018年5月8日
	 * @param festivalReq
	 * @return
	 */
	Map<String, Object> queryList(FestivalReq festivalReq);

	/**
	 * 查询两个日期之间的节假日配置
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 * @param festivalReq
	 * @return
	 */
	List<Festival> queryFestivals(FestivalReq festivalReq);

}
