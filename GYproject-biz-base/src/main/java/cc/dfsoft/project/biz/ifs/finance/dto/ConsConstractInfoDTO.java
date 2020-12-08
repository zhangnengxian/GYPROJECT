package cc.dfsoft.project.biz.ifs.finance.dto;

/**
 * 施工合同DTO（cons_constractInfo）
 * @author duw
 *
 */
public class ConsConstractInfoDTO {
	
	//施工合同协议号
	private String work_code;

	//施工单位名称
	private String work_unit;
	//施工单位编码
	private String work_unit_code;
			
	//施工任务时间		
	private String work_date;
			
	
	//施工合同金额
	private String work_money;
			
	//监理单位		
	private String supervision_unit;
	//监理单位编码
	private String supervision_code;
	
	//施工合同备注
	private String work_remark;
	
	//税率
	private String shuilv;
	
	public String getWork_unit() {
		return work_unit;
	}

	public void setWork_unit(String work_unit) {
		this.work_unit = work_unit;
	}

	public String getWork_date() {
		return work_date;
	}

	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}

	public String getSupervision_unit() {
		return supervision_unit;
	}

	public void setSupervision_unit(String supervision_unit) {
		this.supervision_unit = supervision_unit;
	}

	public String getWork_code() {
		return work_code;
	}

	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}

	public String getWork_money() {
		return work_money;
	}

	public void setWork_money(String work_money) {
		this.work_money = work_money;
	}

	public String getWork_remark() {
		return work_remark;
	}

	public void setWork_remark(String work_remark) {
		this.work_remark = work_remark;
	}

	public String getShuilv() {
		return shuilv;
	}

	public void setShuilv(String shuilv) {
		this.shuilv = shuilv;
	}

	public String getWork_unit_code() {
		return work_unit_code;
	}

	public void setWork_unit_code(String work_unit_code) {
		this.work_unit_code = work_unit_code;
	}

	public String getSupervision_code() {
		return supervision_code;
	}

	public void setSupervision_code(String supervision_code) {
		this.supervision_code = supervision_code;
	}
	
}
