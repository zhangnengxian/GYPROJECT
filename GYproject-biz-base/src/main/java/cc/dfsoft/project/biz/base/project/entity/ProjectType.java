package cc.dfsoft.project.biz.base.project.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ProjectType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="PROJECT_TYPE"
)

public class ProjectType  implements java.io.Serializable {


	public static String PROJECTTYPE_RESIDENT = "11";//居民户工程类型id
	
    // Fields

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projTypeId;		//主键id
    private String projTypeDes;		//工程类型
    private String projConstructDes;//工程建设类型
    private String contractType;	//合同类型
    private String contractTypeDes;	//合同类型描述
    
    private String scaleType;		//规模大小
    // Constructors

    /** default constructor */
    public ProjectType() {
    }
   
    // Property accessors
    @Id 
    
    @Column(name="PROJ_TYPE_ID", unique=true, nullable=false)

    public String getProjTypeId() {
        return this.projTypeId;
    }
    
    public void setProjTypeId(String projTypeId) {
        this.projTypeId = projTypeId;
    }
    @Column(name="PROJ_TYPE_DES")
    public String getProjTypeDes() {
        return this.projTypeDes;
    }
    
    public void setProjTypeDes(String projTypeDes) {
        this.projTypeDes = projTypeDes;
    }
    
    @Column(name="PROJ_CONSTRUCT_DES")
	public String getProjConstructDes() {
		return projConstructDes;
	}

	public void setProjConstructDes(String projConstructDes) {
		this.projConstructDes = projConstructDes;
	}

	@Column(name="CONTRACT_TYPE")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Column(name="CONTRACT_TYPE_DES")
	public String getContractTypeDes() {
		return contractTypeDes;
	}

	public void setContractTypeDes(String contractTypeDes) {
		this.contractTypeDes = contractTypeDes;
	}
	@Column(name="SCALE_TYPE")
	public String getScaleType() {
		return scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}
	
}