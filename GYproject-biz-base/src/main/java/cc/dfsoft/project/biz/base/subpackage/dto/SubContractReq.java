package cc.dfsoft.project.biz.base.subpackage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 分包协议查询dto
 * @author pengtt
 * @createTime 2016-07-13
 *
 */
public class SubContractReq extends PageSortReq{
	
	private String scNo;					//分包合同编号
	private String projId;					//工程ID
	private String projNo;					//工程编号
	
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	
	
	
}
