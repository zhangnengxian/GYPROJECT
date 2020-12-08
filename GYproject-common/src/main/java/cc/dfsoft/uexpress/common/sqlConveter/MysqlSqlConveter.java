package cc.dfsoft.uexpress.common.sqlConveter;

import org.springframework.stereotype.Repository;

@Repository("mysqlSqlConveter")
public class MysqlSqlConveter implements SqlFunctionConverter {

	/**
	 * 获取字符串的长度，汉字算一个字符
	 */
	@Override
	public String funcLenConvert(String param1) {
		return "char_length(" + param1 +")";
	}

	@Override
	public String funcLenConvert() {
		return "char_length";
	}

	/**
	 * 截取字符串，从1开始
	 */
	@Override
	public String funcSubstringConvert(String param1, int start, int length) {
		return "substring("+param1+","+start+","+length+")";
	}

	@Override
	public String funcSubstringConvert(String param1, int start) {
		return "substring("+param1+","+start+")";
	}

	@Override
	public String funcSubstringConvert() {
		return "substring";
	}

	@Override
	public String funcGetdateConvert() {
		return "sysdate()";
	}

	@Override
	public String funcGetdateFromConvert() {
		return "sysdate()";
	}

	@Override
	public String dataOperate(String param1) {
		
		return "str_to_date('"+param1+"','%Y-%m-%d %H:%i:%s')";
	}

	@Override
	public String dateYearPart(String param1) {
		return "DATE_FORMAT(" + param1 + ",'%Y')";
	}

	@Override
	public String dateMonthPart(String param1) {
		return "DATE_FORMAT(" + param1 + ",'%m')";
	}

	@Override
	public String dateDayPart(String param1) {
		return "DATE_FORMAT(" + param1 + ",'%d')";
	}

	@Override
	public String dateHourPart(String param1) {
		return "DATE_FORMAT(" + param1 + ",'%H')";
	}

	@Override
	public String funcIsnullConvert(String param1, String param2) {
		return "IFNULL(" + param1 + "," + param2 + ") ";
	}

	@Override
	public String funcIsnullConvert() {
		return "IFNULL";
	}

	//将时间字符串转换为 年-月-日 时-分秒 日期格式
	@Override
	public String funcConvert(String type, String target, int format) {
		return " date_format(" + target + ",'%Y-%m-%d %H:%i:%s') ";
	}

	@Override
	public String funcConvert(String type, String target) {
		return "date_format";
	}

	@Override
	public String funcConcat(String sql) {
		return null;
	}

	@Override
	public String funcConvert(String type, String target, String formate) {
		return "date_format(" + target +"," + formate + ")";
	}

	@Override
	public String funcConvertToNumber(String target) {
		return null;
	}

	/**
	 * 将日期转换为 年-月-日 类型
	 */
	@Override
	public String funcConvertISO(String type, String target, int format) {
		return "date_format(" + target + ",'%Y-%m-%d') ";
	}
	/**
	 * 将日期转换为 年月日 
	 */

	@Override
	public String funcConvertISO2(String type, String target, int format) {
		return "date_format(" + target + ",'%Y%m%d') ";
	}

	@Override
	public String funcConnactString() {
		return null;
	}

	@Override
	public String funcDateAdd(String type, String number, String date) {
		return "DATE_ADD(" + date+ ",INTERVAL " + number + " " + type + ")";
	}

	/**
	 * 两个日期之间的天数
	 */
	@Override
	public String funcDateDiff(String type, String date1, String date2) {
		return "DATEDIFF('" + date1+ "','" + date2 + "')";
	}
	
	/**
	 * 在字符串key 的start位置到length位置替换为expression
	 */
	@Override
	public String funcReplaceConvert(String key, int start, int length,
			String expression) {
		return "INSERT(" +key+ "," +start+ "," +length+ "," +expression+ ")";
	}

	/**
	 * 获取日期的 年-月
	 */
	@Override
	public String dataOperateYearAndMonth(String param) {
		return "date_format("+param+",'%Y-%m')";
	}
	
	@Override
	public String funcNvl2(String expr1,String expr2,String expr3){
		return "if("+expr1+","+expr2+","+expr3+")";
	}

	@Override
	public String funcNvl2() {
		// TODO Auto-generated method stub
		return "if";
	}

	@Override
	public String funcAddMonths(String sysDate, int month) {
		return "DATE_ADD("+sysDate+", interval "+month+" month)";
	}

	@Override
	public String rownumberConveter(String str) {
		return "(@row_number\\:=@row_number+1) as "+str+" (SELECT @row_number\\:=0) as n,";
	}
	
	public String rownumOverConveter(String col1,String col2,String strSql ){
		return null;
	}
}
