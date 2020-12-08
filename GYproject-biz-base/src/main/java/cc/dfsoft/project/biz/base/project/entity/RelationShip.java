package cc.dfsoft.project.biz.base.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RELATION_SHIP")
public class RelationShip {
	
	private String id;           //主键id
	private String projLtypeDes;	//工程类型描述
	
	public RelationShip(){
	}
	
	@Id
    @Column(name="ID", unique = true)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="PROJ_LTYPE_DES")
	public String getProjLtypeDes() {
		return projLtypeDes;
	}
	public void setProjLtypeDes(String projLtypeDes) {
		this.projLtypeDes = projLtypeDes;
	}
	
	
}	
