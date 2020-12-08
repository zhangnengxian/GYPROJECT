package cc.dfsoft.project.biz.base.inspection.enums;
/** 
 * 报验单是否完成
 * * @author  作者 E-mail: 
 * * @date 创建时间：2017年11月28日 上午9:15:59 *
 *  @version 1.0 *
 *   @parameter  * 
 *   @since  * 
 *   @return  
 *   */
public enum CheckFlagEnum {
	FLAG_COMPLETE(1,"完成"),
	FLAG_NO_COMPLETE(0,"未完成");
	private final int value;
	private String message;
	CheckFlagEnum(int value,String message){
		this.value=value;
		this.message=message;
	}
	public int getValue(){
		return value;
	}
	public String getMessage(){
		return message;
	}
	
}
