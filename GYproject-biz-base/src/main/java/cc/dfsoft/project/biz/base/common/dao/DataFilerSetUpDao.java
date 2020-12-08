package cc.dfsoft.project.biz.base.common.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.common.entity.DataFilerSetUp;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DataFilerSetUpDao extends CommonDao<DataFilerSetUp, String> {
	/**
	 * 查询所有的数据过滤配置
	 * @author fuliwei  
	 * @date 2018年8月22日  
	 * @version 1.0
	 */
	public List<DataFilerSetUp> getDataFilerSetUpList();
}
