package cc.dfsoft.project.biz.base.complete.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 自检单Dao
 * @author Yuanyx
 *
 */
public interface SelfInspectionListDao extends CommonDao<SelfInspectionList, String>{
	
	/**
	 * 根据工程Id查自检单
	 * @param projId
	 * @return
	 */
	List<SelfInspectionList> findByprojectId(String projId);
	
	/**
	 * 自检单列表查询
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySelInspection(PreinspectionReq req) throws ParseException ;

	/**
	 * 标记自检为删除
	 * @author liaoyq
	 * @param isDel 
	 * @createTime 2018年7月27日
	 * @param silId
	 */
	void deleteByProjID(String projId, String isDel);

	boolean isExistValid(String projId);
}
