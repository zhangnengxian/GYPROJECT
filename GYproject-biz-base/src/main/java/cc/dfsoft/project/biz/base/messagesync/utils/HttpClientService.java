package cc.dfsoft.project.biz.base.messagesync.utils;


import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 *@Description: HttpClient远程接口封装
 *@author: zhangnx
 *@Date: 2019/11/27 14:41
 *@Version:1.0
 * */
public class HttpClientService {

    private static final boolean isVerifyCa=true;//HTTPS请求时是否需要检验CA证书(即:是否需要检验服务器的身份)
    private static final String caFileName="ds.crt";//证书路径+文件名称（默认从classpath中找文件(文件放在resources目录下)）
    private static InputStream ca=null;



    public HttpClientService() {
        ca=this.getClass().getClassLoader().getResourceAsStream(caFileName);
    }



    /**
    * @Description: POST发送(无参或普通参数：类似GET提交)
    * @author zhangnx
    * @param
    * @date 2019/11/27 14:15
    **/
    public static ResultMsg doPost(String url, boolean isHttps){
        //获得Http客户端
        CloseableHttpClient httpClient = getHttpClient(isHttps);
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;

           try {
               // 由客户端执行(发送)Post请求
               response=httpClient.execute(httpPost);
               // 从响应模型中获取响应实体
               HttpEntity responseEntity = response.getEntity();

               String result=null;
               if (responseEntity != null) {
                   result = EntityUtils.toString(response.getEntity(), "UTF-8");
               }
               return new ResultMsg(response.getStatusLine().getStatusCode(),result);

           }catch (Exception e){
               e.printStackTrace();
               return new ResultMsg(404,"请求失败!");
           }
    }











    /**
    * @Description: POST发送（对象参数）
    * @author zhangnx
    * @param url 调用地址
    * @param obj 对象参数
    * @param isHttps 是否是HTTPS请求
    * @date 2019/11/27 11:28
    **/
    public static ResultMsg doPost(String url, Object obj, boolean isHttps){
        //获得Http客户端
        CloseableHttpClient httpClient = getHttpClient(isHttps);
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(JSON.toJSONString(obj), "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            String result=null;
            if (responseEntity != null) {
                 result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            return new ResultMsg(response.getStatusLine().getStatusCode(),result);

        }catch (IOException e){
            e.printStackTrace();
            return new ResultMsg(404,"请求失败!");
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    /**
    * @Description: POST发送（对象参数+普通参数）
    * @author zhangnx
     * @param uri 调用地址
     * @param obj 对象参数
     * @param isHttps 是否是HTTPS请求
    * @date 2019/11/27 12:00
    **/
    public static ResultMsg doPost(URI uri, Object obj, boolean isHttps) {

        //获得Http客户端
        CloseableHttpClient httpClient = getHttpClient(isHttps);
        HttpPost httpPost = new HttpPost(uri);
        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(obj), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            String result=null;
            if (responseEntity != null) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            return new ResultMsg(response.getStatusLine().getStatusCode(),result);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMsg(404,"请求失败!");
        }finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    /**
    * @Description: 获取HttpClient客户端
    * @author zhangnx
    * @param isHttps 是否是HTTPS请求
    * @date 2019/11/27 9:49
    **/
    private static CloseableHttpClient getHttpClient(boolean isHttps){

        if (isHttps){ //HTTPS请求
            SSLConnectionSocketFactory sslSocketFactory;

            if (isVerifyCa){ //需要证书检验
                // 证书的别名，即:key。 注:cAalias只需要保证唯一即可，不过推荐使用生成keystore时使用的别名。
                 String cAalias = System.currentTimeMillis() + "" + new SecureRandom().nextInt(1000);
                sslSocketFactory = getSocketFactory(isVerifyCa, ca, cAalias);
            }else {
                sslSocketFactory = getSocketFactory(isVerifyCa, null, null);
            }

            return HttpClientBuilder.create().setSSLSocketFactory(sslSocketFactory).build();

        }else { //HTTP请求
           return HttpClientBuilder.create().build();
        }
    }



    
    
    /**
    * @Description: 创建SSLSocketFactory实例
    * @author zhangnx
    * @param isVerifyCa 是否需要检验CA证书(即:是否需要检验服务器的身份)
    * @param caInputStream CA证书。(若不需要检验证书，那么此处传null即可)
    * @param cAalias 别名。(若不需要检验证书，那么此处传null即可) 注意:别名应该是唯一的， 别名不要和其他的别名一样，否者会覆盖之前的相同别名的证书信息。别名即key-value中的key
    * @date 2019/11/27 9:54
    **/
    private static SSLConnectionSocketFactory getSocketFactory(boolean isVerifyCa, InputStream caInputStream, String cAalias){
        X509TrustManager x509TrustManager;
        // https请求，需要校验证书
        if (isVerifyCa){
            KeyStore keyStore = getKeyStore(caInputStream, cAalias);
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                }
                x509TrustManager = (X509TrustManager) trustManagers[0];
                // 这里传TLS或SSL其实都可以的
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                return new SSLConnectionSocketFactory(sslContext);

            } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
                e.printStackTrace();
            }

        }else {
            // https请求，不作证书校验
            x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) {}
                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) {}
                @Override
                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0];  }
            };

            try {
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
                return new SSLConnectionSocketFactory(sslContext);

            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
        }

        return null;
    }







    /**
    * @Description: 获取密钥证书
    * @author zhangnx
    * @param caInputStream CA证书(此证书应由要访问的服务端提供)
    * @param cAalias 别名 注意:别名应该是唯一的， 别名不要和其他的别名一样，否者会覆盖之前的相同别名的证书信息。别名即key-value中的key。
    * @date 2019/11/27 10:00
    **/
    private static KeyStore getKeyStore(InputStream caInputStream, String cAalias){
        try {
            // 证书工厂
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            // 秘钥仓库
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry(cAalias, certificateFactory.generateCertificate(caInputStream));
            return keyStore;

        } catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
       return null;
    }




    





}
