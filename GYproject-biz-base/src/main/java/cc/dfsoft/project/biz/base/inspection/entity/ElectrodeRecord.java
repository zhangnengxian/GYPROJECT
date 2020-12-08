package cc.dfsoft.project.biz.base.inspection.entity;

import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.util.ClobUtil;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialException;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * 焊条领用记录实体类
 * @author liaoyq
 * @date 2017-6-28
 */
@Entity
@Table(name = "electrode_record")
public class ElectrodeRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**焊条领用记录ID*/
	private String erId;
	/**报验单ID*/
	private String pcId;
	/**焊条焊号*/
	private String electrodeNo;
	/**焊条规格*/
	private String electrodeForamt;
	/**领用人*/
	private Clob  recipientStaff;
	/**领用数量*/
	private String recipientNum;
	/**领用时间*/
	private Date recipientDate;
	/**批号*/
	private String ecBatchNum;
	/**工程ID*/
	private String projId;
	
	private List<Signature> sign;
	private String menuId;

	private Integer version;		//版本控制

	private String isFinishSign;//是否完成签字 1是，0否

	public ElectrodeRecord() {
		super();
	}
	
	
	@Id
	@Column(name = "ER_ID", unique = true, nullable = false)
	public String getErId() {
		return erId;
	}
	public void setErId(String erId) {
		this.erId = erId;
	}
	
	@Column(name = "PC_ID")
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	
	@Column(name = "ELECTRODE_NO")
	public String getElectrodeNo() {
		return electrodeNo;
	}
	public void setElectrodeNo(String electrodeNo) {
		this.electrodeNo = electrodeNo;
	}
	
	@Column(name = "ELECTRODE_FORMAT")
	public String getElectrodeForamt() {
		return electrodeForamt;
	}
	public void setElectrodeForamt(String electrodeForamt) {
		this.electrodeForamt = electrodeForamt;
	}
	
	@Column(name = "RECIPIENT_STAFF")
	public String getRecipientStaff() {
		return ClobUtil.ClobToString(this.recipientStaff);
	}
	public void setRecipientStaff(String recipientStaff) throws SerialException, SQLException {
		this.recipientStaff = ClobUtil.stringToClob(recipientStaff);
	}
	
	@Column(name = "RECIPIENT_NUM")
	public String getRecipientNum() {
		return recipientNum;
	}
	public void setRecipientNum(String recipientNum) {
		this.recipientNum = recipientNum;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "RECIPIENT_DATE")
	public Date getRecipientDate() {
		return recipientDate;
	}
	public void setRecipientDate(Date recipientDate) {
		this.recipientDate = recipientDate;
	}

	@Column( name = "EC_BATCH_NUMBER")
	public String getEcBatchNum() {
		return ecBatchNum;
	}


	public void setEcBatchNum(String ecBatchNum) {
		this.ecBatchNum = ecBatchNum;
	}


	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}


	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Transient
	public List<Signature> getSign() {
		return sign;
	}
	
	public void setSign(List<Signature> sign) {
		this.sign = sign;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name="IS_FINISH_SIGN")
	public String getIsFinishSign() {
		return isFinishSign;
	}

	public void setIsFinishSign(String isFinishSign) {
		this.isFinishSign = isFinishSign;
	}
}
