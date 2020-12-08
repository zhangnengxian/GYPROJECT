package cc.dfsoft.uexpress.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串处理工具类
 * @author van.zheng
 *
 */
public class StringUtil extends StringUtils{

	/**
	 * 去除以逗号分隔的字符串的重复项
	 * @param str
	 * @return
	 */
	public static List<String> RemoveRepeatItem(String str) {
		if (!isNotBlank(str)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String[] array = str.split(",");
		for (String s : array) {
			if (Collections.frequency(list, s) < 1) {
				list.add(s);
			}
		}
		return list;
	}

	/**
	 * 数字不足位数左补0
	 * @param str
	 * @param strLength
	 */
	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str); // 左补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		return str;
	}

}
