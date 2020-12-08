package cc.dfsoft.project.biz.base.project.enums;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CollectionTypeEnum {
	
	DESIGN_DEPOSIT("11","设计定金","1","",""),
	DESIGN_FEE("12","设计费","3","03","结算款"),
	INITIAL_PAYMENT("13","首付款","2","01","首付款"),
	STAGE_PAYMENT("19","进度款","2","02","阶段款"),
	BALANCE_PAYMENT("14","工程尾款","2","03","工程尾款"),
	SUBCONTRACT_PAYMENT("15","分包预付款","3","01","预付款"),
	GAS_CONFIRM("16","通气确认款","3","04","通气确认款"),//-未使用
	SUBCONTRACT_BALANCE_PAYMENT("17","分包结算款","3","03","结算款"),
	SUPPLEMENTAL_CONTRACT("18","补充协议款","2","05","补充协议款"),
	SUPPLEMENTAL_MODIFY_CONTRACT("22","补充协议应退款","2","05","补充协议应退款"),
	MODIFY_CONTRACT("20","合同应退款","2","06","合同应退款"),
	INTELLIGENT_CONTRACT("30","智能表合同首付款","2","01","首付款"),
	STAGE_INTELLIGENT_CONTRACT("31","智能表合同阶段款","2","02","阶段款"),
	IMC_MODIFY_CONTRACT("34","智能表合同应退款","2","03","智能表合同应退款"),
	SU_INTELLIGENT_CONTRACT("32","智能表分包合同首付款","3","01","预付款"),
	STAGE_SU_INTELLIGENT_CONTRACT("33","智能表分包合同阶段款","3","03","结算款"),
	SU_FEE("41","监理费","3","03","结算款"),
	CHECK_FEE("42","检测费","3","03","结算款");
	
	private final String value;
    
    private final String message;
    
    private final String type;
    
    private final String code;
    
    private final String codeDesc;
    
    CollectionTypeEnum(String value, String message, String type,String code,String codeDesc){
        this.value = value;
        this.message = message;
        this.type = type;
        this.code = code;
        this.codeDesc = codeDesc;
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
    
    public String getCode() {
		return code;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public static CollectionTypeEnum valueof(String value) {
    	for(CollectionTypeEnum e: CollectionTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
	public static JSONObject toJson() {
		Map<String, String> map = new HashMap<String, String>();
		for (CollectionTypeEnum e : CollectionTypeEnum.values()) {
			map.put(e.value, e.message);

		}
		return JSONObject.fromObject(map);
	}
	
	//获取同类型的数据
  	public static List<CollectionTypeEnum> getObjByType(String type) {
  		List<CollectionTypeEnum> enums=new ArrayList<CollectionTypeEnum>();
  		for (CollectionTypeEnum e : CollectionTypeEnum.values()) {
  			if (e.getType().equals(type)) {
  				enums.add(e);	
  			}
  		}
  		return enums;
  	}
}
