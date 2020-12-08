package cc.dfsoft.project.biz.base.budget.enums;

import java.util.ArrayList;
import java.util.List;

import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;

/**
 * 单位工程费用汇总表类型
 * @author zhangjj
 *
 */
public enum BudgetCostTypeEnum {
	CIVIL_INDOOR("01", "民用户内",ProjLtypeEnum.CIVILIAN.getValue()), 
	CIVIL_COURTYARD("02", "民用庭院",ProjLtypeEnum.CIVILIAN.getValue()),
	CIVIL_ENGINEERING("03", "民用土建",ProjLtypeEnum.CIVILIAN.getValue()),
	TECHNOLOGY("04", "工艺",ProjLtypeEnum.PUBLIC.getValue()),
	BOILER_INSTRUMENT("05", "仪表",ProjLtypeEnum.PUBLIC.getValue()),
	COURTYARD_EARTHWORK("06", "土建",ProjLtypeEnum.PUBLIC.getValue()),
	OTHER("07","其他","14");
	
		
	private final String value;

	private final String message;
	
	private final String type;

	BudgetCostTypeEnum(String value, String message,String type) {
		this.value = value;
		this.message = message;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public String getType() {
		return type;
	}
	public String getMessage() {
		return message;
	}
	public static BudgetCostTypeEnum valueof(String value) {
		for (BudgetCostTypeEnum e : BudgetCostTypeEnum.values()) {
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}
	//获取同类型的数据
	public static List<BudgetCostTypeEnum> getObjByType(String type) {
		List<BudgetCostTypeEnum> enums=new ArrayList<BudgetCostTypeEnum>();
		for (BudgetCostTypeEnum e : BudgetCostTypeEnum.values()) {
			if (e.getType().equals(type)) {
				enums.add(e);	
			}
		}
		return enums;
	}
	
}
