package cc.dfsoft.uexpress.common.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* @Description: 注解工具类
* @author zhangnx
* @date 2019/8/24 15:09
*/
public class Annotations {

    /**
    * @Description: 获取类上注解的表名
    * @author zhangnx
    * @date 2019/8/30 12:42
    */
    public static <T> String getClassTableAnNameVal(Class<T> tClass) {
        List<Annotation> anList = Annotations.getClassAnnotations(tClass);
        return Annotations.getAnnotationValue(anList,"Table","name");
    }

    /**
    * @Description: 获取字段上注解的表字段名
    * @author zhangnx
    * @date 2019/8/30 12:43
    */
    public static <T> String getFieldColumnAnNameVal(Class<T> tClass,String fieldName) {
        List<Annotation> anList = Annotations.getFieldAnnotations(tClass,fieldName);
        return Annotations.getAnnotationValue(anList,"Column","name");
    }

    /**
    * @Description: 获取字段的get方法上注解的表字段名
    * @author zhangnx
    * @date 2019/8/30 12:44
    */
    public static <T> String getFieldGetMethodColumnAnNameVal(Class<T> tClass,String fieldName) {
        List<Annotation> anList = Annotations.getFieldGetMethodAnnotations(tClass,fieldName);
        return Annotations.getAnnotationValue(anList,"Column","name");
    }



    /**
    * @Description: 通过注解名，注解属性名获取注解属性值（类上的注解）
    * @author zhangnx
    * @date 2019/8/30 12:46
    */
    public static <T> String getClassAnVal(Class<T> tClass, String annotationName, String annotationField) {
        List<Annotation> anList = Annotations.getClassAnnotations(tClass);
        return Annotations.getAnnotationValue(anList,annotationName,annotationField);
    }

    /**
     * @Description: 通过注解名，注解属性名获取注解属性值（属性get方法上的注解）
     * @author zhangnx
     * @date 2019/8/30 12:46
     */
    public static <T> String getFieldGetMethodAnVal(Class<T> tClass,String fieldName,String annotationName, String annotationField) {
        List<Annotation> anList = Annotations.getFieldGetMethodAnnotations(tClass,fieldName);
        return Annotations.getAnnotationValue(anList,annotationName,annotationField);
    }

    /**
     * @Description: 通过注解名，注解属性名获取注解属性值（属性上的注解）
     * @author zhangnx
     * @date 2019/8/30 12:46
     */
    public static <T> String getFieldAnVal(Class<T> tClass,String fieldName,String annotationName, String annotationField) {
        List<Annotation> anList = Annotations.getFieldAnnotations(tClass,fieldName);
        return Annotations.getAnnotationValue(anList,annotationName,annotationField);
    }






    /**
     * @Description: 获取类注解
     * @author zhangnx
     * @date 2019/8/24 14:44
     */
    public static <T> List<Annotation> getClassAnnotations(Class<T> tClass) {
        List<Annotation> anList = new ArrayList<>();
        if (tClass==null) return anList;

        Annotation[] ans = tClass.getAnnotations();
        if (ans == null || ans.length < 1) return null;
        for (Annotation an : ans) {
            anList.add(an);
        }
        return anList;
    }


    /**
     * @Description: 获取属性注解
     * @author zhangnx
     * @date 2019/8/24 14:44
     */
    public static <T> List<Annotation> getFieldAnnotations(Class<T> tClass, String fieldName) {
        List<Annotation> anList = new ArrayList<>();
        if (tClass==null) return anList;

        Field[] fields = tClass.getDeclaredFields();
        if (fields==null || fields.length<1) return null;
        for (Field field:fields) {
            if (field.getName().equals(fieldName)){
                Annotation[] ans = field.getDeclaredAnnotations();
                if (ans == null || ans.length < 1) return null;
                for (Annotation an : ans) {
                    anList.add(an);
                }
                continue;
            }
        }
        return anList;
    }




    /**
    * @Description: 获取属性get方法上的注解
    * @author zhangnx
    * @date 2019/8/24 14:44
    */
    public static <T> List<Annotation> getFieldGetMethodAnnotations(Class<T> tClass, String fieldName) {

        List<Annotation> anList = new ArrayList<>();
        if (tClass==null) return anList;

        Field[] fields = tClass.getDeclaredFields();
        if (fields==null || fields.length<1) return null;

        for (Field field:fields) {

            if (field.getName().equals(fieldName)) {
                String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);//拼接get方法名
                Method method = null;//通过JavaBean对象拿到该属性的get方法，从而进行操控

                try {
                    method = tClass.getMethod(getMethodName, new Class[]{});
                    if (method == null) return null;
                    //method.setAccessible(true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                Annotation[] ans = method.getAnnotations(); // 获取方法中的注解
                if (ans == null || ans.length < 1) return null;

                for (Annotation an : ans) {
                    anList.add(an);
                }
               continue;
            }
        }
        return anList;

    }








    /**
     * @Description: 截取注解里面的内容
     * @author zhangnx
     * @date 2019/8/26 15:25
     */
    private static String subAnnotationStr(List<Annotation> anList,String annotationName){
        if (anList == null || anList.size() < 1) return null;
        for (Annotation an:anList) {
            if (an==null) return null;
            String anStr = an.toString();
            if (StringUtils.isBlank(anStr)) return null;

            if (anStr.contains(annotationName)){
                int start = anStr.indexOf("(");
                int end = anStr.lastIndexOf(")");
                if (start<end) {
                    return anStr.substring(start + 1, end);
                }
            }
        }
        return null;
    }



    /**
     * @Description: 获取注解里面某个属性的值
     * @author zhangnx
     * @date 2019/8/26 15:22
     */
    private static String getAnnotationValue( List<Annotation> anList,String annotationName,String annotationField){
        String subAnnotationStr = Annotations.subAnnotationStr(anList, annotationName);

        if (StringUtils.isBlank(subAnnotationStr)) return null;

        List<Map<String,Object>> anFields=new ArrayList<>();
        String[] split = subAnnotationStr.split(",");
        for (String s:split) {
            Map<String,Object> fvMap=new HashMap();
            if (s.indexOf("=")>0) {
                fvMap.put("field", s.substring(0, s.indexOf("=")).trim());
            }
            if (s.indexOf("=")<s.length()){
                fvMap.put("value",s.substring(s.indexOf("=")+1,s.length()).trim());
            }
            anFields.add(fvMap);
        }

        for (Map<String,Object> map:anFields) {
            String field = map.get("field").toString();
            if (annotationField.equals(field)){
                return map.get("value")!=null?map.get("value").toString():null;
            }
        }
        return null;
    }

}
