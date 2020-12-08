package cc.dfsoft.project.biz.base.station.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/9/1.
 */
@Entity
@Table(name = "station_data")
public class StationData {
    private String sdId;
    private String sdType;
    private String sdDes;

    @Id
    @Column(name = "SD_ID")
    public String getSdId() {
        return sdId;
    }

    public void setSdId(String sdId) {
        this.sdId = sdId;
    }

    @Basic
    @Column(name = "SD_TYPE")
    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType;
    }

    @Basic
    @Column(name = "SD_DES")
    public String getSdDes() {
        return sdDes;
    }

    public void setSdDes(String sdDes) {
        this.sdDes = sdDes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationData that = (StationData) o;

        if (sdId != null ? !sdId.equals(that.sdId) : that.sdId != null) return false;
        if (sdType != null ? !sdType.equals(that.sdType) : that.sdType != null) return false;
        if (sdDes != null ? !sdDes.equals(that.sdDes) : that.sdDes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sdId != null ? sdId.hashCode() : 0;
        result = 31 * result + (sdType != null ? sdType.hashCode() : 0);
        result = 31 * result + (sdDes != null ? sdDes.hashCode() : 0);
        return result;
    }
}
