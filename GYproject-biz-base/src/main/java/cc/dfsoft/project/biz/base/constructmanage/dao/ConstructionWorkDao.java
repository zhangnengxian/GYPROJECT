package cc.dfsoft.project.biz.base.constructmanage.dao;

import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;
/**
 * 交底记录Dao
 * @author Administrator
 *
 */
public interface ConstructionWorkDao extends CommonDao<ConstructionWork,String> {
	
	/**
	 * 根据工程id 和资料类型查询工程交底
	 * @author 
	 * @createTime 2017-1-27
	 * @param 
	 * @return
	 */
	List<ConstructionWork> findByProjId(String projId,String dataType);

	/**
	 * @author liaoyq
	 * @createTime 2017年7月28日
	 * @param request
	 * @param constructionWorkReq 会审交底查询条件dto
	 * @return  Map<String, Object> 会审交底记录及分页信息
	 */
	Map<String, Object> queryList(ConstructionWorkReq constructionWorkReq);

	/**
	 *获取第一次交底信息
	 * @author liaoyq
	 * @createTime 2018年5月4日
	 * @param constructionWorkReq
	 * @return
	 */
	ConstructionWork queryFirstCw(ConstructionWorkReq constructionWorkReq);

	/**
	 * 查询已完成签字的交底记录数量
	 * @author liaoyq
	 * @createTime 2019年5月15日
	 * @param projId
	 * @param signStatus
	 * @return
	 */
	Integer countSignedByProjId(String projId, String signStatus);

    List<ConstructionWork> findByProjId(String projId);
}
