package cc.dfsoft.project.biz.base.maintain.dao.impl;

import cc.dfsoft.project.biz.base.maintain.dao.SysCodeDescDao;
import cc.dfsoft.project.biz.base.maintain.dto.SysCodeDescReq;
import cc.dfsoft.project.biz.base.maintain.entity.SysCodeDesc;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:16
 * @Version:1.0
 */
@Repository
public class SysCodeDescDaoImpl extends NewBaseDAO<SysCodeDesc, String> implements SysCodeDescDao {
    @Override
    public List<SysCodeDesc> getSysCodeDescList(SysCodeDescReq sysCodeDescReq) {
        sysCodeDescReq.setSort("asc");
        sysCodeDescReq.setSortName("sort");
        Criteria criteria = super.getCriteria();

        if (StringUtil.isNotBlank(sysCodeDescReq.getTableName())) {
            criteria.add(Restrictions.eq("tableName", sysCodeDescReq.getTableName()));
        }
        if (StringUtil.isNotBlank(sysCodeDescReq.getType())) {
            criteria.add(Restrictions.eq("type",sysCodeDescReq.getType()));
        }
        List<SysCodeDesc> sysCodeDescList = this.findBySortCriteria(criteria,sysCodeDescReq);
        return sysCodeDescList;
    }
}
