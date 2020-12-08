package cc.dfsoft.project.biz.base.withgas.service;

import cc.dfsoft.project.biz.base.withgas.dto.GasPlanReq;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;

import java.text.ParseException;
import java.util.Map;

/**
 * 带气计划服务接口
 * @author cui
 * @createTime 2017-08-8
 *
 */
public interface GasPlanService {

    /**
     * 查询带气工程列表
     * @param gasProjectReq
     * @return
     */
    Map<String,Object> queryGasProject(GasProjectReq gasProjectReq) throws ParseException;

    /**
     * 查询带气计划列表
     * @param gasPlanReq
     * @return
     */
    Map<String,Object> queryGasPlan(GasPlanReq gasPlanReq);

    /**
     * @author cui
     * @createTime 2017-08-8
     * 保存带气计划
     * @param gasPlanReq
     * @return
     */
    String saveGasPlan(GasPlanReq gasPlanReq);

    /**
     * 获取数据库时间
     * @return
     */
    java.util.Date getDatabaseDate();
}
