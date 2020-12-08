package cc.dfsoft.project.biz.base.accept.service;

import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;

import java.util.Map;


public interface MarketInfoService {

	/**
	 * 市场营销员列表查询
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param
	 * @return
	 */
	public Map<String, Object> queryMarket(DesignerQueryReq designerQueryReq);

	/**
	 * 市场派工后更新工程表
	 * @author wangang
	 * @createTime 2019-11-27
	 * @param designDispatchReq
	 * @return
	 */
	public void update(DesignDispatchDto designDispatchReq);

	
}
