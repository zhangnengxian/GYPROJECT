package cc.dfsoft.uexpress.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

/**
 * Bean utilities
 */
public class ClobUtil {

	/**
	 * clob 转换成string
	 * 
	 * @param clob
	 * @return
	 */
	public static String ClobToString(Clob clob) {
		if (null != clob) {
			String reString = "";
			Reader is = null;
			try {
				is = clob.getCharacterStream();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = null;
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			StringBuffer sb = new StringBuffer();
			while (s != null) {
				// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			reString = sb.toString();
			return reString;
		} else {
			return null;
		}
	}

	/**
	 * base64字符串转换成clob类型
	 * 
	 * @param params
	 * @return
	 * @throws SerialException
	 * @throws SQLException
	 */
	public static java.sql.Clob stringToClob(String params) throws SerialException, SQLException {
		if (StringUtil.isNotBlank(params)) {
			return new javax.sql.rowset.serial.SerialClob(params.toCharArray());
		} else {
			return null;
		}
	}
}