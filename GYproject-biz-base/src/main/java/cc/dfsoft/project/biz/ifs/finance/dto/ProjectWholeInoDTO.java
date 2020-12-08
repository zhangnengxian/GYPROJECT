package cc.dfsoft.project.biz.ifs.finance.dto;

import java.util.List;

/**
 * 工程信息汇总DTO
 * @author duw
 *
 */
public class ProjectWholeInoDTO {
	
	//操作类型
	private String operate_type;
	//更新方式
	private String update_type;
	
	//工程基本信息
	private ProjectInfoDTO projectInfo;
	
	//建设合同信息
	private ConstractInfoDTO constractInfo;
	
	//施工合同信息
	private ConsConstractInfoDTO cons_constractInfo;
	
	//结算信息
	private SettleInfoDTO settleInfo;

	public String getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}

	public String getUpdate_type() {
		return update_type;
	}

	public void setUpdate_type(String update_type) {
		this.update_type = update_type;
	}

	public ProjectInfoDTO getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfoDTO projectInfo) {
		this.projectInfo = projectInfo;
	}

	public SettleInfoDTO getSettleInfo() {
		return settleInfo;
	}

	public void setSettleInfo(SettleInfoDTO settleInfo) {
		this.settleInfo = settleInfo;
	}

	public ConstractInfoDTO getConstractInfo() {
		return constractInfo;
	}

	public void setConstractInfo(ConstractInfoDTO constractInfo) {
		this.constractInfo = constractInfo;
	}

	public ConsConstractInfoDTO getCons_constractInfo() {
		return cons_constractInfo;
	}

	public void setCons_constractInfo(ConsConstractInfoDTO cons_constractInfo) {
		this.cons_constractInfo = cons_constractInfo;
	}

}
