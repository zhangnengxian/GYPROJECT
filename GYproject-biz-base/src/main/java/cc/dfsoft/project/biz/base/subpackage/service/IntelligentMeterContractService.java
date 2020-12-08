package cc.dfsoft.project.biz.base.subpackage.service;

import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;

import java.text.ParseException;
import java.util.Map;

/**
 * 描述:智能表合同业务接口
 * @author liaoyq
 * @createTime 2017年9月16日
 */
public interface IntelligentMeterContractService {

	/**
	 * 保存合同信息
	 * @param contract
	 * @return
	 * @throws Exception 
	 */
	String saveContract(IntelligentMeterContract contract) throws Exception;

	/**
	 * 根据工程ID查询合同信息
	 * @param id
	 * @return
	 */
	IntelligentMeterContract viewContractByProjId(String id);

	/**
	 * 查询智能表合同
	 * @param imcReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryContract(IntelligentMeterContractReq imcReq) throws ParseException;

	/**
	 * 根据工程ID修改合同打印信息
	 * @param projId
	 */
	void signImcPrint(String projId);

	/**
	 * 待签智能表合同工程列表查询
	 * @param imcReq
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> queryProjectImc(IntelligentMeterContractReq imcReq)
			throws ParseException;

	/**
	 * 修改智能表合同信息
	 * @param imcContract
	 * @return
	 */
    String modifyImcContract(IntelligentMeterContract imcContract);

	/**
	 * 合同列表条件查询仅用于智能表合同修改审核页面
	 * @param imcQueryReq
	 * @return
	 */
    Map<String,Object> queryContractforModifyAudit(IntelligentMeterContractReq imcQueryReq) throws ParseException;

    IntelligentMeterContract getImContract(String imcId);

    /**
     * 根据projId查智能表合同
     * @param projId
     * @return
     */
	IntelligentMeterContract findContractByprojId(String projId);
}
