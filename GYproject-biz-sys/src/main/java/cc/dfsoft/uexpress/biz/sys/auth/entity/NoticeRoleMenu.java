package cc.dfsoft.uexpress.biz.sys.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 通知角色菜单
 * @author fuliwei
 *
 */
@Entity
@Table(name = "NOTICE_ROLE_MENU")
public class NoticeRoleMenu implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 8162351789400992645L;
	
	private String nrId;      //角色id
	private String nrNenuIds; //角色菜单
	//private String tenantId;
	
	@Id
	@Column(name = "NR_ID")
	public String getNrId() {
		return nrId;
	}
	public void setNrId(String nrId) {
		this.nrId = nrId;
	}
	
	@Column(name = "NR_MENU_IDS")
	public String getNrMenuIds() {
		return nrNenuIds;
	}
	public void setNrMenuIds(String nrNenuIds) {
		this.nrNenuIds = nrNenuIds;
	}
}
