package cc.dfsoft.project.biz.base.baseinfo.service;

import cc.dfsoft.project.biz.base.baseinfo.dto.SupervisionQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SupervisionUnit;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;

import java.text.ParseException;
import java.util.Map;

/**
 * 
 * @author cui
 * 监理单位service接口
 *
 */
public interface SupervisionUnitService {
	/**
	 * 监理单位列表查询
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> querySupervisionUnit(SupervisionQueryReq supervisionQueryReq) throws ParseException;
	
	public SupervisionUnit viewSupervisionById(String id);

	public String saveSupervision(SupervisionUnit supervisionUnit);

	public void delSupervision(String id);

    void modifyInfo(ConstructionPlan cplan);
}




