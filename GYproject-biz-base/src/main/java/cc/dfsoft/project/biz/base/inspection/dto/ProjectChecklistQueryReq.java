package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.Date;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ProjectChecklistQueryReq extends PageSortReq{

	private String projId;			//工程ID
	private String slPart;			//测试放线部位
	private String slBasePage;  	//依据材料页数
	private String projNo;      	//工程编号
	private Date   inspectionDate;  //报验日期
	private String pcDesId;			//检验单类型
	private String process;			//施工工序
	private int flag;
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getSlPart() {
		return slPart;
	}
	public void setSlPart(String slPart) {
		this.slPart = slPart;
	}
	public String getSlBasePage() {
		return slBasePage;
	}
	public void setSlBasePage(String slBasePage) {
		this.slBasePage = slBasePage;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public String getPcDesId() {
		return pcDesId;
	}
	public void setPcDesId(String pcDesId) {
		this.pcDesId = pcDesId;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
