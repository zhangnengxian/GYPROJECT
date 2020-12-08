package cc.dfsoft.uexpress.common.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectUtil {
	private static final Log logger = LogFactory.getLog(ReflectUtil.class);

	/**
	 * 根据ognl表达式（例如address.name），从对象obj中取值。
	 * 以address.name为例，实际执行操作为obj.getAdderss().getName();
	 * @param obj
	 * @param ognl
	 * @return
	 */
	public static Object getOgnlValueFromObject(Object obj, String ognl) {
		if(obj == null || ognl == null || ognl.trim().equals(""))
			return null;

		ognl = ognl.trim();
		
		int index = ognl.indexOf(".");
		if(index == -1) return getValueFromObject(obj, ognl);
		
		String name = ognl.substring(0, index);
		Object childObj = getValueFromObject(obj, name);
		
		return getOgnlValueFromObject(childObj, ognl.substring(index+1));
	}
	
	
	
	/**
	 * 从对象obj中取得name的值，此时，obj中应该有"get" + name所对应的方法。
	 * @param obj
	 * @param name
	 * @return
	 */
	public static Object getValueFromObject(Object obj, String name) {
		if(obj == null || name == null || name.trim().equals(""))
			return null;
		
		name = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
		
		return getMethodReturnValue(obj, name);
	}
	
	
	/**
	 * 取得对象obj的methodName方法的返回值。
	 * @param obj
	 * @param methodName
	 * @return
	 */
	public static Object getMethodReturnValue(Object obj, String methodName) {
		if(obj == null || methodName == null || methodName.trim().equals(""))
			return null;
		
		try{
			Class c = obj.getClass();
			Method m = c.getMethod(methodName, new Class[]{});
			return m.invoke(obj, new Object[]{});
		}catch(Exception e) {
			//logger.warn("Get object property value failed: " + e.getMessage());
			return null;
		}
	}

	
	/**
	 * 根据ognl表达式（例如address.name），往对象obj中存值value。
	 * 以address.name为例，实际执行操作为obj.getAddress().setName(value);
	 * @param obj
	 * @param ognl
	 * @param value
	 */
	public static void setOgnlValueToObject(Object obj, String ognl, Object value) {
		if(obj == null || ognl == null || ognl.trim().equals("")) return;

		ognl = ognl.trim();
		
		int index = ognl.indexOf(".");
		if(index == -1) {
			setValueToObject(obj, ognl, value);
			return;
		}
		
		String name = ognl.substring(0, index);
		Object childObj = getValueFromObject(obj, name);
		
		setOgnlValueToObject(childObj, ognl.substring(index+1), value);
	}
	
	
	/**
	 * 往对象obj中写name的值，此时，obj中应该有"set" + name所对应的方法。
	 * @param obj
	 * @param name
	 * @param value
	 */
	public static void setValueToObject(Object obj, String name, Object value) {
		if(obj == null || name == null || name.trim().equals("")) return;
		
		//name = "set" + name.substring(0,1).toUpperCase() + name.substring(1);		
		//setObjectPropertyValue(obj, name, value);
		try{
			/*Class c = obj.getClass();
			Method m = c.getMethod(methodName, new Class[]{Object.class});
			m.invoke(obj, new Object[]{value});*/
			BeanUtils.setProperty(obj, name, value);
		}catch(Exception e) {
			//logger.warn("Set object property value failed: " + e.getMessage());
			return;
		}
	}
	
	
	/**
	 * 执行obj的methodName方法，methodName方法以value为参数。
	 * @param obj
	 * @param methodName
	 * @param value
	 */
	/*
	public static void setObjectPropertyValue(Object obj, String methodName, Object value) {
		if(obj == null || methodName == null || methodName.trim().equals("")) return;
		
		
		
		try{
			Class c = obj.getClass();
			Method m = c.getMethod(methodName, new Class[]{Object.class});
			m.invoke(obj, new Object[]{value});
		}catch(Exception e) {
			logger.warn("Set object property value failed: " + e.getMessage());
			return;
		}
	}*/
	
	
	/**
	 * 判断obj是否有methodName方法
	 * @param obj
	 * @param methodName
	 * @return
	 */
	public static boolean isMethodOf(Object obj, String methodName) {
		if(obj == null || methodName == null || methodName.trim().equals(""))
			return false;
		
		methodName = methodName.trim();	
		try{
			Class c = obj.getClass();
			c.getMethod(methodName, new Class[]{});
			return true;
		}catch(Exception e) {
			//logger.warn("Relection info: " + e.getMessage());
			return false;
		}
	}

	
	/**
	 * 判断类c是否有methodName方法
	 * @param obj
	 * @param methodName
	 * @return
	 */
	public static boolean isMethodOf(Class c, String methodName) {
		if(c == null || methodName == null || methodName.trim().equals(""))
			return false;
		
		methodName = methodName.trim();
		try{
			c.getMethod(methodName, new Class[]{});
			return true;
		}catch(Exception e) {
			//logger.warn("Relection info: " + e.getMessage());
			return false;
		}
	}
	
	/**
	 *set empty string to property who's value is null
	 */
	public static void cleanNull(Object obj)
	{
		Method[] mar=obj.getClass().getMethods();
		for(int i=0;i<mar.length;i++)
		{
			String methodName=mar[i].getName();
			if(methodName.startsWith("get"))
			{
				try{
					Object result=mar[i].invoke(obj,new Object[]{});
					if(result==null)
					{
						String setMethodName=methodName.replaceFirst("g","s");
						Method setMethod=obj.getClass().getMethod(setMethodName,new Class[]{String.class});
						if(setMethod!=null)
						{
							setMethod.invoke(obj,new Object[]{""});
						}						
					}
				}catch(NoSuchMethodException e1)
				{
					continue;
				}
				catch(Exception e)
				{
					
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @param data
	 * @param keyName
	 * @param valueName
	 * @return
	 */
	public static Map convertToMap(List data,String keyName,String valueName)
	{
		HashMap hm=new HashMap();
		if(data==null)
			return new HashMap();
		for(int i=0;i<data.size();i++)
		{
			try{
				String firstC=keyName.substring(0,1);
				String methodName="get"+firstC.toUpperCase()+keyName.substring(1,keyName.length());
			
				Method m=data.get(i).getClass().getMethod(methodName,new Class[]{});
				Object kObj=m.invoke(data.get(i),new Object[]{});
				
				 firstC=valueName.substring(0,1);
				 methodName="get"+firstC.toUpperCase()+valueName.substring(1,valueName.length());
			
				m=data.get(i).getClass().getMethod(methodName,new Class[]{});
				Object vObj=m.invoke(data.get(i),new Object[]{});
				
				
				hm.put(kObj,vObj);
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return hm;
	}
	/**
	 * 
	 * @param data
	 * @param elementName ( using ongl )
	 */
	public static String[] convertToArray(List data,String elementName)
	{
		//convert elementName to array
		String elementName2=elementName;
		LinkedList element=new LinkedList();
			int bpos=0,epos=0;
		while(epos!=-1)
		{
			epos=elementName2.indexOf(".");
			if(epos==-1)
				element.addLast(elementName2);
			else
				element.addLast(elementName2.substring(bpos,epos));
			elementName2=elementName2.substring(epos+1,elementName2.length());
			bpos=epos+1;
		}
		//conver to array
		String[] str=new String[data.size()];
		for(int i=0;i<data.size();i++)
		{
			try{
				
				String firstC="";
				String methodName="";
				Object obj=data.get(i);
				for(int j=0;j<element.size();j++)
				{
					firstC=((String)element.get(j)).substring(0,1);
					methodName="get"+firstC.toUpperCase()+((String)element.get(j)).substring(1);
					Method m=obj.getClass().getMethod(methodName,new Class[]{});
					obj=m.invoke(obj,new Object[]{});					
				}

				if(obj instanceof String)
					str[i]=(String)obj;
				else
					str[i]="";
				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return str;
	}
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static Map clone(Map data)
	{
		HashMap hm=new HashMap();

		Iterator it=data.keySet().iterator();
		while(it.hasNext())
		{
			Object key=it.next();
			hm.put(key,data.get(key));
		}
		return hm;

	}
	/**
	 * note:the return data don't containt array value
	 * @param data
	 * @return
	 */
	public static Map getDataFromRequestMap(Map data)
	{
		HashMap hm=new HashMap();

		Iterator it=data.keySet().iterator();
		while(it.hasNext())
		{
			Object key=it.next();
			Object value=data.get(key);
			if(value instanceof String[])
			{
				String[] valueArry=(String[])value;
				if(valueArry!=null&&valueArry.length!=0)
				{
					value=valueArry[0];
				}
			}
			hm.put(key,value);
		}
		return hm;		
	}
}
