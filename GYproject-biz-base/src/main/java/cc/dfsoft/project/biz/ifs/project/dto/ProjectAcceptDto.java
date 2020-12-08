package cc.dfsoft.project.biz.ifs.project.dto;
/**
 * 工程报装辅助类
 * @author liaoyq
 * @createTime 2018年8月16日
 */
public class ProjectAcceptDto {
	private String cust_contact; //客户联系人
	private String cust_phone;   //客户联系人电话
	private String proj_addr;    //报装地址-工程地址
	private String cust_name;    //客户单位名称-如果没有则默认问联系人名称
	private String remark;		 //备注
	private String corp_code;	 //燃气公司编号
    private String cust_no;		 //客户号
    private String source_type;	//报装来源
	public String getCust_contact() {
		return cust_contact;
	}
	public void setCust_contact(String cust_contact) {
		this.cust_contact = cust_contact;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getProj_addr() {
		return proj_addr;
	}
	public void setProj_addr(String proj_addr) {
		this.proj_addr = proj_addr;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCorp_code() {
		return corp_code;
	}
	public void setCorp_code(String corp_code) {
		this.corp_code = corp_code;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getSource_type() {
		return source_type;
	}
	public void setSource_type(String source_type) {
		this.source_type = source_type;
	}
    
    
}
