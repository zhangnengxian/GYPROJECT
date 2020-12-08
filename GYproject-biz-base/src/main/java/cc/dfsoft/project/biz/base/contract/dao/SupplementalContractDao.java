package cc.dfsoft.project.biz.base.contract.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 补充协议Dao
 * @author Administrator
 *
 */
public interface SupplementalContractDao extends CommonDao<SupplementalContract, String>{

	/**
	 * 按合同编号查询
	 * @param scNo
	 * @return
	 */
	public List<SupplementalContract> findByScNo(String scNo);
	/**
	 * 根据变更记录Id和变更类型mcType
	 * @param cmId
	 * @param mcType
	 * @return
	 */
	public SupplementalContract findByCmId(String cmId,String mcType);
	/**
	 * 补充协议条件查询
	 * @param supplementalContractQueryReq
	 * @return
	 */
	public Map<String, Object> querySupplementalContract(SupplementalContractQueryReq supplementalContractQueryReq)throws ParseException;
	
	/**
	 * 通过预算id查询补充协议
	 * @author fuliwei
	 * @createTime 2017-1-25
	 * @param budgetId
	 * @return SupplementalContract
	 */
	public SupplementalContract viewSupplementalContract(String budgetId);
	/**
	 * 按合同Id查询
	 * @param conId
	 * @return
	 */
	public List<SupplementalContract> findByConID(String conId);
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
	 * 查询修改过的补充协议
	 */
	public Map<String, Object> querySupplementalModify(SupplementalContractQueryReq suppleConReq) throws Exception; 
	
}
