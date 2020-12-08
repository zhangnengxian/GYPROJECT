package cc.dfsoft.project.biz.base.inspection.service;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListAntisepsisSpecReq;
import cc.dfsoft.project.biz.base.inspection.entity.AntisepsisSpec;

/**
 * 除锈/防腐检查规格接口
 * @author zhangjj
 *
 */
public interface AntisepsisSpecService {
	/**
	 * 根据报验单id查询列表
	 * @return List<AntisepsisSpec>
	 */
	public List<AntisepsisSpec> queryAntSpecByPcId(String pcId,String asType);
	/**
	 * 保存规格列表
	 * @param req
	 */
	public void saveAntSpecList(ProjectCheckListAntisepsisSpecReq req);
}
