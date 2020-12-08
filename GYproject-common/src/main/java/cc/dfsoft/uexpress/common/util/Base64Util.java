package cc.dfsoft.uexpress.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.freehep.graphicsio.swf.End;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 描述:base64加密解密工具类
 * @author liaoyq
 * @createTime 2017年11月9日
 */
public class Base64Util {
	
	
	
	/** 
     * 使用jdk的base64 加密字符串 
     * */  
    public static String jdkBase64Encoder(String str)  
    {  
        BASE64Encoder encoder = new BASE64Encoder();  
        String encode = encoder.encode(str.getBytes());  
        return encode;  
    }  
    /** 
     * 使用jdk的base64 解密字符串 
     * 返回为null表示解密失败 
     * */  
    public static String jdkBase64Decoder(String str)  
    {  
        BASE64Decoder decoder = new BASE64Decoder();  
        String decode=null;  
        try {  
            decode = new String( decoder.decodeBuffer(str));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return decode;  
    }  
    
    public static void main(String[] args) {
		String str="peng01";
		String encode = jdkBase64Encoder(str);
		System.err.println(encode);
		
		String decode = jdkBase64Decoder("NjMzMTIz");
		System.err.println(decode);
    	//String base64Str = imageToBase64Str("D:/201808311404334350410567093994.png");
        //System.out.println(base64Str);
		
        //boolean b = base64StrToImage("data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PCFET0NUWVBFIHN2ZyBQVUJMSUMgIi0vL1czQy8vRFREIFNWRyAxLjEvL0VOIiAiaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkIj48c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgdmVyc2lvbj0iMS4xIiB3aWR0aD0iNDkwIiBoZWlnaHQ9IjE0MCI+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxIDQwIGMgMC4wOSAwLjAzIDMuMzQgMS45MyA1IDIgYyAxMy41MiAwLjU1IDI4Ljk3IDAuNTMgNDQgMCBjIDE0LjA1IC0wLjUgMjcuNDYgLTEuMjUgNDEgLTMgYyA3LjExIC0wLjkyIDE5LjkxIC00LjYyIDIxIC01IGwgLTQgLTEiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDcxIDIgYyAtMC4wNyAwLjQ1IC0zLjcyIDE3LjI0IC00IDI2IGMgLTEuMDYgMzMuMjggMC4zMSA2Ni4wMSAwIDEwMCBjIC0wLjAzIDMuNzUgLTEgMTEgLTEgMTEiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDQ5IDYzIGMgLTAuMDcgMC4wNyAtMi45OSAyLjQ4IC00IDQgYyAtNC4xOCA2LjI2IC03LjIzIDE0LjQ2IC0xMiAyMCBjIC01LjA4IDUuODkgLTE5IDE2IC0xOSAxNiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gNjkgNjIgYyAwLjA3IDAuMzUgMS43MSAxMy44NSA0IDIwIGMgMy44NyAxMC4zOCAxNSAzMSAxNSAzMSIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTMzIDUgYyAwLjU0IDAgMjAuNjkgMC40NyAzMSAwIGMgNC4zOCAtMC4yIDguODMgLTEuMDcgMTMgLTIgYyAxLjcxIC0wLjM4IDQuODQgLTIuNDcgNSAtMiBjIDAuMjUgMC43NSAtMS42MyA1LjYzIC0zIDggYyAtMi4xOCAzLjc3IC01LjEyIDcuNTEgLTggMTEgYyAtMS43OSAyLjE4IC00LjI3IDMuNzkgLTYgNiBjIC00LjI0IDUuNDIgLTEyLjgyIDE1LjQ3IC0xMiAxNyBjIDAuNzcgMS40MyAxMy4yNCAtMi40MyAxOSAtNCBjIDEuMDcgLTAuMjkgMS45MiAtMS41OSAzIC0yIGMgNi42NCAtMi40OSAxMy45NCAtNS4yMyAyMSAtNyBjIDQuODMgLTEuMjEgMTEuOTggLTIuNTMgMTUgLTIgYyAxLjA3IDAuMTkgMS43OCAzLjM2IDIgNSBjIDAuNDEgMy4wNSAwLjQ0IDYuOCAwIDEwIGMgLTAuNTQgMy45NiAtMi4zIDcuODkgLTMgMTIgYyAtMS4zMiA3LjcxIC0yLjY3IDE1LjIyIC0zIDIzIGMgLTAuNjggMTYuMDkgMC42OSA0MS42NyAwIDQ4IGMgLTAuMDggMC43MSAtMy44OSAtMC44NSAtNSAtMiBjIC02Ljk5IC03LjI1IC0yMiAtMjYgLTIyIC0yNiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMTM2IDMxIGMgMCAwLjMyIDAuNCAxMi4wNCAwIDE4IGMgLTAuMjcgNC4wMyAtMC44NyA4LjIgLTIgMTIgYyAtMS41IDUuMDUgLTMuODYgMTAuMTcgLTYgMTUgYyAtMC40OCAxLjA4IC0xLjE3IDIuMjYgLTIgMyBjIC0xLjk5IDEuNzcgLTcgNSAtNyA1Ii8+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAxNzggNDQgYyAtMC4wNCAwLjA1IC0xLjY0IDEuOTIgLTIgMyBjIC0xLjg2IDUuNTggLTIuODUgMTIuMTYgLTUgMTggYyAtMi41MyA2Ljg1IC01LjY2IDEzLjMzIC05IDIwIGMgLTIuNzkgNS41OCAtNS41OCAxMC44NyAtOSAxNiBjIC01LjMzIDggLTExLjA3IDE1LjA5IC0xNyAyMyBjIC0xLjMzIDEuNzcgLTIuNTcgMy41NyAtNCA1IGwgLTMgMiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMjc3IDQ2IGMgMC4zOSAwIDE0LjQ2IDAuMjggMjIgMCBjIDEwLjk5IC0wLjQxIDIxLjExIC0wLjg0IDMyIC0yIGMgMTQuNzQgLTEuNTcgMjguODggLTMuNjkgNDMgLTYgYyAyLjA2IC0wLjM0IDYgLTIgNiAtMiIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMzM1IDQgbCAwIDExNSIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gMzE3IDQ5IGMgLTAuMTEgMC40NiAtMy4yNiAxNy42MSAtNiAyNiBjIC0yLjkgOC44NyAtMTEgMjYgLTExIDI2Ii8+PHBhdGggZmlsbD0ibm9uZSIgc3Ryb2tlPSIjMDAwMDAwIiBzdHJva2Utd2lkdGg9IjgiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgZD0iTSAzMjkgNTAgYyAwLjA3IDAuMDkgMy4xIDMuMTUgNCA1IGMgNS44OCAxMi4wMyAxMC45NiAyNS4xNCAxNyAzOCBjIDEuODIgMy44NyA2IDExIDYgMTEiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDM5MiAzOSBjIDAuMDUgMC4wNSAxLjg5IDIuOSAzIDMgYyA4LjExIDAuNzIgMjAuNTUgMC41NCAzMSAwIGMgOS4xNiAtMC40NyAxOC42NyAtMC44NCAyNyAtMyBjIDcuNjkgLTIgMjMgLTEwIDIzIC0xMCIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gNDM3IDYgYyAwIDAuMjEgLTAuMzYgOC4wMSAwIDEyIGMgMC42NCA3LjA2IDIuOCAxMy45MSAzIDIxIGMgMC44IDI4LjA2IDAgODQgMCA4NCIvPjxwYXRoIGZpbGw9Im5vbmUiIHN0cm9rZT0iIzAwMDAwMCIgc3Ryb2tlLXdpZHRoPSI4IiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIGQ9Ik0gNDIzIDUyIGMgLTAuMDkgMC4zMyAtMi42NSAxMy4yMyAtNSAxOSBjIC0xLjgyIDQuNDkgLTUuMDcgOC43OSAtOCAxMyBjIC0yLjQ2IDMuNTQgLTggMTAgLTggMTAiLz48cGF0aCBmaWxsPSJub25lIiBzdHJva2U9IiMwMDAwMDAiIHN0cm9rZS13aWR0aD0iOCIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBkPSJNIDQ1NCA0NSBjIDAuMDIgMC4xMiAwLjEzIDUgMSA3IGMgMi4yNiA1LjIgNi4zIDEwLjYgOSAxNiBjIDAuOTMgMS44NiAwLjkzIDQuMzUgMiA2IGMgMy4wMiA0LjY1IDcgOS42NSAxMSAxNCBjIDMuNjYgMy45OCAxMiAxMSAxMiAxMSIvPjwvc3ZnPg","D:/3.jpg");
        //System.out.println(b);

		SimpleDateFormat sdf=new   SimpleDateFormat( " yyyy-MM-dd HH:mm:ss" );
		String str1 = sdf.format(new Date());
		System.out.println("1"+str1);

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
