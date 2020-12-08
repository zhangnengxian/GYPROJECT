package cc.dfsoft.project.biz.base.constructmanage.dto;

import java.util.List;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 
 * 描述:整改通知查询辅助类
 * @author liaoyq
 * @createTime 2017年8月4日
 */
public class RectifyNoticeReq extends PageSortReq {

	private String projId;			//工程ID
	private String rnNo;			//工程编号
	private String rnDateStart;		//通知日期开始
	private String rnDateEnd;		//通知日期结束
	private List<String> rnStatus;	//通知状态
	private String busOrderId;		//单据ID
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getRnNo() {
		return rnNo;
	}

	public void setRnNo(String rnNo) {
		this.rnNo = rnNo;
	}

	public String getRnDateStart() {
		return rnDateStart;
	}

	public void setRnDateStart(String rnDateStart) {
		this.rnDateStart = rnDateStart;
	}

	public String getRnDateEnd() {
		return rnDateEnd;
	}

	public void setRnDateEnd(String rnDateEnd) {
		this.rnDateEnd = rnDateEnd;
	}

	public List<String> getRnStatus() {
		return rnStatus;
	}

	public void setRnStatus(List<String> rnStatus) {
		this.rnStatus = rnStatus;
	}

	public String getBusOrderId() {
		return busOrderId;
	}

	public void setBusOrderId(String busOrderId) {
		this.busOrderId = busOrderId;
	}
}
