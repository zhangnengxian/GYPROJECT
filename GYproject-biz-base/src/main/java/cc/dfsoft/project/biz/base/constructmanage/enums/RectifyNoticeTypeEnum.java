package cc.dfsoft.project.biz.base.constructmanage.enums;
/**
 * 
 * 描述:整改通知类型
 * @author liaoyq
 * @createTime 2017年12月14日
 */
public enum RectifyNoticeTypeEnum {

	ACCEPTANCE("1", "验收整改"),
	INSPECTION("2", "中间检查"); 


	private final String value;

	private final String message;


	RectifyNoticeTypeEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static RectifyNoticeTypeEnum valueof(String value) {
		for (RectifyNoticeTypeEnum e : RectifyNoticeTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
