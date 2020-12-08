package cc.dfsoft.project.biz.ifs.finance.dto;
/**
 * 安装合同DTO（constractInfo）
 * @author duw
 *
 */
public class ConstractInfoDTO {
	//合同协议号
	private String contract_code;

	//合同户数量
	private String total_num;

	//签订时间
	private String contract_date;

	//安装合同金额
	private String contract_money;
	
	//安装合同备注
	private String contract_remark;

	//税率
	private String shuilv1;
	
	//每户金额
	private String unit_money;

	public String getContract_code() {
		return contract_code;
	}

	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}

	public String getTotal_num() {
		return total_num;
	}

	public void setTotal_num(String total_num) {
		this.total_num = total_num;
	}

	public String getContract_date() {
		return contract_date;
	}

	public void setContract_date(String contract_date) {
		this.contract_date = contract_date;
	}

	public String getContract_money() {
		return contract_money;
	}

	public void setContract_money(String contract_money) {
		this.contract_money = contract_money;
	}
	
	public String getContract_remark() {
		return contract_remark;
	}

	public void setContract_remark(String contract_remark) {
		this.contract_remark = contract_remark;
	}

	public String getShuilv1() {
		return shuilv1;
	}

	public void setShuilv1(String shuilv1) {
		this.shuilv1 = shuilv1;
	}

	public String getUnit_money() {
		return unit_money;
	}

	public void setUnit_money(String unit_money) {
		this.unit_money = unit_money;
	}
	
}
