package cc.dfsoft.project.biz.base.baseinfo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.baseinfo.enums.UnitEnum;
/**
 * 签证工程量标准
 * @author fuliwei
 *
 */
@Entity
@Table(name = "VISA_QUANTITIES")
public class VisaQuantities {
    private String id;					//ID
    private String des;					//分项名称
    private BigDecimal quantitiesNum;	//签证工程量标准
    private BigDecimal actualNum;
    private String unit;				//单位
    private String unitDes;	
    
    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Column(name = "UNIT")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
	
	@Transient
	public BigDecimal getActualNum() {
		return actualNum;
	}

	public void setActualNum(BigDecimal actualNum) {
		this.actualNum = actualNum;
	}

}
