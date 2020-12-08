package cc.dfsoft.project.biz.base.station.dao.impl;

import cc.dfsoft.project.biz.base.station.dao.StationDataDao;
import cc.dfsoft.project.biz.base.station.entity.StationData;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
@Repository
public class StationDataDaoImpl extends NewBaseDAO<StationData, String> implements StationDataDao {
    @Override
    public List<StationData> findByType(String value) {
        Criteria c = super.getCriteria();
        //资料类型
        if(StringUtils.isNotBlank(value)){
            c.add(Restrictions.eq("sdType",value));
        }
        return this.findByCriteria(c);
    }
}
