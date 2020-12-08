package cc.dfsoft.project.biz.base.withgas.dao;

import cc.dfsoft.project.biz.base.withgas.dto.GasPlanReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasPlan;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;

/**
 * 
 * @author cui
 * @CreateTime 2017-8-8
 *
 */
public interface GasPlanDao extends CommonDao<GasPlan, String> {

    /**
     * 带气计划查询列表
     * @author cui
     * @CreateTime 2017-8-8
     * @param gasPlanReq
     * @return
     */
    Map<String,Object> queryGasPlan(GasPlanReq gasPlanReq);
}