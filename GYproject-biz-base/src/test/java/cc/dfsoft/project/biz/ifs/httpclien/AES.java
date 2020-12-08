package cc.dfsoft.project.biz.ifs.httpclien;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AES {
    private static String src = "张能先 你好！";
    public static void main(String[] args) {
        jdkAES();
    }

    public static void jdkAES(){
        try {
            //生成KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //初始化长度
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //KEY的转换
            Key key = new SecretKeySpec(keyBytes,"AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk aes encrypt:"+ Base64.encodeBase64String(result));

            //解密
            cipher.init(Cipher.DECRYPT_MODE,key);
            result = cipher.doFinal(result);
            System.out.println("jdk aes desrypt:"+new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
