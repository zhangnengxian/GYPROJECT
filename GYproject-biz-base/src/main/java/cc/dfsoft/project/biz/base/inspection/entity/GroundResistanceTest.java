package cc.dfsoft.project.biz.base.inspection.entity;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 接地电阻测试
 * Created by Administrator on 2017/2/5 0005.
 */
@Entity
@Table(name = "GROUND_RESISTANCE_TEST")
public class GroundResistanceTest {
    private String id;						//主键ID
    private String pcId;					//报验单ID
    private String testPoint;				//测试点
    private BigDecimal specifiedResistance;	//规定阻值
    private BigDecimal testResistance;		//实测阻值
    private Date testDate;				    //实测日期
    private String weatherStuation;			//天气情况
    private String testResult;				//测试结果
    
    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

   
    @Column(name = "PC_ID")
    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

   
    @Column(name = "TEST_POINT")
    public String getTestPoint() {
        return testPoint;
    }

    public void setTestPoint(String testPoint) {
        this.testPoint = testPoint;
    }

   
    @Column(name = "SPECIFIED_RESISTANCE")
    public BigDecimal getSpecifiedResistance() {
        return specifiedResistance;
    }

    public void setSpecifiedResistance(BigDecimal specifiedResistance) {
        this.specifiedResistance = specifiedResistance;
    }

   
    @Column(name = "TEST_RESISTANCE")
    public BigDecimal getTestResistance() {
        return testResistance;
    }

    public void setTestResistance(BigDecimal testResistance) {
        this.testResistance = testResistance;
    }

   
    @Temporal(TemporalType.DATE)
    @Column(name = "TEST_DATE")
    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

   
    @Column(name = "WEATHER_STUATION")
    public String getWeatherStuation() {
        return weatherStuation;
    }

    public void setWeatherStuation(String weatherStuation) {
        this.weatherStuation = weatherStuation;
    }

   
    @Column(name = "TEST_RESULT")
    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

}
