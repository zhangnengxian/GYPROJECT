package cc.dfsoft.project.biz.base.contract.dao;

import java.util.Map;

import cc.dfsoft.project.biz.base.contract.dto.ContractReviewDto;
import cc.dfsoft.project.biz.base.contract.entity.ContractReview;
import cc.dfsoft.uexpress.common.dao.CommonDao;   

/**
 * 合同评审dao层
 * @author Administrator
 *
 */
public interface ContractReviewDao extends CommonDao<ContractReview, String>{
	
	
	/**
	 * 根据参数查询合同评审记录
	 * @param contractReviewReq
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryContractReview(ContractReviewDto contractReviewReq) throws Exception;
	

}
