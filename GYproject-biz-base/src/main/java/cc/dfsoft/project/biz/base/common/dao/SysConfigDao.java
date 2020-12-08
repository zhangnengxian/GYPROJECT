package cc.dfsoft.project.biz.base.common.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.common.entity.SysConfigBean;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface SysConfigDao extends CommonDao<SysConfigBean, String>{
	
	public List<SysConfigBean> getSysConfigList();

	public List<Map<String,Object>> getConstantsList();

	/**
	 * 
	 * 注释：根据key值模糊查询配置列表
	 * @author liaoyq
	 * @createTime 2019年7月30日
	 * @param key
	 * @return
	 *
	 */
	public List<SysConfigBean> findByKey(String key);

}
