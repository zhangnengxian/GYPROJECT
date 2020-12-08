package cc.dfsoft.project.biz.base.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/1/27 9:28
 * @Version:1.0
 */
@Entity
@Table(name = "T_SYS_CONSTANTS")
public class TSysConstants {
   private String id;
    private String pid;
    private String cnname;
    private String cnvalue;
    private String isuse;
    private String reserve1;
    private String reserve2;

    @Id
    @Column(name = "ID", unique = true)
    public String getId() {
        return id;
    }
    @Column(name = "PID")
    public String getPid() {
        return pid;
    }
    @Column(name = "CNNAME")
    public String getCnname() {
        return cnname;
    }
    @Column(name = "CNVALUE")
    public String getCnvalue() {
        return cnvalue;
    }
    @Column(name = "ISUSE")
    public String getIsuse() {
        return isuse;
    }
    @Column(name = "RESERVE1")
    public String getReserve1() {
        return reserve1;
    }
    @Column(name = "RESERVE2")
    public String getReserve2() {
        return reserve2;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public void setCnvalue(String cnvalue) {
        this.cnvalue = cnvalue;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    @Override
    public String toString() {
        return "TSysConstants{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cnname='" + cnname + '\'' +
                ", cnvalue='" + cnvalue + '\'' +
                ", isuse='" + isuse + '\'' +
                ", reserve1='" + reserve1 + '\'' +
                ", reserve2='" + reserve2 + '\'' +
                '}';
    }
}
