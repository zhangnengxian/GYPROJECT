package cc.dfsoft.project.biz.base.messagesync.conrtact;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;


/**
 * 
 * 描述:合同管理系统查询传递参数
 * @author liaoyq
 * @createTime 2019年12月6日
 */
public class ProjectQueryInfo {

	/**接口类型*/
	private String operate_type;
	/**查询条件*/
	private ProjectQueryReq projectQueryReq;
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public ProjectQueryReq getProjectQueryReq() {
		return projectQueryReq;
	}
	public void setProjectQueryReq(ProjectQueryReq projectQueryReq) {
		this.projectQueryReq = projectQueryReq;
	}
	
}
