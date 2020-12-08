package cc.dfsoft.project.biz.base.complete.dao.impl;

import cc.dfsoft.project.biz.base.complete.dao.IgniteDao;
import cc.dfsoft.project.biz.base.complete.dto.IgniteReq;
import cc.dfsoft.project.biz.base.complete.entity.Ignite;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class IgniteDaoImpl extends NewBaseDAO<Ignite, String> implements IgniteDao {

    @Override
    public Map<String, Object> queryIgnite(IgniteReq igniteReq) throws ParseException {
        Criteria c = super.getCriteria();
        //工程编号
        if(StringUtils.isNotBlank(igniteReq.getProjNo())){
            c.add(Restrictions.eq("projNo",igniteReq.getProjNo()));
        }
        //工程名称
        if(StringUtils.isNotBlank(igniteReq.getProjName())){
            c.add(Restrictions.like("projName", "%"+igniteReq.getProjName()+"%"));
        }
        //工程地点
        if(StringUtils.isNotBlank(igniteReq.getProjAddr())){
            c.add(Restrictions.like("projAddr", "%"+igniteReq.getProjAddr()+"%"));
        }

        //是否打印
        if(StringUtils.isNotBlank(igniteReq.getIsPrint())){
            c.add(Restrictions.eq("isPrint", igniteReq.getIsPrint()));
        }
        //用于字符串日期格式转换
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //签发日期开始
        if(StringUtils.isNotBlank(igniteReq.getManagerDateStart())){
            c.add(Restrictions.ge("managerDate", sdf.parse(igniteReq.getManagerDateStart())));
        }
        //签发日期结束
        if(StringUtils.isNotBlank(igniteReq.getManagerDateEnd())){
            c.add(Restrictions.le("managerDate", sdf.parse(igniteReq.getManagerDateEnd())));
        }
        //根据公司Id查询工程
        if(StringUtils.isNotBlank(SessionUtil.getLoginInfo().getCorpId())){
			 StringBuffer sql = new StringBuffer("proj_id in(select p.proj_id from project p where p.corp_id='").append(SessionUtil.getLoginInfo().getCorpId()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
        }
        // 数据库根据条件过滤记录数
        int filterCount = this.countByCriteria(c);

        // 根据条件获取查询信息
        List<Ignite> ignites = this.findBySortCriteria(c, igniteReq);

        // 返回结果
        return ResultUtil.pageResult( filterCount, igniteReq.getDraw(),ignites);
    }

    @Override
    public Ignite viewByGprojId(String gprojId) {
        Criteria c = super.getCriteria();
        //工程编号
        if(StringUtils.isNotBlank(gprojId)){
            c.add(Restrictions.eq("gprojId",gprojId));
        }
        // 根据条件获取查询信息
        List<Ignite> ignites = this.findByCriteria(c);
        if(ignites!=null&&ignites.size()>0){
            return ignites.get(0);
        }
        return null;
    }

	@Override
	public List<Ignite> findDeliveryTimeByProjId(String projId) {
		Criteria c = super.getCriteria();
        //工程编号
        if(StringUtils.isNotBlank(projId)){
            c.add(Restrictions.eq("projId",projId));
        }
        c.addOrder(Order.desc("deliveryTime"));
        // 根据条件获取查询信息
        return this.findByCriteria(c);
	}
}
