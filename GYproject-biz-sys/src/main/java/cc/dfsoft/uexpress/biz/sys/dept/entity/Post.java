package cc.dfsoft.uexpress.biz.sys.dept.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 职务
 * @author fuliwei
 *
 */
@Entity
@Table(name = "post")
public class Post implements java.io.Serializable{
	private static final long serialVersionUID = 837665215715192348L;
	
	private String id;      //id
	private String postName;//名称
	private String tableColumName;//对应表字段名称
	
	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "post_name")
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	@Column(name = "TABLE_COLUM_NAME")
	public String getTableColumName() {
		return tableColumName;
	}
	public void setTableColumName(String tableColumName) {
		this.tableColumName = tableColumName;
	}
	
	
	
}
