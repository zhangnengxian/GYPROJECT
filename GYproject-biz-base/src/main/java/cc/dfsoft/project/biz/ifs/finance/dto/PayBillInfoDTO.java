package cc.dfsoft.project.biz.ifs.finance.dto;
/**
 * 
 * 描述:用友付款单接口传递信息类
 * @author liaoyq
 * @createTime 2017年11月17日
 */
public class PayBillInfoDTO {

	//付款单信息
	private PaymentDTO payInfo;
	//操作类型
	private String operate_type;
	
	public PaymentDTO getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(PaymentDTO payInfo) {
		this.payInfo = payInfo;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
}
