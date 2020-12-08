package cc.dfsoft.project.biz.ifs.finance.dto;

/**
 * 付款信息DTO（payInfo）
 * @author duw
 *
 */
public class PaymentDTO {

	//项目编号	
	private String pro_no;

	//付款单ID	
	private String bill_id;

	//项目名称	
	private String prj_name;

	//施工合同编号	
	private String contract_code;

	//合同金额	
	private String contract_money;

	//施工日期-计划施工日期
	private String work_date;

	//施工金额	-付款金额
	private String work_money;
	
	//付款账号
	private String pay_bankno;
	
	//施工单位	-付款单位（施工单位、监理单位、检测单位、设计单位）
	private String work_unit;
	//施工单位编码
	private String work_unit_code;

	//付款性质	01-预付款，02-进度款，03-结算款，04-工程款
	private String pay_type;

	//付款部门	
	private String dept_code;
	
	//收款人员编号
	private String oper_code;
	
	//收款人员姓名
	private String oper_name;

	//税率	
	private String shuilv;
	
	//组织编码
	private String org_code;

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

	public String getPrj_name() {
		return prj_name;
	}

	public void setPrj_name(String prj_name) {
		this.prj_name = prj_name;
	}

	public String getContract_code() {
		return contract_code;
	}

	public void setContract_code(String contract_code) {
		this.contract_code = contract_code;
	}

	public String getContract_money() {
		return contract_money;
	}

	public void setContract_money(String contract_money) {
		this.contract_money = contract_money;
	}

	public String getWork_date() {
		return work_date;
	}

	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}

	public String getWork_money() {
		return work_money;
	}

	public void setWork_money(String work_money) {
		this.work_money = work_money;
	}

	public String getWork_unit() {
		return work_unit;
	}

	public void setWork_unit(String work_unit) {
		this.work_unit = work_unit;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getShuilv() {
		return shuilv;
	}

	public void setShuilv(String shuilv) {
		this.shuilv = shuilv;
	}

	public String getPay_bankno() {
		return pay_bankno;
	}

	public void setPay_bankno(String pay_bankno) {
		this.pay_bankno = pay_bankno;
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

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getWork_unit_code() {
		return work_unit_code;
	}

	public void setWork_unit_code(String work_unit_code) {
		this.work_unit_code = work_unit_code;
	}
	
}
