package cc.dfsoft.project.biz.base.project.enums;

public enum CollectAccessoryItemEnum {
	
	
	CIVILIAN_ITEM("11","1.小区总平面布置图或红线图（图纸比例1:500或1:1000)"
					+ "2.小区地下已建的隐蔽工程（给排水、采暖、电力等）或其他综合管网图（1:500或1:1000）"
					+ "3.每栋用气住宅楼的建筑平面图、设备平面图（1:100）"
					+ "4.家用灶具明确安装位置壁挂炉用气提供壁挂炉型号,以及安装位置"),
	PUBLIC_ITEM("12","1.酒店、餐厅、锅炉房所在建筑总平面布置图或红线图（图纸比例1:500或1:1000）；"
					+ "2.建筑周边地下已建的隐蔽工程（给排水、采暖、电力等）或其他综合管网图（1:500或1:1000）；"
					+ "3.选定灶具（提供灶具用气压力和每种灶具用气量）或锅炉（锅炉吨位，用气量，用气压力，接口位置和接口尺寸等）的相关参数及运行模式。"
					+ "4.提供餐厅后堂平面布置图或锅炉房平面布置图（1:100），和相关设施的布置图（明确灶具或锅炉的摆放位置）。"),
	VEHICLE_USED_ITEM("13","1.厂区总平面布置图或红线图（图纸比例1:500或1:1000）;"
					+ "2.厂区地下已建的隐蔽工程（给排水、采暖、电力等）或其他综合管网图（1:500或1:1000）;"
					+ "3.用气设备,提供用气设备相关参数（单个设备的用气量,用气压力,接口位置和接口尺寸等）及运行模式；"
					+ "4.提供用气设备房间的平面布置图和相关设施布置图。");
	
	private final String value;
    
    private final String message;
    
    CollectAccessoryItemEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static ProjLtypeEnum valueof(String value) {
    	for(ProjLtypeEnum e: ProjLtypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
	
}
