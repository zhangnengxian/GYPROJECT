package cc.dfsoft.project.biz.base.withgas.entity;

import javax.persistence.*;

import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;

import java.math.BigDecimal;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */
@Entity
@Table(name = "gas_project")
public class GasProject {
    private String gprojId;
    private String gpId;
    private String projId;
    private String projNo;
    private String projAddr;
    private String projName;
    private String projScaleDes;
    private String projLtypeId;
    private String deptName;
    private String cuName;
    private String scNo;
    private String gasComLegalRepresent;
    private String preparer;
    private Date preparDate;
    private String preparDept;
    private Date planGasDate;
    private String pipeMaterial;
    private String pipeSize;
    private String pipeRating;
    private String gasContent;
    private String gasRemark;
    private String custName;
    private String custAddr;// 用户地址
    private String custContcat;  //联系人
    private String custPhone;  //联系电话
    private BigDecimal contractAmount;  //签约金额
    private String installationNumber; //安装户数
    private String settlementAmount;  //已结算金额
    private String jaClum;

    private String gasProjStatus;

    private String mrAuditLevel; 		//已审核级别----只读属性
    private String level;        		//几级审核------只读属性
    private String projectTypeDes;  	//工程类型
    private String gasProjStatusDes;    //状态描述
    private String preparerDes;
    private String preparDeptDes;
    
    private String projectType;          //工程类型-----

    private String isBack;               //是否退回
    private Date deliveryTime;         	 //交付时间
    private String reason;               //原因
    private Date acceptDate;             //验收时间

    private String builder;              //----点火页面显示
    private String households;           //----点火页面显示
    private String conNo;                //----点火页面显示

    private Date planGasEndDate;		 //计划通气结束时间
    

    private String acceptId;             //验收id------
    private String acceptType;           //验收类型
    private String isStepDown;			 //是否降压：0-否，1-是
    private String isSignIgnite;		 //是否签订点火单 0 未完成 1 已完成
    
    private String isSpecialSign;		//是否特殊工程 1 是 0 否

	private String isHaveCM;			//未完成补充协议的变更数
	
	private String corpId;				//分公司id
	private String gasPoint;			//带气点数
	private String corpBack;			//分公司是否退回 0表示退回
	private String isPrint;				//打印标记     0 已打印,1未打印
	private String applyerId;			//开通计划申请人ID
	private String applyer;				//开通计划申请人
	
	
    @Transient
    public String getIsSpecialSign() {
		return isSpecialSign;
	}

	public void setIsSpecialSign(String isSpecialSign) {
		this.isSpecialSign = isSpecialSign;
	}

	@Temporal(TemporalType.DATE)
    @Column(name = "ACCEPT_DATE")
    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    @Column(name = "IS_BACK")
    public String getIsBack() {
        return isBack;
    }

    public void setIsBack(String isBack) {
        this.isBack = isBack;
    }
    
   
    @Temporal(TemporalType.DATE)
    @Column(name = "DELIVERY_TIME")
    public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	 @Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Transient
    public String getGasProjStatusDes() {
        return gasProjStatusDes;
    }

    public void setGasProjStatusDes(String gasProjStatusDes) {
        this.gasProjStatusDes = gasProjStatusDes;
    }

    @Transient
    public String getPreparerDes() {
        return preparerDes;
    }

    public void setPreparerDes(String preparerDes) {
        this.preparerDes = preparerDes;
    }

    @Transient
    public String getPreparDeptDes() {
        return preparDeptDes;
    }

    public void setPreparDeptDes(String preparDeptDes) {
        this.preparDeptDes = preparDeptDes;
    }

    @Transient
    public String getProjectTypeDes() {
        return projectTypeDes;
    }

    public void setProjectTypeDes(String projectTypeDes) {
        this.projectTypeDes = projectTypeDes;
    }

    @Id
    @Column(name = "GPROJ_ID")
    public String getGprojId() {
        return gprojId;
    }

    public void setGprojId(String gprojId) {
        this.gprojId = gprojId;
    }

    @Basic
    @Column(name = "GP_ID")
    public String getGpId() {
        return gpId;
    }

    public void setGpId(String gpId) {
        this.gpId = gpId;
    }

    @Basic
    @Column(name = "PROJ_ID")
    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    @Basic
    @Column(name = "PROJ_NO")
    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    @Basic
    @Column(name = "PROJ_ADDR")
    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    @Basic
    @Column(name = "PROJ_NAME")
    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    @Basic
    @Column(name = "PROJ_SCALE_DES")
    public String getProjScaleDes() {
        return projScaleDes;
    }

    public void setProjScaleDes(String projScaleDes) {
        this.projScaleDes = projScaleDes;
    }

    @Basic
    @Column(name = "PROJ_LTYPE_ID")
    public String getProjLtypeId() {
        return projLtypeId;
    }

    public void setProjLtypeId(String projLtypeId) {
        this.projLtypeId = projLtypeId;
    }

