package cc.dfsoft.project.biz.ifs.project.dto;

/**
 * 报装返回数据辅助类
 * @author liaoyq
 * @createTime 2018年8月16日
 */
public class ProjectAcceptReDto {

	private String ret_type;		//返回状态码
	private String ret_message;     ///返回信息
	private ProjectRetDto projectInfo;//工程信息
	public String getRet_type() {
		return ret_type;
	}
	public void setRet_type(String ret_type) {
		this.ret_type = ret_type;
	}
	public String getRet_message() {
		return ret_message;
	}
	public void setRet_message(String ret_message) {
		this.ret_message = ret_message;
	}
	public ProjectRetDto getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectRetDto projectInfo) {
		this.projectInfo = projectInfo;
	}
	
	
	
}
