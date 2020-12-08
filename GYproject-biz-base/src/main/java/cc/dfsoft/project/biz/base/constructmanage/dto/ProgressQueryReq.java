package cc.dfsoft.project.biz.base.constructmanage.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 工程进度查询
 * @author pengtt
 * @createTime 2016-07-27
 *
 */
public class ProgressQueryReq extends PageSortReq{
	
	private String projId;				//工程id
	private String nuitProject;			//分项名称
	private String registerDate;		//登记日期
	

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getNuitProject() {
		return nuitProject;
	}

	public void setNuitProject(String nuitProject) {
		this.nuitProject = nuitProject;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
}
