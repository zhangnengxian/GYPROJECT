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
 * 复压记录实体类
 * @author wangang
 *
 */
@Entity
@Table(name = "pressure_records")
public class PressureRecords implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1053353115797706646L;
	private String id;				    //复压记录ID
	private String pcId;				//报验单ID
	private String riserNumber;			//楼栋立管编号
	private String record;		        //复压记录
	private String installTrouble;		//户内安装隐患
	private String rectifyInfo;			//整改完成情况
	private String rectifyTeam;			//整改班组
	private String remark;				//备注
	private String projId;			    //工程id


	public PressureRecords() {
		super();
	}


	@Id
	@Column( name ="ID" , unique = true, nullable = false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column( name ="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column( name ="RISER_NUMBER")
	public String getRiserNumber() {
		return riserNumber;
	}

	public void setRiserNumber(String riserNumber) {
		this.riserNumber = riserNumber;
	}

	@Column( name ="RECORD")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Column( name ="INSTALL_TROUBLE")
	public String getInstallTrouble() {
		return installTrouble;
	}

	public void setInstallTrouble(String installTrouble) {
		this.installTrouble = installTrouble;
	}

	@Column( name ="RECTIFY_INFO")
	public String getRectifyInfo() {
		return rectifyInfo;
	}

	public void setRectifyInfo(String rectifyInfo) {
		this.rectifyInfo = rectifyInfo;
	}

	@Column( name ="RECTIFY_TEAM")
	public String getRectifyTeam() {
		return rectifyTeam;
	}

	public void setRectifyTeam(String rectifyTeam) {
		this.rectifyTeam = rectifyTeam;
	}

	@Column( name ="REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column( name ="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
}
