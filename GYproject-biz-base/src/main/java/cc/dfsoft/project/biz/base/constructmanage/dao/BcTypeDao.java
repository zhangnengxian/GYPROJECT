package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.BcType;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 通知类型Dao
 * @author Yuanyx
 *
 */
public interface BcTypeDao extends CommonDao<BcType,String>{

	/**
	 * 查询通知类型
	 * @param typeId
	 * @param relationTypeId
	 * @return
	 */
	List<BcType> findBcType(String typeId, String relationTypeId);

	/**
	 * 查所有类型
	 * @return
	 */
	List<BcType> findAllBcType();
}
