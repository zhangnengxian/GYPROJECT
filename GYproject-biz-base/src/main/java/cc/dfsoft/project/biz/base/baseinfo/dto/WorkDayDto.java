package cc.dfsoft.project.biz.base.baseinfo.dto;
/**
 * 工作日辅助类
 * @author liaoyq
 * @createTime 2018年5月11日
 */
public class WorkDayDto {

	private String daysLimit;		//设置的工作时限
	private String workDays;		//已用工作日
	private String haveDays;		//剩余工作日
	public String getDaysLimit() {
		return daysLimit;
	}
	public void setDaysLimit(String daysLimit) {
		this.daysLimit = daysLimit;
	}
	public String getWorkDays() {
		return workDays;
	}
	public void setWorkDays(String workDays) {
		this.workDays = workDays;
	}
	public String getHaveDays() {
		return haveDays;
	}
	public void setHaveDays(String haveDays) {
		this.haveDays = haveDays;
	}
	
}
