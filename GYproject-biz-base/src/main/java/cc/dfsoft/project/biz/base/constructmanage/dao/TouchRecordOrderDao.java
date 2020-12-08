package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecordOrder;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface TouchRecordOrderDao extends CommonDao<ConnectRecordOrder,String>{

	/**
	 * 碰口记录单
	 * @param projId
	 * @return
	 */
	List<ConnectRecordOrder> findbyProjId(String projId);

}
