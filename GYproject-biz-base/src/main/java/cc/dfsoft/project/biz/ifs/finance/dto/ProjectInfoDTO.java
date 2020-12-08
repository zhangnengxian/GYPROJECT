package cc.dfsoft.project.biz.ifs.finance.dto;

import cc.dfsoft.project.biz.base.project.enums.AreaEnum;

/**
 * 工程项目基本信息DTO（projectInfo）
 * @author duw
 *
 */
public class ProjectInfoDTO {
	
	//项目编号
	private String pro_no;

	//项目类型
	private String project_type;

	//出资方式
	private String pay_model;

	//项目名称
	private String pro_name;

	//项目地址
	private String prj_adds;

	//项目状态 --
	private String pro_status;

	//分公司编号
	private String company_code;
	
	//地区编码
	private String area_code;
	
	//地区名称--
	private String area_name;
	
	//项目开始时间
	private String begin_date;
	
	//项目竣工时间
	private String end_date;
	
	/**银行信息*/
	
	//银行账号
	private String account_no;
	//账号名称
	private String account_name;
	//账户性质
	private String account_xz;
	//银行类别
	private String bank_lb;
	//开户银行
	private String account_bank;
	
	/**客户信息*/
	
	//客户名称
	private String cust_name;

	//客户编码
	private String cust_code;
	
	//法人名称
	private String legal;

	//企业地址
	private String company_adds;

	//客户基本分类
	private String cust_class;
	
	//电话
	private String tel;
	
	//设计单位
	private String design_unit;
	//设计单位编码
	private String design_code;
	//项目启用状态:true表示项目启用，false表示项目停用； 2019-7-15
	private boolean pro_enabled;

	public String getPro_no() {
		return pro_no;
	}

	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getPay_model() {
		return pay_model;
	}

	public void setPay_model(String pay_model) {
		this.pay_model = pay_model;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPrj_adds() {
		return prj_adds;
	}

	public void setPrj_adds(String prj_adds) {
		this.prj_adds = prj_adds;
	}

	public String getPro_status() {
		return pro_status;
	}

	public void setPro_status(String pro_status) {
		this.pro_status = pro_status;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getAccount_xz() {
		return account_xz;
	}

	public void setAccount_xz(String account_xz) {
		this.account_xz = account_xz;
	}

	public String getBank_lb() {
		return bank_lb;
	}

	public void setBank_lb(String bank_lb) {
		this.bank_lb = bank_lb;
	}

	public String getAccount_bank() {
		return account_bank;
	}

	public void setAccount_bank(String account_bank) {
		this.account_bank = account_bank;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_code() {
		return cust_code;
	}

	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getCompany_adds() {
		return company_adds;
	}

	public void setCompany_adds(String company_adds) {
		this.company_adds = company_adds;
	}

	public String getCust_class() {
		return cust_class;
	}

	public void setCust_class(String cust_class) {
		this.cust_class = cust_class;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDesign_unit() {
		return design_unit;
	}

	public void setDesign_unit(String design_unit) {
		this.design_unit = design_unit;
	}

	public String getDesign_code() {
		return design_code;
	}

	public void setDesign_code(String design_code) {
		this.design_code = design_code;
	}

	public boolean isPro_enabled() {
		return pro_enabled;
	}

	public void setPro_enabled(boolean pro_enabled) {
		this.pro_enabled = pro_enabled;
	}
	 
}
