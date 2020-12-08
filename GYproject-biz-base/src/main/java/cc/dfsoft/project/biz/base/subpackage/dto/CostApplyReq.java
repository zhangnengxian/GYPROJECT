package cc.dfsoft.project.biz.base.subpackage.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 已作废
 * @author fuliwei
 *
 */
public class CostApplyReq extends PageSortReq{
	
	private String caId;    			//请款id
	private String caNo;				//申请编号
	private String caStartDate;			//申请开始日期
	private String caEndDate;			//申请结束日期
	private String caUnitId;   			//请款单位
	private String caType;             	//请款项
	private String applyerId;           //请款人

	public String getCaId() {
		return caId;
	}

	public void setCaId(String caId) {
		this.caId = caId;
	}

	public String getCaNo() {
		return caNo;
	}

	public void setCaNo(String caNo) {
		this.caNo = caNo;
	}

	public String getCaStartDate() {
		return caStartDate;
	}

	public void setCaStartDate(String caStartDate) {
		this.caStartDate = caStartDate;
	}

	public String getCaEndDate() {
		return caEndDate;
	}

	public void setCaEndDate(String caEndDate) {
		this.caEndDate = caEndDate;
	}

	public String getCaUnitId() {
		return caUnitId;
	}

	public void setCaUnitId(String caUnitId) {
		this.caUnitId = caUnitId;
	}

	public String getCaType() {
		return caType;
	}

	public void setCaType(String caType) {
		this.caType = caType;
	}

	public String getApplyerId() {
		return applyerId;
	}

	public void setApplyerId(String applyerId) {
		this.applyerId = applyerId;
	}
}
