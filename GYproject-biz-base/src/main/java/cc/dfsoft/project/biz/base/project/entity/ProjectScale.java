package cc.dfsoft.project.biz.base.project.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * ProjectScale entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="PROJECT_SCALE"
)

public class ProjectScale  implements java.io.Serializable {


    // Fields

     private String psId;
     private String psDes;
     private ProjectType projType;
     private String projTypeId;
     private String scaleType;

    // Constructors

    /** default constructor */
    public ProjectScale() {
    }

	/** minimal constructor */
    public ProjectScale(String psId) {
        this.psId = psId;
    }
  
   
    // Property accessors
    @Id 
    @Column(name="PS_ID", unique=true, nullable=false)
    public String getPsId() {
        return this.psId;
    }
    
    public void setPsId(String psId) {
        this.psId = psId;
    }
    
    @Column(name="PS_DES")
    public String getPsDes() {
        return this.psDes;
    }
    
    public void setPsDes(String psDes) {
        this.psDes = psDes;
    }
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )  
    @JoinColumn(name="PROJ_TYPE_ID")
    public ProjectType getProjType() {
        return this.projType;
    }    
    public void setProjType(ProjectType projType) {
        this.projType = projType;
    }
    @Transient
	public String getProjTypeId() {
		return projTypeId;
	}

	public void setProjTypeId(String projTypeId) {
		this.projTypeId = projTypeId;
	}

	@Column(name="SCALE_TYPE")
	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}

}