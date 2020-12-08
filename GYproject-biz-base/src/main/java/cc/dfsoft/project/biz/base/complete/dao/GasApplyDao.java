package cc.dfsoft.project.biz.base.complete.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.GasApplyReq;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface GasApplyDao extends CommonDao<GasApply, String>{

	
	/**
	 * 根据工程id查询GasApply
	 * @param id
	 * @return
	 */
	public GasApply viewGasApplyById(String id);

	/**
	 * 通气申请列表条件查询
	 * @param gasApplyReq
	 * @return
	 */
	Map<String, Object> queryGasApply(GasApplyReq gasApplyReq) throws ParseException ;

}

