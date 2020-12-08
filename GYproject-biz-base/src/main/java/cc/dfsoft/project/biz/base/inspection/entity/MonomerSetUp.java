package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.inspection.enums.CheckItemsEnum;

/**
 * 单体调校
 * Created by Administrator on 2017/2/5 0005.
 */
@Entity
@Table(name = "MONOMER_SET_UP")
public class MonomerSetUp {
    private String id;			//主键ID
    private String pcId;		//报验单ID
    private String monomer;		//测试点
    private String result;		//检查结果

    private String monomerDes;	//测试点描述 ----------用于页面显示
    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name = "PC_ID")
    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    
    @Column(name = "MONOMER")
    public String getMonomer() {
        return monomer;
    }

    public void setMonomer(String monomer) {
        this.monomer = monomer;
    }

    
    @Column(name = "RESULT")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

	@Transient
	public String getMonomerDes() {
		for(CheckItemsEnum e: CheckItemsEnum.values()) {
	   		if(e.getValue().equals(this.monomer)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setMonomerDes(String monomerDes) {
		this.monomerDes = monomerDes;
	}

}
