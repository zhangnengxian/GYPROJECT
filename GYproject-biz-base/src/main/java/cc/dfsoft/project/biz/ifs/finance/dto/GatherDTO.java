package cc.dfsoft.project.biz.ifs.finance.dto;

/**
 * 收款接口DTO（receiptInfo）
 * @author duw
 *
 */
public class GatherDTO {

	//项目编号	
	private String pro_code;

	//客户名称	
	private String cust_code;

	//项目类型	
	private String project_type;

	//合同编号	
	private String contract_code;

	//收款金额	
	private String money;

	//收费员编号	
	private String oper_code;

	//收费员名称	
	private String oper_name;

	//收费部门名称	
	private String dept_name;

	//收费部门编号	
	private String dept_code;

	//单据日期-收款日期
	private String bill_date;
	
	//分公司编号	
	private String company_code;
	
	//收款性质
	private String receiptType;

	//备注	
	private String remark;
	//税率
	private String shuilv;
	//收款单ID
	private String bill_id;

	public String getPro_code() {
		return pro_code;
	}

	public void setPro_code(String pro_code) {
		this.pro_code = pro_code;
	}

	public String getCust_code() {
		return cust_code;
	}

	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getContract_code() {
		return contract_code;
	}

	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getOper_code() {
		return oper_code;
	}

	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBill_date() {
		return bill_date;
	}

	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public String getShuilv() {
		return shuilv;
	}

	public void setShuilv(String shuilv) {
		this.shuilv = shuilv;
	}

	public String getBill_id() {
		return bill_id;
	}

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}
	
}
