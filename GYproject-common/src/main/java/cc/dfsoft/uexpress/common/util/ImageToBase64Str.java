package cc.dfsoft.uexpress.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ImageToBase64Str {
	public static void main(String[] args) {
		String base64Str = imageToBase64Str("F:/fulw.png");
        System.out.println(base64Str);
		
        boolean b = base64StrToImage(base64Str, "F:/fulw1.png");
        System.out.println(b);
	}
	
	  /**
     * 图片转base64字符串
     * @param imgFile 图片路径
     * @return
     */
    public static String imageToBase64Str(String imgFile) {
    	   InputStream inputStream = null;
    	   byte[] data = null;
    	   try {
    	     inputStream = new FileInputStream(imgFile);
    	     data = new byte[inputStream.available()];
    	     inputStream.read(data);
    	     inputStream.close();
    	   } catch (IOException e) {
    	     e.printStackTrace();
    	   }
    	   // 加密
    	   BASE64Encoder encoder = new BASE64Encoder();
    	   return encoder.encode(data);
    	 }
    
    /**
     * base64编码字符串转换为图片
     * @param imgStr base64编码字符串
     * @param path 图片路径
     * @return
     */
    public static boolean base64StrToImage(String imgStr, String path) {
    	   if (imgStr == null)
    	   return false;
    	   BASE64Decoder decoder = new BASE64Decoder();
    	   try {
    	     // 解密
    	     byte[] b = decoder.decodeBuffer(imgStr);
    	     // 处理数据
    	     for (int i = 0; i < b.length; ++i) {
    	       if (b[i] < 0) {
    	         b[i] += 256;
    	       }
    	     }
    	     //文件夹不存在则自动创建
    	     File tempFile = new File(path);
    	     if (!tempFile.getParentFile().exists()) {
    	       tempFile.getParentFile().mkdirs();
    	     }
    	     OutputStream out = new FileOutputStream(tempFile);
    	     out.write(b);
    	     out.flush();
    	     out.close();
    	     return true;
    	   } catch (Exception e) {
    	     return false;
    	   }
    	 }
}
