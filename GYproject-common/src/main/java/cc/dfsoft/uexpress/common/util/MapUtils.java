package cc.dfsoft.uexpress.common.util;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/1/28 19:35
 * @Version:1.0
 */
public class MapUtils {

    /**
     * @MethodDesc: List<Map<String,Object>转List<Bean>
     * @Author zhangnx
     * @Date 2019/1/28 19:36
     */
    public static <T> List<T> convertListMap2ListBean(List<Map<String,Object>> listMap, Class T){
        List<T> beanList = new ArrayList<>();
        if (listMap == null || listMap.size() == 0) return beanList;
        try {
            for (Map<String, Object> map : listMap) {
                T bean = (T) T.newInstance();
                org.apache.commons.beanutils.BeanUtils.copyProperties(bean, map);
                beanList.add(bean);
            }
            return beanList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
    * @Description: map转对象
    * @author zhangnx
    * @date 2019/8/30 11:04
    */
    public static <T> T mapTransBean(Map<String, Object> map, Class T) {
        try {
            T bean = (T) T.newInstance();
            org.apache.commons.beanutils.BeanUtils.copyProperties(bean, map);
            return bean;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
    * @Description: Map<String,Object>转Map<String,String>
    * @author zhangnx
    * @date 2019/8/30 11:04
    */
    public static Map<String, String>  objTransformStr(Map<String, Object> map) {
        if (map==null) return null;

        Map<String, String> resultMap=new HashMap<>();
        for (Object key:map.keySet()) {
            if (key!=null){
                resultMap.put(key.toString(),map.get(key)!=null?map.get(key).toString():"");
            }
        }
        return resultMap;
    }


    /**
    * @Description: 对象转Map
    * @author zhangnx
    * @date 2019/8/30 11:14
    */
    public static Map<String, Object> objTransMap(Object obj) {
        if (obj == null) return null;
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.equals("class")) { // 过滤class属性
                    continue;
                }
                Method getter = property.getReadMethod();// 得到property对应的getter方法
                Object value = getter.invoke(obj);
                map.put(key, value);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }





    public static String getDiffDataBetweenFrontAndBack(Map<String, Object> origMap, Map<String, Object> newMap, List<Map<String, Object>> columnList, Class<?> clazz){
        return diffDataMapToStr(mapDiffBetweenFrontAndBack(origMap, newMap), columnList, clazz);
    }

    public static <T> String getDiffDataBetweenFrontAndBack(T origObj, T nowObj, List<Map<String, Object>> columnList){
       return diffDataMapToStr(objDiffBetweenFrontAndBack(origObj, nowObj), columnList, origObj.getClass());
    }



    public static String diffDataMapToStr(Map<String, Object> diffMap, List<Map<String, Object>> columnList, Class<?> clazz){
        if (diffMap==null) return null;
        StringBuilder resultStr=new StringBuilder();
        for (Object key:diffMap.keySet()) {
            String columnName = Annotations.getFieldColumnAnNameVal(clazz,key+"");
            if (StringUtils.isBlank(columnName)){
                columnName = Annotations.getFieldGetMethodColumnAnNameVal(clazz,key+"");
            }
            if (StringUtils.isBlank(columnName)){
                columnName=key+"";
            }

            if (columnList!=null && columnList.size()>0 && isContains(columnList,columnName)){
                for (Map<String, Object> column : columnList) {
                    if (columnName.equals(column.get("columnName") + "")) {
                        resultStr.append(column.get("columnComment") + ":" + diffMap.get(key));
                        break;
                    }
                }
            }else {
                resultStr.append(key+":"+diffMap.get(key));
            }
        }
        return resultStr.toString();
    }

    public static boolean isContains(List<Map<String, Object>> columnList,String columnName){
        if (columnList==null || columnList.size()<1) return false;
        for (Map<String, Object> column:columnList) {
            if (columnName.equals(column.get("columnName"))){
                return true;
            }
        }
        return false;
    }


    public static  Map<String,Object> objDiffBetweenFrontAndBack(Object origObj, Object nowObj){
        Map<String, Object> origMap = MapUtils.objTransMap(origObj);
        Map<String, Object> newMap = MapUtils.objTransMap(nowObj);
        return mapDiffBetweenFrontAndBack(origMap,newMap);
    }


    public static  Map<String,Object> mapDiffBetweenFrontAndBack(Map<String, Object> origMap, Map<String, Object> newMap){
        if (origMap==null || newMap==null) return null;
        Map<String, Object> resultMap = new HashMap<>();
        for (Object okey:origMap.keySet()) {
            for (Object nkey:newMap.keySet()) {
                if (okey.equals(nkey) && !(origMap.get(okey)+"").equals(newMap.get(nkey)+"")){
                    Object diff="'"+origMap.get(okey)+"' 更改为：'"+newMap.get(nkey)+"';\n";
                    resultMap.put(okey.toString(),diff);
                    break;
                }
            }
        }
        return resultMap;
    }







}
