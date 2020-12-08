package cc.dfsoft.project.biz.ifs.projectQuery.dto;
/**
 * 返回工程基本信息辅助类
 * @author liaoyq
 * @createTime 2018年8月16日
 */
public class ProjectDto {
	private String proj_no;		   //工程编号
	private String proj_status;	   //工程状态
	private String surveyer;	   //踏勘人员
	private String surveyer_phone; //踏勘人员-联系电话
	private String proj_name;	   //工程名称
	private String proj_scale_des;	//工程规模
	private String proj_addr;	//工程规模
	private String remarks;		//备注
	private String projType;		//工程类型
	private String conb;		//出资方式
	private String stepId;		//步骤Id
	private String grade;		//审核级别


	public String getProj_no() {
		return proj_no;
	}
	public void setProj_no(String proj_no) {
		this.proj_no = proj_no;
	}
	public String getProj_status() {
		return proj_status;
	}
	public void setProj_status(String proj_status) {
		this.proj_status = proj_status;
	}
	public String getSurveyer() {
		return surveyer;
	}
	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}
	public String getSurveyer_phone() {
		return surveyer_phone;
	}
	public void setSurveyer_phone(String surveyer_phone) {
		this.surveyer_phone = surveyer_phone;
	}
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	public String getProj_scale_des() {
		return proj_scale_des;
	}
	public void setProj_scale_des(String proj_scale_des) {
		this.proj_scale_des = proj_scale_des;
	}
	public String getProj_addr() {
		return proj_addr;
	}
	public void setProj_addr(String proj_addr) {
		this.proj_addr = proj_addr;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getConb() {
		return conb;
	}

	public void setConb(String conb) {
		this.conb = conb;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
