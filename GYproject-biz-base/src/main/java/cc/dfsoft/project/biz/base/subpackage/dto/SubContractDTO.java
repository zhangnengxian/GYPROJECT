package cc.dfsoft.project.biz.base.subpackage.dto;

import java.math.BigDecimal;
import java.util.Date;

public class SubContractDTO {
	
	
	private String scId;				//分包id
	private String scNo;				//分包合同编号
	private String projId;				//工程ID
	private String projNo;				//工程编号
	private String projName;			//工程名称
	private String projAddr;			//工程地点
	private String projScaleDes;		//工程规模
	
	 private String deptId;		        //甲方ID
    private String deptName;	        //甲方名称
    private String projCompDirector;	//甲方委托代理人
	private String projCompPm;			//甲方项目经理
	private String gasComLegalRepresent;//甲方代表
	private String gasComPhone;			//甲方联系电话
	
	private String cuId;				//分包单位id
	private String cuName;				//分包单位名称
	private String cuLegalRepresent;	//法定代表
	private String cuDirector;			//乙方委托代表

	private String cuPm;				//项目经理
	private String cuPmPhone;			//联系电话
	private String certificationName;	//资格证名称
	private String certificationNo;		//资格证编号
	private String scScope;				//承包范围
	private String scType;				//承包方式
	private Date scPlannedStartDate;	//计划开工日期
	private Date scPlannedEndDate;		//计划竣工日期
	private String scPlannedTotalDays;	//计划天数
	private String qualityStandar;		//质量标准
	private BigDecimal scAmount;		//协议价款
	private Date scSignDate;			//签订日期
	private String conAgent;			//经办人
	private String remark;				//备注
	private String payType; 			//支付方式
	
	private BigDecimal endDeclarationCost;	//终审金额
	
