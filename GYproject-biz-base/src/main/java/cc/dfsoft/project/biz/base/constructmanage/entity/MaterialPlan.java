package cc.dfsoft.project.biz.base.constructmanage.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cc.dfsoft.project.biz.base.constructmanage.enums.MaterialPlanFeedBackEnum;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;

/**
 * Created by Administrator on 2017/1/17 0017.
 */
@Entity
@Table(name = "MATERIAL_PLAN")
public class MaterialPlan implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7401601635434919677L;
	private String mpId;       			//主键id
    private String projId;				//工程id
    private String projNo;				//工程编号
    private String projName;			//工程名称
    private String proposer;			//申请人
    private String proposerId;			//申请人id
    private Date applicationDate;		//申请时间
    private String cuName;				//分包单位
    private String cuLegalRepresent;	//项目经理
    private String builder;				//甲方代表
    private String feedBacker;			//反馈人
    private String feedBackerId;		//反馈人id
    private Date feedBackDate;			//反馈时间
    private String isFeedBack;			//是否反馈
    private String isFeedBackDes;		//是否反馈
    private Date planReceiveDate;		//计划领用日期
    
    private List<MaterialPlanDetail> mpList;//
    private String feedBackDes;			//反馈信息
    private Date modifyReceiveDate;	    //反馈领用日期
    
    private String flag;//0 保存 1 推送
    
    private Date createDate;//生成时间
    private String isOwnNum;//是否欠量
    private String isExport;//是否导出 0 已导出 1 未导出
    private String isExportDes;		//是否反馈
    
    
    
    public MaterialPlan() {
	}
    
    @Id
    @Column(name = "MP_ID", unique = true)
    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    
    @Column(name = "PROJ_ID")
    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    
    @Column(name = "PROJ_NO")
    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    
    @Column(name = "PROJ_NAME")
    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    
    @Column(name = "PROPOSER")
    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    
    @Column(name = "PROPOSER_ID")
    public String getProposerId() {
        return proposerId;
    }

    public void setProposerId(String proposerId) {
        this.proposerId = proposerId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "APPLICATION_DATE")
    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    
    @Column(name = "CU_NAME")
    public String getCuName() {
        return cuName;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    
    @Column(name = "CU_LEGAL_REPRESENT")
    public String getCuLegalRepresent() {
        return cuLegalRepresent;
    }

    public void setCuLegalRepresent(String cuLegalRepresent) {
        this.cuLegalRepresent = cuLegalRepresent;
    }

    
    @Column(name = "BUILDER")
    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    
    @Column(name = "FEED_BACKER")
    public String getFeedBacker() {
        return feedBacker;
    }

    public void setFeedBacker(String feedBacker) {
        this.feedBacker = feedBacker;
    }

    
    @Column(name = "FEED_BACKER_ID")
    public String getFeedBackerId() {
        return feedBackerId;
    }

    public void setFeedBackerId(String feedBackerId) {
        this.feedBackerId = feedBackerId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FEED_BACK_DATE")
    public Date getFeedBackDate() {
        return feedBackDate;
    }

    public void setFeedBackDate(Date feedBackDate) {
        this.feedBackDate = feedBackDate;
    }

    
    @Column(name = "IS_FEED_BACK")
    public String getIsFeedBack() {
        return isFeedBack;
    }

    public void setIsFeedBack(String isFeedBack) {
        this.isFeedBack = isFeedBack;
    }
    
    @Transient
	public String getIsFeedBackDes() {
		for(MaterialPlanFeedBackEnum e: MaterialPlanFeedBackEnum.values()) {
	   		if(e.getValue().equals(this.isFeedBack)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setIsFeedBackDes(String isFeedBackDes) {
		this.isFeedBackDes = isFeedBackDes;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_RECEIVE_DATE")
	public Date getPlanReceiveDate() {
		return planReceiveDate;
	}

	public void setPlanReceiveDate(Date planReceiveDate) {
		this.planReceiveDate = planReceiveDate;
	}
	
	@Transient
	public List<MaterialPlanDetail> getMpList() {
		return mpList;
	}

	public void setMpList(List<MaterialPlanDetail> mpList) {
		this.mpList = mpList;
	}
	
	@Column(name = "FEED_BACK_DES")
	public String getFeedBackDes() {
		return feedBackDes;
	}

	public void setFeedBackDes(String feedBackDes) {
		this.feedBackDes = feedBackDes;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFY_RECEIVE_DATE")
	public Date getModifyReceiveDate() {
		return modifyReceiveDate;
	}

	public void setModifyReceiveDate(Date modifyReceiveDate) {
		this.modifyReceiveDate = modifyReceiveDate;
	}
	
	@Transient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Transient
	public String getIsOwnNum() {
		return isOwnNum;
	}

	public void setIsOwnNum(String isOwnNum) {
		this.isOwnNum = isOwnNum;
	}
	
	@Column(name = "IS_EXPROT")
	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}
	
	@Transient
	public String getIsExportDes() {
		for(ProcurementPlanExport e: ProcurementPlanExport.values()) {
	   		if(e.getValue().equals(this.isExport)) {
	   			return e.getMessage();
	   		}
	   	}
		return "";
	}

	public void setIsExportDes(String isExportDes) {
		this.isExportDes = isExportDes;
	}
}

