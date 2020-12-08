package cc.dfsoft.project.biz.base.change.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.design.enums.MaterialFlagEnum;

/**
 * 材料变更 
 * @author zhangjj
 *
 */
@Entity
@Table(name = "MATERIAL_CHANGE")
public class MaterialChange{


    // Fields

     private String mcId;        //材料变更记录ID
     private String cmId;        //变更记录ID
     private String mcType;        //类型
     private String projId;      //工程ID
     private String projNo;      //工程编号
     private String materialId;  //材料清单ID
     private String materialName;//材料名称
     private String materialSpec;//规格型号
     private String materialUnit;//单位
     private BigDecimal adjustQuantity;//调节量
     private Date optTime;     //操作时间
     private String mcOperator;  //操作人ID
     
     
    private String materialCode;//物料编码
 	private String materialStandard;//物料规格
	private String flag;		  //是否从物资公司购买：1-是
	private String flagDes;		  //是否由物资购买

    @Id
    @Column(name="MC_ID", nullable=false)
    public String getMcId() {
        return this.mcId;
    }
    
    public void setMcId(String mcId) {
        this.mcId = mcId;
    }

    @Column(name="CM_ID")
    public String getCmId() {
        return this.cmId;
    }
    
    public void setCmId(String cmId) {
        this.cmId = cmId;
    }

    @Column(name="MC_TYPE")
    public String getMcType() {
  		return mcType;
  	}

  	public void setMcType(String mcType) {
  		this.mcType = mcType;
  	}

    @Column(name="PROJ_ID", nullable=false)
    public String getProjId() {
        return this.projId;
    }
    
  

	public void setProjId(String projId) {
        this.projId = projId;
    }

    @Column(name="PROJ_NO")
    public String getProjNo() {
        return this.projNo;
    }
    
    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }
    @Column(name="MATERIAL_ID", nullable=false)

    public String getMaterialId() {
        return this.materialId;
    }   
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Column(name="MATERIAL_NAME")
    public String getMaterialName() {
        return this.materialName;
    }
    
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    @Column(name="MATERIAL_SPEC")
    public String getMaterialSpec() {
        return this.materialSpec;
    }
    
    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    @Column(name="MATERIAL_UNIT")
    public String getMaterialUnit() {
        return this.materialUnit;
    }
    
    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    @Column(name="ADJUST_QUANTITY")
    public BigDecimal getAdjustQuantity() {
        return this.adjustQuantity;
    }
    
    public void setAdjustQuantity(BigDecimal adjustQuantity) {
        this.adjustQuantity = adjustQuantity;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="OPT_TIME")
    public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
    @Column(name="MC_OPERATOR")
    public String getMcOperator() {
        return this.mcOperator;
    }
    
   

	public void setMcOperator(String mcOperator) {
        this.mcOperator = mcOperator;
    }
	
	@Column(name="MATERIAL_CODE")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	@Column(name="MATERIAL_STANDARD")
	public String getMaterialStandard() {
		return materialStandard;
	}

	public void setMaterialStandard(String materialStandard) {
		this.materialStandard = materialStandard;
	}

	@Column(name="FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Transient
	public String getFlagDes() {
		for(MaterialFlagEnum e: MaterialFlagEnum.values()) {
	   		if(e.getValue().equals(this.flag)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setFlagDes(String flagDes) {
		this.flagDes = flagDes;
	}
   
}