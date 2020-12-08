package cc.dfsoft.project.biz.base.constructmanage.enums;
/**
 * 
 * 描述:无损检测项目枚举类
 * @author liaoyq
 * @createTime 2017年9月27日
 */
public enum NdeTestItemEnum {
	RT("1", "RT"),
	UT("2", "UT"),
	PT("3", "PT"),
	MT("4", "MT");


	private final String value;

	private final String message;


	NdeTestItemEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static NdeTestItemEnum valueof(String value) {
		for (NdeTestItemEnum e : NdeTestItemEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
