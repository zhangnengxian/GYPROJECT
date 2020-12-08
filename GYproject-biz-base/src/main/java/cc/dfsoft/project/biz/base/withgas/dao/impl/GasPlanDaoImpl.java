package cc.dfsoft.project.biz.base.withgas.dao.impl;

import cc.dfsoft.project.biz.base.withgas.dao.GasPlanDao;
import cc.dfsoft.project.biz.base.withgas.dto.GasPlanReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasPlan;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author cui
 * @createTime 2017-8-8
 *
 */
@Repository
public class GasPlanDaoImpl extends NewBaseDAO<GasPlan, String> implements GasPlanDao {

    @Override
    public Map<String, Object> queryGasPlan(GasPlanReq gasPlanReq) {
        Criteria c = super.getCriteria();

        LoginInfo loginInfo = SessionUtil.getLoginInfo();
        if(DeptDivideEnum.CUSTOMER_SERVICE_CENTER.getValue().equals(loginInfo.getDeptDivide())) {
            //客服中心的查自己
            c.add(Restrictions.like("deptId",loginInfo.getDeptId()+"%"));
        }else{
            //技术组查自己的
            c.add(Restrictions.isNull("deptId"));
        }
        c.add(Restrictions.like("corpId",loginInfo.getCorpId()+"%"));
        //计划id
        if(StringUtils.isNotBlank(gasPlanReq.getGpId())){
            c.add(Restrictions.eq("gpId",gasPlanReq.getGpId()));
        }
        //计划编号
        if(StringUtils.isNotBlank(gasPlanReq.getGpNo())){
            c.add(Restrictions.like("gpNo",gasPlanReq.getGpNo()+"%"));
        }
        //计划名称
        if(StringUtils.isNotBlank(gasPlanReq.getGpName())){
            c.add(Restrictions.like("gpName","%"+gasPlanReq.getGpName()+"%"));
        }
        // 数据库根据条件过滤记录数
        c.addOrder(Order.desc("gpId"));
        int filterCount = this.countByCriteria(c);
        // 根据条件获取查询信息
        List<GasPlan> gasPalns = this.findBySortCriteria(c, gasPlanReq);

        // 返回结果
        return ResultUtil.pageResult( filterCount, gasPlanReq.getDraw(),gasPalns);
    }
}