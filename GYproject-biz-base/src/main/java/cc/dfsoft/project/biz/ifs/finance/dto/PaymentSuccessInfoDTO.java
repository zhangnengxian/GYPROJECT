package cc.dfsoft.project.biz.ifs.finance.dto;
/**
 * 
 * 描述:付款成功后，用友调用工程系统传递的参数类
 * @author liaoyq
 * @createTime 2017年11月9日
 */
public class PaymentSuccessInfoDTO {

	private String operate_type;	//操作类型
	private String pro_no;			//工程编号
	private String bill_id;			//实付款单ID
	
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public String getPro_no() {
		return pro_no;
	}
	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}
	public String getBill_id() {
		return bill_id;
	}
	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
}
