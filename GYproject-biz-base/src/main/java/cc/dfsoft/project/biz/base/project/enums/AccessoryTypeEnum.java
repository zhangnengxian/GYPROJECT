package cc.dfsoft.project.biz.base.project.enums;

public enum AccessoryTypeEnum {
	
	IMAGE_FILE("1","图片类（Jpg、gif、png等）"),
	  PDF_FILE("2","Pdf类（pdf文档）"),
	 WORD_FILE("3","Word类（doc，docx）"),
	EXCEL_FILE("4","Excel类（xls，xlsx）");
	
	private final String value;
    
    private final String message;
    
    AccessoryTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static AccessoryTypeEnum valueof(String value) {
    	for(AccessoryTypeEnum e: AccessoryTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
