package cc.dfsoft.project.biz.base.constructmanage.enums;

import java.util.ArrayList;
import java.util.List;

import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;

/**
 * 材料计划是否反馈
 * @author fuliwei
 *
 */
public enum MaterialPlanFeedBackEnum {
	HAVE_NOT_FEED_BACK("0", "未反馈","1"),
	HAVEN_FEED_BACK("1", "已反馈","1");
	
	private final String value;

	private final String message;
	
	private final String type;

	MaterialPlanFeedBackEnum(String value, String message,String type) {
		this.value = value;
		this.message = message;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}
	
	public String getType() {
		return type;
	}
	public static MaterialPlanFeedBackEnum valueof(String value) {
		for (MaterialPlanFeedBackEnum e : MaterialPlanFeedBackEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
	
	 //获取同类型的数据
  		public static List<MaterialPlanFeedBackEnum> getObjByType(String type) {
  		List<MaterialPlanFeedBackEnum> enums=new ArrayList<MaterialPlanFeedBackEnum>();
  		for (MaterialPlanFeedBackEnum e : MaterialPlanFeedBackEnum.values()) {
  			if (e.getType().equals(type)) {
  				enums.add(e);	
  			}
  		}
  		return enums;
  	}
}
