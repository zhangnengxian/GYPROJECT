package cc.dfsoft.uexpress.common.util;

//import cc.dfsoft.smartmeter.protocol.variableLength.HexBinaryAdapter;
//import cc.dfsoft.smartmeter.protocol.variableLength.KeyGenerator;
//import cc.dfsoft.smartmeter.protocol.variableLength.Mac;
//import cc.dfsoft.smartmeter.protocol.variableLength.NoSuchAlgorithmException;
//import cc.dfsoft.smartmeter.protocol.variableLength.SecretKey;
//import cc.dfsoft.smartmeter.protocol.variableLength.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringToolUtil {
	protected static final Logger logger = LoggerFactory
			.getLogger(StringToolUtil.class);

	/**
	 * 转换十六进制编码为字符串 。 比如：要发送24550410，--24=$, 55=U, 04= 10=.
	 * 就需要首先把24550410用toStringHex()转换成16进制字符串， 然后在调用toHexString()转换成字符串发出去。
	 * 
	 * @param s
	 * @return
	 */
	public static String toStringHex(String s) {
		if ("0x".equals(s.substring(0, 2))) {
			s = s.substring(2);
		}

		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	/**
	 * 4byte数组转为int 并从低位到高位排序
	 * @param res
	 * @return
	 */
	public static byte[] byte2intToByte(byte[] res,int lens) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
//		if(res.length < 4) {
//			logger.error("传入字节数组长度小于4，无法转换为整数！");
//			return null;
//		}
//		
//		if(res.length >4) {
//			logger.warn("穿入字节数组长度大于4，只把前4个字节转换为整数！");
//		}
		int targets =  0;
		if(res.length==1)targets = res[0] & 0xff;
		if(res.length==2)targets= (res[1] & 0xff) | ((res[0] << 8) & 0xff00) ;
		if(res.length==3)targets= (res[2] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或   
				| ((res[0] << 24) >>> 8) ;
		if(res.length==4)targets =(res[3] & 0xff) | ((res[2] << 8) & 0xff00) // | 表示安位或   
		| ((res[1] << 24) >>> 8) | (res[0] << 24);  
		int isj = 1;
		byte[] newres =toByteArray(targets,res.length);//将排序后的值放入一个新的数组中
	
		byte[] msg = new byte[lens];
		System.arraycopy(newres,0,msg,0,newres.length);
		int digit = newres.length;
		if(res.length<lens){
			for(int i=0;i<lens-res.length;i++){
				byte[] a = new byte[1];
				 a[0] = 0x00;
				System.arraycopy(a,0,msg,digit,a.length);
				digit++;
			}
		}
		String result = Bytes2HexString(msg);//将获取新的10进制数组转换为16进制字符串
		return msg;   
	}
	//代码转自：java int 与 byte转换 
	public static byte[] toByteArray(int iSource, int iArrayLen) {
	    byte[] bLocalArr = new byte[iArrayLen];
	    for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
	        bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
	    }
	    return bLocalArr;
	}



	/**
	 * 参考资料：http://origin100.iteye.com/blog/267165 高字节数组转换为float
	 * 
	 * @param b
	 *            byte[]
	 * @return float
	 */
	public static float hBytesToFloat(byte[] b) {
		int i = 0;
		Float F = new Float(0.0);
		i = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8
				| (b[3] & 0xff);
		return F.intBitsToFloat(i);
	}

	/**
	 * 双字节转整型
	 * 
	 * @param intByte
	 * @return
	 */
	public static int bytesToInt(byte[] intByte) {
		int fromByte = 0;
		if(intByte.length==1){
			int n = (intByte[0] < 0 ? (int) intByte[0] + 256 : (int) intByte[0]);
			return n;
		}
		for (int i = 0; i < 2; i++) {
			int n = (intByte[i] < 0 ? (int) intByte[i] + 256 : (int) intByte[i]) << (8 * i);
			// System.out.println(n);
			fromByte += n;
		}
		return fromByte;
	}

	/**
	 * 四字节转换成float。 结果不正确？ 参考资料：http://zhidao.baidu.com/question/99515322.html
	 * 
	 * @param b
	 * @return
	 */
	public static float getFloat(byte[] b) {
		// 4 bytes

		int accum = 0;
		float f = 0;

		// [0]
		f = byteToFloat(b);
		logger.debug("__getFloat__0_byteToFloat:f=" + f);

		byte[] l_b = new byte[b.length];
		System.arraycopy(b, 0, l_b, 0, b.length);
		logger.debug("__getFloat__System.arraycopy: b=" + Bytes2HexString(b)
				+ ",l_b=" + Bytes2HexString(l_b));

		// [1]
		System.arraycopy(b, 0, l_b, 0, b.length);
		for (int shiftBy = 0; shiftBy < 4; shiftBy++) {
			accum |= (l_b[shiftBy] & 0xff) << shiftBy * 8;
		}
		f = Float.intBitsToFloat(accum);
		logger.debug("__getFloat__1:f=" + f);
		 return f; 
	}

	// byte[]转float
	public static float byteToFloat(byte[] v) {
		ByteBuffer bb = ByteBuffer.wrap(v);
		FloatBuffer fb = bb.asFloatBuffer();
		return fb.get();
	}

	/**
	 * 低字节数组转换为float
	 * 
	 * @param b
	 *            byte[]
	 * @return float
	 */
	public static float lBytesToFloat(byte[] b) {
		int i = 0;
		Float F = new Float(0.0);
		if(b.length==4)
		i = ((((b[3] & 0xff) << 8 | (b[2] & 0xff)) << 8) | (b[1] & 0xff)) << 8
				| (b[0] & 0xff);
		if(b.length==3)
			i = ((((b[3] & 0xff) << 8 | (b[2] & 0xff)) << 8) | (b[1] & 0xff)) << 8
			| (b[0] & 0xff);
		return F.intBitsToFloat(i);
	}
	//将得到的数组进行排序
	public static String lBytesToString(byte[] b){
			String result ="";
			for(int i=1;i<=b.length;i++){
				result+=Integer.toString(b[b.length-i]&0xff,16).length()==1?"0"+Integer.toString(b[b.length-i]&0xff,16):Integer.toString(b[b.length-i]&0xff,16);
			}
			return result;
	}
	public static String hBytesToString(byte[] b) {
		Float F = new Float(0.0);
		String result ="";
		for(int i=0;i<b.length;i++){
			result+=b[i]&0xff;
		}
		return result;
	}
	/** 
     * 十六进制求和
     *  
     * @param bytes 
     * @return 
     */  
    public static int CS(byte[] bytes)  
    {  
        StringBuilder sb = new StringBuilder();  
        String tmp = null;  
        int sum =0;
        for (byte b : bytes)  
        {  
           // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制   
        tmp = Integer.toHexString(0xFF & b);  
            if (tmp.length() == 1)// 每个字节8为，转为16进制标志，2个16进制位   
            {  
                tmp = "0" + tmp;  
            }  
            sum=sum+Integer.parseInt(tmp.toString(),16);
            sb.append(tmp);  
        }  
        sum = sum%256;
        return sum;
    }  
    /**
     * ASCII默认起始和校验
     * @param bytes
     * @return
     */
    public static int CSASCII(byte[] bytes)  
    {  
        StringBuilder sb = new StringBuilder();  
        String tmp = null;  
        int sum =0;
        for (byte b : bytes)  
        {  
           // 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制   
        tmp = Integer.toHexString(0xFF & b-0x30);  
            if (tmp.length() == 1)// 每个字节8为，转为16进制标志，2个16进制位   
            {  
                tmp = "0" + tmp;  
            }  
            sum=sum+Integer.parseInt(tmp.toString(),16);
            sb.append(tmp);  
        }  
        sum = sum%256;
        return sum;
    }  

	/**
	 *
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String Bytes2HexString(byte[] b) {

		if (b == null || b.length == 0)
			return null;

		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" –> byte[]{0x2B, 0×44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		if (src == null)
			return null;

		int len = src.length() / 2;
		int i = 0;
		byte[] ret = new byte[len];
		// byte[] ret = new byte[len+2];
		byte[] tmp = src.getBytes();
		for (i = 0; i < len; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		// ret[i]='\r';//在最后一次循环，i已经指向了最后一个位置了。
		// ret[i+1]='\n';//在最后一次循环，i已经指向了最后一个位置了。
		return ret;
	}
	public static byte[] HexStringBytes(String src) {
		if (src == null)
			return null;

		int len = src.length();
		int i = 0;
		byte[] ret = new byte[len];
		// byte[] ret = new byte[len+2];
		byte[] tmp = src.getBytes();
		for (i = 0; i < len; i++) {
			ret[i] = uniteByte(tmp[i]);
		}
		// ret[i]='\r';//在最后一次循环，i已经指向了最后一个位置了。
		// ret[i+1]='\n';//在最后一次循环，i已经指向了最后一个位置了。
		return ret;
	}
	public static byte uniteByte(byte src0) {
		byte ret = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		return ret;
	}
	  /**
     * @param: intString :要解析的整形字符串
     */
    
    public static String Long2HexString (String intString)
    {
    	if (intString == null)
    		return null;
    	try {
	    	Long i = Long.parseLong(intString);
	    	String s = Long.toHexString(i);
	    	if (s.length()==1)
	    		s = "0"+s;
	    	return s;
    	} catch (Exception e)
    	{
    		return null;//如果解析失败，就表明这个字符串有问题，
    	}
    	
    }

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"–> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * MODBUS-RTU 模式采用 16 位 CRC 校验，多项式为 X16+X15+X2+1 CRC 开始时先把寄存器的
	 * 16位全部置成“1”,然后把相邻 2 个 8 位字节的数据放入当 前寄存器中，每个 8
	 * 位数据与该寄存器的内容进行异或运算，然后向最低有效位(LSB)方向 移位，用零填入最高有效位(MSB)后，再对 LSB 检查，若
	 * LSB=1，则寄存器与预置的固定值 异或，若 LSB=0，不作异或运算。 重复上述处理过程，直至移位 8 次，最后一次(第 8 次)移位后，下一个
	 * 8 位字节数 据与寄存器的当前值异或，再重复上述过程。全部处理完信息中的数据字节后，最终得到的 寄存器值为 CRC 值。
	 * 
	 * @return
	 */
	public static byte[] CalcCRC16(byte[] pPtr, int nCount) {
		int n = 0;
		int wCRC = 0xFFFF;

		while (nCount > 0) {
			nCount--;
			wCRC = wCRC ^ (int) (0x00FF & pPtr[n++]);
			for (int i = 0; i < 8; i++) {
				if ((wCRC & 0x0001) > 0)
					wCRC = wCRC >> 1 ^ 0xA001;
				else
					wCRC = wCRC >> 1;
			}
		}
		// wCRC = (wCRC<< 8) | ((wCRC >> 8) & 0xFF);
		byte[] cb = { (byte) (wCRC & (0xff)), (byte) ((wCRC >> 8) & 0xFF) };
		return cb;
	}
	public static int get_crc16 (byte[] bufData, int buflen, byte[] pcrc)  
    {  
        int ret = 0;  
        int CRC = 0x0000ffff;  
        int POLYNOMIAL = 0x0000a001;  
        int i, j;  
  
  
        if (buflen == 0)  
        {  
            return ret;  
        }  
        for (i = 0; i < buflen; i++)  
        {  
            CRC ^= ((int)bufData[i] & 0x000000ff);  
            for (j = 0; j < 8; j++)  
            {  
                if ((CRC & 0x00000001) != 0)  
                {  
                    CRC >>= 1;  
                    CRC ^= POLYNOMIAL;  
                }  
                else  
                {  
                    CRC >>= 1;  
                }  
            }  
            //System.out.println(Integer.toHexString(CRC));   
        }  
          
        System.out.println(Integer.toHexString(CRC));  
        pcrc[0] = (byte)(CRC & 0x00ff);  
        pcrc[1] = (byte)(CRC >> 8);  
  
        return ret;  
    }
