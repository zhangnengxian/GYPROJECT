package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.entity.Project;

public class ProjectViewDto implements java.io.Serializable {
	
	private Project project;
	private ConstructionPlan constructionPlan;
	private String dlDate;
	private String dlRecorder;//记录人
	private String staffId;//
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public ConstructionPlan getConstructionPlan() {
		return constructionPlan;
	}
	public void setConstructionPlan(ConstructionPlan constructionPlan) {
		this.constructionPlan = constructionPlan;
	}
	public String getDlDate() {
		return dlDate;
	}
	public void setDlDate(String dlDate) {
		this.dlDate = dlDate;
	}
	public String getDlRecorder() {
		return dlRecorder;
	}
	public void setDlRecorder(String dlRecorder) {
		this.dlRecorder = dlRecorder;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
}