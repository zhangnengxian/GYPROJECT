package cc.dfsoft.project.biz.base.complete.dao;

import cc.dfsoft.project.biz.base.complete.dto.IgniteReq;
import cc.dfsoft.project.biz.base.complete.entity.Ignite;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IgniteDao extends CommonDao<Ignite, String>{

    /**
     * @auther cui on 2017-11-17
     * @param igniteReq
     * @return
     */
    Map<String,Object> queryIgnite(IgniteReq igniteReq) throws ParseException;

    Ignite viewByGprojId(String gprojId);

    /**
     * 根据工程ID查询点火单信息
     * @param projId
     * @return
     */
	List<Ignite> findDeliveryTimeByProjId(String projId);
}
