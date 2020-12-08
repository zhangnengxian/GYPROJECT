package cc.dfsoft.project.biz.base.messagesync.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.File;

/**
 *@Description: 对称加密
 *@author: zhangnx
 *@Date: 2019/12/10 15:32
 *@Version:1.0
 * */
public class DESUtils {

    private static final String DES="DES";//对称加密算法DES（数据加密标准）
    private static final int LENGTH=56;
    private static final String ALGORITHM="DES/ECB/PKCS5Padding";

    public static void main(String[] args) {

        //生成密钥文件
        DESUtils.createFileKey("E:/secrity.txt");

        //读取密钥
        SecretKey secretKey = DESUtils.getSecretKey("E:/secrity.txt");

        String orgData="张能先，你好！";

        byte[] encrypt = DESUtils.encrypt(orgData, secretKey);
        System.out.println("加密的结果：" + Hex.encodeHexString(encrypt));

        byte[] decrypt = DESUtils.decrypt(encrypt, secretKey);
        System.out.println("解密结果：" + new String(decrypt));

    }




    /**
     * @Desc 生成密钥编码后以 Base64 的格式保存到指定文件
     * @param  pathFile 密钥文件路径
     */
    public static void createFileKey(String pathFile) {
        try {
            //1.生成KEY
            KeyGenerator generator = KeyGenerator.getInstance(DES);
            generator.init(LENGTH);
            SecretKey secretKey = generator.generateKey();
            if (StringUtils.isNotBlank(pathFile)) {
                File keyFile = new File(pathFile);
                // 获取密钥编码后的格式
                byte[] encoded = secretKey.getEncoded();
                // 转换为 Base64 文本
                String encBase64 = new BASE64Encoder().encode(encoded);
                IOUtils.writeFile(encBase64, keyFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Desc 根据密钥的 Base64 文本创建公钥对象
     * @param pathFile 密钥文件路径
     **/
    public static SecretKey getSecretKey(String pathFile){
        try {
            String base64Key = IOUtils.readFile(new File(pathFile));
            //2.KEY转换
            DESKeySpec desKeySpec = new DESKeySpec(base64Key.getBytes());//实例化DESKey秘钥的相关内容
            SecretKeyFactory factory = SecretKeyFactory.getInstance(DES);//实例一个秘钥工厂，指定加密方式
            SecretKey secretKey = factory.generateSecret(desKeySpec);
            return secretKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }


    /**
     * @Desc 加密
     * @param data 加密的数据
     * @param secretKey 加密的密钥
     * */
    public static byte[] encrypt(String data,SecretKey secretKey){
        try {
            //3.加密DES/ECB/PKCS5Padding--->算法/工作方式/填充方式
             Cipher cipher = Cipher.getInstance(ALGORITHM);//通过Cipher这个类进行加解密相关操作
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data.getBytes());//输入要加密的内容
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Desc 解密
     * @param data 解密的数据
     * @param secretKey 解密的密钥
     * */
    public static byte[] decrypt(byte[] data,SecretKey secretKey){
        try {
            //4.解密
            Cipher cipher = Cipher.getInstance(ALGORITHM);//通过Cipher这个类进行加解密相关操作
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




}





















