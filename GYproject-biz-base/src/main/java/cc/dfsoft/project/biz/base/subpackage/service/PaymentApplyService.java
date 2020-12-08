package cc.dfsoft.project.biz.base.subpackage.service;

import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;

import java.text.ParseException;
import java.util.Map;

/**
 * 
 * @author fuliwei
 *
 */
public interface PaymentApplyService {
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public PaymentApply findById(String projId,String paId)throws ParseException ;
	
	
	/**
	 *付款申请保存
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public void savePaymentApply(PaymentApply paymentApply);
	
	/**
	 * 付款申请列表
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryPaymentApply(PaymentApplyReq req)throws ParseException ;
	
	/**
	 * 付款申请审核列表
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryPaymentApplyAudit(PaymentApplyReq req)throws ParseException ;
	
	
	/**
	 * 推送到待审核
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public void pushPaymentApply(String paId);
	
	/**
	 * 根据主键id查询详述
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	public PaymentApply findById(String paId);
	
	/**
	 * 费用申请页面保存
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	public void saveCostPaymentApply(PaymentApplyReq paymentApplyReq);
	
	
	/**
	 * 保存审核派遣
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	public void saveCostPaymentDispatch(DesignDispatchDto designDispatchDto);
	
	/**
	 * 查询预算员
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryAuditer(DesignerQueryReq designerQueryReq);
	
	
	
	/**
	 * 推送待审核派遣
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	public void pushCostApply(String paId);
	/**
	 * @author wanghuijun
	 * @createTime 2018年9月4日
	 */
	public Map<String, Object>  getpaymentpplyMap(PaymentApplyReq paymentApplyReq) throws Exception;

    void saveUpdate(PaymentApply pa);
}
