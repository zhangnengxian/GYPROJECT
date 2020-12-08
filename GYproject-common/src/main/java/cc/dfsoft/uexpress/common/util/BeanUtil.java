package cc.dfsoft.uexpress.common.util;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Bean utilities
 */
public class BeanUtil {

	public static void copyNotNullProperties(Object dest, Object orig) {
		if (dest == null || orig == null)
			return;

		try {
			if (orig instanceof Map) { // 对Map处理
				@SuppressWarnings("rawtypes")
				Iterator names = ((Map) orig).keySet().iterator();
				while (names.hasNext()) {
					String name = (String) names.next();
					if (PropertyUtils.isWriteable(dest, name)) {
						@SuppressWarnings("rawtypes")
						Object value = ((Map) orig).get(name);
						Object valueDes = PropertyUtils.getSimpleProperty(dest,
								name);
						if (!(value instanceof String)
								&& !(value instanceof Number)) {
							if (value == null || value instanceof List
									|| value instanceof Map) {
								continue;
							} else if (value instanceof Date) {
								BeanUtils.copyProperty(dest, name, value);
								continue;
							} else if (valueDes == null) {
								Object o = value.getClass().newInstance();
								copyNotNullProperties(o, value);
								BeanUtils.setProperty(dest, name, o);
							} else {
								Object o = BeanUtils.cloneBean(valueDes);
								copyNotNullProperties(o, value);
								BeanUtils.setProperty(dest, name, o);
							}
						} else if (value != null) {
							if (value.toString().trim().equals("")) {
								BeanUtils.setProperty(dest, name, null);
							} else {
								BeanUtils.setProperty(dest, name, value);
							}
						}
					}
				}
			} else {// 标准JavaBean处理
				PropertyDescriptor origDescriptors[] = PropertyUtils
						.getPropertyDescriptors(orig);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue;
					} // getClass()省略

					if (PropertyUtils.isReadable(orig, name)
							&& PropertyUtils.isWriteable(dest, name)) {
						Object value = PropertyUtils.getSimpleProperty(orig,
								name);
						Object valueDes = PropertyUtils.getSimpleProperty(dest,
								name);

						if (!(value instanceof String)
								&& !(value instanceof Number)) {
							if (value == null || value instanceof List
									|| value instanceof Map) {
								continue;
							} else if (value instanceof Date) {
								BeanUtils.copyProperty(dest, name, value);
								continue;
							} else if (valueDes == null) {
								Object o = value.getClass().newInstance();
								copyNotNullProperties(o, value);
								BeanUtils.setProperty(dest, name, o);
							} else {
								Object o = BeanUtils.cloneBean(valueDes);
								copyNotNullProperties(o, value);
								BeanUtils.setProperty(dest, name, o);
							}
						} else if (value != null) {
							if (value.toString().trim().equals("")) {
								BeanUtils.setProperty(dest, name, null);
							} else {
								BeanUtils.setProperty(dest, name, value);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setNullProperties(Object orig) {
		if (orig == null)
			return;
		try {
			// 标准JavaBean处理
			PropertyDescriptor origDescriptors[] = PropertyUtils
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue;
				} // getClass()省略
				Object value = PropertyUtils.getSimpleProperty(orig, name);
				if (value == null)
					continue;
				if (!(value instanceof String) && !(value instanceof Number)) {
					continue;
				} else if (value != null) {
					if (value.toString().trim().equals("")) {
						BeanUtils.setProperty(orig, name, null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}