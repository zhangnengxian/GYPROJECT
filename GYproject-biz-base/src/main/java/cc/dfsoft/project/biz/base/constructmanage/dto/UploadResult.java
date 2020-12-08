package cc.dfsoft.project.biz.base.constructmanage.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.entity.VisaQuantitiesRecord;

public class UploadResult {

	private String result;
	private String drawUrl;
	private String operateId;
	private String stepId;	 //步骤描述
	private String alPath;
	private String step;	//步骤ID
	//private List<VisaQuantitiesRecord> list;//页面
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDrawUrl() {
		return drawUrl;
	}

	public void setDrawUrl(String drawUrl) {
		this.drawUrl = drawUrl;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getAlPath() {
		return alPath;
	}

	public void setAlPath(String alPath) {
		this.alPath = alPath;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	
	/*public List<VisaQuantitiesRecord> getList() {
		return list;
	}

	public void setList(List<VisaQuantitiesRecord> list) {
		this.list = list;
	}*/
	
	
}