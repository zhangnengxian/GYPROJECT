package cc.dfsoft.project.biz.base.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 描述:税率实体类
 * @author liaoyq
 * @createTime 2017年11月26日
 */
@Entity
@Table(name="INCREMENT")
public class Increment {
	private String inId;			//表ID
	private String increment;		//税率值
	private String incrementCode;	//税率编码
	
	
	public Increment() {
		super();
	}
	@Id
	@Column(name="IN_ID")
	public String getInId() {
		return inId;
	}
	public void setInId(String inId) {
		this.inId = inId;
	}
	
	@Column(name="INCREMENT")
	public String getIncrement() {
		return increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	
	@Column(name="INCREMENT_CODE")
	public String getIncrementCode() {
		return incrementCode;
	}
	public void setIncrementCode(String incrementCode) {
		this.incrementCode = incrementCode;
	}
	
	

}
