package cc.dfsoft.project.biz.base.contract.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;

import com.fr.third.v2.org.apache.xmlbeans.impl.jam.internal.elements.VoidClassImpl;

import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.contract.dto.ContractReviewDto;   
import cc.dfsoft.project.biz.base.contract.entity.ContractReview;

/**
 * 合同评审service接口
 * @author wanghuijun
 * 2019年7月19日
 *
 */
public interface ContractReviewService {
 
	/**
	 * 保存合同评审
	 * @param contractReview
	 * @return
	 * @throws Exception
	 */
	public String saveContractReview(ContractReview contractReview) throws Exception;
	
	/**
	 * 查询合同评审记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ContractReview contractReviewDetail(String id)throws Exception;
	
	/**
	 * 根据参数查询合同评审记录
	 * @param contractReviewReq
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> queryContractReview(ContractReviewDto contractReviewReq) throws Exception;
	
	/**
	 * 标记打印状态
	 * @param crId
	 * @return
	 * @throws Exception
	 */
	public String signPrint(String crId) throws Exception;
}
