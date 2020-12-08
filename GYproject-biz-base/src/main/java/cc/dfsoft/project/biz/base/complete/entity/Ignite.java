package cc.dfsoft.project.biz.base.complete.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */
@Entity
@Table(name = "IGNITE")
public class Ignite {
    private String igniteId;
    private String gprojId;
    private String projId;
    private String projName;
    private String projNo;
    private String projAddr;
    private String projScaleDes;
    private String projType;
    private String custName;
    private String igniteAddr;
    private String igniteNo;
    private String agent;
    private String agentPhone;
    private String households;
    private String doHouseholds;
    private String igniteHouseholds;
    private String cuName;
    private String businessLicense; //营业执照
    private String scNo;
    private String builder;
    private String remark;
    private String lister;
    private Clob builderSign;
    private Clob treasurer;
    private String treasurerView;
    private Clob manager;
    private Date managerDate;     //部门经理签发日期
    private String custServiceView;
    private Clob custServiceSign;
    private Clob custSign;
    private String isPrint;
    private String isPass;
    private String projLtypeId;
    private Date planGasDate;
    private String deptName;
    private String buTel;
    private String igniteContent;
    private boolean pushFlag;                   //推送标记-------
    private List<Signature> sign;				//签字相关数据----
    private Date planGasEndDate;				//通气计划结束时间
    private Date deliveryTime;					//交付时间
    
    private String stepId;						//步骤ID-只读
    private String uploadFlag;				    //文件上传-只读
    private Date acceptDate;					//验收日期-只读
    private String acceptType;					//验收类型-只读

    @Id
    @Column(name = "IGNITE_ID")
    public String getIgniteId() {
        return igniteId;
    }

    public void setIgniteId(String igniteId) {
        this.igniteId = igniteId;
    }
    @Basic
    @Column(name = "LISTER")
    public String getLister() {
		return lister;
	}

	public void setLister(String lister) {
		this.lister = lister;
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
    @Column(name = "PROJ_NAME")
    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
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
    @Column(name = "PROJ_SCALE_DES")
    public String getProjScaleDes() {
        return projScaleDes;
    }

    public void setProjScaleDes(String projScaleDes) {
        this.projScaleDes = projScaleDes;
    }

    @Basic
    @Column(name = "PROJ_TYPE")
    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    @Basic
    @Column(name = "CUST_NAME")
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "IGNITE_ADDR")
    public String getIgniteAddr() {
        return igniteAddr;
    }

    public void setIgniteAddr(String igniteAddr) {
        this.igniteAddr = igniteAddr;
    }

    @Basic
    @Column(name = "IGNITE_NO")
    public String getIgniteNo() {
        return igniteNo;
    }

    public void setIgniteNo(String igniteNo) {
        this.igniteNo = igniteNo;
    }

    @Basic
    @Column(name = "AGENT")
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Basic
    @Column(name = "AGENT_PHONE")
    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    @Basic
    @Column(name = "HOUSEHOLDS")
    public String getHouseholds() {
        return households;
    }

    public void setHouseholds(String households) {
        this.households = households;
    }

    @Basic
    @Column(name = "DO_HOUSEHOLDS")
    public String getDoHouseholds() {
        return doHouseholds;
    }

    public void setDoHouseholds(String doHouseholds) {
        this.doHouseholds = doHouseholds;
    }

    @Basic
    @Column(name = "IGNITE_HOUSEHOLDS")
    public String getIgniteHouseholds() {
        return igniteHouseholds;
    }

    public void setIgniteHouseholds(String igniteHouseholds) {
        this.igniteHouseholds = igniteHouseholds;
    }

