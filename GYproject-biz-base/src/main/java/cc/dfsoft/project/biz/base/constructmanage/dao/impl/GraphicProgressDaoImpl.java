package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.GraphicProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.GraphicProgress;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
@Repository
public class GraphicProgressDaoImpl extends NewBaseDAO<GraphicProgress,String> implements GraphicProgressDao{
	
	/**
	 * 查找形象进度
	 * @author fuliwei
	 * @createTime 2017年3月24日
	 * @param 
	 * @return
	 */
	@Override
	public List<GraphicProgress> queryGraphicProgress() {
		StringBuffer sql=new StringBuffer();
		sql.append("from GraphicProgress");
		List<GraphicProgress> result = super.findByHql(sql.toString());
		return result;
	}
}
