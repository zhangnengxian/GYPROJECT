package cc.dfsoft.project.biz.base.design.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

import java.util.Date;

/**
 * 查询设计派遣页面设计员表格
 * @author Administrator
 *
 */
public class DesignerQueryReq extends PageSortReq{

	private String projId;        //工程
	private String surveyer;        //勘察人
	private String surveyRegister;  //待勘察任务
	private String designDrawing;	//待设计任务
	private String projStatus;		//工程状态
	private String projStatusId;    //工程状态
	private String untiType;        //单位类型
	private String post;			//职务
	private String deptId;          //所属部门id
	private String designStationId; //设计院id
	private String postType;		//职务(设计员)
	private String postType2;		//职务(设计所长)
	private String corpId;			//分公司
	private String deptDivide;		//部门划分
	private String projNo;
	private String projName;
	private String ocoDate;					//委托日期
	private String signDateStart;
	private String signDateEnd;

	private String menuId;			//菜单id
	private String projAddr;		//工程地址
	private String designer;		//设计员
	public String getSignDateStart() {
		return signDateStart;
	}

	public void setSignDateStart(String signDateStart) {
		this.signDateStart = signDateStart;
	}

	public String getSignDateEnd() {
		return signDateEnd;
	}

	public void setSignDateEnd(String signDateEnd) {
		this.signDateEnd = signDateEnd;
	}

	public String getOcoDate() {
		return ocoDate;
	}

	public void setOcoDate(String ocoDate) {
		this.ocoDate = ocoDate;
	}

	public String getProjNo() {
		return projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	public String getSurveyer() {
		return surveyer;
	}
	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}
	public String getSurveyRegister() {
		return surveyRegister;
	}
	public void setSurveyRegister(String surveyRegister) {
		this.surveyRegister = surveyRegister;
	}
	public String getDesignDrawing() {
		return designDrawing;
	}
	public void setDesignDrawing(String designDrawing) {
		this.designDrawing = designDrawing;
	}
	public String getProjStatus() {
		return projStatus;
	}
	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}
	public String getProjStatusId() {
		return projStatusId;
	}
	public void setProjStatusId(String projStatusId) {
		this.projStatusId = projStatusId;
	}
	public String getUntiType() {
		return untiType;
	}
	public void setUntiType(String untiType) {
		this.untiType = untiType;
	}
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}

	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getDesignStationId() {
		return designStationId;
	}
	public void setDesignStationId(String designStationId) {
		this.designStationId = designStationId;
	}
	public String getPostType2() {
		return postType2;
	}
	public void setPostType2(String postType2) {
		this.postType2 = postType2;
	}
	public String getCorpId() {
		return corpId;
	}
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}
	public String getDeptDivide() {
		return deptDivide;
	}
	public void setDeptDivide(String deptDivide) {
		this.deptDivide = deptDivide;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjAddr() {
		return projAddr;
	}

	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}

	public String getDesigner() {
		return designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}
}
