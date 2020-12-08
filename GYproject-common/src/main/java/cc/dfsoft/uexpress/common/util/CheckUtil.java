package cc.dfsoft.uexpress.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * 描述:验证工具类
 * @author liaoyq
 * @createTime 2018年8月28日
 */
public class CheckUtil {
	/**
	 * 正则表达式验证
	 * @param str
	 * @return
	 */
	public static boolean checkNumber(String str) {
		//验证数字
		String regex = "\\d+";
		if(StringUtil.isNotBlank(str) && str.matches(regex)){
			return true;
		}
		return false;
		
	}
	/**
	 * 正则表达式验证
	 * @param str
	 * @return
	 */
	public static boolean checkFloat(String str) {
		//验证数字
		String regex = "^(-?\\d+)(\\.\\d+)?$";
		if(StringUtil.isNotBlank(str) && str.matches(regex)){
			return true;
		}
		return false;
		
	}

	/**
	 * 验证字符串是否是日期
	 * @param plannedEndDate
	 * @return
	 */
	public static boolean checkDate(String plannedEndDate) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	    	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
	        format.setLenient(false);
	        format.parse(plannedEndDate);
	    } catch (ParseException e) {
	        // e.printStackTrace();
	        // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
	        convertSuccess=false;
	   } 
	   return convertSuccess;
	}
	
	public static void main(String[] args) {
		System.err.println(checkDate(""));
		System.err.println(checkFloat("0.009"));
	}

}