//	//低位前高位后进行调整
//	public byte[] crcLower(byte[] pcrc){
//		byte[] newmac = new byte[2];
//		newmac[0] = (byte)(pcrc[0] & 0x00ff);  
//		newmac[1] = (byte)(pcrc[1] >> 8); 
//		return newmac;
//	}
	/**
	 * @功能: BCD码转为10进制串(阿拉伯数据)
	 * @参数: BCD码
	 * @结果: 10进制串
	 */
	public static String bcd2Str(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString();
	}

	/**
	 * 奇校验
	 * <ul>
	 * <li>就是让原有数据序列中（包括你要加上的一位）1的个数为奇数</li>
	 * <li>1000110（0）你必须添0这样原来有3个1已经是奇数了所以你添上0之后1的个数还是奇数个</li>
	 * </ul>
	 * 
	 * @param bytes
	 *            长度为8的整数倍
	 * @param parity
	 *            0:奇校验,1:偶校验
	 * @return
	 * @throws Exception
	 */
	public static byte[] parityOfOdd(byte[] bytes, int parity) throws Exception {
		if (bytes == null || bytes.length % 8 != 0) {
			throw new Exception("数据错误!");
		}
		if (!(parity == 0 || parity == 1)) {
			throw new Exception("参数错误!");
		}
		byte[] _bytes = bytes;
		String s; // 字节码转二进制字符串
		char[] cs; // 二进制字符串转字符数组
		int count; // 为1的总个数
		boolean lastIsOne; // 最后一位是否为1
		for (int i = 0; i < _bytes.length; i++) {
			// 初始化参数
			s = Integer.toBinaryString((int) _bytes[i]); // 字节码转二进制字符串
			cs = s.toCharArray();// 二进制字符串转字符数组
			count = 0;// 为1的总个数
			lastIsOne = false;// 最后一位是否为1
			for (int j = 0; j < s.length(); j++) {
				if (cs[j] == '1') {
					count++;
				}
				if (j == (cs.length - 1)) { // 判断最后一位是否为1
					if (cs[j] == '1') {
						lastIsOne = true;
					} else {
						lastIsOne = false;
					}
				}
			}
			// 偶数个1时
			if (count % 2 == parity) {
				// 最后一位为1,变为0
				if (lastIsOne) {
					_bytes[i] = (byte) (_bytes[i] - 0x01);
				} else {
					// 最后一位为0,变为1
					_bytes[i] = (byte) (_bytes[i] + 0x01);
				}
			}
		}
		return _bytes;
	}

	/**
	 * int转bcd码。
	 * 
	 * @param input
	 * @return
	 */
	public static byte[] IntToBCD(int input) {
		if (input > 9999 || input < 0)
			return null;

		int thousands = input / 1000;
		int hundreds = (input -= thousands * 1000) / 100;
		int tens = (input -= hundreds * 100) / 10;
		int ones = (input -= tens * 10);

		byte[] bcd = new byte[] { (byte) (thousands << 4 | hundreds),
				(byte) (tens << 4 | ones) };

		return bcd;
	}

	/**
	 * MAC算法工具类 对于HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512应用的步骤都是一模一样的。
	 * 具体看下面的代码
	 */

	/**
	 * 产生HmacMD5摘要算法的密钥
	 */
	public static byte[] initHmacMD5Key() throws NoSuchAlgorithmException {
		// 初始化HmacMD5摘要算法的密钥产生器
		KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");
		// 产生密钥
		SecretKey secretKey = generator.generateKey();
		// 获得密钥
		byte[] key = secretKey.getEncoded();
		return key;
	}

	/**
	 * HmacMd5摘要算法 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
	 */
	public static String encodeHmacMD5(byte[] data, byte[] key)
			throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
		// 实例化Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化mac
		mac.init(secretKey);
		// 执行消息摘要
		byte[] digest = mac.doFinal(data);
		return new HexBinaryAdapter().marshal(digest);// 转为十六进制的字符串
	}

	/**
	 * 产生HmacSHA1摘要算法的密钥www.2cto.com
	 */
	public static byte[] initHmacSHAKey() throws NoSuchAlgorithmException {
		// 初始化HmacMD5摘要算法的密钥产生器
		KeyGenerator generator = KeyGenerator.getInstance("HmacSHA1");
		// 产生密钥
		SecretKey secretKey = generator.generateKey();
		// 获得密钥
		byte[] key = secretKey.getEncoded();
		return key;
	}

	/**
	 * HmacSHA1摘要算法 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
	 */
	public static String encodeHmacSHA(byte[] data, byte[] key)
			throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, "HmacSHA1");
		// 实例化Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化mac
		mac.init(secretKey);
		// 执行消息摘要
		byte[] digest = mac.doFinal(data);
		return new HexBinaryAdapter().marshal(digest);// 转为十六进制的字符串
	}

	/**
	 * 产生HmacSHA256摘要算法的密钥
	 */
	public static byte[] initHmacSHA256Key() throws NoSuchAlgorithmException {
		// 初始化HmacMD5摘要算法的密钥产生器
		KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
		// 产生密钥
		SecretKey secretKey = generator.generateKey();
		// 获得密钥
		byte[] key = secretKey.getEncoded();
		return key;
	}

	/**
	 * HmacSHA1摘要算法 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
	 */
	public static String encodeHmacSHA256(byte[] data, byte[] key)
			throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
		// 实例化Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化mac
		mac.init(secretKey);
		// 执行消息摘要
		byte[] digest = mac.doFinal(data);
		return new HexBinaryAdapter().marshal(digest);// 转为十六进制的字符串
	}

	/**
	 * 产生HmacSHA256摘要算法的密钥
	 */
	public static byte[] initHmacSHA384Key() throws NoSuchAlgorithmException {
		// 初始化HmacMD5摘要算法的密钥产生器
		KeyGenerator generator = KeyGenerator.getInstance("HmacSHA384");
		// 产生密钥
		SecretKey secretKey = generator.generateKey();
		// 获得密钥
		byte[] key = secretKey.getEncoded();
		return key;
	}

	/**
	 * HmacSHA1摘要算法 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
	 */
	public static String encodeHmacSHA384(byte[] data, byte[] key)
			throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, "HmacSHA384");
		// 实例化Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化mac
		mac.init(secretKey);
		// 执行消息摘要
		byte[] digest = mac.doFinal(data);
		return new HexBinaryAdapter().marshal(digest);// 转为十六进制的字符串
	}

	/**
	 * 产生HmacSHA256摘要算法的密钥
	 */
	public static byte[] initHmacSHA512Key() throws NoSuchAlgorithmException {
		// 初始化HmacMD5摘要算法的密钥产生器
		KeyGenerator generator = KeyGenerator.getInstance("HmacSHA512");
		// 产生密钥
		SecretKey secretKey = generator.generateKey();
		// 获得密钥
		byte[] key = secretKey.getEncoded();
		return key;
	}

	/**
	 * HmacSHA1摘要算法 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
	 */
	public static String encodeHmacSHA512(byte[] data, byte[] key)
			throws Exception {
		// 还原密钥
		SecretKey secretKey = new SecretKeySpec(key, "HmacSHA512");
		// 实例化Mac
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		// 初始化mac
		mac.init(secretKey);
		// 执行消息摘要
		byte[] digest = mac.doFinal(data);
		return new HexBinaryAdapter().marshal(digest);// 转为十六进制的字符串
	}

	public static void main(String[] args) throws Exception {
		String testString = "asdasd";

		byte[] keyHmacMD5 = initHmacMD5Key();
		System.out.println(encodeHmacMD5(testString.getBytes(), keyHmacMD5));

		byte[] keyHmacSHA1 = initHmacSHAKey();
		System.out.println(encodeHmacSHA(testString.getBytes(), keyHmacSHA1));

		byte[] keyHmacSHA256 = initHmacSHA256Key();
		System.out.println(encodeHmacSHA256(testString.getBytes(),
				keyHmacSHA256));

		byte[] keyHmacSHA384 = initHmacSHA384Key();
		System.out.println(encodeHmacSHA384(testString.getBytes(),
				keyHmacSHA384));

		byte[] keyHmacSHA512 = initHmacSHA512Key();
		System.out.println(encodeHmacSHA512(testString.getBytes(),
				keyHmacSHA512));
	}
	/**
	 * 读取Byte字节数组.将传回的信息按不同字节分组,每组表达不同数值和信息.
	 * 每个byte减去0x30 之后 每2个byte合并为1个
	 * @param start
	 * @param len
	 * @param msg
	 * @return
	 */
	public static byte [] getBytes_Cut30H(byte [] msg){
		byte b []=new byte[msg.length];
		int num = 0;
		for (int i = 0;i<msg.length;i++){
			b[i]=(byte) (msg[num]-0x30);
			num++;
		}
		int count =0;
		
		byte bb []=new byte[msg.length/2];
		for (int i = 0;i<msg.length/2;i++){
			byte a1 = msg[count];
			byte a2 = msg[++count];
			bb[i] = (byte) (a1*16 + a2);
			count++;
		}
		return b;
	}
}
