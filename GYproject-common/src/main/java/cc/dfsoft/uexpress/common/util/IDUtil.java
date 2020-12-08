package cc.dfsoft.uexpress.common.util;

import java.util.Date;
import java.util.Random;

/**
 * 唯一ID生成工具类
 * @author van.zheng
 *
 */
public class IDUtil {

	/**
	 * 生成唯一值
	 * @return
	 */
	public static String getUniqueId(String moduleCode){
		//YYYYMMDDHHMMSSSSS+模块号+随机数组成
		return DateUtil.getLongDateSSS(new Date())+ moduleCode + buildRandomNum(9);
	}
	
	/** 
     * 返回一个定长的随机字符串(只包含数字) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    private static String buildRandomNum(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(random.nextInt(10));  
        }  
        return sb.toString();  
    }  

    /**
	 * 生成部门ID
	 * @param id
	 * @return
	 */
	public static String getDeptId(String id){
		String str = String.valueOf(Integer.valueOf(id) + 1);
		int len = id.length();
		int strLen = str.length();
		
		if(len < strLen) return null;
		
		if(len == strLen) return str;
		
		StringBuffer num =  new StringBuffer();
		for(int i=0; i<len-strLen;i++){
			num.append("0");
		}
		num.append(str);
		return num.toString();
	}

	public static void main(String[] args) {
		//例如：客户模块-0001，表具模块-0002
		System.out.println(getUniqueId("0001"));
	}
	
	
	public static String randomNum(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(random.nextInt(10));  
        }  
        return sb.toString();  
    } 
	
}
