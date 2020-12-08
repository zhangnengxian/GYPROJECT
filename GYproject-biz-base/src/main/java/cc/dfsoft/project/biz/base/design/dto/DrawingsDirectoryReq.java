package cc.dfsoft.project.biz.base.design.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

/**
 * 图纸目录查询条件
 * @author zhangjj
 * @createTime 2016-06-27
 *
 */
public class DrawingsDirectoryReq  extends PageSortReq{
	
	private String projId;				//工程编号
	private String drawingNo;
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
	
	
}
