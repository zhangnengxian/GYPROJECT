package cc.dfsoft.project.biz.base.baseinfo.dto;

public class WorkFlowDto {
	
    private int actionNo;		//步骤序号
    private String actionCode;	//步骤编码
    private String actionDes;	//步骤描述
    
	public int getActionNo() {
		return actionNo;
	}
	public void setActionNo(int actionNo) {
		this.actionNo = actionNo;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getActionDes() {
		return actionDes;
	}
	public void setActionDes(String actionDes) {
		this.actionDes = actionDes;
	}

    
}
