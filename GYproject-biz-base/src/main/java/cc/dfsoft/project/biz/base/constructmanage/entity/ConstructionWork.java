package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * ConstructionWork entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONSTRUCTION_WORK")
public class ConstructionWork implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3198336390815770623L;
	private String cwId;				//施工交底ID
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	
	private Date cwDate;				//交底日期
	private String constructionUnit;	//施工单位
	private String cuPm;				//施工单位项目经理
	private String cuPmPost;			//施工经理职务
	private String custName;			//建设单位
	private String duName;				//设计单位
	private String duDeputy;			//设计代表
	
	private String suName;				//监理单位
	private String suFieldJgj;			//现场监理
	private String ongcName;			//天然气公司
	private String ongcDeputy;			//天然气分公司甲方代表
	private String suCes;				//总监理工程师',
	private String cuName; 			    //分包单位
	
	private String longitude; 	        //经度
	private String latitude;		    //纬度
	private String projectType;           //工程类型
	private String cwContent;			//交底内容
	private String reviewContent;		//会审内容
	private String findQuestion;		//发现问题
	private String drawingNo;			//图纸编号
	private String designer;			//设计人员
	private String fieldPrincipal;		//现场代表
	private String designerPost;		//设计人员职务
	private String fieldRepresentPost;	//现场代表职务
	private String suFieldJgjPost;		//现场监理职务
	private String userSign;		//用户签字
	private String testLeaderSign;//施工班组签字
	private Integer delFlag;//删除标志
	private Integer isWorked;//是否具备开工条件



	private List<Signature> sign;		//签字相关数据
	
	private Integer version;			//版本控制

	private String signState;			//签字状态 null 或0 未完成，1已完成

	private String corpId;			   //分公司ID-只读
	private String hiddenConfig;			   //分公司ID-只读
	
	// Constructors
	/** default constructor */
	public ConstructionWork() {
	}

	// Property accessors
	@Id
	@Column(name = "CW_ID", unique = true,nullable = false)
	public String getCwId() {
		return this.cwId;
	}

	public void setCwId(String cwId) {
		this.cwId = cwId;
	}

	@Column(name = "PROJ_ID",nullable = false)
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO",nullable = false)
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "PROJ_NAME",nullable = false)
	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CW_DATE")
	public Date getCwDate() {
		return this.cwDate;
	}

	public void setCwDate(Date cwDate) {
		this.cwDate = cwDate;
	}
	@Column(name = "CONSTRUCTION_UNIT")
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	
	@Column(name = "CU_PM")
	public String getCuPm() {
		return  this.cuPm;
	}

	public void setCuPm(String cuPm)throws SerialException, SQLException {
		this.cuPm = cuPm;
	}

	@Column(name = "CUST_NAME", length = 50)
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "DU_NAME", length = 50)
	public String getDuName() {
		return this.duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
	}

	@Column(name = "DU_DEPUTY")
	public String getDuDeputy() {
		return this.duDeputy;
	}

	public void setDuDeputy(String duDeputy) throws SerialException, SQLException{
		this.duDeputy =  duDeputy;
	}
	@Column(name = "SU_CES")
	public String getSuCes() {
		return this.suCes;
	}

	public void setSuCes(String suCes) throws SerialException, SQLException{
		this.suCes = suCes;
	}

	@Column(name = "SU_NAME", length = 50)
	public String getSuName() {
		return this.suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "SU_FIELD_JGJ")
	public String getSuFieldJgj() {
		return this.suFieldJgj;
	}

	public void setSuFieldJgj(String suFieldJgj) throws SerialException, SQLException{
		this.suFieldJgj = suFieldJgj;
	}

	@Column(name = "ONGC_NAME", length = 50)
	public String getOngcName() {
		return this.ongcName;
	}

	public void setOngcName(String ongcName) {
		this.ongcName = ongcName;
	}

	@Column(name = "ONGC_DEPUTY")
	public String getOngcDeputy() {
		return this.ongcDeputy;
	}

	public void setOngcDeputy(String ongcDeputy) throws SerialException, SQLException{
		this.ongcDeputy =  ongcDeputy;
	}
	
	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}
	
	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name="CW_CONTENT")
	public String getCwContent() {
		return cwContent;
	}

	public void setCwContent(String cwContent) {
		this.cwContent = cwContent;
	}
	
	@Column(name="REVIEW_CONTENT")
	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	
	@Column(name="FIND_QUESTION")
	public String getFindQuestion() {
		return findQuestion;
	}

	public void setFindQuestion(String findQuestion) {
		this.findQuestion = findQuestion;
	}
	
	@Column(name="DRAWING_NO")
	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	@Column(name="FIELD_REPRESENT")
	public String getFieldPrincipal() {
		return fieldPrincipal;
	}

	public void setFieldPrincipal(String fieldPrincipal) {
		this.fieldPrincipal = fieldPrincipal;
	}
	@Column(name="DESIGNER")
	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	@Column(name="CU_PM_POST")
	public String getCuPmPost() {
		return cuPmPost;
	}

	public void setCuPmPost(String cuPmPost) {
		this.cuPmPost = cuPmPost;
	}

	@Column(name="DESIGNER_POST")
	public String getDesignerPost() {
		return designerPost;
	}

	public void setDesignerPost(String designerPost) {
		this.designerPost = designerPost;
	}

	@Column(name="FIELD_REPRESENT_POST")
	public String getFieldRepresentPost() {
		return fieldRepresentPost;
	}

	public void setFieldRepresentPost(String fieldRepresentPost) {
		this.fieldRepresentPost = fieldRepresentPost;
	}

	@Column(name="SU_FIELD_JGJ_POST")
	public String getSuFieldJgjPost() {
		return suFieldJgjPost;
	}

	public void setSuFieldJgjPost(String suFieldJgjPost) {
		this.suFieldJgjPost = suFieldJgjPost;
	}

	@Column(name="USER_SIGN")
	public String getUserSign() {
		return userSign;
	}

	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}

	@Column(name="LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name="LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Column(name="SIGN_STATE")
	public String getSignState() {
		return signState;
	}

	public void setSignState(String signState) {
		this.signState = signState;
	}


	@Column(name="TEST_LEADER_SIGN")
	public String getTestLeaderSign() {
		return testLeaderSign;
	}

	public void setTestLeaderSign(String testLeaderSign) {
		this.testLeaderSign = testLeaderSign;
	}

	@Column(name="DELFLAG")
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Transient
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Transient
	public String getHiddenConfig() {
		return hiddenConfig;
	}

	public void setHiddenConfig(String hiddenConfig) {
		this.hiddenConfig = hiddenConfig;
	}

	@Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Column(name="IS_WORKED")
	public Integer getIsWorked() {
		return isWorked;
	}

	public void setIsWorked(Integer isWorked) {
		this.isWorked = isWorked;
	}
	
	
}