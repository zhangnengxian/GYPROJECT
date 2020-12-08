package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.BasicProjectCheckItem;
import cc.dfsoft.project.biz.base.inspection.entity.GuaranteeItemsList;
import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class ThreadingPipeReq extends PageSortReq{
	
	private String pcId;		//报验单id
	private String itemType;	//类型id
	private List<GuaranteeItemsList>  firstList;//保证项目
	private List<BasicProjectCheckItem> secondList;//基本项目
	private List<BasicProjectCheckItem> thirdList; //允许偏差项目
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public List<GuaranteeItemsList> getFirstList() {
		return firstList;
	}
	public void setFirstList(List<GuaranteeItemsList> firstList) {
		this.firstList = firstList;
	}
	public List<BasicProjectCheckItem> getSecondList() {
		return secondList;
	}
	public void setSecondList(List<BasicProjectCheckItem> secondList) {
		this.secondList = secondList;
	}
	public List<BasicProjectCheckItem> getThirdList() {
		return thirdList;
	}
	public void setThirdList(List<BasicProjectCheckItem> thirdList) {
		this.thirdList = thirdList;
	}
	
}
