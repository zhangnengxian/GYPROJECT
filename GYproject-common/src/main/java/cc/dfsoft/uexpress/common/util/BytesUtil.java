package cc.dfsoft.uexpress.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对byte数组的运算
 * @author JOHN
 * 20150208
 */
public class BytesUtil {
	protected static final Logger logger = LoggerFactory.getLogger(BytesUtil.class);
	
	/**
	 * int转为4byte数组
	 * @param res
	 * @return
	 */
	public static byte[] int2byte(int res) {  
		byte[] targets = new byte[4];  
		  
		targets[0] = (byte) (res & 0xff);// 最低位   
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位   
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位   
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。   
		return targets;   
	}
	
	/**
	 * 4byte数组转为int
	 * @param res
	 * @return
	 */
	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
		if(res.length < 4) {
			logger.error("传入字节数组长度小于4，无法转换为整数！");
			return 0;
		}
		
		if(res.length >4) {
			logger.warn("穿入字节数组长度大于4，只把前4个字节转换为整数！");
		}
		
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或   
		| ((res[2] << 24) >>> 8) | (res[3] << 24);   
		return targets;   
	} 
	
	/**
	 * 从source数组的sourceStart位置拷贝到dest数组从destStart开始的位置，拷贝长度为length
	 * @param source
	 * @param sourceStart
	 * @param dest
	 * @param destStart
	 * @param length
	 */
//	public static void copy(byte[] source, int sourceStart, byte[] dest, int destStart, int length) {
//		
//		for(int i = 0; i < length; i++) {
//			dest[destStart + i] = source[sourceStart + i];
//		}
//	}
	
	/**
	 * 把source中的所有字节拷贝到dest中从destStart开始的位置
	 * @param source
	 * @param sourceStart
	 * @param dest
	 * @param destStart
	 */
	public static void copy(byte[] source, byte[] dest, int destStart) {		
		System.arraycopy(source, 0, dest, destStart, source.length);
	}

}
