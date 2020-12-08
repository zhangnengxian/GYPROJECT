package cc.dfsoft.project.biz.base.subpackage.service;

import java.text.ParseException;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;

/**
 * 费用申请工程清单
 * @author fuliwei
 *
 */
public interface FeeApplyListService {
	
	/**
	 * 查询费用申请工程列表
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryFeeApplyList(PaymentApplyReq paymentApplyReq) throws ParseException;
	
	
	/**
	 * 批量导入清单
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	public String batInsertFeeApplyList(JSONArray jsonArr,PaymentApplyReq req);
	
	/**
	 * 导入设计费
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	public String batInsertDesignFee(JSONArray jsonArr,PaymentApplyReq req);
	
	/**
	 * 导入监理费
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	public String batInsertSuFee(JSONArray jsonArr,PaymentApplyReq req);
	
	/**
	 * 导入检测费
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	public String batInsertCheckFee(JSONArray jsonArr,PaymentApplyReq req);
	
	/**
	 * 删除记录
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	public void deleteByPaId(String paId);
}
