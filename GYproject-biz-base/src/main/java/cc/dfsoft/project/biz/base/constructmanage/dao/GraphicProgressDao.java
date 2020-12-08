package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.GraphicProgress;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 形象进度Dao
 * @author fuliwei
 *
 */
public interface GraphicProgressDao extends CommonDao<GraphicProgress,String>{
	
	/**
	 * 查找形象进度
	 * @author fuliwei
	 * @createTime 2017年3月24日
	 * @param 
	 * @return
	 */
	public List<GraphicProgress> queryGraphicProgress();
}
