package cc.dfsoft.uexpress.common.util;

import java.io.ByteArrayOutputStream;  
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonEncoding;  
import com.fasterxml.jackson.core.JsonGenerator;  
import com.fasterxml.jackson.databind.DeserializationFeature;  
import com.fasterxml.jackson.databind.ObjectMapper;

  
public class JsonUtils {  
  
    public static String encode(Object obj) {  
        ObjectMapper om = new ObjectMapper();  
        ByteArrayOutputStream baos = null;  
        try {  
            baos = new ByteArrayOutputStream();  
            JsonGenerator generator = om.getFactory().createGenerator(baos,  
                    JsonEncoding.UTF8);  
            generator.writeObject(obj);  
            String result = new String(baos.toByteArray(), "utf-8");  
            return result;  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        } finally {  
            try {  
                if (baos != null) {  
                    baos.close();  
                }  
            } catch (IOException e) {  
            }  
        }  
    }  
  
    public static <T> T decode(String content, Class<T> valueType) {  
        ObjectMapper om = new ObjectMapper();  
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
        om.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,  
                true);  
        om.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,  
                true);  
        try {  
            return om.readValue(content, valueType);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
    /**  
     * 从一个JSON 对象字符格式中得到一个java对象  
     *   
     * @param jsonString  
     * @param beanCalss  
     * @return  
     */
    public static <T> T jsonToBean(JSONObject jsonObject, Class<T> beanCalss) {
         
       // JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T bean = (T) JSONObject.toJavaObject(jsonObject, beanCalss);
         
        return bean;
         
    }

}  