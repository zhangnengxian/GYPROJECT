package cc.dfsoft.project.biz.base.project.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class MenuBackSetReq extends PageSortReq {
    private String mbsId;				//主键id
    private String corpId;	//分公司Id

    private String menuName;//菜单名称
    private String projectType;//工程类型
    private String contributionMode;//出资方式



    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getMbsId() {
        return mbsId;
    }

    public void setMbsId(String mbsId) {
        this.mbsId = mbsId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getContributionMode() {
        return contributionMode;
    }

    public void setContributionMode(String contributionMode) {
        this.contributionMode = contributionMode;
    }
}
