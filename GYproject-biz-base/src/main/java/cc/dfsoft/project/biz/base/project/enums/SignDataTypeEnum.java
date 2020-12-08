package cc.dfsoft.project.biz.base.project.enums;

public enum SignDataTypeEnum {

	
	TECHNOLOGY_TELL("1","会审交底"),
	STARTING_REPORT("2","开工报告"),
	CONSTRUCTION_ORGANIZATION("3","施工组织"),
	CONSTRUCT_DIARY("4","工程日志"),
	SECURITYINSPECTION("5","安全检查"),
	QUALITYINSPECTION("6","质量保证"),
	DESIGNALTERATION("7","变更记录"),
	ENGINEERING("8","签证记录"),
	RECTIFY_NOTICE("9","整改通知"),
	SHUTDOWN_RECORD("10","停复工记录"),
	RECTIFY_NOTICE_BACK("11","通知回复"),
	SHUTDOWN_APROVAL("12","复工申报"),
	NDE_RECORD("13","无损检测"),
	BUS_CON("1301","业务沟通"),
	STRENGTH_TEST("14","试压记录"),
	COMPLETE_REPORT("15","竣工报告"),
	
	
	ELECTRODE_RECORD("16","焊条领用"),
	ELECTRODE_BAKING("17","焊条烘烤"),
	GROOVE_RECORD("18","管沟开挖"),
	PIPELINEINSTALL("19","管道安装"),
	PE_PIPEWELDING("20","PE管焊接"),
	PE_PIPEWELDING_CHECK("201","PE管焊接"),
	PRESERVATIVE("21","防腐记录"),
	PRESERVATIVE_INPECT("22","防腐检查"),
	HIDDEN_WORKS("23","隐蔽工程"),
	UNDERGROUNDINPECT("24","埋地检查"),
	PURGE("25","吹扫记录"),
	TRENCH_BACKFILL("26","沟槽回填"),
	BALL_RECORD("27","通球记录"),
	INDOOR_POCKET_WATCH("28","户内挂表"),
	EQUIPMENTINSTALL("29","设备安装"),
	HOT_MELT_DOCKING("30","热熔对接"),
	ANODE_INSTALL("31","阳极安装"),
	WELDLINE_INPECT("32","管道焊缝检查"),
	PE_WELDLINE_INSPECT("33","PE管焊缝检查"),
	CLEAR_RECORD("34","清扫记录"),
	PIPE_WELD_RECORD("35","焊口记录"),
	
	
	PROJECT_SELF_CHECK("40","工程自检"),
	PRE_INSPECTION("41","预验收"),
	DIVISIONAL_ACCEPTANCE("42","分部验收"),
	JOINT_ACCEPTANCE("43","联合验收"),
	IGNITE("44","点火单签订"),
	SETTLEMENT_JONINTLY_SIGN("45","结算汇签"),
	SCENE_TAZHA("46","分部验收"),
	CONTRACT_REVIEW("47","合同评审");
	
	private final String value;
    
    private final String message;
    
    SignDataTypeEnum(String value, String message){
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    
    public static SignDataTypeEnum valueof(String value) {
    	for(SignDataTypeEnum e: SignDataTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
