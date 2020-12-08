package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectRecordOrder;

/**
 * 碰口记录单service
 * @author cui
 * @createTime 2016-08-03
 *
 */
public interface TouchRecordOrderService {

	/**
	 * 碰口记录单保存
	 * @author cui
	 * @createTime 2016-08-04
	 */
	public void touchRecordOrderSave(ConnectRecordOrder connectRecordOrder)throws Exception;

	/**
	 * 显示出该工程碰口记录单详述
	 * @param projId
	 * @return
	 */
	public ConnectRecordOrder touchRecordOrderDetail(String projId);

}
