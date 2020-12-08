package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.Date;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 计量表辅助查询类
 * @author wanghuijun
 * @createTime 2018年9月17日
 */
public class MeasurementReq extends PageSortReq{
	private String tableNumber;     // 计量表铭牌号
	private String projId;          // 工程id
	private String projNo;          // 工程编号
	private String tableType;        // 计量表类型
	private String tableTypeNumber;  // 计量类型编号
	private String producers;        // 计量表制造商
	private String model;            // 计量表型号
	private String producersNumber;  // 计量表制造商编号
	private String modelNumber;      // 计量表型号编号
	private Date dateOfProduction;   // 计量表生产日期
	private Date firstCheckDate;     // 首次检查日期
	private String leftDigits;       // 左侧位数
	private String rightDigits;       // 右侧位数
	private String readType;          // 读出类型
	private String airInletDirection; // 进气方向
	private String paintNumber;       // 油漆编号
	private String intelligentTable;   // 是否智能表
	private String tableCode;          // 表具传输模式
	private String other;              // 其他 
	public String getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public String getTableTypeNumber() {
		return tableTypeNumber;
	}
	public void setTableTypeNumber(String tableTypeNumber) {
		this.tableTypeNumber = tableTypeNumber;
	}
	public String getProducers() {
		return producers;
	}
	public void setProducers(String producers) {
		this.producers = producers;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProducersNumber() {
		return producersNumber;
	}
	public void setProducersNumber(String producersNumber) {
		this.producersNumber = producersNumber;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public Date getDateOfProduction() {
		return dateOfProduction;
	}
	public void setDateOfProduction(Date dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}
	public Date getFirstCheckDate() {
		return firstCheckDate;
	}
	public void setFirstCheckDate(Date firstCheckDate) {
		this.firstCheckDate = firstCheckDate;
	}
	public String getLeftDigits() {
		return leftDigits;
	}
	public void setLeftDigits(String leftDigits) {
		this.leftDigits = leftDigits;
	}
	public String getRightDigits() {
		return rightDigits;
	}
	public void setRightDigits(String rightDigits) {
		this.rightDigits = rightDigits;
	}
	public String getReadType() {
		return readType;
	}
	public void setReadType(String readType) {
		this.readType = readType;
	}
	public String getAirInletDirection() {
		return airInletDirection;
	}
	public void setAirInletDirection(String airInletDirection) {
		this.airInletDirection = airInletDirection;
	}
	public String getPaintNumber() {
		return paintNumber;
	}
	public void setPaintNumber(String paintNumber) {
		this.paintNumber = paintNumber;
	}
	public String getIntelligentTable() {
		return intelligentTable;
	}
	public void setIntelligentTable(String intelligentTable) {
		this.intelligentTable = intelligentTable;
	}
	public String getTableCode() {
		return tableCode;
	}
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
}
