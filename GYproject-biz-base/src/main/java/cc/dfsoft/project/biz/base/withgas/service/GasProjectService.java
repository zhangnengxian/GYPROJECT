package cc.dfsoft.project.biz.base.withgas.service;

import cc.dfsoft.project.biz.base.project.entity.ManageRecord;
import cc.dfsoft.project.biz.base.withgas.dto.GasProjectReq;
import cc.dfsoft.project.biz.base.withgas.entity.GasProject;

import java.text.ParseException;
import java.util.Map;

/**
 * 带气计划服务接口
 * @author cui
 * @createTime 2017-08-8
 *
 */
public interface GasProjectService {

    /**
     * 通气方案审核页面
     * @author cui
     * @createTime 2017-8-8
     * @param gasProjectReq 查询条件dto
     * @return Map<String,Object>
     */
    Map<String,Object> queryGasAudit(GasProjectReq gasProjectReq) throws ParseException;

    /**
     * 根据id查详述
     * @author cui
     * @createTime 2017-8-8
     * @param gprojId 查询条件dto
     * @return Map<String,Object>
     */
    GasProject findById(String gprojId);

    /**
     * 审核记录保存
     * @createTime
     * @param manageRecord
     * @throws ParseException 
     */
    void auditPass(ManageRecord manageRecord) throws ParseException;

    /**
     * 保存通气工程
     * @author cui
     * @createTime 2017-8-8
     * @param  gasProject
     * @return String
     */
    String saveGasProject(GasProject gasProject);

    /**
     * 查询通气计划中待通气工程
     * @author cui
     * @createTime 2017-8-8
     * @param  gasProjectReq
     * @return String
     */
    Map<String,Object> queryGasProject(GasProjectReq gasProjectReq) throws ParseException;

    /**
     * 查询待通气工程
     * @author cui
     * @createTime 2017-10-20
     * @param  gasProjectReq
     * @return String
     */
    Map<String,Object> queryAllowGasProject(GasProjectReq gasProjectReq) throws ParseException;

    /**
     * 通气工程开通确认
     * @author cui
     * @createTime 2017-10-23
     * @param  gasProject
     * @return String
     */
    String enterGasProject(GasProject gasProject);

    /**
     * 点火单详述
     * @author cui
     * @createTime 2017-10-23
     * @param  gprojId
     * @return String
     */
    GasProject viewGasProject(String gprojId);
    
    
    /**
     * 登记通气时间
     * @author fuliwei  
     * @date 2018年9月14日  
     * @version 1.0
     */
    String saveGasDateRegister(GasProject gasProject);
    
    /**
     * 登记交付时间
     * @author fuliwei  
     * @date 2018年9月14日  
     * @version 1.0
     */
    String saveDeliverDateRegister(GasProject gasProject);
    
    /**
     * 通气单打印标记
     * @author fuliwei  
     * @date 2018年9月27日  
     * @version 1.0
     */
    public String signGasPrint(String gprojId);
}
