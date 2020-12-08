package cc.dfsoft.project.biz.base.inspection.dao;
import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.AntisepsisSpec;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface AntisepsisSpecDao extends CommonDao<AntisepsisSpec, String>{
	/**
	 * 根据报验单id查询列表
	 * @return
	 */
	public List<AntisepsisSpec> queryAntSpecByPcId(String pcId,String asType);
	/**
	 * 保存规格列表
	 * @param list
	 */
	public void saveAntSpecList(List<AntisepsisSpec> list);
	
	/**
	 * 删除除锈防腐检查规格
	 * @author
	 * @createTime 2016-08-28
	 * @param pcId
	 * @return
	 */
	public void deleteByPcId(String pcId);
}
