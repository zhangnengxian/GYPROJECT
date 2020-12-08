package cc.dfsoft.project.biz.base.project.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;


/**
 * 工程规模明细服务接口
 * @author pengtt
 * @createTime 2016-06-22
 *
 */
public interface ScaleDetailService {
	
	/**
	 * 该方法只用于立项申请  选中工程后的工程规模明细查询方法
	 * @author pengtt
	 * @createTime 2016-06-21
	 * @param scaleDetailQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	public Map<String,Object> queryScaleDetail(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException;
	
	/**
	 * 该方法用于立项申请  右侧多条工程规模明细的维护（即删除原有工程的规模明细，新增现工程规模明细）
	 * @param result
	 * 		{ 工程id：projId，
	 * 		    工程规模明细：list<ScaleDetail>
	 * 		}
	 */
	public void batMaintain(Map<String,Object> result);
	
	/**
	 * 查询工程规模明细列表
	 * @param scaleDetailQueryReq
	 * @author pengtt
	 * @createTime 2016-07-12
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> queryScaleDetailCommon(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException;

	/**
	 * 根据工程查询规模记录
	 * @param projId
	 * @return
	 */
	public List<ScaleDetail> findByprojId(String projId);

	/**
	 * 批量保存规模细类
	 * @param newList
	 */
	public void batchInsertObjects(List<ScaleDetail> newList);
}
