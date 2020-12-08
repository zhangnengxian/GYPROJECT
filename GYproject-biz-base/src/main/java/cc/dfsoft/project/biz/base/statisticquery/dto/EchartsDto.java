package cc.dfsoft.project.biz.base.statisticquery.dto;

import java.util.Map;

/**
 * 用于堆积柱状图返回数据
 * @author pengtt
 * @createTime 2016-09-01
 */
public class EchartsDto {
	
	private String name;
	private Map itemStyle;
	private String type;
	private String stack;
	private int[] data;
	private Map label;
	private Double[] doubleData;
	
	private String barCategoryGap;
	private String barGap;
	
	public String getBarCategoryGap() {
		return barCategoryGap;
	}
	public void setBarCategoryGap(String barCategoryGap) {
		this.barCategoryGap = barCategoryGap;
	}
	public String getBarGap() {
		return barGap;
	}
	public void setBarGap(String barGap) {
		this.barGap = barGap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map getItemStyle() {
		return itemStyle;
	}
	public void setItemStyle(Map itemStyle) {
		this.itemStyle = itemStyle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public int[] getData() {
		return data;
	}
	public void setData(int[] data) {
		this.data = data;
	}
	public Map getLabel() {
		return label;
	}
	public void setLabel(Map label) {
		this.label = label;
	}
	public Double[] getDoubleData() {
		return doubleData;
	}
	public void setDoubleData(Double[] doubleData) {
		this.doubleData = doubleData;
	}
	
	
}
