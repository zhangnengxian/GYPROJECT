package cc.dfsoft.project.biz.base.complete.enums;

/**
 * 
 * 描述:竣工资料批量打印枚举
 * 存储资料类型标志、描述、报表文件
 * @author liaoyq
 * @createTime 2017年9月28日
 */
public enum CompletionDataPrintEnum {

	CONTRACT("100","安装合同","[{\"type\":\"11\",\"reportlet\":\"/contract/constructCivil.cpt\"},{\"type\":\"21\",\"reportlet\":\"/contract/constructPublic.cpt\"},{\"type\":\"31\",\"reportlet\":\"/contract/constructPipe.cpt\"}]"),
	SUB_CONTRACT("101-110604","施工合同","[{\"type\":\"11\",\"reportlet\":\"/subContract/subContractPrint.cpt\"},{\"type\":\"12\",\"reportlet\":\"/subContract/subContractPublic.cpt\"},{\"type\":\"13\",\"reportlet\":\"/subContract/subContractReform.cpt\"},{\"type\":\"14\",\"reportlet\":\"/subContract/subContractArtery.cpt\"},{\"type\":\"15\",\"reportlet\":\"/subContract/subContractPrint.cpt\"}]"),
	SU_BUDGET("102-110608","工程预算书","[{\"type\":\"\",\"reportlet\":\"/budget/budgetPrint.cpt\"}]"),
	SETTLEMENT("103-110804","工程结算书","[{\"type\":\"\",\"reportlet\":\"/settlement/settlementAudit1.cpt\"}]"),
	CON_TASK("104-110509","施工任务单","[{\"type\":\"\",\"reportlet\":\"/subContract/constructionTaskPrint.cpt\"}]"),
	TECHNOLOGY_TELL("200-120102","会审交底","[{\"type\":\"\",\"reportlet\":\"/constructmanage/technologyTell.cpt\"}]"),
	CU_ORGANIZATION("201-120104","施工组织","[{\"type\":\"\",\"reportlet\":\"/constructmanage/constructionOrganization.cpt\"}]"),
	STARTING_REPORT("202-120103","开工报告","[{\"type\":\"\",\"reportlet\":\"/constructmanage/startingReport.cpt\"}]"),
	CU_DAIRY("203-120201","施工日志","[{\"type\":\"\",\"reportlet\":\"/constructmanage/constructDiary.cpt\"}]"),
	CHANGE_MENT("204-110208","设计变更","[{\"type\":\"1\",\"reportlet\":\"/constructmanage/designerAlterProject.cpt\"},{\"type\":\"2\",\"reportlet\":\"/constructmanage/designAlterationUser.cpt\"}]"),
	VSIA_RECORD("205-120206","签证记录","[{\"type\":\"\",\"reportlet\":\"/constructmanage/engineering.cpt\"}]"),
	COMPLETE_REPORT("206-120502","竣工报告","[{\"type\":\"\",\"reportlet\":\"constructmanage/completeReport1.cpt\"}]"),
	ELECTRODE_RECORD("300-130121","焊条领用","[{\"type\":\"\",\"reportlet\":\"/inspection/electrodeRecord.cpt\"}]"),
	ELECTRODE_BACKING("301-130122","焊条烘烤","[{\"type\":\"\",\"reportlet\":\"/inspection/electrodeBaking.cpt\"}]"),
	GROOVE_RECORD("302-130103","管沟开挖","[{\"type\":\"\",\"reportlet\":\"/grooveRecord/grooveRecord1.cpt\"}]"),
	PIPELINE_INSTALL("303-130123","管道安装","[{\"type\":\"\",\"reportlet\":\"/inspection/pipelineInstall.cpt\"}]"),
	PIPE_WELDING("304-130106","钢管焊接","[{\"type\":\"\",\"reportlet\":\"/pipeWelding/pipeWelding1.cpt\"}]"),
	PEPIPEWELDING("305-130107","PE管焊接","[{\"type\":\"\",\"reportlet\":\"/pePipeWelding/pePipeWelding1.cpt\"}]"),
	PRESERVATIVE("306-130105","防腐记录","[{\"type\":\"1\",\"reportlet\":\"/preservative/preservativeJoint.cpt\"},{\"type\":\"2\",\"reportlet\":\"/preservative/preservative1.cpt\"}]"),
	PRESERVATIVE_INPECT("307-130126","防腐检查","[{\"type\":\"0\",\"reportlet\":\"/inspection/preservativeInpectJoint.cpt\"},{\"type\":\"1\",\"reportlet\":\"/inspection/preservativeInpectPaint.cpt\"}]"),
	HIDDEN_WORKS("308-130110","隐蔽工程","[{\"type\":\"\",\"reportlet\":\"/hiddenWorks/hiddenWorks1.cpt\"}]"),
	PURGE("309-130111","吹扫记录","[{\"type\":\"\",\"reportlet\":\"/inspection/purgeRecord.cpt\"}]"),
	UNDERGROUND_INPECT("310-130127","埋地检查","[{\"type\":\"\",\"reportlet\":\"/inspection/undergroundInpect.cpt\"}]"),
	TRENCH_BACKFILL("311-130109","沟槽回填","[{\"type\":\"\",\"reportlet\":\"/trenchBackfill/trenchBackfill.cpt\"}]"),
	BALL_RECORD("312-130130","通球记录","[{\"type\":\"\",\"reportlet\":\"/inspection/ballRecord.cpt\"}]"),
	INDOOR_POCKET_WATCH("313-130132","户内挂表","[{\"type\":\"\",\"reportlet\":\"/inspection/indoorPocketWatch.cpt\"}]"),
	EQUIPMENT_INSTALL("314-130133","设备安装","[{\"type\":\"\",\"reportlet\":\"/inspection/equipmentInstall.cpt\"}]"),
	HOTMELT_DOCKING("315-130134","热熔对接","[{\"type\":\"\",\"reportlet\":\"/inspection/hotMeltDocking.cpt\"}]"),
	ANODE_INSTALL("316-130135","阳极安装","[{\"type\":\"\",\"reportlet\":\"/inspection/anodeInstall.cpt\"}]"),
	WELD_LINE_INPECT("317-130124","管道焊缝检查","[{\"type\":\"\",\"reportlet\":\"/inspection/weldLineInpect.cpt\"}]"),
	PE_WELD_LINE_INPECT("318-130125","PE管焊缝检查","[{\"type\":\"1\",\"reportlet\":\"/inspection/peWeldLineInpectElectricMelt.cpt\"},{\"type\":\"2\",\"reportlet\":\"/inspection/peWeldLineInpectHotMelt.cpt\"}]"),
	STRENTH_TEST("319-130131","强度试验","[{\"type\":\"1\",\"reportlet\":\"/inspection/strengthTest.cpt\"},{\"type\":\"2\",\"reportlet\":\"/inspection/strengthTestIndoor1.cpt\"}]"),
	PIPE_WELD_RECORD("320-130137","焊口记录","[{\"type\":\"\",\"reportlet\":\"/inspection/pipeWeldRecord.cpt\"}]"),
	CLEAR_RECORD("321-130136","清扫记录","[{\"type\":\"\",\"reportlet\":\"/inspection/clearRecord.cpt\"}]"),
	MEASUREMENT_RECORD("322-130138","计量记录","[{\"type\":\"\",\"reportlet\":\"/inspection/measurementTable.cpt\"}]"),
	ONE_STOP_ACCEPT("400-110724","一站式打印","[{\"type\":\"\",\"reportlet\":\"/complete/oneStopAcceptancePrint.cpt\"}]"),
	SELF_CHECK("401-110711","自检表","[{\"type\":\"\",\"reportlet\":\"/complete/projectSelfCheckPrint.cpt\"}]"),
	DIVISIONAL_ACCEPT("402-110713","分部验收单","[{\"type\":\"\",\"reportlet\":\"/complete/divisionalAcceptancePrint.cpt\"}]"),
	JOINT_ACCEPT("403-110704","验收单","[{\"type\":\"\",\"reportlet\":\"/complete/jointAcceptancePrint.cpt\"}]"),
	PRE_INSPECTION("404-110722","预验收单","[{\"type\":\"\",\"reportlet\":\"/complete/preinspectionPrint.cpt\"}]");
	
	private final String value;
    
    private final String message;
    
    private final String cptUrl;
    
    CompletionDataPrintEnum(String value, String message,String cptUrl){
        this.value = value;
        this.message = message;
        this.cptUrl = cptUrl;
    }

    public String getValue() {
        return value;
    }
    
    public String getMessage(){
    	return message;
    }
   
	public String getCptUrl() {
		return cptUrl;
	}

	public static CompletionDataPrintEnum valueof(String value) {
    	for(CompletionDataPrintEnum e: CompletionDataPrintEnum.values()) {
    		if(e.getValue().equals(value)) {
    			return e;
    		}
    	}
    	return null;
    }
}
