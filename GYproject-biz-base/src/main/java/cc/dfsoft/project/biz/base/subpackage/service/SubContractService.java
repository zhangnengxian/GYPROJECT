package cc.dfsoft.project.biz.base.subpackage.service;

import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.subpackage.dto.SubContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;

import java.text.ParseException;
import java.util.Map;

public interface SubContractService {
	/**
	 * 保存分包合同
	 * @author fuliwei
	 * @createTime 2016-7-5
	 * @param  subContract 分包合同
	 * @return String 
	 * @throws Exception 
	 */
	public String saveSubContract(SubContract subContract) throws Exception;
	/**
	 * 分包合同列表条件查询
	 * @param subContractQueryReq
	 * @return
	 */
	public Map<String,Object> querySubContract(SubContractQueryReq subContractQueryReq)throws ParseException;
	/**
	 * 分包合同列表条件查询(带时长)
	 * @param subContractQueryReq
	 * @return
	 */
	public Map<String,Object> querySubContractByTime(SubContractQueryReq subContractQueryReq)throws ParseException;
	/**
	 * 根据工程Id查分包合同
	 * @param projId
	 * @return
	 * @throws ParseException
	 */
	public SubContract findSubContractByprojId(String projId)throws ParseException;
	
	/**
	 * 根据业务ID查找分包合同
	 * @param scId
	 * @return
	 * @throws ParseException
	 */
	public SubContract findSubContractByScId(String scId) throws ParseException;
	/**
	 * 保存打印分包合同
	 * @param subContract
	 * @return
	 */
	public String saveSubContractPrint(SubContract subContract);
	
	/**
	 * 归档资料详述
	 * @param id
	 */
	GasApply jointDetail(String id);
	
	/**
	 * 通气确认
	 * @param 
	 */
	public String saveGas(SubContract subContract) throws ParseException;
	
	/**
	 * 合同列表条件查询仅用于分包合同审核页面
	 * @author ht.hu
	 * @param subAuditReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> querySubAudit(SubContractQueryReq subContractQueryReq) throws ParseException;

    void signSubContractPrint(String projId) throws ParseException;

	/**
	 * 施工合同页面详述根据工程Id查详述
	 * @author cui
	 * @createTime 2017-8-4
	 * @param id
	 * @return SubContract
	 */
    SubContract viewSubContractByProjId(String id);
    /**
     * 根据工程ID查询分包合同信息
     * 并返回打印报表路径参数
     * @param projId
     * @return
     * @throws ParseException 
     */
	public String findPrintDataByProjId(String projId,String type) throws ParseException;
	/**
	 * 修改施工合同
	 * @param subContract
	 * @return
	 */
	public String modifySubContract(SubContract subContract);
	/**
	 * 施工合同修改审核列表
	 * @param subContractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String, Object> querySubContractAudit(
			SubContractQueryReq subContractQueryReq) throws ParseException;
	/**
	 * 组装施工合同的计划开工竣工以及签订日期
	 * @author liaoyq
	 * @createTime 2018年5月4日
	 * @param scContract
	 * @param constructionPlan
	 * @return
	 */
	SubContract getScContractPlanDate(SubContract scContract,
			ConstructionPlan constructionPlan);

    boolean rollBackContainsSubContract(String projId,String rollBackReason);
}
