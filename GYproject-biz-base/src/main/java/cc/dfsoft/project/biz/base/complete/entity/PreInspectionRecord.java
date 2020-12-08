package cc.dfsoft.project.biz.base.complete.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "PRE_INSPECTION_RECORD")
public class PreInspectionRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1131726157294323552L;
	
	private String pirId;		//预验收检查记录ID
	private String piId;		//预验收记录ID
	private String projId;		//工程ID
	private String sirType;		//检查类型
	private String ciId;		//检查项目ID
	private String sirResult;	//检查结果
	private String sirNum;		//份数
	private String sirRemark;	//备注（检查意见）
	
	public PreInspectionRecord(){
		
	}
	
	// Property accessors
		@Id
		@Column(name = "PIR_ID", unique = true)
		public String getPirId() {
			return this.pirId;
		}

		public void setPirId(String pirId) {
			this.pirId = pirId;
		}

		@Column(name = "PI_ID")
		public String getPiId() {
			return this.piId;
		}

		public void setPiId(String piId) {
			this.piId = piId;
		}

		@Column(name = "PROJ_ID")
		public String getProjId() {
			return this.projId;
		}

		public void setProjId(String projId) {
			this.projId = projId;
		}

		@Column(name = "SIR_TYPE")
		public String getSirType() {
			return this.sirType;
		}

		public void setSirType(String sirType) {
			this.sirType = sirType;
		}

		@Column(name = "CI_ID")
		public String getCiId() {
			return this.ciId;
		}

		public void setCiId(String ciId) {
			this.ciId = ciId;
		}

		@Column(name = "SIR_RESULT")
		public String getSirResult() {
			return this.sirResult;
		}

		public void setSirResult(String sirResult) {
			this.sirResult = sirResult;
		}

		@Column(name = "SIR_NUM")
		public String getSirNum() {
			return this.sirNum;
		}

		public void setSirNum(String sirNum) {
			this.sirNum = sirNum;
		}

		@Column(name = "SIR_REMARK")
		public String getSirRemark() {
			return this.sirRemark;
		}

		public void setSirRemark(String sirRemark) {
			this.sirRemark = sirRemark;
		}
}
