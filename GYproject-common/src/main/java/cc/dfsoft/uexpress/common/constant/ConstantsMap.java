package cc.dfsoft.uexpress.common.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantsMap {
	/**
	 * 配置表
	 */
	public static Map<String, Object> sysConfigMap = new HashMap<String, Object>();
	
	
	/**
	 * 查询配置表信息
	 * @param key
	 * @return
	 */
	public static Object getSysConfigByKey(String key){
		if(sysConfigMap !=null ){
			return sysConfigMap.get(key);
		}else{
			return null;
		}
	}

	/**
	 * 常量表map
	 */
	public static Map<String, List<Map<String, Object>>> constantsMap = new HashMap<String, List<Map<String, Object>>>();

	/**
	 * 获取常量表信息
	 * @param key
	 * @return
	 */
	public static List<Map<String, Object>> getConstantsMapByKey(String key){
		return  constantsMap.get(key);
	}

    /**
     * 获取配置单条数据
     * @param key
     * @param id
     * @return
     */
	public static Map<String, Object> getConsByKeyAndId(String key , String id){
        List<Map<String, Object>> list = constantsMap.get(key);
        if(list !=null && list.size()>0){
            for(Map<String, Object> map : list){
                if(id.equals(String.valueOf(map.get("ID")))){
                    return map;
                }
            }
        }
		return  null;
	}

	/**
	 * 通过id和扩展字段查询数据
	 * @param key
	 * @param id
	 * @param str
	 * @return
	 */
	public static Map<String,Object> getConsData(String key , String id , String str){
		List<Map<String, Object>> list = constantsMap.get(key);
		if(list !=null && list.size()>0){
			for(Map<String, Object> map : list){
				if(id.equals(String.valueOf(map.get("ID")))&&str.equals(String.valueOf(map.get("RESERVE1")))){
					return map;
				}
			}
		}
		return  null;
	}


	public static Map<String, List<DataFilerSetUpDto>> dataFilterMap = new HashMap<String, List<DataFilerSetUpDto>>();
	/**
	 * 查询配置表信息
	 * @param key
	 * @return
	 */
	public static List<DataFilerSetUpDto> getDataFilterMapByKey(String key){
		if(dataFilterMap !=null ){
			return dataFilterMap.get(key);
		}else{
			return null;
		}
	}

	public static DataFilerSetUpDto isConfig(String key){
		List<DataFilerSetUpDto> dataFList = getDataFilterMapByKey(key);
		if (dataFList!=null&&dataFList.size()>0){
			return dataFList.get(0);
		}
		return null;
	}

}
