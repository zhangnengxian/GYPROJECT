package cc.dfsoft.project.biz.base.common.dto;
/**
 * 打印报表版本控制查询辅助
 * @author liaoyq
 * @createTime 2018年8月13日
 */
public class ReportVersionReq {

	private String menuId;
	private String corpId;
	private String projType;
	private String signDate;	//签订日期
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getProjType() {
		return projType;
	}
	public void setProjType(String projType) {
		this.projType = projType;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	
}
