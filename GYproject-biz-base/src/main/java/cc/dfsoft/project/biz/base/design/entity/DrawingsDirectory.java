package cc.dfsoft.project.biz.base.design.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DrawingsDirectory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DRAWINGS_DIRECTORY")
public class DrawingsDirectory implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4514731415494338656L;
	private String drawDirectId;	//图纸目录ID
	private String diId;			//设计ID
	private String projId;			//工程ID
	private String projNo;			//工程编号
	private String drawingNo;		//图号
	private String drawDirect;		//图纸目录
	private Integer drawQuantity;	//数量
	private String remark;			//备注
	private String ddNum;//序号
	private String mapSheet;//图幅
	private String convertIntoNum;//折合张数

	// Constructors

	/** default constructor */
	public DrawingsDirectory() {
	}

	// Property accessors
	@Id
	@Column(name = "DRAW_DIRECT_ID", unique = true)
	public String getDrawDirectId() {
		return this.drawDirectId;
	}

	public void setDrawDirectId(String drawDirectId) {
		this.drawDirectId = drawDirectId;
	}

	@Column(name = "DI_ID")
	public String getDiId() {
		return this.diId;
	}

	public void setDiId(String diId) {
		this.diId = diId;
	}

	@Column(name = "PROJ_ID")
	public String getProjId() {
		return this.projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name = "PROJ_NO")
	public String getProjNo() {
		return this.projNo;
	}

	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}

	@Column(name = "DRAWING_NO")
	public String getDrawingNo() {
		return this.drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}

	@Column(name = "DRAW_DIRECT")
	public String getDrawDirect() {
		return this.drawDirect;
	}

	public void setDrawDirect(String drawDirect) {
		this.drawDirect = drawDirect;
	}

	@Column(name = "DRAW_QUANTITY")
	public Integer getDrawQuantity() {
		return this.drawQuantity;
	}

	public void setDrawQuantity(Integer drawQuantity) {
		this.drawQuantity = drawQuantity;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	@Column(name = "MAP_SHEET")
	public String getMapSheet() {
		return mapSheet;
	}

	public void setMapSheet(String mapSheet) {
		this.mapSheet = mapSheet;
	}
	
	@Column(name = "CONVERT_INTO_NUM")
	public String getConvertIntoNum() {
		return convertIntoNum;
	}

	public void setConvertIntoNum(String convertIntoNum) {
		this.convertIntoNum = convertIntoNum;
	}
	
	@Column(name = "DD_NUM")
	public String getDdNum() {
		return ddNum;
	}

	public void setDdNum(String ddNum) {
		this.ddNum = ddNum;
	}

}