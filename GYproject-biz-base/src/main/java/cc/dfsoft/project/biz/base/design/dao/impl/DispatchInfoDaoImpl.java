package cc.dfsoft.project.biz.base.design.dao.impl;

import cc.dfsoft.project.biz.base.design.dao.DispatchInfoDao;
import cc.dfsoft.project.biz.base.design.entity.DispatchInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DispatchInfoDaoImpl extends NewBaseDAO<DispatchInfo, String> implements DispatchInfoDao{

    @Override
    public DispatchInfo findByProjId(String projId) {
        Criteria c = super.getCriteria();
        if(StringUtils.isNotBlank(projId)){
            c.add(Restrictions.eq("projId",projId));
        }
        List<DispatchInfo> list = c.list();
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
