package cc.dfsoft.project.biz.base.project.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 
 * @author zhangjj
 *
 */
public class ProjectTypeReq extends PageSortReq{
	
	private String projectTypeDes;				//工程编号

	public String getProjectTypeDes() {
		return projectTypeDes;
	}

	public void setProjectTypeDes(String projectTypeDes) {
		this.projectTypeDes = projectTypeDes;
	}
		
	
}
