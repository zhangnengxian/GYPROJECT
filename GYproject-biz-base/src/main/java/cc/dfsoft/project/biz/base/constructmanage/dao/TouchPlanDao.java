package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.TouchPlanQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.project.biz.base.constructmanage.entity.TouchPlan;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface TouchPlanDao extends CommonDao<TouchPlan,String>{

	
	/**
	 * 工程下所有碰口方案
	 * @param projId
	 * @return
	 */
	List<TouchPlan> findbyProjId(String projId);

	/**
	 * 碰口方案下所有开路况
	 * @param tpId
	 * @return
	 */
	List<DigRoad> findbyTpId(String tpId);
	
	/**
	 * 碰口方案审核列表查询
	 * @param touchPlanQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> queryTouchPlanAudit(TouchPlanQueryReq touchPlanQueryReq) throws ParseException;

}
