package cc.dfsoft.project.biz.base.inspection.enums;

public enum ProjectChecklistTypeEnum {
	SURVEY_LINING("0","测量放线","","",""),
	ALTIMETRIC_SURVEY("0","高程测量","","",""),
	DERUSTING("0","除锈工序","","",""),
	//PRESERVATIVE("5","防腐工序"),
	//GROOVE_RECORD("3","沟槽记录"),
	//PIPE_WELDING("6","钢管焊接"),
	//PEPIPE_WELDING("7","PE管焊接"),
	//TRENC_HBACK_FILL("8","沟槽回填"),
	//HIDDEN_WORKS("9","隐蔽工程"),
	//PURGE("10","吹扫记录"),
	PRESSURE("0","试压记录","","",""),
	MONOMER_SET_UP("0","单体调校","","",""),
	HIDDEN_WORKS_ALARM("0","报警隐蔽工程","","",""),
	THREADING_PIPE("0","管内穿线","","",""),
	LOW_VOLTAGE_INSTALLATION("0","低压安装","","",""),
	INSULATION_RESISTANCE_TEST("0","绝缘电阻","","",""),
	GROUND_RESISTANCE_TEST("0","接地电阻测试","","",""),
	CONTROL_DEBUGGING("0","控制系统调试","","",""),
	
	ELECTRODE_RECORD("1","焊条领用","inspection/electrodeRecord","Electrode_Record","1"),
	ELECTRODE_BAKING("2","焊条烘烤","inspection/electrodeBaking","electrode_baking","1"),
	GROOVE_RECORD("3","管沟开挖","grooveRecord/grooveRecord1","groove_Record","1"),
    PIPELINE_INSTALL("4","管道安装","inspection/pipelineInstall","pipe_line_install","0"),
	PIPE_WELDING("5","钢管焊接","","",""),
	PEPIPE_WELDING("6","PE管焊接","pePipeWelding/pePipeWelding1","pepipe_welding","1"),
	PRESERVATIVE("7","防腐记录","inspection/preservative","derusting_preservative","1"),
	PRESERVATIVE_INPECT("8","防腐检查","inspection/preservativeInpectPaint","preservative_inpect","0"),
	HIDDEN_WORKS("9","隐蔽工程","hiddenWorks/hiddenWorks1","hidden_works","0"),
	PURGE("10","吹扫记录","inspection/purgeRecord","purge_record","1"),
	UNDER_GROUND_INPECT("11","埋地检查","inspection/undergroundInpect","Underground_Inpect","0"),
	TRENCH_BACK_FILL("12","沟槽回填","trenchBackfill/trenchBackfill","TrenchBack_fill","0"),
	VALVE_TEST("13","阀门试验","","",""),
	INSTALL_SUMMARY("14","安装汇总","","",""),
	BALL_RECORD("15","通球记录","inspection/ballRecord","Ball_Record","0"),
	STRENGTH_TEST("16","强度试验","inspection/strengthTest","strength_test","0"),
	INDOOR_POCKET_WATCH("17","户内挂表","inspection/indoorPocketWatch","indoor_pocket_watch","0"),
	EQUIPMENT_INSTALL("18","设备安装","inspection/equipmentInstall","equipment_install","0"),
	HOT_MELT_DOCKING("19","热熔对接","inspection/hotMeltDocking","hotmelt_docking","0"),
	ANODE_INSTALL("20","阳极安装","inspection/anodeInstall","anode_Install","0"),
	WELD_LINE_INPECT("21","管道焊缝检查","inspection/weldLineInpect","weld_line_inpect","1"),
	PE_WELD_LINE_INPECT("22","PE管焊缝检查","inspection/peWeldLineInspect","pe_weld_line_inspect","0"),
	CLEAR_RECORD("23","清扫记录","inspection/clearRecord","clear_record","1"),
	PIPE_WELD_RECORD("24","焊口记录","inspection/pipeWeldRecord","pipe_weld_record","0"),
	ACCEPTANCE_REGISTER("25","验收登记","inspection/acceptanceRegister","acceptance_record","0"),
	PRESSURE_RECORDS("26","复压记录","inspection/pressureRecords","pressure_records","0");
	
	private final String value;
    private final String message;
    private final String url;

	private final String tableName;
	private final String isCheckSign;//是否检查记录区签字1是 0否

    ProjectChecklistTypeEnum(String value, String message,String url,String tableName,String isCheckSign){
        this.value = value;
        this.message = message;
        this.url=url;
		this.tableName=tableName;
		this.isCheckSign=isCheckSign;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
    public String getUrl(){
    	return url;
    }

	public String getTableName() {
		return tableName;
	}

	public String getIsCheckSign() {
		return isCheckSign;
	}

	public static ProjectChecklistTypeEnum valueof(String value) {
    	for(ProjectChecklistTypeEnum e: ProjectChecklistTypeEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }	
}
