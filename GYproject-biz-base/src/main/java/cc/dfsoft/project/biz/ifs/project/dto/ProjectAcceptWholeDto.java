package cc.dfsoft.project.biz.ifs.project.dto;
/**
 * 报装接收的信息服务类
 * 查询工程信息服务类
 * @author liaoyq
 * @createTime 2018年8月16日
 */
public class ProjectAcceptWholeDto {

	private String operate_type;	//操作类型
	private ProjectAcceptDto projectInfo; //立项工程信息
	
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public ProjectAcceptDto getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectAcceptDto projectInfo) {
		this.projectInfo = projectInfo;
	}
	
}
