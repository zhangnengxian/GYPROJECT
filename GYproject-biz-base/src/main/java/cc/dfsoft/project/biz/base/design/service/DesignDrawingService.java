package cc.dfsoft.project.biz.base.design.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DrawingsDirectoryReq;
import cc.dfsoft.project.biz.base.design.entity.DrawingsDirectory;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
/**
 * 设计草图服务接口
 * @author zhangjj
 * @createTime 2016-06-30
 *
 */
public interface DesignDrawingService {
	/**
	 * 图纸目录列表查询
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryDrawDirectory(DrawingsDirectoryReq pageSortReq);
	/**
	 * 新增图纸目录
	 * @param PageSortReq
	 * @return
	 */
	public void insertDrawDirectory(DrawingsDirectory drawingsDirectory);
	/**
	 * 判断图号或图纸名称是否重复
	 * @param drawingsDirectory
	 * @return
	 */
	public String judgeRepeat(DrawingsDirectory drawingsDirectory);
	/**
	 * 更新状态
	 * @param projId
	 * @param projNo
	 * @return
	 */
	public void updateState(ProjectQueryReq  proReq);
	
	/**
	 * 图纸批量导入
	 * @author fuliwei
	 * @createTime 2016-7-25
	 * @param
	 * @return void
	 */
	public void batInsertCostSum(JSONArray jsonArr,String projId);
	/**
	 * 改派保存
	 * @param designDispatchReq
	 */
	public void updateProject(DesignDispatchDto designDispatchReq);
	/**
	 * 保存采购计划
	 * @param projId
	 */
	public void saveProcurePlan(String projId);
	
}
