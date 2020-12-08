package cc.dfsoft.project.biz.base.baseinfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.baseinfo.enums.AssistProgressTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.WorkFlowTypeEnum;

/**
 * Created by fuliwei on 2017/3/10.
 */
@Entity
@Table(name = "WORK_FLOW")
public class WorkFlow {
    private String wfId;			//主键ID
    private String corpId;			//分公司Id
    private String projType;		//工程类型
    private String workFlowCode;	//流程编码
    private String contributionCode;//流程编码
    
    private String contribution;	//出资类型------- 用于页面展示
    private String projTypeDes;		//工程类型------用于页面展示
    private String corp;			//分公司------- 用于页面展示
    
    private String typeId;			//工作流类型1 主流程 2 辅助流程
    private String typeDes;			//工作流类型-只读
    private String assistTypeId;	//辅助流程类型 如1通气 2签证 3变更 
    private String assistTypeDes;	//辅助流程类型-只读
    
    @Id
    @Column(name = "WF_ID")
    public String getWfId() {
        return wfId;
    }

    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    
    @Column(name = "CORP_ID")
    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Column(name = "PROJ_TYPE")
    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    @Column(name = "WORK_FLOW_CODE")
	public String getWorkFlowCode() {
		return workFlowCode;
	}

	public void setWorkFlowCode(String workFlowCode) {
		this.workFlowCode = workFlowCode;
	}

	@Transient
	public String getProjTypeDes() {
		return projTypeDes;
	}

	public void setProjTypeDes(String projTypeDes) {
		this.projTypeDes = projTypeDes;
	}

	@Transient
	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	@Transient
	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	@Column(name = "CONTRIBUTION_CODE")
	public String getContributionCode() {
		return contributionCode;
	}

	public void setContributionCode(String contributionCode) {
		this.contributionCode = contributionCode;
	}
	
	@Column(name = "TYPE_ID")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Column(name = "ASSIST_TYPE_ID")
	public String getAssistTypeId() {
		return assistTypeId;
	}

	public void setAssistTypeId(String assistTypeId) {
		this.assistTypeId = assistTypeId;
	}
	
	@Transient
	public String getTypeDes() {
		for(WorkFlowTypeEnum e: WorkFlowTypeEnum.values()) {
	   		if(e.getValue().equals(this.typeId)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}
	
	@Transient
	public String getAssistTypeDes() {
		for(AssistProgressTypeEnum e: AssistProgressTypeEnum.values()) {
	   		if(e.getValue().equals(this.assistTypeId)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setAssistTypeDes(String assistTypeDes) {
		this.assistTypeDes = assistTypeDes;
	}
}
