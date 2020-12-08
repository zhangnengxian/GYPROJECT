package cc.dfsoft.project.biz.base.budget.entity;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
  * 工程费率
  */
@Entity
@Table(name="PROJECT_RATE")
public class ProjectRate  implements java.io.Serializable {


   
	private static final long serialVersionUID = 1L;
	// Fields
     private String rateId;     //主键id
     private BigDecimal inspection; //监检费率
     private BigDecimal supervisor; //监理费率
     private BigDecimal store;      //储备金费率


    // Constructors

    /** default constructor */
    public ProjectRate() {
    }

	/** minimal constructor */
    public ProjectRate(String rateId) {
        this.rateId = rateId;
    }
    
    /** full constructor */
    public ProjectRate(String rateId, BigDecimal inspection, BigDecimal supervisor, BigDecimal store) {
        this.rateId = rateId;
        this.inspection = inspection;
        this.supervisor = supervisor;
        this.store = store;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="RATE_ID", unique=true, nullable=false, length=6)

    public String getRateId() {
        return this.rateId;
    }
    
    public void setRateId(String rateId) {
        this.rateId = rateId;
    }
    
    @Column(name="INSPECTION")

    public BigDecimal getInspection() {
        return this.inspection;
    }
    
    public void setInspection(BigDecimal inspection) {
        this.inspection = inspection;
    }
    
    @Column(name="SUPERVISOR")

    public BigDecimal getSupervisor() {
        return this.supervisor;
    }
    
    public void setSupervisor(BigDecimal supervisor) {
        this.supervisor = supervisor;
    }
    
    @Column(name="STORE")

    public BigDecimal getStore() {
        return this.store;
    }
    
    public void setStore(BigDecimal store) {
        this.store = store;
    }
}