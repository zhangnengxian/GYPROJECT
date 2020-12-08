package cc.dfsoft.project.biz.base.maintain.dto;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/1 20:44
 * @Version:1.0
 */
public class ImgResultDto<T> {
    private int  errno;//错误代码
    private String[] data;//存放数据

    public ImgResultDto() {
    }

    public ImgResultDto(int errno, String[] data) {
        this.errno = errno;
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
