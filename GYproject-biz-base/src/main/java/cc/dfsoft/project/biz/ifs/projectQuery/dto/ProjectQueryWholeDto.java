package cc.dfsoft.project.biz.ifs.projectQuery.dto;
/**
 * 
 * @author liaoyq
 * @createTime 2018年8月16日
 */
public class ProjectQueryWholeDto {

	private String operate_type;
	private ProjectQueryDto projectInfo; // 根据工程编号查询工程信息
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public ProjectQueryDto getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectQueryDto projectInfo) {
		this.projectInfo = projectInfo;
	}
	
}
