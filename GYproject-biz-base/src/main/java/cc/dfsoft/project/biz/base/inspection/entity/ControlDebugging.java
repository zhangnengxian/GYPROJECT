package cc.dfsoft.project.biz.base.inspection.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 控制系统调试
 * Created by Administrator on 2017/2/5 0005.
 */
@Entity
@Table(name = "CONTROL_DEBUGGING")
public class ControlDebugging {
    private String cdId;				//主键ID
    private String pcId;				//报验单ID
    private String equipmentNameType;	//设备名称型号
    private BigDecimal cdNum;			//数量	
    private String cdNo;				//编号
    private Date leaveFactoryDate;		//出厂年月
    private String manufactureFactory;	//生产厂家
    private String note;				//备注

    @Id
    @Column(name = "CD_ID")
    public String getCdId() {
        return cdId;
    }

    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

    
    @Column(name = "PC_ID")
    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    
    @Column(name = "EQUIPMENT_NAME_TYPE")
    public String getEquipmentNameType() {
        return equipmentNameType;
    }

    public void setEquipmentNameType(String equipmentNameType) {
        this.equipmentNameType = equipmentNameType;
    }

    
    @Column(name = "CD_NUM")
    public BigDecimal getCdNum() {
        return cdNum;
    }

    public void setCdNum(BigDecimal cdNum) {
        this.cdNum = cdNum;
    }

    
    @Column(name = "CD_NO")
    public String getCdNo() {
        return cdNo;
    }

    public void setCdNo(String cdNo) {
        this.cdNo = cdNo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "LEAVE_FACTORY_DATE")
    public Date getLeaveFactoryDate() {
        return leaveFactoryDate;
    }

    public void setLeaveFactoryDate(Date leaveFactoryDate) {
        this.leaveFactoryDate = leaveFactoryDate;
    }

    
    @Column(name = "MANUFACTURE_FACTORY")
    public String getManufactureFactory() {
        return manufactureFactory;
    }

    public void setManufactureFactory(String manufactureFactory) {
        this.manufactureFactory = manufactureFactory;
    }

    
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
