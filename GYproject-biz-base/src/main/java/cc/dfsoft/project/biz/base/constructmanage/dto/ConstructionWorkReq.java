package cc.dfsoft.project.biz.base.constructmanage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 会审交底查询辅助类
 * @author liaoyq
 * @createTime 2017年7月28日
 */
public class ConstructionWorkReq extends PageSortReq{

	private String projId;	//工程Id
	private String drawingNo;	//图号
	private String signState;	//完成标记
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getDrawingNo() {
		return drawingNo;
	}
	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	public String getSignState() {
		return signState;
	}
	public void setSignState(String signState) {
		this.signState = signState;
	}
	
	
}
