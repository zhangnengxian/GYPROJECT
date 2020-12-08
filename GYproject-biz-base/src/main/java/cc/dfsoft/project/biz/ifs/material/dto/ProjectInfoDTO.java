package cc.dfsoft.project.biz.ifs.material.dto;
/**
 * 物资接口工程实体
 * @author fuliwei
 *
 */
public class ProjectInfoDTO {
	
	//项目id
	private String proj_id;
	
	//项目编号
	private String proj_no;

	//项目名称
	private String proj_name;

	//项目地址
	private String proj_addr;

	//施工合同号
	private String work_code;
	
	//客户编码
	private String cust_code;
	//变更/签证ID
	private String change_id;
	
	//施工单位编码-物料计划
	private String unit_code;
	private String unit_name;
	
	//施工单位-领料单
	private String construction_unit;
	
	//单据编号
	private String billno;
	//单据日期
	private String billdate;
	//公司编码
	private String org_code;
	//客户编码
	private String custcode;
	//操作员编码
	private String opercode;
	

	public String getProj_id() {
		return proj_id;
	}

	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}

	public String getProj_no() {
		return proj_no;
	}

	public void setProj_no(String proj_no) {
		this.proj_no = proj_no;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public String getProj_addr() {
		return proj_addr;
	}

	public void setProj_addr(String prj_addr) {
		this.proj_addr = prj_addr;
	}

	public String getWork_code() {
		return work_code;
	}

	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}

	public String getCust_code() {
		return cust_code;
	}

	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}

	public String getUnit_code() {
		return unit_code;
	}

	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getBilldate() {
		return billdate;
	}

	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getCustcode() {
		return custcode;
	}

	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	public String getOpercode() {
		return opercode;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}

	public String getConstruction_unit() {
		return construction_unit;
	}

	public void setConstruction_unit(String construction_unit) {
		this.construction_unit = construction_unit;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getChange_id() {
		return change_id;
	}

	public void setChange_id(String change_id) {
		this.change_id = change_id;
	}
}
