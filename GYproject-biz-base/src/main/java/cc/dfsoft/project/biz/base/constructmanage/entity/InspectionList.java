package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * InspectionList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INSPECTION_LIST")
@DynamicUpdate(true)
public class InspectionList implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8418628726090305472L;
	private String ilId; // 安全质量检查单ID
	private String projId; // 工程ID
	private String projNo; // 工程编号
	private String projName; // 工程名称
	private String projScaleDes; // 工程规模
	private String areaId; // 区域
	private String constructionDepartment; // 施工部门
	private String cuName;// 分包单位
	private String fieldPrincipal; // 现场负责人
	private Clob inspector; // 检查人
	private Clob examinee; // 受检人
	private String projAddr; // 工程地点
	private Double total; // 扣分合计
	private Date checkDate; // 检查日期
	private String remark; // 备注
	private String type; // 检查单类型
	/** 复查人签字 */
	private Clob reviewOfPeople;
	/** 复查情况以及问题 */
	private String reviewOfOpinion;
	private List<Signature> sign; // 签字相关数据

	private List<InspectionRecord> inspectionRecords;// 用于检查记录保存

	private String checkDepartment; // 检查部门
	// Constructors

	/** default constructor */
	public InspectionList() {
	}

	// Property accessors
	@Id
	@Column(name = "IL_ID", unique = true)
	public String getIlId() {
		return this.ilId;
	}

	public void setIlId(String ilId) {
		this.ilId = ilId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME")
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "PROJ_SCALE_DES")
	public String getProjScaleDes() {
		return this.projScaleDes;
	}

	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}

	@Column(name = "AREA_ID")
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "CONSTRUCTION_DEPARTMENT")
	public String getConstructionDepartment() {
		return this.constructionDepartment;
	}

	public void setConstructionDepartment(String constructionDepartment) {
		this.constructionDepartment = constructionDepartment;
	}

	@Column(name = "FIELD_PRINCIPAL")
	public String getFieldPrincipal() {
		return this.fieldPrincipal;
	}

	public void setFieldPrincipal(String fieldPrincipal) {
		this.fieldPrincipal = fieldPrincipal;
	}

	@Column(name = "INSPECTOR")
	public String getInspector() {
		return ClobUtil.ClobToString(this.inspector);
	}

	public void setInspector(String inspector) throws SerialException, SQLException {
		this.inspector = ClobUtil.stringToClob(inspector);
	}

	@Column(name = "EXAMINEE")
	public String getExaminee() {
		return ClobUtil.ClobToString(this.examinee);
	}

	public void setExaminee(String examinee) throws SerialException, SQLException {
		this.examinee = ClobUtil.stringToClob(examinee);
	}

	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return this.projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	@Column(name = "TOTAL")
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CHECK_DATE")
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public List<InspectionRecord> getInspectionRecords() {
		return inspectionRecords;
	}

	public void setInspectionRecords(List<InspectionRecord> inspectionRecords) {
		this.inspectionRecords = inspectionRecords;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSignature(List<Signature> sign) {
		this.sign = sign;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "CHECK_DEPARTMENT")
	public String getCheckDepartment() {
		return checkDepartment;
	}

	public void setCheckDepartment(String checkDepartment) {
		this.checkDepartment = checkDepartment;
	}

	@Column(name="REVIEW_OF_PEOPLE") 
	public String getReviewOfPeople() {
		return  ClobUtil.ClobToString(this.reviewOfPeople);
	}

	public void setReviewOfPeople(String reviewOfPeople) throws SerialException, SQLException {
		this.reviewOfPeople =  ClobUtil.stringToClob(reviewOfPeople);
	}

	@Column(name = "REVIEW_OF_OPINION")
	public String getReviewOfOpinion() {
		return reviewOfOpinion;
	}

	public void setReviewOfOpinion(String reviewOfOpinion) {
		this.reviewOfOpinion = reviewOfOpinion;
	}

	@Override
	public String toString() {
		return "InspectionList [ilId=" + ilId + ", projId=" + projId + ", projNo=" + projNo + ", projName=" + projName
				+ ", projScaleDes=" + projScaleDes + ", areaId=" + areaId + ", constructionDepartment="
				+ constructionDepartment + ", cuName=" + cuName + ", fieldPrincipal=" + fieldPrincipal + ", inspector="
				+ inspector + ", examinee=" + examinee + ", projAddr=" + projAddr + ", total=" + total + ", checkDate="
				+ checkDate + ", remark=" + remark + ", type=" + type + ", reviewOfPeople=" + reviewOfPeople
				+ ", reviewOfOpinion=" + reviewOfOpinion + ", sign=" + sign + ", inspectionRecords=" + inspectionRecords
				+ ", checkDepartment=" + checkDepartment + "]";
	}

	
}