package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import cc.dfsoft.project.biz.base.subpackage.dao.CostApplyDao;
import cc.dfsoft.project.biz.base.subpackage.dto.CostApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.CostApply;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 付款申请
 * @author cui
 *已作废
 */
@Repository
public class CostApplyDaoImpl extends NewBaseDAO<CostApply,String> implements CostApplyDao {

    @Override
    public Map<String, Object> queryCostApply(CostApplyReq costApplyReq) throws ParseException {
        Criteria c = super.getCriteria();
        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        //根据Id进行查询
        if(StringUtils.isNotBlank(costApplyReq.getCaId())){
            c.add(Restrictions.eq("caId",costApplyReq.getCaId()));
        }

        //根据申请付款类型进行查询
        if(StringUtils.isNotBlank(costApplyReq.getCaType())){
            c.add(Restrictions.eq("caType",costApplyReq.getCaType()));
        }

        //申请编号
        if(StringUtils.isNotBlank(costApplyReq.getCaNo())){
            c.add(Restrictions.like("caNo","%"+costApplyReq.getCaNo()+"%"));
        }

        //请款单位
//        if(StringUtils.isNotBlank(costApplyReq.getCaUnit())){
//            c.add(Restrictions.like("caUnit","%"+costApplyReq.getCaUnit()+"%"));
//        }

        //用于字符串日期格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //申请日期开始
        if(StringUtils.isNotBlank(costApplyReq.getCaStartDate())){
            c.add(Restrictions.ge("caDate", sdf.parse(costApplyReq.getCaStartDate())));
        }
        //申请日期结束
        if(StringUtils.isNotBlank(costApplyReq.getCaEndDate())){
            c.add(Restrictions.le("caDate", sdf.parse(costApplyReq.getCaEndDate())));
        }

        String post=loginInfo.getPost();
        String [] postArray=post.split(",");

        // 数据库根据条件过滤记录数
        int filterCount = this.countByCriteria(c);
        // 根据条件获取查询信息
        List<CostApply> list = this.findBySortCriteria(c, costApplyReq);
        // 返回结果
        return ResultUtil.pageResult(filterCount, costApplyReq.getDraw(),list);
    }
}
