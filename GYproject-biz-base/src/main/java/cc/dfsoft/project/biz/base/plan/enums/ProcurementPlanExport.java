package cc.dfsoft.project.biz.base.plan.enums;

public enum ProcurementPlanExport {
	
	ALREADY_EXPORT("0","已导出"),
	HAVE_NOT_EXPORT("1","未导出");
	
	private final String value;
	private final String message;
	
	ProcurementPlanExport(String value,String message){
		this.value=value;
		this.message=message;
	}
	
	public String getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
	public static ProcurementPlanExport valueof(String value) {
    	for(ProcurementPlanExport e: ProcurementPlanExport.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
