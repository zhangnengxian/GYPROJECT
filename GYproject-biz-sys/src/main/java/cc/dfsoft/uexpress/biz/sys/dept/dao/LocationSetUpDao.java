package cc.dfsoft.uexpress.biz.sys.dept.dao;

import java.util.Map;

import cc.dfsoft.uexpress.biz.sys.dept.dto.LocationSetUpQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.LocationSetUp;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface LocationSetUpDao extends CommonDao<LocationSetUp, String>{
	
	/**
	 * 查询定位设置列表
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	public Map<String,Object> quertLocationSetUpList(LocationSetUpQueryReq req);
	
	
	/**
	 * 通过deptType查询
	 * @author fuliwei
	 * @createTime 2017年2月24日
	 * @param 
	 * @return
	 */
	public LocationSetUp queryByDeptType(String deptType);
}
