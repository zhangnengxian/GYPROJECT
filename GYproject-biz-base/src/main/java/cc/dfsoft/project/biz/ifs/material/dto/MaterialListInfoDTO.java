package cc.dfsoft.project.biz.ifs.material.dto;
/**
 * 物资接口材料信息
 * @author fuliwei
 *
 */
public class MaterialListInfoDTO {
	
	/**物料计划单-规格对照*/
	private String mapping_guize;
	
	/**材料名称*/
	private String material_name;
	
	/**单位*/
	private String material_unit;
	
	/**设计数量*/
	private String material_num;
	
	/**材料表类型*/
	private String material_table_type;
	
	/**材料规格*/
	private String material_spec;
	
	/**本次领用量*/
	private String material_receive_num;
	
	/**领用总量*/
	private String get_anum;
	
	/**领料单-物料编码*/
	private String material_no;
	
	/**材料表ID*/
	private String primary_key;
	
	/**物料供应商名称*/
	private String supname;
	
	
	public String getMapping_guize() {
		return mapping_guize;
	}

	public void setMapping_guize(String mapping_guize) {
		this.mapping_guize = mapping_guize;
	}

	public String getMaterial_name() {
		return material_name;
	}

	public void setMaterial_name(String material_name) {
		this.material_name = material_name;
	}

	public String getMaterial_unit() {
		return material_unit;
	}

	public void setMaterial_unit(String material_unit) {
		this.material_unit = material_unit;
	}

	public String getMaterial_num() {
		return material_num;
	}

	public void setMaterial_num(String material_num) {
		this.material_num = material_num;
	}

	public String getMaterial_table_type() {
		return material_table_type;
	}

	public void setMaterial_table_type(String material_table_type) {
		this.material_table_type = material_table_type;
	}

	public String getMaterial_spec() {
		return material_spec;
	}

	public void setMaterial_spec(String material_spec) {
		this.material_spec = material_spec;
	}

	public String getMaterial_receive_num() {
		return material_receive_num;
	}

	public void setMaterial_receive_num(String material_receive_num) {
		this.material_receive_num = material_receive_num;
	}

	public String getGet_anum() {
		return get_anum;
	}

	public void setGet_anum(String get_anum) {
		this.get_anum = get_anum;
	}

	public String getMaterial_no() {
		return material_no;
	}

	public void setMaterial_no(String material_no) {
		this.material_no = material_no;
	}

	public String getPrimary_key() {
		return primary_key;
	}

	public void setPrimary_key(String primary_key) {
		this.primary_key = primary_key;
	}

	public String getSupname() {
		return supname;
	}

	public void setSupname(String supname) {
		this.supname = supname;
	}
	
}
