package cc.dfsoft.project.biz.base.contract.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.dto.supplementalContractModifyHistoryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContractModifyhistory;
/**
 * 补充协议服务
 * @author Administrator
 *
 */
public interface SupplementalContractService {
	/**
	 * 保存补充协议
	 * @param contract
	 * @return
	 * @throws ParseException 
	 */
	public String saveSupplementalContract(SupplementalContract supplementalContract) throws Exception;
	/**
	 * 根据协议编号查协议
	 * @param conNo
	 * @return
	 */
	public List<SupplementalContract> findByScNo(String scNo);
	/**
	 * 协议列表条件查询
	 * @param supplementalContractQueryReq
	 * @return
	 */
	public Map<String, Object> querySupplementalContract(SupplementalContractQueryReq supplementalContractQueryReq) throws ParseException;

	/**
	 * 通过预算id查询补充协议
	 * @author fuliwei
	 * @createTime 2017-1-25
	 * @param budgetId
	 * @return SupplementalContract
	 */
	public SupplementalContract viewSupplementalContract(String budgetId);
	/**
	 * 根据合同Id
	 * @param conId
	 * @return
	 */
	public List<SupplementalContract> findByConID(String conId);
	/**
	 * 根据ID
	 * @param scID
	 */
	public SupplementalContract findByScId(String scId);
	/**
	 * 标记补充协议打印
	 * @param scId
	 * @return
	 */
	public String signSupplementalContract(String scId);
	/**
	 * 查询待审核的补充协议列表
	 * @throws ParseException 
	 */
	public Map<String,Object> querySupplementalforAudit(SupplementalContractQueryReq supplementalContract) throws ParseException;
	/**
	 * 根据工程ID查询补充协议
	 * @param projId
	 * @return
	 */
	public List<SupplementalContract> findByProjId(String projId);
	/**
	 * 查询已签补充协议列表
	 */
	public Map<String,Object> queryAgreementList(SupplementalContractQueryReq supplementalContract) throws ParseException;
	/**
	 * 根据协议ID查询补充协议详述
	 * wang.hui.jun
	 */
	public SupplementalContract searchSupplementalContract(String scId) throws Exception;
	/**
	 * wang.hui.jun
	 * 保存修改后的补充协议
	 */
	public String saveSupplementalAgreement(SupplementalContract supplementalContract) throws ParseException;
	/**
	 * 查询补充协议修改历史
	 * wang.hui.jun
	 */
	 public  Map<String, Object> queryModifyHistory(String scId,supplementalContractModifyHistoryReq modifyhistoryReq)throws Exception;
	 /**
	  * 查询补充协议审核历史
	  */
	public Map<String, Object> querySupplementalModify(SupplementalContractQueryReq suppleConReq) throws Exception;
}
