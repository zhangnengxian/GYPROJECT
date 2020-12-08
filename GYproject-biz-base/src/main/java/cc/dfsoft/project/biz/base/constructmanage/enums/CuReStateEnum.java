package cc.dfsoft.project.biz.base.constructmanage.enums;
/**
 * 签证确认状态
 * @author liaoyq
 * @createTime 2018年6月13日
 */
public enum CuReStateEnum {

	NOT_RE("-1", "未确认"),
	TO_RE_AUDIT("0", "有异议"),
	PASSED("1", "无异议"); 


	private final String value;

	private final String message;


	CuReStateEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static CuReStateEnum valueof(String value) {
		for (CuReStateEnum e : CuReStateEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
