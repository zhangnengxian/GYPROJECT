package cc.dfsoft.project.biz.base.inspection.entity;

import javax.persistence.*;

/**
 * 电 器 绝 缘 电 阻
 * Created by Administrator on 2017/2/5 0005.
 */
@Entity
@Table(name = "INSULATION_RESISTANCE_TEST")
public class InsulationResistanceTest {
    private String irtId;			//主键ID
    private String pcId;			//报验单ID
    private String loop;			//回路
    private String phaseAB;			//相间A-B
    private String phaseBC;			//相间B-C
    private String phaseCA;			//相间C-A
    private String zeroAN;			//相对零A_N
    private String zeroBN;			//相对零B_N
    private String zeroCN;			//相对零C_N
    private String earthAE;			//相对地A_E
    private String earthBE;			//相对地B_E
    private String earthCE;			//相对地C_E
    private String zeroEarthNE;		//零对地N_E

    @Id
    @Column(name = "IRT_ID")
    public String getIrtId() {
        return irtId;
    }

    public void setIrtId(String irtId) {
        this.irtId = irtId;
    }

    
    @Column(name = "PC_ID")
    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    
    @Column(name = "LOOP")
    public String getLoop() {
        return loop;
    }

    public void setLoop(String loop) {
        this.loop = loop;
    }

    
    @Column(name = "PHASE_A_B")
    public String getPhaseAB() {
        return phaseAB;
    }

    public void setPhaseAB(String phaseAB) {
        this.phaseAB = phaseAB;
    }

    
    @Column(name = "PHASE_B_C")
    public String getPhaseBC() {
        return phaseBC;
    }

    public void setPhaseBC(String phaseBC) {
        this.phaseBC = phaseBC;
    }

    
    @Column(name = "PHASE_C_A")
    public String getPhaseCA() {
        return phaseCA;
    }

    public void setPhaseCA(String phaseCA) {
        this.phaseCA = phaseCA;
    }

    
    @Column(name = "ZERO_A_N")
    public String getZeroAN() {
        return zeroAN;
    }

    public void setZeroAN(String zeroAN) {
        this.zeroAN = zeroAN;
    }

    
    @Column(name = "ZERO_B_N")
    public String getZeroBN() {
        return zeroBN;
    }

    public void setZeroBN(String zeroBN) {
        this.zeroBN = zeroBN;
    }

    
    @Column(name = "ZERO_C_N")
    public String getZeroCN() {
        return zeroCN;
    }

    public void setZeroCN(String zeroCN) {
        this.zeroCN = zeroCN;
    }

    
    @Column(name = "EARTH_A_E")
    public String getEarthAE() {
        return earthAE;
    }

    public void setEarthAE(String earthAE) {
        this.earthAE = earthAE;
    }

    
    @Column(name = "EARTH_B_E")
    public String getEarthBE() {
        return earthBE;
    }

    public void setEarthBE(String earthBE) {
        this.earthBE = earthBE;
    }

    
    @Column(name = "EARTH_C_E")
    public String getEarthCE() {
        return earthCE;
    }

    public void setEarthCE(String earthCE) {
        this.earthCE = earthCE;
    }

    
    @Column(name = "ZERO_EARTH_N_E")
    public String getZeroEarthNE() {
        return zeroEarthNE;
    }

    public void setZeroEarthNE(String zeroEarthNE) {
        this.zeroEarthNE = zeroEarthNE;
    }

}
