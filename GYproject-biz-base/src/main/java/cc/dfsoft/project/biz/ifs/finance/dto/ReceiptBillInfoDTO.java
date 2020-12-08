package cc.dfsoft.project.biz.ifs.finance.dto;
/**
 * 
 * 描述:用友接口收款单传递信息类
 * @author liaoyq
 * @createTime 2017年11月17日
 */
public class ReceiptBillInfoDTO {

	//收款单信息
	private GatherDTO receiptInfo;
	//操作类型
	private String operate_type;
	
	public GatherDTO getReceiptInfo() {
		return receiptInfo;
	}
	public void setReceiptInfo(GatherDTO receiptInfo) {
		this.receiptInfo = receiptInfo;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	
}
