package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;

import java.util.Date;

/**
 *@ Description: 工程基本信息（鸿巨）
 *@ author: zhangnx
 *@ Date: 2019/11/22 15:23
 *@ Version:1.0
 * */
public class SignInfo {
    private String signatureName; //签字人
    private String signTime;        //签字日期
    private String postName;      //签字职务

    public String getSignatureName() {
        return signatureName;
    }

    public void setSignatureName(String signatureName) {
        this.signatureName = signatureName;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "SignInfo{" +
                "signatureName='" + signatureName + '\'' +
                ", signTime=" + signTime +
                ", postName='" + postName + '\'' +
                '}';
    }
}
