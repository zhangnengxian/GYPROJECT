package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.entity.ProjectSign;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;

/**
 * 工程标记信息Dao
 * @author cui
 *
 */
public interface ProjectSignDao extends CommonDao<ProjectSign, String>{

    /**
     * 根据类型查标记工程
     * @param projId
     * @param value
     * @return
     */
    ProjectSign findOnly(String projId, String signType);
    
    
    /**
     *查询是否存在标记
     * @author fuliwei
     * @createTime 2017年12月28日
     * @param 
     * @return
     */
    public List<ProjectSign> findByProjIdAndStatus(String projId,String status);


    /**
     * 根据工程、状态、类型查询标记列表
     * @author liaoyq
     * @createTime 2018年6月29日
     * @param projId
     * @param status
     * @param signTypes
     * @return
     */
	List<ProjectSign> findByProjIdAndStatus(String projId, String status,
			List<String> signTypes);

    List<ProjectSign> findListByStatus(String status);
}
