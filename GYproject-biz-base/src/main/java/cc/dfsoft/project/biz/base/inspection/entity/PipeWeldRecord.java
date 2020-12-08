package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
/**
 * 焊口记录实体类
 * @author liaoyq
 * @createTime 2017年7月25日
 */
@Entity
@Table(name="PIPE_WELD_RECORD")
public class PipeWeldRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pwrId;			//`PWR_ID` varchar(30) NOT NULL COMMENT '管道焊口记录ID',
	private String projId;			//  `PROJ_ID` varchar(30) NOT NULL COMMENT '工程ID',
	private String pcId;			//  `PC_ID` varchar(30) DEFAULT NULL COMMENT '报验单ID',
	private String pwrNo;			//  `PWR_NO` varchar(50) DEFAULT NULL COMMENT '焊口记录及编号',
	private String pwSpec;			// `PW_SPEC` varchar(50) DEFAULT NULL COMMENT '规格',
	private String welderName;		//  `WELDER_NAME` varchar(100) DEFAULT NULL COMMENT '焊工姓名',
	private String welderNo;		//  `WELDER_NO` varchar(50) DEFAULT NULL COMMENT '焊工证号',
	private Integer version;		//版本控制
	public PipeWeldRecord() {
		super();
	}

	@Id
	@Column(name="PWR_ID",unique = true, nullable = false)
	public String getPwrId() {
		return pwrId;
	}

	public void setPwrId(String pwrId) {
		this.pwrId = pwrId;
	}

	@Column(name="PROJ_ID", nullable = false)
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="PWR_NO")
	public String getPwrNo() {
		return pwrNo;
	}

	public void setPwrNo(String pwrNo) {
		this.pwrNo = pwrNo;
	}

	@Column(name="PW_SPEC")
	public String getPwSpec() {
		return pwSpec;
	}

	public void setPwSpec(String pwSpec) {
		this.pwSpec = pwSpec;
	}

	@Column(name="WELDER_NAME")
	public String getWelderName() {
		return welderName;
	}

	public void setWelderName(String welderName) {
		this.welderName = welderName;
	}

	@Column(name="WELDER_NO")
	public String getWelderNo() {
		return welderNo;
	}

	public void setWelderNo(String welderNo) {
		this.welderNo = welderNo;
	}
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	

}
