package cc.dfsoft.project.biz.base.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * SystemSet entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="SYSTEM_SET"
)

public class SystemSet  implements java.io.Serializable {


    // Fields

     private String sysId;
     private String menuId;
     private String stepId;
     private String timeSpan;
     private String associateType;

     private String corpId;		//分公司id
    // Constructors

    /** default constructor */
    public SystemSet() {
    }

	/** minimal constructor */
    public SystemSet(String sysId) {
        this.sysId = sysId;
    }
    
    /** full constructor */
    public SystemSet(String sysId, String menuId, String stepId, String timeSpan, String associateType) {
        this.sysId = sysId;
        this.menuId = menuId;
        this.stepId = stepId;
        this.timeSpan = timeSpan;
        this.associateType = associateType;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="SYS_ID", unique=true, nullable=false, length=30)

    public String getSysId() {
        return this.sysId;
    }
    
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }
    
    @Column(name="MENU_ID")

    public String getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    @Column(name="STEP_ID")

    public String getStepId() {
        return this.stepId;
    }
    
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }
    
    @Column(name="TIME_SPAN")

    public String getTimeSpan() {
        return this.timeSpan;
    }
    
    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }
    
    @Column(name="ASSOCIATE_TYPE")
    public String getAssociateType() {
        return this.associateType;
    }
    
    public void setAssociateType(String associateType) {
        this.associateType = associateType;
    }
    
    @Column(name="CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
   








}