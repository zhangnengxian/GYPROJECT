package cc.dfsoft.project.biz.base.inspection.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
/**
 * 户内挂表通气记录实体类
 * @author liaoyq
 * @createTime 2017-7-21
 */
@Entity
@Table(name="indoor_pocket_watch")
public class IndoorPocketWatch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String floorName="floor"; //楼层字段前缀
	
	private String ipwId;			//`IPW_ID` varchar(30) NOT NULL COMMENT '户内挂表记录ID',
	private String projId;			//  `PROJ_ID` varchar(30) NOT NULL COMMENT '工程Id',
	private String pcId;			//  `PC_ID` varchar(30) DEFAULT NULL COMMENT '报验单ID',
	private String riserNo;			// `RISER_NO` varchar(30) DEFAULT NULL COMMENT '立管编号',
	private String installNum;		//  `INSTALL_NUM` varchar(30) DEFAULT NULL COMMENT '安装户数',
	private String floor1;			//  `FLOOR_1` varchar(4) DEFAULT NULL COMMENT '楼层1安装情况：1-已安装',
	private String floor2;			//  `FLOOR_2` varchar(4) DEFAULT NULL COMMENT '楼层1安装情况：1-已安装',
	private String floor3;			
	private String floor4;			
	private String floor5;			
	private String floor6;			
	private String floor7;			
	private String floor8;			
	private String floor9;			
	private String floor10;			
	private String floor11;			
	private String floor12;			
	private String floor13;			
	private String floor14;			
	private String floor15;			
	private String floor16;			
	private String floor17;			
	private String floor18;			
	private String floor19;			
	private String floor20;			
	private String floor21;			
	private String floor22;			
	private String floor23;			
	private String floor24;			
	private String floor25;			
	private String floor26;			
	private String floor27;			
	private String floor28;			
	private String floor29;			
	private String floor30;			
	private String floor31;			
	private String floor32;			
	private String floor33;			
	private String floor34;			
	private String floor35;			
	private String floorStart;	//安装楼层
	private String floorEnd;	//安装楼层
	private Integer version;	//版本控制
	public IndoorPocketWatch() {
		super();
	}

	@Id
	@Column(name="IPW_ID")
	public String getIpwId() {
		return ipwId;
	}

	public void setIpwId(String ipwId) {
		this.ipwId = ipwId;
	}

	@Column(name="PROJ_ID")
	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	@Column(name="PC_ID")
	public String getPcId() {
		return pcId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	@Column(name="RISER_NO")
	public String getRiserNo() {
		return riserNo;
	}

	public void setRiserNo(String riserNo) {
		this.riserNo = riserNo;
	}

	@Column(name="INSTALL_NUM")
	public String getInstallNum() {
		return installNum;
	}

	public void setInstallNum(String installNum) {
		this.installNum = installNum;
	}
	
	@Column(name="FLOOR_1")
	public String getFloor1() {
		return floor1;
	}

	public void setFloor1(String floor1) {
		this.floor1 = floor1;
	}
	@Column(name="FLOOR_2")
	public String getFloor2() {
		return floor2;
	}

	public void setFloor2(String floor2) {
		this.floor2 = floor2;
	}
	@Column(name="FLOOR_3")
	public String getFloor3() {
		return floor3;
	}

	public void setFloor3(String floor3) {
		this.floor3 = floor3;
	}
	@Column(name="FLOOR_4")
	public String getFloor4() {
		return floor4;
	}
	
	public void setFloor4(String floor4) {
		this.floor4 = floor4;
	}
	@Column(name="FLOOR_5")
	public String getFloor5() {
		return floor5;
	}

	public void setFloor5(String floor5) {
		this.floor5 = floor5;
	}
	@Column(name="FLOOR_6")
	public String getFloor6() {
		return floor6;
	}

	public void setFloor6(String floor6) {
		this.floor6 = floor6;
	}
	@Column(name="FLOOR_7")
	public String getFloor7() {
		return floor7;
	}

	public void setFloor7(String floor7) {
		this.floor7 = floor7;
	}
	@Column(name="FLOOR_8")
	public String getFloor8() {
		return floor8;
	}

	public void setFloor8(String floor8) {
		this.floor8 = floor8;
	}
	@Column(name="FLOOR_9")
	public String getFloor9() {
		return floor9;
	}

	public void setFloor9(String floor9) {
		this.floor9 = floor9;
	}
	@Column(name="FLOOR_10")
	public String getFloor10() {
		return floor10;
	}

	public void setFloor10(String floor10) {
		this.floor10 = floor10;
	}
	@Column(name="FLOOR_11")
	public String getFloor11() {
		return floor11;
	}

	public void setFloor11(String floor11) {
		this.floor11 = floor11;
	}
	@Column(name="FLOOR_12")
	public String getFloor12() {
		return floor12;
	}
	
	public void setFloor12(String floor12) {
		this.floor12 = floor12;
	}
	@Column(name="FLOOR_13")
	public String getFloor13() {
		return floor13;
	}

	public void setFloor13(String floor13) {
		this.floor13 = floor13;
	}
	@Column(name="FLOOR_14")
	public String getFloor14() {
		return floor14;
	}

	public void setFloor14(String floor14) {
		this.floor14 = floor14;
	}
	@Column(name="FLOOR_15")
	public String getFloor15() {
		return floor15;
	}

	public void setFloor15(String floor15) {
		this.floor15 = floor15;
	}
	@Column(name="FLOOR_16")
	public String getFloor16() {
		return floor16;
	}

	public void setFloor16(String floor16) {
		this.floor16 = floor16;
	}
	@Column(name="FLOOR_17")
	public String getFloor17() {
		return floor17;
	}

	public void setFloor17(String floor17) {
		this.floor17 = floor17;
	}
	@Column(name="FLOOR_18")
	public String getFloor18() {
		return floor18;
	}

	public void setFloor18(String floor18) {
		this.floor18 = floor18;
	}
	@Column(name="FLOOR_19")
	public String getFloor19() {
		return floor19;
	}

	public void setFloor19(String floor19) {
		this.floor19 = floor19;
	}
	@Column(name="FLOOR_20")
	public String getFloor20() {
		return floor20;
	}

	public void setFloor20(String floor20) {
		this.floor20 = floor20;
	}
	@Column(name="FLOOR_21")
	public String getFloor21() {
		return floor21;
	}

	public void setFloor21(String floor21) {
		this.floor21 = floor21;
	}
	@Column(name="FLOOR_22")
	public String getFloor22() {
		return floor22;
	}

	public void setFloor22(String floor22) {
		this.floor22 = floor22;
	}
	@Column(name="FLOOR_23")
	public String getFloor23() {
		return floor23;
	}

	public void setFloor23(String floor23) {
		this.floor23 = floor23;
	}
	@Column(name="FLOOR_24")
	public String getFloor24() {
		return floor24;
	}

	public void setFloor24(String floor24) {
		this.floor24 = floor24;
	}
	@Column(name="FLOOR_25")
	public String getFloor25() {
		return floor25;
	}

	public void setFloor25(String floor25) {
		this.floor25 = floor25;
	}
	@Column(name="FLOOR_26")
	public String getFloor26() {
		return floor26;
	}

	public void setFloor26(String floor26) {
		this.floor26 = floor26;
	}
	@Column(name="FLOOR_27")
	public String getFloor27() {
		return floor27;
	}

	public void setFloor27(String floor27) {
		this.floor27 = floor27;
	}
	@Column(name="FLOOR_28")
	public String getFloor28() {
		return floor28;
	}

	public void setFloor28(String floor28) {
		this.floor28 = floor28;
	}
	@Column(name="FLOOR_29")
	public String getFloor29() {
		return floor29;
	}

	public void setFloor29(String floor29) {
		this.floor29 = floor29;
	}
	@Column(name="FLOOR_30")
	public String getFloor30() {
		return floor30;
	}

	public void setFloor30(String floor30) {
		this.floor30 = floor30;
	}
	@Column(name="FLOOR_31")
	public String getFloor31() {
		return floor31;
	}

	public void setFloor31(String floor31) {
		this.floor31 = floor31;
	}
	@Column(name="FLOOR_32")
	public String getFloor32() {
		return floor32;
	}

	public void setFloor32(String floor32) {
		this.floor32 = floor32;
	}
	@Column(name="FLOOR_33")
	public String getFloor33() {
		return floor33;
	}

	public void setFloor33(String floor33) {
		this.floor33 = floor33;
	}
	@Column(name="FLOOR_34")
	public String getFloor34() {
		return floor34;
	}

	public void setFloor34(String floor34) {
		this.floor34 = floor34;
	}
	@Column(name="FLOOR_35")
	public String getFloor35() {
		return floor35;
	}

	public void setFloor35(String floor35) {
		this.floor35 = floor35;
	}

	@Transient
	public String getFloorStart() {
		return floorStart;
	}

	public void setFloorStart(String floorStart) {
		this.floorStart = floorStart;
	}
	@Transient
	public String getFloorEnd() {
		return floorEnd;
	}

	public void setFloorEnd(String floorEnd) {
		this.floorEnd = floorEnd;
	}
	@Version
	@Column(name="VERSION")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
