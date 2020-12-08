package cc.dfsoft.project.biz.base.messagesync.hongju.pojo;


/**
 *@ Description: 交底信息
 *@ author: zhangnx
 *@ Date: 2019/11/22 16:27
 *@ Version:1.0
 * */
public class ConstructionWorkInfo {
    private String cwId;				//施工交底ID
    private String projNo;				//工程编号
    private String cwDate;				//交底日期
    private String cwContent;			//交底内容
    private String reviewContent;		//会审内容
    private String findQuestion;		//发现问题
    private String drawingNo;			//图纸编号
    private String signState;			//签字状态 null 或0 未完成，1已完成

    public String getCwId() {
        return cwId;
    }

    public void setCwId(String cwId) {
        this.cwId = cwId;
    }

    public String getProjNo() {
        return projNo;
    }

    public void setProjNo(String projNo) {
        this.projNo = projNo;
    }

    public String getCwDate() {
        return cwDate;
    }

    public void setCwDate(String cwDate) {
        this.cwDate = cwDate;
    }

    public String getCwContent() {
        return cwContent;
    }

    public void setCwContent(String cwContent) {
        this.cwContent = cwContent;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getFindQuestion() {
        return findQuestion;
    }

    public void setFindQuestion(String findQuestion) {
        this.findQuestion = findQuestion;
    }

    public String getDrawingNo() {
        return drawingNo;
    }

    public void setDrawingNo(String drawingNo) {
        this.drawingNo = drawingNo;
    }

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }

    @Override
    public String toString() {
        return "ConstructionWorkInfo{" +
                "cwId='" + cwId + '\'' +
                ", projNo='" + projNo + '\'' +
                ", cwDate=" + cwDate +
                ", cwContent='" + cwContent + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", findQuestion='" + findQuestion + '\'' +
                ", drawingNo='" + drawingNo + '\'' +
                ", signState='" + signState + '\'' +
                '}';
    }
}
