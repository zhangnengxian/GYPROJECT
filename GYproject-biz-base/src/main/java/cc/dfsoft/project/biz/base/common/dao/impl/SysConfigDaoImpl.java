package cc.dfsoft.project.biz.base.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.common.dao.SysConfigDao;
import cc.dfsoft.project.biz.base.common.entity.SysConfigBean;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Repository
public class SysConfigDaoImpl extends NewBaseDAO<SysConfigBean, String> implements SysConfigDao{

	@Override
	public List<SysConfigBean> getSysConfigList() {
		Criteria c = super.getCriteria();
		 List<SysConfigBean> list = this.findByCriteria(c);
		return list;
	}

	@Override
	public List<Map<String, Object>> getConstantsList() {
		String sql = "SELECT ID,PID,CNNAME,CNVALUE,ISUSE,RESERVE1,RESERVE2 FROM T_SYS_CONSTANTS WHERE ISUSE='yes' ORDER BY PID,CNVALUE";
		return this.findListBySql(sql);
	}

	@Override
	public List<SysConfigBean> findByKey(String key) {
		Criteria c = super.getCriteria();
		if(StringUtil.isNotBlank(key)){
			c.add(Restrictions.like("keyId",key+"%"));
		}
		return this.findByCriteria(c);
	}
}
