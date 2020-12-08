package cc.dfsoft.uexpress.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * 签名算法管理
 * @author JOHN
 * 2015.01.15
 */
public class SignAlgorithmUtil {
	public final static String SHA1withRSA_ALGORITHM = "SHA1withRSA";
	
	/**
	 * 用算法“algorithm”对data进行签名
	 * @param data
	 * @param algorithm
	 * @param keyStoreFile
	 * @param storeFilePass
	 * @param keyAlias
	 * @param keyAliasPass
	 * @return
	 */
	public static byte[] sign(byte[] data, String algorithm, String keyStoreFile,
			String storeFilePass, String keyAlias, String keyAliasPass) {		
		if(algorithm.equalsIgnoreCase(SignAlgorithmUtil.SHA1withRSA_ALGORITHM)) {
			return signWithSHA1withRSA(data, keyStoreFile, storeFilePass, keyAlias, keyAliasPass);
		} else {
			return null;
		}		
	}
	/**
	 * 用算法“alorithm”对data签名signed进行验证
	 * @param signed
	 * @param alorithm
	 * @param keyStoreFile
	 * @param storeFilePass
	 * @param keyAlias
	 * @param keyAliasPass
	 * @return
	 */
	public static boolean verifySign(byte[] data, byte[] signed, String algorithm, String keyStoreFile,
			String storeFilePass, String keyAlias) {
		if(algorithm.equalsIgnoreCase(SignAlgorithmUtil.SHA1withRSA_ALGORITHM)) {
			return verifySignWithSHA1withRSA(data, signed, keyStoreFile, storeFilePass, keyAlias);
		} else {
			return false;
		}		
	}
	
	/**
	 * 用SHA1withRSA算法对data进行签名
	 * @param data
	 * @param keyStoreFile
	 * @param storeFilePass
	 * @param keyAlias
	 * @param keyAliasPass
	 * @return
	 */
	private static byte[] signWithSHA1withRSA(byte[] data, String keyStoreFile,
							String storeFilePass, String keyAlias, String keyAliasPass) {
		Signature sign;
		try {
			//实例化一个用SHA算法进行散列，用RSA算法进行加密的Signature.
			sign = Signature.getInstance("SHA1WithRSA");
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
			return null;
		}
		try {
			//设置加密散列码用的私钥
			sign.initSign(getPrivateKey(keyStoreFile, storeFilePass, keyAlias, keyAliasPass));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		try {
			//设置散列算法的输入
			sign.update(data);	
			//进行散列，对产生的散列码进行加密并返回
			return sign.sign();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param data
	 * @param signed
	 * @param algorithm
	 * @param keyStoreFile
	 * @param storeFilePass
	 * @param keyAlias
	 * @param keyAliasPass
	 * @return
	 */
	private static boolean verifySignWithSHA1withRSA(byte[] data, byte[] signed, String keyStoreFile,
			String storeFilePass, String keyAlias) {
		Signature sign;
		try {
			sign = Signature.getInstance("SHA1WithRSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			sign.initVerify(getPublicKey(keyStoreFile, storeFilePass, keyAlias));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			sign.update(data);
			if(sign.verify(signed)) return true;
			else return false;
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
	}
	
	
	public static PublicKey getPublicKey(String keyStoreFile, String storeFilePass, String keyAlias) {
		// 读取密钥是所要用到的工具类
		KeyStore ks;		
		// 公钥类所对应的类
		PublicKey pubkey = null;
		try {
			// 得到实例对象
			ks = KeyStore.getInstance("JKS");
			FileInputStream fin;
			try {			
				// 读取JKS文件
				fin = new FileInputStream(keyStoreFile);
				try {
				// 读取公钥
					ks.load(fin, storeFilePass.toCharArray());
					java.security.cert.Certificate cert = ks.getCertificate(keyAlias);
					pubkey = cert.getPublicKey();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (CertificateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		
		return pubkey;
	}

	 /**
	  * 得到私钥
	  * 
	  * @param keyStoreFile
	  *            私钥文件
	  * @param storeFilePass
	  *            私钥文件的密码
	  * @param keyAlias
	  *            别名
	  * @param keyAliasPass
	  *            密码
	  * @return
	  */
	 public static PrivateKey getPrivateKey(String keyStoreFile,
	   String storeFilePass, String keyAlias, String keyAliasPass) {
		  KeyStore ks;
		  PrivateKey prikey = null;
		  try {
			   ks = KeyStore.getInstance("JKS");
			   FileInputStream fin;
			   try {
				    fin = new FileInputStream(keyStoreFile);
				    try {
					     try {
						      ks.load(fin, storeFilePass.toCharArray());
						      // 先打开文件
						      prikey = (PrivateKey) ks.getKey(keyAlias, keyAliasPass.toCharArray());
						      // 通过别名和密码得到私钥
					     } catch (UnrecoverableKeyException e) {
					    	 e.printStackTrace();
					     } catch (CertificateException e) {
					    	 e.printStackTrace();
					     } catch (IOException e) {
					    	 e.printStackTrace();
					     }
				    } catch (NoSuchAlgorithmException e) {
				    	e.printStackTrace();
				    }
			   } catch (FileNotFoundException e) {
				   e.printStackTrace();
			   }
		  } catch (KeyStoreException e) {
			  e.printStackTrace();
		  }
		  return prikey;
	 }

	 public static void main(String[] args) {
	  PublicKey publicKey;
	  PrivateKey privateKey;
	  
	  publicKey=getPublicKey("C:\\aaa.jks","AAAAAAAA", "ibmwebspheremq");
	  privateKey=getPrivateKey("C:\\aaa.jks","AAAAAAAA", "ibmwebspheremq","AAAAAAAA");
	  
	  System.out.println(publicKey.toString());
	  System.out.println(privateKey.toString());
	 }

}
