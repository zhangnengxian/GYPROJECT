package cc.dfsoft.project.biz.base.constructmanage.enums;

public enum CertificateCompleteEnum {
	HAVE("0", "否"),
	NOT_HAVE("1", "是"),
	INCOMPLETE("2","不全");
	private final String value;

	private final String message;


	CertificateCompleteEnum(String value, String message) {
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public static CertificateCompleteEnum valueof(String value) {
		for (CertificateCompleteEnum e : CertificateCompleteEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
