package cc.dfsoft.project.biz.ifs.finance.enums;
/**
 * 
 * 描述：操作类型枚举描述
 * @author liaoyq
 * @createTime 2017年11月8日
 */
public enum FinanceOperateTypeEnum {
	PROJ_ACCEPT_INSERT("J-A","立项存在借料工程新增"),
	PROJ_ACCEPT_UPDATE("J-B","立项存在借料工程修改"),
	PROJ_ACCEPT_DELETE("J-C","立项存在借料工程删除"),
	CONTRACT_SIGN("0-A","安装合同新增"),
	CONTRACT_UPDATE("0-B","安装合同变更"),
	CONTRACT_DELETE("0-C","安装合同删除"),
	SUBCONTRACT_SIGN("1-A","施工合同新增"),
	SUBCONTRACT_UPDATE("1-B","施工合同变更"),
	SUBCONTRACT_DELETE("1-C","施工合同删除"),
	PROJECT_SETTLEMENT("2-A","工程结算"),
	PROJECT_SETTLEMENT_UPDATE("2-B","工程结算信息修改");
	
	private final String value;
    
    private final String message;
    
    FinanceOperateTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static FinanceOperateTypeEnum valueof(String value) {
    	for(FinanceOperateTypeEnum e: FinanceOperateTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
