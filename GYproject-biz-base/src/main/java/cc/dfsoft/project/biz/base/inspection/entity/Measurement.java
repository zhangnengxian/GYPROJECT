package cc.dfsoft.project.biz.base.inspection.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 计量表实体类
 * @author wanghuijun
 * @createTime 2018年9月17日
 */
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

@Entity
@Table(name = "measurement")
public class Measurement {
	private static final long serialVersionUID = 5954773964000146659L;
	private String msId;            // 计量表ID
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
	private String readTypeNumber;    //读出类型编号
	private String paintNumber;       // 油漆编号
	private String intelligentTable;   // 是否智能表
	private String tableCode;          // 表具传输模式
	private String other;              // 其他
	private Integer version;           // 版本控制

	public Measurement() {
		
	}

	@Id
	@Column(name = "MS_ID",unique = true,nullable = false)
	public String getMsId() {
		return msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}
 
	@Column(name = "TABLE_NUMBER")
	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
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

	
	@Column(name = "TABLE_TYPE")
	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	
	@Column(name = "TABLE_TYPE_NUMBER")
	public String getTableTypeNumber() {
		return tableTypeNumber;
	}

	public void setTableTypeNumber(String tableTypeNumber) {
		this.tableTypeNumber = tableTypeNumber;
	}

	
	@Column(name = "PRODUCERS")
	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	 @Column(name = "MODEL")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "PRODUCERS_NUMBER")
	public String getProducersNumber() {
		return producersNumber;
	}

	public void setProducersNumber(String producersNumber) {
		this.producersNumber = producersNumber;
	}

	
	@Column(name = "MODEL_NUMBER")
	public String getModelNumber() {
		return modelNumber;
	}

	
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_PRODUCTION")
	public Date getDateOfProduction() {
		return dateOfProduction;
	}

	public void setDateOfProduction(Date dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIRST_CHECK_DATE")
	public Date getFirstCheckDate() {
		return firstCheckDate;
	}

	public void setFirstCheckDate(Date firstCheckDate) {
		this.firstCheckDate = firstCheckDate;
	}

	
	@Column(name = "LEFT_DIGITS")
	public String getLeftDigits() {
		return leftDigits;
	}

	public void setLeftDigits(String leftDigits) {
		this.leftDigits = leftDigits;
	}

	@Column(name = "RIGHT_DIGITS")
	public String getRightDigits() {
		return rightDigits;
	}

	public void setRightDigits(String rightDigits) {
		this.rightDigits = rightDigits;
	}
	
	@Column(name = "READ_TYPE")
	public String getReadType() {
		return readType;
	}

	public void setReadType(String readType) {
		this.readType = readType;
	}

	
	
	@Column(name = "READ_TYPE_NUMBER")
	public String getReadTypeNumber() {
		return readTypeNumber;
	}

	public void setReadTypeNumber(String readTypeNumber) {
		this.readTypeNumber = readTypeNumber;
	}

	@Column(name = "AIR_INLET_DIRECTION")
	public String getAirInletDirection() {
		return airInletDirection;
	}

	public void setAirInletDirection(String airInletDirection) {
		this.airInletDirection = airInletDirection;
	}
	
	@Column(name = "PAINT_NUMBER")
	public String getPaintNumber() {
		return paintNumber;
	}

	public void setPaintNumber(String paintNumber) {
		this.paintNumber = paintNumber;
	}

	@Column(name = "INTELLIGENT_TABLE")
	public String getIntelligentTable() {
		return intelligentTable;
	}

	public void setIntelligentTable(String intelligentTable) {
		this.intelligentTable = intelligentTable;
	}

	@Column(name = "TABLE_CODE")
	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	@Column(name = "OTHER")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
	
	@Version
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
