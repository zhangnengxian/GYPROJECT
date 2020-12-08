package cc.dfsoft.project.biz.base.subpackage.dao;

import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PaymentApply;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.text.ParseException;
import java.util.Map;
/**
 * 付款申请
 * @author fuliwei
 *
 */
public interface PaymentApplyDao extends CommonDao<PaymentApply,String>{
	
	/**
	 * 付款申请审核列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryPaymentApplyAudit(PaymentApplyReq req)throws ParseException ;

	/**
	 * 查询要生成的付款申请编号
	 * @return
	 */
    String getMaxApplyNo(String date,String projId);
    
    
    /**
	 * 查询预算员
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	public Map<String, Object> queryAuditer(DesignerQueryReq designerQueryReq);
	
	/**
	 * 生成费用申请编号
	 * @author fuliwei
	 * @createTime 2017年12月21日
	 * @param 
	 * @return
	 */
	public  String getMaxApplyNo(String date);
	/**
	 * 
	 * 
	 * @author wanghuijun
	 * @createTime 2018年9月4日
	 * @param paymentApplyReq
	 * @return
	 */
	public Map<String, Object>  getpaymentpplyList(PaymentApplyReq paymentApplyReq);
	
}
