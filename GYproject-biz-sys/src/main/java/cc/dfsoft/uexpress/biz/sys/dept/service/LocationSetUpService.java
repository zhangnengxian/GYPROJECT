package cc.dfsoft.uexpress.biz.sys.dept.service;

import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.dept.dto.LocationSetUpQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.LocationSetUp;

public interface LocationSetUpService {
	
	/**
	 * 查询定位设置列表
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	public Map<String,Object> quertLocationSetUpList(LocationSetUpQueryReq req);
	
	/**
	 * 保存定位设置
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	public String saveLocationSetUp(LocationSetUp location);
	
	/**
	 * 删除定位设置
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	public void delLocationSetUp(String lsuId);
	
}