	private String ssId;					//分包安全协议ID 
	private String cuResponsiblePerson;		//乙方现场负责人
	private String signAddr;				//签订地址
	private String safeManager;				//安全管理员
	private String ssAccount;				//注册账号
	private Date ssSignDate;				//签订日期
	private String cuResponsiblePhone; 	//联系方式
	
	
	private String sscNo;//补充协议编号
	private Date sscPlannedStartDate;	//开工日期
	private Date sscPlannedEndDate;		//竣工日期
	private String sscPlannedTotalDays;//日历天数
	private String sscQualityStandar;	//质量标准
	private String sscaCompPm;		//甲方项目经理
	private String sscbCompPm;//乙方驻地代表
	private String sscaLegalRepresent;//甲方法定代表人
	private String sscbLegalRepresent;//乙方法定代表人
	private String sscaAddr;//甲方地址
	private String sscbAddr;//乙方地址
	private String sscaDirector;//甲方委托代表
	private String sscbDirector;//乙方委托代表
	private String sscaSoptLeader;//甲方现场负责人
	private String sscbSoptLeader;//乙方现场负责人
	private String sscaPhone;	  //甲方电话
	private String sscbPhone;		//乙方电话
	private String sscSignDate;		//签订日期
	
	
	
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjScaleDes() {
		return projScaleDes;
	}
	public void setProjScaleDes(String projScaleDes) {
		this.projScaleDes = projScaleDes;
	}
	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public BigDecimal getScAmount() {
		return scAmount;
	}
	public void setScAmount(BigDecimal scAmount) {
		this.scAmount = scAmount;
	}
	public String getConAgent() {
		return conAgent;
	}
	public void setConAgent(String conAgent) {
		this.conAgent = conAgent;
	}
	public String getCuPm() {
		return cuPm;
	}
	public void setCuPm(String cuPm) {
		this.cuPm = cuPm;
	}
	public String getCuPmPhone() {
		return cuPmPhone;
	}
	public void setCuPmPhone(String cuPmPhone) {
		this.cuPmPhone = cuPmPhone;
	}
	public String getCertificationName() {
		return certificationName;
	}
	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}
	public String getCertificationNo() {
		return certificationNo;
	}
	public void setCertificationNo(String certificationNo) {
		this.certificationNo = certificationNo;
	}
	public String getCuLegalRepresent() {
		return cuLegalRepresent;
	}
	public void setCuLegalRepresent(String cuLegalRepresent) {
		this.cuLegalRepresent = cuLegalRepresent;
	}
	public String getCuDirector() {
		return cuDirector;
	}
	public void setCuDirector(String cuDirector) {
		this.cuDirector = cuDirector;
	}
	public BigDecimal getEndDeclarationCost() {
		return endDeclarationCost;
	}
	public void setEndDeclarationCost(BigDecimal endDeclarationCost) {
		this.endDeclarationCost = endDeclarationCost;
	}
	public String getScId() {
		return scId;
	}
	public void setScId(String scId) {
		this.scId = scId;
	}
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getProjCompDirector() {
		return projCompDirector;
	}
	public void setProjCompDirector(String projCompDirector) {
		this.projCompDirector = projCompDirector;
	}
	public String getProjCompPm() {
		return projCompPm;
	}
	public void setProjCompPm(String projCompPm) {
		this.projCompPm = projCompPm;
	}
	public String getGasComLegalRepresent() {
		return gasComLegalRepresent;
	}
	public void setGasComLegalRepresent(String gasComLegalRepresent) {
		this.gasComLegalRepresent = gasComLegalRepresent;
	}
	public String getGasComPhone() {
		return gasComPhone;
	}
	public void setGasComPhone(String gasComPhone) {
		this.gasComPhone = gasComPhone;
	}
	public String getScScope() {
		return scScope;
	}
	public void setScScope(String scScope) {
		this.scScope = scScope;
	}
	public String getScType() {
		return scType;
	}
	public void setScType(String scType) {
		this.scType = scType;
	}
	public Date getScPlannedStartDate() {
		return scPlannedStartDate;
	}
	public void setScPlannedStartDate(Date scPlannedStartDate) {
		this.scPlannedStartDate = scPlannedStartDate;
	}
	public Date getScPlannedEndDate() {
		return scPlannedEndDate;
	}
	public void setScPlannedEndDate(Date scPlannedEndDate) {
		this.scPlannedEndDate = scPlannedEndDate;
	}
	public String getScPlannedTotalDays() {
		return scPlannedTotalDays;
	}
	public void setScPlannedTotalDays(String scPlannedTotalDays) {
		this.scPlannedTotalDays = scPlannedTotalDays;
	}
	public String getQualityStandar() {
		return qualityStandar;
	}
	public void setQualityStandar(String qualityStandar) {
		this.qualityStandar = qualityStandar;
	}
	public Date getScSignDate() {
		return scSignDate;
	}
	public void setScSignDate(Date scSignDate) {
		this.scSignDate = scSignDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getSsId() {
		return ssId;
	}
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}
	public String getCuResponsiblePerson() {
		return cuResponsiblePerson;
	}
	public void setCuResponsiblePerson(String cuResponsiblePerson) {
		this.cuResponsiblePerson = cuResponsiblePerson;
	}
	public String getSignAddr() {
		return signAddr;
	}
	public void setSignAddr(String signAddr) {
		this.signAddr = signAddr;
	}
	public String getSafeManager() {
		return safeManager;
	}
	public void setSafeManager(String safeManager) {
		this.safeManager = safeManager;
	}
	public String getSsAccount() {
		return ssAccount;
	}
	public void setSsAccount(String ssAccount) {
		this.ssAccount = ssAccount;
	}
	public Date getSsSignDate() {
		return ssSignDate;
	}
	public void setSsSignDate(Date ssSignDate) {
		this.ssSignDate = ssSignDate;
	}
	public String getCuResponsiblePhone() {
		return cuResponsiblePhone;
	}
	public void setCuResponsiblePhone(String cuResponsiblePhone) {
		this.cuResponsiblePhone = cuResponsiblePhone;
	}
	public String getSscNo() {
		return sscNo;
	}
	public void setSscNo(String sscNo) {
		this.sscNo = sscNo;
	}
	public Date getSscPlannedStartDate() {
		return sscPlannedStartDate;
	}
	public void setSscPlannedStartDate(Date sscPlannedStartDate) {
		this.sscPlannedStartDate = sscPlannedStartDate;
	}
	public Date getSscPlannedEndDate() {
		return sscPlannedEndDate;
	}
	public void setSscPlannedEndDate(Date sscPlannedEndDate) {
		this.sscPlannedEndDate = sscPlannedEndDate;
	}
	public String getSscPlannedTotalDays() {
		return sscPlannedTotalDays;
	}
	public void setSscPlannedTotalDays(String sscPlannedTotalDays) {
		this.sscPlannedTotalDays = sscPlannedTotalDays;
	}
	public String getSscQualityStandar() {
		return sscQualityStandar;
	}
	public void setSscQualityStandar(String sscQualityStandar) {
		this.sscQualityStandar = sscQualityStandar;
	}
	public String getSscaCompPm() {
		return sscaCompPm;
	}
	public void setSscaCompPm(String sscaCompPm) {
		this.sscaCompPm = sscaCompPm;
	}
	public String getSscbCompPm() {
		return sscbCompPm;
	}
	public void setSscbCompPm(String sscbCompPm) {
		this.sscbCompPm = sscbCompPm;
	}
	public String getSscaLegalRepresent() {
		return sscaLegalRepresent;
	}
	public void setSscaLegalRepresent(String sscaLegalRepresent) {
		this.sscaLegalRepresent = sscaLegalRepresent;
	}
	public String getSscbLegalRepresent() {
		return sscbLegalRepresent;
	}
	public void setSscbLegalRepresent(String sscbLegalRepresent) {
		this.sscbLegalRepresent = sscbLegalRepresent;
	}
	public String getSscaAddr() {
		return sscaAddr;
	}
	public void setSscaAddr(String sscaAddr) {
		this.sscaAddr = sscaAddr;
	}
	public String getSscbAddr() {
		return sscbAddr;
	}
	public void setSscbAddr(String sscbAddr) {
		this.sscbAddr = sscbAddr;
	}
	public String getSscaDirector() {
		return sscaDirector;
	}
	public void setSscaDirector(String sscaDirector) {
		this.sscaDirector = sscaDirector;
	}
	public String getSscbDirector() {
		return sscbDirector;
	}
	public void setSscbDirector(String sscbDirector) {
		this.sscbDirector = sscbDirector;
	}
	public String getSscaSoptLeader() {
		return sscaSoptLeader;
	}
	public void setSscaSoptLeader(String sscaSoptLeader) {
		this.sscaSoptLeader = sscaSoptLeader;
	}
	public String getSscbSoptLeader() {
		return sscbSoptLeader;
	}
	public void setSscbSoptLeader(String sscbSoptLeader) {
		this.sscbSoptLeader = sscbSoptLeader;
	}
	public String getSscaPhone() {
		return sscaPhone;
	}
	public void setSscaPhone(String sscaPhone) {
		this.sscaPhone = sscaPhone;
	}
	public String getSscbPhone() {
		return sscbPhone;
	}
	public void setSscbPhone(String sscbPhone) {
		this.sscbPhone = sscbPhone;
	}
	public String getSscSignDate() {
		return sscSignDate;
	}
	public void setSscSignDate(String sscSignDate) {
		this.sscSignDate = sscSignDate;
	}
	
    
}
