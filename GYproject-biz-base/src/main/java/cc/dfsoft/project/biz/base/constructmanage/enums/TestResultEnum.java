package cc.dfsoft.project.biz.base.constructmanage.enums;
/**
 * 
 * 描述:无损检测结果枚举
 * @author liaoyq
 * @createTime 2017年9月27日
 */
public enum TestResultEnum {
	PASSED("1", "合格"),
	REPAIRED("2", "返修"),
	EXTEND("3", "扩探");


	private final String value;

	private final String message;


	TestResultEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static TestResultEnum valueof(String value) {
		for (TestResultEnum e : TestResultEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
