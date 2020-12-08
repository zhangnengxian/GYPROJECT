package cc.dfsoft.project.biz.base.messagesync.utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *@Description: 非对称加密
 *@author: zhangnx
 *@Date: 2019/12/6 13:55
 *@Version:1.0
 * */
public class RSAUtils {


    /** 算法名称 */
    private static final String RSA = "RSA";

    /** 密钥长度，DH算法的默认密钥长度是1024 密钥长度必须是64的倍数，在512到65536位之间 */
    private static final int KEY_SIZE =1024;//数字越大加密的内容就也多速度越就越慢（512->53个字节、1024->117个字节、2048->245个字节）


    public static void main(String[] args){

        //生成密钥对保存到文件中
        KeyPair keyPair = RSAUtils.generateKeyPair();//随机生成密钥对（包含公钥和私钥）
        PublicKey pubKey = keyPair.getPublic();//公钥
        PrivateKey priKey = keyPair.getPrivate();//私钥

        RSAUtils.saveKeyForEncodedBase64(pubKey,"E:/pub.txt");
        RSAUtils.saveKeyForEncodedBase64(priKey,"E:/pri.txt");


        //读取公钥-私钥(密钥)
        PublicKey publicKey = RSAUtils.getPublicKey("E:/pub.txt");
        PrivateKey privateKey = RSAUtils.getPrivateKey("E:/pri.txt");


        String str = "你好! Hello Word";
        System.out.println("原数据：" + str);

        System.out.println("**********************公钥加密私钥解密***********************************");

        byte[] code1 = RSAUtils.publicKeyEncrypt(str,publicKey);
        System.out.println("公钥加密——加密数据：" + Base64.encodeBase64String(code1));

        //使用私钥对数据进行解密
        byte[] decode2 = RSAUtils.privateKeyDecrypt(code1, privateKey);
        System.out.println("私钥解密——解密数据：" + new String(decode2));


        System.out.println("==========================私钥加密公钥解密============================");

        byte[] code3 = RSAUtils.privateKeyEncrypt(str,privateKey);
        System.out.println("私钥加密——加密数据：" + Base64.encodeBase64String(code3));

        //使用私钥对数据进行解密
        byte[] decode4 = RSAUtils.publicKeyDecrypt(code3, publicKey);
        System.out.println("公钥解密——解密数据：" + new String(decode4));
    }




















    /**
     * @Description: 公钥加密数据
     * @author zhangnx
     * @param data 待加密数据
     * @param key 公钥密钥
     * @date 2019/12/6 13:26
     * @return byte[] 加密数据
     **/
    public static byte[] publicKeyEncrypt(String data, PublicKey key){
        try {
            //实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            //初始化公钥
            //密钥材料转换
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key.getEncoded());
            //产生公钥
            PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);

            //数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @Description: 私钥解密数据
     * @author zhangnx
     * @param data 待解密数据
     * @param key 私密密钥
     * @date 2019/12/6 13:31
     * @return byte[] 解密数据
     **/
    public static byte[] privateKeyDecrypt(byte[] data, PrivateKey key){

        try { // 获取指定算法的密码器
            //取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            //生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            //数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




        /**
        * @Description: 私钥加密
        * @author zhangnx
        * @param data 待加密数据
        * @param key 私密密钥
        * @date 2019/12/6 13:26
        * @return byte[] 加密数据
        **/
        public static byte[] privateKeyEncrypt(String data, PrivateKey key){
            try {
                //取得私钥
                PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key.getEncoded());
                KeyFactory keyFactory = KeyFactory.getInstance(RSA);
                //生成私钥
                PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
                //数据加密
                Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
                cipher.init(Cipher.ENCRYPT_MODE, privateKey);
                return cipher.doFinal(data.getBytes());
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }


    /**
     * @Description: 公钥解密数据
     * @author zhangnx
     * @param data 待解密数据
     * @param key 公钥密钥
     * @date 2019/12/6 13:32
     * @return byte[] 解密数据
     **/
    public static byte[] publicKeyDecrypt(byte[] data, PublicKey key){
        try {
            //实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            //初始化公钥
            //密钥材料转换
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key.getEncoded());
            //产生公钥
            PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
            //数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            return cipher.doFinal(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }







    /**
     * 根据公钥的 Base64 文本创建公钥对象
     */
    public static PublicKey getPublicKey(String pathFile){

        try {
            String base64Key = IOUtils.readFile(new File(pathFile));
            // 把 公钥的Base64文本 转换为已编码的 公钥bytes
             byte[] encPubKey = new BASE64Decoder().decodeBuffer(base64Key);
            // 创建 已编码的公钥规格
            X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(encPubKey);

            // 获取指定算法的密钥工厂, 根据 已编码的公钥规格, 生成公钥对象
            return KeyFactory.getInstance(RSA).generatePublic(encPubKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 根据私钥的 Base64 文本创建私钥对象
     */
    public static PrivateKey getPrivateKey(String pathFile){
        try {
            String base64Key = IOUtils.readFile(new File(pathFile));
            // 把 私钥的Base64文本 转换为已编码的 私钥bytes
            byte[] encPriKey = new BASE64Decoder().decodeBuffer(base64Key);

            // 创建 已编码的私钥规格
            PKCS8EncodedKeySpec encPriKeySpec = new PKCS8EncodedKeySpec(encPriKey);

            // 获取指定算法的密钥工厂, 根据 已编码的私钥规格, 生成私钥对象
            return KeyFactory.getInstance(RSA).generatePrivate(encPriKeySpec);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 将 公钥/私钥 编码后以 Base64 的格式保存到指定文件
     */
    public static void saveKeyForEncodedBase64(Key key, String pathFile){

        if (StringUtils.isNotBlank(pathFile)){
            File keyFile = new File(pathFile);
            // 获取密钥编码后的格式
            byte[] encBytes = key.getEncoded();

            // 转换为 Base64 文本
            String encBase64 = new BASE64Encoder().encode(encBytes);

            try { // 保存到文件
                IOUtils.writeFile(encBase64, keyFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 随机生成密钥对（包含公钥和私钥）
     */
    public static KeyPair generateKeyPair(){
        try {
            // 获取指定算法的密钥对生成器
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance(RSA);
            // 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
            kpGen.initialize(KEY_SIZE);
            // 随机生成一对密钥（包含公钥和私钥）
            return kpGen.generateKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




}
