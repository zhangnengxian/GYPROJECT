package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.GraphicProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.GraphicProgress;
import cc.dfsoft.project.biz.base.constructmanage.service.GraphicProgressService;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class GraphicProgressServiceImpl implements GraphicProgressService{
	
	/**形象进度Dao*/
	@Resource
	GraphicProgressDao graphicProgressDao;
	
	/**
	 * 查找形象进度
	 * @author fuliwei
	 * @createTime 2017年3月24日
	 * @param 
	 * @return
	 */
	@Override
	public List<GraphicProgress> queryGraphicProgress() {
		return graphicProgressDao.queryGraphicProgress();
	}
	
	/**
	 * 通过主键id查找
	 * @author fuliwei
	 * @createTime 2017年3月24日
	 * @param 
	 * @return
	 */
	@Override
	public GraphicProgress findById(String gpId) {
		return graphicProgressDao.get(gpId);
	}
	
	/**
	 * 保存形象进度
	 * @author fuliwei
	 * @createTime 2017年3月25日
	 * @param graphicProgress
	 * @return
	 */
	@Override
	public void saveGraphicProgress(GraphicProgress graphicProgress) {
		
	}

}
