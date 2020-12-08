package cc.dfsoft.project.biz.base.accept.dto;

/**
 * 立项右侧保存dto
 * @author pengtt
 *
 */
public class ProjectAcceptDto {
	
	private String projectFlag;//主对象标志
	private String custContact;
	private String custPhone;
	private String duName;
	private String projAddr;
	private String projId;
	private String projInfoFlag;
	private String projLtypeId;
	private String projName;
	private String projNo;
	private String projSubConSta;
	
	private String projStypeId;
	private String scaleId;		  //工程规模id
	private Integer houseNum;	  //户数（数量）
	private Integer searNum;	  //座数
	private Integer num;		  //台数
	private Integer tonnage;	  //吨位
	private Double gasConsumption;//预计用气量（Nm3/h）
	
	public String getCustContact() {
		return custContact;
	}
	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getDuName() {
		return duName;
	}
	public void setDuName(String duName) {
		this.duName = duName;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjInfoFlag() {
		return projInfoFlag;
	}
	public void setProjInfoFlag(String projInfoFlag) {
		this.projInfoFlag = projInfoFlag;
	}
	public String getProjLtypeId() {
		return projLtypeId;
	}
	public void setProjLtypeId(String projLtypeId) {
		this.projLtypeId = projLtypeId;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjSubConSta() {
		return projSubConSta;
	}
	public void setProjSubConSta(String projSubConSta) {
		this.projSubConSta = projSubConSta;
	}
	public String getScaleId() {
		return scaleId;
	}
	public void setScaleId(String scaleId) {
		this.scaleId = scaleId;
	}
	public Integer getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}
	public Integer getSearNum() {
		return searNum;
	}
	public void setSearNum(Integer searNum) {
		this.searNum = searNum;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getTonnage() {
		return tonnage;
	}
	public void setTonnage(Integer tonnage) {
		this.tonnage = tonnage;
	}
	public Double getGasConsumption() {
		return gasConsumption;
	}
	public void setGasConsumption(Double gasConsumption) {
		this.gasConsumption = gasConsumption;
	}
	public String getProjectFlag() {
		return projectFlag;
	}
	public void setProjectFlag(String projectFlag) {
		this.projectFlag = projectFlag;
	}
	public String getProjStypeId() {
		return projStypeId;
	}
	public void setProjStypeId(String projStypeId) {
		this.projStypeId = projStypeId;
	}
	
}
