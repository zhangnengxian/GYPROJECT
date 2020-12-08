package cc.dfsoft.project.biz.base.subpackage.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.FeeApplyList;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 费用申请工程清单
 * @author fuliwei
 *
 */
public interface FeeApplyListDao extends CommonDao<FeeApplyList,String>{
	
	/**
	 * 查询费用申请工程列表
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryFeeApplyList(PaymentApplyReq paymentApplyReq) throws ParseException;
	
	
	/**
	 * 根据申请单id删除申请清单
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
    public void deleteByPaId(String paId);
    
    
    /***
     * 付款清单
     * @author fuliwei
     * @createTime 2017年12月25日
     * @param 
     * @return
     */
    public List<FeeApplyList> findByPaId(String paId); 
}
