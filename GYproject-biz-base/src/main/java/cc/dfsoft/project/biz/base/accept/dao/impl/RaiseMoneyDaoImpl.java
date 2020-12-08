package cc.dfsoft.project.biz.base.accept.dao.impl;

import cc.dfsoft.project.biz.base.accept.dao.ProjInfoBackDao;
import cc.dfsoft.project.biz.base.accept.dao.RaiseMoneyDao;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoBack;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoModify;
import cc.dfsoft.project.biz.base.accept.entity.RaiseMoney;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 提资信息
 * @author fuliwei
 *
 */
@Repository
public class RaiseMoneyDaoImpl extends NewBaseDAO<RaiseMoney,String> implements RaiseMoneyDao{

    @Override
    public void saveRaiseMoney(RaiseMoney raiseMoney) {
    }

    @Override
    public void updateById(RaiseMoney raiseMoney) {
        String hql="UPDATE raise_money a set " +
                "a.APPLAY_DATE='"+raiseMoney.getApplyDate()+"'," +
                "a.REMARKS='"+raiseMoney.getRemark()+"'," +
                "a.UPLOADFLAG='"+raiseMoney.getUploadFlag()+"' " +
                "where a.PROJ_ID='"+raiseMoney.getProjId()+"'";
        this.executeHql(hql);

    }
    
    /***
     * 通过工程id查详述
     * @author fuliwei  
     * @date 2018年9月13日  
     * @version 1.0
     */
	@Override
	public RaiseMoney viewByProjId(String projId) {
		Criteria c = super.getCriteria();
		
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		
		List<RaiseMoney> list= this.findByCriteria(c);
		
		if(list != null  && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
