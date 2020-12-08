package cc.dfsoft.uexpress.biz.sys.dept.enums;

import java.util.Iterator;

/**
 * 员工在状态
 * @author Administrator
 *
 */
public enum DeleteOfMarkEnum {
	ON_THE_JOB("1","在职"),
	LEAVE_JOB("0","离职");
	private final String value;
	private final String message;

	DeleteOfMarkEnum(String value,String message){
		this.value = value;
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
	
	public static DeleteOfMarkEnum valueof(String value) {
		for (DeleteOfMarkEnum e : DeleteOfMarkEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
}
