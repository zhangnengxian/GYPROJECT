package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.DelayDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Delay;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DelayDaoImpl extends NewBaseDAO<Delay, String> implements DelayDao {

    @Override
    public Map<String, Object> getDataList(RectifyNoticeReq rectifyNoticeReq) {
        Criteria c = super.getCriteria();
        if(StringUtils.isNotBlank(rectifyNoticeReq.getProjId())){
            c.add(Restrictions.eq("projId",rectifyNoticeReq.getProjId()));
        }

        c.addOrder(Order.desc("addtime"));  // 通知日期
        int filterCount = this.countByCriteria(c);
        List<Delay> list = this.findByCriteria(c);
        return ResultUtil.pageResult( filterCount, rectifyNoticeReq.getDraw(),list);
    }

    /**
     * 查询计划开工时间，计划竣工时间，实际开工时间
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getDate(String id) {
        String sql = "SELECT DATE_FORMAT(S.SC_PLANNED_START_DATE, '%Y-%m-%d' ) PLANSTART," +
                "DATE_FORMAT(S.SC_PLANNED_END_DATE, '%Y-%m-%d' ) PLANEND," +
                "DATE_FORMAT(W.PLANNED_START_DATE, '%Y-%m-%d' ) STUTASDATE " +
                "FROM SUB_CONTRACT S JOIN WORK_REPORT W ON S.PROJ_ID= W.PROJ_ID JOIN T_DELAY_APPLY A ON S.PROJ_ID=A.PROJ_ID WHERE A.AD_ID=?";
        Map<String,Object> date = this.findObjectBySql(sql,new Object[]{id});
        return date;
    }
}
