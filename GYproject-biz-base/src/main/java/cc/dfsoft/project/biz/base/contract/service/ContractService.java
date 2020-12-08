package cc.dfsoft.project.biz.base.contract.service;

import cc.dfsoft.project.biz.base.contract.dto.ContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.project.entity.Project;

import java.text.ParseException;
import java.util.Map;

/**
 * 合同服务接口
 * @author fuliwei
 * @createTime 2016-06-28
 */
public interface ContractService {
	
	/**
	 * 保存合同
	 * @author fuliwei
	 * @createTime 2016-06-27
	 * @param Contract合同
	 * @return
	 */
	public String saveContract(Contract contract) throws Exception;
	/**
	 * 保存合同(只保存物资登记和物资登记备注)
	 * @author wmy
	 * @createTime 2016-06-27
	 * @param Contract合同
	 * @return
	 */
	public String saveMaterialContract(Contract contract);
	
	
	/**
	 * 根据工程编号查询合同
	 * @author 张萌
	 * @param 工程编号
	 * @return
	 */
	public Contract viewContractByprojId(String id);
	
	/**
	 * 合同id获取合同对象
	 * @author pengtt
	 * @createTime 2016-07-11
	 * @param id
	 * @return
	 */
	public Contract getContract(String id);
	
	/**
	 * 合同列表条件查询
	 * @param contractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> queryContract(ContractQueryReq contractQueryReq) throws ParseException;
	/**
	 * 已经审核通过合同列表条件查询
	 * @author wmy
	 * @param contractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> queryPassContract(ContractQueryReq contractQueryReq) throws ParseException;
	/**
	 * 合同列表条件查询仅用于合同审核页面
	 * @param contractQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> queryContractforAudit(ContractQueryReq contractQueryReq) throws ParseException;
	/**
	 * 合同列表条件查询仅用于合同打印页面
	 * @param contractQueryReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> queryContractPrint(ContractQueryReq contractQueryReq)throws ParseException;
	
	/**
	 * 保存打印合同
	 * @author fuliwei
	 * @createTime 2016-07-22
	 * @param Contract合同
	 * @return
	 */
	public String saveContractPrint(Contract contract);
	/**
	 * 根据工程ID产生合同尾款
	 * @param project
	 * @return
	 */
	public void createRetainage(Project project);

	/**
	 * 根据工程Id查合同 仅用于施工合同
	 * @param projId
	 * @return
	 */
	public Contract findByProjId(String projId);
	
	/**
	 * 合同打印标记
	 * @createTime 2016-12-30
	 * @param  String projId
	 * @return String 
	 */
	public String signContractPrint(String projId);

	/**
	 * 合同修改保存
	 * @param contract
	 * @return
	 * @throws Exception 
	 */
	public String modifyContract(Contract contract) throws Exception;

	/**
	 * 合同列表条件查询仅用于合同修改审核页面
	 * @param contractQueryReq
	 * @return
	 */
	public Map<String, Object> queryContractforModifyAudit(ContractQueryReq contractQueryReq)throws ParseException;

	/**
	 * 根据工程ID查询安装合同信息，并返回打印报表路径及参数
	 * @param projId
	 * @return String
	 */
	public String findPrintDataByProjId(String projId);

	/**
	 * 确认收费
	 * @author fuliwei
	 * @date 2019/5/22
	*/
	public String saveChargeConfirm(Contract contract) throws Exception;

    boolean rollBackContainsContract(String projId,String rollBackReason);
}