    @Basic
    @Column(name = "DEPT_NAME")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "CU_NAME")
    public String getCuName() {
        return cuName;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    @Basic
    @Column(name = "SC_NO")
    public String getScNo() {
        return scNo;
    }

    public void setScNo(String scNo) {
        this.scNo = scNo;
    }

    @Basic
    @Column(name = "GAS_COM_LEGAL_REPRESENT")
    public String getGasComLegalRepresent() {
        return gasComLegalRepresent;
    }

    public void setGasComLegalRepresent(String gasComLegalRepresent) {
        this.gasComLegalRepresent = gasComLegalRepresent;
    }

    @Basic
    @Column(name = "PREPARER")
    public String getPreparer() {
        return preparer;
    }

    public void setPreparer(String preparer) {
        this.preparer = preparer;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "PREPAR_DATE")
    public Date getPreparDate() {
        return preparDate;
    }

    public void setPreparDate(Date preparDate) {
        this.preparDate = preparDate;
    }

    @Basic
    @Column(name = "PREPAR_DEPT")
    public String getPreparDept() {
        return preparDept;
    }

    public void setPreparDept(String preparDept) {
        this.preparDept = preparDept;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PLAN_GAS_DATE")
    public Date getPlanGasDate() {
        return planGasDate;
    }

    public void setPlanGasDate(Date planGasDate) {
        this.planGasDate = planGasDate;
    }

    @Basic
    @Column(name = "PIPE_MATERIAL")
    public String getPipeMaterial() {
        return pipeMaterial;
    }

    public void setPipeMaterial(String pipeMaterial) {
        this.pipeMaterial = pipeMaterial;
    }

    @Basic
    @Column(name = "PIPE_SIZE")
    public String getPipeSize() {
        return pipeSize;
    }

    public void setPipeSize(String pipeSize) {
        this.pipeSize = pipeSize;
    }

    @Basic
    @Column(name = "PIPE_RATING")
    public String getPipeRating() {
        return pipeRating;
    }

    public void setPipeRating(String pipeRating) {
        this.pipeRating = pipeRating;
    }

    @Basic
    @Column(name = "GAS_CONTENT")
    public String getGasContent() {
        return gasContent;
    }

    public void setGasContent(String gasContent) {
        this.gasContent = gasContent;
    }

    @Basic
    @Column(name = "GAS_REMARK")
    public String getGasRemark() {
        return gasRemark;
    }

    public void setGasRemark(String gasRemark) {
        this.gasRemark = gasRemark;
    }

    @Basic
    @Column(name = "CUST_NAME")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    
    
    
    @Column(name = "CUST_ADDR")
    public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	 @Column(name = "CUST_CONTCAT")
	public String getCustContcat() {
		return custContcat;
	}

	public void setCustContcat(String custContcat) {
		this.custContcat = custContcat;
	}

	@Column(name = "CUST_PHONE")
	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	
	@Column(name = "CONTRACT_AMOUNT")
	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	
	@Column(name = "INSTALLATION_NUMBER")
	public String getInstallationNumber() {
		return installationNumber;
	}

	public void setInstallationNumber(String installationNumber) {
		this.installationNumber = installationNumber;
	}

	
	@Column(name = "SETTLEMENT_MOUNT")
	public String getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(String settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	@Basic
    @Column(name = "JA_CLUM")
    public String getJaClum() {
        return jaClum;
    }

    public void setJaClum(String jaClum) {
        this.jaClum = jaClum;
    }

    @Basic
    @Column(name = "GAS_PROJ_STATUS")
    public String getGasProjStatus() {
        return gasProjStatus;
    }

    public void setGasProjStatus(String gasProjStatus) {
        this.gasProjStatus = gasProjStatus;
    }

    @Transient
    public String getMrAuditLevel() {
        return mrAuditLevel;
    }

    public void setMrAuditLevel(String mrAuditLevel) {
        this.mrAuditLevel = mrAuditLevel;
    }

    @Transient
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    @Transient
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

    @Transient
    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    @Transient
    public String getHouseholds() {
        return households;
    }

    public void setHouseholds(String households) {
        this.households = households;
    }

    @Transient
    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PLAN_GAS_END_DATE")
	public Date getPlanGasEndDate() {
		return planGasEndDate;
	}

	public void setPlanGasEndDate(Date planGasEndDate) {
		this.planGasEndDate = planGasEndDate;
	}

    @Transient
    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    @Column(name = "ACCEPT_TYPE")
    public String getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(String acceptType) {
        this.acceptType = acceptType;
    }

    @Column(name="IS_STEP_DOWN")
	public String getIsStepDown() {
		return isStepDown;
	}

	public void setIsStepDown(String isStepDown) {
		this.isStepDown = isStepDown;
	}
	
	@Column(name="IS_SIGN_IGNITE")
	public String getIsSignIgnite() {
		return isSignIgnite;
	}

	public void setIsSignIgnite(String isSignIgnite) {
		this.isSignIgnite = isSignIgnite;
	}

	@Transient
	public String getIsHaveCM() {
		return isHaveCM;
	}

	public void setIsHaveCM(String isHaveCM) {
		this.isHaveCM = isHaveCM;
	}
	@Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	
	@Column(name="GAS_POINT")
	public String getGasPoint() {
		return gasPoint;
	}

	public void setGasPoint(String gasPoint) {
		this.gasPoint = gasPoint;
	}
	
	@Column(name="CORP_BACK")
	public String getCorpBack() {
		return corpBack;
	}

	public void setCorpBack(String corpBack) {
		this.corpBack = corpBack;
	}
	
	@Column(name="IS_PRINT")
	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	@Column(name="APPLYER_ID")
	public String getApplyerId() {
		return applyerId;
	}

	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
	}

	@Column(name="APPLYER")
	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}
    
	
}
