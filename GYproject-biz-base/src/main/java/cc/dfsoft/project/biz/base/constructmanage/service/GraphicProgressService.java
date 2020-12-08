package cc.dfsoft.project.biz.base.constructmanage.service;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.GraphicProgress;

/**
 * 形象进度
 * @author fuliwei
 *
 */
public interface GraphicProgressService {
	
	/**
	 * 查找形象进度
	 * @author fuliwei
	 * @createTime 2017年3月24日
	 * @param 
	 * @return
	 */
	public List<GraphicProgress> queryGraphicProgress();
	
	
	/**
	 * 通过主键id查找
	 * @author fuliwei
	 * @createTime 2017年3月24日
	 * @param 
	 * @return
	 */
	public GraphicProgress findById(String gpId);
	
	/**
	 * 保存形象进度
	 * @author fuliwei
	 * @createTime 2017年3月25日
	 * @param graphicProgress
	 * @return
	 */
	public void saveGraphicProgress(GraphicProgress graphicProgress);
	
	
}
