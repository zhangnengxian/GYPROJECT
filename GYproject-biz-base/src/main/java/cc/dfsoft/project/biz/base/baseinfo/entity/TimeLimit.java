package cc.dfsoft.project.biz.base.baseinfo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIME_LIMIT")
public class TimeLimit {
    private String tlId;			//主键Id
    private String tlType;			//类型
    private BigDecimal tlDuration;	//时长

    @Id
    @Column(name = "TL_ID")
    public String getTlId() {
        return tlId;
    }

    public void setTlId(String tlId) {
        this.tlId = tlId;
    }

    @Column(name = "TL_TYPE")
	public String getTlType() {
		return tlType;
	}

	public void setTlType(String tlType) {
		this.tlType = tlType;
	}

	 @Column(name = "TL_DURATION")
	public BigDecimal getTlDuration() {
		return tlDuration;
	}

	public void setTlDuration(BigDecimal tlDuration) {
		this.tlDuration = tlDuration;
	}

}
