package cc.dfsoft.project.biz.base.common.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.common.dao.DataFilerSetUpDao;
import cc.dfsoft.project.biz.base.common.entity.DataFilerSetUp;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class DataFilerSetUpDaoImpl  extends NewBaseDAO<DataFilerSetUp, String> implements DataFilerSetUpDao {
	
	/**
	 * 查询所有的数据过滤配置
	 * @author fuliwei  
	 * @date 2018年8月22日  
	 * @version 1.0
	 */
	@Override
	public List<DataFilerSetUp> getDataFilerSetUpList() {
		Criteria c = super.getCriteria();
		 List<DataFilerSetUp> list = this.findByCriteria(c);
		return list;
	}

}
