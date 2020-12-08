package cc.dfsoft.project.biz.base.constructmanage.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * ConstructionOrganization entity.
 * 施工组织
 * @author cui
 */
@Entity
@Table(name = "CONSTRUCTION_ORGANIZATION")
public class ConstructionOrganization implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 4044285095011024012L;

	private String coId;			//施工组织表ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String projName;		//工程名称
	private String projAddr;		//工程地点
	
	private String coType;			//类型
	private String editor;			//编制人
	private Date editDate;			//编制日期
	
	private String checker;			//审核人
	private String bookNumber;		//册数
	private String pagesNumber;		//页数
	
	private String applicateDescription;	//申报简述
	private String applicant;				//申报人
	private String applicateDept;			//申报部门
	
	private String cuName;					//施工单位
	private String projectLeader;	   		//项目负责人
	private String technicalDept;			//技术部门
	private String techDeptAdvice;			//技术部门审批意见
	private String isAttachPages;			//有无附件
	private String techDeptChecker;			//技术部门审核人
	private Date techDeptCheckDate;			//技术部门审核日期
	
	private String suName;		    		//监理单位
	private String suAdvice;     			//监理公司审核意见
	private String	checkResult;			//审核结论
	private String suChecker;	    		//监理公司审核人
	private Date suCheckDate;				//监理单位审核日期
	private String suJgjSign; // 现场监理签字
    private Date suJgjSignDate;  // 现场监理签字日期
	private String custName;				//建设单位

	private String corpId;					//分公司ID
	private String corpName;				//分公司名称
	private String tenantId;				//租户ID

	private List<Signature> sign;			//签字相关数据

	private String drawName;				//附件名称
	//private String menuDes; 				//菜单描述
	private String drawUrl;         		//简图路径
	private Integer version;				//版本控制
	
	private String signState;				//签字状态 null 或0 未完成，1已完成
	private String reState;					//是否已重传标记 0-待重传，1-已重传
	
	private String cwSignState;				//是否完成会审交底标记
	private String accListId;				//附件id-只读
	
	// Constructors

	/** default constructor */
	public ConstructionOrganization() {
	}

	// Property accessors
	@Id
	@Column(name = "CO_ID")
	public String getCoId() {
		return this.coId;
	}

	public void setCoId(String coId) {
		this.coId = coId;
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
	
	@Column(name = "CU_NAME")
	public String getCuName() {
		return cuName;
	}

	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	@Column(name = "SU_NAME")
	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	@Column(name = "SU_ADVICE")
	public String getSuAdvice() {
		return suAdvice;
	}

	public void setSuAdvice(String suAdvice) {
		this.suAdvice = suAdvice;
	}

	@Column(name = "CUST_NAME")
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	@Column(name = "PROJ_ADDR")
	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	
	@Column(name="CO_TYPE")
	public String getCoType() {
		return coType;
	}

	public void setCoType(String coType) {
		this.coType = coType;
	}

	@Column(name="EDITOR")
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="EDIT_DATE")
	public Date getEditDate() {
		return editDate;
	}
	
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	@Column(name="CHECKER")
	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	@Column(name="BOOK_NUMBER")
	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	@Column(name="PAGES_NUMBER")
	public String getPagesNumber() {
		return pagesNumber;
	}

	public void setPagesNumber(String pagesNumber) {
		this.pagesNumber = pagesNumber;
	}

	@Column(name="APPLICATE_DESCRIPTION")
	public String getApplicateDescription() {
		return applicateDescription;
	}

	public void setApplicateDescription(String applicateDescription) {
		this.applicateDescription = applicateDescription;
	}

	@Column(name="APPLICANT")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	@Column(name="TECHNICAL_DEPT")
	public String getTechnicalDept() {
		return technicalDept;
	}

	public void setTechnicalDept(String technicalDept) {
		this.technicalDept = technicalDept;
	}

	@Column(name="TECH_DEPT_ADVICE")
	public String getTechDeptAdvice() {
		return techDeptAdvice;
	}

	public void setTechDeptAdvice(String techDeptAdvice) {
		this.techDeptAdvice = techDeptAdvice;
	}

	@Column(name="IS_ATTACH_PAGES")
	public String getIsAttachPages() {
		return isAttachPages;
	}

	public void setIsAttachPages(String isAttachPages) {
		this.isAttachPages = isAttachPages;
	}
	
	@Column(name="TECH_DEPT_CHECKER")
	public String getTechDeptChecker() {
		return techDeptChecker;
	}

	public void setTechDeptChecker(String techDeptChecker) {
		this.techDeptChecker = techDeptChecker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="TECH_DEPT_CHECK_DATE")
	public Date getTechDeptCheckDate() {
		return techDeptCheckDate;
	}

	public void setTechDeptCheckDate(Date techDeptCheckDate) {
		this.techDeptCheckDate = techDeptCheckDate;
	}

	@Column(name="CHECK_RESULT")
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	@Column(name="SU_CHECKER")
	public String getSuChecker() {
		return suChecker;
	}

	public void setSuChecker(String suChecker) {
		this.suChecker = suChecker;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="SU_CHECK_DATE")
	public Date getSuCheckDate() {
		return suCheckDate;
	}

	public void setSuCheckDate(Date suCheckDate) {
		this.suCheckDate = suCheckDate;
	}

	
	@Column(name="SUJGJ_SIGN")
	public String getSuJgjSign() {
		return suJgjSign;
	}

	public void setSuJgjSign(String suJgjSign) {
		this.suJgjSign = suJgjSign;
	}

	@Column(name = "SUJGJ_SIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getSuJgjSignDate() {
		return suJgjSignDate;
	}

	public void setSuJgjSignDate(Date suJgjSignDate) {
		this.suJgjSignDate = suJgjSignDate;
	}

	@Column(name="APPLICATE_DEPT")
	public String getApplicateDept() {
		return applicateDept;
	}

	public void setApplicateDept(String applicateDept) {
		this.applicateDept = applicateDept;
	}

	@Column(name="PROJECT_LEADER")
	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}

	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	@Column(name="CORP_NAME")
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Column(name="TENANT_ID")
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}

	public void setSignature(List<Signature> sign) {
		this.sign = sign;
	}

	@Column(name = "DRAW_NAME")
	public String getDrawName() {
		return drawName;
	}

	public void setDrawName(String drawName) {
		this.drawName = drawName;
	}

	@Transient
	public String getDrawUrl() {
		return drawUrl;
	}

	public void setDrawUrl(String drawUrl) {
		this.drawUrl = drawUrl;
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
	
	@Transient
	public String getCwSignState() {
		return cwSignState;
	}

	public void setCwSignState(String cwSignState) {
		this.cwSignState = cwSignState;
	}
	
	@Transient
	public String getAccListId() {
		return accListId;
	}

	public void setAccListId(String accListId) {
		this.accListId = accListId;
	}

	@Column(name="RE_STATE")
	public String getReState() {
		return reState;
	}

	public void setReState(String reState) {
		this.reState = reState;
	}
	
}