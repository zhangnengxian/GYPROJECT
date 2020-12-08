package cc.dfsoft.project.biz.base.constructmanage.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class of bc_type.
 * 通知类型
 * @author generated by ERMaster
 * @version $Id$
 */
@Entity
@Table(name = "BC_TYPE")
public class BcType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2096845108407806431L;
	private String typeId;			//类型
	private String typeDes;			//类型描述
	private String relationTypeId;	//关联类型Id

	/**
	 * Constructor.
	 */
	public BcType() {
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Id
	@Column(name = "TYPE_ID", unique = true)
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeDes(String typeDes) {
		this.typeDes = typeDes;
	}

	@Column(name = "TYPE_DES")
	public String getTypeDes() {
		return this.typeDes;
	}

	public void setRelationTypeId(String relationTypeId) {
		this.relationTypeId = relationTypeId;
	}

	@Column(name = "RELATION_TYPE_ID")
	public String getRelationTypeId() {
		return this.relationTypeId;
	}
}
