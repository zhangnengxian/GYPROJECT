package cc.dfsoft.project.biz.base.design.enums;

import java.util.ArrayList;
import java.util.List;

public enum UnitTypeEnum {
	GAS_COMPANY("1","燃气集团","1"),
	//DESIGN_UNIT("2","设计院","1"),
	TEST_UNIT("3","检测单位","2"),
	SUPERVISION_UNIT("4","监理单位","2"),
	CONSTRUCTION_UNIT("5","分包单位","2"),
	INSPECTION_UNIT("6","探伤单位","2"),
	MANAGEMENT_UNIT("7","施工单位","2"),
	INTELLIGENT_METER_UNIT("8","智能表公司","2"),
	CUSTOMER_UNIT("9","客户单位","3");
//	CONNECT_CONTENT2("7","天然气分公司","3");
	
	private final String value;
    
    private final String message;
    
    private final String type;
    
    UnitTypeEnum(String value, String message,String type){
        this.value = value;
        this.message = message;
        this.type = type;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public String getType() {
		return type;
	}
    
    public static UnitTypeEnum valueof(String value) {
    	for(UnitTypeEnum e: UnitTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
   
  //获取同类型的数据
  	public static List<UnitTypeEnum> getObjByType(String type) {
  		List<UnitTypeEnum> enums=new ArrayList<UnitTypeEnum>();
  		for (UnitTypeEnum e : UnitTypeEnum.values()) {
  			if (e.getType().equals(type)) {
  				enums.add(e);	
  			}
  		}
  		return enums;
  	}
}
