package cc.dfsoft.project.biz.base.project.enums;
/**
 * 
 * 描述:默认贵阳的，其他的分公司地区编码为分公司编码
 * @author liaoyq
 * @createTime 2018年9月18日
 */
public enum AreaEnum {
	
	GUIZHOU("5200","贵州省"),
	GUIYANG("5201","贵阳市"),
	LIUPANSHUI("5202","六盘水市"),
	ZUNYI("5203","遵义市"),
	ANSHUN("5204","安顺市"),
	BIJIE("5205","毕节市"),
	TONGREN("5206","铜仁市"),
	QIANXINAN("5223","黔西南布依族苗族自治州"),
	QIANDONGNAN("5226","黔东南苗族侗族自治州"),
	QIANNAN("5227","黔南布依族苗族自治州");
	
	private final String value;
    
    private final String message;
    
    AreaEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AreaEnum valueof(String value) {
    	for(AreaEnum e: AreaEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
    
    public static String[] getMessages(){
    	String[] messages = new String[AreaEnum.values().length];
    	for(int i=0;i<AreaEnum.values().length;i++){
    		messages[i]=AreaEnum.values()[i].getMessage();
    	}
    	return messages;
    }
}
