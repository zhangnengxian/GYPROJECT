package cc.dfsoft.project.biz.base.constructmanage.dao;

import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionOrganization;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;


/**
 * 施工组织Dao
 * @author cui
 * @createTime 2016-06-30
 *
 */
public interface ConstructionOrganizationDao extends CommonDao<ConstructionOrganization,String>{

    /**
     * @createTime 2017-09-30
     * @author cui
     * @param projId 工程id
     * @return
     */
    List<ConstructionOrganization> findbyProjId(String projId);
    
    /**
	 * 查询施工组织是否已完成
	 * @author fuliwei
	 * @createTime 2017年11月4日
	 * @param 
	 * @return
	 */
	public ConstructionOrganization findByProjIdAndState(String projId,String signState);

	/**
	 * 查询当前施工员发生重报的施工组织
	 * @author liaoyq
	 * @createTime 2018年6月8日
	 * @return
	 */

	List<ConstructionOrganization> queryCuOrganizationNotice(String staffId);

	/**
	 * 查询完成签字的施工组织记录数量
	 * @author liaoyq
	 * @createTime 2019年5月15日
	 * @param projId
	 * @param signStatus 
	 * @return
	 */
	Integer countSignedByProjId(String projId, String signStatus);
}
