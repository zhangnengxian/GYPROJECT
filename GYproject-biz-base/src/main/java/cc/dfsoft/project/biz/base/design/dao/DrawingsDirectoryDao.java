package cc.dfsoft.project.biz.base.design.dao;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DrawingsDirectory;
import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author zhangjj
 *
 */
public interface DrawingsDirectoryDao extends CommonDao<DrawingsDirectory, String>{
	/**
	 * 图纸目录列表查询(分页)
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryDrawDirectory(DrawingsDirectoryReq pageSortReq);
	
	/**
	 * 图纸目录列表查询
	 * @param pageSortReq
	 * @return
	 */
	public List<DrawingsDirectory> queryAllDrawDirect(DrawingsDirectory drawingsDirectory);

	/**
	 * 图纸审核页面查询工程列表用
	 * @author
	 * @createTime	2016-7-5
	 * @param	projId 工程id
	 * @return	List<DrawingsDirectory>
	 */
	public List<DrawingsDirectory> findByProjId(String projId);
	/**
	 * 根据工程Id查询图纸目录列表
	 * @param projId
	 * @return
	 */
	public List<DrawingsDirectory> queryDrawingsDirectoryList(String projId);



}
