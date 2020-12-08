package cc.dfsoft.project.biz.base.baseinfo.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.SupervisionQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SupervisionUnit;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SupervisionUnitDao extends CommonDao<SupervisionUnit,String>{
	/**
	 * 监理单位列表查询
	 * @author cui
	 * @param supervisionQueryReq
	 * @return
	 */
	public Map<String, Object> querySupervisionUnit(SupervisionQueryReq supervisionQueryReq);

    
}
