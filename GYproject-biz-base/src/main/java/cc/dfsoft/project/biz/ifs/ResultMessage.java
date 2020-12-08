package cc.dfsoft.project.biz.ifs;
/**
 * 
 * 描述:调用接口返回的信息类
 * @author liaoyq
 * @createTime 2017年11月9日
 */
public class ResultMessage {

	private String ret_type;
	private String ret_message;


	public ResultMessage() {}

	public ResultMessage(String ret_type, String ret_message) {
		this.ret_type = ret_type;
		this.ret_message = ret_message;
	}

	public String getRet_type() {
		return ret_type;
	}
	public void setRet_type(String ret_type) {
		this.ret_type = ret_type;
	}
	public String getRet_message() {
		return ret_message;
	}
	public void setRet_message(String ret_message) {
		this.ret_message = ret_message;
	}
	
}
