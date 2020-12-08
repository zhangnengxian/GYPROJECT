package cc.dfsoft.project.biz.base.common.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * MenuStep entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="MENU_STEP"
)

public class MenuStep  implements java.io.Serializable {


    // Fields

     private String meunId;
     private String stepId;


    // Constructors

    /** default constructor */
    public MenuStep() {
    }

    
    /** full constructor */
    public MenuStep(String meunId, String stepId) {
        this.meunId = meunId;
        this.stepId = stepId;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="MEUN_ID", unique=true, nullable=false)

    public String getMeunId() {
        return this.meunId;
    }
    
    public void setMeunId(String meunId) {
        this.meunId = meunId;
    }
    
    @Column(name="STEP_ID")

    public String getStepId() {
        return this.stepId;
    }
    
    public void setStepId(String stepId) {
        this.stepId = stepId;
    }
   








}