package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.DataAcceptanceDao;
import cc.dfsoft.project.biz.base.complete.entity.DataAcceptance;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Repository
public class DataAcceptanceDaoImpl extends NewBaseDAO<DataAcceptance, String> implements DataAcceptanceDao{
	
	/**
	 * 根据工程id查询资料验收申请表
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	@Override
	public DataAcceptance findByProjId(String projId) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<DataAcceptance> list=this.findByCriteria(c);
			if(list!=null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}
	

}
