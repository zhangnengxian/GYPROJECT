package cc.dfsoft.project.biz.base.project.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author pengtt
 * @CreateTime 2016-06-21
 *
 */
public interface ScaleDetailDao extends CommonDao<ScaleDetail, String>{
	
	/**
	 * 工程规模明细列表查询
	 * @author pengtt
	 * @createTime 2016-06-23
	 * @param scaleDetailQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> queryScaleDetail(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException;
	
	/**
	 * 按工程id删除工程规模明细
	 * @author pengtt
	 * @createTime 2016-06-23
	 * @param projId
	 */
	public void deleteByProjId(String projId);

	/**
	 * 根据工程Id查询规模列表
	 * @param projId
	 * @return
	 */
	public List<ScaleDetail> findByprojId(String projId);
	
	/**
	 * 查询工程项目对应的工程规模数量
	 * @param proID
	 * @return
	 */
	public Map<String,Object>queryNumOfProId(String proID);
	
}
