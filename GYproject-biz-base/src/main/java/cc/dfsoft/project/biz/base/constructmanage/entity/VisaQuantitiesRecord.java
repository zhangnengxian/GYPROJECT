package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.math.BigDecimal;

import javax.persistence.*;

import cc.dfsoft.project.biz.base.baseinfo.enums.UnitEnum;

/**
 * 签证标准记录
 */
@Entity
@Table(name = "VISA_QUANTITIES_RECORD")
public class VisaQuantitiesRecord {
	
    private String vqeId; 			  //主键id
    private String evId;			  //工程签证ID
    private String projId;			  //工程id
    private String des;				  //分项名称
    private BigDecimal quantitiesNum;//签证工程量标准
    private BigDecimal actualNum;	  //实际工程量
    private String unit;	  			//单位
    private String id;				//签证工程量标准id
    private String unitDes;	

    @Id
    @Column(name = "VQE_ID")
    public String getVqeId() {
        return vqeId;
    }

    public void setVqeId(String vqeId) {
        this.vqeId = vqeId;
    }

   
    @Column(name = "EV_ID")
    public String getEvId() {
        return evId;
    }

    public void setEvId(String evId) {
        this.evId = evId;
    }

   
    @Column(name = "PROJ_ID")
    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

   
    @Column(name = "DES")
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

   
    @Column(name = "QUANTITIES_NUM")
    public BigDecimal getQuantitiesNum() {
        return quantitiesNum;
    }

    public void setQuantitiesNum(BigDecimal quantitiesNum) {
        this.quantitiesNum = quantitiesNum;
    }

   
    @Column(name = "ACTUAL_NUM")
    public BigDecimal getActualNum() {
        return actualNum;
    }

    public void setActualNum(BigDecimal actualNum) {
        this.actualNum = actualNum;
    }

   
    @Column(name = "UNIT")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    @Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Transient
	public String getUnitDes() {
		for(UnitEnum e: UnitEnum.values()) {
	   		if(e.getValue().equals(this.unit)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setUnitDes(String unitDes) {
		this.unitDes = unitDes;
	}
}
