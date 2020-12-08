package cc.dfsoft.project.biz.base.constructmanage.dto;


import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 工程查询
 * @author pengtt
 * @createTime 2016-06-21
 *
 */
public class ReviewRecordQueryReq extends PageSortReq{
	
	private String projId;				//工程id
	private String drId;				//会审id
	

	public String getDrId() {
		return drId;
	}

	public void setDrId(String drId) {
		this.drId = drId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	
}
