package cc.dfsoft.project.biz.ifs.log.dao.impl;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.maintain.dto.WebServiceSetReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.ifs.log.dao.WebServiceSetDao;
import cc.dfsoft.project.biz.ifs.log.entity.WebServiceSet;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 接口【配置DAO
 * @author liaoyq
 * @createTime 2018年4月22日
 */
@Repository
public class WebServiceSetDaoImpl extends NewBaseDAO<WebServiceSet, String> implements WebServiceSetDao {

	@Override
	public List<WebServiceSet> queryIsOpenByNO(String serviceNo, String corpId) {
		 Criteria c = super.getCriteria();
	      
        if(StringUtils.isNotBlank(serviceNo)){
            c.add(Restrictions.eq("webServiceNo",serviceNo));
        }
        if(StringUtils.isNotBlank(corpId)){
        	c.add(Restrictions.eq("corpId",corpId));
        }
        c.add(Restrictions.eq("webServiceFlag", "1"));
        return this.findByCriteria(c);
	}


    @Override
    public WebServiceSet queryWebServiceSet(String interfaceNo, String corpId) {
        Criteria c = super.getCriteria();
        c.add(Restrictions.eq("webServiceNo",interfaceNo));
        c.add(Restrictions.eq("corpId",corpId));
        List<WebServiceSet> list = this.findByCriteria(c);
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }


    @Override
    public Map<String, Object> querywebserviceSetMap(WebServiceSetReq webServiceSetReq) {
        Criteria c = super.getCriteria();

        if (StringUtils.isNotBlank(webServiceSetReq.getWebServiceNo())) {
            c.add(Restrictions.like("webServiceNo",  webServiceSetReq.getWebServiceNo() + "%"));
        }
        if (StringUtils.isNotBlank(webServiceSetReq.getWebServiceName())) {
            c.add(Restrictions.like("webServiceName",  "%" + webServiceSetReq.getWebServiceName() + "%"));
        }
        if (StringUtils.isNotBlank(webServiceSetReq.getWebServiceFlag())) {
            c.add(Restrictions.eq("webServiceFlag",  webServiceSetReq.getWebServiceFlag()));
        }
        if (StringUtils.isNotBlank(webServiceSetReq.getCorpId())) {
            c.add(Restrictions.eq("corpId",  webServiceSetReq.getCorpId()));
        }

        // 数据库根据条件过滤记录数
        int filterCount = this.countByCriteria(c);
        // 根据条件获取查询信息
        List<WebServiceSet> webServiceSetList = this.findBySortCriteria(c, webServiceSetReq);

        return ResultUtil.pageResult(filterCount, webServiceSetReq.getDraw(),webServiceSetList);

    }
}
