package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.BcType;

/**
 * 通知类型接口
 * @author Yuanyx
 *
 */
public interface BcTypeService {

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
