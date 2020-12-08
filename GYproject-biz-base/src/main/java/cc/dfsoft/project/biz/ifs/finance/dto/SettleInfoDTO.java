package cc.dfsoft.project.biz.ifs.finance.dto;

/**
 * 结算信息DTO（settleInfo）
 * @author duw
 *
 */
public class SettleInfoDTO {

	//结算金额
	private String settle_money;

	//交付时间
	private String ready_date;
			
	//验收时间
	private String check_date;
			
	//竣工时间
	private String complet_date;
	
	//检测单位
	private String inspection_unit;
	//检测单位编码
	private String inspection_code;
	//破路费
	private String break_road_money;
	//其他费用
	private String other_money;

	public String getSettle_money() {
		return settle_money;
	}

	public void setSettle_money(String settle_money) {
		this.settle_money = settle_money;
	}

	public String getReady_date() {
		return ready_date;
	}

	public void setReady_date(String ready_date) {
		this.ready_date = ready_date;
	}

	public String getCheck_date() {
		return check_date;
	}

	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}

	public String getComplet_date() {
		return complet_date;
	}

	public void setComplet_date(String complet_date) {
		this.complet_date = complet_date;
	}

	public String getInspection_unit() {
		return inspection_unit;
	}

	public void setInspection_unit(String inspection_unit) {
		this.inspection_unit = inspection_unit;
	}

	public String getInspection_code() {
		return inspection_code;
	}

	public void setInspection_code(String inspection_code) {
		this.inspection_code = inspection_code;
	}

	public String getBreak_road_money() {
		return break_road_money;
	}

	public void setBreak_road_money(String break_road_money) {
		this.break_road_money = break_road_money;
	}

	public String getOther_money() {
		return other_money;
	}

	public void setOther_money(String other_money) {
		this.other_money = other_money;
	}
	
}