    @Basic
    @Column(name = "CU_NAME")
    public String getCuName() {
        return cuName;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    
    @Column(name = "BUSINESS_LICENSE")
    public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
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
    @Column(name = "BUILDER")
    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    @Basic
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Basic
    @Column(name = "BUILDER_SIGN")
    public String getBuilderSign() {
        return ClobUtil.ClobToString(builderSign);
    }

    public void setBuilderSign(String builderSign) throws SQLException {
        this.builderSign = ClobUtil.stringToClob(builderSign);
    }

    @Basic
    @Column(name = "TREASURER")
    public String getTreasurer() {
        return ClobUtil.ClobToString(treasurer);
    }

    public void setTreasurer(String treasurer) throws SQLException {
        this.treasurer = ClobUtil.stringToClob(treasurer);
    }

    @Basic
    @Column(name = "TREASURER_VIEW")
    public String getTreasurerView() {
        return treasurerView;
    }

    public void setTreasurerView(String treasurerView) {
        this.treasurerView = treasurerView;
    }

    @Basic
    @Column(name = "MANAGER")
    public String getManager() {
        return ClobUtil.ClobToString(manager);
    }

    public void setManager(String manager) throws SQLException {
        this.manager = ClobUtil.stringToClob(manager);
    }

    @Basic
    @Column(name = "MANAGER_DATE")
    @Temporal(TemporalType.DATE)
    public Date getManagerDate() {
        return managerDate;
    }

    public void setManagerDate(Date managerDate) {
        this.managerDate = managerDate;
    }

    @Basic
    @Column(name = "CUST_SERVICE_VIEW")
    public String getCustServiceView() {
        return custServiceView;
    }

    public void setCustServiceView(String custServiceView) {
        this.custServiceView = custServiceView;
    }

    @Basic
    @Column(name = "CUST_SERVICE_SIGN")
    public String getCustServiceSign() {
        return ClobUtil.ClobToString(custServiceSign);
    }

    public void setCustServiceSign(String custServiceSign) throws SQLException {
        this.custServiceSign = ClobUtil.stringToClob(custServiceSign);
    }

    @Basic
    @Column(name = "CUST_SIGN")
    public String getCustSign() {
        return ClobUtil.ClobToString(custSign);
    }

    public void setCustSign(String custSign) throws SQLException {
        this.custSign = ClobUtil.stringToClob(custSign);
    }

    @Column(name = "IS_PRINT")
    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    @Column(name = "IS_PASS")
    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    @Column(name = "PROJ_LTYPE_ID")
    public String getProjLtypeId() {
        return projLtypeId;
    }

    public void setProjLtypeId(String projLtypeId) {
        this.projLtypeId = projLtypeId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PLAN_GAS_DATE")
    public Date getPlanGasDate() {
        return planGasDate;
    }

    public void setPlanGasDate(Date planGasDate) {
        this.planGasDate = planGasDate;
    }

    @Column(name = "GPROJ_ID")
    public String getGprojId() {
        return gprojId;
    }

    public void setGprojId(String gprojId) {
        this.gprojId = gprojId;
    }

    @Column(name = "DEPT_NAME")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Transient
    public boolean isPushFlag() {
        return pushFlag;
    }

    public void setPushFlag(boolean pushFlag) {
        this.pushFlag = pushFlag;
    }

    @Column(name = "BU_TEL")
    public String getBuTel() {
        return buTel;
    }

    public void setBuTel(String buTel) {
        this.buTel = buTel;
    }

    @Transient
    public List<Signature> getSign() {
        return sign;
    }

    public void setSign(List<Signature> sign) {
        this.sign = sign;
    }

    @Column(name = "IGNITE_CONTENT")
    public String getIgniteContent() {
        return igniteContent;
    }

    public void setIgniteContent(String igniteContent) {
        this.igniteContent = igniteContent;
    }
    @Temporal(TemporalType.DATE)
    @Transient
	public Date getPlanGasEndDate() {
		return planGasEndDate;
	}

	public void setPlanGasEndDate(Date planGasEndDate) {
		this.planGasEndDate = planGasEndDate;
	}
	@Column(name="DELIVERY_TIME")
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	@Transient
	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	@Transient
	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	@Temporal(TemporalType.DATE)
	@Transient
	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	@Transient
	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}
	
}
