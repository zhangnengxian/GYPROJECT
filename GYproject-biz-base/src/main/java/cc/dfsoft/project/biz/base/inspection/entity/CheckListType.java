package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="CHECK_LIST_TYPE")
public class CheckListType {
	private String Id;
	private String Des;
	@Column(name="DES")
	public String getDes() {
		return Des;
	}
	public void setDes(String des) {
		Des = des;
	}
	@Id
	@Column(name="Id")
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	
}
