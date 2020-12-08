package cc.dfsoft.project.biz.base.design.enums;

import java.util.ArrayList;
import java.util.List;

public enum FeedbackStateEnum {
	TO_FEEDBACK("1","未提交","0"),
	FEEDBACKING("2","待反馈","1"),
	FEEDBACKED("3","已反馈","1");
	private final String value;
    
    private final String message;
    
    private final String type;
    FeedbackStateEnum(String value, String message,String type){
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
    
    public static FeedbackStateEnum valueof(String value) {
    	for(FeedbackStateEnum e: FeedbackStateEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    
    //获取同类型的数据
  	public static List<FeedbackStateEnum> getObjByType(String type) {
  		List<FeedbackStateEnum> enums=new ArrayList<FeedbackStateEnum>();
  		for (FeedbackStateEnum e : FeedbackStateEnum.values()) {
  			if (e.getType().equals(type)) {
  				enums.add(e);	
  			}
  		}
  		return enums;
  	}
}
