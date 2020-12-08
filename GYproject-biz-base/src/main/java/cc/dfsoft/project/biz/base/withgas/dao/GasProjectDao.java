package cc.dfsoft.project.biz.base.withgas.dao;

import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * 
 * @author cui
 * @CreateTime 2017-8-8
 *
 */
public interface GasProjectDao extends CommonDao<GasProject, String> {

    /**
     * @author cui
     * @CreateTime 2017-8-8
     * @param gasProjectReq
     * @return
     */
    Map<String,Object> queryGasProject(GasProjectReq gasProjectReq) throws ParseException;

    /**
     * 根据计划Id删除工程
     * @param gpId
     */
    void deleteByGpId(String gpId);

    /**
     * 根据工程Id查待气工程
     * @param
     */
    GasProject findByProjId(String projId,Date acceptDate);
}